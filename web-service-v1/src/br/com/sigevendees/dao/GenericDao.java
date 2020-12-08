package br.com.sigevendees.dao;

import java.util.List;
import org.hibernate.Session;
import br.com.sigevendees.connectionFactory.FactoryHibernate;

/* Esta classe representa as a��es em comum entre os DAO
 * E representa o tipo da Entity para persistir, atualizar e recuperar do BD.
 * I representa o tipo do Id utilizado para recuperar do BD.*/
public abstract class GenericDao<E, I> implements InterfaceDao<E, I>{

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
			System.out.println("ERRO! N�o foi possivel salvar \n" + "Motivo: ");
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
			System.out.println("ERRO! N�o foi possivel atualizar \n" + "Motivo: ");
			e.printStackTrace();
			FactoryHibernate.closeSessioFactory();
			this.resultado = false;
		} finally {
			this.session.close();
		}
		return resultado;
	}

	protected E buscarPor(Class<E> classe, I id) {
		E resultado = null;
		try {
			this.session = FactoryHibernate.getSessionFactory().openSession();
			this.session.beginTransaction();
			resultado = session.find(classe, id);
			this.session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("ERRO! N�o foi possivel realizar a busca \n" + "Motivo: ");
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
			System.out.println("ERRO! N�o foi possivel realizar a busca \n" + "Motivo: ");
			e.printStackTrace();
			FactoryHibernate.closeSessioFactory();
		} finally {
			this.session.close();
		}
		return lista;
	}
}
