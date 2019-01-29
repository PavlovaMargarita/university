package com.jibestream.entity;

import com.sun.istack.internal.NotNull;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;
import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;

@Entity
@Table(name = "student")
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    @NotNull
    private String firstName;

    @Column(name = "last_name")
    @NotNull
    private String lastName;

    @OneToMany(mappedBy = "studentEntity", cascade = { CascadeType.ALL}, orphanRemoval=true)
    private List<ClassStudentGradeEntity> classStudentGradeEntityList;

    public StudentEntity() {

    }

    private StudentEntity(Builder builder) {
        id = builder.id;
        firstName = builder.firstName;
        lastName = builder.lastName;
        classStudentGradeEntityList = builder.classStudentGradeEntityList;
    }

    public static Builder aStudentEntity() {
        return new Builder();
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<ClassStudentGradeEntity> getClassStudentGradeEntityList() {
        return classStudentGradeEntityList;
    }

    @Override
    public int hashCode() {
        return reflectionHashCode(this);
    }

    @Override
    public boolean equals(final Object obj) {
        return reflectionEquals(this, obj);
    }

    @Override
    public String toString() {
        return reflectionToString(this);
    }

    public static final class Builder {
        private Long id;
        private String firstName;
        private String lastName;
        private List<ClassStudentGradeEntity> classStudentGradeEntityList;

        private Builder() {
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder withClassGradeEntityList(List<ClassStudentGradeEntity> classStudentGradeEntityList) {
            this.classStudentGradeEntityList = classStudentGradeEntityList;
            return this;
        }

        public StudentEntity build() {
            return new StudentEntity(this);
        }
    }
}
