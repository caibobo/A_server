package com.util;

import java.io.IOException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {
	private static SessionFactory sessionFactory;
	private static Session session;
	
	static{
		Configuration config=new Configuration().configure();
		StandardServiceRegistryBuilder ssrb=new StandardServiceRegistryBuilder()
				.applySettings(config.getProperties());
		StandardServiceRegistry ssr=ssrb.build();
		sessionFactory=config.buildSessionFactory(ssr);
	}
	
	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}
	
	public static Session getSession(){
		session=sessionFactory.openSession();
		return session;
	}
	
	public static void closeSession(Session session) throws IOException{
		if(session!=null)session.close();
	}
}
