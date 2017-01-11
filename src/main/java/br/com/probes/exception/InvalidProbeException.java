package br.com.probes.exception;


public class InvalidProbeException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public InvalidProbeException(String id) {
		super(id);
	}

}
