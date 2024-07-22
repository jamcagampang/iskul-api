package com.jamsoftwares.iskul.common;

import java.time.LocalDateTime;

public class BaseTO {

	private long createdBy;
	private LocalDateTime createdDt;
	private long lastModBy;
	private LocalDateTime lastModDt;
	private char softDeleted;

	public long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(long createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getCreatedDt() {
		return createdDt;
	}

	public void setCreatedDt(LocalDateTime createdDt) {
		this.createdDt = createdDt;
	}

	public long getLastModBy() {
		return lastModBy;
	}

	public void setLastModBy(long lastModBy) {
		this.lastModBy = lastModBy;
	}

	public LocalDateTime getLastModDt() {
		return lastModDt;
	}

	public void setLastModDt(LocalDateTime lastModDt) {
		this.lastModDt = lastModDt;
	}

	public char getSoftDeleted() {
		return softDeleted;
	}

	public void setSoftDeleted(char softDeleted) {
		this.softDeleted = softDeleted;
	}
}
