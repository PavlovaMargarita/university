package com.jibestream.service;

import com.jibestream.dto.StudentGradeDto;
import com.jibestream.dto.StudentDto;

public interface StudentService {

    void getStudent(Long studentId);

    void createStudent(StudentDto studentDto);

    void updateStudent(Long id, StudentDto studentDto);

    void deleteStudent(Long id);

    void assignStudentToClass(StudentGradeDto studentGradeDto);

    void unassignStudentFromClass(StudentGradeDto studentGradeDto);
}
