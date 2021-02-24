package com.revature.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;

public class HibernateUtil {



    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
<<<<<<< HEAD
            // Create the SessionFactory from hibernate.cfg.xml
            return new Configuration().configure(new File("/hibernate.cfg.xml")).buildSessionFactory();
=======
            // Create the SessionFactory from hibernate.cfg.xml ----
            return new Configuration().configure(new File("/src/resources/hibernate.cgf.xml")).buildSessionFactory();
>>>>>>> c56ae9a2368876a3007bae10e9e6304bbf02676f

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
