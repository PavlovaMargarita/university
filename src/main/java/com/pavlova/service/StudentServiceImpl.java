package com.pavlova.service;

import com.pavlova.dto.StudentClassGradeDto;
import com.pavlova.dto.StudentDto;
import com.pavlova.entity.ClassStudentGradeEntity;
import com.pavlova.entity.StudentEntity;
import com.pavlova.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.pavlova.dto.StudentClassGradeDto.aStudentClassGradeDto;
import static com.pavlova.entity.StudentEntity.aStudentEntity;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<StudentClassGradeDto> getStudentClasses(final Long studentId) {
        StudentEntity studentEntity = verifyStudentExists(studentId);
        List<StudentClassGradeDto> studentClasses = new ArrayList<>();
        for (ClassStudentGradeEntity classStudentGradeEntity : studentEntity.getClassStudentGradeEntityList()) {
            StudentClassGradeDto studentClassGradeDto = aStudentClassGradeDto()
                    .withClassName(classStudentGradeEntity.getUniversityClassEntity().getName())
                    .withGrade(classStudentGradeEntity.getGrade())
                    .build();

            studentClasses.add(studentClassGradeDto);
        }

        return studentClasses;
    }

    @Override
    public void createStudent(final StudentDto studentDto) {
        StudentEntity studentEntity = aStudentEntity()
                .withFirstName(studentDto.getFirstName())
                .withLastName(studentDto.getLastName())
                .build();
        studentRepository.save(studentEntity);
    }

    @Override
    public void updateStudent(Long id, StudentDto studentDto) {
        StudentEntity existingStudentEntity = verifyStudentExists(id);
        StudentEntity studentEntity = aStudentEntity()
                .withId(id)
                .withFirstName(studentDto.getFirstName())
                .withLastName(studentDto.getLastName())
                .withClassGradeEntityList(existingStudentEntity.getClassStudentGradeEntityList())
                .build();
        studentRepository.save(studentEntity);
    }

    @Override
    public void deleteStudent(Long id) {
        verifyStudentExists(id);
        studentRepository.delete(id);
    }

    private StudentEntity verifyStudentExists(Long studentId) {
        StudentEntity studentEntity = studentRepository.findOne(studentId);
        if(studentEntity == null) {
            throw new UnsupportedOperationException("Student not found");
        }
        return studentEntity;
    }

}
