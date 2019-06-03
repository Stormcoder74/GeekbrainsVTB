package com.geekbrains.teryaevs;

import java.lang.reflect.Method;

public class AnnotationApp {
    @MyAnnotation
    public void markedMethod() {
        System.out.println( "Java" );
    }
    public static void main(String[] args) {
        Method[] methods = Cat.class.getDeclaredMethods();
        for (Method o : methods) {
            if (o.getAnnotation(MyAnnotation.class) != null ) {
                System.out.println(o);
            }
        }
    }
}
