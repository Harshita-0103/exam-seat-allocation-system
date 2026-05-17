package com.examallocation.exam_allocation_db.repository;

import com.examallocation.exam_allocation_db.entity.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacultyRepository extends JpaRepository<Faculty, Integer> {

    boolean existsByFacultyName(String facultyName);

    boolean existsByFacultyNameAndIdNot(String facultyName, int id);
}