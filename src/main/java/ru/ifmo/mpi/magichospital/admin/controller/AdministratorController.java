package ru.ifmo.mpi.magichospital.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import ru.ifmo.mpi.magichospital.admin.domain.dao.Administrator;
import ru.ifmo.mpi.magichospital.admin.domain.dto.AdministratorLongDTO;
import ru.ifmo.mpi.magichospital.admin.exception.NoEntityWithSuchIdException;
import ru.ifmo.mpi.magichospital.admin.mappers.AdministratorMapper;
import ru.ifmo.mpi.magichospital.admin.service.AdministratorService;
import ru.ifmo.mpi.magichospital.admin.util.PathConstants;

@RestController
public class AdministratorController {

	@Autowired
	AdministratorService administratorService;
	
	@Autowired
	AdministratorMapper mapper;
	
	@Operation(summary = "Get specific administrator")
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "Get administrator", 
			    content = { @Content(mediaType = "application/json", 
			      schema = @Schema(implementation = AdministratorLongDTO.class)) }),
			  @ApiResponse(responseCode = "400", description = "No administrator with such id. Full description in \"message\" field", 
			    content = @Content) })
	@GetMapping(PathConstants.API_PREFIX+PathConstants.ADMIN_PREFIX+"/administrator/{id}")
	public AdministratorLongDTO getAdministrator(@PathVariable int id) 
			throws NoEntityWithSuchIdException {
		Administrator administrator = administratorService.getAdministrator(id);
		return mapper.toLongDTO(administrator);
	}
	
}
