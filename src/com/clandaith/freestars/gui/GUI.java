package com.clandaith.freestars.gui;

import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;

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

	public void run() {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}

	private void createAndShowGUI() {
		JFrame f = new JFrame("Map");
		f.setSize(600, 300);
		f.setLocation(100, 100);
		f.setPreferredSize(new Dimension(600, 300));
		f.setResizable(true);

		f.add(buildContent());
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}

	private JComponent buildContent() {
		FormLayout layout = new FormLayout("pref, 4dlu, pref", "pref, 4dlu, pref");

		JLabel starInfo = new StarInfo();
		starInfo.setText("asdfasd f sda fds");

		PanelBuilder builder = new PanelBuilder(layout);
		builder.addLabel("&Planet Info:", CC.xy(1, 1));
		builder.addLabel("&Map:", CC.xy(3, 1));
		builder.add(starInfo, CC.xy(1, 3));
		builder.add(new Map().getMap(), CC.xy(3, 3));

		return builder.getPanel();
	}
}
