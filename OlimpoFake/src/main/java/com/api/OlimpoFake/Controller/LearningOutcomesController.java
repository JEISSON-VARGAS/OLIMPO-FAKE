package com.api.OlimpoFake.Controller;

import com.api.OlimpoFake.Business.LearningOutcomesBusiness;
import com.api.OlimpoFake.Dto.LearningOutcomesDto;
import com.api.OlimpoFake.Utilities.Exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.crypto.Cipher;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/learningOutcomes")
public class LearningOutcomesController {

    @Autowired
    private LearningOutcomesBusiness learningOutcomesBusiness;

    //End-Point Ṕara Traer Todos Los Resultados De Aprendizaje
    @GetMapping("/all")
    public ResponseEntity<List<LearningOutcomesDto>> getAllLearningOutcomes(){
        List<LearningOutcomesDto> learningOutcomesList = learningOutcomesBusiness.findAll();
        if (learningOutcomesList.isEmpty()){
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(learningOutcomesList);
        }
    }

    //End-Point Para Traer Un Resultado De Aprendizaje Por Id
    @GetMapping("/{id}")
    public ResponseEntity<Map<String , Object>> getLearningById(@PathVariable Long id){
        try {
            LearningOutcomesDto learningOutcomes = learningOutcomesBusiness.findLearningOutcomesById(id);
            Map<String , Object> response = new HashMap<>();
            response.put("Status" , "success");
            response.put("message" , "Todos las personas han sido cargados");
            response.put("data " , learningOutcomes);
            response.put("code" , 200);
            return ResponseEntity.ok(response);
        } catch (CustomException e){
            return handleException(e);
        }
    }

   //End-Point Para Crear Un Resultado De Aprendizaje
    @PostMapping("/create")
    public ResponseEntity<Map<String , Object>> createLearningOutcomes(@Validated @RequestBody LearningOutcomesDto learningOutcomesDto){
        try {
            learningOutcomesBusiness.create(learningOutcomesDto);
            Map<String , Object> response = new HashMap<>();
            response.put("Status" , "success");
            response.put("message " , "Learning Outcome Created successfully" );
            response.put("code" , 200);
            return ResponseEntity.ok(response);
        } catch (CustomException e){
            return handleException(e);
        }
    }

    // End-Point Para Editar-Actualizar Un Resultado De Aprendizaje
    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String , Object>> updateLearningOutcomes(@PathVariable Long id , @Validated @RequestBody LearningOutcomesDto learningOutcomesDto){
        try {
            learningOutcomesBusiness.update(id, learningOutcomesDto);
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Learning Outcome updated successfully");
            return ResponseEntity.ok(response);
        } catch (CustomException e){
            return handleException(e);
        }
    }

    // End-Point Para Eliminar Un Resultado De Aprendizaje
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String , Object>> deleteLearningOutcomes (@PathVariable Long id){
        try {
            learningOutcomesBusiness.delete(id);
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Learning Outcome deleted successfully");
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
