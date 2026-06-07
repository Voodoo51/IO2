package com.IO2.Gradebook.repositories;

import com.IO2.Gradebook.models.Attendance;
import com.IO2.Gradebook.models.AttendanceId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, AttendanceId> {
    List<Attendance> findAllByStudentId(Integer studentId);
    @Query("""
    SELECT a
    FROM Attendance a
    WHERE a.student.schoolClass.id = :class_Id
""")
    List<Attendance> findAllByClassId(
            @Param("class_Id") Integer class_Id
    );}