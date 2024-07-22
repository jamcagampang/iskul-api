package com.jamsoftwares.iskul.classroom;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "CLASSROOMS")
public class Classroom {

	@Id
	@Column(name = "CLASSROOM_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "classroomSeqGen")
	@SequenceGenerator(name = "classroomSeqGen", sequenceName = "CLASSROOM_SQ01", allocationSize = 1)
	private Long id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "DESCRIPTION")
	@Nullable
	private String description;

	@Column(name = "EXP_DT")
	@Nullable
	private LocalDateTime expiryDate;

	@Column(name = "TEACHER_ID")
	private Long teacherId;

	@Column(name = "CREATED_BY")
	@JsonIgnore
	private Long createdBy;

	@Column(name = "CREATED_DT")
	@JsonIgnore
	private LocalDateTime createdDt;

	@Column(name = "LAST_MOD_BY")
	@JsonIgnore
	@Nullable
	private Long lastModBy;

	@Column(name = "LAST_MOD_DT")
	@JsonIgnore
	@Nullable
	private LocalDateTime lastModDt;

	@Column(name = "SOFT_DELETED")
	@JsonIgnore
	private String softDeleted;

	@Override
	public String toString() {
		return "Classroom [id=" + id + ", name=" + name + ", description=" + description + ", expiryDate=" + expiryDate
				+ ", teacherId=" + teacherId + ", createdBy=" + createdBy + ", createdDt=" + createdDt + ", lastModBy="
				+ lastModBy + ", lastModDt=" + lastModDt + ", softDeleted=" + softDeleted + "]";
	}
	
}
