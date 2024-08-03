package edu.tus.wizworld.errors;

public class ErrorMessage {
	String error;

	public ErrorMessage(String message) {
		this.error = message;
	}

	public String getErrorMessage() {
		return error;
	}
}
