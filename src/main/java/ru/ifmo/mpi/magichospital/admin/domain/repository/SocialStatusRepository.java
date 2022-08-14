package ru.ifmo.mpi.magichospital.admin.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.ifmo.mpi.magichospital.admin.domain.dao.dict.SocialStatus;

@Repository
public interface SocialStatusRepository extends JpaRepository<SocialStatus, Integer> {
	Optional<SocialStatus> findByCode(String code);
}

