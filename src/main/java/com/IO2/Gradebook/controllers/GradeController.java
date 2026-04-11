package com.IO2.Gradebook.controllers;

import com.IO2.Gradebook.repositories.GradeRepository;
import com.IO2.Gradebook.models.Grade;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequestMapping("/grade")
public class GradeController {

    private final GradeRepository gradeRepository;

    public GradeController(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    @GetMapping("/{id}")
    public List<Grade> getStudentGrades(@PathVariable int id, HttpServletResponse res) {
        res.setStatus(HttpStatus.OK.value());
        return gradeRepository.getStudentGrades(id);
    }

    @GetMapping("{subjectId}/{classId}")
    public List<Grade> getSubjectGradesInClass(@PathVariable int subjectId, @PathVariable int classId) {
        return gradeRepository.getSubjectGradesInClass(subjectId, classId);
    }
}