package com.geekbrains.teryaevs.controllers;

import com.geekbrains.teryaevs.dao.DAO;
import com.geekbrains.teryaevs.entities.Consumer;
import com.geekbrains.teryaevs.entities.Product;
import com.geekbrains.teryaevs.entities.Purchase;

import java.util.Scanner;

public class Controller {
    DAO dbService;

    public Controller() {
        dbService = new DAO();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        String input;
        boolean exit = false;

        System.out.println("Введите команду");
        do {
            System.out.print(">");
            input = scanner.nextLine();
            String[] commands = input.split(" ");
            if (!commands[0].isEmpty())
                switch (commands[0]) {
                    case "/exit":
                        dbService.close();
                        exit = true;
                        break;
                    case "/showProductsByPerson":
                        showProductsByPerson(commands);
                        break;
                    case "/findPersonsByProduct":
                        findPersonsByProduct(commands);
                        break;
                    case "/removePerson":
                        removePerson(commands);
                        break;
                    case "/removeProduct":
                        removeProduct(commands);
                        break;
                    case "/buy":
                        buy(commands);
                        break;
                    default:
                        System.out.println("неизвестная команда");
                }
        } while (!exit);
    }

    private void showProductsByPerson(String[] commands) {
        if (commands.length > 1) {
            Consumer consumer = dbService.getConsumerByName(commands[1]);
            for (Purchase p : consumer.getPurchases()) {
                System.out.println(p.getProduct().getName() + " " + p.getPrice());
            }
        }
    }

    private void findPersonsByProduct(String[] commands) {
        if (commands.length > 1) {
            Product product = dbService.getProductByName(commands[1]);
            for (Purchase p : product.getPurchases()) {
                System.out.println(p.getConsumer().getName() + " " + p.getPrice());
            }
        }
    }

    private void removePerson(String[] commands) {

    }

    private void removeProduct(String[] commands) {

    }

    private void buy(String[] commands) {
        if (commands.length > 2) {
            Consumer consumer = dbService.getConsumerByName(commands[1]);
            if (consumer == null) {
                System.out.println("такого покупателя не существует");
                return;
            }
            Product product = dbService.getProductByName(commands[2]);
            if (product == null) {
                System.out.println("такого товара не существует");
                return;
            }

            if (dbService.insert(new Purchase(consumer, product, product.getPrice()))) {
                System.out.println("" + consumer.getName() + " успешно приобрел " + product.getName());
            } else {
                System.out.println("ошибка при покупке");
            }
        } else {
            System.out.println("неверное число параметров");
        }
    }

//    private void add(String[] commands) {
//        if (commands.length > 2) {
//            switch (commands[1]) {
//                case "student":
//                    if (dbService.insert(new Student(commands[2]))) {
//                        System.out.println("Студент " + commands[2] + " добавлен");
//                    } else {
//                        System.out.println("Ошибка добавления ");
//                    }
//                    break;
//                case "course":
//                    int duration;
//                    if (commands.length > 3) {
//                        try {
//                            duration = Integer.parseInt(commands[3]);
//                            if (duration > 0 && duration < 100) {
//                                if (dbService.insert(new Course(commands[2], duration))) {
//                                    System.out.println("Курс " + commands[2] + " добавлен");
//                                } else {
//                                    System.out.println("Ошибка добавления ");
//                                }
//                            } else {
//                                System.out.println("неверные параметры");
//                            }
//                        } catch (NumberFormatException e) {
//                            System.out.println("неверные параметры");
//                        }
//                    }else {
//                        System.out.println("неверное число параметров");
//                    }
//                    break;
//                default:
//                    System.out.println("неверные параметры");
//            }
//        } else {
//            System.out.println("неверное число параметров");
//        }
//    }
//
//    private void show(String[] commands) {
//        if (commands.length > 1) {
//            switch (commands[1]) {
//                case "students":
//                    List<Student> students = dbService.getAll(Student.class);
//                    for (Student s : students) {
//                        System.out.println(s.getName());
//                    }
//                    break;
//                case "courses":
//                    List<Course> courses = dbService.getAll(Course.class);
//                    for (Course c : courses) {
//                        System.out.println(c.getTitle());
//                    }
//                    break;
//                case "student":
//                    if (commands.length > 2){
//                        Student student = dbService.getStudentByName(commands[2]);
//                        for (StudentCourseInfo sci: student.getCoursesInfo())
//                            System.out.println(sci);
//                    }
//                    break;
//                case "course":
//                    if (commands.length > 2){
//                        Course course = dbService.getCourseByName(commands[2]);
//                        for (StudentCourseInfo sci: course.getStudentsInfo())
//                            System.out.println(sci);
//                    }
//                    break;
//                default:
//                    System.out.println("неверные параметры");
//            }
//        } else {
//            System.out.println("неверное число параметров");
//        }
//    }
//
//    private void enroll(String[] commands) {
//        if (commands.length > 2) {
//            Student student = dbService.getStudentByName(commands[1]);
//            if (student == null) {
//                System.out.println("такого студента не существует");
//                return;
//            }
//            Course course = dbService.getCourseByName(commands[2]);
//            if (course == null) {
//                System.out.println("такого курса не существует");
//                return;
//            }
//
//            if (dbService.insert(new StudentCourseInfo(student, course))) {
//                System.out.println("" + student.getName() + " записан на курс " + course.getTitle());
//            } else {
//                System.out.println("студент уже учится на этом курсе");
//            }
//        } else {
//            System.out.println("неверное число параметров");
//        }
//    }
//
//    private void evaluateStudentLesson(String[] commands) {
//        if (commands.length > 3) {
//            int score = 0;
//            try {
//                score = Integer.parseInt(commands[3]);
//                if (dbService.updateStudentsCourse(commands[1], commands[2], score)) {
//                    System.out.println("Урок успешно засчитан");
//                }else{
//                    System.out.println("Ошибка оценки урока");
//                }
//            } catch (NumberFormatException e) {
//                e.printStackTrace();
//                System.out.println("Количество баллов должно быть числом");
//            }
//
//        } else {
//            System.out.println("неверное число параметров");
//        }
//    }
}
