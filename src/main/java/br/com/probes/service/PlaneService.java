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
import br.com.probes.model.Plane;
import br.com.probes.model.Probe;
import br.com.probes.model.position.Direction;
import br.com.probes.model.position.Point;
import br.com.probes.solr.PlaneRepository;
import br.com.probes.solr.document.PlaneDocument;
import br.com.probes.validation.PositionValidator;

@Component
public class PlaneService {

	@Autowired
	private ProbeService probeService;

	@Autowired
	private PositionValidator positionValidator;

	@Autowired
	public PlaneRepository planeRepository;

	public Plane createPlane(@RequestParam Integer x, @RequestParam Integer y) {
		Plane plane = new Plane(x, y);
		planeRepository.save(new PlaneDocument(plane));
		return plane;
	}
	
	public void deletePlane(String planeId) {
		planeRepository.delete(planeId);
	}
	
	public void deleteAllPlanes() {
		planeRepository.deleteAll();
	}

	public List<Plane> getPlanes() throws InvalidPlaneException {
		return StreamSupport
				.stream(planeRepository.findAll().spliterator(), false)
				.map(p -> new Plane(p)).collect(Collectors.toList());
	}

	public Plane getPlane(String planeId) throws InvalidPlaneException {
		PlaneDocument planeDocument = planeRepository.findById(planeId);

		if (planeDocument == null) {
			throw new InvalidPlaneException(planeId);
		}

		return new Plane(planeDocument);
	}

	public Probe createProbe(String planeId, int x, int y, Direction direction)
			throws InvalidPositionException, InvalidPlaneException {

		Plane plane = getPlane(planeId);

		Point point = new Point(x, y);
		positionValidator.validateMove(point, plane);

		Probe probe = probeService.createProbe(point, direction);

		plane.getPositionMap().put(point, probe.getId());

		planeRepository.save(new PlaneDocument(plane));
		return probe;
	}

	public void turnLeft(String planeId, String probeId)
			throws InvalidPlaneException, InvalidProbeException {

		probeService.turnLeft(probeId);
	}

	public void turnRight(String planeId, String probeId)
			throws InvalidPlaneException, InvalidProbeException {

		probeService.turnRight(probeId);
	}

	public void move(String planeId, String probeId)
			throws InvalidPlaneException, InvalidProbeException,
			InvalidPositionException {
		
		Plane plane = getPlane(planeId);
		Probe probe = probeService.getProbe(probeId);
		positionValidator.validateMove(probeService.nextMove(probeId), plane);

		plane.getPositionMap().remove(probe.getPosition());
		probeService.move(probeId);
		plane.getPositionMap().put(probe.getPosition(), probe.getId());
	}

}
