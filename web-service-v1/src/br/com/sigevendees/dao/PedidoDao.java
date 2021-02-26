package br.com.sigevendees.dao;

import java.util.List;
import br.com.sigevendees.entity.Pedido;

public class PedidoDao extends GenericDao<Pedido, Integer> {

	@Override
	public Pedido buscarPor(Integer codigo) {
		setJpql(null);
		return buscarPor(Pedido.class, codigo);
	}

	@Override
	public List<Pedido> buscarTodos() {
		setJpql("FROM Pedido");
		return buscarTodos(Pedido.class);
	}

}
