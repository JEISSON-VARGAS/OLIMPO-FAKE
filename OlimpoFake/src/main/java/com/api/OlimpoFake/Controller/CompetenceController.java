package com.api.OlimpoFake.Controller;

import com.api.OlimpoFake.Business.CompetenceBusiness;
import com.api.OlimpoFake.Dto.CompetenceDto;
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
@RequestMapping("/api/competences")
public class CompetenceController {

    @Autowired
    private CompetenceBusiness competenceBusiness;

    //End-Point Para Traer Todas las Competencias
    @GetMapping("/all")
    public ResponseEntity<List<CompetenceDto>> getAllCompetence(){
        List<CompetenceDto> competenceList = competenceBusiness.findAll();
        if (competenceList.isEmpty()){
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(competenceList);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<Map<String , Object>> getCompetenceById (@PathVariable Long id){
        try {
            CompetenceDto competences = competenceBusiness.findCompetenceById(id);
            Map<String , Object> response = new HashMap<>();
            response.put("Status" , "success");
            response.put("message" , "Todos las personas han sido cargados");
            response.put("data " , competences);
            response.put("code" , 200);
            return ResponseEntity.ok(response);
        } catch (CustomException e){
            return handleException(e);
        }
    }

    //End-Point Para Crear Una Competencia
    @PostMapping("/create")
    public ResponseEntity<Map<String , Object>> createCompetence (@Validated @RequestBody CompetenceDto competenceDto){
        try {
            competenceBusiness.create(competenceDto);
            Map<String , Object> response = new HashMap<>();
            response.put("Status" , "success");
            response.put("message " , "Competence Created successfully" );
            response.put("code" , 200);
            return ResponseEntity.ok(response);
        } catch (CustomException e){
            return handleException(e);
        }
    }

    // End-Point Para Editar-Actualizar Una Competencia
    public ResponseEntity<Map< String , Object>> updateCompetence (@PathVariable Long id , @Validated @RequestBody CompetenceDto competenceDto){
        try {
            competenceBusiness.update(id, competenceDto);
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Competence updated successfully");
            return ResponseEntity.ok(response);
        } catch ( CustomException e) {
            return handleException(e);
        }
    }

    // End-Point Para Eliminar Una Competencia
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String , Object>> deleteCompetence(@PathVariable Long id){
        try {
            competenceBusiness.delete(id);
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Competence deleted successfully");
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
