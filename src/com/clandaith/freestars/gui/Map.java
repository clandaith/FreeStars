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
import java.util.ArrayList;
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
	private static int starDiameter = 14;
	private static int searchMultiplier = 1;

	private static JMenuItem klienciMenuItem = new JMenuItem("");
	private static JPopupMenu menuPopup = new JPopupMenu();
	private static JTextArea starName = new JTextArea();
	private static final int GAP = 5;

	private static ArrayList<String> starNames = new ArrayList<String>();

	public static void main(String args[]) throws Exception {
		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
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
		f.setResizable(true);

		// JPanel mainPanel = new JPanel();
		// mainPanel.add(new Map().getMap());
		// f.add(mainPanel);

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

			String[] letters = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K" };
			Random r = new Random();

			private String getStarName() {
				String name = "";
				String letter;
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

			private void doDrawing(Graphics g) {
				Graphics2D g2d = (Graphics2D)g;

				g2d.setColor(Color.red);

				Dimension size = getSize();
				Insets insets = getInsets();

				int w = size.width - (starDiameter * 3) - insets.left - insets.right;
				int h = size.height - (starDiameter * 3) - insets.top - insets.bottom;

				Star star;

				if (starPlacement == null) {
					starPlacement = new Hashtable<String, Star>();

					for (int i = 0; i < 5; i++) {

						int x = Math.abs(r.nextInt()) % w;
						int y = Math.abs(r.nextInt()) % h;

						star = new Star();
						star.setX(x);
						star.setY(y);
						star.setName(getStarName());

						if (i == 3) {
							star.setMines(true);
							star.setMineDistance(starDiameter * 3);
							g2d.setColor(Color.blue);
							g2d.fillOval(star.getX() - (star.getMineDistance() / 2), star.getY() - (star.getMineDistance() / 2),
											star.getMineDistance(), star.getMineDistance());
						}

						g2d.setColor(Color.red);
						starPlacement.put(star.getPosition(), star);
						g2d.fillOval(x - (starDiameter / 2), y - (starDiameter / 2), starDiameter, starDiameter);
					}
				} else {
					Iterator<Star> starPlacementIter = starPlacement.values().iterator();
					while (starPlacementIter.hasNext()) {
						star = starPlacementIter.next();

						if (star.hasMines()) {
							g2d.setColor(Color.blue);
							g2d.fillOval(star.getX() - (star.getMineDistance() / 2), star.getY() - (star.getMineDistance() / 2),
											star.getMineDistance(), star.getMineDistance());
						}

						g2d.setColor(Color.red);
						g2d.fillOval(star.getX() - (starDiameter / 2), star.getY() - (starDiameter / 2), starDiameter, starDiameter);
					}
				}
			}

			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				doDrawing(g);
			}

			boolean leftMouseClick = false;
			boolean rightMouseClick = false;

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
							rightMouseClick = true;

							// System.out.println("Right mouse clicking: " + e.getX() + ":" + e.getY());
						} else {
							rightMouseClick = false;
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
							rightMouseClick = true;
						} else {
							leftMouseClick = false;
							rightMouseClick = false;

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
						g.drawLine(pointStart.x - (starDiameter / 2), pointStart.y - (starDiameter / 2), pointEnd.x - (starDiameter / 2),
										pointEnd.y - (starDiameter / 2));
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

				for (int a = (x - (starDiameter * searchMultiplier)); a <= x + (starDiameter * searchMultiplier); a++) {
					for (int b = (y - (starDiameter * searchMultiplier)); b <= y + (starDiameter * searchMultiplier); b++) {

						checkPoint = a + ":" + b;

						if (found == false && starPlacement.containsKey(checkPoint)) {
							a += (starDiameter / 2);
							b += (starDiameter / 2);
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

				String position = x + ":" + y;
				String checkPoint = "";

				// System.out.println("Start: " + position);
				boolean found = false;

				for (int a = (x - (starDiameter * searchMultiplier)); a <= x + (starDiameter * searchMultiplier); a++) {
					for (int b = (y - (starDiameter * searchMultiplier)); b <= y + (starDiameter * searchMultiplier); b++) {

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
