package com.examallocation.exam_allocation_db.service;

import com.examallocation.exam_allocation_db.entity.Exam;
import com.examallocation.exam_allocation_db.repository.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ExamService {

    @Autowired
    private ExamRepository examRepository;

    public List<Exam> getAllExams() {
        return examRepository.findAll();
    }

    public String saveExam(Exam exam) {
        if (examRepository.existsBySubjectName(exam.getSubjectName())) {
            return "duplicate";
        }
        examRepository.save(exam);
        return "success";
    }

    public String updateExam(int id, Exam exam) {
        if (examRepository.existsBySubjectNameAndIdNot(exam.getSubjectName(), id)) {
            return "duplicate";
        }
        exam.setId(id);
        examRepository.save(exam);
        return "success";
    }

    public void deleteExam(int id) {
        examRepository.deleteById(id);
    }

    public Exam getExamById(int id) {
        return examRepository.findById(id).orElse(null);
    }
}