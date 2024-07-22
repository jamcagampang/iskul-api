package com.jamsoftwares.iskul.classroom.response;

import java.util.List;

import com.jamsoftwares.iskul.classroom.Classroom;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseMultipleTO {
	private Long userId;
	private List<Classroom> classrooms;
}
