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

import br.com.probes.model.Probe;
import br.com.probes.model.position.Point;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class ProbeServiceTest {

	@Autowired
	private ProbeService probeService;
	
	private Probe probe;
	
	@Before
	public void setUp() {
		probe = probeService.createProbe(new Point(1, 1), N);
	}

	@After
	public void cleanUp() {
		probeService.deleteProbe(probe.getId());
	}

	@Test
	public void turnLeft() throws Exception {
		probe = probeService.turnLeft(probe.getId());
		assertEquals(W, probe.getDirection());
		probe = probeService.turnLeft(probe.getId());
		assertEquals(S, probe.getDirection());
		probe = probeService.turnLeft(probe.getId());
		assertEquals(E, probe.getDirection());
		probe = probeService.turnLeft(probe.getId());
		assertEquals(N, probe.getDirection());
	}

	@Test
	public void turnRight() throws Exception {
		probe = probeService.turnRight(probe.getId());
		assertEquals(E, probe.getDirection());
		probe = probeService.turnRight(probe.getId());
		assertEquals(S, probe.getDirection());
		probe = probeService.turnRight(probe.getId());
		assertEquals(W, probe.getDirection());
		probe = probeService.turnRight(probe.getId());
		assertEquals(N, probe.getDirection());
	}

	@Test
	public void moveNorth() throws Exception {
		Probe probe = probeService.createProbe(new Point(1, 1), N);
		probe = probeService.move(probe.getId());
		assertEquals(new Point(1, 2), probe.getPosition());
		probeService.deleteProbe(probe.getId());
	}

	@Test
	public void moveWest() throws Exception {
		Probe probe = probeService.createProbe(new Point(1, 1), W);
		probe = probeService.move(probe.getId());
		assertEquals(new Point(0, 1), probe.getPosition());
		probeService.deleteProbe(probe.getId());
	}

	@Test
	public void moveSouth() throws Exception {
		Probe probe = probeService.createProbe(new Point(1, 1), S);
		probe = probeService.move(probe.getId());
		assertEquals(new Point(1, 0), probe.getPosition());
		probeService.deleteProbe(probe.getId());
	}

	@Test
	public void moveEast() throws Exception {
		Probe probe = probeService.createProbe(new Point(1, 1), E);
		probe = probeService.move(probe.getId());
		assertEquals(new Point(2, 1), probe.getPosition());
		probeService.deleteProbe(probe.getId());
	}

	@Test
	public void nextMoveNorth() throws Exception {
		Probe probe = probeService.createProbe(new Point(1, 1), N);
		assertEquals(new Point(1, 2), probeService.nextMove(probe.getId()));
		probeService.deleteProbe(probe.getId());
	}

	@Test
	public void nextMoveWest() throws Exception {
		Probe probe = probeService.createProbe(new Point(1, 1), W);
		assertEquals(new Point(0, 1), probeService.nextMove(probe.getId()));
		probeService.deleteProbe(probe.getId());
	}

	@Test
	public void nextMoveSouth() throws Exception {
		Probe probe = probeService.createProbe(new Point(1, 1), S);
		assertEquals(new Point(1, 0), probeService.nextMove(probe.getId()));
		probeService.deleteProbe(probe.getId());
	}

	@Test
	public void nextMoveEast() throws Exception {
		Probe probe = probeService.createProbe(new Point(1, 1), E);
		assertEquals(new Point(2, 1), probeService.nextMove(probe.getId()));
		probeService.deleteProbe(probe.getId());
	}

}
