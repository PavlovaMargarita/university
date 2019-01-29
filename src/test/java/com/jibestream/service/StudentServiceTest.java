package com.jibestream.service;

import com.jibestream.dto.StudentDto;
import com.jibestream.dto.UniversityClassDto;
import com.jibestream.entity.StudentEntity;
import com.jibestream.entity.UniversityClassEntity;
import com.jibestream.repository.StudentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
public class StudentServiceTest {
    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentServiceImpl studentService;

    @Test
    public void shouldCreateUniversityClass() {
        StudentDto studentDto = StudentDto.newBuilder()
                .withFirstName("John")
                .withLastName("Smith")
                .build();

        studentService.createStudent(studentDto);

        StudentEntity expectedStudentEntity = StudentEntity.newBuilder()
                .withFirstName("John")
                .withLastName("Smith")
                .build();
        verify(studentRepository).save(expectedStudentEntity);
    }

    @Test
    public void shouldUpdateUniversityClass() {
        StudentDto studentDto = StudentDto.newBuilder()
                .withFirstName("John")
                .withLastName("Smith")
                .build();
        Long studentId = 123L;

        studentService.updateStudent(studentId, studentDto);

        StudentEntity expectedStudentEntity = StudentEntity.newBuilder()
                .withId(studentId)
                .withFirstName("John")
                .withLastName("Smith")
                .build();
        verify(studentRepository).save(expectedStudentEntity);
    }

    @Test
    public void shouldDeleteUniversityClass() {
        Long studentId = 3L;

        studentService.deleteStudent(studentId);

        verify(studentRepository).delete(studentId);
    }

}
