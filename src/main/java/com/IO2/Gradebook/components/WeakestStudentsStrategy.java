package com.IO2.Gradebook.components;

import com.IO2.Gradebook.dto.StudentRankingDTO;
import com.IO2.Gradebook.models.Grade;
import com.IO2.Gradebook.models.User;
import org.springframework.stereotype.Component;
import com.IO2.Gradebook.repositories.GradeRepository;

import java.util.Comparator;
import java.util.List;

@Component
public class WeakestStudentsStrategy
        implements StudentRankingStrategy {

    private final GradeRepository gradeRepository;

    public WeakestStudentsStrategy(
            GradeRepository gradeRepository
    ) {
        this.gradeRepository = gradeRepository;
    }

    @Override
    public List<StudentRankingDTO> calculate(
            List<User> students
    ) {

        return students.stream()
                .map(student -> {

                    List<Grade> grades =
                            gradeRepository.findAllByStudentId(
                                    student.getId()
                            );

                    double average =
                            grades.stream()
                                    .mapToInt(Grade::getValue)
                                    .average()
                                    .orElse(0);

                    return new StudentRankingDTO(
                            student.getId(),
                            student.getName(),
                            student.getSurname(),
                            average
                    );
                })
                .filter(student ->
                        student.getAverage() < 2.5
                )
                .sorted(
                        Comparator.comparing(
                                StudentRankingDTO::getAverage
                        )
                )
                .limit(3)
                .toList();
    }
}