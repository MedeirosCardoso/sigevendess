package br.com.sigevendees.dao;

import java.util.List;
import br.com.sigevendees.connectionFactory.FactoryHibernate;
import br.com.sigevendees.entity.Componente;

public class ComponenteDao extends GenericDao<Componente> implements DaoInterface<Componente> {

	public Componente buscarPorId(Integer codigo) {
		Componente resultado = null;
		try {
			session = FactoryHibernate.getSessionFactory().openSession();
			String jpql = "SELECT NEW br.com.sigevendees.entity.Componente(c.codigo, c.descricao, c.categoria, c.simbolo, c.preco, c.estoqueMin, c.estoqueAtual) FROM Componente c WHERE c.codigo = :codigo";
			session.beginTransaction();
			resultado = session.createQuery(jpql, Componente.class).setParameter("codigo", codigo).getSingleResult();
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("ERRO! Não foi possivel realizar a busca \n" + "Motivo: ");
			e.printStackTrace();
			FactoryHibernate.closeSessioFactory();
		} finally {
			session.close();
		}
		return resultado;
	}

	public List<Componente> buscarTodos() {
		List<Componente> lista = null;
		try {
			session = FactoryHibernate.getSessionFactory().openSession();
			String jpql = "SELECT NEW br.com.sigevendees.entity.Componente(c.codigo, c.descricao, c.categoria, c.simbolo, c.preco, c.estoqueMin, c.estoqueAtual) FROM Componente c";
			session.beginTransaction();
			lista = session.createQuery(jpql, Componente.class).getResultList();
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
	 * Esse método tambem faz um select na tabela Receita, pois o Produto possui
	 * Receita. public Componente buscarPorId(Integer codigo) { return
	 * super.buscarPorId(Componente.class, codigo); }
	 */
	/*
	 * Esse método tambem faz um select na tabela Receita, pois o Produto possui
	 * Receita. public List<Componente> buscarTodos() { return
	 * super.buscarTodos(Componente.class); }
	 */
}
