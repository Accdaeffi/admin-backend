package ru.ifmo.mpi.magichospital.admin.domain.dto;

import java.time.LocalDate;

import lombok.Getter;
import ru.ifmo.mpi.magichospital.admin.domain.dao.Healer;

@Getter
public class HealerDTO {
	
    private String name;
    private String surname;
    private boolean isMale;
    private LocalDate workStartDate;
    
    private int healerPower;
    private int queue;
    
    private String socialStatus;
	
	public HealerDTO(Healer healer) {
    	this.name = healer.getName();
    	this.surname = healer.getSurname();    	
    	this.isMale = healer.isMale();
    	this.workStartDate = healer.getWorkStartDate();
    	this.socialStatus = healer.getSocialStatus().getName();
    	this.healerPower = healer.getHealerPower();
    	this.queue = healer.getHealerPower();
	}

}
