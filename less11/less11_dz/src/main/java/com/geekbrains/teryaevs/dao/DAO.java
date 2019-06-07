package com.geekbrains.teryaevs.dao;

import com.geekbrains.teryaevs.entities.Consumer;
import com.geekbrains.teryaevs.entities.Product;
import com.geekbrains.teryaevs.entities.Purchase;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import java.util.List;

public class DAO {
    private static SessionFactory factory;

    static {
        factory = HibernateUtil.getSessionFactory();
    }

    public void close() {
        if (factory != null) factory.close();
    }

    public <T> boolean insert(T object) {
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

    public Consumer getConsumerByName(String name) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();

            Consumer consumer = (Consumer) session.createQuery(
                    "FROM Consumer WHERE name = '" + name + "'").getSingleResult();
//            List<Purchase> list = consumer.getPurchases();

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
//
//    public <T> List<T> getAll(Class<T> tClass) {
//        List<T> list;
//        try {
//            Session session = factory.getCurrentSession();
//            session.beginTransaction();
//
//            list = session.createQuery("FROM " + tClass.getSimpleName()).list();
//
//            session.getTransaction().commit();
//            return list;
//        } catch (HibernateException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    public boolean updateStudentsCourse(String studentName, String courseTitle, int score) {
//        try (Session session = factory.getCurrentSession()) {
//            session.beginTransaction();
//
//            Student student = (Student) session.createQuery(
//                    "FROM Student WHERE name = '" + studentName + "'").getSingleResult();
//
//            for (StudentCourseInfo info : student.getCoursesInfo()) {
//                if (info.getCourse().getTitle().equals(courseTitle)) {
//                    if (info.getCourse().getDuration() <= info.getCompleted()) {
//                        System.out.println("Этот студент уже закончил этот курс");
//                        session.getTransaction().rollback();
//                        return false;
//                    }
//
//                    info.setScore(info.getScore() + score);
//                    info.setCompleted(info.getCompleted() + 1);
//
//                    session.getTransaction().commit();
//                    return true;
//                }
//            }
//
//            System.out.println("Этот студент не запмсан на этот курс");
//            return false;
//        } catch (HibernateException | NoResultException e) {
//            e.printStackTrace();
//            System.out.println("Нет такого студента");
//            return false;
//        }
//    }
}
