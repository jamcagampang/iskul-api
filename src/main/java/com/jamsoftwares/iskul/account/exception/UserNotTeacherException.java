package com.jamsoftwares.iskul.account.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class UserNotTeacherException extends RuntimeException {

	public UserNotTeacherException(String message) {
		super(message);
	}
}
