package br.com.probes;

import static br.com.probes.Direction.E;
import static br.com.probes.Direction.N;
import static br.com.probes.Direction.S;
import static br.com.probes.Direction.W;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class ProbeTest {

	private Probe probe;
	
	@Before
	public void setUp() {
		probe = new Probe(new Point(1, 1), N);
	}
	
	@Test
	public void turnLeft() {
		probe.turnLeft();
		assertEquals(W, probe.getDirection());
		probe.turnLeft();
		assertEquals(S, probe.getDirection());
		probe.turnLeft();
		assertEquals(E, probe.getDirection());
		probe.turnLeft();
		assertEquals(N, probe.getDirection());
	}

	@Test
	public void turnRight() {
		probe.turnRight();
		assertEquals(E, probe.getDirection());
		probe.turnRight();
		assertEquals(S, probe.getDirection());
		probe.turnRight();
		assertEquals(W, probe.getDirection());
		probe.turnRight();
		assertEquals(N, probe.getDirection());
	}

	@Test
	public void moveNorth() {
		probe = new Probe(new Point(1, 1), N);
		probe.move();
		assertEquals(new Point(1, 2), probe.getPosition());
	}

	@Test
	public void moveWest() {
		probe = new Probe(new Point(1, 1), W);
		probe.move();
		assertEquals(new Point(0, 1), probe.getPosition());
	}

	@Test
	public void moveSouth() {
		probe = new Probe(new Point(1, 1), S);
		probe.move();
		assertEquals(new Point(1, 0), probe.getPosition());
	}

	@Test
	public void moveEast() {
		probe = new Probe(new Point(1, 1), E);
		probe.move();
		assertEquals(new Point(2, 1), probe.getPosition());
	}

	@Test
	public void nextMoveNorth() {
		probe = new Probe(new Point(1, 1), N);
		assertEquals(new Point(1, 2), probe.nextMove());
	}

	@Test
	public void nextMoveWest() {
		probe = new Probe(new Point(1, 1), W);
		assertEquals(new Point(0, 1), probe.nextMove());
	}

	@Test
	public void nextMoveSouth() {
		probe = new Probe(new Point(1, 1), S);
		assertEquals(new Point(1, 0), probe.nextMove());
	}

	@Test
	public void nextMoveEast() {
		probe = new Probe(new Point(1, 1), E);
		assertEquals(new Point(2, 1), probe.nextMove());
	}

}
