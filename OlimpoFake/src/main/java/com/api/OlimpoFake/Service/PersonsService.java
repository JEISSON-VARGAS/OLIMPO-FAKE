package com.api.OlimpoFake.Service;

import com.api.OlimpoFake.Entity.PersonsEntity;
import com.api.OlimpoFake.Repository.PersonsRepository;
import com.api.OlimpoFake.Service.Dao.Idao;
import com.api.OlimpoFake.Utilities.Exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonsService  implements Idao<PersonsEntity , Long> {
    @Autowired
    private  PersonsRepository personsRepository;

    @Override
    public List<PersonsEntity> findAll() {
        return personsRepository.findAll();
    }

    @Override
    public PersonsEntity getById(Long id) {
        Optional<PersonsEntity> persons = personsRepository.findById(id);
        return persons.orElse(null);
    }

    @Override
    public void update(PersonsEntity entity) {
        this.personsRepository.save(entity);
    }

    @Override
    public PersonsEntity save(PersonsEntity entity) {
        return this.personsRepository.save(entity);
    }

    @Override
    public void delete(PersonsEntity entity) {
        this.personsRepository.delete(entity);
    }

    @Override
    public void create(PersonsEntity entity) {
        this.personsRepository.save(entity);
    }

    public PersonsEntity findByDocument(String document) {
        return personsRepository.findByDocument(document);
    }
}
