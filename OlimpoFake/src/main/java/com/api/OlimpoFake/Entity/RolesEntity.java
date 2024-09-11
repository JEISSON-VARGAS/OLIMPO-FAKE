package com.api.OlimpoFake.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Rol")
public class RolesEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdRol", nullable = false)
    private Long IdRol;

    @Column(nullable = false, length = 20,unique = true)
    private String name;

    @Column(nullable = false, length = 255, unique = true)
    private String description;

    @Column(nullable = false, columnDefinition = "boolean default true")
    private Boolean state;
    //Relation With Person
}
