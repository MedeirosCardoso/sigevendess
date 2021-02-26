package br.com.sigevendees.dao;

import java.util.List;

import br.com.sigevendees.entity.Venda;

public class VendaDao extends GenericDao<Venda, Integer> {

	@Override
	public Venda buscarPor(Integer codigo) {
		setJpql("SELECT v FROM Venda v JOIN FETCH v.pedidos WHERE v.codigo = :codigo");
		return buscarPor(Venda.class, codigo);
	}

	@Override
	public List<Venda> buscarTodos() {
		setJpql("SELECT DISTINCT v FROM Venda v JOIN FETCH v.pedidos");
		return buscarTodos(Venda.class);
	}

}
