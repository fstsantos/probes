package br.com.probes.exception;


public class InvalidPlaneException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public InvalidPlaneException(String id) {
		super(id);
	}

}
