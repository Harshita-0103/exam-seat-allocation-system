package com.examallocation.exam_allocation_db.repository;

import com.examallocation.exam_allocation_db.entity.SeatAllocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatAllocationRepository extends JpaRepository<SeatAllocation, Integer> {

    // Same hall + same seat + same exam duplicate check
    boolean existsByHallNameAndSeatNumberAndExamName(
            String hallName, String seatNumber, String examName);

    boolean existsByHallNameAndSeatNumberAndExamNameAndIdNot(
            String hallName, String seatNumber, String examName, int id);
}