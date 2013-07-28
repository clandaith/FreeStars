package com.clandaith.freestars.data.star;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import com.clandaith.freestars.data.MapEntity;
import com.clandaith.freestars.data.ship.Ship;

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

	private Boolean mines_defensive = false;
	private Integer mineDistance = 0;

	private Integer barium;
	private Integer germanium;
	private Integer blahium;

	private Integer radiation;
	private Integer atmosphere;

	private ArrayList<Ship> shipsInOrbit = new ArrayList<Ship>();

	private Integer mines_minerals;
	private Integer factories;

	public Star(int x, int y) {
		hitBox = new Rectangle(x, y, starDiameter, starDiameter);
		this.collisionDistance = 20;

		this.setName(Stars.getStarName());
	}

	public boolean hasMines_Defensive() {
		return mines_defensive;
	}

	public void setMines_Defensive(boolean mines) {
		this.mines_defensive = mines;
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
		if (this.hasMines_Defensive()) {
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

	public Integer getMines_Mineral() {
		return mines_minerals;
	}

	public void setMines_Mineral(Integer mineral_mines) {
		this.mines_minerals = mineral_mines;
	}

	public Integer getFactories() {
		return factories;
	}

	public void setFactories(Integer factories) {
		this.factories = factories;
	}

	public Boolean getMines_defensive() {
		return mines_defensive;
	}

	public void setMines_defensive(Boolean mines_defensive) {
		this.mines_defensive = mines_defensive;
	}

	public Integer getRadiation() {
		return radiation;
	}

	public void setRadiation(Integer radiation) {
		this.radiation = radiation;
	}

	public Integer getAtmosphere() {
		return atmosphere;
	}

	public void setAtmosphere(Integer atmosphere) {
		this.atmosphere = atmosphere;
	}

	public ArrayList<Ship> getShipsInOrbit() {
		return shipsInOrbit;
	}

	public void setShipsInOrbit(ArrayList<Ship> shipsInOrbit) {
		this.shipsInOrbit = shipsInOrbit;
	}

	public Integer getMines_minerals() {
		return mines_minerals;
	}

	public void setMines_minerals(Integer mines_minerals) {
		this.mines_minerals = mines_minerals;
	}
}
