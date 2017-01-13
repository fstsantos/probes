package br.com.probes.plane;

import static br.com.probes.plane.position.Direction.E;
import static br.com.probes.plane.position.Direction.N;
import static br.com.probes.plane.position.Direction.S;
import static br.com.probes.plane.position.Direction.W;

import java.util.UUID;

import org.apache.commons.lang3.builder.ToStringBuilder;

import br.com.probes.plane.position.Direction;
import br.com.probes.plane.position.Point;

public class Probe {

	private String id = UUID.randomUUID().toString();
	
	private Point position;
	
	private Direction direction;

	protected Probe(Point position, Direction direction) {
		this.position = position;
		this.direction = direction;
	}
	
	protected void turnLeft() {
		switch(this.direction) {
		case N:
			this.direction = W;
			break;
		case W:
			this.direction = S;
			break;
		case S:
			this.direction = E;
			break;
		case E:
			this.direction = N;
			break;
		}
	}

	protected void turnRight() {
		switch(this.direction) {
		case N:
			this.direction = E;
			break;
		case W:
			this.direction = N;
			break;
		case S:
			this.direction = W;
			break;
		case E:
			this.direction = S;
			break;
		}
	}
	
	protected void move() {
		switch(this.direction) {
		case N:
			this.position.incY();
			break;
		case W:
			this.position.decX();
			break;
		case S:
			this.position.decY();
			break;
		case E:
			this.position.incX();
			break;
		}
	}

	protected Point nextMove() {
		switch(this.direction) {
		case N:
			return new Point(this.position.getX(), this.position.getY() + 1);
		case W:
			return new Point(this.position.getX() - 1, this.position.getY());
		case S:
			return new Point(this.position.getX(), this.position.getY() - 1);
		case E:
			return new Point(this.position.getX() + 1, this.position.getY());
		}
		return null;
	}

	public String getId() {
		return id;
	}

	public Point getPosition() {
		return position;
	}

	public Direction getDirection() {
		return direction;
	}
	
	@Override
	public String toString() {
	   return ToStringBuilder.reflectionToString(this);
	}

}
