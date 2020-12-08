package br.com.sigevendees.controller;

import javax.annotation.PostConstruct;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import br.com.sigevendees.repository.ProdutoRepository;

@Path("/service")
public class ProdutoController {
	private ProdutoRepository repositorio;

	@PostConstruct
	private void init() {
		this.repositorio = new ProdutoRepository();
	}

	@POST
	@Path("/produtos")
	@Consumes("application/json; charset=utf-8")
	@Produces("application/json; charset=utf-8")
	public Response incluir(String dados) {
		return repositorio.novo(dados);
	}

	@GET
	@Path("/produtos/{id}")
	@Produces("application/json; charset=utf-8")
	public Response recuperarProdutoPeloId(@PathParam("id") Integer id) {
		return repositorio.getRecurso(id);
	}

	@GET
	@Path("/produtos")
	@Produces("application/json; charset=utf-8")
	public Response listarProdutos() {
		return repositorio.getRecursos();
	}

	@GET
	@Path("/produtos/{id}/receitas")
	@Produces("application/json; charset=utf-8")
	public Response recuperarProdutoPeloId_e_sua_receita(@PathParam("id") Integer id) {
		return repositorio.getProduto_e_sua_receita(id);
	}

	@GET
	@Path("/produtos/receitas")
	@Produces("application/json; charset=utf-8")
	public Response listarProdutos_e_suas_receitas() {
		return repositorio.getProdutos_e_suas_receitas();
	}
}
