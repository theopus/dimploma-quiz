package com.ira.quizplatform.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
public class Question {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;
    private String title;
    private String description;
    private Float weitgh = 1.0f;

    @ElementCollection()
    @CollectionTable(
            name = "answers_all",
            joinColumns = @JoinColumn(name = "question_id")
    )
    private List<Answer> all;

    @ElementCollection
    @CollectionTable(
            name = "answers_correct",
            joinColumns = @JoinColumn(name = "question_id")
    )
    private List<Answer> correct;

    public Question() {
    }

    public Question(String title, String description, List<Answer> all, List<Answer> correct) {
        this.title = title;
        this.description = description;
        this.all = all;
        this.correct = correct;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Answer> getAll() {
        return all;
    }

    public void setAll(List<Answer> all) {
        this.all = all;
    }

    public List<Answer> getCorrect() {
        return correct;
    }

    public void setCorrect(List<Answer> correct) {
        this.correct = correct;
    }

    public Float getWeitgh() {
        return weitgh;
    }

    public void setWeitgh(Float weitgh) {
        this.weitgh = weitgh;
    }


    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", weitgh=" + weitgh +
                ", all=" + all +
                ", correct=" + correct +
                '}';
    }
}
