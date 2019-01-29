package com.jibestream.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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

    private UniversityClassEntity(Builder builder) {
        id = builder.id;
        name = builder.name;
    }

    public UniversityClassEntity() {

    }
    public static Builder aClassEntity() {
        return new Builder();
    }

    public Long getId() {
        return id;
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

    public static final class Builder {
        private Long id;
        private String name;

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

        public UniversityClassEntity build() {
            return new UniversityClassEntity(this);
        }
    }
}
