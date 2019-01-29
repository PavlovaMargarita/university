package com.jibestream.entity;

import java.io.Serializable;

public class ClassStudentGradeEntityIdentifier implements Serializable {

    private Long universityClassEntity;

    private Long studentEntity;

    public Long getUniversityClassEntity() {
        return universityClassEntity;
    }

    public void setUniversityClassEntity(Long universityClassEntity) {
        this.universityClassEntity = universityClassEntity;
    }

    public Long getStudentEntity() {
        return studentEntity;
    }

    public void setStudentEntity(Long studentEntity) {
        this.studentEntity = studentEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        ClassStudentGradeEntityIdentifier that = (ClassStudentGradeEntityIdentifier) o;

        if (universityClassEntity != null ?
                !universityClassEntity.equals(that.universityClassEntity) :
                that.universityClassEntity != null)
            return false;
        return studentEntity != null ? studentEntity.equals(that.studentEntity) : that.studentEntity == null;
    }

    @Override
    public int hashCode() {
        int result = universityClassEntity != null ? universityClassEntity.hashCode() : 0;
        result = 31 * result + (studentEntity != null ? studentEntity.hashCode() : 0);
        return result;
    }
}
