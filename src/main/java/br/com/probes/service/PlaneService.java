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
		planeRepository.save(plane);
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
				.collect(Collectors.toList());
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

		Plane plane = getPlane(planeId);

		Point point = new Point(x, y);
		positionValidator.validateMove(point, plane);

		Probe probe = probeService.createProbe(point, direction);

		plane.getPositionMap().put(point.toString(), probe.getId());

		planeRepository.save(plane);
		return probe;
	}

	public Probe turnLeft(String planeId, String probeId)
			throws InvalidPlaneException, InvalidProbeException {

		return probeService.turnLeft(probeId);
	}

	public Probe turnRight(String planeId, String probeId)
			throws InvalidPlaneException, InvalidProbeException {

		return probeService.turnRight(probeId);
	}

	public Probe move(String planeId, String probeId)
			throws InvalidPlaneException, InvalidProbeException,
			InvalidPositionException {
		
		Plane plane = getPlane(planeId);
		Probe probe = probeService.getProbe(probeId);
		positionValidator.validateMove(probeService.nextMove(probeId), plane);

		plane.getPositionMap().remove(probe.getPosition().toString());
		probe = probeService.move(probeId);
		plane.getPositionMap().put(probe.getPosition().toString(), probe.getId());
		
		planeRepository.save(plane);
		
		return probe;
	}

}
