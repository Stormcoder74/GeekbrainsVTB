package com.geekbrains.teryaevs;

import com.geekbrains.teryaevs.entities.Item;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import javax.persistence.NoResultException;
import javax.persistence.OptimisticLockException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class MainApp {
    private static final int ITERATIONS = 20_000;
    private static final int ROWS = 40;
    private static final int THREADS = 8;

    private static void prepareData(SessionFactory factory) throws IOException {
        try (Session session = factory.getCurrentSession()) {
            String sql = Files
                    .lines(Paths.get("Script-dz-12.sql"))
                    .collect(Collectors.joining(" "));
            session.beginTransaction();
            session.createNativeQuery(sql).executeUpdate();
            session.getTransaction().commit();
        }
    }

    private static void checkSum(SessionFactory factory) {
        int checkSum = 0;

        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();

            Item item;
            for (int i = 1; i <= ROWS; i++) {
                item = (Item) session.createQuery(
                        "FROM Item WHERE id = " + i).getSingleResult();
                checkSum += item.getValue();
            }

            session.getTransaction().commit();
        } catch (HibernateException | NoResultException e) {
            e.printStackTrace();
        }

        System.out.println("Check sum is: " + checkSum);
    }

    public static void main(String[] args) throws IOException {
        long time = System.currentTimeMillis();
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        prepareData(factory);

        ExecutorService executorService = Executors.newFixedThreadPool(THREADS);
        for (int j = 0; j < THREADS; j++) {
            executorService.execute(() -> {
                for (int i = 0; i < ITERATIONS; i++) {
                    Session session = factory.getCurrentSession();
                    try {
                        long rowId = (long) (Math.random() * ROWS) + 1;
                        while (session.getTransaction().getStatus() != TransactionStatus.COMMITTED) {
                            session.beginTransaction();

                            Item item = (Item) session.createQuery(
                                    "FROM Item WHERE id = " + rowId).getSingleResult();
                            item.setValue(item.getValue() + 1);
                            Thread.sleep(5);

                            try {
                                session.getTransaction().commit();
                            } catch (OptimisticLockException e) {
                                session.getTransaction().rollback();
                                session = factory.getCurrentSession();
                            }
                        }
                    } catch (HibernateException | NoResultException | InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        session.close();
                    }
                }
            });
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(180_000, TimeUnit.MILLISECONDS);
            checkSum(factory);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(System.currentTimeMillis() - time);
        factory.close();
    }
}

//    Check sum is: 160000
//    147507