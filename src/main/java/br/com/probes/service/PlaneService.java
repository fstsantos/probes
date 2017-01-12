package br.com.probes.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.probes.exception.InvalidPlaneException;
import br.com.probes.exception.InvalidPositionException;
import br.com.probes.exception.InvalidProbeException;
import br.com.probes.plane.Plane;
import br.com.probes.plane.Probe;
import br.com.probes.plane.position.Direction;

@Component
public class PlaneService {

	private Map<String, Plane> planeMap = new HashMap<String, Plane>();

	public Plane createPlane(@RequestParam Integer x, @RequestParam Integer y) {
		Plane plane = new Plane(x, y);
		planeMap.put(plane.getId().toString(), plane);
		return plane;
	}

	public List<Plane> getPlanes() throws InvalidPlaneException {
		return new ArrayList<Plane>(planeMap.values());
	}

	public Plane getPlane(String id) throws InvalidPlaneException {
		Plane plane = planeMap.get(id);

		if (plane == null) {
			throw new InvalidPlaneException(id);
		}

		return plane;
	}

	public Probe createProbe(String planeId, int x, int y, Direction direction)
			throws InvalidPositionException, InvalidPlaneException {

		return getPlane(planeId).createProbe(x, y, direction);
	}

	public void turnLeft(String planeId, String probeId)
			throws InvalidPlaneException, InvalidProbeException {

		getPlane(planeId).turnLeft(probeId);
	}

	public void turnRight(String planeId, String probeId)
			throws InvalidPlaneException, InvalidProbeException {

		getPlane(planeId).turnRight(probeId);
	}

	public void move(String planeId, String probeId)
			throws InvalidPlaneException, InvalidProbeException,
			InvalidPositionException {

		getPlane(planeId).move(probeId);
	}

}
