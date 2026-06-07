package com.IO2.Gradebook.repositories;

import com.IO2.Gradebook.models.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StatisticsRepository extends JpaRepository<Grade, Integer> {

    @Query(value = """
        SELECT COUNT(*)
        FROM obecnosc
        WHERE uczen_id = :studentId
        AND obecnosc = true
    """, nativeQuery = true)
    Long countPresent(Integer studentId);

    @Query(value = """
        SELECT COUNT(*)
        FROM obecnosc
        WHERE uczen_id = :studentId
    """, nativeQuery = true)
    Long countAllLessons(Integer studentId);
}