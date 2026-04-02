package com.example.demo;

import jakarta.persistence.*;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private boolean completed;

    public Task() {}

    public Task(String name) {
        this.name = name;
        this.completed = false;
    }

    public Long getId() { return id; }

    public String getName() { return name; }

    public boolean isCompleted() { return completed; }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public void setName(String name) {
        this.name = name;
    }
}