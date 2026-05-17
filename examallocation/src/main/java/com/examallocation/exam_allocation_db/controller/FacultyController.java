package com.examallocation.exam_allocation_db.controller;

import com.examallocation.exam_allocation_db.entity.Faculty;
import com.examallocation.exam_allocation_db.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class FacultyController {

    @Autowired
    private FacultyService facultyService;

    @GetMapping("/faculty")
    public String getFaculty(Model model) {
        model.addAttribute("faculty", facultyService.getAllFaculty());
        return "faculty";
    }

    @PostMapping("/faculty/add")
    public String addFaculty(Faculty faculty, Model model) {
        String result = facultyService.saveFaculty(faculty);
        if (result.equals("duplicate")) {
            model.addAttribute("faculty", facultyService.getAllFaculty());
            model.addAttribute("error", "Faculty Name " + faculty.getFacultyName() + " already exists!");
            return "faculty";
        }
        return "redirect:/faculty";
    }

    @PostMapping("/faculty/update")
    public String updateFaculty(Faculty faculty, Model model) {
        String result = facultyService.updateFaculty(faculty.getId(), faculty);
        if (result.equals("duplicate")) {
            model.addAttribute("faculty", facultyService.getAllFaculty());
            model.addAttribute("editFaculty", facultyService.getFacultyById(faculty.getId()));
            model.addAttribute("error", "Faculty Name " + faculty.getFacultyName() + " already exists!");
            return "faculty";
        }
        return "redirect:/faculty";
    }

    @GetMapping("/faculty/delete/{id}")
    public String deleteFaculty(@PathVariable int id) {
        facultyService.deleteFaculty(id);
        return "redirect:/faculty";
    }

    @GetMapping("/faculty/edit/{id}")
    public String editFaculty(@PathVariable int id, Model model) {
        model.addAttribute("editFaculty", facultyService.getFacultyById(id));
        model.addAttribute("faculty", facultyService.getAllFaculty());
        return "faculty";
    }
}