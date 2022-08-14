package ru.ifmo.mpi.magichospital.admin.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.ifmo.mpi.magichospital.admin.domain.dao.LoginInfo;

@Repository
public interface LoginInfoRepository extends JpaRepository<LoginInfo, String> {
	Optional<LoginInfo> findByLogin(String login);
}