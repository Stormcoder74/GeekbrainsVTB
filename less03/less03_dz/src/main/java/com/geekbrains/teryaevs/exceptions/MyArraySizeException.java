package com.geekbrains.teryaevs.exceptions;

public class MyArraySizeException extends RuntimeException {
    public MyArraySizeException() {
        this("Array size not match expected one");
    }

    public MyArraySizeException(String message) {
        super(message);
    }
}
