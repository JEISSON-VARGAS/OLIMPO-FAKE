package com.api.OlimpoFake.Service;

import com.api.OlimpoFake.Entity.RolesEntity;
import com.api.OlimpoFake.Repository.RolesRepository;
import com.api.OlimpoFake.Service.Dao.Idao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolesService implements Idao<RolesEntity , Long> {
    @Autowired
    RolesRepository rolesRepository;

    @Override
    public List<RolesEntity> findAll() {
        return rolesRepository.findAll();
    }

    @Override
    public RolesEntity getById(Long id) {
        Optional<RolesEntity> roles = rolesRepository.findById(id);
        return roles.orElse(null);
    }

    @Override
    public void update(RolesEntity entity) {
        this.rolesRepository.save(entity);
    }

    @Override
    public RolesEntity save(RolesEntity entity) {
        return rolesRepository.save(entity);
    }

    @Override
    public void delete(RolesEntity entity) {
        this.rolesRepository.delete(entity);
    }

    @Override
    public void create(RolesEntity entity) {
        this.rolesRepository.save(entity);
    }

    public RolesEntity findByName(String name) {
        return rolesRepository.findByName(name);
    }
}
