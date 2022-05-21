package ru.ifmo.mpi.magichospital.admin.mappers;

import org.springframework.stereotype.Service;

import ru.ifmo.mpi.magichospital.admin.domain.dao.Healer;
import ru.ifmo.mpi.magichospital.admin.domain.dto.healer.HealerLongDTO;
import ru.ifmo.mpi.magichospital.admin.domain.dto.healer.HealerShortDTO;

@Service
public class HealerMapper {
	
	public HealerShortDTO toShortDTO(Healer healer) {
		HealerShortDTO dto = new HealerShortDTO();
		
    	dto.setName(healer.getName());
    	dto.setSurname(healer.getSurname());    
    	
    	return dto;
	}
	
	public HealerLongDTO toLongDTO(Healer healer) {
		HealerLongDTO dto = new HealerLongDTO();
		
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
