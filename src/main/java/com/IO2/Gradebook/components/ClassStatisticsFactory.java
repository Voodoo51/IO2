package com.IO2.Gradebook.components;

import com.IO2.Gradebook.dto.ClassStatisticsDTO;
import com.IO2.Gradebook.dto.GradeDistributionDTO;
import com.IO2.Gradebook.dto.StudentRankingDTO;
import com.IO2.Gradebook.models.Attendance;
import com.IO2.Gradebook.models.Grade;
import com.IO2.Gradebook.models.User;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClassStatisticsFactory {

    public ClassStatisticsDTO create(
            Integer classId,
            List<User> students,
            List<Grade> grades,
            List<Attendance> attendances,
            List<StudentRankingDTO> topStudents,
            List<StudentRankingDTO> weakestStudents
    ) {

        String className = students.isEmpty()
                ? null
                : students.get(0).getSchoolClass().getName();

        double classAverage = grades.stream()
                .mapToInt(Grade::getValue)
                .average()
                .orElse(0);

        long present = attendances.stream()
                .filter(Attendance::getPresent)
                .count();

        double attendancePercentage = attendances.isEmpty()
                ? 0
                : (present * 100.0) / attendances.size();

        List<GradeDistributionDTO> gradeDistribution =
                grades.stream()
                        .collect(Collectors.groupingBy(
                                Grade::getValue,
                                Collectors.counting()
                        ))
                        .entrySet()
                        .stream()
                        .map(entry ->
                                new GradeDistributionDTO(
                                        entry.getKey(),
                                        entry.getValue()
                                )
                        )
                        .sorted(
                                Comparator.comparing(
                                        GradeDistributionDTO::getGrade
                                )
                        )
                        .toList();

        return ClassStatisticsDTO.builder()
                .classId(classId)
                .className(className)
                .studentCount(students.size())
                .totalGrades(grades.size())
                .classAverage(classAverage)
                .attendancePercentage(attendancePercentage)
                .gradeDistribution(gradeDistribution)
                .topStudents(topStudents)
                .weakestStudents(weakestStudents)
                .build();
    }
}