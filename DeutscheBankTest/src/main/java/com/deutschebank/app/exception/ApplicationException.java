package com.deutschebank.app.exception;

import javax.ws.rs.ext.Provider;

public class ApplicationException extends RuntimeException {
	private static final long serialVersionUID = -6672254138772742602L;

	public ApplicationException() {
		super("Some went thing wrong in back end service !!");
	}

	public ApplicationException(String string) {
		super(string);
	}
}