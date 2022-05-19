package ru.ifmo.mpi.magichospital.admin.domain.dto;

import lombok.Data;
import ru.ifmo.mpi.magichospital.admin.domain.dao.Patient;

@Data
public class PatientShortDTO {
	
	private int id;
    private String name;
    private String surname;
	
    public static PatientShortDTO fromPatientShortDTO(Patient patient) {
    	PatientShortDTO patientDTO = new PatientShortDTO(); 
    	
    	patientDTO.id = patient.getId();   
    	patientDTO.name = patient.getName();
    	patientDTO.surname = patient.getSurname();
    	
    	return patientDTO;
    }

}
