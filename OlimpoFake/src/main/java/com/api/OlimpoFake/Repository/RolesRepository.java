package com.api.OlimpoFake.Repository;

import com.api.OlimpoFake.Entity.RolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<RolesEntity, Long> {
    RolesEntity findByName(String name);
}
