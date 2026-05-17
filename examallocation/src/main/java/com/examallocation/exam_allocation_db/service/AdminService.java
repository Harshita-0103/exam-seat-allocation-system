package com.examallocation.exam_allocation_db.service;

import com.examallocation.exam_allocation_db.entity.Admin;
import com.examallocation.exam_allocation_db.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public Admin login(
            String username,
            String password
    ) {

        return adminRepository
                .findByUsernameAndPassword(
                        username,
                        password
                );
    }
}