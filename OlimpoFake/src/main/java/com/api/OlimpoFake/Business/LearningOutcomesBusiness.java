package com.api.OlimpoFake.Business;

import com.api.OlimpoFake.Dto.LearningOutcomesDto;
import com.api.OlimpoFake.Entity.LearningOutcomesEntity;
import com.api.OlimpoFake.Service.LearningOutcomesService;
import com.api.OlimpoFake.Utilities.Exception.CustomException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LearningOutcomesBusiness {
    @Autowired
    private LearningOutcomesService learningOutcomesService;

    private final ModelMapper modelMapper = new ModelMapper(); // Instancia de ModelMapper para mapear objetos


    //Metodo Para Obtener Todos los Resultados De Aprendizaje
    public List<LearningOutcomesDto> findAll(){
        try {
            List<LearningOutcomesEntity> learningOutcomesList = learningOutcomesService.findAll();
            if (learningOutcomesList.isEmpty()){
                return new ArrayList<>();
            }
            List<LearningOutcomesDto> learningOutcomesDtoList = new ArrayList<>();
            learningOutcomesList.forEach(LearningOutcomesEntity -> learningOutcomesDtoList.add(modelMapper.map(LearningOutcomesEntity , LearningOutcomesDto.class)));
            return learningOutcomesDtoList;
        } catch (Exception e){
            throw new CustomException("Error Getting All Learning Outcomes");
        }
    }

    // Metodo Para Obtener Un Resultado De Aprendizaje Por ID
    public LearningOutcomesDto findLearningOutcomesById(Long id){
        try {
            LearningOutcomesEntity learningOutcomes = learningOutcomesService.getById(id);
            if (learningOutcomes == null){
                throw new CustomException("Learning Outcomes With Id" + id + "Not Found");
            }
            return modelMapper.map(learningOutcomes , LearningOutcomesDto.class);
        } catch (Exception e){
            throw new CustomException("Error Getting Learning Outcomes By Id");
        }
    }

    // Metodo Para Actualizar Un Resultado De Aprendizaje
    public void update(Long id , LearningOutcomesDto learningOutcomesDto){
        try {
            LearningOutcomesEntity existingLearningOutcomes = learningOutcomesService.getById(id);
            if (existingLearningOutcomes == null){
                throw new CustomException("Learning Outcomes With ID" + id + "Not Found" );
            }
            existingLearningOutcomes.setName(learningOutcomesDto.getName());
            existingLearningOutcomes.setUpdatedAt(learningOutcomesDto.getUpdatedAt());
            existingLearningOutcomes.setState(learningOutcomesDto.getState());
            existingLearningOutcomes.setCreatedAt(learningOutcomesDto.getCreatedAt());
            existingLearningOutcomes.setDescription(learningOutcomesDto.getDescription());
            learningOutcomesService.save(existingLearningOutcomes);
        } catch (Exception e){
            throw new CustomException("Error Updating Learning Outcome");
        }
    }

    // Metodo Para Crear Un Nuevo Resultado De Aprendizaje
    public void create(LearningOutcomesDto learningOutcomesDto){
        try {
            String name = learningOutcomesDto.getName();
            LearningOutcomesEntity existingLearningOutcomes = learningOutcomesService.findByName(name);
            if (existingLearningOutcomes != null){
                throw new CustomException("The Learning Outcome With Name" + name + "Already Exists");
            }
            LearningOutcomesEntity learningOutcomes = modelMapper.map(learningOutcomesDto , LearningOutcomesEntity.class);
            learningOutcomesService.save(learningOutcomes);
        } catch (Exception e){
            throw new CustomException("Error Creating Learning Outcome");
        }
    }

    // Metodo Para Eliminar Un Resultado De Aprendizaje
    public void delete(Long IdLearningO){
        try {
            LearningOutcomesEntity learningOutcomes = learningOutcomesService.getById(IdLearningO);
            if (learningOutcomes == null){
                throw new CustomException("Learning With Id" + IdLearningO + "Not Found");
            }
            learningOutcomesService.delete(learningOutcomes);
        } catch (Exception e){
            throw new CustomException("Error Deleting Learning Outcome:" + e.getMessage());
        }
    }

}
