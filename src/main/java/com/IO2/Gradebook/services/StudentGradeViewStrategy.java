package com.IO2.Gradebook.services;

import com.IO2.Gradebook.dto.GradeDTO;
import com.IO2.Gradebook.dto.SubjectGradesDTO;
import com.IO2.Gradebook.models.Grade;
import com.IO2.Gradebook.utils.GradeViewStrategy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentGradeViewStrategy
        implements GradeViewStrategy<List<SubjectGradesDTO>> {

    @Override
    public List<SubjectGradesDTO> build(List<Grade> grades) {

        return grades.stream()
                .collect(Collectors.groupingBy(g -> g.getSubject().getName()))
                .entrySet()
                .stream()
                .map(entry -> {

                    List<GradeDTO> gradeDTOs = entry.getValue()
                            .stream()
                            .map(GradeDTO::new)
                            .toList();

                    double average = calculateWeightedAverage(entry.getValue());

                    return new SubjectGradesDTO(
                            entry.getKey(),
                            gradeDTOs,
                            average
                    );
                })
                .toList();
    }

    private double calculateWeightedAverage(List<Grade> grades) {

        int weightedSum = grades.stream()
                .mapToInt(g -> g.getValue() * g.getWeight())
                .sum();

        int totalWeight = grades.stream()
                .mapToInt(Grade::getWeight)
                .sum();

        if (totalWeight == 0) {
            return 0;
        }

        return (double) weightedSum / totalWeight;
    }
}