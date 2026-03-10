package com.ochanhyeok.board.global.error;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException e) {
		ErrorCode errorCode = e.getErrorCode();
		ErrorResponse errorResponse = ErrorResponse.of(errorCode);

		return ResponseEntity.status(errorResponse.getStatus()).body(errorResponse);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class) // spring이 던지는 예외라 ErrorCode가 없음
	public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
		ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE, fieldErrors.get(0).getDefaultMessage());

		return ResponseEntity.status(errorResponse.getStatus()).body(errorResponse);
	}
}
