package br.com.sigevendees.controller;

import javax.annotation.PostConstruct;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import br.com.sigevendees.repository.ComponenteRepository;

@Path("/service")
public class ComponenteController {
	private ComponenteRepository repositorio;

	@PostConstruct
	private void init() {
		this.repositorio = new ComponenteRepository();
	}

	@POST
	@Path("/componentes")
	@Consumes("application/json; charset=utf-8")
	@Produces("application/json; charset=utf-8")
	public Response incluir(String dadosComponente) {
		return repositorio.novo(dadosComponente);
	}

	@GET
	@Path("/componentes/{id}")
	@Produces("application/json; charset=utf-8")
	public Response recuperarComponentePeloId(@PathParam("id") Integer id) {
		return repositorio.getRecurso(id);
	}

	@GET
	@Path("/componentes")
	@Produces("application/json; charset=utf-8")
	public Response listarComponentes() {
		return repositorio.getRecursos();
	}
}
