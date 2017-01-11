package br.com.probes;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Point {

	private int x;
	
	private int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
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
	public String toString() {
	   return ToStringBuilder.reflectionToString(this);
	}

}
