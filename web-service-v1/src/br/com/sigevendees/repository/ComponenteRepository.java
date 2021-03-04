package br.com.sigevendees.repository;

import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import br.com.sigevendees.dao.ComponenteDao;
import br.com.sigevendees.entity.Componente;

public class ComponenteRepository extends GenericRepository<Componente, Integer> {

	public ComponenteRepository() {
		setDao(new ComponenteDao());
		setClasse(Componente.class);
		setGson(new Gson());
		setCollectionType(new TypeToken<List<Componente>>() {}.getType());
	}

}
