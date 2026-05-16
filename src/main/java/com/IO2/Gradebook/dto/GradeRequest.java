package com.IO2.Gradebook.dto;

public class GradeRequest {
    private int id;

    public GradeRequest(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
