package br.com.probes.plane;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.probes.exception.InvalidPositionException;
import br.com.probes.exception.InvalidProbeException;
import br.com.probes.plane.position.Direction;
import br.com.probes.plane.position.Point;
import br.com.probes.solr.ProbeRepository;
import br.com.probes.solr.document.PlaneDocument;

public class Plane {

	@Autowired
	private ProbeRepository probeRepository;
	
	private String id = UUID.randomUUID().toString();
	
	private Point bottomLimit = new Point(0, 0);
	
	private Point upperLimit;
	
	private Map<String, Probe> probeMap = new HashMap<String, Probe>();
	
	private Map<Point, Probe> positionMap = new HashMap<Point, Probe>();

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
	}
	
	public Probe createProbe(int x, int y, Direction direction) throws InvalidPositionException {
		Point point = new Point(x, y);
		validateMove(point);

		Probe probe = new Probe(point, direction);
		probeMap.put(probe.getId().toString(), probe);
		
		positionMap.put(point, probe);
		
		return probe;
	}
	
	public void turnLeft(String probeId) throws InvalidProbeException {
		getProbe(probeId).turnLeft();
	}

	public void turnRight(String probeId) throws InvalidProbeException {
		getProbe(probeId).turnRight();
	}
	
	public void move(String probeId) throws InvalidProbeException, InvalidPositionException {
		Probe probe = getProbe(probeId);
		validateMove(probe.nextMove());
		
		positionMap.remove(probe.getPosition());
		probe.move();
		positionMap.put(probe.getPosition(), probe);
	}
	
	private void validateMove(Point point) throws InvalidPositionException {
		validateBoundaries(point);
		validatePosition(point);
	}

	private void validateBoundaries(Point point) throws InvalidPositionException {
		if (point.getX() < bottomLimit.getX() ||
			point.getY() < bottomLimit.getY() ||
			point.getX() > upperLimit.getX() ||
			point.getY() > upperLimit.getY()) {
			
			throw new InvalidPositionException(point);
		}
	}

	private void validatePosition(Point point) throws InvalidPositionException {
		if (positionMap.get(point) != null) {
			throw new InvalidPositionException(point, positionMap.get(point));
		}
	}

	public Probe getProbe(String probeId) throws InvalidProbeException {
		Probe probe = probeRepository.findById(probeId);
		
		if (probe == null) {
			throw new InvalidProbeException(probeId);
		}

		return probe;
	}

	public String getId() {
		return id;
	}

	public Point getBottomLimit() {
		return bottomLimit;
	}

	public Point getUpperLimit() {
		return upperLimit;
	}

	public Map<String, Probe> getProbeMap() {
		return this.probeMap;
	}
	
}
