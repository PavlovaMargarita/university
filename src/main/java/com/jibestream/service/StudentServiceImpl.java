package com.jibestream.service;

import com.jibestream.dto.StudentClassGradeDto;
import com.jibestream.dto.StudentDto;
import com.jibestream.entity.ClassStudentGradeEntity;
import com.jibestream.entity.StudentEntity;
import com.jibestream.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.jibestream.dto.StudentClassGradeDto.aStudentClassGradeDto;
import static com.jibestream.entity.StudentEntity.aStudentEntity;

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
