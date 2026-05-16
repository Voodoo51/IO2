package com.IO2.Gradebook.services;

import com.IO2.Gradebook.dto.GradeDTO;
import com.IO2.Gradebook.dto.TeacherGradeEntryDTO;
import com.IO2.Gradebook.dto.TeacherGradesDTO;
import com.IO2.Gradebook.models.Grade;
import com.IO2.Gradebook.utils.GradeViewStrategy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherGradeViewStrategy
        implements GradeViewStrategy<List<TeacherGradesDTO>> {

    @Override
    public List<TeacherGradesDTO> build(List<Grade> grades) {

        return grades.stream()
                .collect(Collectors.groupingBy(g -> g.getSubject().getName()))
                .entrySet()
                .stream()
                .map(entry -> {

                    List<TeacherGradeEntryDTO> entries = entry.getValue()
                            .stream()
                            .map(TeacherGradeEntryDTO::new)
                            .toList();

                    return new TeacherGradesDTO(
                            entry.getKey(),
                            entries
                    );

                }).toList();
    }
}