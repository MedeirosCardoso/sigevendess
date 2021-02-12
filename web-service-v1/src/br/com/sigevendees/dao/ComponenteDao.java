package br.com.sigevendees.dao;

import java.util.List;
import br.com.sigevendees.entity.Componente;

public class ComponenteDao extends GenericDao<Componente, Integer> {

	@Override
	public Componente buscarPor(Integer codigo) {
		setJpql(null);
		return buscarPor(Componente.class, codigo);
	}

	@Override
	public List<Componente> buscarTodos() {
		setJpql("FROM Componente");
		return buscarTodos(Componente.class);
	}

}
