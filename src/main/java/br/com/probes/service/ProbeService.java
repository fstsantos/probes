package br.com.probes.service;

import static br.com.probes.model.position.Direction.E;
import static br.com.probes.model.position.Direction.N;
import static br.com.probes.model.position.Direction.S;
import static br.com.probes.model.position.Direction.W;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.probes.exception.InvalidProbeException;
import br.com.probes.model.Probe;
import br.com.probes.model.position.Direction;
import br.com.probes.model.position.Point;
import br.com.probes.solr.ProbeRepository;

@Component
public class ProbeService {

	@Autowired
	public ProbeRepository probeRepository;

	public Probe createProbe(Point point, Direction direction) {

		Probe probe = new Probe(point, direction);
		probeRepository.save(probe);
		
		return probe;
	}

	public void deleteProbe(String probeId) {
		probeRepository.delete(probeId);
	}
	
	public void deleteAllProbes() {
		probeRepository.deleteAll();
	}

	public Probe getProbe(String probeId) throws InvalidProbeException {
		Probe probe = probeRepository.findOne(probeId);
		
		if (probe == null) {
			throw new InvalidProbeException(probeId);
		}

		return probe;
	}

	public Probe turnLeft(String probeId) throws InvalidProbeException {
		Probe probe = getProbe(probeId);
		
		switch(probe.getDirection()) {
		case N:
			probe.setDirection(W);
			break;
		case W:
			probe.setDirection(S);
			break;
		case S:
			probe.setDirection(E);
			break;
		case E:
			probe.setDirection(N);
			break;
		}

		probeRepository.save(probe);
		return probe;
	}

	public Probe turnRight(String probeId) throws InvalidProbeException {
		Probe probe = getProbe(probeId);
		
		switch(probe.getDirection()) {
		case N:
			probe.setDirection(E);
			break;
		case W:
			probe.setDirection(N);
			break;
		case S:
			probe.setDirection(W);
			break;
		case E:
			probe.setDirection(S);
			break;
		}
		
		probeRepository.save(probe);
		return probe;
	}
	
	public Probe move(String probeId) throws InvalidProbeException {
		Probe probe = getProbe(probeId);
		
		switch(probe.getDirection()) {
		case N:
			probe.incY();
			break;
		case W:
			probe.decX();
			break;
		case S:
			probe.decY();
			break;
		case E:
			probe.incX();
			break;
		}

		probeRepository.save(probe);
		return probe;
	}

	public Point nextMove(String probeId) throws InvalidProbeException {
		Probe probe = getProbe(probeId);
		
		switch(probe.getDirection()) {
		case N:
			return new Point(probe.getPosition().getX(), probe.getPosition().getY() + 1);
		case W:
			return new Point(probe.getPosition().getX() - 1, probe.getPosition().getY());
		case S:
			return new Point(probe.getPosition().getX(), probe.getPosition().getY() - 1);
		case E:
			return new Point(probe.getPosition().getX() + 1, probe.getPosition().getY());
		}

		return null;
	}

}
