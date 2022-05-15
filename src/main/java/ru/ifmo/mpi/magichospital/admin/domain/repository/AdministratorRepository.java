package ru.ifmo.mpi.magichospital.admin.domain.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import ru.ifmo.mpi.magichospital.admin.domain.dao.Administrator;

public interface AdministratorRepository extends CrudRepository<Administrator, String> {
	Optional<Administrator> findByLogin(String login);
}
