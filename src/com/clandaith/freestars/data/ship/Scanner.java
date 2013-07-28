package com.clandaith.freestars.data.ship;

/*
 *
 * Copyright Troy Davidson
 * 
 * Create by Troy Davidson  troy@clandaith.com
 * Created on Jul 27, 2013 10:32:22 PM
 *
 */

public class Scanner {
	private String name;
	private Integer type;
	private Integer range_active;
	private Integer range_passive;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getRange_active() {
		return range_active;
	}

	public void setRange_active(Integer range_active) {
		this.range_active = range_active;
	}

	public Integer getRange_passive() {
		return range_passive;
	}

	public void setRange_passive(Integer range_passive) {
		this.range_passive = range_passive;
	}

}
