package com.IO2.Gradebook.repositories;

import com.IO2.Gradebook.models.LoginData;
import com.IO2.Gradebook.models.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<User> userRowMapper = (rs, rowNum) ->
            new User(
                    rs.getInt("id"),
                    rs.getInt("uzytkownik_typ_id"),
                    rs.getInt("klasa_id"),
                    rs.getString("email"),
                    rs.getString("haslo"),
                    rs.getString("imie"),
                    rs.getString("nazwisko")
            );

    public List<User> findAll() {
        String sql = "SELECT * FROM uzytkownik";
        return jdbcTemplate.query(sql, userRowMapper);
    }

    public User findById(int id) {
        String sql = "SELECT * FROM uzytkownik WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, userRowMapper, id);
    }

    public int save(User user) {
        String sql = "INSERT INTO uzytkownik (uzytkownik_typ_id, klasa_id, mail, haslo, imie, nazwisko) VALUES (?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, user.getUserTypeId(), user.getClassId(), user.getEmail(), user.getPassword(), user.getName(), user.getSurname());
    }

    /* not needed now, reference for later
    public int update(User user) {
        String sql = "UPDATE uzytkownik SET imie = ?, email = ? WHERE id = ?";
        return jdbcTemplate.update(sql, user.getName(), user.getEmail(), user.getId());
    }
    */

    public int deleteById(int id) {
        String sql = "DELETE FROM users WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    public User tryLogin(String email, String password) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM uzytkownik WHERE email = ? and haslo = ?",
                    userRowMapper, email, password);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public String login(HttpSession session, HttpServletResponse response, HttpServletRequest request, LoginData loginData) {
        User user = getFromSession(session);
        if(user != null) {
            if(user.getEmail().equals(loginData.getEmail())) {
                System.out.println("CASE 1");
                return "OK1";
            }
            else {
                System.out.println("CASE 2");
                session.invalidate();
                session = request.getSession(true);
            }
        }

        try {
            user = jdbcTemplate.queryForObject("SELECT * FROM uzytkownik WHERE email = ? and haslo = ?",
                    userRowMapper, loginData.getEmail(), loginData.getPassword());
            System.out.println("CASE 3");

        } catch (EmptyResultDataAccessException e) {
            System.out.println("CASE 4");
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return "bad email or password";
        }
        System.out.println("CASE 5");
        session.setAttribute("id", user.getId());
        return "OK2";
    }

    public User getFromSession(HttpSession session) {
        User user = new User();
        try {
            System.out.println("SES ATRIB" + session.getAttribute("id"));
            user = jdbcTemplate.queryForObject("SELECT * FROM uzytkownik WHERE id = ?",
            userRowMapper, (int) session.getAttribute("id"));
        } catch (EmptyResultDataAccessException | NullPointerException e) {
            return null;
        }

        return user;
    }

    //public User checkUserPermission(HttpSession session)
}