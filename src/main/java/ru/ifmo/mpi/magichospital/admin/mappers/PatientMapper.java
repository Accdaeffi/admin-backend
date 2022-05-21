package ru.ifmo.mpi.magichospital.admin.mappers;

import java.util.stream.Collectors;

import ru.ifmo.mpi.magichospital.admin.domain.dao.Patient;
import ru.ifmo.mpi.magichospital.admin.domain.dao.dict.SocialStatus;
import ru.ifmo.mpi.magichospital.admin.domain.dto.PatientLongDTO;
import ru.ifmo.mpi.magichospital.admin.domain.dto.PatientShortDTO;

public class PatientMapper {

    public static PatientShortDTO toShortDTO(Patient patient) {
    	PatientShortDTO patientDTO = new PatientShortDTO(); 
    	
    	patientDTO.setId(patient.getId());   
    	patientDTO.setName(patient.getName()); 
    	patientDTO.setSurname(patient.getSurname()); 
    	
    	return patientDTO;
    }
	
    public static PatientLongDTO toLongDTO(Patient patient) {
    	PatientLongDTO patientDTO = new PatientLongDTO(); 
    	
    	patientDTO.setId(patient.getId());   
    	patientDTO.setName(patient.getName()); 
    	patientDTO.setSurname(patient.getSurname());    	
    	patientDTO.setSocialStatus(patient.getSocialStatus().getCode());
    	patientDTO.setMale(patient.isMale());
    	patientDTO.setMage(patient.isMage());
    	patientDTO.setRegistrationTime(patient.getRegistrationTime());
    	

    	patientDTO.setDiseaseCases(
    			patient.getDiseaseCases().stream()
    				.map(disease -> DiseaseCaseMapper.toDTO(disease))
    				.collect(Collectors.toList())
    	);
    	
    	return patientDTO;
    }
	
    public static Patient fromDTO(PatientLongDTO patientDto, SocialStatus status) {
    	Patient dao = new Patient();
    	
    	dao.setName(patientDto.getName());
    	dao.setSurname(patientDto.getSurname());
    	dao.setMale(patientDto.isMale());
    	dao.setMage(patientDto.isMage());
    	dao.setRegistrationTime(patientDto.getRegistrationTime());
    	dao.setSocialStatus(status);
    	
    	return dao;
	}
    
}
