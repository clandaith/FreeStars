package com.clandaith.freestars.data;

/*
 *
 * Copyright Maverik.inc 
 * 
 * Create by Troy Davidson  troy.davidson@maverik.com
 * Created on Jul 24, 2013 8:41:44 AM
 *
 */

public class Star {
	private String name;
	private Integer x;
	private Integer y;
	private boolean mines = false;
	private Integer mineDistance = 0;

	public boolean hasMines() {
		return mines;
	}

	public void setMines(boolean mines) {
		this.mines = mines;
	}

	public Integer getMineDistance() {
		return mineDistance;
	}

	public void setMineDistance(Integer mineDistance) {
		this.mineDistance = mineDistance;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getX() {
		return x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}

	public String getPosition() {
		return x + ":" + y;
	}

}
