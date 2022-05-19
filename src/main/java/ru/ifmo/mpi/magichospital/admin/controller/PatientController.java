package ru.ifmo.mpi.magichospital.admin.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ru.ifmo.mpi.magichospital.admin.domain.dao.Patient;
import ru.ifmo.mpi.magichospital.admin.domain.dto.ListPatientDTO;
import ru.ifmo.mpi.magichospital.admin.domain.dto.PatientLongDTO;
import ru.ifmo.mpi.magichospital.admin.domain.dto.PatientShortDTO;
import ru.ifmo.mpi.magichospital.admin.exception.NoEntityWithSuchIdException;
import ru.ifmo.mpi.magichospital.admin.exception.PossibleSqlInjectionAttackException;
import ru.ifmo.mpi.magichospital.admin.service.PatientService;

@RestController
public class PatientController {

	private static final String API_PREFIX = "/api/v1";
	private static final String ADMIN_PREFIX = "/admin";
	
	@Autowired 
	PatientService patientService;
	
	@GetMapping(API_PREFIX+ADMIN_PREFIX+"/patients")
	public ListPatientDTO getPatients(@RequestParam(value="name", required = false) String searchString) 
			throws PossibleSqlInjectionAttackException {	
		List<PatientShortDTO> patients;
		
		if (searchString == null) {
			patients = convertPateintListToPateintShortDTOList(patientService.getPatients());
		} else {
			patients = convertPateintListToPateintShortDTOList(patientService.getPatientsByName(searchString));
		}
		
		return new ListPatientDTO(patients);
	}
	
	@GetMapping(API_PREFIX+ADMIN_PREFIX+"/patient/{id}")
	public PatientLongDTO getPatient(@PathVariable int id) 
			throws NoEntityWithSuchIdException {
		Patient patient = patientService.getPatient(id);
		return PatientLongDTO.fromPatientLongDTO(patient);
	}
	
	@PostMapping(API_PREFIX+ADMIN_PREFIX+"/patients")
	public PatientLongDTO addPatient(@RequestBody PatientLongDTO patient) 
			throws NoEntityWithSuchIdException {
		Patient savedPatient = patientService.addPatient(patient);
		return PatientLongDTO.fromPatientLongDTO(savedPatient);
	}
	
	private List<PatientShortDTO> convertPateintListToPateintShortDTOList(List<Patient> patients) {
		List<PatientShortDTO> patientDTOs =  patients.stream()
				.map(patient -> PatientLongDTO.fromPatientShortDTO(patient))
				.collect(Collectors.toList());
			
		return patientDTOs;
	}
	
}
