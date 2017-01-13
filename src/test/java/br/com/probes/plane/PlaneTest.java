package br.com.probes.plane;

import static br.com.probes.model.position.Direction.E;
import static br.com.probes.model.position.Direction.N;
import static br.com.probes.model.position.Direction.S;
import static br.com.probes.model.position.Direction.W;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.probes.exception.InvalidPositionException;
import br.com.probes.exception.InvalidProbeException;
import br.com.probes.model.Plane;
import br.com.probes.model.Probe;
import br.com.probes.service.PlaneService;
import br.com.probes.service.ProbeService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class PlaneTest {

	@Autowired
	private PlaneService planeService;

	@Autowired
	private ProbeService probeService;

	private Plane plane;
	
	@Before
	public void setUp() {
		plane = planeService.createPlane(5, 5);
	}

	@Test(expected = InvalidPositionException.class)
	public void createProbeInvalidBottomX() throws Exception {
		planeService.createProbe(plane.getId(), -1, 1, N);
	}

	@Test(expected = InvalidPositionException.class)
	public void createProbeInvalidBottomY() throws Exception {
		planeService.createProbe(plane.getId(), 1, -1, N);
	}

	@Test(expected = InvalidPositionException.class)
	public void createProbeInvalidUpperX() throws Exception {
		planeService.createProbe(plane.getId(), 6, 1, N);
	}

	@Test(expected = InvalidPositionException.class)
	public void createProbeInvalidUpperY() throws Exception {
		planeService.createProbe(plane.getId(), 1, 6, N);
	}

	@Test(expected = InvalidPositionException.class)
	public void createProbeDuplicatePosition() throws Exception {
		planeService.createProbe(plane.getId(), 1, 1, N);
		planeService.createProbe(plane.getId(), 1, 1, N);
	}

	@Test(expected = InvalidProbeException.class)
	public void turnLeftInvalidProbe() throws Exception {
		planeService.createProbe(plane.getId(), 1, 1, N);
		planeService.turnLeft(plane.getId(), "INVALID");
	}

	@Test(expected = InvalidProbeException.class)
	public void turnRightInvalidProbe() throws Exception {
		planeService.createProbe(plane.getId(), 1, 1, N);
		planeService.turnLeft(plane.getId(), "INVALID");
	}

	@Test(expected = InvalidProbeException.class)
	public void moveInvalidProbe() throws Exception {
		planeService.createProbe(plane.getId(), 1, 1, N);
		planeService.turnLeft(plane.getId(), "INVALID");
	}

	@Test(expected = InvalidPositionException.class)
	public void moveInvalidBottomX() throws Exception {
		Probe probe = planeService.createProbe(plane.getId(), 0, 1, W);
		planeService.turnLeft(plane.getId(), probe.getId());
	}

	@Test(expected = InvalidPositionException.class)
	public void moveInvalidBottomY() throws Exception {
		Probe probe = planeService.createProbe(plane.getId(), 1, 0, S);
		planeService.turnLeft(plane.getId(), probe.getId());
	}

	@Test(expected = InvalidPositionException.class)
	public void moveInvalidUpperX() throws Exception {
		Probe probe = planeService.createProbe(plane.getId(), 5, 1, E);
		planeService.turnLeft(plane.getId(), probe.getId());
	}

	@Test(expected = InvalidPositionException.class)
	public void moveInvalidUpperY() throws Exception {
		Probe probe = planeService.createProbe(plane.getId(), 1, 5, N);
		planeService.turnLeft(plane.getId(), probe.getId());
	}

	@Test(expected = InvalidPositionException.class)
	public void moveDuplicatePositionNorth() throws Exception {
		planeService.createProbe(plane.getId(), 2, 3, N);
		Probe probe = planeService.createProbe(plane.getId(), 2, 2, N);
		planeService.move(plane.getId(), probe.getId());
	}

	@Test(expected = InvalidPositionException.class)
	public void moveDuplicatePositionWest() throws Exception {
		planeService.createProbe(plane.getId(), 2, 3, N);
		Probe probe = planeService.createProbe(plane.getId(), 3, 3, W);
		planeService.move(plane.getId(), probe.getId());
	}

	@Test(expected = InvalidPositionException.class)
	public void moveDuplicatePositionSouth() throws Exception {
		planeService.createProbe(plane.getId(), 2, 3, N);
		Probe probe = planeService.createProbe(plane.getId(), 2, 4, S);
		planeService.move(plane.getId(), probe.getId());
	}

	@Test(expected = InvalidPositionException.class)
	public void moveDuplicatePositionEast() throws Exception {
		planeService.createProbe(plane.getId(), 2, 3, N);
		Probe probe = planeService.createProbe(plane.getId(), 1, 3, E);
		planeService.move(plane.getId(), probe.getId());
	}

	@Test
	public void case1() throws Exception {
		Probe p1 = planeService.createProbe(plane.getId(), 1, 2, N);
		planeService.turnLeft(plane.getId(), p1.getId());
		planeService.move(plane.getId(), p1.getId());
		planeService.turnLeft(plane.getId(), p1.getId());
		planeService.move(plane.getId(), p1.getId());
		planeService.turnLeft(plane.getId(), p1.getId());
		planeService.move(plane.getId(), p1.getId());
		planeService.turnLeft(plane.getId(), p1.getId());
		planeService.move(plane.getId(), p1.getId());
		planeService.move(plane.getId(), p1.getId());

		Probe p2 = planeService.createProbe(plane.getId(), 3, 3, E);
		planeService.move(plane.getId(), p2.getId());
		planeService.move(plane.getId(), p2.getId());
		planeService.turnRight(plane.getId(), p2.getId());
		planeService.move(plane.getId(), p2.getId());
		planeService.move(plane.getId(), p2.getId());
		planeService.turnRight(plane.getId(), p2.getId());
		planeService.move(plane.getId(), p2.getId());
		planeService.turnRight(plane.getId(), p2.getId());
		planeService.turnRight(plane.getId(), p2.getId());
		
		planeService.move(plane.getId(), p2.getId());

		Probe probe1 = probeService.getProbe(p1.getId());
		assertEquals(1, probe1.getPosition().getX());
		assertEquals(3, probe1.getPosition().getY());
		assertEquals(N, probe1.getDirection());
		
		Probe probe2 = probeService.getProbe(p2.getId());
		assertEquals(5, probe2.getPosition().getX());
		assertEquals(1, probe2.getPosition().getY());
		assertEquals(E, probe2.getDirection());
	}
}
