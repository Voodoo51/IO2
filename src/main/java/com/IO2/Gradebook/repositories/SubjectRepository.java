package com.IO2.Gradebook.repositories;

import com.IO2.Gradebook.models.Subject;
import com.IO2.Gradebook.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {

}