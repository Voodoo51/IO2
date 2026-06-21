package com.IO2.Gradebook.controllers;

import com.IO2.Gradebook.dto.*;
import com.IO2.Gradebook.misc.LoginData;
import com.IO2.Gradebook.services.AuthorizationService;
import com.IO2.Gradebook.services.GradeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.Console;
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

    @PostMapping("/grade")
    public ResponseEntity<GradeDTO> addGrade(@Valid @RequestBody AddGradeRequest request) {
        return ResponseEntity.ok(gradeService.addGrade(request));
    }//Idk czy tu pelne dto zwracac ale na wszelki wypadek, zawsze mozna zmienic na response

    @DeleteMapping("/grade/{id}")
    public ResponseEntity<Void> deleteGrade(@PathVariable Integer id) {
        System.err.println("DELETE________________________________");
        gradeService.deleteGrade(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/grade/{id}")
    public ResponseEntity<GradeResponse> updateGrade(
            @PathVariable Integer id,
            @RequestBody UpdateGradeRequest request) {
        System.out.println("UPDATE ______________________________");
        System.out.println(request.getGradeValue());
        System.out.println(request.getWeight());
        System.out.println(request.getText());

        return ResponseEntity.ok(gradeService.updateGrade(id, request));
    }
}
