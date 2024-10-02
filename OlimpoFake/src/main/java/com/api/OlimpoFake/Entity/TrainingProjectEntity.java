package com.api.OlimpoFake.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Setter
@Getter
//@EqualsAndHashCode(exclude ={"coordination","studySheets"})
//@ToString(exclude ={"coordination","studySheets"})
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "training_projects")
public class TrainingProjectEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTrainingProject;

    @Column(nullable = false, length = 100, unique = true)
    private String name;

    @Column(nullable = false, length = 255, unique = true)
    private String description;

    @Column(nullable = false)
    private Integer duration;

    @Column(nullable = false, columnDefinition = "boolean default true")
    private Boolean state;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_at")
    private Date updatedAt;

    // Relación Many-to-Many con LearningOutcomesEntity
    @ManyToMany(mappedBy = "trainingProjects")
    private List<LearningOutcomesEntity> learningOutcomes;

    // Relación Many-to-Many con LearningOutcomesEntity
    @ManyToMany
    @JoinTable(
            name = "training_projects_learning_outcomes",
            joinColumns = @JoinColumn(name = "id_training_project"),
            inverseJoinColumns = @JoinColumn(name = "id_learning_outcome")
    )


    // Relación Many-to-One con ProgramsEntity
    @ManyToOne
    @JoinColumn(name = "IdProgram", nullable = false)
    private ProgramsEntity program;

}
