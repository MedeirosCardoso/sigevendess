package br.com.sigevendees.repository;

import java.util.List;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;
import br.com.sigevendees.dao.ComponenteDao;
import br.com.sigevendees.entity.Componente;

public class ComponenteRepository extends GenericRepository<Componente, Integer> {

	public ComponenteRepository() {
		this.dao = new ComponenteDao();
		this.classe = Componente.class;
	}

	@Override
	public List<Componente> converterParaListaJava(String listaEmJson) {
		/*
		 * Gson n�o aceita objetos gen�rico, falha ao desserializar os values.
		 * Para resolver esse problema, � necessario especificar o tipo parametrizado correto para seu tipo gen�rico.
		 * Para isso � necessario o uso da classe TypeToken.
		 * FONTE: https://sites.google.com/site/gson/gson-user-guide#TOC-Array-Examples
		 */
		Type collectionType = new TypeToken<List<Componente>>() {}.getType();
		try {
			List<Componente> lista = this.gson.fromJson(listaEmJson, collectionType);
			return lista;
		} catch (Exception e) {
			System.out.println("ERRO! N�o foi possivel converter o Objeto informado \n" + "Motivo: ");
			e.printStackTrace();
			return null;
		}
	}
}
