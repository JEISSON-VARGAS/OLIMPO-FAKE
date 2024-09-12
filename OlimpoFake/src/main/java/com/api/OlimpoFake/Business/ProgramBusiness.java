package com.api.OlimpoFake.Business;

import com.api.OlimpoFake.Dto.ProgramDto;
import com.api.OlimpoFake.Entity.ProgramsEntity;
import com.api.OlimpoFake.Service.ProgramService;
import com.api.OlimpoFake.Utilities.Exception.CustomException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProgramBusiness {
    @Autowired
    private ProgramService programService;

    private final ModelMapper modelMapper = new ModelMapper(); // Instancia de ModelMapper para mapear objetos


    // Metodo para Traer Toos los programas
    public List<ProgramDto> findAll(){
        try {
            List<ProgramsEntity> programsList = programService.findAll();
            if (programsList.isEmpty()){
                return new ArrayList<>();
            }
            List<ProgramDto> programDtoList = new ArrayList<>();
            programsList.forEach(ProgramsEntity -> programDtoList.add(modelMapper.map(ProgramsEntity , ProgramDto.class)));
            return programDtoList;
        } catch (Exception e){
            throw new CustomException("Error Getting All Programs");
        }
    }

    // Metodo para traer un Programa por el Id
    public ProgramDto findProgramById(Long id){
        try {
            ProgramsEntity programs = programService.getById(id);
            if (programs == null){
                throw new CustomException("Program With Id" + id + "not found");
            }
            return modelMapper.map(programs , ProgramDto.class);
        } catch (Exception e){
            throw new CustomException("Error Getting Program By Id");
        }
    }

    //  Metodo para Actualizar Un Programa
    public void update (Long id , ProgramDto programDto){
        try {
            ProgramsEntity existingProgram = programService.getById(id);
            if (existingProgram == null){
                throw new CustomException("Program With Id" + id + "not found");
            }
            existingProgram.setName(programDto.getName());
            existingProgram.setUpdatedAt(programDto.getUpdatedAt());
            existingProgram.setState(programDto.getState());
            existingProgram.setDescription(programDto.getDescription());
            existingProgram.setTrainingLevel(programDto.getTrainingLevel());
            existingProgram.setCreatedAt(programDto.getCreatedAt());
            programService.save(existingProgram);
        } catch (Exception e){
            throw new CustomException("Error Updating Program");
        }
    }

    // Metodo para crear un Programa
    public void create(ProgramDto programDto){
        try {
            String name = programDto.getName();
            ProgramsEntity existingProgram = programService.findByName(name);
            if (existingProgram != null){
                throw new CustomException("The program with Name" + name + "Already Exists");
            }
            ProgramsEntity programs = modelMapper.map( programDto , ProgramsEntity.class);
            programService.save(programs);
        } catch (Exception e){
            throw new CustomException("Error Creating Program");
        }
    }

    // Metodo para Eliminar un Programa
    public void delete(Long IdProgram){
        try {
            ProgramsEntity programs = programService.getById(IdProgram);
            if (programs == null){
                throw new CustomException("Program With Id" + IdProgram + "Not Found");
            }
            programService.delete(programs);
        } catch (Exception e){
            throw new CustomException("Error Deleting Program:" + e.getMessage());
        }
    }
    
}
