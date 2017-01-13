package br.com.probes.exception;

import br.com.probes.model.position.Point;

public class InvalidPositionException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public InvalidPositionException(Point point) {
		super(point.toString());
	}

	public InvalidPositionException(Point point, String probeId) {
		super(String.format("Position %s is being used by probe id = %s", point, probeId));
	}

}
