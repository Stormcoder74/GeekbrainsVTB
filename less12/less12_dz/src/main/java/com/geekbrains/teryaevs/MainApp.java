package com.geekbrains.teryaevs;

import com.geekbrains.teryaevs.entities.Item;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.NoResultException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class MainApp {
    private static final int ITERATIONS = 20_0;
    private static final int ROWS = 10;
    private static final int THREADS = 3;

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

    public static void main(String[] args) throws IOException {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        prepareData(factory);

        ExecutorService executorService = Executors.newFixedThreadPool(THREADS);
        for (int j = 0; j < THREADS; j++) {
            executorService.execute(() -> {
                for (int i = 0; i < ITERATIONS; i++) {
                    try (Session session = factory.getCurrentSession()) {
                        long rowId = (long) (Math.random() * ROWS) + 1;
                        session.beginTransaction();

                        Item item = (Item) session.createQuery(
                                "FROM Item WHERE id = " + rowId).getSingleResult();
                        item.setValue(item.getValue() + 1);
                        Thread.sleep(5);

                        session.getTransaction().commit();
                    } catch (HibernateException | NoResultException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(180_000, TimeUnit.MILLISECONDS);
            int checkSum = 0;
            for (int i = 1; i <= ROWS; i++) {
                try (Session session = factory.getCurrentSession()) {
                    session.beginTransaction();

                    Item item = (Item) session.createQuery(
                            "FROM Item WHERE id = " + i).getSingleResult();
                    checkSum += item.getValue();

                    session.getTransaction().commit();
                } catch (HibernateException | NoResultException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Check sum is: " + checkSum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
