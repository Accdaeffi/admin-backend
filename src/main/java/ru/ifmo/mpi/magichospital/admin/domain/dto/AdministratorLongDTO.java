package ru.ifmo.mpi.magichospital.admin.domain.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AdministratorLongDTO {
	
    private int id;
    private String name;
    private String surname;
    
    @JsonProperty("isMale")
    private boolean isMale;
    
    private LocalDate workStartDate;
    private String socialStatus;
    
}
