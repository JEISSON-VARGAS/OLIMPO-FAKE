package com.api.OlimpoFake.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Apprentices")
public class ApprenticeEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_apprentice", nullable = false)
    private Long id_apprentice;

    @Column(nullable = false)
    private String state;

    // Relación Many-to-One con PersonsEntity
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_person", nullable = false)
    @JsonBackReference
    private PersonsEntity person;

    // Relación Many-to-One con StudentSheetEntity
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_student_sheet", nullable = false)
    @JsonBackReference
    private PersonSheetEntity studentSheet;
}
