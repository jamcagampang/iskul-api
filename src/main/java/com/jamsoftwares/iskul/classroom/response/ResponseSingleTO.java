package com.jamsoftwares.iskul.classroom.response;

import com.jamsoftwares.iskul.classroom.Classroom;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseSingleTO {
	private Long userId;
	private Classroom classroom;
}
