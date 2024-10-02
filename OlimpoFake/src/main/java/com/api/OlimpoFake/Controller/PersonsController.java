package com.api.OlimpoFake.Controller;

import com.api.OlimpoFake.Business.PersonsBusiness;
import com.api.OlimpoFake.Dto.PersonsDto;
import com.api.OlimpoFake.Utilities.Exception.CustomException;
import org.modelmapper.ModelMapper;
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

    @Autowired
    private ModelMapper modelMapper;  // Agregado desde el Código 1

    // End-Point para traer todas las personas
    @GetMapping("/all")
    public ResponseEntity<List<PersonsDto>> getAllPersons() {
        List<PersonsDto> personsList = personsBusiness.findAll();
        if (personsList.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(personsList);
        }
    }

    // End-Point para traer una persona por Id
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getPersonById(@PathVariable Long id) {
        try {
            PersonsDto persons = personsBusiness.findPersonById(id);
            Map<String, Object> response = new HashMap<>();
            response.put("Status", "success");
            response.put("message", "Persona cargada exitosamente");
            response.put("data", persons);
            response.put("code", 200);
            return ResponseEntity.ok(response);
        } catch (CustomException e) {
            return handleException(e);
        }
    }

    // End-Point para crear una nueva persona
    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createPerson(@Validated @RequestBody PersonsDto personsDto) {
        try {
            System.out.println("Datos recibidos: " + personsDto);

            // Convertir PersonsDto a PersonsEntitie desde el Código 1
            PersonsEntitie personEntitie = modelMapper.map(personsDto, PersonsEntitie.class);
            personsBusiness.create(personsDto);

            Map<String, Object> response = new HashMap<>();
            response.put("Status", "success");
            response.put("message", "Persona creada exitosamente");
            response.put("code", 200);
            return ResponseEntity.ok(response);
        } catch (CustomException e) {
            System.out.println("Error: " + e.getMessage());
            return handleException(e);
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("status", "error", "message", "Error inesperado"));
        }
    }

    // End-Point para actualizar una persona
    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, Object>> updatePerson(@PathVariable Long id, @Validated @RequestBody PersonsDto personsDto) {
        try {
            personsBusiness.update(id, personsDto);
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Persona actualizada exitosamente");
            return ResponseEntity.ok(response);
        } catch (CustomException e) {
            return handleException(e);
        }
    }

    // End-Point para eliminar una persona
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> deletePerson(@PathVariable Long id) {
        try {
            personsBusiness.delete(id);
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Persona eliminada exitosamente");
            return ResponseEntity.ok(response);
        } catch (CustomException e) {
            return handleException(e);
        }
    }

    // Manejo de excepciones (agregado desde el Código 2)
    private ResponseEntity<Map<String, Object>> handleException(CustomException e) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "error");
        response.put("message", e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
