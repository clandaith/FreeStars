package com.clandaith.freestars.gui;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;

/**
 * @author troyd
 * 
 *         Copyright Maverik, inc 2013
 * 
 */
public class MainGUI {

	public void run() {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				createAndShowGUI();
			}
		});
	}

	private void createAndShowGUI() {
		JFrame mainFrame = new JFrame("FreeStars!");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		mainFrame.setLocation(100, 100);
		mainFrame.setResizable(true);

		mainFrame.setJMenuBar(MenuBar.createMenuBar());

		mainFrame.add(buildContent());
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setVisible(true);
		mainFrame.pack();
	}

	private JComponent buildContent() {
		FormLayout layout = new FormLayout("4dlu, pref, 4dlu", "4dlu, pref, 4dlu");

		PanelBuilder builder = new PanelBuilder(layout);
		builder.add(_mainSplitPane(), CC.xy(2, 4));

		return builder.getPanel();
	}

	private JSplitPane _mainSplitPane() {
		JSplitPane splitPane = new JSplitPane();

		
		
		return splitPane;
	}
	
	private JScrollPane _
}
