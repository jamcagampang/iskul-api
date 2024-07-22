package com.jamsoftwares.iskul.account.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
@Entity(name = "USERS")
public class User {

	@Id
	@Column(name = "USER_ID")
	private Long userId;

	@Column(name = "USER_TYPE")
	@Nullable
	private String userType;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "LAST_NAME")
	private String lastName;

	@Column(name = "GENDER")
	@Nullable
	private String gender;

	@Column(name = "DATE_OF_BIRTH")
	@Nullable
	private LocalDateTime dateOfBirth;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "PASSWORD")
	@JsonIgnore
	private String password;

	@Column(name = "PROFILE_PICTURE")
	@Nullable
	private String profilePicture;

	@Column(name = "LAST_LOGIN_DT")
	@Nullable
	private LocalDateTime lastLoginDate;

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
		return "User [userId=" + userId + ", userType=" + userType + ", firstName=" + firstName + ", lastName="
				+ lastName + ", gender=" + gender + ", dateOfBirth=" + dateOfBirth + ", email=" + email
				+ ", profilePicture=" + profilePicture + ", lastLoginDate=" + lastLoginDate + ", createdBy=" + createdBy
				+ ", createdDt=" + createdDt + ", lastModBy=" + lastModBy + ", lastModDt=" + lastModDt
				+ ", softDeleted=" + softDeleted + "]";
	}
}
