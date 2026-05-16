package com.IO2.Gradebook.utils;

import com.IO2.Gradebook.dto.GradeDTO;
import com.IO2.Gradebook.dto.TeacherGradeEntryDTO;
import com.IO2.Gradebook.dto.TeacherGradesDTO;
import com.IO2.Gradebook.models.Grade;
import org.springframework.stereotype.Component;

@Component
public class GradeMapper {

    public GradeMapper() {}

    public GradeDTO toDTO(Grade grade) {
        return new GradeDTO(
                grade.getId(),
                grade.getTeacher().getName(),
                grade.getTeacher().getSurname(),
                grade.getText(),
                grade.getDate(),
                grade.getValue(),
                grade.getWeight()
        );
    }

    public TeacherGradeEntryDTO toTeacherDTO(Grade grade) {
        return new TeacherGradeEntryDTO(
                grade.getId(),
                grade.getStudent().getName(),
                grade.getStudent().getSurname(),
                grade.getText(),
                grade.getDate(),
                grade.getValue(),
                grade.getWeight()
        );
    }
}