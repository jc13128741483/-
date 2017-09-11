package com.hello.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static SessionFactory sf;
	
	static{
		Configuration cfg=new Configuration().configure();
		sf=cfg.buildSessionFactory();
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			public void run() {
				sf.close();
			}
		}));
	}
	
	public static Session openSession(){
		return sf.openSession();
	}
	
	public static Session getCurrentSession(){
		return sf.getCurrentSession();
	}
	
}
