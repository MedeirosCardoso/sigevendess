package br.com.sigevendees.dao;

import java.util.List;
import br.com.sigevendees.entity.Aquisicao;

public class AquisicaoDao extends GenericDao<Aquisicao, Integer> {

	@Override
	public Aquisicao buscarPor(Integer codigo) {
		setJpql("SELECT a FROM Aquisicao a JOIN FETCH a.componentes WHERE a.codigo = :codigo");
		return buscarPor(Aquisicao.class, codigo);
	}

	@Override
	public List<Aquisicao> buscarTodos() {
		setJpql("SELECT DISTINCT a FROM Aquisicao a JOIN FETCH a.componentes");
		return buscarTodos(Aquisicao.class);
	}

}
