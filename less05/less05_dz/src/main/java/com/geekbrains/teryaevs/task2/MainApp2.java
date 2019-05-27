package com.geekbrains.teryaevs.task2;

import java.util.Map;
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

        String agent = "Иванов";
        Set<String> agentPhones = phoneBook.get(agent);
        if (agentPhones != null)
            for (String phone : agentPhones) {
                System.out.println(agent + ": " + phone);
            } // выведет только один телефон, поскольку добавлены одинаковые телефоны
        System.out.println();

        String neighbour = "Петров";
        Set<String> neighbourPhones = phoneBook.get(neighbour);
        if (neighbourPhones != null)
            for (String phone : phoneBook.get(neighbour)) {
                System.out.println(neighbour + ": " + phone);
            } // выведет три добавленных телефона
        System.out.println();

        for (Map.Entry<String, Set<String>> entry : phoneBook.entrySet())
            for (String phone : entry.getValue()) {
                System.out.println(entry.getKey() + ": " + phone);
            } // выведет все телефоны справочника в отсортированном виде
    }
}
