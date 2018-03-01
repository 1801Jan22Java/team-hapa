package com.revature.util;

<<<<<<< HEAD
import org.hibernate.Session;
import org.hibernate.SessionFactory;
=======
import org.hibernate.*;
>>>>>>> 985d324dec09397d93546b147d2511f320e47857
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

<<<<<<< HEAD
	private static SessionFactory mySessionFactory;
	
	private static SessionFactory getSessionFactory(String filename) {
		// If no session exists make new one before returning it
		// org.hibernate loads jdbc into memory using reflection and maven's mapped dependency
		if (HibernateUtil.mySessionFactory == null) {
			Configuration c = new Configuration().configure(filename);
			ServiceRegistry sr = new StandardServiceRegistryBuilder().applySettings(c.getProperties()).build();
			HibernateUtil.mySessionFactory = c.buildSessionFactory(sr);
		}
		return HibernateUtil.mySessionFactory;
	}
	
	public static Session getSession() {
		return getSessionFactory("hibernate.cfg.xml").openSession();
	}
=======
	private static SessionFactory sessionFactory;
	
	private static SessionFactory getSessionFactory(String filename) {
		
		if(HibernateUtil.sessionFactory == null) {
			Configuration c = new Configuration().configure(filename);
			ServiceRegistry sr = new StandardServiceRegistryBuilder().applySettings(c.getProperties()).build();
			HibernateUtil.sessionFactory = c.buildSessionFactory(sr);
		}
		
		return HibernateUtil.sessionFactory;
	}
	
	public static Session getSession() {
		
		return HibernateUtil.getSessionFactory("hibernate.cfg.xml").openSession();
	}
	
>>>>>>> 985d324dec09397d93546b147d2511f320e47857
}
