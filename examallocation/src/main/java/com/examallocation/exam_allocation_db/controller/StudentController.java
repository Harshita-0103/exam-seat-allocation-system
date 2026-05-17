package com.examallocation.exam_allocation_db.controller;

import com.examallocation.exam_allocation_db.entity.Student;
import com.examallocation.exam_allocation_db.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    // VIEW ALL
    @GetMapping("/students")
    public String getStudents(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "students";
    }

    // ADD
    @PostMapping("/students/add")
    public String addStudent(Student student) {
        if (student.getPassword() == null || student.getPassword().isEmpty()) {
            student.setPassword(student.getHallTicket());
        }
        studentService.saveStudent(student);
        return "redirect:/students";
    }

    // DELETE
    @GetMapping("/students/delete/{id}")
    public String deleteStudent(@PathVariable int id) {
        studentService.deleteStudent(id);
        return "redirect:/students";
    }

    // EDIT
    @GetMapping("/students/edit/{id}")
    public String editStudent(@PathVariable int id, Model model) {
        model.addAttribute("editStudent", studentService.getStudentById(id));
        model.addAttribute("students", studentService.getAllStudents());
        return "students";
    }

    // UPDATE
    @PostMapping("/students/update")
    public String updateStudent(Student student) {
        studentService.updateStudent(student.getId(), student);
        return "redirect:/students";
    }
    @GetMapping("/studentdashboard")
    public String studentDashboard() {
        return "studentdashboard";
    }
}