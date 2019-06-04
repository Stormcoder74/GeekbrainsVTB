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

    public Student() {
    }

    public Student(long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
