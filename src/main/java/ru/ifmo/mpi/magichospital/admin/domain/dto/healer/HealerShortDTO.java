package ru.ifmo.mpi.magichospital.admin.domain.dto.healer;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HealerShortDTO {
	
	private int id;
    private String name;
    private String surname;

}
