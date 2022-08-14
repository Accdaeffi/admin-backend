package ru.ifmo.mpi.magichospital.admin.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.ifmo.mpi.magichospital.admin.domain.dao.Disease;

public interface DiseaseRepository extends JpaRepository<Disease, Integer> {

}