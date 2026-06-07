package com.IO2.Gradebook.dto;

public class StudentStatisticsRequest {
    private Integer studentId;

    public StudentStatisticsRequest() {
    }

    public StudentStatisticsRequest(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }
}