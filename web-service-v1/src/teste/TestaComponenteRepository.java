package teste;

import java.util.List;
import br.com.sigevendees.dao.ComponenteDao;
import br.com.sigevendees.entity.Componente;
import br.com.sigevendees.enums.CategoryTypes;
import br.com.sigevendees.enums.MeasureUnits;
import br.com.sigevendees.repository.ComponenteRepository;

public class TestaComponenteRepository {
	static ComponenteDao dao = new ComponenteDao();
	static ComponenteRepository repo = new ComponenteRepository();

	public static void testaConverterJavaParaJson_e_vice_versa(Integer cod) {
		Componente componente = dao.buscarPor(cod);
		System.out.println("\nComponente Retornado");
		System.out.println(componente);

		System.out.println("\nComponente em JSON");
		String componenteEmJson = repo.converterParaJSON(componente);
		System.out.println(componenteEmJson);

		componente = repo.converterParaJava(componenteEmJson);
		System.out.println("\nComponente em JAVA");
		System.out.println(componente);

	}

	public static void testaConverterListJavaParaJson_e_vice_versa() {
		List<Componente> lista = dao.buscarTodos();
		System.out.println("\nComponentes Retornado");
		for (Componente componente : lista) {
			System.out.println(componente);
		}

		System.out.println("\nComponentes em JSON");
		String listEmJson = repo.converterParaArrayJSON(lista);
		System.out.println(listEmJson);

		System.out.println("\nComponentes em JAVA");
		lista = repo.converterParaListaJava(listEmJson);
		for (Componente componente : lista) {
			System.out.println(componente);
		}

	}

	public static void testaNovoComponente() {
		// Cria um novo componente.
		Componente farinha = new Componente("Farinha integral", CategoryTypes.INGREDIENTE, MeasureUnits.GRAMA, 1);
		// converter componente para string em JSON.
		String farinhaEmJson = repo.converterParaJSON(farinha);
		// passar para o controller persistir no BD.
		if (!farinhaEmJson.isEmpty()) {
			System.out.println("CONTROLL " + repo.novo(farinhaEmJson));
		} else {
			System.out.println("ERRO! " + farinhaEmJson);
		}
	}

	public static void main(String[] args) {
		// testaConverterJavaParaJson_e_vice_versa(3);
		// testaConverterListJavaParaJson_e_vice_versa();
		// testaNovoComponente();
	}

}
