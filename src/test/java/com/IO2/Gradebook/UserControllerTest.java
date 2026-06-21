package com.IO2.Gradebook;

import com.IO2.Gradebook.controllers.UserController;
import com.IO2.Gradebook.dto.*;
import com.IO2.Gradebook.misc.LoginData;
import com.IO2.Gradebook.services.AuthorizationService;
import com.IO2.Gradebook.services.GradeService;
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
class UserControllerTest {

    @Mock
    private AuthorizationService authorizationService;

    @Mock
    private GradeService gradeService;

    @InjectMocks
    private UserController userController;

    @Test
    void testLoginEndpoint() {
        LoginData loginData = mock(LoginData.class);
        UserPublicData mockPublicData = mock(UserPublicData.class);

        Mockito.when(authorizationService.login(loginData)).thenReturn(mockPublicData);

        ResponseEntity<UserPublicData> response = userController.login(loginData);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockPublicData, response.getBody());
    }

    @Test
    void testGetGradesEndpoint() {
        GradeRequest gradeRequest = mock(GradeRequest.class);
        Mockito.when(gradeRequest.getId()).thenReturn(1);
        Mockito.when(gradeService.getAllStudentGrades(1)).thenReturn(List.of());

        ResponseEntity<List<SubjectGradesDTO>> response = userController.getGrades(gradeRequest);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isEmpty());
    }

    @Test
    void testGetTeacherGradesEndpoint() {
        GradeRequest gradeRequest = mock(GradeRequest.class);
        Mockito.when(gradeRequest.getId()).thenReturn(2);
        Mockito.when(gradeService.getAllTeacherGrades(2)).thenReturn(List.of());

        ResponseEntity<List<TeacherGradesDTO>> response = userController.getTeacherGrades(gradeRequest);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isEmpty());
    }

    @Test
    void testAddGradeEndpoint() {
        AddGradeRequest request = mock(AddGradeRequest.class);
        GradeDTO mockGradeDto = mock(GradeDTO.class);

        Mockito.when(gradeService.addGrade(request)).thenReturn(mockGradeDto);

        ResponseEntity<GradeDTO> response = userController.addGrade(request);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockGradeDto, response.getBody());
    }

    @Test
    void testDeleteGradeEndpointShouldReturnNoContent() {
        Integer gradeId = 10;

        Mockito.doNothing().when(gradeService).deleteGrade(gradeId);

        ResponseEntity<Void> response = userController.deleteGrade(gradeId);

        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void testUpdateGradeEndpoint() {
        Integer gradeId = 5;
        UpdateGradeRequest request = mock(UpdateGradeRequest.class);
        GradeResponse mockResponse = mock(GradeResponse.class);

        Mockito.when(gradeService.updateGrade(gradeId, request)).thenReturn(mockResponse);

        ResponseEntity<GradeResponse> response = userController.updateGrade(gradeId, request);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockResponse, response.getBody());
    }
}