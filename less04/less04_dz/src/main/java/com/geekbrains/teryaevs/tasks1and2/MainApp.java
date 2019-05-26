package com.geekbrains.teryaevs.tasks1and2;

import java.util.ArrayList;
import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        String[] array = {"1", "2", "3", "4", "5"};
        transposition(array, 0, 4);

        List<String> list = toArrayList(array);

        for (String s : list)
            System.out.println(s);
    }

    private static <E> void transposition(E[] array, int position1, int position2)
            throws IndexOutOfBoundsException {
        if (position1 < array.length && position2 < array.length) {
            E tmp = array[position1];
            array[position1] = array[position2];
            array[position2] = tmp;
            return;
        }
        throw new IndexOutOfBoundsException();
    }

    private static <E> ArrayList<E> toArrayList(E... array) {
        ArrayList<E> list = new ArrayList<>(array.length);
        for (E e : array)
            list.add(e);
        return list;
    }
}
