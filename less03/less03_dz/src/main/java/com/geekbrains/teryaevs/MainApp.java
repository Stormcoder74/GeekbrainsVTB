package com.geekbrains.teryaevs;

import com.geekbrains.teryaevs.exceptions.*;

public class MainApp {
    public static void main(String[] args) {
        String[][] matrix = {
                {"23", "45", "73", "5"},
                {"3", "7", "36", "2"},
                {"9", "2", "84", "12"},
                {"56", "7", "2", "11"}
        };

        try {
            int sum = sumMatrix4x4(matrix);
            System.out.println("Sum of matrix items is " + sum);

        } catch (MyArraySizeException | MyArrayDataException e) {
            e.printStackTrace();
        }

    }

    private static int sumMatrix4x4(String[][] matrix) throws MyArraySizeException, MyArrayDataException {
        int size = 4;

        if (matrix.length != size)
            throw new MyArraySizeException();

        int sum = 0;
        for (int row = 0; row < size; row++) {

            if (matrix[row].length != size)
                throw new MyArraySizeException();

            for (int column = 0; column < size; column++) {
                try {

                    sum += Integer.valueOf(matrix[row][column]);

                } catch (NumberFormatException e) {

                    throw new MyArrayDataException(matrix[row][column], row, column, "matrix");
                }
            }
        }
        return sum;
    }
}
