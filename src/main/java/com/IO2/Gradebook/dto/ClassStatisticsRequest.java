package com.IO2.Gradebook.dto;

public class ClassStatisticsRequest {
    private Integer classId;

    public ClassStatisticsRequest() {
    }

    public ClassStatisticsRequest(Integer classId) {
        this.classId = classId;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }
}