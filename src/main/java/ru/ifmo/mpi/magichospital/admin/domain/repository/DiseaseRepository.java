package ru.ifmo.mpi.magichospital.admin.domain.repository;

import org.springframework.data.repository.CrudRepository;

import ru.ifmo.mpi.magichospital.admin.domain.dao.Disease;

public interface DiseaseRepository extends CrudRepository<Disease, Integer> {

}