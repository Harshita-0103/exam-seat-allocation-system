package com.examallocation.exam_allocation_db.controller;

import com.examallocation.exam_allocation_db.entity.Hall;
import com.examallocation.exam_allocation_db.service.HallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HallController {

    @Autowired
    private HallService hallService;

    @GetMapping("/halls")
    public String getHalls(Model model) {
        model.addAttribute("halls", hallService.getAllHalls());
        return "halls";
    }

    @PostMapping("/halls/add")
    public String addHall(Hall hall, Model model) {
        String result = hallService.saveHall(hall);
        if (result.equals("duplicate")) {
            model.addAttribute("halls", hallService.getAllHalls());
            model.addAttribute("error", "Hall Name " + hall.getHallName() + " already exists!");
            return "halls";
        }
        return "redirect:/halls";
    }

    @PostMapping("/halls/update")
    public String updateHall(Hall hall, Model model) {
        String result = hallService.updateHall(hall.getId(), hall);
        if (result.equals("duplicate")) {
            model.addAttribute("halls", hallService.getAllHalls());
            model.addAttribute("editHall", hallService.getHallById(hall.getId()));
            model.addAttribute("error", "Hall Name " + hall.getHallName() + " already exists!");
            return "halls";
        }
        return "redirect:/halls";
    }

    @GetMapping("/halls/delete/{id}")
    public String deleteHall(@PathVariable int id) {
        hallService.deleteHall(id);
        return "redirect:/halls";
    }

    @GetMapping("/halls/edit/{id}")
    public String editHall(@PathVariable int id, Model model) {
        model.addAttribute("editHall", hallService.getHallById(id));
        model.addAttribute("halls", hallService.getAllHalls());
        return "halls";
    }
}