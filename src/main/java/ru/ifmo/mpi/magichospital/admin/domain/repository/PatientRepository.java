package ru.ifmo.mpi.magichospital.admin.domain.repository;

import org.springframework.data.repository.CrudRepository;

import ru.ifmo.mpi.magichospital.admin.domain.dao.Patient;

public interface PatientRepository extends CrudRepository<Patient, String> {

}
