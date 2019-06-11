package com.geekbrains.hibernate.advanced;

import com.geekbrains.hibernate.basic.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import javax.persistence.LockModeType;
import javax.persistence.OptimisticLockException;
import javax.persistence.Persistence;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;

public class MainAppMy {
    // Повторяющиеся покупки в 4 *
    // hibernate.hbm2ddl.auto none
    // get - caching?

    public static void main(String[] args) throws IOException {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
        prepareData(factory);
//        work(factory);
        optimisticVersioningTest(factory);
//        optimisticVersioningThreadingTest(factory);
    }

    public static void work(SessionFactory factory) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class, 1L);
            Product product2 = session.load(Product.class, 1L);
            Product product3 = session.find(Product.class, 1L);
            System.out.println(product);
            Customer customer = session.get(Customer.class, 1L);
            System.out.println(customer);
            Manufacturer manufacturer = session.load(Manufacturer.class, 1L);
            System.out.println(manufacturer);
            // System.out.println(manufacturer.getProducts());
            System.out.println("PRICE: " + manufacturer.avgProductsPrice);
            manufacturer.getProducts().size();

            session.getTransaction().commit();
            System.out.println(manufacturer.getProducts());
        }
    }

    public static void optimisticVersioningTest(SessionFactory factory) {
        Session session = null;
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            BigItem bigItem = new BigItem(20);
            session.save(bigItem);
            System.out.println(bigItem);
            bigItem.setVal(25);
            System.out.println(bigItem);
            session.save(bigItem);
            System.out.println(bigItem);
            session.getTransaction().commit();

            session = factory.getCurrentSession();
            session.beginTransaction();
            bigItem = session.get(BigItem.class, 1L);
            System.out.println(bigItem);
            session.getTransaction().commit();
        } finally {
            factory.close();
            if (session != null) {
                session.close();
            }
        }
    }

    public static void optimisticVersioningThreadingTest(SessionFactory factory) {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        try (Session session = factory.getCurrentSession()) {
            new Thread(() -> {
                System.out.println("Thread #1 started");
                session.beginTransaction();

                BigItem bigItem = session.get(BigItem.class, 1L);
                bigItem.setVal(100);
                uncheckableSleep(1000);
                session.save(bigItem);

                session.getTransaction().commit();
                System.out.println("Thread #1 committed");
                if (session != null) {
                    session.close();
                }
                countDownLatch.countDown();
            }).start();

            new Thread(() -> {
                System.out.println("Thread #2 started");
                session.beginTransaction();

                BigItem bigItem = session.get(BigItem.class, 1L);
                bigItem.setVal(200);
                uncheckableSleep(3000);
                try {
                    session.save(bigItem);

                    session.getTransaction().commit();
                    System.out.println("Thread #2 committed");
                } catch (OptimisticLockException e) {
                    session.getTransaction().rollback();
                    System.out.println("Thread #2 rollback");
                    e.printStackTrace();
                }
                if (session != null) {
                    session.close();
                }
                countDownLatch.countDown();
            }).start();
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("END");
        }
    }

    public static void queryOptimisticLockHandle(SessionFactory factory) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            List<Product> products = session.createQuery("SELECT p FROM Product p WHERE p.title = :title", Product.class)
                    .setLockMode(LockModeType.OPTIMISTIC)
                    .setParameter("title", "Sprite")
                    .getResultList();

            session.getTransaction().commit();
        }
    }

    public static void queryPessimisticLockHandle(SessionFactory factory) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            BigDecimal totalCost = new BigDecimal(0);
            List<Product> products = session.createQuery("SELECT p FROM Product p WHERE p.title = :title", Product.class) // тут можем идти допустим по категориям
                    .setLockMode(LockModeType.PESSIMISTIC_READ) // FOR UPDATE
                    .setHint("javax.persistence.lock.timeout", 5000)
                    .setParameter("title", "Sprite")
                    .getResultList();
            for (Product p : products) {
                totalCost.add(p.price);
            }
            session.getTransaction().commit();
        }
    }

    public static void rollbackEx(SessionFactory factory) {
        Session session = null;
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            // Тут что-то делаем
            session.getTransaction().commit();
            // System.out.println(manufacturer.getProducts());
        } catch (Exception e) {
            try {
                if (session.getTransaction().getStatus() == TransactionStatus.ACTIVE || session.getTransaction().getStatus() == TransactionStatus.MARKED_ROLLBACK) {
                    session.getTransaction().rollback();
                }
            } catch (Exception rollbackException) {
                System.err.println("Rollback failed");
                rollbackException.printStackTrace();
            }
            throw new RuntimeException(e);
        } finally {
            factory.close();
            if (session != null) {
                session.close();
            }
        }
    }

    public static void prepareData(SessionFactory factory) throws IOException {
        try (Session session = factory.getCurrentSession()) {
            String sql = Files.lines(Paths.get("drop-and-create.sql")).collect(Collectors.joining(" "));
            session.beginTransaction();
            session.createNativeQuery(sql).executeUpdate();
            session.getTransaction().commit();
        }
    }

    public static void uncheckableSleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
