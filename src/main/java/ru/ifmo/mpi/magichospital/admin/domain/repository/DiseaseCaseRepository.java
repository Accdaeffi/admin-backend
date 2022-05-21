package ru.ifmo.mpi.magichospital.admin.domain.repository;

import org.springframework.data.repository.CrudRepository;

import ru.ifmo.mpi.magichospital.admin.domain.dao.DiseaseCase;

public interface DiseaseCaseRepository extends CrudRepository<DiseaseCase, Integer> {
	
}

