package com.clandaith.freestars.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.SwingUtilities;

import com.clandaith.freestars.Utils;
import com.clandaith.freestars.data.star.Star;
import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;

/*
 *
 * Copyright Troy Davidson
 * 
 * Create by Troy Davidson  troy@clandaith.com
 * Created on Jul 28, 2013 9:44:54 AM
 *
 */

public class GUI {

	private Map mapPanel = new Map();
	private StarInfo starInfo = new StarInfo();

	public void run() {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}

	private void createAndShowGUI() {
		JFrame f = new JFrame("Map");
		f.setLocation(100, 100);
		f.setResizable(true);

		f.setJMenuBar(createMenuBar());

		f.add(buildContent());
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		f.pack();
	}

	private JMenuBar createMenuBar() {

		final JFrame aboutFrame = new JFrame();
		JMenuBar menuBar = new JMenuBar();
		JMenu menu, submenu;
		JMenuItem menuItem;
		JRadioButtonMenuItem rbMenuItem;
		JCheckBoxMenuItem cbMenuItem;

		// Build the first menu.
		menu = new JMenu("File");
		menu.setMnemonic(KeyEvent.VK_F);
		menuBar.add(menu);

		// a group of JMenuItems
		menuItem = new JMenuItem("Exit", KeyEvent.VK_X);
		menuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		menu.add(menuItem);

		// Build second menu in the menu bar.
		menu = new JMenu("Help");
		menu.setMnemonic(KeyEvent.VK_H);
		menuBar.add(menu);

		menuItem = new JMenuItem("About");
		menuItem.setMnemonic(KeyEvent.VK_A);
		menuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (aboutFrame.isVisible() == false) {
					JLabel aboutLabel = new JLabel("This is the about window.");

					aboutFrame.add(aboutLabel);

					aboutFrame.pack();
					aboutFrame.setAlwaysOnTop(true);
					aboutFrame.setVisible(true);
					aboutFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				} else {
					aboutFrame.setVisible(true);
				}
			}
		});
		menu.add(menuItem);

		return menuBar;
	}

	private JComponent buildContent() {
		FormLayout layout = new FormLayout("4dlu, pref, 4dlu, pref, 4dlu", "4dlu, pref, 4dlu, pref, 4dlu");

		starInfo.setStarPlacement(new ArrayList<Star>(mapPanel.getStarPlacement().values()));

		PanelBuilder builder = new PanelBuilder(layout);
		builder.addLabel("&Planet Info:", CC.xy(2, 2));
		builder.addLabel("&Map:", CC.xy(4, 2));
		builder.add(starInfo.getStarInfoPanel(), CC.xy(2, 4));
		builder.add(mapPanel.getMap(), CC.xy(4, 4));

		return builder.getPanel();
	}

	class Map {
		private Hashtable<String, Star> starPlacement = new Hashtable<String, Star>();
		private boolean starsPlaced = false;
		private int searchMultiplier = 1;
		private JPanel p;

		private JMenuItem klienciMenuItem = new JMenuItem("");

		private JPopupMenu menuPopup = new JPopupMenu();

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

			p.setBorder(BorderFactory.createLoweredBevelBorder());
			p.setBackground(Color.black);
			p.setComponentPopupMenu(menuPopup);
			p.setPreferredSize(new Dimension(500, 300));

			return p;
		}
	}

	class StarInfo {
		private JPanel starInfoPanel = new JPanel();
		private Star star;
		private int currentViewingStar = 0;
		private ArrayList<Star> stars;
		private JLabel starName = new JLabel();
		private JPanel starImage = new JPanel();
		private JButton btn_Prev = new JButton();
		private JButton btn_Next = new JButton();

		public void setStarPlacement(ArrayList<Star> starPlacement) {
			stars = starPlacement;
			star = starPlacement.get(currentViewingStar);
			starInfoPanel.add(buildPanel());

			modifyStarInfoPanel();
		}

		public JComponent buildPanel() {
			FormLayout layout = new FormLayout("pref, 4dlu, pref", "pref, 4dlu, pref, 4dlu, pref");

			btn_Prev.setText("Prev");
			btn_Prev.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					currentViewingStar--;
					if (currentViewingStar == -1) {
						currentViewingStar = stars.size() - 1;
					}

					star = stars.get(currentViewingStar);

					mapPanel.highlightStar(star);
					setStar(star);
				}
			});
			btn_Next.setText("Next");
			btn_Next.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					currentViewingStar++;
					if (currentViewingStar > stars.size() - 1) {
						currentViewingStar = 0;
					}

					star = stars.get(currentViewingStar);
					mapPanel.highlightStar(star);
					setStar(star);
				}
			});

			PanelBuilder builder = new PanelBuilder(layout);
			builder.add(starName, CC.xyw(1, 1, 3));
			builder.add(starImage, CC.xywh(3, 1, 1, 3));
			builder.add(btn_Prev, CC.xy(3, 3));
			builder.add(btn_Next, CC.xy(3, 5));

			return builder.getPanel();
		}

		public void setStar(Star star) {
			this.star = star;

			modifyStarInfoPanel();
		}

		private void modifyStarInfoPanel() {
			starName.setText(star.getName());
		}

		public JPanel getStarInfoPanel() {
			return starInfoPanel;
		}
	}

}
