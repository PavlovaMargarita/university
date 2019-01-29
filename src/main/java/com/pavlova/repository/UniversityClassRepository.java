package com.pavlova.repository;

import com.pavlova.entity.UniversityClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UniversityClassRepository extends JpaRepository<UniversityClassEntity, Long> {
}
