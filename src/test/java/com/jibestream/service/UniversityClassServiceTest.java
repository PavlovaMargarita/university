package com.jibestream.service;

import com.jibestream.dto.UniversityClassDto;
import com.jibestream.entity.UniversityClassEntity;
import com.jibestream.repository.UniversityClassRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
public class UniversityClassServiceTest {
    @Mock
    private UniversityClassRepository universityClassRepository;

    @InjectMocks
    private UniversityClassServiceImpl universityClassService;

    @Test
    public void shouldCreateUniversityClass() {
        UniversityClassDto universityClassDto = UniversityClassDto.newBuilder()
                .withName("Computer Science")
                .build();

        universityClassService.createClass(universityClassDto);

        UniversityClassEntity expectedUniversityClassEntity = UniversityClassEntity.aClassEntity()
                .withName("Computer Science").build();
        verify(universityClassRepository).save(expectedUniversityClassEntity);
    }

    @Test
    public void shouldUpdateUniversityClass() {
        UniversityClassDto universityClassDto = UniversityClassDto.newBuilder()
                .withName("Computer Science")
                .build();
        Long classId = 3L;

        universityClassService.updateClass(classId, universityClassDto);

        UniversityClassEntity expectedUniversityClassEntity = UniversityClassEntity.aClassEntity()
                .withId(classId)
                .withName("Computer Science").build();
        verify(universityClassRepository).save(expectedUniversityClassEntity);
    }

    @Test
    public void shouldDeleteUniversityClass() {
        Long classId = 3L;

        universityClassService.deleteClass(classId);

        verify(universityClassRepository).delete(classId);
    }

}
