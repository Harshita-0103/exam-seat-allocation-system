package com.examallocation.exam_allocation_db.repository;

import com.examallocation.exam_allocation_db.entity.Student;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository
        extends JpaRepository<Student, Integer> {
    Student findByHallTicketAndPassword(String hallTicket, String password);}