package com.api.OlimpoFake.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Sheets")
public class SheetsEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdSheet", nullable = false)
    private Long IdSheet;

    @Column (nullable = false ,unique = true)
    private Integer number;

    @Column (unique = true)
    private Integer num;

    @Column (name = "num_students", nullable = false)
    private Integer numberStudents;

    @Column (name = "start_lective", nullable = false)
    private Date startLective;

    @Column (name = "end_lective", nullable = false)
    private Date endLective;

    @Column (nullable = false, columnDefinition = "boolean default true")
    private Boolean state;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column (name ="created_at" ,updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_at")
    private Date updatedAt;

    // Relación One-to-One con QuartersEntity
    @OneToOne
    @JoinColumn(name = "IdQuarter", referencedColumnName = "IdQuarter")
    private QuartersEntity quarter;

    // Relación Many-to-One con ProgramsEntity
    @ManyToOne
    @JoinColumn(name = "IdProgram", referencedColumnName = "IdProgram")
    private ProgramsEntity program;

    // Relación One-to-Many con PersonsEntity
    @OneToMany(mappedBy = "sheet", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<PersonsEntity> students;


}
