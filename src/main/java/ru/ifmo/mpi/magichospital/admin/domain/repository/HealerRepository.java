package ru.ifmo.mpi.magichospital.admin.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ru.ifmo.mpi.magichospital.admin.domain.dao.Healer;

public interface HealerRepository extends JpaRepository<Healer, Integer> {

	@Query(value = "select * from healer h where (h.name ILIKE ?1 and (h.surname ILIKE ?2 or (h.surname IS NULL and '%%' = ?2))) or (h.name ILIKE ?2 and (h.surname ILIKE ?1 or (h.surname IS NULL and '%%' = ?1)))", 
			nativeQuery = true)
	List<Healer> findByTokens(String token1, String token2);
}
