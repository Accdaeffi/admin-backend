package ru.ifmo.mpi.magichospital.admin.mappers;

import org.springframework.stereotype.Service;

import ru.ifmo.mpi.magichospital.admin.domain.dao.Administrator;
import ru.ifmo.mpi.magichospital.admin.domain.dao.DiseaseCase;
import ru.ifmo.mpi.magichospital.admin.domain.dao.Healer;
import ru.ifmo.mpi.magichospital.admin.domain.dto.DiseaseCaseDTO;

@Service
public class DiseaseCaseMapper {

    public DiseaseCaseDTO toDTO(DiseaseCase diseaseCase) {
    	DiseaseCaseDTO dto = new DiseaseCaseDTO(); 
    	
    	dto.setId(diseaseCase.getId());
    	
    	Administrator registrator = diseaseCase.getAdministrator();
    	if (registrator.getSurname() != null) {
    		dto.setRegistrator(String.format("%s %s", registrator.getName(), registrator.getSurname()));
    	} else {
    		dto.setRegistrator(String.format("%s", registrator.getName()));
    	}
    	
    	Healer healer = diseaseCase.getHealer();
    	if (healer.getSurname() != null) {
    		dto.setHealer(String.format("%s %s", healer.getName(), healer.getSurname()));
    	} else {
    		dto.setHealer(String.format("%s", healer.getName()));
    	}
    
    	dto.setPatientComplaints(diseaseCase.getPatientComplaints());
    	dto.setRegistrationTime(diseaseCase.getRegistrationTime());
    	dto.setDisease(diseaseCase.getDisease().getTitle());
    	dto.setActions(diseaseCase.getActions());
    	
    	return dto;
    }
}
