package com.clandaith.freestars.data.star;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.clandaith.freestars.data.MapEntity;

/*
 *
 * Copyright Troy Davidson
 * 
 * Create by Troy Davidson  troy@clandaith.com
 * Created on Jul 27, 2013 9:42:04 PM
 *
 */

public class Star extends MapEntity {
	public static final int starDiameter = 14;

	private Boolean defense_mines = false;
	private Integer mineDistance = 0;

	private Integer barium;
	private Integer germanium;
	private Integer blahium;

	private Integer mineral_mines;
	private Integer factories;

	public Star(int x, int y) {
		hitBox = new Rectangle(x, y, starDiameter, starDiameter);
		this.collisionDistance = 20;

		this.setName(Stars.getStarName());
	}

	public boolean hasDefensive_Mines() {
		return defense_mines;
	}

	public void setDefensive_Mines(boolean mines) {
		this.defense_mines = mines;
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
		if (this.hasDefensive_Mines()) {
			g2d.setColor(Color.blue);
			g2d.fillOval(hitBox.x - (this.getMineDistance() / 2), hitBox.y - (this.getMineDistance() / 2), this.getMineDistance(),
							this.getMineDistance());
		}

		g2d.setColor(Color.red);
		g2d.fillOval(hitBox.x - (starDiameter / 2), hitBox.y - (starDiameter / 2), starDiameter, starDiameter);
	}

	public Integer getBarium() {
		return barium;
	}

	public void setBarium(Integer barium) {
		this.barium = barium;
	}

	public Integer getGermanium() {
		return germanium;
	}

	public void setGermanium(Integer germanium) {
		this.germanium = germanium;
	}

	public Integer getBlahium() {
		return blahium;
	}

	public void setBlahium(Integer blahium) {
		this.blahium = blahium;
	}

	public Integer getMineral_mines() {
		return mineral_mines;
	}

	public void setMineral_mines(Integer mineral_mines) {
		this.mineral_mines = mineral_mines;
	}

	public Integer getFactories() {
		return factories;
	}

	public void setFactories(Integer factories) {
		this.factories = factories;
	}
}
