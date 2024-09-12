package com.api.OlimpoFake.Controller;

import com.api.OlimpoFake.Business.SheetsBusiness;
import com.api.OlimpoFake.Dto.SheetsDto;
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
@RequestMapping("/api/sheets")
public class SheetsController {
    @Autowired
    private SheetsBusiness sheetsBusiness;

    // End-Point Traer todos
    @GetMapping("/all")
    public ResponseEntity<List<SheetsDto>> getAllSheets(){
        List<SheetsDto> sheetsList = sheetsBusiness.findAll();
        if (sheetsList.isEmpty()){
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(sheetsList);
        }
    }

    // End-Point Sheet Por Id
    @GetMapping("/{id}")
    public ResponseEntity<Map<String , Object>> getSheetById(@PathVariable Long id){
        try {
            SheetsDto sheets = sheetsBusiness.findSheetById(id);
            Map<String , Object> response = new HashMap<>();
            response.put("Status" , "success");
            response.put("message" , "Todos las Fichas han sido cargados");
            response.put("data " , sheets);
            response.put("code" , 200);
            return ResponseEntity.ok(response);
        } catch (CustomException e){
            return handleException(e);
        }
    }

    // End-Point para crear una nueva ficha
    @PostMapping("/create")
    public ResponseEntity<Map<String ,Object>> createSheet (@Validated @RequestBody SheetsDto sheetsDto){
        try {
            sheetsBusiness.create(sheetsDto);
            Map<String , Object> response = new HashMap<>();
            response.put("Status" , "success");
            response.put("message " , "Sheet Created successfully" );
            response.put("code" , 200);
            return ResponseEntity.ok(response);
        } catch (CustomException e){
            return handleException(e);
        }
    }

    // End-Point Para Actualizar una Ficha
    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String , Object>> updateSheet (@PathVariable Long id , @Validated @RequestBody SheetsDto sheetsDto){
        try {
            sheetsBusiness.update(id, sheetsDto);
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Person updated successfully");
            return ResponseEntity.ok(response);
        } catch (CustomException e){
            return handleException(e);
        }
    }

    // End-Point para Eliminar una ficha
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String , Object>> deleteSheet (@PathVariable Long id){
        try {
            sheetsBusiness.delete(id);
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Sheet deleted successfully");
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
