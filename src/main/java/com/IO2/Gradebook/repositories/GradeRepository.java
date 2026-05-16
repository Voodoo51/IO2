package com.IO2.Gradebook.repositories;

import com.IO2.Gradebook.models.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Integer> {
    List<Grade> findByStudentId(Integer id);

    @Query("""
    SELECT o FROM Grade o
    JOIN o.subject s
    WHERE o.student.id = :student_id
""")
    List<Grade> findAllByStudentId(@Param("student_id") Integer student_id);

    @Query("""
    SELECT o FROM Grade o
    JOIN o.subject s
    WHERE o.teacher.id = :teacher_id
""")
    List<Grade> findAllByTeacherId(@Param("teacher_id") Integer teacher_id);
}
