package com.api.OlimpoFake.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrainingProjectDto {
    private Long idTrainingProject;
    private String name;
    private String description;
    private Integer duration;
    private Boolean state;
    private Date createdAt;
    private Date updatedAt;
}
