package com.examallocation.exam_allocation_db.service;

import com.examallocation.exam_allocation_db.entity.Faculty;
import com.examallocation.exam_allocation_db.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FacultyService {

    @Autowired
    private FacultyRepository facultyRepository;

    public List<Faculty> getAllFaculty() {
        return facultyRepository.findAll();
    }

    public String saveFaculty(Faculty faculty) {
        if (facultyRepository.existsByFacultyName(faculty.getFacultyName())) {
            return "duplicate";
        }
        facultyRepository.save(faculty);
        return "success";
    }

    public String updateFaculty(int id, Faculty faculty) {
        if (facultyRepository.existsByFacultyNameAndIdNot(faculty.getFacultyName(), id)) {
            return "duplicate";
        }
        faculty.setId(id);
        facultyRepository.save(faculty);
        return "success";
    }

    public void deleteFaculty(int id) {
        facultyRepository.deleteById(id);
    }

    public Faculty getFacultyById(int id) {
        return facultyRepository.findById(id).orElse(null);
    }
}