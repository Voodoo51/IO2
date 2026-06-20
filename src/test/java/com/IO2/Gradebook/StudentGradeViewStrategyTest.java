package com.IO2.Gradebook;

import com.IO2.Gradebook.dto.SubjectGradesDTO;
import com.IO2.Gradebook.models.*;
import com.IO2.Gradebook.services.StudentGradeViewStrategy;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class StudentGradeViewStrategyTest {
    private User createUser() {
        return new User(
                0,
                new UserRole(),
                new SchoolClass(),
                "email",
                "haslo",
                "imie",
                "nazwisko"
        );
    }
    private Grade createGrade(int value, int weight, Subject subject) {
        Grade grade = new Grade();
        grade.setValue(value);
        grade.setWeight(weight);
        grade.setStudent(createUser());
        grade.setTeacher(createUser());
        grade.setSubject(subject);
        return grade;
    }
    private final StudentGradeViewStrategy strategy =
            new StudentGradeViewStrategy();

    @Test
    void testNoGrades() {
        List<SubjectGradesDTO> result = strategy.build(List.of());

        assertTrue(result.isEmpty());
    }

    @Test
    void testGroupBySubjects() {
        Subject subject1 = new Subject();
        subject1.setName("Math");

        Grade grade1 = createGrade(5, 1, subject1);
        Grade grade2 = createGrade(4, 2, subject1);

        List<SubjectGradesDTO> result =
                strategy.build(List.of(grade1, grade2));

        assertEquals(1, result.size());

        SubjectGradesDTO dto = result.get(0);

        assertEquals("Math", dto.getSubject());
        assertEquals(2, dto.getEntries().size());
    }

    @Test
    void testAverage() {
        Subject subject1 = new Subject();
        subject1.setName("Math");

        Grade grade1 = createGrade(5, 1, subject1);
        Grade grade2 = createGrade(3, 3, subject1);

        List<SubjectGradesDTO> result =
                strategy.build(List.of(grade1, grade2));

        SubjectGradesDTO dto = result.get(0);

        double expectedAverage =
                (double) (grade1.getValue() * grade1.getWeight()
                        + grade2.getValue() * grade2.getWeight())
                / (grade1.getWeight() + grade2.getWeight());

        assertEquals(
                expectedAverage,
                dto.getAverage(),
                0.001
        );
    }

    @Test
    void testSeparateSubjects() {
        Subject subject1 = new Subject();
        subject1.setName("Math");
        Subject subject2 = new Subject();
        subject2.setName("Physics");

        Grade grade1 = createGrade(5, 1, subject1);
        Grade grade2 = createGrade(4, 1, subject2);

        List<SubjectGradesDTO> result =
                strategy.build(List.of(grade1, grade2));

        assertEquals(2, result.size());

        assertTrue(
                result.stream()
                        .anyMatch(dto ->
                                dto.getSubject().equals("Math"))
        );

        assertTrue(
                result.stream()
                        .anyMatch(dto ->
                                dto.getSubject().equals("Physics"))
        );
    }

    @Test
    void testAverageOfSingleGrade() {
        Subject subject1 = new Subject();
        subject1.setName("Math");

        Grade grade = createGrade(5, 3, subject1);

        List<SubjectGradesDTO> result =
                strategy.build(List.of(grade));

        SubjectGradesDTO dto = result.get(0);

        assertEquals(
                5.0,
                dto.getAverage(),
                0.001
        );
    }
}