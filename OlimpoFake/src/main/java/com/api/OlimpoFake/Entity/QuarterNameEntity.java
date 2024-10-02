package com.api.OlimpoFake.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
@Embeddable
@Setter
@Getter
@EqualsAndHashCode
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuarterNameEntity implements Serializable {

    @Column(nullable = false)
    private Integer number;

    @Column(nullable = false, length = 5)
    private String extension;
}
