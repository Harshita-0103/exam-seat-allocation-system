package com.examallocation.exam_allocation_db.service;

import com.examallocation.exam_allocation_db.entity.Faculty;
import com.examallocation.exam_allocation_db.entity.Hall;
import com.examallocation.exam_allocation_db.entity.InvigilatorAllocation;

import com.examallocation.exam_allocation_db.repository.FacultyRepository;
import com.examallocation.exam_allocation_db.repository.HallRepository;
import com.examallocation.exam_allocation_db.repository.InvigilatorAllocationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class InvigilatorAllocationService {

    @Autowired
    private InvigilatorAllocationRepository
            invigilatorAllocationRepository;

    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    private HallRepository hallRepository;

    // VIEW ALL
    public List<InvigilatorAllocation>
    getAllInvigilators() {

        return invigilatorAllocationRepository
                .findAll();
    }

    // SAVE
    public String saveInvigilator(
            InvigilatorAllocation invigilator) {

        if (invigilatorAllocationRepository
                .existsByFacultyIdAndDutyDate(
                        invigilator.getFacultyId(),
                        invigilator.getDutyDate())) {

            return "duplicate";
        }

        invigilatorAllocationRepository
                .save(invigilator);

        return "success";
    }

    // UPDATE
    public String updateInvigilator(
            int id,
            InvigilatorAllocation invigilator) {

        if (invigilatorAllocationRepository
                .existsByFacultyIdAndDutyDateAndIdNot(
                        invigilator.getFacultyId(),
                        invigilator.getDutyDate(),
                        id)) {

            return "duplicate";
        }

        invigilator.setId(id);

        invigilatorAllocationRepository
                .save(invigilator);

        return "success";
    }

    // DELETE
    public void deleteInvigilator(int id) {

        invigilatorAllocationRepository
                .deleteById(id);
    }

    // GET BY ID
    public InvigilatorAllocation
    getInvigilatorById(int id) {

        return invigilatorAllocationRepository
                .findById(id)
                .orElse(null);
    }

    // AUTOMATIC INVIGILATOR ALLOCATION
    public void generateAutomaticInvigilators() {

        // DELETE OLD DATA
        invigilatorAllocationRepository
                .deleteAll();

        // FETCH FACULTY
        List<Faculty> facultyList =
                facultyRepository.findAll();

        // FETCH HALLS
        List<Hall> hallList =
                hallRepository.findAll();

        // CHECK EMPTY
        if (facultyList.isEmpty()
                || hallList.isEmpty()) {

            return;
        }

        int facultyIndex = 0;

        // LOOP HALLS
        for (Hall hall : hallList) {

            Faculty faculty =
                    facultyList.get(facultyIndex);

            // CREATE OBJECT
            InvigilatorAllocation allocation =
                    new InvigilatorAllocation();

            allocation.setFacultyId(
                    faculty.getId());

            allocation.setFacultyName(
                    faculty.getFacultyName());

            allocation.setHallId(
                    hall.getId());

            allocation.setDutyDate(
                    LocalDate.now().toString());

            // SAVE
            invigilatorAllocationRepository
                    .save(allocation);

            // NEXT FACULTY
            facultyIndex++;

            // RESTART FACULTY LIST
            if (facultyIndex >= facultyList.size()) {

                facultyIndex = 0;
            }
        }
    }
}