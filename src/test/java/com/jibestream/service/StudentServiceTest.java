package com.jibestream.service;

import com.jibestream.dto.StudentClassGradeDto;
import com.jibestream.dto.StudentDto;
import com.jibestream.entity.ClassStudentGradeEntity;
import com.jibestream.entity.StudentEntity;
import com.jibestream.entity.UniversityClassEntity;
import com.jibestream.repository.StudentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.jibestream.dto.StudentDto.aStudentDto;
import static com.jibestream.entity.StudentEntity.aStudentEntity;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class StudentServiceTest {
    private final StudentDto TEST_STUDENT = aStudentDto().withFirstName("John").withLastName("Smith").build();
    private final Long TEST_STUDENT_ID = 123L;

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentServiceImpl studentService;

    @Test
    public void shouldCreateUniversityClass() {
        studentService.createStudent(TEST_STUDENT);

        StudentEntity expectedPersistedStudentEntity = aStudentEntity()
                .withFirstName("John")
                .withLastName("Smith")
                .build();
        verify(studentRepository).save(expectedPersistedStudentEntity);
    }

    @Test
    public void shouldUpdateUniversityClass() {
        when(studentRepository.findOne(TEST_STUDENT_ID)).thenReturn(aStudentEntity().build());

        studentService.updateStudent(TEST_STUDENT_ID, TEST_STUDENT);

        StudentEntity expectedPersistedStudentEntity = aStudentEntity()
                .withId(TEST_STUDENT_ID)
                .withFirstName("John")
                .withLastName("Smith")
                .build();
        verify(studentRepository).save(expectedPersistedStudentEntity);
    }

    @Test
    public void shouldDeleteUniversityClass() {
        when(studentRepository.findOne(TEST_STUDENT_ID)).thenReturn(aStudentEntity().build());

        studentService.deleteStudent(TEST_STUDENT_ID);

        verify(studentRepository).delete(TEST_STUDENT_ID);
    }

    @Test
    public void shouldGetStudentClasses() {
        UniversityClassEntity computerScience = UniversityClassEntity.aUniversityClassEntity()
                .withName("Computer Science")
                .build();
        ClassStudentGradeEntity computerScienceGrade = ClassStudentGradeEntity.aClassStudentGradeEntity()
                .withGrade(9)
                .withUniversityClassEntity(computerScience)
                .build();

        UniversityClassEntity philosophy = UniversityClassEntity.aUniversityClassEntity()
                .withName("Philosophy")
                .build();

        ClassStudentGradeEntity philosophyGrade = ClassStudentGradeEntity.aClassStudentGradeEntity()
                .withGrade(5)
                .withUniversityClassEntity(philosophy)
                .build();

        StudentEntity studentEntity = aStudentEntity()
                .withClassGradeEntityList(asList(computerScienceGrade, philosophyGrade))
                .build();
        when(studentRepository.findOne(TEST_STUDENT_ID)).thenReturn(studentEntity);

        List<StudentClassGradeDto> actualStudentClasses = studentService.getStudentClasses(TEST_STUDENT_ID);

        StudentClassGradeDto expectedComputerScienceGrade = StudentClassGradeDto.aStudentClassGradeDto()
                .withClassName("Computer Science")
                .withGrade(9)
                .build();

        StudentClassGradeDto expectedPhilosophyGrade = StudentClassGradeDto.aStudentClassGradeDto()
                .withClassName("Philosophy")
                .withGrade(5)
                .build();

        assertEquals(actualStudentClasses.size(), 2);
        assertTrue(actualStudentClasses.containsAll(asList(expectedComputerScienceGrade, expectedPhilosophyGrade)));
    }

}
