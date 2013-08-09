package com.clandaith.freestars.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Random;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

import com.clandaith.freestars.Utils;
import com.clandaith.freestars.data.star.Star;

/**
 * @author troyd
 * 
 *         Copyright Maverik, inc 2013
 * 
 */
public class Map {

	private Hashtable<String, Star> starPlacement = new Hashtable<String, Star>();
	private boolean starsPlaced = false;
	private int searchMultiplier = 1;
	private JPanel p;

	private JMenuItem klienciMenuItem = new JMenuItem("");
	private JPopupMenu menuPopup = new JPopupMenu();

	private StarInfo starInfo;

	public int width = 1024;
	public int height = 768;

	public void setStarInfo(StarInfo starInfo) {
		this.starInfo = starInfo;
	}

	public Hashtable<String, Star> getStarPlacement() {
		return starPlacement;
	}

	public void highlightStar(Star starToHighlight) {
		starPlacement.get(starToHighlight.getPosition()).setHighlightStar(true);
		p.repaint();
	}

	public Map() {
		starPlacement = new Hashtable<String, Star>();

		Star star;
		for (int x = 0; x < 5; x++) {
			star = new Star(0, 0);
			starPlacement.put(star.getName(), star);
			System.out.println("Star count: " + x);
		}
	}

	public JPanel getMap() {
		klienciMenuItem.setText("In Space");
		menuPopup.add(klienciMenuItem);

		p = new JPanel() {
			private static final long serialVersionUID = 1l;
			Point pointStart = null;
			Point pointEnd = null;

			Random r = new Random();

			private void doDrawing(Graphics g) {
				Graphics2D g2d = (Graphics2D)g;
				Star star;

				if (!starsPlaced) {
					setInitialStarPlacement(getSize(), getInsets());

					Iterator<Star> starPlacementIter = starPlacement.values().iterator();
					while (starPlacementIter.hasNext()) {
						starPlacementIter.next().draw(g2d);
					}

				} else {
					Iterator<Star> starPlacementIter = starPlacement.values().iterator();
					while (starPlacementIter.hasNext()) {
						star = starPlacementIter.next();

						star.draw(g2d);
					}
				}
			}

			public void setInitialStarPlacement(Dimension dimension, Insets insets) {
				int w = dimension.width - (Star.starDiameter * 3) - insets.left - insets.right;
				int h = dimension.height - (Star.starDiameter * 3) - insets.top - insets.bottom;

				Iterator<Star> starPlacementIter;
				Star existingStar;
				Star star;

				Hashtable<String, Star> blahStars = new Hashtable<String, Star>();

				Iterator<Star> starsIter = starPlacement.values().iterator();
				int starCount = 0;
				while (starsIter.hasNext()) {
					star = starsIter.next();

					int x = Math.abs(r.nextInt()) % w;
					int y = Math.abs(r.nextInt()) % h;

					star.setXY(x, y);

					boolean placeStar = true;
					starPlacementIter = starPlacement.values().iterator();
					while (starPlacementIter.hasNext() && placeStar == true) {
						existingStar = starPlacementIter.next();

						if (existingStar != star && existingStar.colidesWithOtherObject(star, star.getCollisionDistance())) {
							placeStar = false;
						}
					}

					if (placeStar == false) {
						continue;
					}

					if (starCount == 3) {
						star.setMines_Defensive(true);
						star.setMineDistance(Star.starDiameter * 3);
					}

					blahStars.put(star.getPosition(), star);
					System.out.println(Utils.convertObjectToString(star));

					starCount++;
				}

				starPlacement = new Hashtable<String, Star>(blahStars);
				starsPlaced = true;
			}

			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				doDrawing(g);
			}

			boolean leftMouseClick = false;

			{
				addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {

						double x = e.getX();
						double y = e.getY();

						Star star = getNearPlanetsStar(x, y);

						if (star != null) {
							starInfo.setStar(star);
							klienciMenuItem.setText(star.getName());
							System.out.println(star.getName());
						} else {
							klienciMenuItem.setText("In Space");
							System.out.println("Star is null");
						}

						if (SwingUtilities.isRightMouseButton(e)) {
							leftMouseClick = false;
						} else {
							leftMouseClick = true;
							pointStart = e.getPoint();

							String start = isNearAnotherPlanet(x, y);

							pointStart.setLocation(Double.parseDouble(start.split(":")[0]), Double.parseDouble(start.split(":")[1]));
						}
					}

					@Override
					public void mouseReleased(MouseEvent e) {
						if (SwingUtilities.isRightMouseButton(e)) {
							leftMouseClick = false;
						} else {
							leftMouseClick = false;
							pointEnd = e.getPoint();

							double x = pointEnd.getX();
							double y = pointEnd.getY();

							String start = isNearAnotherPlanet(x, y);

							pointEnd.setLocation(Double.parseDouble(start.split(":")[0]), Double.parseDouble(start.split(":")[1]));

							Double length = Math.pow(pointEnd.x - pointStart.x, 2) + Math.pow(pointEnd.y - pointStart.y, 2);
							length = Math.sqrt(length);
							System.out.println("Length: " + Math.abs(length.intValue()));

							pointStart = null;
							pointEnd = null;
						}
					}
				});

				addMouseMotionListener(new MouseMotionAdapter() {
					@Override
					public void mouseMoved(MouseEvent e) {
						if (leftMouseClick) {
							pointEnd = e.getPoint();

							double x = pointEnd.getX();
							double y = pointEnd.getY();

							String start = isNearAnotherPlanet(x, y);

							pointEnd.setLocation(Double.parseDouble(start.split(":")[0]), Double.parseDouble(start.split(":")[1]));

							repaint();
						}
					}

					@Override
					public void mouseDragged(MouseEvent e) {
						if (leftMouseClick) {
							pointEnd = e.getPoint();

							double x = pointEnd.getX();
							double y = pointEnd.getY();

							String start = isNearAnotherPlanet(x, y);

							pointEnd.setLocation(Double.parseDouble(start.split(":")[0]), Double.parseDouble(start.split(":")[1]));
							repaint();
						}
					}
				});
			}

			@Override
			public void paint(Graphics g) {
				super.paint(g);

				if (leftMouseClick && pointStart != null) {
					g.setColor(Color.yellow);

					if (pointStart != null && pointEnd != null) {
						g.drawLine(pointStart.x - (Star.starDiameter / 2), pointStart.y - (Star.starDiameter / 2), pointEnd.x
										- (Star.starDiameter / 2), pointEnd.y - (Star.starDiameter / 2));
					}
				}
			}

			public String isNearAnotherPlanet(Double xD, Double yD) {
				int x = xD.intValue();
				int y = yD.intValue();

				String position = x + ":" + y;
				String checkPoint = "";

				// System.out.println("Start: " + position);
				boolean found = false;

				for (int a = (x - (Star.starDiameter * searchMultiplier)); a <= x + (Star.starDiameter * searchMultiplier); a++) {
					for (int b = (y - (Star.starDiameter * searchMultiplier)); b <= y + (Star.starDiameter * searchMultiplier); b++) {

						checkPoint = a + ":" + b;

						if (found == false && starPlacement.containsKey(checkPoint)) {
							a += (Star.starDiameter / 2);
							b += (Star.starDiameter / 2);
							position = a + ":" + b;
							// System.out.println("Found: " + checkPoint + " (" + position + ")");

							found = true;
						}
					}
				}

				return position;
			}

			public Star getNearPlanetsStar(Double xD, Double yD) {
				Star star = null;
				int x = xD.intValue();
				int y = yD.intValue();

				// String position = x + ":" + y;
				String checkPoint = "";

				// System.out.println("Start: " + position);
				boolean found = false;

				for (int a = (x - (Star.starDiameter * searchMultiplier)); a <= x + (Star.starDiameter * searchMultiplier); a++) {
					for (int b = (y - (Star.starDiameter * searchMultiplier)); b <= y + (Star.starDiameter * searchMultiplier); b++) {

						checkPoint = a + ":" + b;

						if (found == false && starPlacement.containsKey(checkPoint)) {
							star = starPlacement.get(checkPoint);

							found = true;
						}
					}
				}

				return star;
			}
		};

		// p.setBorder(BorderFactory.createLoweredBevelBorder());
		p.setBackground(Color.black);
		p.setComponentPopupMenu(menuPopup);
		p.setPreferredSize(new Dimension(500, 300));

		return p;
	}

}
