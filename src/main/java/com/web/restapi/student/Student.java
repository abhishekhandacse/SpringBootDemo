package com.web.restapi.student;
/*Student class and student package  I have made to understand jpa*/

// Map Student to Table by making it entity

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Student {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String role;

    protected Student(){

    }

    public Student(String name, String role) {
        this.name = name;
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
