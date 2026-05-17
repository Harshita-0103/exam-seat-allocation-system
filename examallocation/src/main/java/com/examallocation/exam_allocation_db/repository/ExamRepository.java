package com.examallocation.exam_allocation_db.repository;

import com.examallocation.exam_allocation_db.entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamRepository extends JpaRepository<Exam, Integer> {

    boolean existsBySubjectName(String subjectName);

    boolean existsBySubjectNameAndIdNot(String subjectName, int id);
}