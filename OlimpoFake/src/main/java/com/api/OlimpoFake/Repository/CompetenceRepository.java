package com.api.OlimpoFake.Repository;

import com.api.OlimpoFake.Entity.CompetenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetenceRepository extends JpaRepository<CompetenceEntity , Long> {
    CompetenceEntity findByName(String name);
}
