package com.IO2.Gradebook.dto;

import java.util.Map;

public class StudentStatisticsDTO {

    private Integer studentId;
    private String name;
    private String surname;

    private Double averageGrade;
    private Double weightedAverage;

    private Integer totalGrades;

    private Integer presentLessons;
    private Integer absentLessons;

    private Double attendancePercentage;

    private Map<String, Double> subjectAverages;

    public StudentStatisticsDTO() {
    }

    public StudentStatisticsDTO(Integer studentId, String name, String surname, Double averageGrade, Double weightedAverage, Integer totalGrades, Integer presentLessons, Integer absentLessons, Double attendancePercentage, Map<String, Double> subjectAverages) {
        this.studentId = studentId;
        this.name = name;
        this.surname = surname;
        this.averageGrade = averageGrade;
        this.weightedAverage = weightedAverage;
        this.totalGrades = totalGrades;
        this.presentLessons = presentLessons;
        this.absentLessons = absentLessons;
        this.attendancePercentage = attendancePercentage;
        this.subjectAverages = subjectAverages;
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

    public Double getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(Double averageGrade) {
        this.averageGrade = averageGrade;
    }

    public Double getWeightedAverage() {
        return weightedAverage;
    }

    public void setWeightedAverage(Double weightedAverage) {
        this.weightedAverage = weightedAverage;
    }

    public Integer getTotalGrades() {
        return totalGrades;
    }

    public void setTotalGrades(Integer totalGrades) {
        this.totalGrades = totalGrades;
    }

    public Integer getPresentLessons() {
        return presentLessons;
    }

    public void setPresentLessons(Integer presentLessons) {
        this.presentLessons = presentLessons;
    }

    public Integer getAbsentLessons() {
        return absentLessons;
    }

    public void setAbsentLessons(Integer absentLessons) {
        this.absentLessons = absentLessons;
    }

    public Double getAttendancePercentage() {
        return attendancePercentage;
    }

    public void setAttendancePercentage(Double attendancePercentage) {
        this.attendancePercentage = attendancePercentage;
    }

    public Map<String, Double> getSubjectAverages() {
        return subjectAverages;
    }

    public void setSubjectAverages(Map<String, Double> subjectAverages) {
        this.subjectAverages = subjectAverages;
    }
}