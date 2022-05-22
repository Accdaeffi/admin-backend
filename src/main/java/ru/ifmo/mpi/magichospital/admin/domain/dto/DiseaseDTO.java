package ru.ifmo.mpi.magichospital.admin.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DiseaseDTO {

    private int id;
	private String title;
	private String symptoms;
	private String recipe;
}
