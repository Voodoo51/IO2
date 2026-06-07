package com.IO2.Gradebook.models;

import jakarta.persistence.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "lekcja")
public class Lesson {

    @Id
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "klasa_id")
    private SchoolClass schoolClass;

    @ManyToOne
    @JoinColumn(name = "nauczyciel_id")
    private User teacher;

    @ManyToOne
    @JoinColumn(name = "przedmiot_id")
    private Subject subject;

    @Column(name = "godzina")
    private OffsetDateTime dateTime;

    public Lesson() {
    }

    public Lesson(Integer id, SchoolClass schoolClass, User teacher, Subject subject, OffsetDateTime dateTime) {
        this.id = id;
        this.schoolClass = schoolClass;
        this.teacher = teacher;
        this.subject = subject;
        this.dateTime = dateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SchoolClass getSchoolClass() {
        return schoolClass;
    }

    public void setSchoolClass(SchoolClass schoolClass) {
        this.schoolClass = schoolClass;
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

    public OffsetDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(OffsetDateTime dateTime) {
        this.dateTime = dateTime;
    }
}