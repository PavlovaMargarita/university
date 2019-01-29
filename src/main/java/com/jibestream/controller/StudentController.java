package com.jibestream.controller;

import com.jibestream.dto.StudentClassGradeDto;
import com.jibestream.dto.StudentDto;
import com.jibestream.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
public class StudentController {

    private StudentService studentService;

    @Autowired
    public StudentController(final StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(value = "/student/{id}/classes")
    public List<StudentClassGradeDto> getStudent(@PathVariable @NotNull Long id) {
        return studentService.getStudentClasses(id);
    }

    @PostMapping(value = "/student")
    public void createStudent(@RequestBody @Valid StudentDto studentDto) {
        studentService.createStudent(studentDto);
    }

    @PutMapping(value = "/student/{id}")
    public void updateStudent(@PathVariable @NotNull Long id, @RequestBody @Valid StudentDto studentDto) {
        studentService.updateStudent(id, studentDto);
    }

    @DeleteMapping(value = "/student/{id}")
    public void deleteStudent(@PathVariable @NotNull Long id) {
        studentService.deleteStudent(id);
    }

}
