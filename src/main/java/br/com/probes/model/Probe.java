package br.com.probes.model;

import java.util.UUID;

import org.apache.commons.lang3.builder.ToStringBuilder;

import br.com.probes.model.position.Direction;
import br.com.probes.model.position.Point;
import br.com.probes.solr.document.ProbeDocument;

public class Probe {

	private String id = UUID.randomUUID().toString();
	
	private Point position;
	
	private Direction direction;

	public Probe(Point position, Direction direction) {
		this.position = position;
		this.direction = direction;
	}
	
	public Probe(ProbeDocument probeDocument) {
		this.position = new Point(probeDocument.getX(), probeDocument.getY());
		this.direction = Direction.valueOf(probeDocument.getDirection());
	}
	
	public String getId() {
		return id;
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	@Override
	public String toString() {
	   return ToStringBuilder.reflectionToString(this);
	}

}
