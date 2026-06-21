package com.IO2.Gradebook.utils;

import com.IO2.Gradebook.dto.GradeDTO;
import com.IO2.Gradebook.dto.TeacherGradeEntryDTO;
import com.IO2.Gradebook.models.Grade;
import com.IO2.Gradebook.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class GradeMapperTest {

    private GradeMapper gradeMapper;

    @BeforeEach
    void setUp() {
        gradeMapper = new GradeMapper();
    }

    @Test
    void testToDTO() {
        User teacher = new User();
        teacher.setName("Jan");
        teacher.setSurname("Kowalski");

        Grade grade = new Grade();
        grade.setId(1);
        grade.setTeacher(teacher);
        grade.setText("Dobry");
        grade.setDate(java.sql.Date.valueOf(java.time.LocalDate.now()));
        grade.setValue(5);
        grade.setWeight(2);

        GradeDTO dto = gradeMapper.toDTO(grade);

        assertNotNull(dto);
        assertEquals("Jan", dto.getTeacherName());
        assertEquals("Kowalski", dto.getTeacherSurname());
        assertEquals("Dobry", dto.getText());
        assertEquals(5, dto.getValue());
        assertEquals(2, dto.getWeight());
    }

    @Test
    void testToTeacherDTO() {
        User student = new User();
        student.setName("Anna");
        student.setSurname("Nowak");

        Grade grade = new Grade();
        grade.setId(2);
        grade.setStudent(student);
        grade.setText("Bardzo dobry");
        grade.setValue(6);

        TeacherGradeEntryDTO dto = gradeMapper.toTeacherDTO(grade);

        assertNotNull(dto);
        assertEquals("Anna", dto.getStudentName());
        assertEquals("Nowak", dto.getStudentSurname());
        assertEquals(6, dto.getValue());
    }
}