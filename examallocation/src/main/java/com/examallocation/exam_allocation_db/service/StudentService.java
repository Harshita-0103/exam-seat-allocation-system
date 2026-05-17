package com.examallocation.exam_allocation_db.service;

import com.examallocation.exam_allocation_db.entity.Student;
import com.examallocation.exam_allocation_db.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    // VIEW ALL
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // SAVE
    public void saveStudent(Student student) {
        studentRepository.save(student);
    }

    // DELETE
    public void deleteStudent(int id) {
        studentRepository.deleteById(id);
    }

    // GET BY ID
    public Student getStudentById(int id) {
        return studentRepository.findById(id).orElse(null);
    }

    // UPDATE
    public void updateStudent(int id, Student student) {
        student.setId(id);
        studentRepository.save(student);
    }

    // FIND BY HALL TICKET AND PASSWORD
    public Student findByHallTicketAndPassword(
            String hallTicket, String password) {
        return studentRepository
                .findByHallTicketAndPassword(hallTicket, password);
    }
}