package com.IO2.Gradebook.controllers;

import com.IO2.Gradebook.dto.GradeRequest;
import com.IO2.Gradebook.dto.SubjectGradesDTO;
import com.IO2.Gradebook.dto.TeacherGradesDTO;
import com.IO2.Gradebook.dto.UserPublicData;
import com.IO2.Gradebook.misc.LoginData;
import com.IO2.Gradebook.services.AuthorizationService;
import com.IO2.Gradebook.services.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private AuthorizationService authorizationService;
    @Autowired
    private GradeService gradeService;

    @PostMapping("/login")
    public ResponseEntity<UserPublicData> login(@Validated @RequestBody LoginData loginData) {
        UserPublicData userPublicData = authorizationService.login(loginData);

        return ResponseEntity.ok(userPublicData);
    }

    @PostMapping("/grades")
    public ResponseEntity<List<SubjectGradesDTO>> getGrades(@Validated @RequestBody GradeRequest gradeRequest) {
        List<SubjectGradesDTO> subjectGradesDTO = gradeService.getAllStudentGrades(gradeRequest.getId());

        return ResponseEntity.ok(subjectGradesDTO);
    }

    @PostMapping("/teacherGrades")
    public ResponseEntity<List<TeacherGradesDTO>> getTeacherGrades(@Validated @RequestBody GradeRequest gradeRequest) {
        List<TeacherGradesDTO> teacherGradesDTO = gradeService.getAllTeacherGrades(gradeRequest.getId());

        return ResponseEntity.ok(teacherGradesDTO);
    }
}
