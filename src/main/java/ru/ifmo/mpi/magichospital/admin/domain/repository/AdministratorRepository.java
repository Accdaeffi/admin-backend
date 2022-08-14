package ru.ifmo.mpi.magichospital.admin.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.ifmo.mpi.magichospital.admin.domain.dao.Administrator;

public interface AdministratorRepository extends JpaRepository<Administrator, Integer> {
	Optional<Administrator> findByLogin(String login);
}
