package com.api.OlimpoFake.Repository;

import com.api.OlimpoFake.Entity.PersonsEntity;
import com.api.OlimpoFake.Entity.SheetsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SheetsRepository extends JpaRepository<SheetsEntity , Long> {
    SheetsEntity findByNumber(Integer number);
    @Query("SELECT p FROM PersonsEntity p WHERE p.sheet.IdSheet = :sheetId")
    List<PersonsEntity> findStudentsBySheet(@Param("sheetId") Long sheetId);
}
