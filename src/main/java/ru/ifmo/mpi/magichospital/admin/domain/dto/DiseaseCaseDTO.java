package ru.ifmo.mpi.magichospital.admin.domain.dto;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DiseaseCaseDTO {
    
    private int id;
    private String registrator;
    private String healer;
    private String patientComplaints;
    private LocalDateTime registrationTime;
    private String disease;
    private String actions;     
	
}
