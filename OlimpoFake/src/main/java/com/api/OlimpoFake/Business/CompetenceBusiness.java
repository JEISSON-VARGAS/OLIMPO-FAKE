package com.api.OlimpoFake.Business;

import com.api.OlimpoFake.Dto.CompetenceDto;
import com.api.OlimpoFake.Entity.CompetenceEntity;
import com.api.OlimpoFake.Service.CompetenceService;
import com.api.OlimpoFake.Utilities.Exception.CustomException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CompetenceBusiness {

    @Autowired
    private CompetenceService competenceService;

    private final ModelMapper modelMapper = new ModelMapper(); // Instancia de ModelMapper para mapear objetos

    // Metodo para traer Todas las Competences
    public List<CompetenceDto> findAll(){
        try {
            List<CompetenceEntity> competenceList = competenceService.findAll();
            if (competenceList.isEmpty()){
                return new ArrayList<>();
            }
            List<CompetenceDto> competenceDtoList = new ArrayList<>();
            competenceList.forEach(CompetenceEntity -> competenceDtoList.add(modelMapper.map(CompetenceEntity , CompetenceDto.class)));
            return competenceDtoList;
        } catch (Exception e){
            throw new CustomException("Error Getting All Competences");
        }
    }

    // Metodo Para Obtener Una Competencia por Id
    public CompetenceDto findCompetenceById(Long id){
        try {
            CompetenceEntity competence = competenceService.getById(id);
            if (competence == null){
                throw new CustomException("Competence With Id " + id + "not found");
            }
            return modelMapper.map(competence , CompetenceDto.class);
        } catch (Exception e){
            throw new CustomException("Error Getting Competence By Id");
        }
    }

    // Metodo Para Actualizar Una Competencia
    public void update(Long id, CompetenceDto competenceDto){
        try {
            CompetenceEntity existingCompetence = competenceService.getById(id);
            if (existingCompetence == null){
                throw new CustomException("Competence With Id " + id + "not found");
            }
            existingCompetence.setUpdatedAt(competenceDto.getUpdatedAt());
            existingCompetence.setDescription(competenceDto.getDescription());
            existingCompetence.setName(competenceDto.getName());
            existingCompetence.setCreatedAt(competenceDto.getCreatedAt());
            existingCompetence.setState(competenceDto.getState());
            competenceService.save(existingCompetence);
        } catch (Exception e){
            throw new CustomException("Error Updating Competence");
        }
    }

    // Metodo Para Crear Una Nueva Competencia
    public void create(CompetenceDto competenceDto){
        try {
            String name = competenceDto.getName();
            CompetenceEntity existingCompetence = competenceService.findByName(name);
            if (existingCompetence != null){
                throw new CustomException("The Competence With Name" + name + "Already Exists");
            }
            CompetenceEntity competence = modelMapper.map(competenceDto , CompetenceEntity.class);
            competenceService.save(competence);
        } catch (Exception e){
            throw new CustomException("Error Getting Competence");
        }
    }

    // Metodo Para Eliminar Una Competencia
    public void delete (Long IdCompetence){
        try {
            CompetenceEntity competence = competenceService.getById(IdCompetence);
            if (competence == null){
                throw new CustomException("Competence With Id" + IdCompetence + "Not found");
            }
            competenceService.delete(competence);
        } catch (Exception e){
            throw new CustomException("Error Deleting Competence:" + e.getMessage());
        }
    }

}
