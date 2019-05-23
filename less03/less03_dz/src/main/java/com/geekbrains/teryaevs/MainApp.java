package com.geekbrains.teryaevs;

import com.geekbrains.teryaevs.exceptions.MyArrayDataException;
import com.geekbrains.teryaevs.exceptions.MyArraySizeException;

public class MainApp {
    public static void main(String[] args) {
        String[][] matrix = {
                {"23", "45", "73", "5"},
                {"3", "7", "36", "2"},
                {"9", "2", "84", "12"},
                {"56", "7", "2", "11"}
        };

        int sum = sumMatrix4x4(matrix);

    }

    static int sumMatrix4x4(String[][] matrix) throws MyArraySizeException, MyArrayDataException {
        if (matrix.length != 4)
            throw new MyArraySizeException();
        for (String[] row : matrix) {
            if (row.length != 4)
                throw new MyArraySizeException();
        }

        int sum = 0;


        return sum;
    }
}
