package br.com.probes;

import static br.com.probes.Direction.E;
import static br.com.probes.Direction.N;

import java.util.HashMap;
import java.util.Map;

import br.com.probes.exception.InvalidProbeException;

public class Plane {

	private Point bottomLimit = new Point(0, 0);
	
	private Point upperLimit;
	
	private Map<String, Probe> probeMap = new HashMap<String, Probe>();

	public Plane(Point upperLimit) {
		this.upperLimit = upperLimit;
	}
	
	public Plane(int x, int y) {
		this.upperLimit = new Point(x, y);
	}
	
	public String createProbe(int x, int y, Direction direction) {
		Probe probe = new Probe(x, y, direction);
		probeMap.put(probe.getId().toString(), probe);
		return probe.getId().toString();
	}
	
	public void turnLeft(String id) throws InvalidProbeException {
		this.getProbe(id).turnLeft();
	}

	public void turnRight(String id) throws InvalidProbeException {
		this.getProbe(id).turnRight();
	}
	
	public void move(String id) throws InvalidProbeException {
		this.getProbe(id).move();
	}
	
	private Probe getProbe(String id) throws InvalidProbeException {
		Probe probe = probeMap.get(id);
		
		if (probe == null) {
			throw new InvalidProbeException(id);
		}

		return probe;
	}

	public Map<String, Probe> getProbeMap() {
		return this.probeMap;
	}
	
	public static void main(String[] args) throws Exception {
		Plane plane = new Plane(5, 5);
		
		String p1 = plane.createProbe(1, 2, N);
		plane.turnLeft(p1);
		plane.move(p1);
		plane.turnLeft(p1);
		plane.move(p1);
		plane.turnLeft(p1);
		plane.move(p1);
		plane.turnLeft(p1);
		plane.move(p1);
		plane.move(p1);

		String p2 = plane.createProbe(3, 3, E);
		plane.move(p2);
		plane.move(p2);
		plane.turnRight(p2);
		plane.move(p2);
		plane.move(p2);
		plane.turnRight(p2);
		plane.move(p2);
		plane.turnRight(p2);
		plane.turnRight(p2);
		plane.move(p2);

		for (Probe probe : plane.getProbeMap().values()) {
			System.out.println(probe);
		}
	}
	
}
