package com.geekbrains.teryaevs.dao;

import com.geekbrains.teryaevs.entities.Consumer;
import com.geekbrains.teryaevs.entities.Product;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

public class DAO {
    private SessionFactory factory;

    public DAO() {
        factory = HibernateUtil.getSessionFactory();
    }

    public void close() {
        HibernateUtil.close();
    }

    public boolean insert(Object object) {
        try {
            Session session = factory.getCurrentSession();
            session.beginTransaction();
            session.save(object);
            session.getTransaction().commit();
            return true;
        } catch (PersistenceException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean remove(Object object) {
        try {
            Session session = factory.getCurrentSession();
            session.beginTransaction();
            session.remove(object);
            session.getTransaction().commit();
            return true;
        } catch (PersistenceException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Consumer getConsumerByName(String name) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();

            Consumer consumer = (Consumer) session.createQuery(
                    "FROM Consumer WHERE name = '" + name + "'").getSingleResult();

            session.getTransaction().commit();
            return consumer;
        } catch (HibernateException | NoResultException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Product getProductByName(String name) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();

            Product product = (Product) session.createQuery(
                    "FROM Product WHERE name = '" + name + "'").getSingleResult();

            session.getTransaction().commit();
            return product;
        } catch (HibernateException | NoResultException e) {
            e.printStackTrace();
            return null;
        }
    }
}
