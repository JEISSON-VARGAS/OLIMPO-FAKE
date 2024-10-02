package com.api.OlimpoFake.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Quarters")
public class QuartersEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long IdQuarter;

    // Si QuarterNameEntity no es clave compuesta, usa @Embedded en lugar de @EmbeddedId
    @Embedded
    private QuarterNameEntity name;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column ( name ="created_at" ,updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_at")
    private Date updatedAt;

    // Relations

    // Relación One-to-One con SheetsEntity (opcional)
    @OneToOne(mappedBy = "quarter")
    private SheetsEntity sheet;
}
