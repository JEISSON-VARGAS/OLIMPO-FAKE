package com.api.OlimpoFake.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LearningOutcomesDto {
    private Long idLearningO;
    private String name;
    private String description;
    private Boolean state;
    private Date createdAt;
    private Date updatedAt;
}
