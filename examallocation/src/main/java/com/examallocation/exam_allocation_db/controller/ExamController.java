package com.examallocation.exam_allocation_db.controller;

import com.examallocation.exam_allocation_db.entity.Exam;
import com.examallocation.exam_allocation_db.entity.Student;
import com.examallocation.exam_allocation_db.service.ExamService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ExamController {

    @Autowired
    private ExamService examService;

    @GetMapping("/exams")
    public String getExams(Model model) {
        model.addAttribute("exams", examService.getAllExams());
        return "exams";
    }

    @PostMapping("/exams/add")
    public String addExam(Exam exam, Model model) {
        String result = examService.saveExam(exam);
        if (result.equals("duplicate")) {
            model.addAttribute("exams", examService.getAllExams());
            model.addAttribute("error", "Subject " + exam.getSubjectName() + " already exists!");
            return "exams";
        }
        return "redirect:/exams";
    }

    @PostMapping("/exams/update")
    public String updateExam(Exam exam, Model model) {
        String result = examService.updateExam(exam.getId(), exam);
        if (result.equals("duplicate")) {
            model.addAttribute("exams", examService.getAllExams());
            model.addAttribute("editExam", examService.getExamById(exam.getId()));
            model.addAttribute("error", "Subject " + exam.getSubjectName() + " already exists!");
            return "exams";
        }
        return "redirect:/exams";
    }

    @GetMapping("/exams/delete/{id}")
    public String deleteExam(@PathVariable int id) {
        examService.deleteExam(id);
        return "redirect:/exams";
    }

    @GetMapping("/exams/edit/{id}")
    public String editExam(@PathVariable int id, Model model) {
        model.addAttribute("editExam", examService.getExamById(id));
        model.addAttribute("exams", examService.getAllExams());
        return "exams";
    }

    @GetMapping("/student/exams")
    public String studentExams(
            HttpSession session,
            Model model) {

        Student loggedStudent =
                (Student) session.getAttribute("loggedStudent");

        if (loggedStudent == null) {
            return "redirect:/";
        }

        model.addAttribute("exams", examService.getAllExams());
        return "studentexams";
    }
}