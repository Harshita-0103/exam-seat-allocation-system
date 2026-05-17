package com.examallocation.exam_allocation_db.service;

import com.examallocation.exam_allocation_db.entity.Hall;
import com.examallocation.exam_allocation_db.repository.HallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class HallService {

    @Autowired
    private HallRepository hallRepository;

    public List<Hall> getAllHalls() {
        return hallRepository.findAll();
    }

    public String saveHall(Hall hall) {
        if (hallRepository.existsByHallName(hall.getHallName())) {
            return "duplicate";
        }
        hallRepository.save(hall);
        return "success";
    }

    public String updateHall(int id, Hall hall) {
        if (hallRepository.existsByHallNameAndIdNot(hall.getHallName(), id)) {
            return "duplicate";
        }
        hall.setId(id);
        hallRepository.save(hall);
        return "success";
    }

    public void deleteHall(int id) {
        hallRepository.deleteById(id);
    }

    public Hall getHallById(int id) {
        return hallRepository.findById(id).orElse(null);
    }
}