package com.IO2.Gradebook.repositories;

import com.IO2.Gradebook.models.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Integer> {
    List<Lesson> findAllByTeacherId(Integer teacherId);
}