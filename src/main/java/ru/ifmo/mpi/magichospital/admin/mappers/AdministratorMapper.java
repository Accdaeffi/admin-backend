package ru.ifmo.mpi.magichospital.admin.mappers;

import org.springframework.stereotype.Service;

import ru.ifmo.mpi.magichospital.admin.domain.dao.Administrator;
import ru.ifmo.mpi.magichospital.admin.domain.dto.AdministratorLongDTO;

@Service
public class AdministratorMapper {

	public AdministratorLongDTO toLongDTO(Administrator administrator) {
		AdministratorLongDTO dto = new AdministratorLongDTO();
		
		dto.setId(administrator.getId());
    	dto.setName(administrator.getName());
    	dto.setSurname(administrator.getSurname());    	
    	dto.setMale(administrator.isMale());
    	dto.setWorkStartDate(administrator.getWorkStartDate());
    	dto.setSocialStatus(administrator.getSocialStatus().getName());
    	
    	return dto;
	}
}
