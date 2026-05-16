package com.IO2.Gradebook.models;


import com.IO2.Gradebook.dto.UserRoleDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="uzytkownik_typ")
public class UserRole {
    @Id
    private int id;
    @Column(name = "nazwa")
    private String name;

    public UserRole(){}

    public UserRole(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserRoleDTO toDTO() {
        return new UserRoleDTO(name);
    }

}
