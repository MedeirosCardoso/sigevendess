package br.com.sigevendees.repository;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Response;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;
import br.com.sigevendees.dao.DaoInterface;
import br.com.sigevendees.dao.ProdutoDao;
import br.com.sigevendees.entity.ItemReceita;
import br.com.sigevendees.entity.Produto;
import br.com.sigevendees.entity.Receita;

public class ProdutoRepository extends GenericRepository<Produto, DaoInterface<Produto>> {

	public ProdutoRepository() {
		dao = new ProdutoDao();
		classe = Produto.class;
	}

	@Override
	public Response novo(String dadosEmJson) {
		// pega os dados em JSON vindo do front.
		// converter para objeto java do tipo Produto.
		Produto dadosNovoProduto = converterParaJava(dadosEmJson);
		if (dadosNovoProduto != null) {
			// convertido com sucesso.
			// pegar os dados para criar um novo Produto:

			// criar a Receita com os dados enviado para o novo Produto.
			Receita receita = new Receita(dadosNovoProduto.getReceita().getRendimento(),
					dadosNovoProduto.getReceita().getTempoPreparo());
			// pegar a lista de componentes que ira compor a receita do produto.
			// adiciona na lista da receita o componente e a qtd utilizada.
			for (ItemReceita item : dadosNovoProduto.getReceita().getComponentes()) {
				receita.addComponente(item.getComponete(), item.getQtdUtilizada());
			}
			// criar um novo Produto com os dados enviado e com a receita pronta.
			Produto produto = new Produto(dadosNovoProduto.getDescricao(), dadosNovoProduto.getCategoria(),
					dadosNovoProduto.getSimbolo(), dadosNovoProduto.getPreco(), receita);
			// persistir no BD o novo Produto.
			if (dao.salvar(produto)) {
				// novo produto foi criado e persistido no BD com sucesso.
				resposta = mensagemCriadoRecurso();
			} else {
				// os dados informado possui campos inválidos.
				resposta = mensagemDadosInvalidos();
			}
		} else {
			// aconteceu um erro com o servidor;
			resposta = mensagemErro();
		}
		return resposta;
	}

	public Response getProduto_e_sua_receita(Integer codigo) {
		// guarda os valores retornados do BD.
		Produto resultado = ((ProdutoDao) dao).buscarProduto_e_sua_receita(codigo);
		if (resultado != null) {
			// pega os componentes da receita do produto retornado.
			List<ItemReceita> componentes = new ArrayList<>();
			for (ItemReceita item : resultado.getReceita().getComponentes()) {
				componentes.add(new ItemReceita(item.getComponete(), item.getQtdUtilizada()));
			}
			// pega a receita do produto retornado.
			Receita receitaEmJava = new Receita(resultado.getReceita().getCodigo(),
					resultado.getReceita().getRendimento(), resultado.getReceita().getTempoPreparo(), componentes);
			// Instancia um novo produto com os dados do produto retornado do BD.
			Produto produtoEmJava = new Produto(resultado.getCodigo(), resultado.getDescricao(),
					resultado.getCategoria(), resultado.getSimbolo(), resultado.getPreco(), receitaEmJava);
			// converte o novo produto para uma string em JSON.
			String produtoEmJson = converterParaJSON(produtoEmJava);
			// retorna um response com o produto em JSON.
			resposta = mensagemSucesso(produtoEmJson);
		} else {
			// retorna um response informando que o Id informado não possui cadastro.
			resposta = mensagemNaoEncontrado();
		}
		return resposta;
	}

	public Response getProdutos_e_suas_receitas() {
	// guarda os valores retornados do BD.
	List<Produto> lista = ((ProdutoDao) dao).buscarTodos_e_suas_receitas();
	if (lista != null) {
		List<Produto> produtos = new ArrayList<>();
		// percorrer a lista retornado do BD.
		for (Produto produto : lista) {
			List<ItemReceita> componentes = new ArrayList<>();
			// pega os componentes da receita de cada produto.
			for (ItemReceita item : produto.getReceita().getComponentes()) {
				componentes.add(new ItemReceita(item.getComponete(), item.getQtdUtilizada()));
			}
			// add um novo produto com os dados da lista percorrida.
			produtos.add(new Produto(produto.getCodigo(), produto.getDescricao(), produto.getCategoria(),
					produto.getSimbolo(), produto.getPreco(),
					new Receita(produto.getReceita().getCodigo(), produto.getReceita().getRendimento(),
							produto.getReceita().getTempoPreparo(), componentes)));
		}
		// converte a lista com os novos produtos para uma string em JSON.
		String listaEmJson = converterParaArrayJSON(produtos);
		// retorna um response com o lista de produtos em JSON.
		resposta = mensagemSucesso(listaEmJson);
	} else {
		// retorna um response informando que não possui cadastro.
		resposta = mensagemNaoEncontrado();
	}
	return resposta;

}

	@Override
	public List<Produto> converterParaListaJava(String listaEmJson) {
		/*
		 * Gson não aceita objetos genérico, falha ao desserializar os values. // Para
		 * resolver esse problema, é necessario especificar o tipo parametrizado correto
		 * para seu tipo genérico. // Para isso é necessario o uso da classe TypeToken.
		 * FONTE: https://sites.google.com/site/gson/gson-user-guide#TOC-Array-Examples
		 */
		Type collectionType = new TypeToken<List<Produto>>() {}.getType();
		try {
			List<Produto> lista = gson.fromJson(listaEmJson, collectionType);
			return lista;
		} catch (Exception e) {
			System.out.println("ERRO! Não foi possivel converter o Objeto informado \n" + "Motivo: ");
			e.printStackTrace();
			return null;
		}
	}
}
