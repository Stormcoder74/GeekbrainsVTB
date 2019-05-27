package com.geekbrains.teryaevs.task2;

import java.util.*;

public class PhoneBook {
    private Map<String, Set<String>> map;

    public PhoneBook() {
        map = new TreeMap<>();
    }

    public Set<String> get(String name) {
        return map.get(name);
    }

    public String put(String name, String phone) {
        boolean phoneAdded = false;
        Set<String> phones = get(name);

        if (phones == null) {
            phones = new TreeSet<>();
            phoneAdded = phones.add(phone);
            map.put(name, phones);
        } else
            phoneAdded = phones.add(phone);

        if (phoneAdded) {
            return phone;
        }
        return null;
    }

    public Set<Map.Entry<String, Set<String>>> entrySet(){
        return map.entrySet();
    }
}
