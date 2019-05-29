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

    public void put(String name, String phone) {
        if (!map.containsKey(name))
            map.put(name, new TreeSet<>());
        map.get(name).add(phone);
    }
}
