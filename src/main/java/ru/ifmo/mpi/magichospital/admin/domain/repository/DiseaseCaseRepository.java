package ru.ifmo.mpi.magichospital.admin.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.ifmo.mpi.magichospital.admin.domain.dao.DiseaseCase;

public interface DiseaseCaseRepository extends JpaRepository<DiseaseCase, Integer> {
	
}

