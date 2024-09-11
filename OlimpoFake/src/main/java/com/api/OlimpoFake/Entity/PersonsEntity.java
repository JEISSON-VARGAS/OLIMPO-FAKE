package com.api.OlimpoFake.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Persons")
public class PersonsEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdPerson", nullable = false)
    private Long IdPerson;

    @Column(name = "Document" , nullable = false , length = 20)
    private String document;

    @Column(name = "TypeDocument" , nullable = false)
    private String TypeDocument;

    @Column(name = "Name" , nullable = false)
    private String Name;

    @Column(name = "LastName" , nullable = false)
    private String LastName;

    @Column (nullable = false)
    private Date date_birth;

    @Column (nullable = false, length = 10)
    private String blood_type;

    @Column (nullable = false, unique = true)
    private String email;

    @Column (nullable = false)
    private BigInteger phone;

    @Column (nullable = false, length = 100)
    private String address ;

    //Relation with Roles




}
