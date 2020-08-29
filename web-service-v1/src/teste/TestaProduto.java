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

	public static void testaClasseProduto() {
		// Pega os valores do produto.
		String descProduto = "Bolo de chocolate";
		CategoryTypes tipoDoProduto = CategoryTypes.DOCE;
		MeasureUnits tipoUnit = MeasureUnits.UNIDADE;
		float precoVenda = 7;

		// Pega os dados da receita.
		int rendimento = 1;
		float tempPreparo = 0.30f;
		// Cria a receita;
		Receita receita = new Receita(rendimento, tempPreparo);

		// Busca a lista de componentes.
		Componente farinha = new Componente("Farinha de trigo", CategoryTypes.INGREDIENTE, MeasureUnits.GRAMA, 1);
		Componente leite = new Componente("Leite integral", CategoryTypes.INGREDIENTE, MeasureUnits.MILILITRO, 1);
		Componente pote = new Componente("Pote descartável", CategoryTypes.EMBALAGEM, MeasureUnits.UNIDADE, 1);

		// Adiciona na lista da receita o componente e a qtd utilizada.
		receita.addComponente(farinha, 250);
		receita.addComponente(leite, 1);
		receita.addComponente(pote, 1);

		// Cria um novo produto;
		Produto boloDeChocolate = new Produto(descProduto, tipoDoProduto, tipoUnit, precoVenda, receita);
		System.out.println(boloDeChocolate);
	}

	public static void testaSalvarProduto() {
		// Busca a lista de componentes.
		Componente farinha = daoComponente.buscarPorId(1);
		Componente leite = daoComponente.buscarPorId(2);
		Componente pote = daoComponente.buscarPorId(3);
		// Cria uma nova receita;
		Receita receita = new Receita(100, 0.60f);
		// Adiciona na lista da receita o componente e a qtd utilizada.
		receita.addComponente(farinha, 250);
		receita.addComponente(leite, 1);
		receita.addComponente(pote, 1);
		// Cria um novo produto e passa a sua receita.
		Produto hotDog = new Produto("Coxinha", CategoryTypes.SALGADO, MeasureUnits.UNIDADE, 7, receita);
		// Salva o produto e sua receita.
		if (daoProduto.salvar(hotDog)) {
			System.out.println("Produto salvo com sucesso!");
		} else {
			System.out.println("Não foi possivel salvar o produto!");
		}

	}

	public static void testaBuscarPorIdProduto(int cod) {
		Produto boloDeChocolate = daoProduto.buscarPorId(cod);
		if (boloDeChocolate != null) {
			System.out.print(boloDeChocolate);
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
		for (Produto produto : daoProduto.buscarTodos_e_suas_receitas()) {
			System.out.println(produto);
			System.out.println();
		}
	}

	public static void testaAtualizarProduto(int cod) {
		Produto produto = daoProduto.buscarPorId(cod);
		// produto com as informações a ser alterada.
		System.out.println(produto);
		// Altera os valores.
		produto.setDescricao("Sanduíche natural");
		daoProduto.atualizar(produto);
		// produto com as informações atualizadas.
		System.out.println(produto);
	}

	public static void testaAtualizarProduto_e_suaReceita(int cod) {
		Produto produto = daoProduto.buscarProduto_e_sua_receita(cod);
		// produto com as informações a ser alterada.
		System.out.println(produto);
		// Altera a quantidade utilizada para 1 do componente com index 4 da lista de componentes do produto.
		produto.getReceita().getComponentes().get(4).setQtdUtilizada(1);
		daoProduto.atualizar(produto);
		// produto com as informações atualizadas.
		System.out.println(produto);
	}

	public static void main(String[] args) {
		 testaSalvarProduto();
		// testaBuscarPorIdProduto(6);
		// testaBuscarProdutoComReceita(6);
		// testaBuscarTodos();
		// testaBuscarTodosComReceita();
		// testaAtualizarProduto(8);
		// testaAtualizarProduto_e_suaReceita(8);
	}

}
