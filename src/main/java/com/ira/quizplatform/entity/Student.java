package com.ira.quizplatform.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class Student {

    @Id@GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name="group_id", nullable=false)
    private Group group;


    private String password;

    public Student() {
    }

    public Student(String name) {
        this.name = name;
    }

    public Student(String name, String password, Group group) {
        this.name = name;
        this.group = group;
        this.password = password;
    }

    public Student(String name, Group group) {
        this.name = name;
        this.group = group;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
