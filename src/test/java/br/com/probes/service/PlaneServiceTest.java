package br.com.probes.service;

import static br.com.probes.model.position.Direction.E;
import static br.com.probes.model.position.Direction.N;
import static br.com.probes.model.position.Direction.S;
import static br.com.probes.model.position.Direction.W;
import static org.junit.Assert.assertEquals;

import org.junit.After;
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

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class PlaneServiceTest {

	@Autowired
	private PlaneService planeService;

	@Autowired
	private ProbeService probeService;

	private Plane plane;
	
	@Before
	public void setUp() {
		plane = planeService.createPlane(5, 5);
	}

	@After
	public void cleanUp() {
		planeService.deletePlane(plane.getId());
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
		Probe p1 = planeService.createProbe(plane.getId(), 1, 1, N);
		try {
			planeService.createProbe(plane.getId(), 1, 1, N);
		} finally {
			probeService.deleteProbe(p1.getId());
		}
	}

	@Test(expected = InvalidProbeException.class)
	public void turnLeftInvalidProbe() throws Exception {
		Probe probe = planeService.createProbe(plane.getId(), 1, 1, N);
		try {
			planeService.turnLeft(plane.getId(), "INVALID");
		} finally {
			probeService.deleteProbe(probe.getId());
		}
	}

	@Test(expected = InvalidProbeException.class)
	public void turnRightInvalidProbe() throws Exception {
		Probe probe = planeService.createProbe(plane.getId(), 1, 1, N);
		try {
			planeService.turnLeft(plane.getId(), "INVALID");
		} finally {
			probeService.deleteProbe(probe.getId());
		}
	}

	@Test(expected = InvalidProbeException.class)
	public void moveInvalidProbe() throws Exception {
		Probe probe = planeService.createProbe(plane.getId(), 1, 1, N);
		try {
			planeService.move(plane.getId(), "INVALID");
		} finally {
			probeService.deleteProbe(probe.getId());
		}
	}

	@Test(expected = InvalidPositionException.class)
	public void moveInvalidBottomX() throws Exception {
		Probe probe = planeService.createProbe(plane.getId(), 0, 1, W);
		try {
			planeService.move(plane.getId(), probe.getId());
		} finally {
			probeService.deleteProbe(probe.getId());
		}
	}

	@Test(expected = InvalidPositionException.class)
	public void moveInvalidBottomY() throws Exception {
		Probe probe = planeService.createProbe(plane.getId(), 1, 0, S);
		try {
			planeService.move(plane.getId(), probe.getId());
		} finally {
			probeService.deleteProbe(probe.getId());
		}
	}

	@Test(expected = InvalidPositionException.class)
	public void moveInvalidUpperX() throws Exception {
		Probe probe = planeService.createProbe(plane.getId(), 5, 1, E);
		try {
			planeService.move(plane.getId(), probe.getId());
		} finally {
			probeService.deleteProbe(probe.getId());
		}
	}

	@Test(expected = InvalidPositionException.class)
	public void moveInvalidUpperY() throws Exception {
		Probe probe = planeService.createProbe(plane.getId(), 1, 5, N);
		try {
			planeService.move(plane.getId(), probe.getId());
		} finally {
			probeService.deleteProbe(probe.getId());
		}
	}

	@Test(expected = InvalidPositionException.class)
	public void moveDuplicatePositionNorth() throws Exception {
		Probe p1 = planeService.createProbe(plane.getId(), 2, 3, N);
		Probe p2 = planeService.createProbe(plane.getId(), 2, 2, N);
		try {
			planeService.move(plane.getId(), p2.getId());
		} finally {
			probeService.deleteProbe(p1.getId());
			probeService.deleteProbe(p2.getId());
		}
	}

	@Test(expected = InvalidPositionException.class)
	public void moveDuplicatePositionWest() throws Exception {
		Probe p1 = planeService.createProbe(plane.getId(), 2, 3, N);
		Probe p2 = planeService.createProbe(plane.getId(), 3, 3, W);
		try {
			planeService.move(plane.getId(), p2.getId());
		} finally {
			probeService.deleteProbe(p1.getId());
			probeService.deleteProbe(p2.getId());
		}
	}

	@Test(expected = InvalidPositionException.class)
	public void moveDuplicatePositionSouth() throws Exception {
		Probe p1 = planeService.createProbe(plane.getId(), 2, 3, N);
		Probe p2 = planeService.createProbe(plane.getId(), 2, 4, S);
		try {
			planeService.move(plane.getId(), p2.getId());
		} finally {
			probeService.deleteProbe(p1.getId());
			probeService.deleteProbe(p2.getId());
		}
	}

	@Test(expected = InvalidPositionException.class)
	public void moveDuplicatePositionEast() throws Exception {
		Probe p1 = planeService.createProbe(plane.getId(), 2, 3, N);
		Probe p2 = planeService.createProbe(plane.getId(), 1, 3, E);
		try {
			planeService.move(plane.getId(), p2.getId());
		} finally {
			probeService.deleteProbe(p1.getId());
			probeService.deleteProbe(p2.getId());
		}
	}

	@Test
	public void case1() throws Exception {
		Probe p1 = planeService.createProbe(plane.getId(), 1, 2, N);
		p1 = planeService.turnLeft(plane.getId(), p1.getId());
		p1 = planeService.move(plane.getId(), p1.getId());
		p1 = planeService.turnLeft(plane.getId(), p1.getId());
		p1 = planeService.move(plane.getId(), p1.getId());
		p1 = planeService.turnLeft(plane.getId(), p1.getId());
		p1 = planeService.move(plane.getId(), p1.getId());
		p1 = planeService.turnLeft(plane.getId(), p1.getId());
		p1 = planeService.move(plane.getId(), p1.getId());
		p1 = planeService.move(plane.getId(), p1.getId());

		Probe p2 = planeService.createProbe(plane.getId(), 3, 3, E);
		p2 = planeService.move(plane.getId(), p2.getId());
		p2 = planeService.move(plane.getId(), p2.getId());
		p2 = planeService.turnRight(plane.getId(), p2.getId());
		p2 = planeService.move(plane.getId(), p2.getId());
		p2 = planeService.move(plane.getId(), p2.getId());
		p2 = planeService.turnRight(plane.getId(), p2.getId());
		p2 = planeService.move(plane.getId(), p2.getId());
		p2 = planeService.turnRight(plane.getId(), p2.getId());
		p2 = planeService.turnRight(plane.getId(), p2.getId());
		p2 = planeService.move(plane.getId(), p2.getId());

		Probe probe1 = probeService.getProbe(p1.getId());
		assertEquals(1, probe1.getPosition().getX());
		assertEquals(3, probe1.getPosition().getY());
		assertEquals(N, probe1.getDirection());
		
		Probe probe2 = probeService.getProbe(p2.getId());
		assertEquals(5, probe2.getPosition().getX());
		assertEquals(1, probe2.getPosition().getY());
		assertEquals(E, probe2.getDirection());
		
		probeService.deleteProbe(p1.getId());
		probeService.deleteProbe(p2.getId());
	}
}
