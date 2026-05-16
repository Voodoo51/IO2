package com.IO2.Gradebook.models;

import jakarta.persistence.*;

@Entity
@Table(name = "przedmiot")
public class Subject {
    @Id
    private int id;
    @Column(name="nazwa")
    private String name;

    public Subject(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Subject() {}

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
}
