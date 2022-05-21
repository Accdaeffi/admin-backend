package ru.ifmo.mpi.magichospital.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.ifmo.mpi.magichospital.admin.domain.dao.Administrator;
import ru.ifmo.mpi.magichospital.admin.domain.repository.AdministratorRepository;

@Service
public class AdministratorService {

	@Autowired
	AdministratorRepository repository;
	
	public Administrator getAdministratorByLogin(String login) {
		return repository.findByLogin(login).get();
	}
	
}
