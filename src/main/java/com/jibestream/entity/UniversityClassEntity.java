package com.jibestream.entity;

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
@Table(name = "university_class")
public class UniversityClassEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "universityClassEntity", cascade = { CascadeType.ALL}, orphanRemoval=true)
    private List<ClassStudentGradeEntity> classStudentGradeEntityList;

    private UniversityClassEntity(Builder builder) {
        id = builder.id;
        name = builder.name;
        classStudentGradeEntityList = builder.classStudentGradeEntityList;
    }

    public UniversityClassEntity() {

    }
    public static Builder aUniversityClassEntity() {
        return new Builder();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
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
        private String name;
        private List<ClassStudentGradeEntity> classStudentGradeEntityList;

        private Builder() {
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withClassGradeEntityList(List<ClassStudentGradeEntity> classStudentGradeEntityList) {
            this.classStudentGradeEntityList = classStudentGradeEntityList;
            return this;
        }

        public UniversityClassEntity build() {
            return new UniversityClassEntity(this);
        }
    }
}
