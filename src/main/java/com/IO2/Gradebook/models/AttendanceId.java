package com.IO2.Gradebook.models;

import jakarta.persistence.Column;

import java.io.Serializable;

public class AttendanceId implements Serializable {

    @Column(name = "uczen_id")
    private Integer studentId;

    @Column(name = "lekcja_id")
    private Integer lessonId;

    public AttendanceId() {
    }

    public AttendanceId(Integer studentId, Integer lessonId) {
        this.studentId = studentId;
        this.lessonId = lessonId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getLessonId() {
        return lessonId;
    }

    public void setLessonId(Integer lessonId) {
        this.lessonId = lessonId;
    }
}