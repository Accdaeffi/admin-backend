package ru.ifmo.mpi.magichospital.admin.domain.dto.list;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.ifmo.mpi.magichospital.admin.domain.dto.patient.PatientShortDTO;

@Data
@AllArgsConstructor
public class ListPatientDTO {
	List<PatientShortDTO> patients;
}
