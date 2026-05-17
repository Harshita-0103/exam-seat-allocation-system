package com.examallocation.exam_allocation_db.repository;

import com.examallocation.exam_allocation_db.entity.InvigilatorAllocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvigilatorAllocationRepository extends JpaRepository<InvigilatorAllocation, Integer> {

    boolean existsByFacultyIdAndDutyDate(int facultyId, String dutyDate);

    boolean existsByFacultyIdAndDutyDateAndIdNot(int facultyId, String dutyDate, int id);
}