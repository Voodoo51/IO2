package com.IO2.Gradebook.models;

import jakarta.persistence.*;

/*

CREATE TABLE uzytkownik(
      id SERIAL primary key,
      uzytkownik_typ_id int references uzytkownik_typ(id),
      klasa_id int references klasa(id),
      email varchar(30) UNIQUE,
      haslo varchar(30),
      imie varchar(30),
      nazwisko varchar(30)
);
 */

@Entity
@Table(name="uzytkownik")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @OneToOne
    @JoinColumn(name="uzytkownik_typ_id", nullable = false)
    private UserRole role;
    @OneToOne
    @JoinColumn(name="klasa_id", nullable = false)
    private SchoolClass schoolClass;
    private String email;
    @Column(name = "haslo")
    private String password;
    @Column(name = "imie")
    private String name;
    @Column(name = "nazwisko")
    private String surname;

    public User() {};

    public User(Integer id, UserRole role, SchoolClass schoolClass, String email, String password, String name, String surname) {
        this.id = id;
        this.role = role;
        this.schoolClass = schoolClass;
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public SchoolClass getSchoolClass() {
        return schoolClass;
    }

    public void setSchoolClass(SchoolClass schoolClass) {
        this.schoolClass = schoolClass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
}
