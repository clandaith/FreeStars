package com.clandaith.freestars.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

/**
 * @author troyd
 * 
 *         Copyright Maverik, inc 2013
 * 
 */
public class MenuBar {

	public static JMenuBar createMenuBar() {

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
}
