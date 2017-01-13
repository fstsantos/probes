package br.com.probes.model.position;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Point {

	private int x;
	
	private int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Point(String point) {
		String[] xy = point.split("_");
		this.x = Integer.valueOf(xy[0]);
		this.y = Integer.valueOf(xy[1]);
	}

	public int getX() {
		return x;
	}

	public void incX() {
		this.x++;
	}

	public void decX() {
		this.x--;
	}

	public int getY() {
		return y;
	}

	public void incY() {
		this.y++;
	}
	
	public void decY() {
		this.y--;
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(x).append(y).build();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Point)) return false;
		Point other = (Point) obj;
		return new EqualsBuilder().append(x, other.getX()).append(y, other.getY()).isEquals();
		
	}

	@Override
	public String toString() {
	   return x + "_" + y;
	}

}
