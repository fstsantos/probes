package br.com.probes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.probes.model.Plane;
import br.com.probes.model.Probe;
import br.com.probes.model.position.Direction;
import br.com.probes.service.PlaneService;

@RestController
public class PlaneController {

	@Autowired
	private PlaneService planeService;
	
	@RequestMapping(value = "/plane", method = RequestMethod.PUT)
	public Plane createPlane(@RequestParam Integer x, @RequestParam Integer y) {
		return planeService.createPlane(x, y);
	}

	@RequestMapping(value = "/plane", method = RequestMethod.GET)
	public List<Plane> getPlanes() throws Exception {
		return planeService.getPlanes();
	}

	@RequestMapping(value = "/plane/{planeId}", method = RequestMethod.GET)
	public Plane getPlane(@PathVariable String planeId) throws Exception {
		return planeService.getPlane(planeId);
	}

	@RequestMapping(value = "/plane/{planeId}/probe", method = RequestMethod.PUT)
	public Probe createProbe(@PathVariable String planeId,
			@RequestParam Integer x, @RequestParam Integer y,
			@RequestParam Direction direction) throws Exception {
		return planeService.createProbe(planeId, x, y, direction);
	}

	@RequestMapping(value = "/plane/{planeId}/probe/{probeId}/turnLeft", method = RequestMethod.GET)
	public void turnLeft(@PathVariable String planeId, @PathVariable String probeId) throws Exception {
		planeService.turnLeft(planeId, probeId);
	}
	
	@RequestMapping(value = "/plane/{planeId}/probe/{probeId}/turnRight", method = RequestMethod.GET)
	public void turnRight(@PathVariable String planeId, @PathVariable String probeId) throws Exception {
		planeService.turnRight(planeId, probeId);
	}

	@RequestMapping(value = "/plane/{planeId}/probe/{probeId}/move", method = RequestMethod.GET)
	public void move(@PathVariable String planeId, @PathVariable String probeId) throws Exception {
		planeService.move(planeId, probeId);
	}

	@RequestMapping(value = "/plane/{planeId}", method = RequestMethod.DELETE)
	public void deletePlane(@PathVariable String planeId) throws Exception {
		planeService.deletePlane(planeId);
	}

	@RequestMapping(value = "/plane", method = RequestMethod.DELETE)
	public void deleteAllPlanes() throws Exception {
		planeService.deleteAllPlanes();
	}

}
