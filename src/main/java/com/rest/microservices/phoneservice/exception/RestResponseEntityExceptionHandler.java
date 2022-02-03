package com.rest.microservices.phoneservice.exception;

import java.util.*;
import java.util.LinkedHashMap;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Exception handler class to handle exceptions and return custom message.
 * 
 * 
 * @author Gaurav Tiwari
 *
 */

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception exception, WebRequest webRequest){
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), 
				exception.getMessage(), webRequest.getDescription(false));
		
		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException exception, WebRequest webRequest){
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), 
				"User Not Found", webRequest.getDescription(false));
		
		return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(PhoneNotFoundException.class)
	public final ResponseEntity<Object> handlePhoneNotFoundException(PhoneNotFoundException exception, WebRequest webRequest){
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), 
				"Phone Not Found", webRequest.getDescription(false));
		
		return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
	}
	
	@Override
	public ResponseEntity<Object> handleHttpMessageNotReadable(
			HttpMessageNotReadableException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {

		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), 
				"Validation Failed", exception.getMostSpecificCause().getMessage());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus status, WebRequest webRequest) {

		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), 
				"Validation Failed", exception.getBindingResult().toString());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	

}
