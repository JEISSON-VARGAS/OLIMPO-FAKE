package com.api.OlimpoFake.Dto;

import com.api.OlimpoFake.Entity.QuarterNameEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuartersDto {
    private Long IdQuarter;
    private QuarterNameEntity name;
    private Date createdAt;
    private Date updatedAt;
}
