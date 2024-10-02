package com.api.OlimpoFake.Controller;

import com.api.OlimpoFake.Business.ProgramBusiness;
import com.api.OlimpoFake.Dto.ProgramDto;
import com.api.OlimpoFake.Utilities.Exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/programs")
public class ProgramController {
    @Autowired
    private ProgramBusiness programBusiness;

    //End-Point Para Traer Todos los Programas
    @GetMapping("/all")
    public ResponseEntity<List<ProgramDto>> getAllPrograms(){
        List<ProgramDto> programList = programBusiness.findAll();
        if (programList.isEmpty()){
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(programList);
        }
    }

    //End-Point Para Traer Por Id Un Programa
    @GetMapping("/{id}")
    public ResponseEntity<Map<String ,Object>> getProgramById(@PathVariable Long id){
        try {
            ProgramDto programs = programBusiness.findProgramById(id);
            Map<String , Object> response = new HashMap<>();
            response.put("Status" , "success");
            response.put("message" , "Todos las personas han sido cargados");
            response.put("data " , programs);
            response.put("code" , 200);
            return ResponseEntity.ok(response);
        } catch (CustomException e){
            return handleException(e);
        }
    }

    //End-Point Para Crear Un Programa
    @PostMapping("/create")
    public ResponseEntity<Map<String , Object>> createProgram (@Validated @RequestBody ProgramDto programDto){
        try {
            programBusiness.create(programDto);
            Map<String , Object> response = new HashMap<>();
            response.put("Status" , "success");
            response.put("message " , "Program Created successfully" );
            response.put("code" , 200);
            return ResponseEntity.ok(response);
        } catch (CustomException e){
            return handleException(e);
        }
    }

    //End-Point Para Actualizar Un Programa
    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String , Object>> updateProgram (@PathVariable Long id , @Validated @RequestBody ProgramDto programDto){
        try {
            programBusiness.update(id, programDto);
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Program updated successfully");
            return ResponseEntity.ok(response);
        } catch (CustomException e){
            return handleException(e);

        }
    }

    //End-Point Para Eliminar Un Programa
    @DeleteMapping("/delete")
    public ResponseEntity<Map<String , Object>> deleteProgram(@PathVariable Long id){
        try {
            programBusiness.delete(id);
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Program deleted successfully");
            return ResponseEntity.ok(response);
        } catch (CustomException e){
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
