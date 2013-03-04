package br.com.moleka.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("moleka"); 
	
	public static EntityManager getEntityManager(){	
		return entityManagerFactory.createEntityManager();
	}
	
	public void close(EntityManager em){
		em.close();
	}
}
