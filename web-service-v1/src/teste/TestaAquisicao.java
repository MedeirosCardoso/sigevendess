package teste;

import br.com.sigevendees.dao.ComponenteDao;
import br.com.sigevendees.dao.AquisicaoDao;
import br.com.sigevendees.entity.Aquisicao;
import br.com.sigevendees.entity.Componente;
import br.com.sigevendees.entity.ItemAquisicao;
import br.com.sigevendees.enums.CategoryTypes;
import br.com.sigevendees.enums.MeasureUnits;

public class TestaAquisicao {
	static ComponenteDao daoComponente = new ComponenteDao();
	static AquisicaoDao daoAquisicao = new AquisicaoDao();

	public static void testaClasse() {
		//Cria uma nova Aquisicao.
		Aquisicao aquisicao = new Aquisicao();
		
		//Pega a lista de componentes.
		Componente farinha = new Componente("Farinha de trigo", CategoryTypes.INGREDIENTE, MeasureUnits.GRAMA, 1);
		Componente leite = new Componente("Leite integral", CategoryTypes.INGREDIENTE, MeasureUnits.MILILITRO, 1);
		Componente pote = new Componente("Pote descartável", CategoryTypes.EMBALAGEM, MeasureUnits.UNIDADE, 1);
		
		//Adiciona na lista de componentes da Aquisicao.
		aquisicao.addComponente(farinha, 500, 10);
		aquisicao.addComponente(leite, 30, 10);
		aquisicao.addComponente(pote, 50, (float) 19.9);
		
		System.out.println("DADOS ESTOQUE ATUAL COMPONENTE");
		System.out.println(farinha);
		System.out.println(leite);
		System.out.println(pote);
		
		
		System.out.println("DADOS DA AQUISIÇÃO");
		System.out.println(aquisicao);
		
		aquisicao.atualizarEstoqueComponente();
		
		System.out.println("DADOS NOVO ESTOQUE COMPONENTE");
		System.out.println(farinha);
		System.out.println(leite);
		System.out.println(pote);
		
		Aquisicao aquisicao2 = new Aquisicao();
		aquisicao2.addComponente(farinha, 500, 15);
		aquisicao2.addComponente(leite, 30, 15);
		aquisicao2.addComponente(pote, 50, (float) 24.9);
		
		System.out.println("DADOS DA AQUISIÇÃO 2");
		System.out.println(aquisicao2);
		
		aquisicao2.atualizarEstoqueComponente();
		
		System.out.println("DADOS NOVO ESTOQUE COMPONENTE 2");
		System.out.println(farinha);
		System.out.println(leite);
		System.out.println(pote);
	}
	
	public static void testaSalvar() {
		// Busca a lista de componentes.
		Componente farinha = daoComponente.buscarPor(1);
		Componente ovo = daoComponente.buscarPor(2);
		Componente pote = daoComponente.buscarPor(3);

		//Cria uma nova Aquisicao.
		Aquisicao aquisicao = new Aquisicao();
		
		//Adiciona na lista de componentes da aquisicao(componente, qtdAdquirida, custo).
		aquisicao.addComponente(farinha, 500, 15);
		aquisicao.addComponente(ovo, 30, 15);
		aquisicao.addComponente(pote, 50, (float) 24.9);

		if (daoAquisicao.salvar(aquisicao)) {
			System.out.println("Aquisição salvo com sucesso!");
			// Atualizar o estoque dos componentes adquiridos.
			aquisicao.atualizarEstoqueComponente();
			for (ItemAquisicao item : aquisicao.getComponentes()) {
				if (daoComponente.atualizar(item.getComponente())) {
					System.out.println("Estoque atualizado com sucesso!");
				}
			}
		} else {
			System.out.println("Não foi possivel salvar aquisição!");
		}
	}
	
	public static void testaBuscarPorId(int cod) {
		Aquisicao resultado = daoAquisicao.buscarPor(cod);
		if (resultado != null) {
			System.out.print(resultado);
		} else {
			System.out.print("Aquisição não cadastrada!");
		}
	}
	
	public static void testaBuscarTodos() {
		for(Aquisicao aquisicao: daoAquisicao.buscarTodos()) {
			System.out.println(aquisicao);
			System.out.println();
		}
	}
	
	public static void testaAtualizar(int cod) {
		Aquisicao aquisicao = daoAquisicao.buscarPor(cod);
		if (aquisicao != null) {
			// Aquisicao com as informações a ser alterada.
			System.out.println(aquisicao);
			// Altera a quantidade utilizada para 500 do componente com index 0 da lista de componentes.
			aquisicao.getComponentes().get(0).setQtdAdquirida(500);;
			daoAquisicao.atualizar(aquisicao);
			// Aquiscao com as informações atualizadas.
			System.out.println(aquisicao);
		} else {
			System.out.print("Aquisição não cadastrada!");
		}
	}
	
	public static void main(String[] args) {
		// testaClasse();
		// testaSalvar();
		// testaBuscarPorId(6);
		// testaBuscarTodos();
		// testaAtualizar(6);
	}

}
