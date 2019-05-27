package com.geekbrains.teryaevs.task1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class MainApp1 {
    public static void main(String[] args) {
        String[] words = {"создать", "массив", "повторяющиеся",
                "слово", "должны", "встречаться", "повторяющиеся",
                "слово", "вывести", "список", "список", "слово",
                "должны", "которых", "состоит", "слово", "список",
                "считаем", "посчитать", "считаем"};

        System.out.println(new HashSet<>(Arrays.asList(words)));

        Map<String, Integer> uniqueWords = new HashMap<>();
        for (String word : words) {
            if (uniqueWords.containsKey(word))
                uniqueWords.put(word, uniqueWords.get(word) + 1);
            else
                uniqueWords.put(word, 1);
        }

        for (Map.Entry entry : uniqueWords.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}