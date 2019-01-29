package com.jibestream.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;
import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;

@JsonDeserialize(builder = StudentDto.Builder.class)
public class StudentDto {
    private String firstName;
    private String lastName;

    private StudentDto(Builder builder) {
        firstName = builder.firstName;
        lastName = builder.lastName;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
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
        private String firstName;
        private String lastName;

        private Builder() {
        }

        public Builder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public StudentDto build() {
            return new StudentDto(this);
        }
    }
}
