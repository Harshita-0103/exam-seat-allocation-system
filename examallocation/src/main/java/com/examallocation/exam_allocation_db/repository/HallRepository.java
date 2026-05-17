package com.examallocation.exam_allocation_db.repository;

import com.examallocation.exam_allocation_db.entity.Hall;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HallRepository extends JpaRepository<Hall, Integer> {

    boolean existsByHallName(String hallName);

    boolean existsByHallNameAndIdNot(String hallName, int id);
}