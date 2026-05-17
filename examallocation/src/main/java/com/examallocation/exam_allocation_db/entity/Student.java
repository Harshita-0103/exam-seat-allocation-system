package com.examallocation.exam_allocation_db.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "hall_ticket_no")
    private String hallTicket;

    private String name;

    private String branch;

    private int year;

    private String password;

    // GETTERS & SETTERS

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHallTicket() {
        return hallTicket;
    }

    public void setHallTicket(String hallTicket) {
        this.hallTicket = hallTicket;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }


public String getPassword() {
    return password;
}

public void setPassword(String password) {
    this.password = password;
}}