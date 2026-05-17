package com.examallocation.exam_allocation_db.controller;

import com.examallocation.exam_allocation_db.entity.InvigilatorAllocation;
import com.examallocation.exam_allocation_db.service.InvigilatorAllocationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class InvigilatorAllocationController {

    @Autowired
    private InvigilatorAllocationService
            invigilatorAllocationService;

    // VIEW ALL
    @GetMapping("/invigilatorallocation")
    public String getInvigilators(Model model) {

        model.addAttribute(
                "invigilators",
                invigilatorAllocationService
                        .getAllInvigilators());

        return "invigilatorallocation";
    }

    // ADD
    @PostMapping("/invigilatorallocation/add")
    public String addInvigilator(
            InvigilatorAllocation invigilator,
            Model model) {

        String result =
                invigilatorAllocationService
                        .saveInvigilator(invigilator);

        // DUPLICATE CHECK
        if (result.equals("duplicate")) {

            model.addAttribute(
                    "invigilators",
                    invigilatorAllocationService
                            .getAllInvigilators());

            model.addAttribute(
                    "error",
                    "Faculty ID "
                            + invigilator.getFacultyId()
                            + " already allocated on "
                            + invigilator.getDutyDate()
                            + "!");

            return "invigilatorallocation";
        }

        return "redirect:/invigilatorallocation";
    }

    // UPDATE
    @PostMapping("/invigilatorallocation/update")
    public String updateInvigilator(
            InvigilatorAllocation invigilator,
            Model model) {

        String result =
                invigilatorAllocationService
                        .updateInvigilator(
                                invigilator.getId(),
                                invigilator);

        // DUPLICATE CHECK
        if (result.equals("duplicate")) {

            model.addAttribute(
                    "invigilators",
                    invigilatorAllocationService
                            .getAllInvigilators());

            model.addAttribute(
                    "editInvigilator",
                    invigilatorAllocationService
                            .getInvigilatorById(
                                    invigilator.getId()));

            model.addAttribute(
                    "error",
                    "Faculty ID "
                            + invigilator.getFacultyId()
                            + " already allocated on "
                            + invigilator.getDutyDate()
                            + "!");

            return "invigilatorallocation";
        }

        return "redirect:/invigilatorallocation";
    }

    // DELETE
    @GetMapping("/invigilatorallocation/delete/{id}")
    public String deleteInvigilator(
            @PathVariable int id) {

        invigilatorAllocationService
                .deleteInvigilator(id);

        return "redirect:/invigilatorallocation";
    }

    // EDIT
    @GetMapping("/invigilatorallocation/edit/{id}")
    public String editInvigilator(
            @PathVariable int id,
            Model model) {

        model.addAttribute(
                "editInvigilator",
                invigilatorAllocationService
                        .getInvigilatorById(id));

        model.addAttribute(
                "invigilators",
                invigilatorAllocationService
                        .getAllInvigilators());

        return "invigilatorallocation";
    }

    // AUTOMATIC INVIGILATOR ALLOCATION
    @GetMapping("/invigilatorallocation/generate")
    public String generateAutomaticInvigilators() {

        invigilatorAllocationService
                .generateAutomaticInvigilators();

        return "redirect:/invigilatorallocation";
    }
}