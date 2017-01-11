package br.com.probes.exception;

import br.com.probes.Point;

public class InvalidPositionException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public InvalidPositionException(Point position) {
		super(position.toString());
	}

}
