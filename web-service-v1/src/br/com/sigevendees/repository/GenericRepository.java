package br.com.sigevendees.repository;

import java.lang.reflect.Type;
import java.util.List;
import javax.ws.rs.core.Response;
import br.com.sigevendees.dao.InterfaceDao;
import com.google.gson.Gson;

/*Esta classe representa as ações em comum entre os repository das entity
 * R representa o tipo do Recurso a ser consumido ou produzido pelo webService.
 * D representa o tipo do DAO utilizado para persistir e recuperar do BD.
 * I representa o tipo do Id utilizado para recuperar do BD.*/
public abstract class GenericRepository<R, I> {

	private Gson gson;
	private Response resposta;
	private InterfaceDao<R, I> dao;
	// atributo utilizado na conversão de dados em Json para objeto java, o qual é
	// informado o tipo do objeto que o json vai representar.
	private Class<R> classe;
	private Type collectionType;

	public void setGson(Gson gson) {
		this.gson = gson;
	}

	public Response getResposta() {
		return resposta;
	}

	public void setResposta(Response resposta) {
		this.resposta = resposta;
	}

	public InterfaceDao<R, I> getDao() {
		return dao;
	}

	public void setDao(InterfaceDao<R, I> dao) {
		this.dao = dao;
	}

	public void setClasse(Class<R> classe) {
		this.classe = classe;
	}

	public void setCollectionType(Type collectionType) {
		this.collectionType = collectionType;
	}

	public Response novo(String dadosEmJson) {
		R dadosNovoRecurso = converterParaJava(dadosEmJson);
		if (dadosNovoRecurso != null) {
			if (dao.salvar(dadosNovoRecurso)) {
				// novo recurso foi criado e persistido no BD com sucesso.
				resposta = mensagemCriadoRecurso();
			} else {
				// os dados informado possui campos inválidos.
				resposta = mensagemDadosInvalidos();
			}
		} else {
			// aconteceu um erro com o servidor;
			resposta = mensagemErro();
		}
		return resposta;
	}

	public Response getRecurso(I id) {
		R resultado = dao.buscarPor(id);
		if (resultado != null) {
			// retornado o recurso solicitado.
			// converter o recurso retornado para JSON.
			String recursoEmJson = converterParaJSON(resultado);
			// retorna um response com o recurso em JSON.
			resposta = mensagemSucesso(recursoEmJson);
		} else {
			// retorna um response informando que o Id informado não possui cadastro.
			resposta = mensagemNaoEncontrado();
		}
		return resposta;
	}

	public Response getRecursos() {
		List<R> lista = dao.buscarTodos();
		if (lista != null) {
			// retornado uma lista com os recursos.
			// converter os recursos retornados para JSON.
			String listaEmJson = converterParaArrayJSON(lista);
			// retorna um response com a lista em JSON.
			resposta = mensagemSucesso(listaEmJson);
		} else {
			// retorna um response informando que não possui cadastro.
			resposta = mensagemNaoEncontrado();
		}
		return resposta;
	}

	public String converterParaJSON(R objetoEmjava) {
		try {
			String objEmJson = gson.toJson(objetoEmjava, classe);
			return objEmJson;
		} catch (Exception e) {
			System.out.println("ERRO! Não foi possivel converter o Objeto informado \n" + "Motivo: ");
			e.printStackTrace();
			return null;
		}
	}

	public R converterParaJava(String objetoEmJson) {
		try {
			R objEmJava = gson.fromJson(objetoEmJson, classe);
			return objEmJava;
		} catch (Exception e) {
			System.out.println("ERRO! Não foi possivel converter o Json informado \n" + "Motivo: ");
			e.printStackTrace();
			return null;
		}
	}

	public String converterParaArrayJSON(List<R> listaEmJava) {
		try {
			String listEmJson = gson.toJson(listaEmJava, Object.class);
			return listEmJson;
		} catch (Exception e) {
			System.out.println("ERRO! Não foi possivel converter o Objeto informado \n" + "Motivo: ");
			e.printStackTrace();
			return null;
		}
	}

	public List<R> converterParaListaJava(String listaEmJson) {
		/*
		 * Gson não aceita objetos genérico, falha ao desserializar os values. Para
		 * resolver esse problema, é necessario especificar o tipo parametrizado correto
		 * para seu tipo genérico. Para isso é necessario o uso da classe TypeToken.
		 * FONTE: https://sites.google.com/site/gson/gson-user-guide#TOC-Array-Examples
		 */
		try {
			List<R> lista = this.gson.fromJson(listaEmJson, collectionType);
			return lista;
		} catch (Exception e) {
			System.out.println("ERRO! Não foi possivel converter o Objeto informado \n" + "Motivo: ");
			e.printStackTrace();
			return null;
		}
	}

	protected Response mensagemSucesso(String resultado) {
		return Response.ok(resultado, "application/json").build();
	}

	protected Response mensagemCriadoRecurso() {
		return Response.created(null).entity("Operação realizada com sucesso!").type("application/json").build();
	}

	protected Response mensagemDadosInvalidos() {
		return Response.status(Response.Status.BAD_REQUEST).entity("ERRO! dados invalidos.").type("application/json")
				.build();
	}

	protected Response mensagemNaoEncontrado() {
		return Response.noContent().entity("Não possui cadastro.").type("application/json").build();
	}

	protected Response mensagemErro() {
		return Response.serverError().entity("ERRO! Operação não realizada.").type("application/json").build();
	}

}
