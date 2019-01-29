package com.jibestream.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;
import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;

@JsonSerialize
public class StudentClassGradeDto {
    private String className;
    private int grade;

    private StudentClassGradeDto(Builder builder) {
        className = builder.className;
        grade = builder.grade;
    }

    public static Builder aStudentClassGradeDto() {
        return new Builder();
    }

    public String getClassName() {
        return className;
    }

    public int getGrade() {
        return grade;
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
        private String className;
        private int grade;

        private Builder() {
        }

        public Builder withClassName(String className) {
            this.className = className;
            return this;
        }

        public Builder withGrade(int grade) {
            this.grade = grade;
            return this;
        }

        public StudentClassGradeDto build() {
            return new StudentClassGradeDto(this);
        }
    }
}
