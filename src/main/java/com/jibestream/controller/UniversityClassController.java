package com.jibestream.controller;

import com.jibestream.dto.StudentGradeDto;
import com.jibestream.dto.UniversityClassDto;
import com.jibestream.service.UniversityClassService;
import com.sun.istack.internal.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UniversityClassController {

    private UniversityClassService universityClassService;

    @Autowired
    public UniversityClassController(final UniversityClassService universityClassService) {
        this.universityClassService = universityClassService;
    }

    @PostMapping(value = "/class")
    public void createClass(@RequestBody UniversityClassDto universityClassDto) {
        universityClassService.createClass(universityClassDto);
    }

    @PutMapping(value = "/class/{id}")
    public void updateClass(@PathVariable Long id, @RequestBody UniversityClassDto universityClassDto) {
        universityClassService.updateClass(id, universityClassDto);
    }

    @DeleteMapping(value = "/class/{id}")
    public void deleteClass(@PathVariable @NotNull Long id) {
        universityClassService.deleteClass(id);
    }

}
