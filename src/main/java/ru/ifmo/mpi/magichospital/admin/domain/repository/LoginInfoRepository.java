package ru.ifmo.mpi.magichospital.admin.domain.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ru.ifmo.mpi.magichospital.admin.domain.dao.LoginInfo;

@Repository
public interface LoginInfoRepository extends CrudRepository<LoginInfo, Integer> {
	Optional<LoginInfo> findByLogin(String login);
}