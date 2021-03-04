package teste;

import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import br.com.sigevendees.dao.ComponenteDao;
import br.com.sigevendees.dao.ProdutoDao;
import br.com.sigevendees.entity.ItemReceita;
import br.com.sigevendees.entity.Produto;
import br.com.sigevendees.entity.Receita;
import br.com.sigevendees.repository.ProdutoRepository;

public class TestaProdutoRepository {

	static ProdutoDao dao = new ProdutoDao();
	static ComponenteDao daoComponente = new ComponenteDao();
	static ProdutoRepository repo = new ProdutoRepository();
	static Gson gson = new Gson();

	public static void testaConverterProdutoParaJson_e_vice_versa(Integer cod) {
		Produto produto = dao.buscarPor(cod);
		System.out.println("\nProduto Retornado");
		System.out.println(produto);

		System.out.println("\n Produto em JSON");
		String produtoEmJson = repo.converterParaJSON(produto);
		System.out.println(produtoEmJson);

		System.out.println("\nProduto em JAVA");
		produto = repo.converterParaJava(produtoEmJson);
		System.out.println(produto);
	}

	public static void testaConverterListJavaParaJson_e_vice_versa() {
		List<Produto> lista = dao.buscarTodos();
		System.out.println("\nProdutos Retornado");
		for (Produto produto : lista) {
			System.out.println(produto);
		}
		System.out.println("\n Produtos em JSON");
		String listEmJson = repo.converterParaArrayJSON(lista);
		System.out.println(listEmJson);

		System.out.println("\nProdutos em JAVA");
		// Type collectionType = new TypeToken<List<Produto>>() {}.getType();
		// lista = gson.fromJson(listEmJson, collectionType);
		lista = repo.converterParaListaJava(listEmJson);
		for (Produto produto : lista) {
			System.out.println(produto);
		}
	}

	public static void testaConverterProduto_e_sua_receita_paraJson_e_vice_versa(Integer cod) {
		// guarda os valores retornados do BD.
		Produto resultado = dao.buscarProduto_e_sua_receita(cod);
		System.out.println("\nProduto Retornado");
		System.out.println(resultado);

		System.out.println("\nProduto em JSON");
		// pega os componentes da receita do produto retornado.
		List<ItemReceita> componentes = new ArrayList<>();
		for (ItemReceita item : resultado.getReceita().getComponentes()) {
			componentes.add(new ItemReceita(item.getComponente(), item.getQtdUtilizada()));
		}
		// pega a receita do produto retornado.
		Receita receitaEmJava = new Receita(resultado.getReceita().getCodigo(), resultado.getReceita().getRendimento(),
				resultado.getReceita().getTempoPreparo(), componentes);
		// Instancia um novo produto com os dados do produto retornado do BD.
		Produto produtoEmJava = new Produto(resultado.getCodigo(), resultado.getDescricao(), resultado.getCategoria(),
				resultado.getSimbolo(), resultado.getPreco(), receitaEmJava);
		// converte o novo produto para uma string em JSON.
		String produtoEmJson = repo.converterParaJSON(produtoEmJava);
		// retorna um response com o produto em JSON.
		System.out.println(produtoEmJson);

		System.out.println("\nProduto em JAVA");
		produtoEmJava = repo.converterParaJava(produtoEmJson);
		System.out.println(produtoEmJava);
	}

	public static void testaConverterListProdutos_e_suas_receitas_paraJson_e_vice_versa() {
		List<Produto> produtos = dao.buscarProdutos_e_suas_receitas();
		System.out.println("\nProdutos Retornado");
		for (Produto p : produtos) {
			System.out.println(p + "\n");
		}

		System.out.println("\nProdutos em JSON");
		List<Produto> lista = new ArrayList<>();
		for (Produto produto : produtos) {
			List<ItemReceita> componentes = new ArrayList<>();
			for (ItemReceita item : produto.getReceita().getComponentes()) {
				componentes.add(new ItemReceita(item.getComponente(), item.getQtdUtilizada()));
			}
			lista.add(new Produto(produto.getCodigo(), produto.getDescricao(), produto.getCategoria(),
					produto.getSimbolo(), produto.getPreco(),
					new Receita(produto.getReceita().getCodigo(), produto.getReceita().getRendimento(),
							produto.getReceita().getTempoPreparo(), componentes)));
		}
		String listEmJson = repo.converterParaArrayJSON(lista);
		System.out.println(listEmJson);

		System.out.println("\nProdutos em JAVA");
		Type collectionType = new TypeToken<List<Produto>>() {}.getType();
		lista = gson.fromJson(listEmJson, collectionType);
		for (Produto produto : lista) {
			System.out.println(produto);
		}
	}

	public static void testaNovoProduto() {
		// guarda os dados em JSON vindo do front.
		String produtoEmJson = "{'descricao':'Beijinho','categoria':'DOCE','simbolo':'CENTO','preco':7.0,'receita':{'rendimento':100,'tempoPreparo':0.6,'componentes':[{'componete':{'codigo':1},'qtdUtilizada':250.0},{'componete':{'codigo':2},'qtdUtilizada':1.0},{'componete':{'codigo':10},'qtdUtilizada':1}]}}";
		// passar para o controller persistir no BD.
		System.out.println("CONTROLL " + repo.novo(produtoEmJson));
	}

	public static void main(String[] args) {
		// testaConverterProdutoParaJson_e_vice_versa(4);
		// testaConverterListJavaParaJson_e_vice_versa();
		// testaConverterProduto_e_sua_receita_paraJson_e_vice_versa(4);
		// testaConverterListProdutos_e_suas_receitas_paraJson_e_vice_versa();
		// testaNovoProduto();
	}

}
