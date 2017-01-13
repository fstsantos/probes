package br.com.probes.plane;

import static br.com.probes.plane.position.Direction.E;
import static br.com.probes.plane.position.Direction.N;
import static br.com.probes.plane.position.Direction.S;
import static br.com.probes.plane.position.Direction.W;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import br.com.probes.exception.InvalidPositionException;
import br.com.probes.exception.InvalidProbeException;
import br.com.probes.plane.Plane;
import br.com.probes.plane.Probe;

public class PlaneTest {

	private Plane plane;

	@Before
	public void setUp() {
		plane = new Plane(5, 5);
	}

	@Test(expected = InvalidPositionException.class)
	public void createProbeInvalidBottomX() throws Exception {
		plane.createProbe(-1, 1, N);
	}

	@Test(expected = InvalidPositionException.class)
	public void createProbeInvalidBottomY() throws Exception {
		plane.createProbe(1, -1, N);
	}

	@Test(expected = InvalidPositionException.class)
	public void createProbeInvalidUpperX() throws Exception {
		plane.createProbe(6, 1, N);
	}

	@Test(expected = InvalidPositionException.class)
	public void createProbeInvalidUpperY() throws Exception {
		plane.createProbe(1, 6, N);
	}

	@Test(expected = InvalidPositionException.class)
	public void createProbeDuplicatePosition() throws Exception {
		plane.createProbe(1, 1, N);
		plane.createProbe(1, 1, N);
	}

	@Test(expected = InvalidProbeException.class)
	public void turnLeftInvalidProbe() throws Exception {
		plane.createProbe(1, 1, N);
		plane.turnLeft("INVALID");
	}

	@Test(expected = InvalidProbeException.class)
	public void turnRightInvalidProbe() throws Exception {
		plane.createProbe(1, 1, N);
		plane.turnRight("INVALID");
	}

	@Test(expected = InvalidProbeException.class)
	public void moveInvalidProbe() throws Exception {
		plane.createProbe(1, 1, N);
		plane.move("INVALID");
	}

	@Test(expected = InvalidPositionException.class)
	public void moveInvalidBottomX() throws Exception {
		Probe probe = plane.createProbe(0, 1, W);
		plane.move(probe.getId().toString());
	}

	@Test(expected = InvalidPositionException.class)
	public void moveInvalidBottomY() throws Exception {
		Probe probe = plane.createProbe(1, 0, S);
		plane.move(probe.getId().toString());
	}

	@Test(expected = InvalidPositionException.class)
	public void moveInvalidUpperX() throws Exception {
		Probe probe = plane.createProbe(5, 1, E);
		plane.move(probe.getId().toString());
	}

	@Test(expected = InvalidPositionException.class)
	public void moveInvalidUpperY() throws Exception {
		Probe probe = plane.createProbe(1, 5, N);
		plane.move(probe.getId().toString());
	}

	@Test(expected = InvalidPositionException.class)
	public void moveDuplicatePositionNorth() throws Exception {
		plane.createProbe(2, 3, N);
		Probe probe = plane.createProbe(2, 2, N);
		plane.move(probe.getId().toString());
	}

	@Test(expected = InvalidPositionException.class)
	public void moveDuplicatePositionWest() throws Exception {
		plane.createProbe(2, 3, N);
		Probe probe = plane.createProbe(3, 3, W);
		plane.move(probe.getId().toString());
	}

	@Test(expected = InvalidPositionException.class)
	public void moveDuplicatePositionSouth() throws Exception {
		plane.createProbe(2, 3, N);
		Probe probe = plane.createProbe(2, 4, S);
		plane.move(probe.getId().toString());
	}

	@Test(expected = InvalidPositionException.class)
	public void moveDuplicatePositionEast() throws Exception {
		plane.createProbe(2, 3, N);
		Probe probe = plane.createProbe(1, 3, E);
		plane.move(probe.getId().toString());
	}

	@Test
	public void case1() throws Exception {
		Probe p1 = plane.createProbe(1, 2, N);
		plane.turnLeft(p1.getId().toString());
		plane.move(p1.getId().toString());
		plane.turnLeft(p1.getId().toString());
		plane.move(p1.getId().toString());
		plane.turnLeft(p1.getId().toString());
		plane.move(p1.getId().toString());
		plane.turnLeft(p1.getId().toString());
		plane.move(p1.getId().toString());
		plane.move(p1.getId().toString());

		Probe p2 = plane.createProbe(3, 3, E);
		plane.move(p2.getId().toString());
		plane.move(p2.getId().toString());
		plane.turnRight(p2.getId().toString());
		plane.move(p2.getId().toString());
		plane.move(p2.getId().toString());
		plane.turnRight(p2.getId().toString());
		plane.move(p2.getId().toString());
		plane.turnRight(p2.getId().toString());
		plane.turnRight(p2.getId().toString());
		
		plane.move(p2.getId().toString());

		Probe probe1 = plane.getProbe(p1.getId().toString());
		assertEquals(1, probe1.getPosition().getX());
		assertEquals(3, probe1.getPosition().getY());
		assertEquals(N, probe1.getDirection());
		
		Probe probe2 = plane.getProbe(p2.getId().toString());
		assertEquals(5, probe2.getPosition().getX());
		assertEquals(1, probe2.getPosition().getY());
		assertEquals(E, probe2.getDirection());
	}
}