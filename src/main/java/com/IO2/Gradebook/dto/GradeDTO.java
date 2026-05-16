package com.IO2.Gradebook.dto;

import com.IO2.Gradebook.models.Grade;
import com.IO2.Gradebook.models.Subject;
import com.IO2.Gradebook.models.User;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

import java.util.Date;

//dodac date moze?!
public class GradeDTO {
    private int id;
    private String teacherName;
    private String teacherSurname;
    private String text;
    private Date date;
    private int value;
    private int weight;

    public GradeDTO(Grade grade) {
        this.id = grade.getId();
        this.teacherName = grade.getTeacher().getName();
        this.teacherSurname = grade.getTeacher().getSurname();
        this.text = grade.getText();
        this.date = grade.getDate();
        this.value = grade.getValue();
        this.weight = grade.getWeight();
    }

    public GradeDTO(int id, String teacherName, String teacherSurname, String text, Date date, int value, int weight) {
        this.id = id;
        this.teacherName = teacherName;
        this.teacherSurname = teacherSurname;
        this.text = text;
        this.date = date;
        this.value = value;
        this.weight = weight;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherSurname() {
        return teacherSurname;
    }

    public void setTeacherSurname(String teacherSurname) {
        this.teacherSurname = teacherSurname;
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
