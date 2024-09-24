package com.api.OlimpoFake.Service;

import com.api.OlimpoFake.Entity.PersonsEntity;
import com.api.OlimpoFake.Entity.SheetsEntity;
import com.api.OlimpoFake.Repository.SheetsRepository;
import com.api.OlimpoFake.Service.Dao.Idao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SheetsService implements Idao<SheetsEntity , Long>{
    @Autowired
    private SheetsRepository sheetsRepository;


    @Override
    public List<SheetsEntity> findAll() {
        return sheetsRepository.findAll();
    }

    @Override
    public SheetsEntity getById(Long id) {
        Optional<SheetsEntity> sheets = sheetsRepository.findById(id);
        return sheets.orElse(null);
    }

    @Override
    public void update(SheetsEntity entity) {
        this.sheetsRepository.save(entity);
    }

    @Override
    public SheetsEntity save(SheetsEntity entity) {
        return sheetsRepository.save(entity);
    }

    @Override
    public void delete(SheetsEntity entity) {
        this.sheetsRepository.delete(entity);
    }

    @Override
    public void create(SheetsEntity entity) {
        this.sheetsRepository.save(entity);
    }

    public SheetsEntity findByNumber(Integer number) {
        return sheetsRepository.findByNumber(number);
    }

    public List<PersonsEntity> findStudentsBySheet(Long IdSheet) {
        return sheetsRepository.findStudentsBySheet(IdSheet);
    }
}
