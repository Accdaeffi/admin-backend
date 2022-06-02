package ru.ifmo.mpi.magichospital.admin.domain.dto.healer;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class HealerLongDTO extends HealerShortDTO {
    
    @JsonProperty("isMale")
    private boolean isMale;
    private LocalDate workStartDate;
    
    private int healerPower;
    private int queue;
    
    private String socialStatus;

}
