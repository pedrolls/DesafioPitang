package jpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * 
 * @author pedro
 *
 */
public class JpaUtil {
	

	private static EntityManagerFactory emf;

	public static void createEntityManagerFactory() {
		if (emf == null)
			emf = Persistence.createEntityManagerFactory("pitang");
	}

	public static EntityManager getEntityManager() {
		if (emf == null)
			createEntityManagerFactory();
		return emf.createEntityManager();
	}

	public static void closeEntityManager() {
		if (emf != null)
			emf.close();
	}

}
