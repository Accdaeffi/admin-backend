package ru.ifmo.mpi.magichospital.admin.service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.ifmo.mpi.magichospital.admin.domain.dao.Administrator;
import ru.ifmo.mpi.magichospital.admin.domain.dao.DiseaseCase;
import ru.ifmo.mpi.magichospital.admin.domain.dto.DiseaseCaseDTO;
import ru.ifmo.mpi.magichospital.admin.domain.repository.AdministratorRepository;
import ru.ifmo.mpi.magichospital.admin.domain.repository.DiseaseCaseRepository;
import ru.ifmo.mpi.magichospital.admin.exception.NoEntityWithSuchIdException;
import ru.ifmo.mpi.magichospital.admin.mappers.DiseaseCaseMapper;

@Service
public class DiseaseCaseService {

	@Autowired
	DiseaseCaseRepository diseaseCaseRepository;

	@Autowired
	AdministratorRepository administratorRepository;

	@Autowired
	DiseaseCaseMapper mapper;

	public DiseaseCase addCase(DiseaseCaseDTO diseaseCaseDTO, String loggedAdministrator) 
			throws NoEntityWithSuchIdException {
		Optional<Administrator> optionalAdministrator = administratorRepository.findById(diseaseCaseDTO.getRegistratorId());
		Administrator admin = optionalAdministrator.get();
		
		try {
			if (admin.getLogin().equals(loggedAdministrator)) {
				DiseaseCase diseaseCase = mapper.fromDTO(diseaseCaseDTO, admin);
				diseaseCase.setId(0);
					
				if (diseaseCase.getRegistrationTime() == null) {
					diseaseCase.setRegistrationTime(LocalDateTime.now());
				}
					
				diseaseCase = diseaseCaseRepository.save(diseaseCase);
				return diseaseCase;
			} else {
				throw new SecurityException("Forbidden");
			}
		}
		catch (NoSuchElementException ex) 
		{
			throw new NoEntityWithSuchIdException("No healer, patient or disease with such id!");
		}
	}
}
