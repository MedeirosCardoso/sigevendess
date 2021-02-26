package teste;

import br.com.sigevendees.dao.ComponenteDao;
import br.com.sigevendees.dao.ProdutoDao;
import br.com.sigevendees.entity.Componente;
import br.com.sigevendees.entity.Produto;
import br.com.sigevendees.entity.Receita;
import br.com.sigevendees.enums.CategoryTypes;
import br.com.sigevendees.enums.MeasureUnits;

public class TestaProduto {
	static ProdutoDao daoProduto = new ProdutoDao();
	static ComponenteDao daoComponente = new ComponenteDao();

	public static void testaClasse() {
		// Pega os valores do produto.
		String descProduto = "Bolo de chocolate";
		CategoryTypes tipoDoProduto = CategoryTypes.DOCE;
		MeasureUnits tipoUnit = MeasureUnits.UNIDADE;
		float precoVenda = 7;

		// Pega os dados da receita.
		int rendimento = 1;
		float tempPreparo = 0.30f;
		// Cria a receita.
		Receita receita = new Receita(rendimento, tempPreparo);

		// Busca a lista de componentes.
		Componente farinha = new Componente("Farinha de trigo", CategoryTypes.INGREDIENTE, MeasureUnits.GRAMA, 1);
		Componente leite = new Componente("Leite integral", CategoryTypes.INGREDIENTE, MeasureUnits.MILILITRO, 1);
		Componente pote = new Componente("Pote descartável", CategoryTypes.EMBALAGEM, MeasureUnits.UNIDADE, 1);

		// Adiciona na lista da receita o componente e a qtd utilizada.
		receita.addComponente(farinha, 250);
		receita.addComponente(leite, 1);
		receita.addComponente(pote, 1);

		// Cria um novo produto.
		Produto boloDeChocolate = new Produto(descProduto, tipoDoProduto, tipoUnit, precoVenda, receita);
		System.out.println(boloDeChocolate);
	}

	public static void testaSalvar() {
		// Busca a lista de componentes.
		Componente farinha = daoComponente.buscarPor(1);
		Componente ovo = daoComponente.buscarPor(2);
		Componente pote = daoComponente.buscarPor(3);
		// Cria uma nova receita (rendimento, tempoPreparo).
		Receita receita = new Receita(100, 0.60f);
		// Adiciona na lista da receita componente e a qtd utilizada.
		receita.addComponente(farinha, 250);
		receita.addComponente(ovo, 2);
		receita.addComponente(pote, 1);
		// Cria um novo produto e passa a sua receita.
		Produto bolo = new Produto("Bolo", CategoryTypes.DOCE, MeasureUnits.UNIDADE, 7, receita);
		// Salva o produto e sua receita.
		if (daoProduto.salvar(bolo)) {
			System.out.println("Produto salvo com sucesso!");
		} else {
			System.out.println("Não foi possivel salvar o produto!");
		}

	}

	public static void testaBuscarPorId(int cod) {
		Produto bolo = daoProduto.buscarPor(cod);
		if (bolo != null) {
			System.out.print(bolo);
		} else {
			System.out.print("Produto não cadastrado!");
		}
	}

	public static void testaBuscarProdutoComReceita(int cod) {
		Produto boloDeChocolate = daoProduto.buscarProduto_e_sua_receita(cod);
		if (boloDeChocolate != null) {
			System.out.println(boloDeChocolate);
		} else {
			System.out.print("Produto não cadastrado!");
		}

	}

	public static void testaBuscarTodos() {
		for (Produto produto : daoProduto.buscarTodos()) {
			System.out.println(produto);
		}
	}

	public static void testaBuscarTodosComReceita() {
		for (Produto produto : daoProduto.buscarProdutos_e_suas_receitas()) {
			System.out.println(produto);
			System.out.println();
		}
	}

	public static void testaAtualizar(int cod) {
		Produto produto = daoProduto.buscarPor(cod);
		if (produto != null) {
			// produto com as informações a ser alterada.
			System.out.println(produto);
			// Altera os valores.
			produto.setDescricao("Bolo de chocolate");
			daoProduto.atualizar(produto);
			// produto com as informações atualizadas.
			System.out.println(produto);
		} else {
			System.out.print("Produto não cadastrado!");
		}
	}

	public static void testaAtualizarProduto_e_sua_Receita(int cod) {
		Produto produto = daoProduto.buscarProduto_e_sua_receita(cod);
		if (produto != null) {
			// produto com as informações a ser alterada.
			System.out.println(produto);
			// Altera a quantidade utilizada para 3 do componente com index 4 da lista de
			// componentes do produto.
			produto.getReceita().getComponentes().get(1).setQtdUtilizada(3);
			daoProduto.atualizar(produto);
			// produto com as informações atualizadas.
			System.out.println(produto);
		} else {
			System.out.print("Produto não cadastrado!");
		}
	}

	public static void main(String[] args) {
		 testaClasse();
		// testaSalvar();
		// testaBuscarPorId(4);
		// testaBuscarProdutoComReceita(4);
		// testaBuscarTodos();
		// testaBuscarTodosComReceita();
		// testaAtualizar(4);
		// testaAtualizarProduto_e_sua_Receita(4);
	}

}
