package ru.ifmo.mpi.magichospital.admin.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.ifmo.mpi.magichospital.admin.domain.dao.Disease;
import ru.ifmo.mpi.magichospital.admin.domain.repository.DiseaseRepository;
import ru.ifmo.mpi.magichospital.admin.exception.NoEntityWithSuchIdException;

@Service
public class DiseaseService {

	@Autowired
	DiseaseRepository repository;
	
	public Disease getDisease(int diseaseId) 
			throws NoEntityWithSuchIdException {
		Optional<Disease> optionalDisease = repository.findById(diseaseId);
		if (optionalDisease.isPresent()) {
			return optionalDisease.get();
		} else {
			throw new NoEntityWithSuchIdException("No disease with such id!");
		}
	}
}
