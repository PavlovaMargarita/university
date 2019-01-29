package com.jibestream.controller;

import com.jibestream.dto.ClassStudentGradeDto;
import com.jibestream.dto.UniversityClassDto;
import com.jibestream.service.UniversityClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
public class UniversityClassController {

    private UniversityClassService universityClassService;

    @Autowired
    public UniversityClassController(final UniversityClassService universityClassService) {
        this.universityClassService = universityClassService;
    }

    @PostMapping(value = "/class")
    public void createClass(@RequestBody @Valid UniversityClassDto universityClassDto) {
        universityClassService.createClass(universityClassDto);
    }

    @PutMapping(value = "/class/{id}")
    public void updateClass(@PathVariable Long id, @RequestBody @Valid UniversityClassDto universityClassDto) {
        universityClassService.updateClass(id, universityClassDto);
    }

    @DeleteMapping(value = "/class/{id}")
    public void deleteClass(@PathVariable @NotNull Long id) {
        universityClassService.deleteClass(id);
    }

    @PostMapping(value = "/class/{id}/assignstudent")
    public void assignStudentToClass(@PathVariable Long id, @RequestBody @Valid ClassStudentGradeDto classStudentGradeDto) {
        universityClassService.assignStudentToClass(id, classStudentGradeDto);
    }

    @DeleteMapping(value = "/class/{id}/unassignstudent/{studentId}")
    public void unassignStudentToClass(@PathVariable Long id, @PathVariable @Valid Long studentId) {
        universityClassService.unassignStudentFromClass(id, studentId);
    }

}
