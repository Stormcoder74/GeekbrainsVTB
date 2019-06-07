package com.geekbrains.teryaevs.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

class HibernateUtil {
    private static SessionFactory factory;

    static SessionFactory getSessionFactory() {
        if (factory == null) {
            factory = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .buildSessionFactory();
        }
        return factory;
    }

    static void close() {
        if (factory != null)
            factory.close();
    }
}
