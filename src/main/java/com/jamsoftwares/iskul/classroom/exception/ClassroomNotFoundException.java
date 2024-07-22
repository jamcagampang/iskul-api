package com.jamsoftwares.iskul.classroom.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ClassroomNotFoundException extends RuntimeException {

	public ClassroomNotFoundException(String message) {
		super(message);
	}
}
