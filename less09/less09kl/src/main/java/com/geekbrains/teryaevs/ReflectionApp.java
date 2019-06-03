package com.geekbrains.teryaevs;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class ReflectionApp {
    public static void main(String[] args) {
        Class strClass = String.class;
        System.out.println("Полное имя класса: " + strClass.getName());
        System.out.println("Простое имя класса: " + strClass.getSimpleName());

        int modifiers = strClass.getModifiers();
        if (Modifier.isPublic(modifiers)) {
            System.out.println(strClass.getSimpleName() + " - public");
        }
        if (Modifier.isAbstract(modifiers)) {
            System.out.println(strClass.getSimpleName() + " - abstract");
        }
        if (Modifier.isFinal(modifiers)) {
            System.out.println(strClass.getSimpleName() + " - final");
        }

        try {
            Cat cat1 = new Cat("Барсик", 3, "черный");
            Cat cat2 = new Cat("Васька", 5, "белый");

            System.out.println(cat1);
            System.out.println(cat2);

            Field fieldName = cat1.getClass().getField("name");
            fieldName.set(cat1, "Мурзик");
            fieldName.set(cat2, "Феофан");

            System.out.println(cat1);
            System.out.println(cat2);

            Field fieldAge = cat1.getClass().getField("age");
            System.out.println(fieldAge.get(cat1));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        System.out.println();
        try {
            ClassWithPrivateField obj = new ClassWithPrivateField(10);
            obj.info();
            Field privateField =
                    ClassWithPrivateField.class.getDeclaredField("field");
            privateField.setAccessible(true);
            System.out.println("get: " + privateField.get(obj));
            privateField.set(obj, 1000);
            obj.info();
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        try {
            System.out.println(Cat.class.getConstructor(new Class[] {String.class, int.class}));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
