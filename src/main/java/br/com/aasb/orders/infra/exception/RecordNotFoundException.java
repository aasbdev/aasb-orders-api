package br.com.aasb.orders.infra.exception;

import lombok.Getter;

@Getter
public class RecordNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public RecordNotFoundException() {
		super("Not found");
	}

	public RecordNotFoundException(final String message) {
		super(message);
	}
}