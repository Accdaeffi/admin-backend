package ru.ifmo.mpi.magichospital.admin.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import ru.ifmo.mpi.magichospital.admin.domain.dao.Healer;
import ru.ifmo.mpi.magichospital.admin.domain.dto.healer.HealerLongDTO;
import ru.ifmo.mpi.magichospital.admin.domain.dto.healer.HealerShortDTO;
import ru.ifmo.mpi.magichospital.admin.domain.dto.list.ListHealerDTO;
import ru.ifmo.mpi.magichospital.admin.exception.NoEntityWithSuchIdException;
import ru.ifmo.mpi.magichospital.admin.exception.PossibleSqlInjectionAttackException;
import ru.ifmo.mpi.magichospital.admin.mappers.HealerMapper;
import ru.ifmo.mpi.magichospital.admin.service.HealerService;
import ru.ifmo.mpi.magichospital.admin.util.PathConstants;

@RestController
public class HealerController {
	
	@Autowired 
	HealerService healerService;
	
	@Autowired
	HealerMapper mapper;
	
	// TODO сделать сортировку по очереди и в зависимости от минимальной силы
	
	@Operation(summary = "Get list of healers")
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "Get list", 
			    content = { @Content(mediaType = "application/json", 
			      schema = @Schema(implementation = ListHealerDTO.class)) }),
			  @ApiResponse(responseCode = "400", description = "Incorrect values (SQL injection, for example). Full description in \"message\" field", 
			    content = @Content) })
	@GetMapping(PathConstants.API_PREFIX+PathConstants.ADMIN_PREFIX+"/healers")
	public ListHealerDTO getHealers(@RequestParam(value="name", required = false) String searchString) 
			throws PossibleSqlInjectionAttackException {	
		List<HealerShortDTO> healers;
		
		if (searchString == null) {
			healers = convertPateintListToPateintShortDTOList(healerService.getHealers());
		} else {
			healers = convertPateintListToPateintShortDTOList(healerService.getHealersByName(searchString));
		}
		
		return new ListHealerDTO(healers);
	}
	
	@Operation(summary = "Get specific healer")
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "Get healer", 
			    content = { @Content(mediaType = "application/json", 
			      schema = @Schema(implementation = HealerLongDTO.class)) }),
			  @ApiResponse(responseCode = "400", description = "No healer with such id. Full description in \"message\" field", 
			    content = @Content) })
	@GetMapping(PathConstants.API_PREFIX+PathConstants.ADMIN_PREFIX+"/healer/{id}")
	public HealerLongDTO getHealer(@PathVariable int id) 
			throws NoEntityWithSuchIdException {
		Healer healer = healerService.getHealer(id);
		return mapper.toLongDTO(healer);
	}
	
	private List<HealerShortDTO> convertPateintListToPateintShortDTOList(List<Healer> healers) {
		return healers.stream()
				.map(healer -> mapper.toShortDTO(healer))
				.collect(Collectors.toList());
	}
	
}
