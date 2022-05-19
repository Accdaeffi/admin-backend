package ru.ifmo.mpi.magichospital.admin.domain.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.ifmo.mpi.magichospital.admin.domain.dao.Patient;

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
    
    public static PatientLongDTO fromPatientLongDTO(Patient patient) {
    	PatientLongDTO patientDTO = new PatientLongDTO(); 
    	
    	patientDTO.id = patient.getId();    	
    	patientDTO.name = patient.getName();
    	patientDTO.surname = patient.getSurname();
    	patientDTO.socialStatus = patient.getSocialStatus().getCode();
    	patientDTO.isMale = patient.isMale();
    	patientDTO.isMage = patient.isMage();
    	patientDTO.registrationTime = patient.getRegistrationTime();
    	
    	return patientDTO;
    }
    
    
}
