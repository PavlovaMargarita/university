package com.jibestream.controller;

import com.jibestream.dto.StudentGradeDto;
import com.jibestream.dto.StudentDto;
import com.jibestream.service.StudentService;
import com.sun.istack.internal.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
public class StudentController {

    private StudentService studentService;

    @Autowired
    public StudentController(final StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(value = "/student/{id}")
    public void getStudent(@PathVariable @NotNull Long id) {
        studentService.getStudent(id);
    }

    @PostMapping(value = "/student")
    public void createStudent(@RequestBody StudentDto studentDto) {
        studentService.createStudent(studentDto);
    }

    @PutMapping(value = "/student/{id}")
    public void updateStudent(@PathVariable @NotNull Long id, @RequestBody StudentDto studentDto) {
        studentService.updateStudent(id, studentDto);
    }

    @DeleteMapping(value = "/student/{id}")
    public void deleteStudent(@PathVariable @NotNull Long id) {
        studentService.deleteStudent(id);
    }

    @PostMapping(value = "/student/assignclass")
    public void assignStudentToClass(@RequestBody StudentGradeDto studentGradeDto) {
        studentService.assignStudentToClass(studentGradeDto);
    }

    @PostMapping(value = "/student/unassignclass")
    public void unassignStudentToClass(@RequestBody StudentGradeDto studentGradeDto) {
        studentService.unassignStudentFromClass(studentGradeDto);
    }

}
