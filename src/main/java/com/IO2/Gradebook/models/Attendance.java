package com.IO2.Gradebook.models;

import com.IO2.Gradebook.models.AttendanceId;
import com.IO2.Gradebook.models.User;
import jakarta.persistence.*;

@Entity
@Table(name = "obecnosc")
public class Attendance {

    @EmbeddedId
    private AttendanceId id;

    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "uczen_id")
    private User student;

    @ManyToOne
    @MapsId("lessonId")
    @JoinColumn(name = "lekcja_id")
    private Lesson lesson;

    @Column(name = "obecnosc")
    private Boolean present;

    public Attendance() {
    }

    public Attendance(AttendanceId id, User student, Lesson lesson, Boolean present) {
        this.id = id;
        this.student = student;
        this.lesson = lesson;
        this.present = present;
    }

    public AttendanceId getId() {
        return id;
    }

    public void setId(AttendanceId id) {
        this.id = id;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public Boolean getPresent() {
        return present;
    }

    public void setPresent(Boolean present) {
        this.present = present;
    }
}