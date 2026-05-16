package com.IO2.Gradebook.dto;

import com.IO2.Gradebook.models.Grade;

import java.util.Date;

public class TeacherGradeEntryDTO {
    private int id;
    private String studentName;
    private String studentSurname;
    private String text;
    private Date date;
    private int value;
    private int weight;

    public TeacherGradeEntryDTO(int id, String studentName, String studentSurname, String text, Date date, int value, int weight) {
        this.id = id;
        this.studentName = studentName;
        this.studentSurname = studentSurname;
        this.text = text;
        this.date = date;
        this.value = value;
        this.weight = weight;
    }

    public TeacherGradeEntryDTO(Grade grade) {
        this.id = grade.getId();
        this.studentName = grade.getStudent().getName();
        this.studentSurname = grade.getStudent().getSurname();
        this.text = grade.getText();
        this.date = grade.getDate();
        this.value = grade.getValue();
        this.weight = grade.getWeight();
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentSurname() {
        return studentSurname;
    }

    public void setStudentSurname(String studentSurname) {
        this.studentSurname = studentSurname;
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
