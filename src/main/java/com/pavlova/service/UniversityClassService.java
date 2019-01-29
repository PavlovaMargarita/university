package com.pavlova.service;

import com.pavlova.dto.ClassStudentGradeDto;
import com.pavlova.dto.UniversityClassDto;

public interface UniversityClassService {

    void createClass(UniversityClassDto universityClassDto);

    void updateClass(Long classId, UniversityClassDto universityClassDto);

    void deleteClass(Long classId);

    void assignStudentToClass(Long classId, ClassStudentGradeDto classStudentGradeDto);

    void unassignStudentFromClass(Long classId, Long studentId);
}
