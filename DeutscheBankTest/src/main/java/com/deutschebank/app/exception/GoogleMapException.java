package com.deutschebank.app.exception;

/**
 * Created by swaroop on 27/03/2017.
 */
public class GoogleMapException extends RuntimeException {
	private static final long serialVersionUID = 961053772796298601L;

	public GoogleMapException() {
		super("Some went thing wrong while connecting to google services !!");
	}

	public GoogleMapException(String string) {
		super(string);
	}
}