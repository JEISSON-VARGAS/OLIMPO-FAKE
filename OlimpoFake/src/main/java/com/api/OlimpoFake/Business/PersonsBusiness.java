package com.api.OlimpoFake.Business;

import com.api.OlimpoFake.Dto.PersonsDto;
import com.api.OlimpoFake.Entity.PersonsEntity;
import com.api.OlimpoFake.Entity.RolesEntity;
import com.api.OlimpoFake.Service.PersonsService;
import com.api.OlimpoFake.Service.RolesService;
import com.api.OlimpoFake.Utilities.Exception.CustomException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonsBusiness {

    @Autowired
    private PersonsService personsService;
    @Autowired
    private RolesService rolesService;

    private final ModelMapper modelMapper = new ModelMapper(); // Instancia de ModelMapper para mapear objetos

    // Metodo Para Obtener Todas Las Personas
    public List<PersonsDto> findAll(){
        try {
            // Obtener La Lista De Todas Las Personas desde el service
            List<PersonsEntity> personsList = personsService.findAll();
            // Verificar si la lista esta vacia
            if (personsList.isEmpty()){
                return new ArrayList<>();
            }
            // Crear una lista de DtoS para almacenar las personas
            List<PersonsDto> personsDtoList = new ArrayList<>();
            // Mapear cada persona a su respectivo Dto y agregegarlo a la lista
            personsList.forEach(PersonsEntity -> personsDtoList.add(modelMapper.map(PersonsEntity , PersonsDto.class)));
            return personsDtoList;
        } catch (Exception e){
            throw new CustomException("Error Getting All Persons");

        }
    }

    // Metodo para Obtener una Persona
    public PersonsDto findPersonById(Long id){
        try {
            PersonsEntity persons = personsService.getById(id);
            if (persons == null){
                throw new CustomException("Person With Id " + id + "not found");
            }
            return modelMapper.map(persons , PersonsDto.class);
        }catch (Exception e){
            throw new CustomException("Error Getting Person By Id");
        }
    }

    // Metodo para actualizar una Persona
    public void update(Long id , PersonsDto personsDto){
        try {
            PersonsEntity existingPerson = personsService.getById(id);
            if (existingPerson == null){
                throw new CustomException("Person With ID" + id + "not found");
            }
            // Actualizar los campos necesarios de la entidad existente
            existingPerson.setDocument(personsDto.getDocument());
            existingPerson.setEmail(personsDto.getEmail());
            existingPerson.setName(personsDto.getName());
            existingPerson.setLastName(personsDto.getLastName());
            existingPerson.setPhone(personsDto.getPhone());
            existingPerson.setPhone(personsDto.getPhone());
            existingPerson.setBlood_type(personsDto.getBlood_type());
            existingPerson.setDate_birth(personsDto.getDate_birth());
            existingPerson.setAddress(personsDto.getAddress());
            existingPerson.setTypeDocument(personsDto.getTypeDocument());
            //Guardar la entidad actualizada
            personsService.save(existingPerson);
        } catch (Exception e){
            throw new CustomException("Error updating Person");
        }
    }

    // Metodo para crear una nueva Persona
    /*public void create(PersonsDto personsDto){
        try {
            //Verificar si el Documento de la persona ya existe en la base de datos
            String Document = personsDto.getDocument();
            PersonsEntity existingPerson = personsService.findByDocument(Document);
            if (existingPerson != null){
                throw new CustomException("The person with Document" + Document + "Already Exists");
            }
            // Mapear el Dto a la Entidad
            PersonsEntity persons = modelMapper.map(personsDto , PersonsEntity.class);
            personsService.save(persons);
        } catch (Exception e){
            throw new CustomException("Error Creating Person");
        }
    }*/
    public void create(PersonsDto personsDto){
        try {
            // Verificar si el Documento de la persona ya existe en la base de datos
            String document = personsDto.getDocument();
            PersonsEntity existingPerson = personsService.findByDocument(document);
            if (existingPerson != null){
                throw new CustomException("The person with Document " + document + " already exists");
            }
            // Buscar el rol por idRole
            RolesEntity role = rolesService.getById(personsDto.getIdRole());
            if (role == null) {
                throw new CustomException("Role with ID " + personsDto.getIdRole() + " not found");
            }
            // Mapear el Dto a la Entidad
            PersonsEntity persons = modelMapper.map(personsDto, PersonsEntity.class);
            persons.setRole(role); // Asignar el rol a la persona
            personsService.save(persons);
        } catch (Exception e){
            throw new CustomException("Error Creating Person: " + e.getMessage());
        }
    }

    // Metodo para Eliminar una Persona
    public void delete(Long IdPerson) {
        try {
            PersonsEntity persons = personsService.getById(IdPerson);
            if (persons == null) {
                throw new CustomException("Person With id" + IdPerson + "Not Found");
            }
            personsService.delete(persons);
        } catch (Exception e) {
            throw new CustomException("Error Deleting Person:" + e.getMessage());
        }
    }

}
