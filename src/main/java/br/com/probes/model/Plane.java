package br.com.probes.model;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Dynamic;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

import br.com.probes.model.position.Point;

@SolrDocument(solrCoreName = "plane")
public class Plane {
	
	@Id
	@Indexed(name = "id", type = "string")
	private String id = UUID.randomUUID().toString();

	@Indexed(name = "bottom_x", type = "int")
	private int bottomX = 0;

	@Indexed(name = "bottom_y", type = "int")
	private int bottomY = 0;
	
	@Indexed(name = "upper_x", type = "int")
	private int upperX;
	
	@Indexed(name = "upper_y", type = "int")
	private int upperY;

	@Dynamic @Field("*_probe_pos")
	private Map<String, String> positionMap = new HashMap<String, String>();

	public Plane() {
		this.upperX = 100;
		this.upperY = 100;
	}
	
	public Plane(Point upperLimit) {
		this.upperX = upperLimit.getX();
		this.upperY = upperLimit.getY();
	}
	
	public Plane(int x, int y) {
		this.upperX = x;
		this.upperY = y;
	}
	
	public String getId() {
		return id;
	}

	public Point getBottomLimit() {
		return new Point(bottomX, bottomY);
	}

	public void setBottomLimit(Point bottomLimit) {
		this.bottomX = bottomLimit.getX();
		this.bottomY = bottomLimit.getY();
	}

	public Point getUpperLimit() {
		return new Point(upperX, upperY);
	}

	public void setUpperLimit(Point upperLimit) {
		this.upperX = upperLimit.getX();
		this.upperY = upperLimit.getY();
	}

	public Map<String, String> getPositionMap() {
		return positionMap;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}
