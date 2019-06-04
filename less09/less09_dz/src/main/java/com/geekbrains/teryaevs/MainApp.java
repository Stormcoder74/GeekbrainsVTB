package com.geekbrains.teryaevs;

import com.geekbrains.teryaevs.handlers.Repository;
import com.geekbrains.teryaevs.handlers.TableMaker;
import com.geekbrains.teryaevs.tables.Course;
import com.geekbrains.teryaevs.tables.Student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainApp {
    private static final boolean AUTO_MAKE_TABLES = true;
    private static final String PAKKAGE
            = "com.geekbrains.teryaevs.tables.";

    private static Connection connection;

    public static void main(String[] args) {
        connect();

        if (AUTO_MAKE_TABLES) {
            List<String> classList = new ArrayList<>(Arrays.asList(
                    PAKKAGE + "Student",
                    PAKKAGE + "Course"));
            TableMaker.makeTables(connection, classList);
        }

        Repository repository = new Repository();

        Student student = new Student(10105, "Иванов", 19);
        repository.save(connection, student);

        Course course = new Course(234, "Java", 40);
        repository.save(connection, course);

        disconnect();
    }

    private static void connect() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            String url = "jdbc:postgresql://localhost:5432/geek_test";
            String username = "geek";
            String pass = "geek";

            connection = DriverManager.getConnection(url, username, pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void disconnect(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
