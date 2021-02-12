package teste;

import br.com.sigevendees.dao.ComponenteDao;
import br.com.sigevendees.entity.Componente;
import br.com.sigevendees.enums.CategoryTypes;
import br.com.sigevendees.enums.MeasureUnits;

public class TestaComponente {
	static ComponenteDao daoComponente = new ComponenteDao();

	public static void testaClasse() {
		// Pega os valores do componente.
		String desc = "Farinha de trigo";
		CategoryTypes tipo = CategoryTypes.INGREDIENTE;
		MeasureUnits tipoUnit = MeasureUnits.GRAMA;
		float estoqueMin = 1;

		// Cria um novo componente;.
		Componente farinha = new Componente(desc, tipo, tipoUnit, estoqueMin);
		System.out.println(farinha);
	}

	public static void testaSalvar() {
		// Cria um novo componente.
		Componente farinha = new Componente("Farinha de trigo", CategoryTypes.INGREDIENTE, MeasureUnits.GRAMA, 1);
		// salva o componente.
		if (daoComponente.salvar(farinha)) {
			System.out.println("Componente salvo com sucesso!");
		} else {
			System.out.println("Não foi possivel salvar o Componente!");
		}

	}

	public static void testaBuscarPorId(int cod) {
		Componente farinha = daoComponente.buscarPor(cod);
		if (farinha != null) {
			System.out.print(farinha);
		} else {
			System.out.print("Componente não cadastrado!");
		}
	}

	public static void testaBuscarTodos() {
		for (Componente componente : daoComponente.buscarTodos()) {
			System.out.println(componente);
			System.out.println();
		}
	}

	public static void testaAtualizar(int cod) {
		// Busca o componente a ser alterado.
		Componente componente = daoComponente.buscarPor(cod);
		// Componente com as informações a ser alterada.
		System.out.println(componente);
		// Altera os valores.
		componente.setDescricao("Ovo br");
		// Salvar as aleterações.
		daoComponente.atualizar(componente);
		// Componente com as informações atualizadas.
		System.out.println(componente);
	}

	public static void main(String[] args) {
		// testaClasse();
		// testaSalvar();
		// testaBuscarPorId(3);
		// testaBuscarTodos();
		// testaAtualizar(2);
	}

}
