package com.jibestream.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import javax.validation.constraints.NotNull;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;
import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;

@JsonDeserialize(builder = ClassStudentGradeDto.Builder.class)
public class ClassStudentGradeDto {
    @NotNull
    private long studentId;

    @NotNull
    private int grade;

    private ClassStudentGradeDto(Builder builder) {
        studentId = builder.studentId;
        grade = builder.grade;
    }

    public static Builder aClassStudentGradeDto() {
        return new Builder();
    }

    public long getStudentId() {
        return studentId;
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

    @JsonPOJOBuilder
    public static final class Builder {
        private long studentId;
        private int grade;

        private Builder() {
        }

        public Builder withStudentId(long studentId) {
            this.studentId = studentId;
            return this;
        }

        public Builder withGrade(int grade) {
            this.grade = grade;
            return this;
        }

        public ClassStudentGradeDto build() {
            return new ClassStudentGradeDto(this);
        }
    }
}
