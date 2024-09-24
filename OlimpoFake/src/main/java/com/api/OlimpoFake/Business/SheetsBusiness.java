package com.api.OlimpoFake.Business;

import com.api.OlimpoFake.Dto.SheetsDto;
import com.api.OlimpoFake.Entity.PersonsEntity;
import com.api.OlimpoFake.Entity.RolesEntity;
import com.api.OlimpoFake.Entity.SheetsEntity;
import com.api.OlimpoFake.Service.PersonsService;
import com.api.OlimpoFake.Service.SheetsService;
import com.api.OlimpoFake.Utilities.Exception.CustomException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SheetsBusiness {
    @Autowired
    private SheetsService sheetsService;

    @Autowired
    private PersonsService personsService;

    private final ModelMapper modelMapper = new ModelMapper(); // Instancia de ModelMapper para mapear objetos

    //  Metodo Para Obtener Todas Fichas
    public List<SheetsDto> findAll(){
        try {
            List<SheetsEntity> sheetsList = sheetsService.findAll();
            if (sheetsList.isEmpty()){
                return new ArrayList<>();
            }
            List<SheetsDto> sheetsDtoList = new ArrayList<>();
            sheetsList.forEach(SheetsEntity -> sheetsDtoList.add(modelMapper.map(SheetsEntity , SheetsDto.class)));
            return sheetsDtoList;
        } catch (Exception e){
            throw new CustomException("Error Getting All Sheets");
        }
    }

    //Metodo para Obtener Una Ficha
    public SheetsDto findSheetById(Long id){
        try {
            SheetsEntity sheets = sheetsService.getById(id);
            if (sheets == null){
                throw new CustomException("Sheet With Id" + id + "not found");
            }
            return modelMapper.map(sheets , SheetsDto.class);
        } catch (Exception e){
            throw new CustomException("Error Getting Sheet By Id");
        }
    }

    // Metodo para Actualizar Una Ficha
    public void update(Long id , SheetsDto sheetsDto){
        try {
            SheetsEntity existingSheet = sheetsService.getById(id);
            if (existingSheet == null){
                throw new CustomException("Sheet With Id" + id + "not found");
            }
            existingSheet.setNum(sheetsDto.getNum());
            existingSheet.setNumberStudents(sheetsDto.getNumberStudents());
            existingSheet.setState(sheetsDto.getState());
            existingSheet.setStartLective(sheetsDto.getStartLective());
            existingSheet.setEndLective(sheetsDto.getEndLective());
            existingSheet.setNumber(sheetsDto.getNumber());
            sheetsService.save(existingSheet);
        } catch (Exception e){
            throw new CustomException("Error Updating Person");
        }
    }

    // Metodo para crear una nueva Ficha
    public void create(SheetsDto sheetsDto){
        try {
            Integer number = sheetsDto.getNumber();
            SheetsEntity existingSheet = sheetsService.findByNumber(number);
            if (existingSheet != null){
                throw new CustomException("The Sheet With Number" + number + "Already Exists");
            }
            SheetsEntity sheets = modelMapper.map(sheetsDto , SheetsEntity.class);
            sheetsService.save(sheets);
        } catch (Exception e){
            throw new CustomException("Error Creating Sheet");
        }
    }

    // Método para asignar una ficha a una persona (solo si es estudiante)
    public void assignSheetToPerson(Long IdPerson, Long IdSheet) {
        try {
            // Buscar persona por ID
            PersonsEntity person = personsService.getById(IdPerson);
            if (person == null) {
                throw new CustomException("Person with ID " + IdPerson + " not found");
            }

            // Verificar si la persona tiene el rol de Estudiante
            RolesEntity role = person.getRole(); // Asumiendo que hay una relación ManyToOne con RolesEntity
            if (!"Estudiante".equals(role.getName())) {
                throw new CustomException("Only users with role 'Estudiante' can be assigned to a sheet");
            }

            // Buscar ficha por ID
            SheetsEntity sheet = sheetsService.getById(IdSheet);
            if (sheet == null) {
                throw new CustomException("Sheet with ID " + IdSheet + " not found");
            }

            // Asignar la ficha a la persona
            person.setSheet(sheet);  // Esto depende de cómo tengas modelada la relación
            personsService.update(person);

        } catch (Exception e) {
            throw new CustomException("Error assigning sheet to person: " + e.getMessage());
        }
    }

    // Metodo para ELiminar una Ficha
    public void delete(Long IdSheet){
        try {
            SheetsEntity sheets = sheetsService.getById(IdSheet);
            if (sheets == null){
                throw new CustomException("Sheet With Id" + IdSheet + "not found");
            }
            sheetsService.delete(sheets);
        } catch (Exception e){
            throw new CustomException("Error Deleting Sheet:" + e.getMessage());
        }
    }

    public List<PersonsEntity> getStudentsBySheet(Long IdSheet) {
        return sheetsService.findStudentsBySheet(IdSheet);
    }
}
