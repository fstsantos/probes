package br.com.probes;

import static br.com.probes.Direction.E;
import static br.com.probes.Direction.N;
import static br.com.probes.Direction.S;
import static br.com.probes.Direction.W;

import org.junit.Before;
import org.junit.Test;

import br.com.probes.exception.InvalidPositionException;
import br.com.probes.exception.InvalidProbeException;

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
		String id = plane.createProbe(0, 1, W);
		plane.move(id);
	}

	@Test(expected = InvalidPositionException.class)
	public void moveInvalidBottomY() throws Exception {
		String id = plane.createProbe(1, 0, S);
		plane.move(id);
	}

	@Test(expected = InvalidPositionException.class)
	public void moveInvalidUpperX() throws Exception {
		String id = plane.createProbe(5, 1, E);
		plane.move(id);
	}
	
	@Test(expected = InvalidPositionException.class)
	public void moveInvalidUpperY() throws Exception {
		String id = plane.createProbe(1, 5, N);
		plane.move(id);
	}

	@Test(expected = InvalidPositionException.class)
	public void moveDuplicatePositionNorth() throws Exception {
		plane.createProbe(2, 3, N);
		String id = plane.createProbe(2, 2, N);
		plane.move(id);
	}

	@Test(expected = InvalidPositionException.class)
	public void moveDuplicatePositionWest() throws Exception {
		plane.createProbe(2, 3, N);
		String id = plane.createProbe(3, 3, W);
		plane.move(id);
	}

	@Test(expected = InvalidPositionException.class)
	public void moveDuplicatePositionSouth() throws Exception {
		plane.createProbe(2, 3, N);
		String id = plane.createProbe(2, 4, S);
		plane.move(id);
	}

	@Test(expected = InvalidPositionException.class)
	public void moveDuplicatePositionEast() throws Exception {
		plane.createProbe(2, 3, N);
		String id = plane.createProbe(1, 3, E);
		plane.move(id);
	}

}
