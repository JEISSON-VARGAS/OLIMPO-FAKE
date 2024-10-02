package com.api.OlimpoFake.Repository;

import com.api.OlimpoFake.Entity.ProgramsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgramRepository extends JpaRepository<ProgramsEntity , Long> {
    ProgramsEntity findByName(String name);
}
