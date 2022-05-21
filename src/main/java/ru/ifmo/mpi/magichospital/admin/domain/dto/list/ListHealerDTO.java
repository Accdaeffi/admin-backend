package ru.ifmo.mpi.magichospital.admin.domain.dto.list;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.ifmo.mpi.magichospital.admin.domain.dto.healer.HealerShortDTO;

@Data
@AllArgsConstructor
public class ListHealerDTO {
	
	List<HealerShortDTO> healers; 
	
}
