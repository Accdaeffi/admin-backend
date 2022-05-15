package ru.ifmo.mpi.magichospital.admin.domain.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import ru.ifmo.mpi.magichospital.admin.domain.dao.Administrator;
import ru.ifmo.mpi.magichospital.admin.domain.dao.DiseaseCase;

@Getter
public class DiseaseCaseDTO {
    
    private int id;
    private String registrator;
    private HealerDTO healer;
    private String patientComplaints;
    private LocalDateTime registrationTime;
    private String disease;
    private String action;    
    
    
    // TODO: обдумать ввод симптомов
    public DiseaseCaseDTO(DiseaseCase diseaseCase) {
    	this.id = diseaseCase.getId();
    	
    	Administrator registrator = diseaseCase.getAdministrator();
    	this.registrator = String.format("%s %s", registrator.getName(), registrator.getSurname());
    	
    	this.healer = new HealerDTO(diseaseCase.getHealer());
    	this.patientComplaints = diseaseCase.getPatientComplaints();
    	this.registrationTime = diseaseCase.getRegistrationTime();
    	this.disease = diseaseCase.getDisease().getTitle();
    	this.action = diseaseCase.getAction();
    }
	
}
