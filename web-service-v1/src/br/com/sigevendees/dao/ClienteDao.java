package br.com.sigevendees.dao;

import java.util.List;
import br.com.sigevendees.entity.Cliente;
import br.com.sigevendees.entity.Telefone;

public class ClienteDao extends GenericDao<Cliente, Telefone> {

	@Override
	public Cliente buscarPor(Telefone numero) {
		setJpql(null);
		return buscarPor(Cliente.class, numero);
	}

	@Override
	public List<Cliente> buscarTodos() {
		setJpql("FROM Cliente");
		return buscarTodos(Cliente.class);
	}

}
