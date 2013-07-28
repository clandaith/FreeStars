package com.clandaith.freestars.data.race;

import java.util.ArrayList;

/*
 *
 * Copyright Troy Davidson
 * 
 * Create by Troy Davidson  troy@clandaith.com
 * Created on Jul 28, 2013 8:46:55 AM
 *
 */

public class Race {
	private Integer id;
	private Integer playerId;
	private String name_singular;
	private String name_plural;
	private String description;
	private Integer homePlanetId;
	private ArrayList<RacialTrait> racialTraits = new ArrayList<RacialTrait>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPlayerId() {
		return playerId;
	}

	public void setPlayerId(Integer playerId) {
		this.playerId = playerId;
	}

	public String getName_singular() {
		return name_singular;
	}

	public void setName_singular(String name_singular) {
		this.name_singular = name_singular;
	}

	public String getName_plural() {
		return name_plural;
	}

	public void setName_plural(String name_plural) {
		this.name_plural = name_plural;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getHomePlanetId() {
		return homePlanetId;
	}

	public void setHomePlanetId(Integer homePlanetId) {
		this.homePlanetId = homePlanetId;
	}

	public ArrayList<RacialTrait> getRacialTraits() {
		return racialTraits;
	}

	public void setRacialTraits(ArrayList<RacialTrait> racialTraits) {
		this.racialTraits = racialTraits;
	}

}
