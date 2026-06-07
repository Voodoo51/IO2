package com.IO2.Gradebook.controllers;

import com.IO2.Gradebook.dto.ClassStatisticsDTO;
import com.IO2.Gradebook.dto.StudentStatisticsDTO;
import com.IO2.Gradebook.dto.TeacherStatisticsDTO;
import com.IO2.Gradebook.services.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    @GetMapping("/student/{id}")
    public ResponseEntity<StudentStatisticsDTO>
    getStudentStatistics(@PathVariable Integer id) {

        return ResponseEntity.ok(
                statisticsService.getStudentStatistics(id)
        );
    }

    @GetMapping("/class/{classId}/teacher/{teacherId}")
    public ResponseEntity<ClassStatisticsDTO>
    getClassStatistics(
            @PathVariable Integer classId,
            @PathVariable Integer teacherId
    ) {

        return ResponseEntity.ok(
                statisticsService.getClassStatistics(
                        classId,
                        teacherId
                )
        );
    }

    @GetMapping("/class/{classId}/students")
    public ResponseEntity<List<StudentStatisticsDTO>>
    getClassStudentsStatistics(@PathVariable Integer classId) {

        return ResponseEntity.ok(
                statisticsService.getStudentsStatistics(classId)
        );
    }

    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<TeacherStatisticsDTO>
    getTeacherStatistics(
            @PathVariable Integer teacherId
    ) {
        return ResponseEntity.ok(
                statisticsService
                        .getTeacherStatistics(teacherId)
        );
    }
}