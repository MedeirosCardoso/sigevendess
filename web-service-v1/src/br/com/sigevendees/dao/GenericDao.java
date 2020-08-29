package br.com.sigevendees.dao;

import java.util.List;
import org.hibernate.Session;
import br.com.sigevendees.connectionFactory.FactoryHibernate;

public abstract class GenericDao<E> {

	protected Session session;
	private boolean resultado;

	public boolean salvar(E entity) {
		try {
			this.session = FactoryHibernate.getSessionFactory().openSession();
			this.session.beginTransaction();
			this.session.save(entity);
			this.session.getTransaction().commit();
			this.resultado = true;
		} catch (Exception e) {
			System.out.println("ERRO! Não foi possivel salvar \n" + "Motivo: ");
			e.printStackTrace();
			FactoryHibernate.closeSessioFactory();
			this.resultado = false;
		} finally {
			this.session.close();
		}
		return resultado;
	}

	public boolean atualizar(E entity) {
		try {
			this.session = FactoryHibernate.getSessionFactory().openSession();
			this.session.beginTransaction();
			this.session.update(entity);
			this.session.getTransaction().commit();
			this.resultado = true;
		} catch (Exception e) {
			System.out.println("ERRO! Não foi possivel atualizar \n" + "Motivo: ");
			e.printStackTrace();
			FactoryHibernate.closeSessioFactory();
			this.resultado = false;
		} finally {
			this.session.close();
		}
		return resultado;
	}

	protected E buscarPorId(Class<E> classe, Integer codigo) {
		E resultado = null;
		try {
			this.session = FactoryHibernate.getSessionFactory().openSession();
			this.session.beginTransaction();
			resultado = session.find(classe, codigo);
			this.session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("ERRO! Não foi possivel realizar a busca \n" + "Motivo: ");
			e.printStackTrace();
			FactoryHibernate.closeSessioFactory();
		} finally {
			this.session.close();
		}
		return resultado;
	}

	protected List<E> buscarTodos(Class<E> classe) {
		List<E> lista = null;
		try {
			this.session = FactoryHibernate.getSessionFactory().openSession();
			String jpql = "FROM " + classe.getName();
			this.session.beginTransaction();
			lista = this.session.createQuery(jpql, classe).getResultList();
			this.session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("ERRO! Não foi possivel realizar a busca \n" + "Motivo: ");
			e.printStackTrace();
			FactoryHibernate.closeSessioFactory();
		} finally {
			this.session.close();
		}
		return lista;
	}
}
