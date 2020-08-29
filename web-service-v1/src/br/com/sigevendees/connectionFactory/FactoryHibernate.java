package br.com.sigevendees.connectionFactory;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class FactoryHibernate {
	private static SessionFactory sessionFactory;
	private static StandardServiceRegistry registry;

	public static SessionFactory getSessionFactory() {
		registry = new StandardServiceRegistryBuilder().configure().build();
		if (sessionFactory == null || !sessionFactory.isOpen()) {
			sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		}
		return sessionFactory;
	}

	public static void closeSessioFactory() {
		if (sessionFactory != null && sessionFactory.isOpen()) {
			StandardServiceRegistryBuilder.destroy(registry);
		}
	}
}
