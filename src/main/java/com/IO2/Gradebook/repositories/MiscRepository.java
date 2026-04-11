package com.IO2.Gradebook.repositories;

import com.IO2.Gradebook.models.Dictionary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MiscRepository {

    private final JdbcTemplate jdbcTemplate;

    public MiscRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Dictionary> dictRowMapper = (rs, rowNum) ->
            new Dictionary(
                    rs.getInt("id"),
                    rs.getString("nazwa")
            );

    public List<Dictionary> getAllSubjects() {
        String sql = "SELECT * FROM przedmiot";
        return jdbcTemplate.query(sql, dictRowMapper);
    }

    public List<Dictionary> getAllClasses() {
        String sql = "SELECT * FROM klasa";
        return jdbcTemplate.query(sql, dictRowMapper);
    }

    public List<Dictionary> getAllUserTypes() {
        String sql = "SELECT * FROM uzytkownik_typ";
        return jdbcTemplate.query(sql, dictRowMapper);
    }
}