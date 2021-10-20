package com.dummy.infrastructure.service;

public class ReportException extends RuntimeException {

	private static final long serialVersionUID = -1881205131059659456L;
	
	public ReportException() {
		super();
	}
	
	public ReportException(String message) {
		super(message);
	}

}
