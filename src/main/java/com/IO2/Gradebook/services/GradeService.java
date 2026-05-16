package com.IO2.Gradebook.services;

import com.IO2.Gradebook.dto.*;
import com.IO2.Gradebook.exceptions.InvalidLoginException;
import com.IO2.Gradebook.models.Grade;
import com.IO2.Gradebook.models.User;
import com.IO2.Gradebook.repositories.GradeRepository;
import jakarta.persistence.Cacheable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GradeService {
    @Autowired
    private GradeRepository gradeRepository;
    @Autowired
    private StudentGradeViewStrategy studentStrategy;
    @Autowired
    private TeacherGradeViewStrategy teacherStrategy;

    public List<SubjectGradesDTO> getAllStudentGrades(Integer id) {
        List<Grade> grades = gradeRepository.findAllByStudentId(id);

        return studentStrategy.build(grades);
    }

    public List<TeacherGradesDTO> getAllTeacherGrades(Integer id) {
        List<Grade> grades = gradeRepository.findAllByTeacherId(id);

        return teacherStrategy.build(grades);
    }
}
