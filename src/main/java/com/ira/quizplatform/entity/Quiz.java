package com.ira.quizplatform.entity;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
public class Quiz {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;
    private String title;
    private Integer completionTime;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "quiz_question",
            joinColumns = { @JoinColumn(name = "quiz_id") },
            inverseJoinColumns = { @JoinColumn(name = "question_id") }

    )
    private List<Question> list;

    public Quiz() {
    }

    public Quiz(String title, Integer completionTime, List<Question> list) {
        this.title = title;
        this.completionTime = completionTime;
        this.list = list;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(Integer completionTime) {
        this.completionTime = completionTime;
    }

    public List<Question> getList() {
        return list;
    }

    public void setList(List<Question> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", completionTime=" + completionTime +
                ", list=" + list +
                '}';
    }
}
