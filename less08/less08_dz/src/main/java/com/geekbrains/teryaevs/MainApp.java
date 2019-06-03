package com.geekbrains.teryaevs;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainApp {
    static class WordsMap extends HashMap<String, Integer> {
        public void put(String key) {
            if(!containsKey(key)) {
                put(key, 1);
                return;
            }
            put(key, get(key) + 1);
        }
    }

    public static void main(String[] args) {
        String[] words = {"список", "массив", "повторяющиеся",
                "слово", "должны", "встречаться", "повторяющиеся",
                "слово", "вывести", "список", "список", "слово",
                "должны", "список", "состоит", "слово", "список",
                "считаем", "посчитать", "считаем"};

        WordsMap wMap = new WordsMap();
        Stream<String> wordsStream = Arrays.stream(words);
        Optional<String> mostFrequent = wordsStream
                .peek(wMap::put)
                .distinct()
                .sorted((x1, x2) -> wMap.get(x2) - wMap.get(x1))
                .findFirst();
        System.out.println(mostFrequent.orElse("список пуст"));

        Employer[] employers = {
                new Employer("Иванов", 35, 70_000),
                new Employer("Петров", 32, 83_000),
                new Employer("Сидоров", 28, 55_000),
                new Employer("Васильев", 44, 90_000),
                new Employer("Николаев", 39, 120_000),
                new Employer("Мишин", 18, 40_000)
        };
        double averageSalary = Arrays
                .stream(employers)
                .map(Employer::getSalary)
                .collect(Collectors.averagingInt(x -> x));
        System.out.printf("Средняя зарплата: %.2f\n", averageSalary);
        
        int n = 3;
        String message = Arrays.stream(employers)
                .sorted((x1, x2) -> x2.getAge() - x1.getAge())
                .limit(n)
                .map(Employer::getName)
                .collect(Collectors.joining(", "
                        , n + " самых старших сотрудников зовут: "
                        , "."));
        System.out.println(message);
    }
}
