package ru.ifmo.mpi.magichospital.admin.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.ifmo.mpi.magichospital.admin.domain.dao.Administrator;
import ru.ifmo.mpi.magichospital.admin.domain.repository.AdministratorRepository;
import ru.ifmo.mpi.magichospital.admin.exception.NoEntityWithSuchIdException;

@Service
public class AdministratorService {

	@Autowired
	AdministratorRepository repository;
	
	/**
	 * Получение администратора по id of
	 * 
	 * @param administratorId
	 * @return
	 * @throws NoEntityWithSuchIdException
	 */
	public Administrator getAdministrator(int administratorId) 
			throws NoEntityWithSuchIdException {
		Optional<Administrator> optionalAdministrator = repository.findById(administratorId);
		if (optionalAdministrator.isPresent()) {
			return optionalAdministrator.get();
		} else {
			throw new NoEntityWithSuchIdException("No administrator with such id!");
		}
	}
	
	public Administrator getAdministratorByLogin(String login) {
		return repository.findByLogin(login).get();
	}
	
}
