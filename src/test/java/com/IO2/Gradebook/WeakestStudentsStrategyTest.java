package com.IO2.Gradebook;

import com.IO2.Gradebook.components.WeakestStudentsStrategy;
import com.IO2.Gradebook.dto.StudentRankingDTO;
import com.IO2.Gradebook.models.Grade;
import com.IO2.Gradebook.models.User;
import com.IO2.Gradebook.repositories.GradeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WeakestStudentsStrategyTest {

    @Mock
    private GradeRepository gradeRepository;

    @InjectMocks
    private WeakestStudentsStrategy weakestStudentsStrategy;

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
    void testCalculateWeakestStudentsLogic() {
        User student1 = createStudent(1, "Jan", "Kowalski");
        User student2 = createStudent(2, "Anna", "Nowak");
        User student3 = createStudent(3, "Piotr", "Zieliński");
        User student4 = createStudent(4, "Tomasz", "Bąk");

        when(gradeRepository.findAllByStudentId(1)).thenReturn(List.of(createGrade(1)));
        when(gradeRepository.findAllByStudentId(2)).thenReturn(List.of(createGrade(2)));
        when(gradeRepository.findAllByStudentId(3)).thenReturn(List.of(createGrade(2), createGrade(2), createGrade(3)));
        when(gradeRepository.findAllByStudentId(4)).thenReturn(List.of(createGrade(4), createGrade(5)));

        List<User> studentsList = List.of(student3, student4, student1, student2);

        List<StudentRankingDTO> result = weakestStudentsStrategy.calculate(studentsList);

        assertNotNull(result);
        assertEquals(3, result.size());

        assertEquals(1, result.get(0).getStudentId());
        assertEquals(1.0, result.get(0).getAverage());

        assertEquals(2, result.get(1).getStudentId());
        assertEquals(2.0, result.get(1).getAverage());

        assertEquals(3, result.get(2).getStudentId());
        assertEquals(2.333, result.get(2).getAverage(), 0.005);

        boolean hasGoodStudent = result.stream().anyMatch(s -> s.getStudentId() == 4);
        assertFalse(hasGoodStudent);
    }

    @Test
    void testCalculateWhenEmptyList() {
        List<StudentRankingDTO> result = weakestStudentsStrategy.calculate(List.of());
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}