package teste;

import br.com.sigevendees.dao.ClienteDao;
import br.com.sigevendees.entity.Cliente;
import br.com.sigevendees.entity.Endereco;
import br.com.sigevendees.entity.Telefone;

public class TestaCliente {
	static ClienteDao dao = new ClienteDao();
	
	public static void testaClasseCliente() {
		// pega os dados do cliente.
		Telefone telefone = new Telefone((byte) 47,999999999);
		String nome = "CLIENTE TESTE";
		String email = "cliente@email.com";
		String estabelecimento = "LOJA 001";
		String obs = "Testando a class Cliente.";
		Endereco endereco = new Endereco(89037001, "RUA A", (short) 00, "CENTRO", "APARTAMENTO", "BLUMENAU", "SC");
		
		// Instância Cliente com os dados recebidos.
		Cliente cliente = new Cliente(telefone,nome,email,estabelecimento,obs,endereco);
		
		// Mostrar cliente.
		System.out.println(cliente);
	}
	
	public static void testaSalvarCliente() {
		// pega os dados do cliente.
		Telefone telefone = new Telefone((byte) 47,999999999);
		String nome = "CLIENTE TESTE";
		String email = "cliente@email.com";
		String estabelecimento = "LOJA 001";
		String obs = "TESTANDO A CLASSE CLIENTE.";
		Endereco endereco = new Endereco(89037001, "RUA A", (short) 00, "CENTRO", "APARTAMENTO", "BLUMENAU", "SC");
		
		// Instância Cliente com os dados recebidos.
		Cliente cliente = new Cliente(telefone,nome,email,estabelecimento,obs,endereco);
		
		// Salvar dados do cliente.
		if (dao.salvar(cliente)) {
			System.out.println("Cliente salvo com sucesso!");
		} else {
			System.out.println("Não foi possivel salvar dados do cliente!");
		}
	}
	
	public static void testaBuscarPorId(byte ddd, int digito) {
		Telefone telefoneInformado =  new Telefone(ddd, digito);
		Cliente resultado = dao.buscarPor(telefoneInformado);
		
		if (resultado != null) {
			System.out.print(resultado);
		} else {
			System.out.print("Cliente não cadastrado!");
		}
	}
	
	public static void main(String[] args) {
		//testaClasseCliente();
		//testaSalvarCliente();
		testaBuscarPorId((byte) 47,999999999);
	}

}
