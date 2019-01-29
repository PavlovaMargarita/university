package com.jibestream.repository;

import com.jibestream.entity.UniversityClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UniversityClassRepository extends JpaRepository<UniversityClassEntity, Long> {
}
