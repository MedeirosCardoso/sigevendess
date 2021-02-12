package br.com.sigevendees.dao;

import java.util.List;
import br.com.sigevendees.entity.Produto;

public class ProdutoDao extends GenericDao<Produto, Integer> {

	// Método retorna o Produto sem a sua Receita.
	@Override
	public Produto buscarPor(Integer codigo) {
		setJpql("SELECT NEW br.com.sigevendees.entity.Produto(p.codigo, p.descricao, p.categoria, p.simbolo, p.preco) "
				+ "FROM Produto p WHERE p.codigo = :codigo");
		return buscarPor(Produto.class, codigo);
	}

	// Método retorna todos os produtos sem a Receita de cada.
	@Override
	public List<Produto> buscarTodos() {
		setJpql("SELECT NEW br.com.sigevendees.entity.Produto(p.codigo, p.descricao, p.categoria, p.simbolo, p.preco) "
				+ "FROM Produto p WHERE p.categoria LIKE('%DOCE%') OR p.categoria LIKE('%SALGADO%')");
		return buscarTodos(Produto.class);
	}

	// Método retorna o Produto e a sua Receita.
	public Produto buscarProduto_e_sua_receita(Integer codigo) {
		setJpql("SELECT p FROM Produto p JOIN FETCH p.receita r JOIN FETCH r.componentes WHERE p.codigo = :codigo");
		return buscarPor(Produto.class, codigo);
	}

	// Método retorna todos os produtos com a Receita de cada.
	public List<Produto> buscarProdutos_e_suas_receitas() {
		setJpql("SELECT DISTINCT p FROM Produto p JOIN FETCH p.receita r JOIN FETCH r.componentes");
		return buscarTodos(Produto.class);
	}

}
