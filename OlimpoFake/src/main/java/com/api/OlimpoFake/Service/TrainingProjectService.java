package com.api.OlimpoFake.Service;

import com.api.OlimpoFake.Entity.TrainingProjectEntity;
import com.api.OlimpoFake.Repository.TrainingProjectRepository;
import com.api.OlimpoFake.Service.Dao.Idao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainingProjectService implements Idao<TrainingProjectEntity , Long> {
    @Autowired
    private TrainingProjectRepository trainingProjectRepository;

    @Override
    public List<TrainingProjectEntity> findAll() {
        return trainingProjectRepository.findAll();
    }

    @Override
    public TrainingProjectEntity getById(Long id) {
        Optional<TrainingProjectEntity> trainingProject = trainingProjectRepository.findById(id);
        return trainingProject.orElse(null);
    }

    @Override
    public void update(TrainingProjectEntity entity) {
        this.trainingProjectRepository.save(entity);
    }

    @Override
    public TrainingProjectEntity save(TrainingProjectEntity entity) {
        return trainingProjectRepository.save(entity);
    }

    @Override
    public void delete(TrainingProjectEntity entity) {
        this.trainingProjectRepository.delete(entity);
    }

    @Override
    public void create(TrainingProjectEntity entity) {
        this.trainingProjectRepository.save(entity);
    }
}
