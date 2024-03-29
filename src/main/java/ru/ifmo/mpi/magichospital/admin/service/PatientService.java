package ru.ifmo.mpi.magichospital.admin.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.rkpunjal.sqlsafe.SqlSafeUtil;

import ru.ifmo.mpi.magichospital.admin.domain.dao.Patient;
import ru.ifmo.mpi.magichospital.admin.domain.dao.dict.SocialStatus;
import ru.ifmo.mpi.magichospital.admin.domain.dto.patient.PatientLongDTO;
import ru.ifmo.mpi.magichospital.admin.domain.repository.PatientRepository;
import ru.ifmo.mpi.magichospital.admin.domain.repository.SocialStatusRepository;
import ru.ifmo.mpi.magichospital.admin.exception.NoEntityWithSuchIdException;
import ru.ifmo.mpi.magichospital.admin.exception.PossibleSqlInjectionAttackException;
import ru.ifmo.mpi.magichospital.admin.mappers.PatientMapper;

@Service
public class PatientService {
	
	@Autowired
	PatientRepository patientRepository;
	
	@Autowired
	SocialStatusRepository socialStatusRepository;
	
	@Autowired 
	PatientMapper patientMapper;

	/**
	 * Получение списка из всех пациентов
	 * 
	 * @return Список пациентов
	 */
	public List<Patient> getPatients() {
		
		List<Patient> result = new ArrayList<>();
		patientRepository.findAll().forEach(result::add);
		
		return result;
	}
	
	/**
	 * Получение списка из всех пациентов на основании строки поиска 
	 * 
	 * @param searchString Строка, разбивающаяся на 2 токена по первому пробелу. Осуществляется как поиск по "имя фамилия", так и "фамилия имя"
	 * @return Список пациентов, удовлетворяющий условию поиска
	 * @throws PossibleSqlInjectionAttackException Если в строке поиска есть SQL-инъекция
	 */
	public List<Patient> getPatientsByName(String searchString) 
			throws PossibleSqlInjectionAttackException {
		String[] tokens = searchString.split(" ", 2);
		
		String token1 = tokens[0];
		String token2 = tokens.length > 1 ? tokens[1] : "";
		
		if (!SqlSafeUtil.isSqlInjectionSafe(token1) || !SqlSafeUtil.isSqlInjectionSafe(token2)) {
            throw new PossibleSqlInjectionAttackException("Possible sql injection attack!");
        }

		
		token1 = "%"+token1+"%";	
		token2 = "%"+token2+"%";
		
		return patientRepository.findByTokens(token1, token2);
	}

	/**
	 * Получение пациента по id
	 * 
	 * @param patientId
	 * @return
	 * @throws NoEntityWithSuchIdException
	 */
	public Patient getPatient(int patientId) 
			throws NoEntityWithSuchIdException {
		Optional<Patient> optionalPatient = patientRepository.findById(patientId);
		if (optionalPatient.isPresent()) {
			return optionalPatient.get();
		} else {
			throw new NoEntityWithSuchIdException("No patient with such id!");
		}
	}
	
	/**
	 * Добавление нового пациента
	 * 
	 * @param patientDto Полученное DTO с описанием пациента
	 * @return Зарегестрированного пациента из БД
	 * @throws NoEntityWithSuchIdException Если нет такого социального статуса
	 */
	public Patient addPatient(PatientLongDTO patientDto) 
			throws NoEntityWithSuchIdException {
		
		Optional<SocialStatus> optionalStatus = socialStatusRepository.findByCode(patientDto.getSocialStatus());
		
		if (optionalStatus.isPresent()) {
			Patient patient = patientMapper.fromDTO(patientDto, optionalStatus.get());
			patient.setId(0);
			
			if (patient.getRegistrationTime() == null) {
				patient.setRegistrationTime(LocalDateTime.now());
			}
			
			patient = patientRepository.save(patient);
			return patient;
		} else {
			throw new NoEntityWithSuchIdException("No social status with such code!");
		}
	}

}
