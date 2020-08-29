package br.com.sigevendees.dao;

import java.util.List;

public interface DaoInterface<E> {

	public boolean salvar(E entity);

	public boolean atualizar(E entity);

	public E buscarPorId(Integer codigo);

	public List<E> buscarTodos();

}
