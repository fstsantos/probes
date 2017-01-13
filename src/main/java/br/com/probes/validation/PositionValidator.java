package br.com.probes.validation;

import org.springframework.stereotype.Component;

import br.com.probes.exception.InvalidPositionException;
import br.com.probes.model.Plane;
import br.com.probes.model.position.Point;

@Component
public class PositionValidator {

	public void validateMove(Point point, Plane plane) throws InvalidPositionException {
		validateBoundaries(point, plane);
		validatePosition(point, plane);
	}

	private void validateBoundaries(Point point, Plane plane) throws InvalidPositionException {
		if (point.getX() < plane.getBottomLimit().getX() ||
			point.getY() < plane.getBottomLimit().getY() ||
			point.getX() > plane.getUpperLimit().getX() ||
			point.getY() > plane.getUpperLimit().getY()) {
			
			throw new InvalidPositionException(point);
		}
	}

	private void validatePosition(Point point, Plane plane) throws InvalidPositionException {
		if (plane.getPositionMap().get(point) != null) {
			throw new InvalidPositionException(point, plane.getPositionMap().get(point));
		}
	}
	
}
