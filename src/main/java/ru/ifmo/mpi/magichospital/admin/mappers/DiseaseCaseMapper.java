package ru.ifmo.mpi.magichospital.admin.mappers;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.ifmo.mpi.magichospital.admin.domain.dao.Administrator;
import ru.ifmo.mpi.magichospital.admin.domain.dao.DiseaseCase;
import ru.ifmo.mpi.magichospital.admin.domain.dao.Healer;
import ru.ifmo.mpi.magichospital.admin.domain.dao.Patient;
import ru.ifmo.mpi.magichospital.admin.domain.dao.dict.Disease;
import ru.ifmo.mpi.magichospital.admin.domain.dto.DiseaseCaseDTO;
import ru.ifmo.mpi.magichospital.admin.domain.repository.DiseaseRepository;
import ru.ifmo.mpi.magichospital.admin.domain.repository.HealerRepository;
import ru.ifmo.mpi.magichospital.admin.domain.repository.PatientRepository;

@Service
public class DiseaseCaseMapper {

	@Autowired
	HealerRepository healerRepository;
	
	@Autowired
	PatientRepository patientRepository;
	
	@Autowired
	DiseaseRepository diseaseRepository;
	
    public DiseaseCaseDTO toDTO(DiseaseCase diseaseCase) {
    	DiseaseCaseDTO dto = new DiseaseCaseDTO(); 
    	
    	dto.setId(diseaseCase.getId());
    	
    	/*Administrator registrator = diseaseCase.getAdministrator();
    	if (registrator.getSurname() != null) {
    		dto.setRegistrator(String.format("%s %s", registrator.getName(), registrator.getSurname()));
    	} else {
    		dto.setRegistrator(String.format("%s", registrator.getName()));
    	}*/
    	
    	dto.setRegistratorId(diseaseCase.getAdministrator().getId());
    	dto.setHealerId(diseaseCase.getHealer().getId());
    	dto.setPatientId(diseaseCase.getPatient().getId());
    	
    	/*Healer healer = diseaseCase.getHealer();
    	if (healer.getSurname() != null) {
    		dto.setHealer(String.format("%s %s", healer.getName(), healer.getSurname()));
    	} else {
    		dto.setHealer(String.format("%s", healer.getName()));
    	}*/
    
    	dto.setPatientComplaints(diseaseCase.getPatientComplaints());
    	dto.setRegistrationTime(diseaseCase.getRegistrationTime());
    	dto.setDiseaseId(diseaseCase.getDisease().getId());
    	dto.setActions(diseaseCase.getActions());
    	
    	return dto;
    }
    
    public DiseaseCase fromDTO(DiseaseCaseDTO diseaseCaseDto, Administrator admin) 
    		throws NoSuchElementException {
		Optional<Healer> optionalHealer = healerRepository.findById(diseaseCaseDto.getHealerId());
		Optional<Patient> optionalPatient = patientRepository.findById(diseaseCaseDto.getPatientId());
		Optional<Disease> optionalDisease = diseaseRepository.findById(diseaseCaseDto.getDiseaseId());
    	
    	DiseaseCase diseaseCase = new DiseaseCase(); 
    	
    	diseaseCase.setId(diseaseCaseDto.getId());
    	
    	diseaseCase.setAdministrator(admin);
    	diseaseCase.setHealer(optionalHealer.get());
    	diseaseCase.setPatient(optionalPatient.get());
    	
    	diseaseCase.setPatientComplaints(diseaseCaseDto.getPatientComplaints());
    	diseaseCase.setRegistrationTime(diseaseCaseDto.getRegistrationTime());
    	diseaseCase.setDisease(optionalDisease.get());
    	diseaseCase.setActions(diseaseCaseDto.getActions());
    	
    	return diseaseCase;
    }
}
