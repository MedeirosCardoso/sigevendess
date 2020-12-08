package teste;

import java.util.List;
import com.google.gson.Gson;
import br.com.sigevendees.dao.ClienteDao;
import br.com.sigevendees.entity.Cliente;
import br.com.sigevendees.entity.Endereco;
import br.com.sigevendees.entity.Telefone;
import br.com.sigevendees.repository.ClienteRepository;

public class TestaClienteRepository {
	
	static ClienteDao dao = new ClienteDao();
	static ClienteRepository repo = new ClienteRepository();
	static Gson gson = new Gson();

	public static void testaConverterJavaParaJson_e_vice_versa(byte ddd, int digito) {
		Telefone telefoneInformado =  new Telefone(ddd, digito);
		Cliente cliente = dao.buscarPor(telefoneInformado);
		System.out.println("\nCliente Recuperado");
		System.out.println(cliente);
		
		System.out.println("\nCliente em JSON");
		String clienteEmJson = repo.converterParaJSON(cliente);
		System.out.println(clienteEmJson);
		
		cliente = repo.converterParaJava(clienteEmJson);
		System.out.println("\nCliente em JAVA");
		System.out.println(cliente);

	}

	public static void testaConverterListJavaParaJson_e_vice_versa() {
		List<Cliente> lista = dao.buscarTodos();
		System.out.println("\nClientes Recuperados");
		for (Cliente cliente : lista) {
			System.out.println(cliente);
		}
		
		System.out.println("\nClientes em JSON");
		String listEmJson = repo.converterParaArrayJSON(lista);
		System.out.println(listEmJson);
		
		System.out.println("\nClientes em JAVA");

		lista = repo.converterParaListaJava(listEmJson);
		for (Cliente cliente : lista) {
			System.out.println(cliente);
		}

	}

	public static void testaNovoCliente() {
		// Cria o endereço do cliente.
		Endereco endereco = new Endereco(89037001, "RUA A", (short) 100, "CENTRO", "APARTAMENTO 101", "BLUMENAU", "SC");
		// Cria um novo cliente.
		Cliente ana = new Cliente(new Telefone((byte) 47,888888888), "Ana Teste","anateste@hotmail.com","Loja001",
				                  "Testando o repository do cliente",endereco);
		// converter cliente para string em JSON.
		String anaEmJson = repo.converterParaJSON(ana);
		// passar para o controller persistir no BD.
		if (!anaEmJson.isEmpty()) {
			System.out.println("CONTROLL " + repo.novo(anaEmJson));
		} else {
			System.out.println("ERRO! " + anaEmJson);
		}
	}

	public static void main(String[] args) {

		testaConverterJavaParaJson_e_vice_versa((byte) 47,999999999);
		/*JSON RETORNADO:
		{
		   "numero":{
		      "ddd":47,
		      "digito":999999999
		   },
		   "nome":"CLIENTE TESTE",
		   "email":"cliente@email.com",
		   "nomEstabelecimento":"LOJA 001",
		   "observacao":"TESTANDO A CLASSE CLIENTE.",
		   "endereco":{
		      "codigo":10,
		      "cep":89037001,
		      "logradouro":"RUA A",
		      "numero":0,
		      "bairro":"CENTRO",
		      "complemento":"APARTAMENTO",
		      "cidade":"BLUMENAU",
		      "estado":"SC"
		   }
		}
		*/
		
		testaNovoCliente();
		
		testaConverterListJavaParaJson_e_vice_versa();
		/* ArrayJSON RETORNADO:
		[
		   {
		      "numero":{
		         "ddd":47,
		         "digito":888888888
		      },
		      "nome":"Ana Teste",
		      "email":"anateste@hotmail.com",
		      "nomEstabelecimento":"Loja001",
		      "observacao":"Testando o repository do cliente",
		      "endereco":{
		         "codigo":11,
		         "cep":89037001,
		         "logradouro":"RUA A",
		         "numero":100,
		         "bairro":"CENTRO",
		         "complemento":"APARTAMENTO 101",
		         "cidade":"BLUMENAU",
		         "estado":"SC"
		      }
		   },
		   {
		      "numero":{
		         "ddd":47,
		         "digito":999999999
		      },
		      "nome":"CLIENTE TESTE",
		      "email":"cliente@email.com",
		      "nomEstabelecimento":"LOJA 001",
		      "observacao":"TESTANDO A CLASSE CLIENTE.",
		      "endereco":{
		         "codigo":10,
		         "cep":89037001,
		         "logradouro":"RUA A",
		         "numero":0,
		         "bairro":"CENTRO",
		         "complemento":"APARTAMENTO",
		         "cidade":"BLUMENAU",
		         "estado":"SC"
		      }
		   }
		]
		*/
	}
}
