package teste;

import java.util.List;
import com.google.gson.Gson;
//Import necessario para deserializar uma lista em Json para um ArrayList.
//import com.google.gson.reflect.TypeToken;
//import java.lang.reflect.Type;
//
import br.com.sigevendees.dao.ComponenteDao;
import br.com.sigevendees.entity.Componente;
import br.com.sigevendees.enums.CategoryTypes;
import br.com.sigevendees.enums.MeasureUnits;
import br.com.sigevendees.repository.ComponenteRepository;

public class TestaComponenteRepository {
	static ComponenteDao dao = new ComponenteDao();
	static ComponenteRepository repo = new ComponenteRepository();
	static Gson gson = new Gson();

	public static void testaConverterJavaParaJson_e_vice_versa(Integer cod) {
		Componente componente = dao.buscarPor(cod);
		System.out.println("\nComponente Recuperados");
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
		System.out.println("\nComponentes Recuperados");
		for (Componente componente : lista) {
			System.out.println(componente);
		}
		
		System.out.println("\nComponentes em JSON");
		String listEmJson = repo.converterParaArrayJSON(lista);
		System.out.println(listEmJson);
		
		System.out.println("\nComponentes em JAVA");
		//Type collectionType = new TypeToken<List<Componente>>() {}.getType();
		//lista = gson.fromJson(listEmJson, collectionType);
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

		testaConverterJavaParaJson_e_vice_versa(1);
		/* JSON RETORNADO:
		 {
		    "codigo": 1,
		    "descricao": "Farinha de trigo",
		    "categoria": "INGREDIENTE",
		    "simbolo": "GRAMA",
		    "estoqueMin": 5000.0,
		    "estoqueAtual": 0.0,    		    
		    "preco": 0.0
		 }
		*/
		testaConverterListJavaParaJson_e_vice_versa();
		/* ArrayJSON RETORNADO:
		[
		    {
		        "codigo": 1,
		        "descricao": "Farinha de trigo",
		        "categoria": "INGREDIENTE",
		        "simbolo": "GRAMA",
		        "estoqueMin": 5000.0,
		        "estoqueAtual": 0.0,
		        "preco": 0.0
		    },
		    {
		        "codigo": 2,
		        "descricao": "Leite",
		        "categoria": "INGREDIENTE",
		        "simbolo": "MILILITRO",
		        "estoqueMin": 1000.0,
		        "estoqueAtual": 0.0,
		        "preco": 0.0
		    },
		    {
		        "codigo": 3,
		        "descricao": "Ovo",
		        "categoria": "INGREDIENTE",
		        "simbolo": "UNIDADE",
		        "estoqueMin": 30.0,
		        "estoqueAtual": 0.0,
		        "preco": 0.0
		    },
		    {
		        "codigo": 4,
		        "descricao": "Pote 250ml",
		        "categoria": "EMBALAGEM",
		        "simbolo": "UNIDADE",
		        "estoqueMin": 50.0,
		        "estoqueAtual": 0.0,
		        "preco": 0.0
		    }
		]
		*/
		testaNovoComponente();
	}

}
