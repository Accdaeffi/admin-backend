package ru.ifmo.mpi.magichospital.admin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.rkpunjal.sqlsafe.SqlSafeUtil;

import ru.ifmo.mpi.magichospital.admin.domain.dao.Healer;
import ru.ifmo.mpi.magichospital.admin.domain.repository.HealerRepository;
import ru.ifmo.mpi.magichospital.admin.exception.NoEntityWithSuchIdException;
import ru.ifmo.mpi.magichospital.admin.exception.PossibleSqlInjectionAttackException;

@Service
public class HealerService {

	@Autowired
	HealerRepository healerRepository;

	/**
	 * Получения списка из всех целителей
	 * 
	 * @return Список целителей
	 */
	public List<Healer> getHealers() {	
		List<Healer> result = new ArrayList<>();
		healerRepository.findAll().forEach(result::add);
		return result;
	}

	/**
	 * Получение списка целитей на основании строки поиска
	 * 
	 * @param searchString Строка, разбивающаяся на 2 токена по первому пробелу. Осуществляется как поиск по "имя фамилия", так и "фамилия имя"
	 * @return Список целителей, удовлетворяющий условию поиска
	 * @throws PossibleSqlInjectionAttackException Если в строке поиска есть SQL-инъекция
	 */
	public List<Healer> getHealersByName(String searchString) 
			throws PossibleSqlInjectionAttackException {
		String[] tokens = searchString.split(" ", 2);
		
		String token1 = tokens[0];
		String token2 = tokens.length > 1 ? tokens[1] : "";
		
		if (!SqlSafeUtil.isSqlInjectionSafe(token1) || !SqlSafeUtil.isSqlInjectionSafe(token2)) {
            throw new PossibleSqlInjectionAttackException("Possible sql injection attack!");
        }

		
		token1 = "%"+token1+"%";	
		token2 = "%"+token2+"%";
		
		return healerRepository.findByTokens(token1, token2);
	}
	
	/**
	 * Получение конкретного целителя по id
	 *  
	 * @param healerId
	 * @return
	 * @throws NoEntityWithSuchIdException
	 */
	public Healer getHealer(int healerId) 
			throws NoEntityWithSuchIdException {
		Optional<Healer> optionalHealer = healerRepository.findById(healerId);
		if (optionalHealer.isPresent()) {
			return optionalHealer.get();
		} else {
			throw new NoEntityWithSuchIdException("No healer with such id!");
		}
	}

	
}
