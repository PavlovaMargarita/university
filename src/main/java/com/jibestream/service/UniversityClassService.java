package com.jibestream.service;

import com.jibestream.dto.ClassStudentGradeDto;
import com.jibestream.dto.UniversityClassDto;

public interface UniversityClassService {

    void createClass(UniversityClassDto universityClassDto);

    void updateClass(Long classId, UniversityClassDto universityClassDto);

    void deleteClass(Long classId);

    void assignStudentToClass(Long classId, ClassStudentGradeDto classStudentGradeDto);

    void unassignStudentFromClass(Long classId, Long studentId);
}
