package com.wiley.covid19.model;

public enum CaseOrigin {

	COMMUNITY("COMMUNITY"), IMPORTED("IMPORTED");

	private final String caseOrigin;

	public String getCaseOrigin() {
		return caseOrigin;
	}

	private CaseOrigin(final String caseOrigin) {
		this.caseOrigin = caseOrigin;
	}

}
