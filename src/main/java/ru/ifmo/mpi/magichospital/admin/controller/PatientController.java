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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import ru.ifmo.mpi.magichospital.admin.domain.dao.Patient;
import ru.ifmo.mpi.magichospital.admin.domain.dto.list.ListPatientDTO;
import ru.ifmo.mpi.magichospital.admin.domain.dto.patient.PatientLongDTO;
import ru.ifmo.mpi.magichospital.admin.domain.dto.patient.PatientShortDTO;
import ru.ifmo.mpi.magichospital.admin.exception.NoEntityWithSuchIdException;
import ru.ifmo.mpi.magichospital.admin.exception.PossibleSqlInjectionAttackException;
import ru.ifmo.mpi.magichospital.admin.mappers.PatientMapper;
import ru.ifmo.mpi.magichospital.admin.service.PatientService;
import ru.ifmo.mpi.magichospital.admin.util.PathConstants;

@RestController
public class PatientController {
	
	@Autowired 
	PatientService patientService;
	
	@Autowired
	PatientMapper mapper;
	
	@Operation(summary = "Get list of patients")
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "Get list", 
			    content = { @Content(mediaType = "application/json", 
			      schema = @Schema(implementation = ListPatientDTO.class)) }),
			  @ApiResponse(responseCode = "400", description = "Incorrect values (SQL injection, for example). Full description in \"message\" field", 
			    content = @Content) })
	@GetMapping(PathConstants.API_PREFIX+PathConstants.ADMIN_PREFIX+"/patients")
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
	
	@Operation(summary = "Get specific patient")
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "Get patient", 
			    content = { @Content(mediaType = "application/json", 
			      schema = @Schema(implementation = PatientLongDTO.class)) }),
			  @ApiResponse(responseCode = "400", description = "No patient with such id. Full description in \"message\" field", 
			    content = @Content) })
	@GetMapping(PathConstants.API_PREFIX+PathConstants.ADMIN_PREFIX+"/patient/{id}")
	public PatientLongDTO getPatient(@PathVariable int id) 
			throws NoEntityWithSuchIdException {
		Patient patient = patientService.getPatient(id);
		return mapper.toLongDTO(patient);
	}
	
	@Operation(summary = "Create new patient")
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "Patient created, returning patient with id field", 
			    content = { @Content(mediaType = "application/json", 
			      schema = @Schema(implementation = PatientLongDTO.class)) }),
			  @ApiResponse(responseCode = "400", description = "Incorrect values (SQL injection, for example). Full description in \"message\" field", 
			    content = @Content) })
	@PostMapping(PathConstants.API_PREFIX+PathConstants.ADMIN_PREFIX+"/patients")
	public PatientLongDTO addPatient(@RequestBody PatientLongDTO patient) 
			throws NoEntityWithSuchIdException {
		Patient savedPatient = patientService.addPatient(patient);
		return mapper.toLongDTO(savedPatient);
	}
	
	private List<PatientShortDTO> convertPateintListToPateintShortDTOList(List<Patient> patients) {
		List<PatientShortDTO> patientDTOs =  patients.stream()
				.map(patient -> mapper.toShortDTO(patient))
				.collect(Collectors.toList());
			
		return patientDTOs;
	}
	
}
