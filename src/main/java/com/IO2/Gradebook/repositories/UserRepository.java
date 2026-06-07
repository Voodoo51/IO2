package com.IO2.Gradebook.repositories;

import com.IO2.Gradebook.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
    User findByEmailAndPassword(String email, String password);
    List<User> findAllBySchoolClassId(Integer classId);
    @Query("""
    SELECT u
    FROM User u
    WHERE u.schoolClass.id = :classId
      AND u.role.name = 'Uczen'
""")
    List<User> findStudentsByClassId(@Param("classId") Integer classId);
}

