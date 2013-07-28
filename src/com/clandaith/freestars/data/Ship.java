package com.clandaith.freestars.data;

import java.awt.Rectangle;
import java.util.ArrayList;

import com.clandaith.freestars.data.ship.Engine;
import com.clandaith.freestars.data.ship.Scanner;
import com.clandaith.freestars.data.ship.Weapon;

/*
 *
 * Copyright Troy Davidson
 * 
 * Create by Troy Davidson  troy@clandaith.com
 * Created on Jul 27, 2013 9:40:56 PM
 *
 */

public class Ship extends MapEntity {

	private ArrayList<Engine> engines = new ArrayList<Engine>();
	private ArrayList<Scanner> scanners = new ArrayList<Scanner>();
	private ArrayList<Weapon> weapons = new ArrayList<Weapon>();

	public Ship(int x, int y) {
		this.collisionDistance = 2;

		hitBox = new Rectangle(x, y, 3, 3);

		setName("asdfasdfasdf");
	}

}
