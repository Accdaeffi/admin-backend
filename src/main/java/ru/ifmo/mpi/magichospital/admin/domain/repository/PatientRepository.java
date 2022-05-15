package ru.ifmo.mpi.magichospital.admin.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ru.ifmo.mpi.magichospital.admin.domain.dao.Patient;

public interface PatientRepository extends CrudRepository<Patient, Integer> {
	
	@Query(value = "select * from patient p where (p.name ILIKE ?1 and (p.surname ILIKE ?2 or (p.surname IS NULL and '%%' = ?2))) or (p.name ILIKE ?2 and (p.surname ILIKE ?1 or (p.surname IS NULL and '%%' = ?1)))", 
			nativeQuery = true)
	List<Patient> findByTokens(String token1, String token2);

}
