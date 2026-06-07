package com.IO2.Gradebook.dto;

public class StudentRankingDTO {
    private Integer studentId;
    private String name;
    private String surname;
    private Double average;

    public StudentRankingDTO() {
    }

    public StudentRankingDTO(Integer studentId, String name, String surname, Double average) {
        this.studentId = studentId;
        this.name = name;
        this.surname = surname;
        this.average = average;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Double getAverage() {
        return average;
    }

    public void setAverage(Double average) {
        this.average = average;
    }
}
