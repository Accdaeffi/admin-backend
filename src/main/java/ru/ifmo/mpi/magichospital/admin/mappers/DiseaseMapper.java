package ru.ifmo.mpi.magichospital.admin.mappers;

import org.springframework.stereotype.Service;

import ru.ifmo.mpi.magichospital.admin.domain.dao.Disease;
import ru.ifmo.mpi.magichospital.admin.domain.dto.DiseaseDTO;

@Service
public class DiseaseMapper {

	public DiseaseDTO toDTO(Disease disease) {
		DiseaseDTO dto = new DiseaseDTO();
		
		dto.setId(disease.getId());
    	dto.setRecipe(disease.getRecipe());
    	dto.setSymptoms(disease.getSymptoms());    	
    	dto.setTitle(disease.getTitle());
    	
    	return dto;
	}
}
