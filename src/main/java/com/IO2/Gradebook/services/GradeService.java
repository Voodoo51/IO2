package com.IO2.Gradebook.services;

import com.IO2.Gradebook.dto.*;
import com.IO2.Gradebook.exceptions.InvalidLoginException;
import com.IO2.Gradebook.models.Grade;
import com.IO2.Gradebook.models.User;
import com.IO2.Gradebook.repositories.GradeRepository;
import com.IO2.Gradebook.repositories.SubjectRepository;
import com.IO2.Gradebook.repositories.UserRepository;
import jakarta.persistence.Cacheable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GradeService {
    @Autowired
    private GradeRepository gradeRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SubjectRepository subjectRepository;

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

    public GradeDTO addGrade(AddGradeRequest request) {

        Grade grade = new Grade();

        grade.setValue(request.getValue());
        grade.setWeight(request.getWeight());
        grade.setText(request.getText());
        grade.setStudent(userRepository.findById(request.getStudentId()).orElseThrow());
        grade.setTeacher(userRepository.findById(request.getTeacherId()).orElseThrow());
        grade.setSubject(subjectRepository.findById(request.getSubjectId()).orElseThrow());

        grade.setDate(new Date());

        Grade saved = gradeRepository.save(grade);
        return new GradeDTO(saved);
    }

    public void deleteGrade(int gradeId) {
        gradeRepository.deleteById(gradeId);
    }

    public GradeResponse updateGrade(int id, UpdateGradeRequest request) {

        Grade grade = gradeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Grade not found"));

        grade.setValue(request.getGradeValue());

        Grade saved = gradeRepository.save(grade);
        return new GradeResponse(saved);
    }
}
