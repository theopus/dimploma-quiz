package com.ira.quizplatform.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by Oleksandr_Tkachov on 20.05.2018.
 */
@Entity(name = "result")
public class Result {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Quiz quiz;

    @ManyToOne(cascade = CascadeType.ALL)
    private Student student;

    private Integer balance;

    private Boolean isPassed;

    public Result() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Boolean getPassed() {
        return isPassed;
    }

    public void setPassed(Boolean passed) {
        isPassed = passed;
    }


    @Override
    public String toString() {
        return "Result{" +
                "id=" + id +
                ", quizId=" + quiz.getId() +
                ", studentId=" + student.getId() +
                ", balance=" + balance +
                ", isPassed=" + isPassed +
                '}';
    }
}
