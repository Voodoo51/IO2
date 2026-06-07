package com.IO2.Gradebook.dto;

public class GradeDistributionDTO {

    private Integer grade;
    private Long count;

    public GradeDistributionDTO() {
    }

    public GradeDistributionDTO(Integer grade, Long count) {
        this.grade = grade;
        this.count = count;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}