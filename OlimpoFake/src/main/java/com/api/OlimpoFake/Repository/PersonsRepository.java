package com.api.OlimpoFake.Repository;

import com.api.OlimpoFake.Entity.PersonsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonsRepository extends JpaRepository<PersonsEntity , Long> {
    PersonsEntity findByDocument(String document);
}
