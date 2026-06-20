package com.IO2.Gradebook.dto;

import org.antlr.v4.runtime.misc.NotNull;

public class AddGradeRequest {

    @NotNull
    private Integer value;
    @NotNull
    private Integer weight;
    private String text;
    @NotNull
    private Integer studentId;
    @NotNull
    private Integer teacherId;
    @NotNull
    private Integer subjectId;

    public AddGradeRequest() {}



    public Integer getValue() {
        return value;
    }
    public void setValue(Integer value) {
        this.value= value;
    }

    public Integer getWeight() {
        return weight;
    }
    public void setWeight(Integer weight) {
        this.weight= weight;
    }

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

    public Integer getStudentId() {
        return studentId;
    }
    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getTeacherId() {
        return teacherId;
    }
    public void setTeacherId(Integer teacherId) {
        this.teacherId= teacherId;
    }

    public Integer getSubjectId() {
        return subjectId;
    }
    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

}
