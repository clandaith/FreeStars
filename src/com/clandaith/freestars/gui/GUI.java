package com.clandaith.freestars.gui;

import javax.swing.JFrame;

/*
 *
 * Copyright Maverik.inc 
 * 
 * Create by Troy Davidson  troy.davidson@maverik.com
 * Created on Jul 24, 2013 1:36:49 PM
 *
 */

public class GUI {
	private static final int GAP = 5;

	public static void main(String[] args) {
		JFrame f = new JFrame("Map");
		f.setSize(600, 300);
		f.setLocation(100, 100);
		f.setResizable(false);

		// JPanel mainPanel = new JPanel();

		// JPanel info = new JPanel();
		// info.add(starName);

		// mainPanel.setLayout(new BorderLayout());
		// mainPanel.setBorder(BorderFactory.createEmptyBorder(GAP, GAP, GAP, GAP));
		// mainPanel.add(info, BorderLayout.WEST);
		// mainPanel.add(p, BorderLayout.CENTER);
		//
		// f.add(mainPanel);
		f.add(new Map().getMap());
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
}
