package ru.ifmo.mpi.magichospital.admin.domain.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import ru.ifmo.mpi.magichospital.admin.domain.dao.Administrator;
import ru.ifmo.mpi.magichospital.admin.domain.dao.DiseaseCase;
import ru.ifmo.mpi.magichospital.admin.domain.dao.Healer;

@Getter
public class DiseaseCaseDTO {
    
    private int id;
    private String registrator;
    private String healer;
    private String patientComplaints;
    private LocalDateTime registrationTime;
    private String disease;
    private String actions;    
    
    
    public DiseaseCaseDTO(DiseaseCase diseaseCase) {
    	this.id = diseaseCase.getId();
    	
    	Administrator registrator = diseaseCase.getAdministrator();
    	if (registrator.getSurname() != null) {
    		this.registrator = String.format("%s %s", registrator.getName(), registrator.getSurname());
    	} else {
    		this.registrator = String.format("%s", registrator.getName());
    	}
    	
    	Healer healer = diseaseCase.getHealer();
    	if (healer.getSurname() != null) {
    		this.healer = String.format("%s %s", healer.getName(), healer.getSurname());
    	} else {
    		this.healer = String.format("%s", healer.getName());
    	}
    
    	this.patientComplaints = diseaseCase.getPatientComplaints();
    	this.registrationTime = diseaseCase.getRegistrationTime();
    	this.disease = diseaseCase.getDisease().getTitle();
    	this.actions = diseaseCase.getActions();
    }
	
}
