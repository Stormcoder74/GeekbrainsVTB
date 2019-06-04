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

    public Course() {
    }

    public Course(long id, String title, int hours) {
        this.id = id;
        this.title = title;
        this.hours = hours;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", hours=" + hours +
                '}';
    }
}
