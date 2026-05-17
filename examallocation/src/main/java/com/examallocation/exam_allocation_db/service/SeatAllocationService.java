package com.examallocation.exam_allocation_db.service;

import com.examallocation.exam_allocation_db.entity.Exam;
import com.examallocation.exam_allocation_db.entity.Hall;
import com.examallocation.exam_allocation_db.entity.SeatAllocation;
import com.examallocation.exam_allocation_db.entity.Student;

import com.examallocation.exam_allocation_db.repository.ExamRepository;
import com.examallocation.exam_allocation_db.repository.HallRepository;
import com.examallocation.exam_allocation_db.repository.SeatAllocationRepository;
import com.examallocation.exam_allocation_db.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatAllocationService {

    @Autowired
    private SeatAllocationRepository seatAllocationRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private HallRepository hallRepository;

    @Autowired
    private ExamRepository examRepository;

    // VIEW ALL
    public List<SeatAllocation> getAllSeatAllocations() {
        return seatAllocationRepository.findAll();
    }

    // SAVE
    public String saveSeatAllocation(SeatAllocation allocation) {
        if (seatAllocationRepository
                .existsByHallNameAndSeatNumberAndExamName(
                        allocation.getHallName(),
                        allocation.getSeatNumber(),
                        allocation.getExamName())) {
            return "duplicate";
        }
        seatAllocationRepository.save(allocation);
        return "success";
    }

    // UPDATE
    public String updateSeatAllocation(int id, SeatAllocation allocation) {
        if (seatAllocationRepository
                .existsByHallNameAndSeatNumberAndExamNameAndIdNot(
                        allocation.getHallName(),
                        allocation.getSeatNumber(),
                        allocation.getExamName(),
                        id)) {
            return "duplicate";
        }
        allocation.setId(id);
        seatAllocationRepository.save(allocation);
        return "success";
    }

    // DELETE
    public void deleteSeatAllocation(int id) {
        seatAllocationRepository.deleteById(id);
    }

    // GET BY ID
    public SeatAllocation getSeatAllocationById(int id) {
        return seatAllocationRepository.findById(id).orElse(null);
    }

    // AUTOMATIC SEAT ALLOCATION
    public void generateAutomaticSeatAllocation() {

        seatAllocationRepository.deleteAll();

        List<Student> students = studentRepository.findAll();
        List<Hall> halls = hallRepository.findAll();
        List<Exam> exams = examRepository.findAll();

        if (students.isEmpty() || halls.isEmpty() || exams.isEmpty()) {
            return;
        }

        int studentIndex = 0;
        int examIndex = 0;

        for (Hall hall : halls) {
            int capacity = hall.getCapacity();
            for (int seat = 1; seat <= capacity; seat++) {
                if (studentIndex >= students.size()) {
                    break;
                }

                Student student = students.get(studentIndex);
                String examName = exams.get(examIndex).getSubjectName();

                SeatAllocation allocation = new SeatAllocation();
                allocation.setStudentName(student.getName());
                allocation.setHallName(hall.getHallName());
                allocation.setSeatNumber("S" + seat);
                allocation.setExamName(examName);

                seatAllocationRepository.save(allocation);

                studentIndex++;
                examIndex++;

                if (examIndex >= exams.size()) {
                    examIndex = 0;
                }
            }
        }
    }
}