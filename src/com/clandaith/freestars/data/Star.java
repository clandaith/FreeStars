package com.clandaith.freestars.data;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/*
 *
 * Copyright Troy Davidson
 * 
 * Create by Troy Davidson  troy@clandaith.com
 * Created on Jul 27, 2013 9:42:04 PM
 *
 */

public class Star extends MapEntity {
	public static int starDiameter = 14;

	private boolean mines = false;
	private Integer mineDistance = 0;

	public Star(int x, int y) {
		hitBox = new Rectangle(x, y, starDiameter, starDiameter);
		this.collisionDistance = 20;

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

	public String getPosition() {
		return hitBox.x + ":" + hitBox.y;
	}

	public void draw(Graphics g2d) {
		if (this.hasMines()) {
			g2d.setColor(Color.blue);
			g2d.fillOval(hitBox.x - (this.getMineDistance() / 2), hitBox.y - (this.getMineDistance() / 2), this.getMineDistance(),
							this.getMineDistance());
		}

		g2d.setColor(Color.red);
		g2d.fillOval(hitBox.x - (starDiameter / 2), hitBox.y - (starDiameter / 2), starDiameter, starDiameter);
	}
}
