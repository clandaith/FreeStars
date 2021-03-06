package com.clandaith.freestars.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

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
	private StarInfo starInfo = new StarInfo(mapPanel);

	public void run() {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				createAndShowGUI();
			}
		});
	}

	private void createAndShowGUI() {

		mapPanel.setStarInfo(starInfo);

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

}
