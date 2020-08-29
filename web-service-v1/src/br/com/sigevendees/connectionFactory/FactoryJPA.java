package br.com.sigevendees.connectionFactory;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class FactoryJPA {

	private static final String PERSISTENCE_UNIT_NAME = "sigevendeesBack";
	private static EntityManagerFactory sessionFactory;

	public static EntityManagerFactory getEntityManagerFactory() {
		if (sessionFactory == null || !sessionFactory.isOpen()) {
			sessionFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		}
		return sessionFactory;
	}

	public static void closeSessionFactory() {
		if (sessionFactory != null && sessionFactory.isOpen()) {
			sessionFactory.close();
		}
	}

}
