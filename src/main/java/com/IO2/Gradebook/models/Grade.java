package com.IO2.Gradebook.models;

public class Grade {
    private int id;
    private int gradeDescId;
    private int studentId; //is user ID
    private int teacherId; //is user ID
    private int subjectNameId;
    private int value;
    private int weight;

    public Grade(int id, int gradeDescId, int studentId, int teacherId, int subjectNameId, int value, int weight) {
        this.id = id;
        this.gradeDescId = gradeDescId;
        this.studentId = studentId;
        this.teacherId = teacherId;
        this.subjectNameId = subjectNameId;
        this.value = value;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGradeDescId() {
        return gradeDescId;
    }

    public void setGradeDescId(int gradeDescId) {
        this.gradeDescId = gradeDescId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public int getSubjectNameId() {
        return subjectNameId;
    }

    public void setSubjectNameId(int subjectNameId) {
        this.subjectNameId = subjectNameId;
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
