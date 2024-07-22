package com.jamsoftwares.iskul.common.exception;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ValidationField {
	private String fieldName;
	private List<String> messages;
}
