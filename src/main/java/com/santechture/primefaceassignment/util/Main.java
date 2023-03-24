package com.santechture.primefaceassignment.util;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManagerFactory;

import org.hibernate.Query;
import org.hibernate.Session;


public class Main {
	private static final Logger LOGGER = Logger.getLogger("JPA");

	public static void main(String[] args) {
		Main main = new Main();
		main.run();
                System.exit(0);
	}

	public void run() {
		EntityManagerFactory factory = null;
		//EntityManager entityManager = null;
                Session entityManager = Factory.getSessionFactory().openSession();
                Query query = entityManager.getNamedQuery("Employees.findAll");
                query.setParameter(0, "ACTIVE");
                query.list();
		try {
			//factory = Persistence.createEntityManagerFactory("PersistenceUnit");
			//entityManager = factory.createEntityManager();
         
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			e.printStackTrace();
		} finally {
			if (entityManager != null) {
				entityManager.close();
			}
			if (factory != null) {
				factory.close();
			}
		}
	}

}
