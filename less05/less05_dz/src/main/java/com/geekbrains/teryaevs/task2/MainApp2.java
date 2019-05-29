package com.geekbrains.teryaevs.task2;

import java.util.Set;

public class MainApp2 {
    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();

        phoneBook.put("Иванов", "12344561289");
        phoneBook.put("Петров", "59583657589");
        phoneBook.put("Сидоров", "89544561289");
        phoneBook.put("Иванов", "12344561289");
        phoneBook.put("Петров", "35839328456");
        phoneBook.put("Петров", "12758483664");
        phoneBook.put("Васин", "58375768474");
        phoneBook.put("Борисов", "13858635475");

        for (String phone : phoneBook.get("Иванов"))
            System.out.println("Иванов" + ": " + phone);

        for (String phone : phoneBook.get("Петров"))
            System.out.println("Петров" + ": " + phone);
    }
}
