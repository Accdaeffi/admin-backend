package ru.ifmo.mpi.magichospital.admin.domain.dto.healer;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HealerLongDTO {
	
    private String name;
    private String surname;
    
    @JsonProperty("isMale")
    private boolean isMale;
    private LocalDate workStartDate;
    
    private int healerPower;
    private int queue;
    
    private String socialStatus;

}
