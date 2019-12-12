package br.com.mecanica.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	private static EntityManagerFactory emf =
			Persistence.createEntityManagerFactory("mecanica");
	
	public static EntityManager getEntityManeger() {
		return emf.createEntityManager();
	}
}
