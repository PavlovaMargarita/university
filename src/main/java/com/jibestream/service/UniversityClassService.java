package com.jibestream.service;

import com.jibestream.dto.UniversityClassDto;

public interface UniversityClassService {

    void createClass(UniversityClassDto universityClassDto);

    void updateClass(Long id, UniversityClassDto universityClassDto);

    void deleteClass(Long id);
}
