package com.jibestream.service;

import com.jibestream.dto.StudentGradeDto;
import com.jibestream.dto.StudentDto;
import com.jibestream.entity.ClassStudentGradeEntity;
import com.jibestream.entity.StudentEntity;
import com.jibestream.entity.UniversityClassEntity;
import com.jibestream.repository.StudentRepository;
import com.jibestream.repository.UniversityClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;
    private UniversityClassRepository universityClassRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, UniversityClassRepository universityClassRepository) {
        this.studentRepository = studentRepository;
        this.universityClassRepository = universityClassRepository;
    }

    @Override
    public void getStudent(final Long studentId) {
        studentRepository.findOne(studentId);
    }

    @Override
    public void createStudent(final StudentDto studentDto) {
        StudentEntity studentEntity = StudentEntity.newBuilder()
                .withFirstName(studentDto.getFirstName())
                .withLastName(studentDto.getLastName())
                .build();
        studentRepository.save(studentEntity);
    }

    @Override
    public void updateStudent(Long id,StudentDto studentDto) {
        StudentEntity studentEntity = StudentEntity.newBuilder()
                .withId(id)
                .withFirstName(studentDto.getFirstName())
                .withLastName(studentDto.getLastName())
                .build();
        studentRepository.save(studentEntity);
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.delete(id);
    }

    @Override
    public void assignStudentToClass(StudentGradeDto studentGradeDto) {
        UniversityClassEntity universityClassEntity = universityClassRepository.findOne(studentGradeDto.getClassId());
        StudentEntity studentEntity = studentRepository.findOne(studentGradeDto.getStudentId());
        ClassStudentGradeEntity classStudentGradeEntity = ClassStudentGradeEntity.newBuilder()
                .withUniversityClassEntity(universityClassEntity)
                .withStudentEntity(studentEntity)
                .withGrade(studentGradeDto.getGrade())
                .build();
        studentEntity.getClassStudentGradeEntityList().add(classStudentGradeEntity);
        studentRepository.save(studentEntity);
    }

    @Override
    public void unassignStudentFromClass(StudentGradeDto studentGradeDto) {
        StudentEntity studentEntity = studentRepository.findOne(studentGradeDto.getStudentId());
        List<ClassStudentGradeEntity> classes = studentEntity.getClassStudentGradeEntityList();
        for (ClassStudentGradeEntity classEntity : classes) {
            if(classEntity.getUniversityClassEntity().getId().equals(studentGradeDto.getClassId())) {
                classes.remove(classEntity);
                break;
            }
        }
        studentRepository.save(studentEntity);
    }
}
