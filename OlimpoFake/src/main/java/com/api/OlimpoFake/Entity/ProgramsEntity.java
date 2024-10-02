package com.api.OlimpoFake.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Programs")
public class ProgramsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdProgram", nullable = false)
    private Long IdProgram;

    @Column (nullable = false, length = 100,unique = true)
    private String name;

    @Column (nullable = false,length = 255, unique = true)
    private String description;

    @Column (nullable = false, length = 50, name = "training_level")
    private String trainingLevel;

    @Column (nullable = false, columnDefinition = "boolean default true")
    private Boolean state;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column (name ="created_at", updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_at")
    private Date updatedAt;


    // Relación One-to-Many con TrainingProjectEntity
    @OneToMany(mappedBy = "program")
    private List<TrainingProjectEntity> trainingProjects;

    // Relación Many-to-Many con LearningOutcomesEntity
    @ManyToMany
    @JoinTable(
            name = "programs_learning_outcomes",
            joinColumns = @JoinColumn(name = "IdProgram"),
            inverseJoinColumns = @JoinColumn(name = "id_learning_outcome")
    )
    private List<LearningOutcomesEntity> learningOutcomes;

}
