package com.IO2.Gradebook;

import com.IO2.Gradebook.controllers.StatisticsController;
import com.IO2.Gradebook.dto.ClassStatisticsDTO;
import com.IO2.Gradebook.dto.StudentStatisticsDTO;
import com.IO2.Gradebook.dto.TeacherStatisticsDTO;
import com.IO2.Gradebook.services.StatisticsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class StatisticsControllerTest {

    @Mock
    private StatisticsService statisticsService;

    @InjectMocks
    private StatisticsController statisticsController;

    @Test
    void testGetStudentStatisticsEndpoint() {
        Integer studentId = 1;

        StudentStatisticsDTO mockDto = new StudentStatisticsDTO();
        mockDto.setStudentId(studentId);
        mockDto.setName("Jan");
        mockDto.setSurname("Kowalski");
        mockDto.setAverageGrade(4.5);

        Mockito.when(statisticsService.getStudentStatistics(studentId)).thenReturn(mockDto);

        ResponseEntity<StudentStatisticsDTO> response = statisticsController.getStudentStatistics(studentId);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Jan", response.getBody().getName());
        assertEquals(4.5, response.getBody().getAverageGrade());
    }

    @Test
    void testGetClassStatisticsEndpoint() {
        ClassStatisticsDTO mockClassDto = mock(ClassStatisticsDTO.class);

        Mockito.when(statisticsService.getClassStatistics(1, 2)).thenReturn(mockClassDto);

        ResponseEntity<ClassStatisticsDTO> response = statisticsController.getClassStatistics(1, 2);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testGetClassStudentsStatisticsEndpoint() {
        Mockito.when(statisticsService.getStudentsStatistics(1)).thenReturn(List.of());

        ResponseEntity<List<StudentStatisticsDTO>> response = statisticsController.getClassStudentsStatistics(1);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isEmpty());
    }

    @Test
    void testGetTeacherStatisticsEndpoint() {
        TeacherStatisticsDTO mockTeacherDto = mock(TeacherStatisticsDTO.class);

        Mockito.when(statisticsService.getTeacherStatistics(5)).thenReturn(mockTeacherDto);

        ResponseEntity<TeacherStatisticsDTO> response = statisticsController.getTeacherStatistics(5);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}