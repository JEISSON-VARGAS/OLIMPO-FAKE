package com.api.OlimpoFake.Repository;

import com.api.OlimpoFake.Entity.LearningOutcomesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LearningOutcomesRepository extends JpaRepository<LearningOutcomesEntity , Long> {
    LearningOutcomesEntity findByName(String name);
}
