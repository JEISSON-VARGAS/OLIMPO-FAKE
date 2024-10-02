package com.api.OlimpoFake.Repository;

import com.api.OlimpoFake.Entity.QuarterNameEntity;
import com.api.OlimpoFake.Entity.QuartersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuartersRepository extends JpaRepository<QuartersEntity , Long> {
    QuartersEntity findByName(QuarterNameEntity name);
}
