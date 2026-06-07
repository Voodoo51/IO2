package com.IO2.Gradebook.dto;

public class ClassSummaryDTO {
    private Integer classId;

    private String className;

    private Integer studentCount;

    private Double averageGrade;

    private Double attendancePercentage;

    public ClassSummaryDTO() {
    }

    public ClassSummaryDTO(Integer classId, String className, Integer studentCount, Double averageGrade, Double attendancePercentage) {
        this.classId = classId;
        this.className = className;
        this.studentCount = studentCount;
        this.averageGrade = averageGrade;
        this.attendancePercentage = attendancePercentage;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Integer getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(Integer studentCount) {
        this.studentCount = studentCount;
    }

    public Double getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(Double averageGrade) {
        this.averageGrade = averageGrade;
    }

    public Double getAttendancePercentage() {
        return attendancePercentage;
    }

    public void setAttendancePercentage(Double attendancePercentage) {
        this.attendancePercentage = attendancePercentage;
    }
}
