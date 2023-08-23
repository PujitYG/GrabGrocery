package com.microservice.authservice.Exception;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.microservice.authservice.DTO.ExceptionDTO;

import io.jsonwebtoken.security.SignatureException;

@RestControllerAdvice
public class ApplicationExceptionHandler {

	
	@ExceptionHandler(value = UserAlreadyExistException.class)
	public ResponseEntity<ExceptionDTO> userAlreadyExist(UserAlreadyExistException exe){
		return exe.getResponseEntity();
	}
	
	@ExceptionHandler(value=MethodArgumentNotValidException.class)
	public ResponseEntity<ExceptionDTO> illegalArgumentException(MethodArgumentNotValidException ex, HttpServletRequest request){
		ExceptionDTO dto = new ExceptionDTO();
		
		dto.setError("BAD REQUEST");
		dto.setMessage("Please check the details");
		dto.setStatusCode(400);
		dto.setTimeStamp(LocalDateTime.now());
		dto.setPath(request.getRequestURI());
		
		return ResponseEntity.badRequest()
				.body(dto);
				
	}
	

	@ExceptionHandler(value = BadCredentialsException.class)
	public ResponseEntity<ExceptionDTO> exception(BadCredentialsException exe){
		ExceptionDTO dto = new ExceptionDTO();
		dto.setError("forbidden");
		dto.setMessage(exe.getMessage());
		dto.setTimeStamp(LocalDateTime.now());
		dto.setStatusCode(500);
		return ResponseEntity
				.status(500)
				.body(dto);
	}
	
	@ExceptionHandler(value = AccessDeniedException.class)
	public ResponseEntity<ExceptionDTO> exception1(AccessDeniedException exe){
		ExceptionDTO dto = new ExceptionDTO();
		dto.setError("forbidden");
		dto.setMessage(exe.getMessage());
		dto.setTimeStamp(LocalDateTime.now());
		dto.setStatusCode(500);
		return ResponseEntity
				.status(500)
				.body(dto);
	}
	
	@ExceptionHandler(value = SignatureException.class)
	public ResponseEntity<ExceptionDTO> signatureException(SignatureException exe){
		ExceptionDTO dto = new ExceptionDTO();
		dto.setError("forbidden");
		dto.setMessage("JWT is not valid");
		dto.setTimeStamp(LocalDateTime.now());
		dto.setStatusCode(402);
		return ResponseEntity
				.status(402)
				.body(dto);
	} 
	
	
}
