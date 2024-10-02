package com.api.OlimpoFake.Business;

import com.api.OlimpoFake.Dto.QuartersDto;
import com.api.OlimpoFake.Entity.QuarterNameEntity;
import com.api.OlimpoFake.Entity.QuartersEntity;
import com.api.OlimpoFake.Service.QuartersService;
import com.api.OlimpoFake.Utilities.Exception.CustomException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class QuartersBusiness {
    @Autowired
    private QuartersService quartersService;

    private final ModelMapper modelMapper = new ModelMapper(); // Instancia de ModelMapper para mapear objetos


    // Metodo Para Obtener Todos los Trimestres
    public List<QuartersDto> findAll(){
        try {
            List<QuartersEntity> quartersList = quartersService.findAll();
            if (quartersList.isEmpty()){
                return new ArrayList<>();
            }
            List<QuartersDto> quartersDtoList = new ArrayList<>();
            quartersList.forEach(QuartersEntity -> quartersDtoList.add(modelMapper.map(QuartersEntity , QuartersDto.class)));
            return quartersDtoList;
        } catch (Exception e){
            throw new CustomException("Error Getting All Competences");
        }
    }

    // Metodo Para Obtener Una Competencia
    public QuartersDto findQuarterById(Long id){
        try {
            QuartersEntity quarters = quartersService.getById(id);
            if (quarters == null){
                throw new CustomException("Quarter With Id" + id + "not found");
            }
            return modelMapper.map(quarters , QuartersDto.class);
        } catch (Exception e){
            throw new CustomException("Error Getting Quarter By Id");
        }
    }

    // Metodo para Actualizar Una Quarter
    public void update (Long id , QuartersDto quartersDto){
        try {
            QuartersEntity existingQuarter =  quartersService.getById(id);
            if (existingQuarter == null){
                throw new CustomException("Quarter With Id" + id + "not found");
            }
            existingQuarter.setName(quartersDto.getName());
            existingQuarter.setUpdatedAt(quartersDto.getUpdatedAt());
            existingQuarter.setCreatedAt(quartersDto.getCreatedAt());
        } catch (Exception e){
            throw new CustomException("Error Updating Quarter");
        }
    }

    // Metodo Para Crear Un Nuevo Trimestre
    public void create(QuartersDto quartersDto){
        try {
            QuarterNameEntity name = quartersDto.getName();
            QuartersEntity existingQuarter = quartersService.findByName(name);
            if (existingQuarter != null){
                throw new CustomException("The Quarter With Name" + name + "Already Exists");
            }
            QuartersEntity quarters = modelMapper.map(quartersDto , QuartersEntity.class);
            quartersService.save(quarters);
        } catch (Exception e){
            throw new CustomException("Error Creating Quarter");
        }
    }

    // Metodo Para Eliminar Un Trimestre
    public void delete(Long IdQuarter){
        try {
            QuartersEntity quarters = quartersService.getById(IdQuarter);
            if (quarters == null){
                throw new CustomException("Quarter With Id" + IdQuarter + "Not Found");
            }
            quartersService.delete(quarters);
        } catch (Exception e){
            throw new CustomException("Error Deleting Quarter:" + e.getMessage());
        }
    }
}
