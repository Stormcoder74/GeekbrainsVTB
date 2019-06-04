package com.geekbrains.teryaevs.tables;

import com.geekbrains.teryaevs.annotations.Column;
import com.geekbrains.teryaevs.annotations.Id;
import com.geekbrains.teryaevs.annotations.Table;

@Table
public class Course {

    @Id
    @Column(name = "course_id")
    private long id;

    @Column
    private String title;

    @Column
    private int hours;

    public Course(long id, String title, int hours) {
        this.id = id;
        this.title = title;
        this.hours = hours;
    }
}
