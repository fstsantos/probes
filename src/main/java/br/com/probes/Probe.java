package br.com.probes;

import static br.com.probes.Direction.E;
import static br.com.probes.Direction.N;
import static br.com.probes.Direction.S;
import static br.com.probes.Direction.W;

import java.util.UUID;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Probe {

	private UUID id = UUID.randomUUID();
	
	private Point position;
	
	private Direction direction;

	public Probe(Point position, Direction direction) {
		this.position = position;
		this.direction = direction;
	}
	
	public Probe(int x, int y, Direction direction) {
		this.position = new Point(x, y);
		this.direction = direction;
	}
	
	public void turnLeft() {
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

	public void turnRight() {
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
	
	public void move() {
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

	public UUID getId() {
		return id;
	}
	
	@Override
	public String toString() {
	   return ToStringBuilder.reflectionToString(this);
	}

}
