package com.api.OlimpoFake.Service;

import com.api.OlimpoFake.Entity.LearningOutcomesEntity;
import com.api.OlimpoFake.Repository.LearningOutcomesRepository;
import com.api.OlimpoFake.Service.Dao.Idao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LearningOutcomesService implements Idao<LearningOutcomesEntity , Long> {
    @Autowired
    private LearningOutcomesRepository learningOutcomesRepository;
    @Override
    public List<LearningOutcomesEntity> findAll() {
        return learningOutcomesRepository.findAll();
    }

    @Override
    public LearningOutcomesEntity getById(Long id) {
        Optional<LearningOutcomesEntity> learningOutcomes = learningOutcomesRepository.findById(id);
        return learningOutcomes.orElse(null);
    }

    @Override
    public void update(LearningOutcomesEntity entity) {
        this.learningOutcomesRepository.save(entity);
    }

    @Override
    public LearningOutcomesEntity save(LearningOutcomesEntity entity) {
        return learningOutcomesRepository.save(entity);
    }

    @Override
    public void delete(LearningOutcomesEntity entity) {
        this.learningOutcomesRepository.delete(entity);
    }

    @Override
    public void create(LearningOutcomesEntity entity) {
        this.learningOutcomesRepository.save(entity);
    }

    public LearningOutcomesEntity findByName(String name) {
        return learningOutcomesRepository.findByName(name);
    }
}
