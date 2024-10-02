package com.api.OlimpoFake.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SheetsDto {
    private Long IdSheet;
    private Integer number;
    private Integer num;
    private Integer numberStudents;
    private Date startLective;
    private Date endLective;
    private Boolean state;
    private Date createdAt;
    private Date updatedAt;
}
