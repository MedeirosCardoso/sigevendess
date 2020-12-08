package br.com.sigevendees.controller;

import javax.annotation.PostConstruct;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import br.com.sigevendees.entity.Telefone;
import br.com.sigevendees.repository.ClienteRepository;

@Path("/service")
public class ClienteController {
	private ClienteRepository repositorio;

	@PostConstruct
	private void init() {
		this.repositorio = new ClienteRepository();
	}

	@POST
	@Path("/clientes")
	@Consumes("application/json; charset=utf-8")
	@Produces("application/json; charset=utf-8")
	public Response incluir(String dadosCliente) {
		return repositorio.novo(dadosCliente);
	}

	@GET
	@Path("/clientes/{ddd}/{digito}")
	@Produces("application/json; charset=utf-8")
	public Response recuperarClientePeloTelefone(@PathParam("ddd") byte ddd, @PathParam("digito") int digito) {
		return repositorio.getRecurso(new Telefone(ddd, digito));
	}

	@GET
	@Path("/clientes")
	@Produces("application/json; charset=utf-8")
	public Response listarClientes() {
		return repositorio.getRecursos();
	}
}
