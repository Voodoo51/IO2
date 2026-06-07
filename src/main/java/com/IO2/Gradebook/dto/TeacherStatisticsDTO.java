package com.IO2.Gradebook.dto;

import java.util.List;

public class TeacherStatisticsDTO {
    private Integer teacherId;

    private String firstName;

    private String lastName;

    private Integer classesCount;

    private Integer studentsCount;

    private Integer gradesGiven;

    private Double averageGradeGiven;

    private Double attendancePercentage;

    private List<ClassSummaryDTO> classes;

    public TeacherStatisticsDTO() {
    }

    public TeacherStatisticsDTO(Integer teacherId, String firstName, String lastName, Integer classesCount, Integer studentsCount, Integer gradesGiven, Double averageGradeGiven, Double attendancePercentage, List<ClassSummaryDTO> classes) {
        this.teacherId = teacherId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.classesCount = classesCount;
        this.studentsCount = studentsCount;
        this.gradesGiven = gradesGiven;
        this.averageGradeGiven = averageGradeGiven;
        this.attendancePercentage = attendancePercentage;
        this.classes = classes;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getClassesCount() {
        return classesCount;
    }

    public void setClassesCount(Integer classesCount) {
        this.classesCount = classesCount;
    }

    public Integer getStudentsCount() {
        return studentsCount;
    }

    public void setStudentsCount(Integer studentsCount) {
        this.studentsCount = studentsCount;
    }

    public Integer getGradesGiven() {
        return gradesGiven;
    }

    public void setGradesGiven(Integer gradesGiven) {
        this.gradesGiven = gradesGiven;
    }

    public Double getAverageGradeGiven() {
        return averageGradeGiven;
    }

    public void setAverageGradeGiven(Double averageGradeGiven) {
        this.averageGradeGiven = averageGradeGiven;
    }

    public Double getAttendancePercentage() {
        return attendancePercentage;
    }

    public void setAttendancePercentage(Double attendancePercentage) {
        this.attendancePercentage = attendancePercentage;
    }

    public List<ClassSummaryDTO> getClasses() {
        return classes;
    }

    public void setClasses(List<ClassSummaryDTO> classes) {
        this.classes = classes;
    }
}
