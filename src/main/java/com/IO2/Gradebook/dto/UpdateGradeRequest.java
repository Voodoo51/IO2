package com.IO2.Gradebook.dto;

public class UpdateGradeRequest {
    private int gradeValue;
    private int gradeWeight;
    private String gradeText;

    public UpdateGradeRequest() {
    }


    public int getGradeValue() {
        return gradeValue;
    }
    public void setGradeValue(int gradeValue) {
        this.gradeValue = gradeValue;
    }


    public int getWeight() {
        return gradeWeight;
    }
    public String getText() {
        return gradeText;
    }
}

