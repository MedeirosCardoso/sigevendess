package br.com.sigevendees.dao;

import java.util.List;
import javax.persistence.NoResultException;
import org.hibernate.jpa.QueryHints;
import br.com.sigevendees.connectionFactory.FactoryHibernate;
import br.com.sigevendees.entity.Produto;

public class ProdutoDao extends GenericDao<Produto> implements DaoInterface<Produto>{

	// Método retorna o Produto sem a sua Receita.
	public Produto buscarPorId(Integer codigo) {
		Produto resultado = null;
		try {
			session = FactoryHibernate.getSessionFactory().openSession();
			String jpql = "SELECT NEW br.com.sigevendees.entity.Produto(p.codigo, p.descricao, p.categoria, p.simbolo, p.preco) "
					+ "FROM Produto p WHERE p.codigo = :codigo";
			session.beginTransaction();
			resultado = session.createQuery(jpql, Produto.class).setParameter("codigo", codigo).getSingleResult();
			session.getTransaction().commit();
		} catch (NoResultException e) {
			// Não foi encotrado o Produto com o codigo informado.
			return null;
		} catch (Exception e) {
			System.out.println("ERRO! Não foi possivel realizar a busca \n" + "Motivo: ");
			e.printStackTrace();
			FactoryHibernate.closeSessioFactory();
		} finally {
			session.close();
		}
		return resultado;
	}

	// Método retorna o Produto e a sua Receita.
	public Produto buscarProduto_e_sua_receita(Integer codigo) {
		Produto resultado = null;
		try {
			session = FactoryHibernate.getSessionFactory().openSession();
			String jpql = "SELECT p FROM Produto p JOIN FETCH p.receita r JOIN FETCH r.componentes WHERE p.codigo = :codigo";
			session.beginTransaction();
			resultado = session.createQuery(jpql, Produto.class).setParameter("codigo", codigo).getSingleResult();
			session.getTransaction().commit();
		} catch (NoResultException e) {
			// Não foi encotrado o Produto com o codigo informado.
			return null;
		} catch (Exception e) {
			System.out.println("ERRO! Não foi possivel realizar a busca \n" + "Motivo: ");
			e.printStackTrace();
			FactoryHibernate.closeSessioFactory();
		} finally {
			session.close();
		}
		return resultado;
	}

	// Método retorna todos os produtos sem a Receita de cada Produto.
	public List<Produto> buscarTodos() {
		List<Produto> lista = null;
		try {
			session = FactoryHibernate.getSessionFactory().openSession();
			String jpql = "SELECT NEW br.com.sigevendees.entity.Produto(p.codigo, p.descricao, p.categoria, p.simbolo, p.preco) "
					+ "FROM Produto p WHERE p.categoria LIKE('%DOCE%') OR p.categoria LIKE('%SALGADO%')";
			session.beginTransaction();
			lista = session.createQuery(jpql, Produto.class).getResultList();
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("ERRO! Não foi possivel realizar a busca \n" + "Motivo: ");
			e.printStackTrace();
			FactoryHibernate.closeSessioFactory();
		} finally {
			session.close();
		}
		return lista;
	}

	// Método retorna todos os produtos e a Receita de cada Produto.
	public List<Produto> buscarTodos_e_suas_receitas() {
		List<Produto> lista = null;
		try {
			session = FactoryHibernate.getSessionFactory().openSession();
			String jpql = "SELECT DISTINCT p FROM Produto p JOIN FETCH p.receita r JOIN FETCH r.componentes";
			session.beginTransaction();
			/*
			 * setHint(QueryHints.HINT_PASS_DISTINCT_THROUGH, false) dica de consulta de
			 * entidade, usado para o Hibernate não pasar a palavra-chave DISTINCT para a
			 * consulta SQL, a DISTINCT não é necessario nesse caso, pois faz uma
			 * classificação redundante do conjunto de resultados. Para mais informações
			 * consultar a documentação na seção 15.14.
			 */
			lista = session.createQuery(jpql, Produto.class).setHint(QueryHints.HINT_PASS_DISTINCT_THROUGH, false)
					.getResultList();
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("ERRO! Não foi possivel realizar a busca \n" + "Motivo: ");
			e.printStackTrace();
			FactoryHibernate.closeSessioFactory();
		} finally {
			session.close();
		}
		return lista;
	}

	/*
	 * essa jpql faz um select na tabela produto e na tabela receita, mas da erro ao
	 * tentar trazer a receita.
	 * "SELECT p FROM Produto p WHERE p.categoria LIKE('%DOCE%') OR p.categoria LIKE('%SALGADO%')"
	 * ;
	 */
}
