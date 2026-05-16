package com.IO2.Gradebook.dto;


import com.IO2.Gradebook.models.User;
import com.IO2.Gradebook.models.UserRole;

public class UserPublicData {
    private int id;
    private String email;
    private String name;
    private String surname;
    private String role;

    public UserPublicData(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.role = user.getRole().toDTO().getRole();
    }

    public UserPublicData(int id, String email, String name, String surname, UserRole role) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.role = role.toDTO().getRole();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
