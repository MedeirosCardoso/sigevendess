package br.com.sigevendees.repository;

import java.lang.reflect.Type;
import java.util.List;
import com.google.gson.reflect.TypeToken;
import br.com.sigevendees.dao.ClienteDao;
import br.com.sigevendees.entity.Cliente;
import br.com.sigevendees.entity.Telefone;

public class ClienteRepository extends GenericRepository<Cliente, Telefone> {

	public ClienteRepository() {
		this.dao = new ClienteDao();
		this.classe = Cliente.class;
	}

	@Override
	public List<Cliente> converterParaListaJava(String listaEmJson) {
		/*
		 * Gson n�o aceita objetos gen�rico, falha ao desserializar os values. Para
		 * resolver esse problema, � necessario especificar o tipo parametrizado correto
		 * para seu tipo gen�rico. Para isso � necessario o uso da classe TypeToken.
		 * FONTE: https://sites.google.com/site/gson/gson-user-guide#TOC-Array-Examples
		 */
		Type collectionType = new TypeToken<List<Cliente>>() {}.getType();
		try {
			List<Cliente> lista = this.gson.fromJson(listaEmJson, collectionType);
			return lista;
		} catch (Exception e) {
			System.out.println("ERRO! N�o foi possivel converter o Objeto informado \n" + "Motivo: ");
			e.printStackTrace();
			return null;
		}
	}

}
