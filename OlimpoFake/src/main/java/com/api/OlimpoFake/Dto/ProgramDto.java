package com.api.OlimpoFake.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProgramDto {
    private Long IdProgram;
    private String name;
    private String description;
    private String trainingLevel;
    private Boolean state;
    private Date createdAt;
    private Date updatedAt;
}
