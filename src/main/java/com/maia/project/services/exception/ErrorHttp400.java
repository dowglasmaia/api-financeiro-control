package com.maia.project.services.exception;

public class ErrorHttp400 extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ErrorHttp400(String msg) {
		super(msg);
	}

	// Sobrecargo do Contrutor passando a causa de exception
	public ErrorHttp400(String msg, Throwable cause) {
		super(msg, cause);
	}
}