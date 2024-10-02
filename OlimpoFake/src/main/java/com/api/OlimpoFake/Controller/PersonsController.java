package com.api.OlimpoFake.Controller;

import com.api.OlimpoFake.Business.PersonsBusiness;
import com.api.OlimpoFake.Dto.PersonsDto;
import com.api.OlimpoFake.Utilities.Exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/persons")
public class PersonsController {
    @Autowired
    private PersonsBusiness personsBusiness;

    //End-Point para traer todas las personas
    @GetMapping("/all")
    public ResponseEntity<List<PersonsDto>> getAllPersons(){
        List<PersonsDto> personsList = personsBusiness.findAll();
        if (personsList.isEmpty()){
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(personsList);
        }
    }

    //End-Point para traer una persona por Id
    @GetMapping("/{id}")
    public ResponseEntity<Map<String , Object>> getPersonById(@PathVariable Long id){
        try {
            PersonsDto persons = personsBusiness.findPersonById(id);
            Map<String , Object> response = new HashMap<>();
            response.put("Status" , "success");
            response.put("message" , "Todos las personas han sido cargados");
            response.put("data " , persons);
            response.put("code" , 200);
            return ResponseEntity.ok(response);
        } catch (CustomException e){
            return handleException(e);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String , Object>> createPerson (@Validated @RequestBody PersonsDto personsDto){
        try {
            System.out.println("Datos recibidos: " + personsDto);
            personsBusiness.create(personsDto);
            Map<String , Object> response = new HashMap<>();
            response.put("Status" , "success");
            response.put("message " , "Person Created successfully" );
            response.put("code" , 200);
            return ResponseEntity.ok(response);
        } catch (CustomException e){
            System.out.println("Error: " + e.getMessage());
            return handleException(e);
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("status", "error", "message", "Unexpected error occurred"));
        }
    }

    // End-point para editar-actualizar una persona
    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, Object>> updatePerson(@PathVariable Long id, @Validated @RequestBody PersonsDto personsDto) {
        try {
            personsBusiness.update(id, personsDto);
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Person updated successfully");
            return ResponseEntity.ok(response);
        } catch (CustomException e) {
            return handleException(e);
        }
    }

    // End-point para eliminar una persona
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> deletePerson(@PathVariable Long id) {
        try {
            personsBusiness.delete(id);
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Person deleted successfully");
            return ResponseEntity.ok(response);
        } catch (CustomException e) {
            return handleException(e);
        }
    }

    // Errores
    private ResponseEntity<Map<String, Object>> handleException(CustomException e) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "error");
        response.put("message", e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}