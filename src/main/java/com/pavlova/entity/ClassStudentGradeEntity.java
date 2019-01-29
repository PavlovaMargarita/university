package com.pavlova.entity;

import com.sun.istack.internal.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "class_student")
@IdClass(ClassStudentGradeEntityIdentifier.class)
public class ClassStudentGradeEntity {

    @Id
    @ManyToOne
    @JoinColumn(name = "class_id", referencedColumnName = "id")
    private UniversityClassEntity universityClassEntity;

    @Id
    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private StudentEntity studentEntity;

    @Column(name = "grade")
    @NotNull
    private int grade;

    public ClassStudentGradeEntity() {

    }

    private ClassStudentGradeEntity(Builder builder) {
        universityClassEntity = builder.universityClassEntity;
        studentEntity = builder.studentEntity;
        grade = builder.grade;
    }

    public static Builder aClassStudentGradeEntity() {
        return new Builder();
    }

    public UniversityClassEntity getUniversityClassEntity() {
        return universityClassEntity;
    }

    public StudentEntity getStudentEntity() {
        return studentEntity;
    }

    public int getGrade() {
        return grade;
    }

    public static final class Builder {
        private UniversityClassEntity universityClassEntity;
        private StudentEntity studentEntity;
        private int grade;

        private Builder() {
        }

        public Builder withUniversityClassEntity(UniversityClassEntity universityClassEntity) {
            this.universityClassEntity = universityClassEntity;
            return this;
        }

        public Builder withStudentEntity(StudentEntity studentEntity) {
            this.studentEntity = studentEntity;
            return this;
        }

        public Builder withGrade(int grade) {
            this.grade = grade;
            return this;
        }

        public ClassStudentGradeEntity build() {
            return new ClassStudentGradeEntity(this);
        }
    }
}
