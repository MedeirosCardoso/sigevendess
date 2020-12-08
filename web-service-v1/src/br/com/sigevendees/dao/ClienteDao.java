package br.com.sigevendees.dao;

import java.util.List;
import br.com.sigevendees.entity.Cliente;
import br.com.sigevendees.entity.Telefone;

public class ClienteDao extends GenericDao<Cliente, Telefone> {
	
	public Cliente buscarPor(Telefone numTelefone) {
		return super.buscarPor(Cliente.class, numTelefone);
	}

	public List<Cliente> buscarTodos() {
		return super.buscarTodos(Cliente.class);
	}

}
