package com.jibestream.service;

import com.jibestream.dto.ClassStudentGradeDto;
import com.jibestream.dto.UniversityClassDto;
import com.jibestream.entity.ClassStudentGradeEntity;
import com.jibestream.entity.StudentEntity;
import com.jibestream.entity.UniversityClassEntity;
import com.jibestream.repository.StudentRepository;
import com.jibestream.repository.UniversityClassRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static com.jibestream.dto.ClassStudentGradeDto.aClassStudentGradeDto;
import static com.jibestream.dto.UniversityClassDto.aUniversityClassDto;
import static com.jibestream.entity.ClassStudentGradeEntity.aClassStudentGradeEntity;
import static com.jibestream.entity.StudentEntity.aStudentEntity;
import static com.jibestream.entity.UniversityClassEntity.aUniversityClassEntity;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class UniversityClassServiceTest {
    private final Long TEST_UNIVERSITY_CLASS_ID = 3L;
    private final Long TEST_STUDENT_ID = 123L;

    @Mock
    private UniversityClassRepository universityClassRepository;

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private UniversityClassServiceImpl universityClassService;

    @Test
    public void shouldCreateUniversityClass() {
        UniversityClassDto universityClassDto = aUniversityClassDto()
                .withName("Computer Science")
                .build();

        universityClassService.createClass(universityClassDto);

        UniversityClassEntity expectedUniversityClassEntity = aUniversityClassEntity()
                .withName("Computer Science").build();
        verify(universityClassRepository).save(expectedUniversityClassEntity);
    }

    @Test
    public void shouldUpdateUniversityClass() {
        when(universityClassRepository.findOne(TEST_UNIVERSITY_CLASS_ID)).thenReturn(aUniversityClassEntity().build());

        UniversityClassDto universityClassDto = aUniversityClassDto()
                .withName("Computer Science")
                .build();

        universityClassService.updateClass(TEST_UNIVERSITY_CLASS_ID, universityClassDto);

        UniversityClassEntity expectedUniversityClassEntity = aUniversityClassEntity()
                .withId(TEST_UNIVERSITY_CLASS_ID)
                .withName("Computer Science").build();
        verify(universityClassRepository).save(expectedUniversityClassEntity);
    }

    @Test
    public void shouldDeleteUniversityClass() {
        when(universityClassRepository.findOne(TEST_UNIVERSITY_CLASS_ID)).thenReturn(aUniversityClassEntity().build());

        universityClassService.deleteClass(TEST_UNIVERSITY_CLASS_ID);

        verify(universityClassRepository).delete(TEST_UNIVERSITY_CLASS_ID);
    }

    @Test
    public void shouldAssignStudentToClass() {
        ClassStudentGradeDto classStudentGradeDto = aClassStudentGradeDto()
                .withGrade(8)
                .withStudentId(TEST_STUDENT_ID)
                .build();

        StudentEntity expectedStudentEntity = aStudentEntity()
                .withId(TEST_STUDENT_ID)
                .withFirstName("John")
                .withLastName("Smith")
                .build();
        when(studentRepository.findOne(TEST_STUDENT_ID)).thenReturn(expectedStudentEntity);

        UniversityClassEntity classEntity = aUniversityClassEntity()
                .withId(TEST_UNIVERSITY_CLASS_ID)
                .withName("Computer Science")
                .withClassGradeEntityList(new ArrayList<>())
                .build();
        when(universityClassRepository.findOne(TEST_UNIVERSITY_CLASS_ID)).thenReturn(classEntity);

        universityClassService.assignStudentToClass(TEST_UNIVERSITY_CLASS_ID, classStudentGradeDto);

        ClassStudentGradeEntity expectedPersistedClassStudentGradeEntity = aClassStudentGradeEntity()
                .withStudentEntity(expectedStudentEntity)
                .withUniversityClassEntity(classEntity)
                .withGrade(8)
                .build();
        classEntity.getClassStudentGradeEntityList().add(expectedPersistedClassStudentGradeEntity);
        verify(universityClassRepository).save(classEntity);
    }

    @Test
    public void shouldUnassignStudentToClass() {
        StudentEntity expectedStudentEntity = aStudentEntity()
                .withId(TEST_STUDENT_ID)
                .withFirstName("John")
                .withLastName("Smith")
                .build();
        when(studentRepository.findOne(TEST_STUDENT_ID)).thenReturn(expectedStudentEntity);

        UniversityClassEntity classEntity = aUniversityClassEntity()
                .withId(TEST_UNIVERSITY_CLASS_ID)
                .withName("Computer Science")
                .withClassGradeEntityList(new ArrayList<>())
                .build();
        ClassStudentGradeEntity expectedClassStudentGradeEntity = aClassStudentGradeEntity()
                .withStudentEntity(expectedStudentEntity)
                .withUniversityClassEntity(classEntity)
                .withGrade(8)
                .build();
        classEntity.getClassStudentGradeEntityList().add(expectedClassStudentGradeEntity);

        when(universityClassRepository.findOne(TEST_UNIVERSITY_CLASS_ID)).thenReturn(classEntity);

        universityClassService.unassignStudentFromClass(TEST_UNIVERSITY_CLASS_ID, TEST_STUDENT_ID);

        UniversityClassEntity expectedPersistedClassEntity = aUniversityClassEntity()
                .withId(TEST_UNIVERSITY_CLASS_ID)
                .withName("Computer Science")
                .withClassGradeEntityList(new ArrayList<>())
                .build();
        verify(universityClassRepository).save(expectedPersistedClassEntity);
    }

}
