package com.examallocation.exam_allocation_db.repository;

import com.examallocation.exam_allocation_db.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Integer> {

    Admin findByUsernameAndPassword(String username, String password);
}