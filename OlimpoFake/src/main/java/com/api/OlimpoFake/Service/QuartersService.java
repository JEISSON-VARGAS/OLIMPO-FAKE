package com.api.OlimpoFake.Service;

import com.api.OlimpoFake.Entity.QuarterNameEntity;
import com.api.OlimpoFake.Entity.QuartersEntity;
import com.api.OlimpoFake.Repository.QuartersRepository;
import com.api.OlimpoFake.Service.Dao.Idao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuartersService implements Idao<QuartersEntity , Long> {

    @Autowired
    private QuartersRepository quartersRepository;

    @Override
    public List<QuartersEntity> findAll() {
        return quartersRepository.findAll();
    }

    @Override
    public QuartersEntity getById(Long id) {
        Optional<QuartersEntity> quarters = quartersRepository.findById(id);
        return quarters.orElse(null);
    }

    @Override
    public void update(QuartersEntity entity) {
        this.quartersRepository.save(entity);
    }

    @Override
    public QuartersEntity save(QuartersEntity entity) {
        return quartersRepository.save(entity);
    }

    @Override
    public void delete(QuartersEntity entity) {
        this.quartersRepository.delete(entity);
    }

    @Override
    public void create(QuartersEntity entity) {
        this.quartersRepository.save(entity);
    }

    public QuartersEntity findByName(QuarterNameEntity name) {
        return quartersRepository.findByName(name);
    }
}
