package com.IO2.Gradebook;

import com.IO2.Gradebook.dto.SubjectGradesDTO;
import com.IO2.Gradebook.dto.TeacherGradesDTO;
import com.IO2.Gradebook.models.Grade;
import com.IO2.Gradebook.repositories.GradeRepository;
import com.IO2.Gradebook.services.GradeService;
import com.IO2.Gradebook.services.StudentGradeViewStrategy;
import com.IO2.Gradebook.services.TeacherGradeViewStrategy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GradeServiceTest {

    @Mock
    private GradeRepository gradeRepository;

    @Mock
    private StudentGradeViewStrategy studentStrategy;

    @Mock
    private TeacherGradeViewStrategy teacherStrategy;

    @InjectMocks
    private GradeService gradeService;

    @Test
    void testReturningStudentGrades() {
        Integer studentId = 1;

        List<Grade> grades = List.of(
                mock(Grade.class),
                mock(Grade.class)
        );

        List<SubjectGradesDTO> expectedResult = List.of(
                mock(SubjectGradesDTO.class)
        );

        when(gradeRepository.findAllByStudentId(studentId))
                .thenReturn(grades);

        when(studentStrategy.build(grades))
                .thenReturn(expectedResult);

        List<SubjectGradesDTO> result =
                gradeService.getAllStudentGrades(studentId);

        assertSame(expectedResult, result);

        verify(gradeRepository).findAllByStudentId(studentId);
        verify(studentStrategy).build(grades);

        verifyNoInteractions(teacherStrategy);
    }

    @Test
    void testReturningTeacherGrades() {
        Integer teacherId = 1;

        List<Grade> grades = List.of(
                mock(Grade.class)
        );

        List<TeacherGradesDTO> expectedResult = List.of(
                mock(TeacherGradesDTO.class)
        );

        when(gradeRepository.findAllByTeacherId(teacherId))
                .thenReturn(grades);

        when(teacherStrategy.build(grades))
                .thenReturn(expectedResult);

        List<TeacherGradesDTO> result =
                gradeService.getAllTeacherGrades(teacherId);

        assertSame(expectedResult, result);

        verify(gradeRepository).findAllByTeacherId(teacherId);
        verify(teacherStrategy).build(grades);

        verifyNoInteractions(studentStrategy);
    }
}