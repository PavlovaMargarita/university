package com.jibestream.service;

import com.jibestream.dto.StudentClassGradeDto;
import com.jibestream.dto.StudentDto;

import java.util.List;

public interface StudentService {

    List<StudentClassGradeDto> getStudentClasses(Long studentId);

    void createStudent(StudentDto studentDto);

    void updateStudent(Long id, StudentDto studentDto);

    void deleteStudent(Long id);
}
