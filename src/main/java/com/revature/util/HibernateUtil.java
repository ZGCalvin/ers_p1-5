package com.revature.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;

/**
 * This class creates the SesisonFactory using the hibernate.cfg.xml file
 */
public class HibernateUtil {


    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {

            // Create the SessionFactory from hibernate.cfg.xml
            //return new Configuration().configure(new File("src/main/java/resources/hibernate.cfg.xml")).buildSessionFactory();

            try {
                //if on the web
                return new Configuration().configure(new File("webapps/test/WEB-INF/hibernateConfig/hibernate.cfg.xml")).buildSessionFactory();
            } catch (Exception e) {
                return new Configuration().configure(new File("hibernate.cfg.xml")).buildSessionFactory();
            }
        }
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed" + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        // Close caches and connection pools
        getSessionFactory().close();
    }
}
