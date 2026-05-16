package com.IO2.Gradebook.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "ocena")
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne
    @JoinColumn(name="uczen_id", nullable = false)
    private User student;
    @ManyToOne
    @JoinColumn(name="nauczyciel_id", nullable = false)
    private User teacher;
    @ManyToOne
    @JoinColumn(name="przedmiot_id", nullable = false)
    private Subject subject;
    @Column(name="opis")
    private String text;
    @Column(name="data")
    private Date date;
    @Column(name="wartosc")
    private int value;
    @Column(name="waga")
    private int weight;

    public Grade() {}

    public Grade(int id, User student, User teacher, Subject subject, String text, int value, int weight) {
        this.id = id;
        this.student = student;
        this.teacher = teacher;
        this.subject = subject;
        this.text = text;
        this.value = value;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public User getTeacher() {
        return teacher;
    }

    public void setTeacher(User teacher) {
        this.teacher = teacher;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
