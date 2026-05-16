package com.IO2.Gradebook.dto;

import jakarta.persistence.Cacheable;

import java.util.List;

@Cacheable
public class SubjectGradesDTO {
    private String subject;
    private List<GradeDTO> entries;
    private Double average;

    public SubjectGradesDTO(String subject, List<GradeDTO> entries, Double average) {
        this.subject = subject;
        this.entries = entries;
        this.average = average;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<GradeDTO> getEntries() {
        return entries;
    }

    public void setEntries(List<GradeDTO> entries) {
        this.entries = entries;
    }

    public Double getAverage() {
        return average;
    }

    public void setAverage(Double average) {
        this.average = average;
    }
}
