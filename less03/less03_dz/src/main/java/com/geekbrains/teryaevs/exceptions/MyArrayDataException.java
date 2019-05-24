package com.geekbrains.teryaevs.exceptions;

public class MyArrayDataException extends NumberFormatException {

    public MyArrayDataException(String item, int row, int column, String array) {
        super("Illegal number format for item \""
                + item + "\", which is located in row " + row
                + " column " + column + " of \"" + array + "\" array");
    }
}
