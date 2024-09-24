package com.api.OlimpoFake.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "PersonSheet")
public class PersonSheetEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private PersonsEntity person;

    @ManyToOne
    @JoinColumn(name = "sheet_id", nullable = false)
    private SheetsEntity sheet;
}
