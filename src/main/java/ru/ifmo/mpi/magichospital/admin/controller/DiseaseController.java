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
import ru.ifmo.mpi.magichospital.admin.domain.dao.Disease;
import ru.ifmo.mpi.magichospital.admin.domain.dto.DiseaseDTO;
import ru.ifmo.mpi.magichospital.admin.exception.NoEntityWithSuchIdException;
import ru.ifmo.mpi.magichospital.admin.mappers.DiseaseMapper;
import ru.ifmo.mpi.magichospital.admin.service.DiseaseService;
import ru.ifmo.mpi.magichospital.admin.util.PathConstants;

@RestController
public class DiseaseController {
	
	@Autowired
	DiseaseService diseaseService;
	
	@Autowired
	DiseaseMapper mapper;
	
	@Operation(summary = "Get specific disease")
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "Get disease", 
			    content = { @Content(mediaType = "application/json", 
			      schema = @Schema(implementation = DiseaseDTO.class)) }),
			  @ApiResponse(responseCode = "400", description = "No disease with such id. Full description in \"message\" field", 
			    content = @Content) })
	@GetMapping(PathConstants.API_PREFIX+PathConstants.ADMIN_PREFIX+"/disease/{id}")
	public DiseaseDTO getDisease(@PathVariable int id) 
			throws NoEntityWithSuchIdException {
		Disease disease = diseaseService.getDisease(id);
		return mapper.toDTO(disease);
	}
}
