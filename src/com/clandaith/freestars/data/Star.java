package com.clandaith.freestars.data;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/*
 *
 * Copyright Maverik.inc 
 * 
 * Create by Troy Davidson  troy.davidson@maverik.com
 * Created on Jul 24, 2013 8:41:44 AM
 *
 */

public class Star {

	private Rectangle rectangle;

	public static int starDiameter = 14;

	private String name;
	private boolean mines = false;
	private Integer mineDistance = 0;

	public Star(int x, int y) {

		rectangle = new Rectangle(x, y, starDiameter, starDiameter);

		this.setName(Stars.getStarName());
	}

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
		return rectangle.x;
	}

	public Integer getY() {
		return rectangle.y;
	}

	public String getPosition() {
		return rectangle.x + ":" + rectangle.y;
	}

	public void draw(Graphics g2d) {
		if (this.hasMines()) {
			g2d.setColor(Color.blue);
			g2d.fillOval(rectangle.x - (this.getMineDistance() / 2), rectangle.y - (this.getMineDistance() / 2),
							this.getMineDistance(), this.getMineDistance());
		}

		g2d.setColor(Color.red);
		g2d.fillOval(rectangle.x - (starDiameter / 2), rectangle.y - (starDiameter / 2), starDiameter, starDiameter);
	}

	public boolean colidesWithOtherStar(Star otherStar) {
		Rectangle biggerRectangle = new Rectangle(rectangle.x - 20, rectangle.y - 20, rectangle.height + 20, rectangle.width + 20);

		System.out.println("Are we coliding: " + biggerRectangle.intersects(otherStar.getRectangle()));

		return biggerRectangle.intersects(otherStar.getRectangle());
	}

	public Rectangle getRectangle() {
		return rectangle;
	}
}
