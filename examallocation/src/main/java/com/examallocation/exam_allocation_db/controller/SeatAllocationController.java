package com.examallocation.exam_allocation_db.controller;

import com.examallocation.exam_allocation_db.entity.SeatAllocation;
import com.examallocation.exam_allocation_db.entity.Student;
import com.examallocation.exam_allocation_db.service.SeatAllocationService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class SeatAllocationController {

    @Autowired
    private SeatAllocationService seatAllocationService;

    // ADMIN - VIEW ALL
    @GetMapping("/seat-allocation")
    public String getSeatAllocations(Model model) {
        model.addAttribute("allocations", seatAllocationService.getAllSeatAllocations());
        return "seatallocation";
    }

    // STUDENT - VIEW OWN SEAT
    @GetMapping("/student/seat-allocation")
    public String studentSeatAllocation(
            HttpSession session,
            Model model) {

        Student loggedStudent =
                (Student) session.getAttribute("loggedStudent");

        if (loggedStudent == null) {
            return "redirect:/";
        }

        List<SeatAllocation> allocations =
                seatAllocationService.getAllSeatAllocations()
                        .stream()
                        .filter(a -> a.getStudentName()
                                .equals(loggedStudent.getName()))
                        .collect(Collectors.toList());

        model.addAttribute("allocations", allocations);
        model.addAttribute("student", loggedStudent);
        return "studentseatallocation";
    }

    // ADD
    @PostMapping("/seat-allocation/add")
    public String addSeatAllocation(SeatAllocation allocation, Model model) {
        String result = seatAllocationService.saveSeatAllocation(allocation);
        if (result.equals("duplicate")) {
            model.addAttribute("allocations", seatAllocationService.getAllSeatAllocations());
            model.addAttribute("error", "Seat " + allocation.getSeatNumber() + " in " + allocation.getHallName() + " already taken!");
            return "seatallocation";
        }
        return "redirect:/seat-allocation";
    }

    // UPDATE
    @PostMapping("/seat-allocation/update")
    public String updateSeatAllocation(SeatAllocation allocation, Model model) {
        String result = seatAllocationService.updateSeatAllocation(allocation.getId(), allocation);
        if (result.equals("duplicate")) {
            model.addAttribute("allocations", seatAllocationService.getAllSeatAllocations());
            model.addAttribute("editAllocation", seatAllocationService.getSeatAllocationById(allocation.getId()));
            model.addAttribute("error", "Seat " + allocation.getSeatNumber() + " in " + allocation.getHallName() + " already taken!");
            return "seatallocation";
        }
        return "redirect:/seat-allocation";
    }

    // DELETE
    @GetMapping("/seat-allocation/delete/{id}")
    public String deleteSeatAllocation(@PathVariable int id) {
        seatAllocationService.deleteSeatAllocation(id);
        return "redirect:/seat-allocation";
    }

    // EDIT
    @GetMapping("/seat-allocation/edit/{id}")
    public String editSeatAllocation(@PathVariable int id, Model model) {
        model.addAttribute("editAllocation", seatAllocationService.getSeatAllocationById(id));
        model.addAttribute("allocations", seatAllocationService.getAllSeatAllocations());
        return "seatallocation";
    }

    // AUTOMATIC SEAT ALLOCATION
    @GetMapping("/seat-allocation/generate")
    public String generateAutomaticSeatAllocation() {
        seatAllocationService.generateAutomaticSeatAllocation();
        return "redirect:/seat-allocation";
    }
}