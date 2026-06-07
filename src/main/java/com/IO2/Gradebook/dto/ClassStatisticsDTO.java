package com.IO2.Gradebook.dto;

import java.util.List;

public class ClassStatisticsDTO {
    private Integer classId;
    private String className;
    private Integer studentCount;
    private Integer totalGrades;
    private Double classAverage;
    private Double attendancePercentage;
    private List<GradeDistributionDTO> gradeDistribution;
    private List<StudentRankingDTO> topStudents;
    private List<StudentRankingDTO> weakestStudents;

    public ClassStatisticsDTO() {
    }

    public ClassStatisticsDTO(Integer classId, String className, Integer studentCount, Integer totalGrades, Double classAverage, Double attendancePercentage, List<GradeDistributionDTO> gradeDistribution, List<StudentRankingDTO> topStudents, List<StudentRankingDTO> weakestStudents) {
        this.classId = classId;
        this.className = className;
        this.studentCount = studentCount;
        this.totalGrades = totalGrades;
        this.classAverage = classAverage;
        this.attendancePercentage = attendancePercentage;
        this.gradeDistribution = gradeDistribution;
        this.topStudents = topStudents;
        this.weakestStudents = weakestStudents;
    }

    private ClassStatisticsDTO(Builder builder) {
        this.classId = builder.classId;
        this.className = builder.className;
        this.studentCount = builder.studentCount;
        this.totalGrades = builder.totalGrades;
        this.classAverage = builder.classAverage;
        this.attendancePercentage = builder.attendancePercentage;
        this.gradeDistribution = builder.gradeDistribution;
        this.topStudents = builder.topStudents;
        this.weakestStudents = builder.weakestStudents;
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

    public Integer getTotalGrades() {
        return totalGrades;
    }

    public void setTotalGrades(Integer totalGrades) {
        this.totalGrades = totalGrades;
    }

    public Double getClassAverage() {
        return classAverage;
    }

    public void setClassAverage(Double classAverage) {
        this.classAverage = classAverage;
    }

    public Double getAttendancePercentage() {
        return attendancePercentage;
    }

    public void setAttendancePercentage(Double attendancePercentage) {
        this.attendancePercentage = attendancePercentage;
    }

    public List<GradeDistributionDTO> getGradeDistribution() {
        return gradeDistribution;
    }

    public void setGradeDistribution(List<GradeDistributionDTO> gradeDistribution) {
        this.gradeDistribution = gradeDistribution;
    }

    public List<StudentRankingDTO> getTopStudents() {
        return topStudents;
    }

    public void setTopStudents(List<StudentRankingDTO> topStudents) {
        this.topStudents = topStudents;
    }

    public List<StudentRankingDTO> getWeakestStudents() {
        return weakestStudents;
    }

    public void setWeakestStudents(List<StudentRankingDTO> weakestStudents) {
        this.weakestStudents = weakestStudents;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Integer classId;
        private String className;

        private Integer studentCount;
        private Integer totalGrades;

        private Double classAverage;
        private Double attendancePercentage;

        private List<GradeDistributionDTO> gradeDistribution;

        private List<StudentRankingDTO> topStudents;
        private List<StudentRankingDTO> weakestStudents;

        public Builder classId(Integer classId) {
            this.classId = classId;
            return this;
        }

        public Builder className(String className) {
            this.className = className;
            return this;
        }

        public Builder studentCount(Integer studentCount) {
            this.studentCount = studentCount;
            return this;
        }

        public Builder totalGrades(Integer totalGrades) {
            this.totalGrades = totalGrades;
            return this;
        }

        public Builder classAverage(Double classAverage) {
            this.classAverage = classAverage;
            return this;
        }

        public Builder attendancePercentage(Double attendancePercentage) {
            this.attendancePercentage = attendancePercentage;
            return this;
        }

        public Builder gradeDistribution(
                List<GradeDistributionDTO> gradeDistribution
        ) {
            this.gradeDistribution = gradeDistribution;
            return this;
        }

        public Builder topStudents(
                List<StudentRankingDTO> topStudents
        ) {
            this.topStudents = topStudents;
            return this;
        }

        public Builder weakestStudents(
                List<StudentRankingDTO> weakestStudents
        ) {
            this.weakestStudents = weakestStudents;
            return this;
        }

        public ClassStatisticsDTO build() {
            return new ClassStatisticsDTO(this);
        }
    }
}