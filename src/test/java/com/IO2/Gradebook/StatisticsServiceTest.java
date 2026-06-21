package com.IO2.Gradebook;

import com.IO2.Gradebook.dto.StudentStatisticsDTO;
import com.IO2.Gradebook.models.*;
import com.IO2.Gradebook.repositories.AttendanceRepository;
import com.IO2.Gradebook.repositories.GradeRepository;
import com.IO2.Gradebook.repositories.LessonRepository;
import com.IO2.Gradebook.repositories.UserRepository;
import com.IO2.Gradebook.services.StatisticsService;
import com.IO2.Gradebook.components.TopStudentsStrategy;
import com.IO2.Gradebook.components.WeakestStudentsStrategy;
import com.IO2.Gradebook.dto.ClassStatisticsDTO;
import com.IO2.Gradebook.dto.TeacherClassDTO;
import com.IO2.Gradebook.dto.TeacherStatisticsDTO;
import com.IO2.Gradebook.models.Lesson;
import com.IO2.Gradebook.models.SchoolClass;
import com.IO2.Gradebook.models.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.ArgumentMatchers.any;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StatisticsServiceTest {

    @Mock
    private GradeRepository gradeRepository;

    @Mock
    private AttendanceRepository attendanceRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private LessonRepository lessonRepository;

    @InjectMocks
    private StatisticsService statisticsService;

    @Mock
    private TopStudentsStrategy topStudentsStrategy;

    @Mock
    private WeakestStudentsStrategy weakestStudentsStrategy;

    private Grade createTestGrade(int value, int weight, String subjectName) {
        Subject subject = new Subject();
        subject.setName(subjectName);

        Grade grade = new Grade();
        grade.setValue(value);
        grade.setWeight(weight);
        grade.setSubject(subject);
        return grade;
    }

    @Test
    void testGetStudentStatisticsWithGradesAndAttendance() {
        Integer studentId = 1;

        User student = new User();
        student.setId(studentId);
        student.setName("Jan");
        student.setSurname("Kowalski");

        Grade grade1 = createTestGrade(5, 2, "Math");
        Grade grade2 = createTestGrade(3, 1, "Math");
        List<Grade> grades = List.of(grade1, grade2);

        Attendance att1 = new Attendance();
        att1.setPresent(true);
        Attendance att2 = new Attendance();
        att2.setPresent(false);
        List<Attendance> attendances = List.of(att1, att2);

        when(userRepository.findById(studentId)).thenReturn(Optional.of(student));
        when(gradeRepository.findAllByStudentId(studentId)).thenReturn(grades);
        when(attendanceRepository.findAllByStudentId(studentId)).thenReturn(attendances);

        StudentStatisticsDTO result = statisticsService.getStudentStatistics(studentId);

        assertNotNull(result);
        assertEquals(studentId, result.getStudentId());
        assertEquals("Jan", result.getName());
        assertEquals("Kowalski", result.getSurname());

        assertEquals(2, result.getTotalGrades());

        assertEquals(4.0, result.getAverageGrade(), 0.001);

        assertEquals(13.0 / 3.0, result.getWeightedAverage(), 0.001);

        assertEquals(1, result.getPresentLessons());
        assertEquals(1, result.getAbsentLessons());
        assertEquals(50.0, result.getAttendancePercentage(), 0.001);

        assertTrue(result.getSubjectAverages().containsKey("Math"));
        assertEquals(4.0, result.getSubjectAverages().get("Math"), 0.001);
    }

    @Test
    void testGetStudentStatisticsWhenNoGradesAndNoAttendance() {
        Integer studentId = 2;

        User student = new User();
        student.setId(studentId);
        student.setName("Anna");
        student.setSurname("Nowak");

        when(userRepository.findById(studentId)).thenReturn(Optional.of(student));
        when(gradeRepository.findAllByStudentId(studentId)).thenReturn(List.of());
        when(attendanceRepository.findAllByStudentId(studentId)).thenReturn(List.of());

        StudentStatisticsDTO result = statisticsService.getStudentStatistics(studentId);

        assertNotNull(result);
        assertEquals(0, result.getTotalGrades());
        assertEquals(0.0, result.getAverageGrade());
        assertEquals(0.0, result.getWeightedAverage());
        assertEquals(0.0, result.getAttendancePercentage());
        assertTrue(result.getSubjectAverages().isEmpty());
    }

    @Test
    void testGetStudentStatistics_UserNotFound() {
        Integer studentId = 999;
        when(userRepository.findById(studentId)).thenReturn(Optional.empty());

        assertThrows(Exception.class, () -> statisticsService.getStudentStatistics(studentId));
    }

    @Test
    void testGetClassStatistics() {
        Integer classId = 1;
        Integer teacherId = 1;

        SchoolClass schoolClass = new SchoolClass();
        schoolClass.setName("1A");

        User student = new User();
        student.setSchoolClass(schoolClass);

        when(gradeRepository.findAllByTeacherAndClass(teacherId, classId)).thenReturn(List.of());
        when(attendanceRepository.findAllByClassId(classId)).thenReturn(List.of());
        when(userRepository.findStudentsByClassId(classId)).thenReturn(List.of(student));

        when(topStudentsStrategy.calculate(any())).thenReturn(List.of());
        when(weakestStudentsStrategy.calculate(any())).thenReturn(List.of());

        ClassStatisticsDTO result = statisticsService.getClassStatistics(classId, teacherId);

        assertNotNull(result);
        assertEquals("1A", result.getClassName());
        assertEquals(0, result.getTotalGrades());
    }

    @Test
    void testGetTeacherClasses() {
        Integer teacherId = 1;

        SchoolClass schoolClass = new SchoolClass();
        schoolClass.setId(10);
        schoolClass.setName("Matematyka");

        Lesson lesson = new Lesson();
        lesson.setSchoolClass(schoolClass);

        when(lessonRepository.findAllByTeacherId(teacherId)).thenReturn(List.of(lesson));

        List<TeacherClassDTO> result = statisticsService.getTeacherClasses(teacherId);

        assertEquals(1, result.size());
        assertEquals("Matematyka", result.get(0).getName());
    }

    @Test
    void testGetStudentsStatistics() {
        Integer classId = 1;
        Integer studentId = 1;

        User student = new User();
        student.setId(studentId);

        when(userRepository.findStudentsByClassId(classId)).thenReturn(List.of(student));
        when(userRepository.findById(studentId)).thenReturn(Optional.of(student));
        when(gradeRepository.findAllByStudentId(studentId)).thenReturn(List.of());
        when(attendanceRepository.findAllByStudentId(studentId)).thenReturn(List.of());

        List<StudentStatisticsDTO> result = statisticsService.getStudentsStatistics(classId);

        assertEquals(1, result.size());
        assertEquals(studentId, result.get(0).getStudentId());
    }

    @Test
    void testGetTeacherStatistics() {
        Integer teacherId = 1;
        User teacher = new User();
        teacher.setId(teacherId);
        teacher.setName("Jan");
        teacher.setSurname("Nauczyciel");

        when(userRepository.findById(teacherId)).thenReturn(Optional.of(teacher));
        when(lessonRepository.findAllByTeacherId(teacherId)).thenReturn(List.of());
        when(gradeRepository.findAllByTeacherId(teacherId)).thenReturn(List.of());

        TeacherStatisticsDTO result = statisticsService.getTeacherStatistics(teacherId);

        assertNotNull(result);
        assertEquals("Jan", result.getFirstName());
        assertEquals("Nauczyciel", result.getLastName());
    }
}