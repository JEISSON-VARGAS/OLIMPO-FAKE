package com.api.OlimpoFake.Service;

import com.api.OlimpoFake.Entity.ProgramsEntity;
import com.api.OlimpoFake.Repository.ProgramRepository;
import com.api.OlimpoFake.Service.Dao.Idao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProgramService implements Idao<ProgramsEntity , Long> {
    @Autowired
    private ProgramRepository programRepository;

    @Override
    public List<ProgramsEntity> findAll() {
        return programRepository.findAll();
    }

    @Override
    public ProgramsEntity getById(Long id) {
        Optional<ProgramsEntity> programs = programRepository.findById(id);
        return programs.orElse(null);
    }

    @Override
    public void update(ProgramsEntity entity) {
        this.programRepository.save(entity);
    }

    @Override
    public ProgramsEntity save(ProgramsEntity entity) {
        return programRepository.save(entity);
    }

    @Override
    public void delete(ProgramsEntity entity) {
        this.programRepository.delete(entity);
    }

    @Override
    public void create(ProgramsEntity entity) {
        this.programRepository.save(entity);
    }

    public ProgramsEntity findByName(String name) {
        return programRepository.findByName(name);
    }
}
