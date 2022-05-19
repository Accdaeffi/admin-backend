package ru.ifmo.mpi.magichospital.admin.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import ru.ifmo.mpi.magichospital.admin.exception.NoEntityWithSuchIdException;
import ru.ifmo.mpi.magichospital.admin.exception.PossibleSqlInjectionAttackException;

@ControllerAdvice
public class Advicer {
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NoEntityWithSuchIdException.class)
    public ResponseEntity<Object> handleException(NoEntityWithSuchIdException e) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("message", e.getMessage());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
    
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PossibleSqlInjectionAttackException.class)
    public ResponseEntity<Object> handleException(PossibleSqlInjectionAttackException e) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("message", e.getMessage());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

}
