package ru.ifmo.mpi.magichospital.admin.mappers;

import ru.ifmo.mpi.magichospital.admin.domain.dao.Healer;
import ru.ifmo.mpi.magichospital.admin.domain.dto.HealerDTO;

public class HealerMapper {
	
	public static HealerDTO toDTO(Healer healer) {
		HealerDTO dto = new HealerDTO();
		
    	dto.setName(healer.getName());
    	dto.setSurname(healer.getSurname());    	
    	dto.setMale(healer.isMale());
    	dto.setWorkStartDate(healer.getWorkStartDate());
    	dto.setSocialStatus(healer.getSocialStatus().getName());
    	dto.setHealerPower(healer.getHealerPower());
    	dto.setQueue(healer.getHealerPower());
    	
    	return dto;
	}

}
