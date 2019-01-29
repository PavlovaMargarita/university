package com.jibestream.service;

import com.jibestream.dto.ClassStudentGradeDto;
import com.jibestream.dto.UniversityClassDto;
import com.jibestream.entity.ClassStudentGradeEntity;
import com.jibestream.entity.StudentEntity;
import com.jibestream.entity.UniversityClassEntity;
import com.jibestream.repository.StudentRepository;
import com.jibestream.repository.UniversityClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.jibestream.entity.UniversityClassEntity.aUniversityClassEntity;

@Service
public class UniversityClassServiceImpl implements UniversityClassService {

    private StudentRepository studentRepository;
    private UniversityClassRepository universityClassRepository;

    @Autowired
    public UniversityClassServiceImpl(StudentRepository studentRepository,
            UniversityClassRepository universityClassRepository) {
        this.studentRepository = studentRepository;
        this.universityClassRepository = universityClassRepository;
    }

    @Override
    public void createClass(UniversityClassDto universityClassDto) {
        final UniversityClassEntity universityClassEntity = aUniversityClassEntity()
                .withName(universityClassDto.getName())
                .build();

        universityClassRepository.save(universityClassEntity);
    }

    @Override
    public void updateClass(Long classId, UniversityClassDto universityClassDto) {
        UniversityClassEntity existingClassEntity = verifyClassExists(classId);
        final UniversityClassEntity universityClassEntity = aUniversityClassEntity()
                .withId(classId)
                .withName(universityClassDto.getName())
                .withClassGradeEntityList(existingClassEntity.getClassStudentGradeEntityList())
                .build();

        universityClassRepository.save(universityClassEntity);
    }

    @Override
    public void deleteClass(Long classId) {
        verifyClassExists(classId);
        universityClassRepository.delete(classId);
    }

    @Override
    public void assignStudentToClass(Long classId, ClassStudentGradeDto classStudentGradeDto) {
        UniversityClassEntity universityClassEntity = verifyClassExists(classId);
        StudentEntity studentEntity = verifyStudentExist(classStudentGradeDto.getStudentId());
        ClassStudentGradeEntity classStudentGradeEntity = ClassStudentGradeEntity.aClassStudentGradeEntity()
                .withUniversityClassEntity(universityClassEntity)
                .withStudentEntity(studentEntity)
                .withGrade(classStudentGradeDto.getGrade())
                .build();
        universityClassEntity.getClassStudentGradeEntityList().add(classStudentGradeEntity);
        universityClassRepository.save(universityClassEntity);
    }

    @Override
    public void unassignStudentFromClass(Long classId, Long studentId) {
        UniversityClassEntity universityClassEntity = verifyClassExists(classId);
        List<ClassStudentGradeEntity> classes = universityClassEntity.getClassStudentGradeEntityList();
        for (ClassStudentGradeEntity classEntity : classes) {
            if(classEntity.getStudentEntity().getId().equals(studentId)) {
                classes.remove(classEntity);
                break;
            }
        }
        universityClassRepository.save(universityClassEntity);
    }

    private StudentEntity verifyStudentExist(Long studentId) {
        StudentEntity studentEntity = studentRepository.findOne(studentId);
        if(studentEntity == null) {
            throw new UnsupportedOperationException("Student not found");
        }
        return studentEntity;
    }

    private UniversityClassEntity verifyClassExists(Long classId) {
        UniversityClassEntity universityClassEntity = universityClassRepository.findOne(classId);
        if(universityClassEntity == null) {
            throw new UnsupportedOperationException("University class not found");
        }
        return universityClassEntity;
    }
}
