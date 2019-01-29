package com.jibestream.service;

import com.jibestream.dto.UniversityClassDto;
import com.jibestream.entity.UniversityClassEntity;
import com.jibestream.repository.UniversityClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UniversityClassServiceImpl implements UniversityClassService {

    private UniversityClassRepository universityClassRepository;

    @Autowired
    public UniversityClassServiceImpl(UniversityClassRepository UniversityClassRepository) {
        this.universityClassRepository = UniversityClassRepository;
    }

    public void createClass(UniversityClassDto universityClassDto) {
        final UniversityClassEntity universityClassEntity = UniversityClassEntity.aClassEntity()
                .withName(universityClassDto.getName())
                .build();

        universityClassRepository.save(universityClassEntity);
    }

    public void updateClass(Long id,UniversityClassDto universityClassDto) {
        final UniversityClassEntity universityClassEntity = UniversityClassEntity.aClassEntity()
                .withId(id)
                .withName(universityClassDto.getName())
                .build();

        universityClassRepository.save(universityClassEntity);
    }

    public void deleteClass(Long id) {
        universityClassRepository.delete(id);
    }
}
