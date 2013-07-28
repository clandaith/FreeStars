package com.clandaith.freestars.gui;

import java.awt.BorderLayout;
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

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import com.clandaith.freestars.data.Star;

public class Map {
	private static Hashtable<String, Star> starPlacement = null;
	private static int searchMultiplier = 1;

	private static JMenuItem klienciMenuItem = new JMenuItem("");
	private static JPopupMenu menuPopup = new JPopupMenu();
	private static JTextArea starName = new JTextArea();
	private static final int GAP = 5;

	public static void main(String args[]) throws Exception {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}

	private static void createAndShowGUI() {
		JFrame f = new JFrame("Map");
		f.setSize(600, 300);
		f.setLocation(100, 100);
		f.setPreferredSize(new Dimension(600, 300));
		f.setResizable(true);

		f.add(new Map().getMap());
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}

	public JPanel getMap() {
		klienciMenuItem.setText("In Space");
		menuPopup.add(klienciMenuItem);

		JPanel p = new JPanel() {
			private static final long serialVersionUID = 1l;
			Point pointStart = null;
			Point pointEnd = null;

			Random r = new Random();

			private void doDrawing(Graphics g) {
				Graphics2D g2d = (Graphics2D)g;

				Dimension size = getSize();
				Insets insets = getInsets();

				int w = size.width - (Star.starDiameter * 3) - insets.left - insets.right;
				int h = size.height - (Star.starDiameter * 3) - insets.top - insets.bottom;

				Star star;

				if (starPlacement == null) {
					starPlacement = new Hashtable<String, Star>();

					Iterator<Star> starPlacementIter;
					Star existingStar;
					int a = 0;
					do {

						int x = Math.abs(r.nextInt()) % w;
						int y = Math.abs(r.nextInt()) % h;

						star = new Star(x, y);

						boolean placeStar = true;
						starPlacementIter = starPlacement.values().iterator();
						while (starPlacementIter.hasNext()) {
							existingStar = starPlacementIter.next();

							if (existingStar.colidesWithOtherStar(star)) {
								placeStar = false;
								continue;
							}
						}

						if (placeStar == false) {
							continue;
						}

						if (a == 3) {
							star.setMines(true);
							star.setMineDistance(Star.starDiameter * 3);
						}

						starPlacement.put(star.getPosition(), star);
						star.draw(g2d);

						a++;
					} while (a < 5);
				} else {
					Iterator<Star> starPlacementIter = starPlacement.values().iterator();
					while (starPlacementIter.hasNext()) {
						star = starPlacementIter.next();

						star.draw(g2d);
					}
				}
			}

			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				doDrawing(g);
			}

			boolean leftMouseClick = false;
			// boolean rightMouseClick = false;

			{
				addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent e) {

						double x = e.getX();
						double y = e.getY();

						Star star = getNearPlanetsStar(x, y);

						if (star != null) {
							klienciMenuItem.setText(star.getName());
							System.out.println(star.getName());
							starName.setText(star.getName());
						} else {
							klienciMenuItem.setText("In Space");
							System.out.println("Star is null");
							starName.setText("");
						}

						if (SwingUtilities.isRightMouseButton(e)) {
							leftMouseClick = false;
							// rightMouseClick = true;

							// System.out.println("Right mouse clicking: " + e.getX() + ":" + e.getY());
						} else {
							// rightMouseClick = false;
							leftMouseClick = true;

							// System.out.println("mousePressed");
							pointStart = e.getPoint();

							String start = isNearAnotherPlanet(x, y);

							pointStart.setLocation(Double.parseDouble(start.split(":")[0]), Double.parseDouble(start.split(":")[1]));

							// System.out.println("+++++++++++++++++++");
						}
					}

					public void mouseReleased(MouseEvent e) {
						if (SwingUtilities.isRightMouseButton(e)) {
							leftMouseClick = false;
							// rightMouseClick = true;
						} else {
							leftMouseClick = false;
							// rightMouseClick = false;

							// System.out.println("mouseReleased");
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
							// System.out.println("+++++++++++++++++++");
						}
					}
				});

				addMouseMotionListener(new MouseMotionAdapter() {
					public void mouseMoved(MouseEvent e) {
						if (leftMouseClick) {
							pointEnd = e.getPoint();

							double x = pointEnd.getX();
							double y = pointEnd.getY();

							String start = isNearAnotherPlanet(x, y);

							pointEnd.setLocation(Double.parseDouble(start.split(":")[0]), Double.parseDouble(start.split(":")[1]));

							// System.out.println("+++++++++++++++++++");
							repaint();
						}
					}

					public void mouseDragged(MouseEvent e) {
						if (leftMouseClick) {
							// System.out.println("mouseDragged");
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
							// System.out.println("Found: " + star.getName());

							found = true;
						}
					}
				}

				return star;
			}
		};

		p.setBackground(Color.black);

		JPanel mainPanel = new JPanel();
		p.setComponentPopupMenu(menuPopup);

		JPanel info = new JPanel();
		info.add(starName);

		mainPanel.setLayout(new BorderLayout());
		mainPanel.setBorder(BorderFactory.createEmptyBorder(GAP, GAP, GAP, GAP));
		mainPanel.add(info, BorderLayout.WEST);
		mainPanel.add(p, BorderLayout.CENTER);

		p.setPreferredSize(new Dimension(500, 300));

		return p;
	}
}
