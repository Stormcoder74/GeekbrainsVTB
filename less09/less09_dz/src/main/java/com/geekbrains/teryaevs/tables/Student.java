package com.geekbrains.teryaevs.tables;

import com.geekbrains.teryaevs.annotations.Column;
import com.geekbrains.teryaevs.annotations.Id;
import com.geekbrains.teryaevs.annotations.Table;

@Table(name = "students")
public class Student {

    @Id
    @Column(name = "students_id")
    private long id;

    @Column
    private String name;

    @Column
    private int age;

    public Student(long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
}
