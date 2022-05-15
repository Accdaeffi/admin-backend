package ru.ifmo.mpi.magichospital.admin.domain.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import ru.ifmo.mpi.magichospital.admin.domain.dao.Patient;

@Getter
public class PatientDTO {
	
	private int id;
    private String name;
    private String surname;
    private boolean isMale;
    private LocalDateTime registrationTime;
    private boolean isMage;
    private String socialStatus;
    
    public static PatientDTO fromPatientShortDTO(Patient patient) {
    	PatientDTO patientDTO = new PatientDTO(); 
    	
    	patientDTO.id = patient.getId();   
    	patientDTO.name = patient.getName();
    	patientDTO.surname = patient.getSurname();
    	patientDTO.socialStatus = patient.getSocialStatus().getName();
    	patientDTO.isMale = patient.isMale();
    	patientDTO.isMage = patient.isMage();
    	
    	return patientDTO;
    }
    
    public static PatientDTO fromPatientFullDTO(Patient patient) {
    	PatientDTO patientDTO = new PatientDTO(); 
    	
    	patientDTO.id = patient.getId();    	
    	patientDTO.name = patient.getName();
    	patientDTO.surname = patient.getSurname();
    	patientDTO.socialStatus = patient.getSocialStatus().getName();
    	patientDTO.isMale = patient.isMale();
    	patientDTO.isMage = patient.isMage();
    	
    	return patientDTO;
    }
    
    
}
