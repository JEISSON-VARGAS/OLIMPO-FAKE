package com.api.OlimpoFake.Business;

import com.api.OlimpoFake.Dto.RolesDto;
import com.api.OlimpoFake.Entity.RolesEntity;
import com.api.OlimpoFake.Service.RolesService;
import com.api.OlimpoFake.Utilities.Exception.CustomException;
import jakarta.persistence.Id;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RolesBusiness {
    @Autowired
    private RolesService rolesService;

    private final ModelMapper modelMapper = new ModelMapper(); // Instancia de ModelMapper para mapear objetos

    // Metodo para obtener todos los roles
    public List<RolesDto> findAll(){
        try {
            List<RolesEntity> rolesList = rolesService.findAll();
            if (rolesList.isEmpty()){
                return new ArrayList<>();
            }
            List<RolesDto> rolesDtoList = new ArrayList<>();
            rolesList.forEach(RolesEntity -> rolesDtoList.add(modelMapper.map(RolesEntity , RolesDto.class)));
            return rolesDtoList;
        }  catch (Exception e){
            throw new CustomException("Error Getting Roles");
        }
    }

    // Metodo para Obtener Un Rol por Id
    public RolesDto findRolById(Long id){
        try {
            RolesEntity roles = rolesService.getById(id);
            if (roles == null){
                throw new CustomException("Rol With Id" + id + "not found");
            }
            return modelMapper.map(roles , RolesDto.class);
        } catch (Exception e){
            throw new CustomException("Error getting Rol By Id");
        }
    }

    // Metodo para Actualizar un Rol
    public void update(Long id , RolesDto rolesDto){
        try {
            RolesEntity existingRol = rolesService.getById(id);
            if (existingRol == null){
                throw new CustomException("Rol With Id" + id + "not found");
            }
            existingRol.setName(rolesDto.getName());
            existingRol.setDescription(rolesDto.getDescription());
            existingRol.setState(rolesDto.getState());
            rolesService.save(existingRol);
        } catch (Exception e){
            throw new CustomException("Error Updating Rol");
        }
    }

    // Metodo para crear un Nuevo rol
    public void create(RolesDto rolesDto){
        try {
            String name = rolesDto.getName();
            RolesEntity existingRol = rolesService.findByName(name);
            if (existingRol != null){
                throw new CustomException("The Rol With Name" + name + "Already Exists");
            }
            RolesEntity roles = modelMapper.map(rolesDto , RolesEntity.class);
            rolesService.save(roles);
        } catch (Exception e){
            throw new CustomException("Error creating Rol");
        }
    }

    // Metodo para Eliminar un Rol
    public void delete (Long IdRol){
        try {
            RolesEntity roles = rolesService.getById(IdRol);
            if (roles == null){
                throw new CustomException("Rol With Id" + IdRol + "Not Found");
            }
            rolesService.delete(roles);
        } catch (Exception e){
            throw new CustomException("Error Deleting Rol:" + e.getMessage());
        }
    }

}
