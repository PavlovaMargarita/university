package com.jibestream.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;
import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;

@JsonDeserialize(builder = UniversityClassDto.Builder.class)
public class UniversityClassDto {
    private String name;

    private UniversityClassDto(Builder builder) {
        name = builder.name;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getName() {
        return name;
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
        private String name;

        private Builder() {
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public UniversityClassDto build() {
            return new UniversityClassDto(this);
        }
    }
}
