package com.geekbrains.teryaevs.task1;

import java.util.HashMap;
import java.util.Map;

public class MainApp1 {
    public static void main(String[] args) {
        String[] words = {"создать", "массив", "повторяющиеся",
                "слово", "должны", "встречаться", "повторяющиеся",
                "слово", "вывести", "список", "список", "слово",
                "должны", "которых", "состоит", "слово", "список",
                "считаем", "посчитать", "считаем"};

        Map<String, Integer> uniqueWords = new HashMap<>();
        for (String word : words)
            uniqueWords.put(word, uniqueWords.getOrDefault(word, 0) + 1);

        for (Map.Entry entry : uniqueWords.entrySet())
            System.out.println(entry.getKey() + ": " + entry.getValue());
    }
}