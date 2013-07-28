package com.clandaith.freestars.data;

import java.awt.Rectangle;

/*
 *
 * Copyright Troy Davidson
 * 
 * Create by Troy Davidson  troy@clandaith.com
 * Created on Jul 27, 2013 9:42:04 PM
 *
 */

public abstract class MapEntity {
	protected Rectangle hitBox;
	protected int collisionDistance;

	public boolean colidesWithOtherObject(Star otherStar, int distanceToCheck) {
		Rectangle biggerhitBox = new Rectangle(hitBox.x - distanceToCheck, hitBox.y - distanceToCheck, hitBox.height
						+ distanceToCheck, hitBox.width + distanceToCheck);

		System.out.println("Are we coliding: " + biggerhitBox.intersects(otherStar.getHitBox()));

		return biggerhitBox.intersects(otherStar.getHitBox());
	}

	public int getCollisionDistance() {
		return collisionDistance;
	}

	public Integer getX() {
		return hitBox.x;
	}

	public Integer getY() {
		return hitBox.y;
	}

	public Rectangle getHitBox() {
		return hitBox;
	}
}
