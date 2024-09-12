package com.api.OlimpoFake.Repository;

import com.api.OlimpoFake.Entity.SheetsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SheetsRepository extends JpaRepository<SheetsEntity , Long> {
    SheetsEntity findByNumber(Integer number);
}
