package br.com.sigevendees.dao;

import java.util.List;
import javax.persistence.NoResultException;
import org.hibernate.Session;
import br.com.sigevendees.connectionFactory.FactoryHibernate;

/* Esta classe representa as ações em comum entre os DAO
 * E representa o tipo da Entity para persistir, atualizar e recuperar do BD.
 * I representa o tipo do Id utilizado para recuperar do BD.*/
public abstract class GenericDao<E, I> implements InterfaceDao<E, I> {

	private boolean ok;
	private E resultado;
	private List<E> lista;
	private Session session;
	private String jpql;
	
	
	public void setJpql(String jpql) {
		this.jpql = jpql;
	}

	public boolean salvar(E entity) {
		try {
			this.session = FactoryHibernate.getSessionFactory().openSession();
			this.session.beginTransaction();
			this.session.save(entity);
			this.session.getTransaction().commit();
			this.ok = true;
		} catch (Exception e) {
			System.out.println("ERRO! Não foi possivel salvar \n" + "Motivo: ");
			e.printStackTrace();
			FactoryHibernate.closeSessioFactory();
			this.ok = false;
		} finally {
			this.session.close();
		}
		return this.ok;
	}

	public boolean atualizar(E entity) {
		try {
			this.session = FactoryHibernate.getSessionFactory().openSession();
			this.session.beginTransaction();
			this.session.update(entity);
			this.session.getTransaction().commit();
			this.ok = true;
		} catch (Exception e) {
			System.out.println("ERRO! Não foi possivel atualizar \n" + "Motivo: ");
			e.printStackTrace();
			FactoryHibernate.closeSessioFactory();
			this.ok = false;
		} finally {
			this.session.close();
		}
		return this.ok;
	}

	public E buscarPor(Class<E> classe, I id) {
		this.resultado = null;
		try {
			this.session = FactoryHibernate.getSessionFactory().openSession();
			this.session.beginTransaction();
			if(this.jpql == null) {
				this.resultado = this.session.find(classe, id);
			}else {
				// Utilizado para consultas do tipo FetchType.LAZY.
				this.resultado = this.session.createQuery(this.jpql, classe).setParameter("codigo", id).getSingleResult();
			}
			this.session.getTransaction().commit();
		} catch (NoResultException e) {
			// Não foi encotrado a Entity com o id informado.
			return null;
		} catch (Exception e) {
			System.out.println("ERRO! Não foi possivel realizar a busca \n" + "Motivo: ");
			e.printStackTrace();
			FactoryHibernate.closeSessioFactory();
		} finally {
			this.session.close();
		}
		return this.resultado;
	}

	public List<E> buscarTodos(Class<E> classe) {
		this.lista = null;
		try {
			this.session = FactoryHibernate.getSessionFactory().openSession();
			this.session.beginTransaction();
			this.lista = this.session.createQuery(this.jpql, classe).getResultList();
			this.session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("ERRO! Não foi possivel realizar a busca \n" + "Motivo: ");
			e.printStackTrace();
			FactoryHibernate.closeSessioFactory();
		} finally {
			this.session.close();
		}
		return this.lista;
	}
}
