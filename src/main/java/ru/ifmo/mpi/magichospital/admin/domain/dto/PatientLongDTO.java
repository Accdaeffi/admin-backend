package ru.ifmo.mpi.magichospital.admin.domain.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PatientLongDTO extends PatientShortDTO {
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value = "isMale")
    private boolean isMale;
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDateTime registrationTime;
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value = "isMage")
    private boolean isMage;
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String socialStatus;
    
    private List<DiseaseCaseDTO> diseaseCases;
    
}
