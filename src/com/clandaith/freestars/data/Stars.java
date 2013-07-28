package com.clandaith.freestars.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

/*
 *
 * Copyright Maverik.inc 
 * 
 * Create by Troy Davidson  troy.davidson@maverik.com
 * Created on Jul 27, 2013 8:48:09 PM
 *
 */

public class Stars {

	private static ArrayList<String> starNames = new ArrayList<String>();

	static String[] letters = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K" };
	static Random r = new Random();

	protected static String getStarName() {
		String name = "";
		String letter;
		r.setSeed(new Date().getTime());
		int a = r.nextInt(10);
		for (int x = 0; x <= a; x++) {

			letter = letters[r.nextInt(letters.length - 1)];
			if (r.nextBoolean() == false) {
				letter = letter.toLowerCase();
			}
			name += letter;
		}

		if (starNames.contains(name)) {
			name = getStarName();
		}

		starNames.add(name);

		return name;
	}
}
