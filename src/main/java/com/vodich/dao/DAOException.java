package com.vodich.dao;

public class DAOException extends Exception {

	private String errorMessage;
	private Exception cause;
	public DAOException(String errorMessage, Exception cause) {
		this.errorMessage = errorMessage;
		this.cause = cause;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public Exception getCause() {
		return cause;
	}

}
