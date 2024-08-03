package edu.tus.wizworld.exceptions;

public class BookValidationException extends BookException {

	private static final long serialVersionUID = 334051992916748022L;

	public BookValidationException(final String errorMessage) {
		super(errorMessage);
	}

}

