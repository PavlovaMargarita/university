package com.pavlova.service;

import com.pavlova.dto.StudentClassGradeDto;
import com.pavlova.dto.StudentDto;

import java.util.List;

public interface StudentService {

    List<StudentClassGradeDto> getStudentClasses(Long studentId);

    void createStudent(StudentDto studentDto);

    void updateStudent(Long id, StudentDto studentDto);

    void deleteStudent(Long id);
}
