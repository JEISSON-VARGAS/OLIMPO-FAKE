package com.api.OlimpoFake.Controller;

import com.api.OlimpoFake.Business.RolesBusiness;
import com.api.OlimpoFake.Dto.RolesDto;
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
@RequestMapping("/api/roles")
public class RolesController {
    @Autowired
    private RolesBusiness rolesBusiness;

    //End-Point para traer todos los roles
    @GetMapping("/all")
    public ResponseEntity<List<RolesDto>> getAllRoles(){
        List<RolesDto> rolesList = rolesBusiness.findAll();
        if (rolesList.isEmpty()){
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(rolesList);
        }
    }

    //End-Point para traer una persona por Id
    @GetMapping("/{id}")
    public ResponseEntity<Map<String , Object>> getRolById(@PathVariable Long id){
        try {
            RolesDto roles = rolesBusiness.findRolById(id);
            Map<String , Object> response = new HashMap<>();
            response.put("Status" , "success");
            response.put("message" , "Todos los roles han sido cargados");
            response.put("data " , roles);
            response.put("code" , 200);
            return ResponseEntity.ok(response);
        } catch (CustomException e){
            return handleException(e);
        }
    }

    //End-point para crear un Rol
    @PostMapping("/create")
    public ResponseEntity<Map<String ,Object>> createRol (@Validated @RequestBody RolesDto rolesDto){
        try {
            rolesBusiness.create(rolesDto);
            Map<String , Object> response = new HashMap<>();
            response.put("Status" , "success");
            response.put("message " , "Rol Created successfully" );
            response.put("code" , 200);
            return ResponseEntity.ok(response);
        } catch (CustomException e){
            return handleException(e);
        }
    }

    //End-point para editar-actualizar un rol
    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String , Object>> updateRol (@PathVariable Long id , @Validated @RequestBody RolesDto rolesDto){
        try {
            rolesBusiness.update(id, rolesDto);
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Rol updated successfully");
            return ResponseEntity.ok(response);
        } catch (CustomException e){
            return handleException(e);
        }
    }

    //End-point para eliminar una persona
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String , Object>> deleteRol (@PathVariable Long id){
        try {
            rolesBusiness.delete(id);
            Map<String , Object> response = new HashMap<>();
            response.put("status" , "success");
            response.put("message" , "Rol deleted successfully");
            return ResponseEntity.ok(response);
        } catch (CustomException e){
            return handleException(e);
        }
    }

    //Errores
    private ResponseEntity<Map<String, Object>> handleException(CustomException e) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "error");
        response.put("message", e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }



}
