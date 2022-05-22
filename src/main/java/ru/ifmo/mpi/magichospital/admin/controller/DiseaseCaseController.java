package ru.ifmo.mpi.magichospital.admin.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import ru.ifmo.mpi.magichospital.admin.domain.dao.DiseaseCase;
import ru.ifmo.mpi.magichospital.admin.domain.dto.DiseaseCaseDTO;
import ru.ifmo.mpi.magichospital.admin.exception.NoEntityWithSuchIdException;
import ru.ifmo.mpi.magichospital.admin.mappers.DiseaseCaseMapper;
import ru.ifmo.mpi.magichospital.admin.service.AdministratorService;
import ru.ifmo.mpi.magichospital.admin.service.DiseaseCaseService;
import ru.ifmo.mpi.magichospital.admin.util.PathConstants;

@RestController
public class DiseaseCaseController {
	
	@Autowired
	DiseaseCaseService diseaseCaseService;	
	
	@Autowired
	AdministratorService administratorService;	
	
	@Autowired
	DiseaseCaseMapper mapper;
	
	@Operation(summary = "Create new disease case")
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "Case created, returning disease case with id field", 
			    content = { @Content(mediaType = "application/json", 
			      schema = @Schema(implementation = DiseaseCaseDTO.class)) }),
			  @ApiResponse(responseCode = "401", description = "Trying register as another registrator", 
			    content = @Content),
			  @ApiResponse(responseCode = "400", description = "Incorrect values (SQL injection, for example). Full description in \"message\" field", 
			    content = @Content) })
	@PostMapping(PathConstants.API_PREFIX+PathConstants.ADMIN_PREFIX+"/case")
	public DiseaseCaseDTO addPatient(@RequestBody DiseaseCaseDTO diseaseCase, Principal loggedAdministrator) 
			throws NoEntityWithSuchIdException {
		
		DiseaseCase savedCase = diseaseCaseService.addCase(diseaseCase, loggedAdministrator.getName());
		return mapper.toDTO(savedCase);
	}
	
}
