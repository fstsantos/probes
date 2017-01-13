package br.com.probes.model;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.builder.ToStringBuilder;

import br.com.probes.model.position.Point;
import br.com.probes.solr.document.PlaneDocument;

public class Plane {
	
	private String id = UUID.randomUUID().toString();
	
	private Point bottomLimit = new Point(0, 0);
	
	private Point upperLimit;
	
	private Map<Point, String> positionMap;

	public Plane() {
		this.upperLimit = new Point(100, 100);
	}
	
	public Plane(Point upperLimit) {
		this.upperLimit = upperLimit;
	}
	
	public Plane(int x, int y) {
		this.upperLimit = new Point(x, y);
	}
	
	public Plane(PlaneDocument planeDocument) {
		this.id = planeDocument.getId();
		this.bottomLimit = new Point(planeDocument.getBottomX(), planeDocument.getBottomY());
		this.upperLimit = new Point(planeDocument.getUpperX(), planeDocument.getUpperY());
		this.positionMap = new HashMap<Point, String>();
		planeDocument.getPositionMap().entrySet().stream().map(e -> this.positionMap.put(new Point(e.getKey()), e.getValue()));
	}
	
	public String getId() {
		return id;
	}

	public Point getBottomLimit() {
		return bottomLimit;
	}

	public void setBottomLimit(Point bottomLimit) {
		this.bottomLimit = bottomLimit;
	}

	public Point getUpperLimit() {
		return upperLimit;
	}

	public void setUpperLimit(Point upperLimit) {
		this.upperLimit = upperLimit;
	}

	public Map<Point, String> getPositionMap() {
		if (positionMap == null) positionMap = new HashMap<Point, String>();
		return positionMap;
	}

	public void setPositionMap(Map<Point, String> positionMap) {
		this.positionMap = positionMap;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}
