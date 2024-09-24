package com.api.OlimpoFake.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
//@EqualsAndHashCode(exclude = {"competence", "projectActivities"})
//@ToString(exclude = {"competence", "projectActivities"})
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "learning_outcomes")
public class LearningOutcomesEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLearningO;

    @Column(nullable = false, length = 50, unique = true)
    private String name;

    @Column(nullable = false, length = 255, unique = true)
    private String description;

    @Column(nullable = false,columnDefinition = "boolean default true")
    private Boolean state;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at",updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_at")
    private Date updatedAt;

    //Relations

    // Relación Many-to-One con CompetenceEntity
    @ManyToOne
    @JoinColumn(name = "IdCompetence", referencedColumnName = "IdCompetence", nullable = false)
    private CompetenceEntity competence;

    // Relación Many-to-Many con TrainingProjectEntity
    @ManyToMany
    @JoinTable(
            name = "project_learning_outcomes",
            joinColumns = @JoinColumn(name = "idLearningO"),
            inverseJoinColumns = @JoinColumn(name = "idTrainingProject")
    )
    private List<TrainingProjectEntity> trainingProjects;
}
