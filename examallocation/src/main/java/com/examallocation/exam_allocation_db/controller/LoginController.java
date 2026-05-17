package com.examallocation.exam_allocation_db.controller;

import com.examallocation.exam_allocation_db.entity.Admin;
import com.examallocation.exam_allocation_db.entity.Student;
import com.examallocation.exam_allocation_db.service.AdminService;
import com.examallocation.exam_allocation_db.service.StudentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private StudentService studentService;

    @GetMapping("/adminlogin")
    public String adminLoginPage() {
        return "adminlogin";
    }

    @PostMapping("/adminLogin")
    public String adminLogin(
            @RequestParam String username,
            @RequestParam String password,
            Model model) {

        Admin admin = adminService.login(username, password);
        if (admin != null) {
            return "admindashboard";
        }
        model.addAttribute("error", "Invalid username or password!");
        return "index";
    }

    @GetMapping("/studentLogin")
    public String studentLoginPage() {
        return "studentlogin";
    }

    @PostMapping("/studentLogin")
    public String studentLogin(
            @RequestParam(required = false) String hallTicket,
            @RequestParam(required = false) String password,
            Model model,
            HttpSession session) {

        if (hallTicket == null || hallTicket.isEmpty() ||
                password == null || password.isEmpty()) {
            model.addAttribute("error", "Please fill all fields!");
            return "index";
        }

        Student student = studentService
                .findByHallTicketAndPassword(hallTicket, password);

        if (student != null) {
            session.setAttribute("loggedStudent", student);
            return "redirect:/studentdashboard";
        }

        model.addAttribute("error", "Invalid Hall Ticket or Password!");
        return "index";
    }
}