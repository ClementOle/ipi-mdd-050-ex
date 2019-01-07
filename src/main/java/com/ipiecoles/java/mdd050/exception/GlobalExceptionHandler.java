package com.ipiecoles.java.mdd050.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(EntityNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handleEntityNotFoundException(EntityNotFoundException entityNotFoundException) {
		return entityNotFoundException.getMessage();
	}

	@ExceptionHandler(EntityExistsException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	public String handleEntityExistsException(EntityExistsException entityExistsException) {
		return entityExistsException.getMessage();
	}

	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String handleIllegalArgumentException(IllegalArgumentException illegalArgumentException) {
		return illegalArgumentException.getMessage();
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	protected String handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException methodArgumentTypeMismatchException) {
		return "Le type du paramètre " + methodArgumentTypeMismatchException.getName() + " est incorrect pour la valeur " + methodArgumentTypeMismatchException.getValue();
	}

	@ExceptionHandler(ConflictException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	public String handleConflictException(ConflictException conflictException) {
		return conflictException.getMessage();
	}
}
