package ru.ifmo.mpi.magichospital.admin.domain.repository;

import org.springframework.data.repository.CrudRepository;

import ru.ifmo.mpi.magichospital.admin.domain.dao.dict.Disease;

public interface DiseaseRepository extends CrudRepository<Disease, String> {

}