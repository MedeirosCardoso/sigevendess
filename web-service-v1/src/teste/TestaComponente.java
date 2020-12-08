package teste;

import br.com.sigevendees.dao.ComponenteDao;
import br.com.sigevendees.entity.Componente;
import br.com.sigevendees.enums.CategoryTypes;
import br.com.sigevendees.enums.MeasureUnits;

public class TestaComponente {
	static ComponenteDao daoComponente = new ComponenteDao();

	public static void testaClasseComponete() {
		// Pega os valores do componente.
		String desc = "Farinha de trigo";
		CategoryTypes tipo = CategoryTypes.INGREDIENTE;
		MeasureUnits tipoUnit = MeasureUnits.GRAMA;
		float estoqueMin = 1;

		// Cria um novo componente;.
		Componente farinha = new Componente(desc, tipo, tipoUnit, estoqueMin);
		System.out.println(farinha);
	}

	public static void testaSalvarComponente() {
		// Cria um novo componente.
		Componente farinha = new Componente("Farinha de trigo", CategoryTypes.INGREDIENTE, MeasureUnits.GRAMA, 1);
		// salva o componente.
		daoComponente.salvar(farinha);
	}

	public static void testaBuscarPorIdComponente(int cod) {
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

	public static void testaAtualizarComponente(int cod) {
		// Busca o componente a ser alterado.
		Componente componente = daoComponente.buscarPor(cod);
		// componente com as informações a ser alterada.
		System.out.println(componente);
		// Altera os valores.
		componente.setDescricao("Ovo br");
		daoComponente.atualizar(componente);
		// componente com as informações atualizadas.
		System.out.println(componente);
	}

	public static void main(String[] args) {
		 testaBuscarPorIdComponente(1);
		// testaBuscarTodos();
	}

}
