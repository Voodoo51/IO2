package com.IO2.Gradebook.controllers;

import com.IO2.Gradebook.models.LoginData;
import com.IO2.Gradebook.repositories.UserRepository;
import com.IO2.Gradebook.models.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id) {
        return userRepository.findById(id);
    }

    @PostMapping
    public String createUser(@RequestBody User user) {
        userRepository.save(user);
        return "User created";
    }

    @PostMapping("/login")
    public User login(HttpSession session, HttpServletResponse response, HttpServletRequest request, @Validated @RequestBody LoginData loginData) {

        User user = userRepository.getFromSession(session);
        if(user != null) {
            if(user.getEmail().equals(loginData.getEmail())) {
                System.out.println("CASE 1");
                return user;
            }
            else {
                System.out.println("CASE 2");
                session.invalidate();
                session = request.getSession(true);
            }
        }

        user = userRepository.tryLogin(loginData.getEmail(), loginData.getPassword());
        if(user == null) {
            System.out.println("CASE 4");
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return null;
        } else {
            System.out.println("CASE 5");
            session.setAttribute("id", user.getId());
            return user;
        }
    }

    @GetMapping("/info")
    public User getUserInfo(HttpServletResponse response, HttpServletRequest request) {
        HttpSession session = request.getSession(false); // ← important
        User user = userRepository.getFromSession(session);
        if(user == null) {
            //response.setStatus(HttpStatus.BAD_REQUEST.value());
        }
        return user;
    }

    /*
    @PutMapping("/{id}")
    public String updateUser(@PathVariable int id, @RequestBody User user) {
        user.setId(id);
        userRepository.update(user);
        return "User updated";
    }
     */

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);
        return "User deleted";
    }
}