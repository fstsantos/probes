package br.com.probes.model;

import java.util.UUID;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

import br.com.probes.model.position.Direction;
import br.com.probes.model.position.Point;

@SolrDocument(solrCoreName = "probe")
public class Probe {

	@Id
	@Indexed(name = "id", type = "string")
	private String id = UUID.randomUUID().toString();

	@Indexed(name = "x", type = "int")
	private int x;
	
	@Indexed(name = "y", type = "int")
	private int y;

	@Indexed(name = "direction", type = "string")
	private String direction;

	public Probe() {
	}
	
	public Probe(Point position, Direction direction) {
		this.x = position.getX();
		this.y = position.getY();
		this.direction = direction.toString();
	}
	
	public String getId() {
		return id;
	}

	public Point getPosition() {
		return new Point(x, y);
	}

	public void incX() {
		this.x++;
	}

	public void decX() {
		this.x--;
	}

	public void incY() {
		this.y++;
	}

	public void decY() {
		this.y--;
	}

	public Direction getDirection() {
		return Direction.valueOf(direction);
	}

	public void setDirection(Direction direction) {
		this.direction = direction.toString();
	}

	@Override
	public String toString() {
	   return ToStringBuilder.reflectionToString(this);
	}

}
