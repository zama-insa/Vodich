package com.vodich.dao;

public class DAOException extends Exception {

	private static final long serialVersionUID = 1L;

	private String errorMessage;
	private Exception cause;
	
	public DAOException(String errorMessage, Exception cause) {
		this.errorMessage = errorMessage;
		this.cause = cause;
	}
	public DAOException(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public Exception getCause() {
		return cause;
	}

}
