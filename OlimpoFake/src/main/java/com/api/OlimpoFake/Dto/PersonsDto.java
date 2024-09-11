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
    private Long IdPerson;
    private String Document;
    private String TypeDocument;
    private String Name;
    private String LastName;
    private Date date_birth;
    private String blood_type;
    private String email;
    private BigInteger phone;
    private String address ;

    //Relation with Roles

}
