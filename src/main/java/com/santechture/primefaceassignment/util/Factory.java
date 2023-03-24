/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.santechture.primefaceassignment.util;

import java.io.File;
import org.hibernate.Interceptor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author M_Aljazwiee
 */
public class Factory {

    public static SessionFactory sessionFactory;
	private static final String xmlPath = "com/santechture/primefaceassignment/xml/";
    public static String XMLFILE;

    private static SessionFactory buildSessionFactory(Interceptor interceptor, String XMLFILE) {
        try {

            Configuration configuration = new Configuration().configure();
            StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();
            serviceRegistryBuilder.applySettings(configuration.getProperties());
            ServiceRegistry serviceRegistry = serviceRegistryBuilder.build();
            if (interceptor != null) {
                configuration.setInterceptor(interceptor);
            }

            sessionFactory = configuration.buildSessionFactory();

            return sessionFactory;
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {

        if (XMLFILE != null && !XMLFILE.isEmpty()) {
            if (sessionFactory != null) {
                return sessionFactory;
            }
        }

        if (XMLFILE == null || XMLFILE.isEmpty()) {
            XMLFILE = "hibernate.cfg.xml";
        }
        System.out.println("Absolute Path  "+new File(xmlPath + XMLFILE).getAbsolutePath());
        return buildSessionFactory(null, xmlPath + XMLFILE);
    }

    public static SessionFactory getSessionFactory(String XMLFILE) {
        System.out.println(new File(xmlPath + XMLFILE).getAbsoluteFile());
        if (Factory.XMLFILE == XMLFILE) {
            if (sessionFactory != null) {
                return sessionFactory;
            }
        }

        Factory.XMLFILE = XMLFILE;
        return buildSessionFactory(null, xmlPath + XMLFILE);

    }

    public static SessionFactory getSessionFactory(String xmlPath, String XMLFILE) {
        System.out.println(new File(xmlPath + XMLFILE).getAbsoluteFile());
        if (Factory.XMLFILE == XMLFILE) {
            if (sessionFactory != null) {
                return sessionFactory;
            }
        }

        Factory.XMLFILE = XMLFILE;
        return buildSessionFactory(null, xmlPath + XMLFILE);

    }

    public static SessionFactory getSessionFactory(String XMLFILE, Interceptor interceptor) {
        System.out.println(new File(xmlPath + XMLFILE).getAbsoluteFile());
        if (Factory.XMLFILE == XMLFILE) {
            if (sessionFactory != null) {
                return sessionFactory;
            }
        }

        Factory.XMLFILE = XMLFILE;
        return buildSessionFactory(interceptor, xmlPath + XMLFILE);

    }

    public static Session getCurrentSession(String XMLFILE) {
        if (sessionFactory != null) {
            return sessionFactory.getCurrentSession();
        }
        return getSessionFactory(XMLFILE).getCurrentSession();
    }

    public static Session getCurrentSession() {
        if (XMLFILE == null || XMLFILE.isEmpty()) {
            XMLFILE = "hibernate.cfg.xml";
        }
        return getCurrentSession(XMLFILE);
    }

    public static SessionFactory getSessionFactory(Interceptor interceptor) {
        return buildSessionFactory(interceptor, xmlPath + "hibernate.cfg.xml");
    }
}
