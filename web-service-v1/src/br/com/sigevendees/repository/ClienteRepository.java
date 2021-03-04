package br.com.sigevendees.repository;

import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import br.com.sigevendees.dao.ClienteDao;
import br.com.sigevendees.entity.Cliente;
import br.com.sigevendees.entity.Telefone;

public class ClienteRepository extends GenericRepository<Cliente, Telefone> {

	public ClienteRepository() {
		setDao(new ClienteDao());
		setClasse(Cliente.class);
		setGson(new Gson());
		setCollectionType(new TypeToken<List<Cliente>>() {}.getType());
	}

}
