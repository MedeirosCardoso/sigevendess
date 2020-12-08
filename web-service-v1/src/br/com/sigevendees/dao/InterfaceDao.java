package br.com.sigevendees.dao;

import java.util.List;

/* E representa o tipo da Entity para persistir, atualizar e recuperar do BD.
 * I representa o tipo do Id utilizado para recuperar do BD.*/
public interface InterfaceDao<E, I> {

	public boolean salvar(E entity);

	public boolean atualizar(E entity);

	public E buscarPor(I id);

	public List<E> buscarTodos();

}
