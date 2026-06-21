package com.IO2.Gradebook.services;

import com.IO2.Gradebook.components.ClassStatisticsFactory;
import com.IO2.Gradebook.components.TopStudentsStrategy;
import com.IO2.Gradebook.components.WeakestStudentsStrategy;
import com.IO2.Gradebook.dto.*;
import com.IO2.Gradebook.models.*;
import com.IO2.Gradebook.repositories.AttendanceRepository;
import com.IO2.Gradebook.repositories.GradeRepository;
import com.IO2.Gradebook.repositories.LessonRepository;
import com.IO2.Gradebook.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StatisticsService {

    @Autowired
    private GradeRepository gradeRepository;
    @Autowired
    private LessonRepository lessonRepository;
    @Autowired
    private AttendanceRepository attendanceRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TopStudentsStrategy topStudentsStrategy;
    @Autowired
    private WeakestStudentsStrategy weakestStudentsStrategy;
    @Autowired
    private ClassStatisticsFactory classStatisticsFactory;

    public StudentStatisticsDTO getStudentStatistics(Integer studentId) {

        User student = userRepository.findById(studentId)
                .orElseThrow();

        List<Grade> grades =
                gradeRepository.findAllByStudentId(studentId);

        List<Attendance> attendances =
                attendanceRepository.findAllByStudentId(studentId);

        StudentStatisticsDTO dto =
                new StudentStatisticsDTO();

        dto.setStudentId(student.getId());
        dto.setName(student.getName());
        dto.setSurname(student.getSurname());

        dto.setTotalGrades(grades.size());

        double average =
                grades.stream()
                        .mapToInt(Grade::getValue)
                        .average()
                        .orElse(0);

        dto.setAverageGrade(average);

        double weightedAverage =
                grades.stream()
                        .mapToDouble(g ->
                                g.getValue() * g.getWeight())
                        .sum()
                        /
                        grades.stream()
                                .mapToInt(Grade::getWeight)
                                .sum();

        dto.setWeightedAverage(
                Double.isNaN(weightedAverage)
                        ? 0
                        : weightedAverage
        );

        long present =
                attendances.stream()
                        .filter(Attendance::getPresent)
                        .count();

        long absent =
                attendances.size() - present;

        dto.setPresentLessons((int) present);
        dto.setAbsentLessons((int) absent);

        dto.setAttendancePercentage(
                attendances.isEmpty()
                        ? 0
                        : (present * 100.0) / attendances.size()
        );

        Map<String, Double> subjectAverages =
                grades.stream()
                        .collect(Collectors.groupingBy(
                                g -> g.getSubject().getName(),
                                Collectors.averagingInt(
                                        Grade::getValue
                                )
                        ));

        dto.setSubjectAverages(subjectAverages);

        return dto;
    }

    public ClassStatisticsDTO getClassStatistics(Integer classId, Integer teacherId) {
        List<Grade> grades = gradeRepository.findAllByTeacherAndClass(teacherId, classId);
        List<Attendance> attendances = attendanceRepository.findAllByClassId(classId);
        List<User> students = userRepository.findStudentsByClassId(classId);

        return classStatisticsFactory.create(
                classId,
                students,
                grades,
                attendances,
                topStudentsStrategy.calculate(students),
                weakestStudentsStrategy.calculate(students)
        );

        /*
        dto.setClassId(classId);

        if (!students.isEmpty()) {
            dto.setClassName(
                    students.get(0)
                            .getSchoolClass()
                            .getName()
            );
        }

        dto.setStudentCount(students.size());
        dto.setTotalGrades(grades.size());

        double classAverage = grades.stream()
                .mapToInt(Grade::getValue)
                .average()
                .orElse(0);

        dto.setClassAverage(classAverage);

        long present = attendances.stream().filter(Attendance::getPresent).count();
        double attendancePercentage = attendances.isEmpty() ? 0 : (present * 100.0) / attendances.size();

        dto.setAttendancePercentage(attendancePercentage);

        Map<Integer, Long> distribution =
                grades.stream()
                        .collect(Collectors.groupingBy(
                                Grade::getValue,
                                Collectors.counting()
                        ));

        List<GradeDistributionDTO> gradeDistribution =
                distribution.entrySet()
                        .stream()
                        .map(entry -> new GradeDistributionDTO(
                                entry.getKey(),
                                entry.getValue()
                        ))
                        .sorted(
                                Comparator.comparing(
                                        GradeDistributionDTO::getGrade
                                )
                        )
                        .toList();

        dto.setGradeDistribution(gradeDistribution);

        String className = students.get(0).getSchoolClass().getName();

        return ClassStatisticsDTO.builder()
                .classId(classId)
                .className(className)
                .studentCount(students.size())
                .totalGrades(grades.size())
                .classAverage(classAverage)
                .attendancePercentage(attendancePercentage)
                .gradeDistribution(gradeDistribution)
                .topStudents(topStudentsStrategy.calculate(students))
                .weakestStudents(weakestStudentsStrategy.calculate(students))
                .build();

         */
    }

    public List<TeacherClassDTO> getTeacherClasses(Integer teacherId) {

        return lessonRepository
                .findAllByTeacherId(teacherId)
                .stream()
                .map(Lesson::getSchoolClass)
                .distinct()
                .map(c -> new TeacherClassDTO(
                        c.getId(),
                        c.getName()
                ))
                .toList();
    }

    public List<StudentStatisticsDTO> getStudentsStatistics(Integer classId) {

        List<User> students = userRepository.findStudentsByClassId(classId);

        return students.stream().map(student -> getStudentStatistics(student.getId())).toList();
    }

    public TeacherStatisticsDTO getTeacherStatistics(Integer teacherId) {
        User teacher = userRepository.findById(teacherId).orElseThrow();
        List<Lesson> lessons = lessonRepository.findAllByTeacherId(teacherId);
        List<SchoolClass> classes = lessons.stream()
                        .map(Lesson::getSchoolClass)
                        .distinct()
                        .toList();

        List<Grade> grades = gradeRepository.findAllByTeacherId(teacherId);
        Set<Integer> studentIds =
                classes.stream()
                        .flatMap(c ->
                                userRepository
                                        .findStudentsByClassId(
                                                c.getId()
                                        )
                                        .stream()
                        )
                        .map(User::getId)
                        .collect(Collectors.toSet());

        long present = 0;
        long totalAttendance = 0;

        for (SchoolClass schoolClass : classes) {

            List<Attendance> attendanceList = attendanceRepository.findAllByClassId(schoolClass.getId());

            present += attendanceList.stream()
                    .filter(Attendance::getPresent)
                    .count();

            totalAttendance += attendanceList.size();
        }

        TeacherStatisticsDTO dto =
                new TeacherStatisticsDTO();

        dto.setTeacherId(teacher.getId());
        dto.setFirstName(teacher.getName());
        dto.setLastName(teacher.getSurname());
        dto.setClassesCount(classes.size());
        dto.setStudentsCount(studentIds.size());
        dto.setGradesGiven(grades.size());
        dto.setAverageGradeGiven(
                grades.stream()
                        .mapToInt(Grade::getValue)
                        .average()
                        .orElse(0)
        );

        dto.setAttendancePercentage(
                totalAttendance == 0
                        ? 0
                        : present * 100.0
                        / totalAttendance
        );

        List<ClassSummaryDTO> classSummaries =
                classes.stream()
                        .map(this::buildClassSummary)
                        .toList();

        dto.setClasses(classSummaries);

        return dto;
    }

    private ClassSummaryDTO buildClassSummary(SchoolClass schoolClass) {
        List<User> students = userRepository.findStudentsByClassId(schoolClass.getId());
        List<Attendance> attendances = attendanceRepository.findAllByClassId(schoolClass.getId());

        long present = attendances.stream().filter(Attendance::getPresent).count();
        double attendance = attendances.isEmpty() ? 0 : present * 100.0 / attendances.size();
        double average = students.stream().flatMap(student -> gradeRepository
                                        .findAllByStudentId(student.getId()).stream())
                        .mapToInt(Grade::getValue)
                        .average()
                        .orElse(0);

        return new ClassSummaryDTO(
            schoolClass.getId(),
            schoolClass.getName(),
            students.size(),
            average,
            attendance
        );
    }
}