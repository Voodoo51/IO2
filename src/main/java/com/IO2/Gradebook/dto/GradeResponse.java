package com.IO2.Gradebook.dto;

import com.IO2.Gradebook.models.Grade;

//nie wiem czy tego trzeba honestly czy nie wyslac pusciaka spowrotem
public class GradeResponse {
    private Integer id;
    private int value;
    private int weight;
    private String subjectName;
    private String studentName;
    private String studentSurname;

    public GradeResponse(Grade grade) {
        this.id = grade.getId();
        this.value = grade.getValue();
        this.weight = grade.getWeight();
        this.subjectName = grade.getSubject().getName();
        this.studentName = grade.getStudent().getName();
        this.studentSurname = grade.getStudent().getSurname();
    }
}