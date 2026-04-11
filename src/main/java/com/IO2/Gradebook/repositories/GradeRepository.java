package com.IO2.Gradebook.repositories;

import com.IO2.Gradebook.models.Grade;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GradeRepository {

    private final JdbcTemplate jdbcTemplate;

    public GradeRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Grade> gradeRowMapper = (rs, rowNum) ->
            new Grade(
                    rs.getInt("id"),
                    rs.getInt("ocena_opis_id"),
                    rs.getInt("uczen_id"),
                    rs.getInt("nauczyciel_id"),
                    rs.getInt("przedmiot_id"),
                    rs.getInt("wartosc"),
                    rs.getInt("waga")
            );

    public List<Grade> findAll() {
        String sql = "SELECT * FROM ocena";
        return jdbcTemplate.query(sql, gradeRowMapper);
    }

    public List<Grade> getStudentGrades(int id) {
        String sql = "SELECT * FROM ocena WHERE uczen_id = ?";
        return jdbcTemplate.query(sql, gradeRowMapper, id);
    }

    public List<Grade> getSubjectGradesInClass(int subjectId, int classId) {
        String sql = "SELECT o.* FROM ocena o JOIN uzytkownik u ON o.uczen_id = u.id WHERE o.przedmiot_id = ? and u.klasa_id = ?";
        return jdbcTemplate.query(sql, gradeRowMapper, subjectId, classId);
    }
}