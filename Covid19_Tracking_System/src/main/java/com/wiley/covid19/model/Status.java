package com.wiley.covid19.model;

public enum Status {
	RECOVERED("RECOVERED"), STILL_IN_HOSPITAL("STILL_IN_HOSPITAL"), DEAD("DEAD");

	private final String status;

	private Status(final String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
}
