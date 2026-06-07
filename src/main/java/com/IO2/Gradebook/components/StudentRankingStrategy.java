package com.IO2.Gradebook.components;

import com.IO2.Gradebook.dto.StudentRankingDTO;
import com.IO2.Gradebook.models.User;

import java.util.List;

public interface StudentRankingStrategy {
    List<StudentRankingDTO> calculate(List<User> students);
}
