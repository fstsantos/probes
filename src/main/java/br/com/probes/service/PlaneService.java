package br.com.probes.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.probes.exception.InvalidPlaneException;
import br.com.probes.exception.InvalidPositionException;
import br.com.probes.exception.InvalidProbeException;
import br.com.probes.plane.Plane;
import br.com.probes.plane.Probe;
import br.com.probes.plane.position.Direction;
import br.com.probes.solr.PlaneRepository;
import br.com.probes.solr.document.PlaneDocument;

@Component
public class PlaneService {

	@Autowired
	public PlaneRepository planeRepository;

	public Plane createPlane(@RequestParam Integer x, @RequestParam Integer y) {
		Plane plane = new Plane(x, y);
		planeRepository.save(new PlaneDocument(plane));
		return plane;
	}

	public List<Plane> getPlanes() throws InvalidPlaneException {
		return StreamSupport
				.stream(planeRepository.findAll().spliterator(), false)
				.map(p -> new Plane(p)).collect(Collectors.toList());
	}

	public Plane getPlane(String planeId) throws InvalidPlaneException {
		Plane plane = planeRepository.findById(planeId);

		if (plane == null) {
			throw new InvalidPlaneException(planeId);
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
