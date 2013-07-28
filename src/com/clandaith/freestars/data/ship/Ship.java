package com.clandaith.freestars.data.ship;

import java.awt.Rectangle;
import java.util.ArrayList;

import com.clandaith.freestars.data.MapEntity;

/*
 *
 * Copyright Troy Davidson
 * 
 * Create by Troy Davidson  troy@clandaith.com
 * Created on Jul 27, 2013 9:40:56 PM
 *
 */

public class Ship extends MapEntity {
	private Integer id;
	private String name;
	private Integer playerId;
	private String description;
	private Integer hullStrength_starting;
	private Integer hullStrength_current;
	private Integer type;

	private ArrayList<Engine> engines = new ArrayList<Engine>();
	private ArrayList<Scanner> scanners = new ArrayList<Scanner>();
	private ArrayList<Weapon> weapons_offensive = new ArrayList<Weapon>();
	private ArrayList<Weapon> weapons_defensive = new ArrayList<Weapon>();

	public Ship(int x, int y) {
		this.collisionDistance = 2;

		hitBox = new Rectangle(x, y, 3, 3);

		setName("asdfasdfasdf");
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPlayerId() {
		return playerId;
	}

	public void setPlayerId(Integer playerId) {
		this.playerId = playerId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getHullStrength_starting() {
		return hullStrength_starting;
	}

	public void setHullStrength_starting(Integer hullStrength_starting) {
		this.hullStrength_starting = hullStrength_starting;
	}

	public Integer getHullStrength_current() {
		return hullStrength_current;
	}

	public void setHullStrength_current(Integer hullStrength_current) {
		this.hullStrength_current = hullStrength_current;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public ArrayList<Engine> getEngines() {
		return engines;
	}

	public void setEngines(ArrayList<Engine> engines) {
		this.engines = engines;
	}

	public ArrayList<Scanner> getScanners() {
		return scanners;
	}

	public void setScanners(ArrayList<Scanner> scanners) {
		this.scanners = scanners;
	}

	public ArrayList<Weapon> getWeapons_offensive() {
		return weapons_offensive;
	}

	public void setWeapons_offensive(ArrayList<Weapon> weapons_offensive) {
		this.weapons_offensive = weapons_offensive;
	}

	public ArrayList<Weapon> getWeapons_defensive() {
		return weapons_defensive;
	}

	public void setWeapons_defensive(ArrayList<Weapon> weapons_defensive) {
		this.weapons_defensive = weapons_defensive;
	}

}
