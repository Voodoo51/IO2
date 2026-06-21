package com.IO2.Gradebook;

import com.IO2.Gradebook.components.TopStudentsStrategy;
import com.IO2.Gradebook.dto.StudentRankingDTO;
import com.IO2.Gradebook.models.Grade;
import com.IO2.Gradebook.models.User;
import com.IO2.Gradebook.repositories.GradeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TopStudentsStrategyTest {

    @Mock
    private GradeRepository gradeRepository;

    @InjectMocks
    private TopStudentsStrategy topStudentsStrategy;

    private User createStudent(Integer id, String name, String surname) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setSurname(surname);
        return user;
    }

    private Grade createGrade(int value) {
        Grade grade = new Grade();
        grade.setValue(value);
        return grade;
    }

    @Test
    void testCalculateTopStudentsLogic() {
        User student1 = createStudent(1, "Jan", "Kowalski");
        User student2 = createStudent(2, "Anna", "Nowak");
        User student3 = createStudent(3, "Piotr", "Zieliński");
        User student4 = createStudent(4, "Tomasz", "Bąk");

        when(gradeRepository.findAllByStudentId(1)).thenReturn(List.of(createGrade(5), createGrade(5)));
        when(gradeRepository.findAllByStudentId(2)).thenReturn(List.of(createGrade(4)));
        when(gradeRepository.findAllByStudentId(3)).thenReturn(List.of(createGrade(3)));
        when(gradeRepository.findAllByStudentId(4)).thenReturn(List.of(createGrade(2)));

        List<User> studentsList = List.of(student3, student1, student4, student2);

        List<StudentRankingDTO> result = topStudentsStrategy.calculate(studentsList);

        assertNotNull(result);
        assertEquals(3, result.size(), "Lista powinna zostać ograniczona do maksymalnie 3 najlepszych uczniów");

        assertEquals(1, result.get(0).getStudentId());
        assertEquals(5.0, result.get(0).getAverage());

        assertEquals(2, result.get(1).getStudentId());
        assertEquals(4.0, result.get(1).getAverage());

        assertEquals(3, result.get(2).getStudentId());
        assertEquals(3.0, result.get(2).getAverage());

        boolean hasDroppedStudent = result.stream().anyMatch(s -> s.getStudentId() == 4);
        assertFalse(hasDroppedStudent);
    }

    @Test
    void testCalculateWhenEmptyList() {
        List<StudentRankingDTO> result = topStudentsStrategy.calculate(List.of());
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}