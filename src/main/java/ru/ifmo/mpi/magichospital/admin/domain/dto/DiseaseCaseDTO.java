package ru.ifmo.mpi.magichospital.admin.domain.dto;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DiseaseCaseDTO {
    
    private int id;
    private int registratorId;
    private int healerId;
    private int patientId;
    private int diseaseId;
    private String patientComplaints;
    private LocalDateTime registrationTime;
    private String actions;     
	
}
