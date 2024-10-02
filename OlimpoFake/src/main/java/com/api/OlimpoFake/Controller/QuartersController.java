package com.api.OlimpoFake.Controller;

import com.api.OlimpoFake.Business.QuartersBusiness;
import com.api.OlimpoFake.Dto.QuartersDto;
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
@RequestMapping("/api/quarters")
public class QuartersController {
    @Autowired
    private QuartersBusiness quartersBusiness;

    // End-Point Para Traer Todos los Trimestres
    @GetMapping("/all")
    public ResponseEntity<List<QuartersDto>> getAllQuarters(){
        List<QuartersDto> quartersList = quartersBusiness.findAll();
        if (quartersList.isEmpty()){
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(quartersList);
        }
    }

    // End-Point Para Traer Un Trimestre Por Id
    @GetMapping("/{id}")
    public ResponseEntity<Map<String , Object>> getQuarterById(@PathVariable Long id){
        try {
            QuartersDto quarters = quartersBusiness.findQuarterById(id);
            Map<String , Object> response = new HashMap<>();
            response.put("Status" , "success");
            response.put("message" , "Todos las personas han sido cargados");
            response.put("data " , quarters);
            response.put("code" , 200);
            return ResponseEntity.ok(response);
        } catch (CustomException e){
            return handleException(e);

        }
    }

    // End-Point Para Crear Un Trimestre
    @PostMapping("/create")
    public ResponseEntity<Map<String ,Object>> createQuarter (@Validated @RequestBody QuartersDto quartersDto){
        try {
            quartersBusiness.create(quartersDto);
            Map<String , Object> response = new HashMap<>();
            response.put("Status" , "success");
            response.put("message " , "Quarter Created successfully" );
            response.put("code" , 200);
            return ResponseEntity.ok(response);
        } catch (CustomException e){
            return handleException(e);
        }
    }

    // End-Point Para Editar-Actualizar Un Trimestre
    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String , Object>> updateQuarter (@PathVariable Long id , @Validated @RequestBody QuartersDto quartersDto){
        try {
            quartersBusiness.update(id, quartersDto);
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Quarter updated successfully");
            return ResponseEntity.ok(response);
        } catch (CustomException e){
            return handleException(e);

        }
    }

    // End-Point Para Eliminar Un Trimestre
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String , Object>> deleteQuarter(@PathVariable Long id){
        try {
            quartersBusiness.delete(id);
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Quarter deleted successfully");
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
