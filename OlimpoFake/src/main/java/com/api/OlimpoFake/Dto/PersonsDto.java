package com.api.OlimpoFake.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonsDto {
    private Long idPerson;
    private String document;
    private String typeDocument;
    private String name;
    private String lastName;
    private Date date_birth;
    private String blood_type;
    private String email;
    private BigInteger phone;
    private String address;
    private Long idRole; // Para referenciar el rol si es necesario
}
