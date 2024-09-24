package com.api.OlimpoFake.Service;

import com.api.OlimpoFake.Entity.CompetenceEntity;
import com.api.OlimpoFake.Repository.CompetenceRepository;
import com.api.OlimpoFake.Service.Dao.Idao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompetenceService implements Idao<CompetenceEntity , Long> {
    @Autowired
    private CompetenceRepository competenceRepository;
    @Override
    public List<CompetenceEntity> findAll() {
        return competenceRepository.findAll();
    }

    @Override
    public CompetenceEntity getById(Long id) {
        Optional<CompetenceEntity> competence = competenceRepository.findById(id);
        return competence.orElse(null);
    }

    @Override
    public void update(CompetenceEntity entity) {
        this.competenceRepository.save(entity);
    }

    @Override
    public CompetenceEntity save(CompetenceEntity entity) {
        return competenceRepository.save(entity);
    }

    @Override
    public void delete(CompetenceEntity entity) {
        this.competenceRepository.delete(entity);
    }

    @Override
    public void create(CompetenceEntity entity) {

    }

    public CompetenceEntity findByName(String name) {
        return competenceRepository.findByName(name);
    }
}
