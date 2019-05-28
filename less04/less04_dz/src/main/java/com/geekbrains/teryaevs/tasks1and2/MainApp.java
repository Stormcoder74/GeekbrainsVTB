package com.geekbrains.teryaevs.tasks1and2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        String[] array = {"1", "2", "3", "4", "5"};
        transposition(array, 0, 5);

        List<String> list = toArrayList(array);

        for (String s : list)
            System.out.println(s);
    }

    private static <E> void transposition(E[] array, int position1, int position2) {
        E tmp = array[position1];
        array[position1] = array[position2];
        array[position2] = tmp;
    }

    private static ArrayList toArrayList(Object... array) {
        return new ArrayList<>(Arrays.asList(array));
    }
}
