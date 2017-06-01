package br.com.horus.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
	private static SessionFactory sessionFactory = createSessionFactory();

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	private static SessionFactory createSessionFactory() {
		try {
			Configuration config = new Configuration().configure();

			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties())
					.build();

			SessionFactory sessionFa = config.buildSessionFactory(serviceRegistry);
			return sessionFa;

		} catch (Throwable ex) {
			System.err.println("Erro na implementação Hibernate." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
}
