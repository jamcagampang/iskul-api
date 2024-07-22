package com.jamsoftwares.iskul.common.exception;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ValidationErrorDetails {
	private LocalDateTime timestamp;
	private String message;
	private String details;
	private List<ValidationField> fields;
}
