package ru.ifmo.mpi.magichospital.admin.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ru.ifmo.mpi.magichospital.admin.domain.dao.Patient;
import ru.ifmo.mpi.magichospital.admin.domain.dto.PatientDTO;
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
	public List<PatientDTO> getPatients(@RequestParam(value="name", required = false) String searchString) 
			throws PossibleSqlInjectionAttackException {	
		if (searchString == null) {
			return convertPateintListToPateintShortDTOList(patientService.getPatients());
		} else {
			return convertPateintListToPateintShortDTOList(patientService.getPatientsByName(searchString));
		}
	}
	
	@GetMapping(API_PREFIX+ADMIN_PREFIX+"/patient/{id}")
	public PatientDTO getPatient(@PathVariable int id) 
			throws NoEntityWithSuchIdException {
		Patient patient = patientService.getPatient(id);
		return PatientDTO.fromPatientFullDTO(patient);
	}
	
	private List<PatientDTO> convertPateintListToPateintShortDTOList(List<Patient> patients) {
		List<PatientDTO> patientDTOs =  patients.stream()
				.map(patient -> PatientDTO.fromPatientShortDTO(patient))
				.collect(Collectors.toList());
			
		return patientDTOs;
	}
	
}
