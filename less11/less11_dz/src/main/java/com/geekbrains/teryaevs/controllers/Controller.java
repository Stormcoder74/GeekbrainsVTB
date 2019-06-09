package com.geekbrains.teryaevs.controllers;

import com.geekbrains.teryaevs.dao.DAO;
import com.geekbrains.teryaevs.entities.Consumer;
import com.geekbrains.teryaevs.entities.Product;
import com.geekbrains.teryaevs.entities.Purchase;

import java.util.Scanner;

public class Controller {
    private DAO dbService;

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
            if (consumer != null)
                for (Purchase p : consumer.getPurchases())
                    System.out.println(p.getProduct().getName() + " " + p.getPrice());
            else
                System.out.println("Нет такого покупателя!");
        }else
            System.out.println("неверное число параметров");
    }

    private void findPersonsByProduct(String[] commands) {
        if (commands.length > 1) {
            Product product = dbService.getProductByName(commands[1]);
            if (product != null)
                for (Purchase p : product.getPurchases())
                    System.out.println(p.getConsumer().getName() + " " + p.getPrice());
            else
                System.out.println("Нет такого товара!");
        }else
            System.out.println("неверное число параметров");
    }

    private void removePerson(String[] commands) {
        if (commands.length > 1) {
            Consumer consumer = dbService.getConsumerByName(commands[1]);
            if (consumer != null) {
                if (dbService.remove(consumer))
                    System.out.println("покупатель " + consumer.getName() + " успешно удален");
            } else
                System.out.println("Нет такого покупателя!");
        }else
            System.out.println("неверное число параметров");
    }

    private void removeProduct(String[] commands) {
        if (commands.length > 1) {
            Product product = dbService.getProductByName(commands[1]);
            if (product != null) {
                if (dbService.remove(product))
                    System.out.println("товар " + product.getName() + " успешно удален");
            } else
                System.out.println("Нет такого товара!");
        }else
            System.out.println("неверное число параметров");
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

            if (dbService.insert(new Purchase(consumer, product, product.getPrice())))
                System.out.println("покупатель " + consumer.getName() + " успешно приобрел " + product.getName());
            else
                System.out.println("ошибка при покупке");
        } else
            System.out.println("неверное число параметров");
    }
}
