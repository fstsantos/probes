package br.com.probes.exception;

import br.com.probes.plane.Probe;
import br.com.probes.plane.position.Point;

public class InvalidPositionException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public InvalidPositionException(Point point) {
		super(point.toString());
	}

	public InvalidPositionException(Point point, Probe usingProbe) {
		super(String.format("Position %s is being used by probe id = %s", point, usingProbe.getId()));
	}

}
