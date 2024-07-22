package com.jamsoftwares.iskul.classroom.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.jamsoftwares.iskul.common.exception.ErrorDetails;

@ControllerAdvice
public class CustomizedClassroomExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ClassroomNotFoundException.class)
	public final ResponseEntity<ErrorDetails> handleUserNotTeacherException(Exception ex, WebRequest request)
			throws Exception {

		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));

		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
	}
}
