package com.jamsoftwares.iskul.classroom.request;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClassroomRequestTO {

	@NotNull(message = "Please provide classroom name.")
	@Size(max = 50, message = "Classroom name can be up to 50 characters only.")
	private String name;

	@Nullable
	@Size(max = 1000, message = "Classroom description can be up to 1000 characters only.")
	private String description;

	@Nullable
	@JsonFormat(pattern = "MM-dd-yyyy HH:mm:ss")
	@FutureOrPresent(message = "Classroom expiry date must be a future date.")
	private LocalDateTime expiryDate;
}
