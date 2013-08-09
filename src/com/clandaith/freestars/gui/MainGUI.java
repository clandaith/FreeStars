package com.clandaith.freestars.gui;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ScrollPaneConstants;

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

	private Map map = new Map();

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
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setResizable(true);

		mainFrame.setJMenuBar(MenuBar.createMenuBar());

		mainFrame.getContentPane().add(buildContent());
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setVisible(true);
		mainFrame.pack();
	}

	private JComponent buildContent() {
		FormLayout layout = new FormLayout("2dlu, pref:grow, 2dlu", "2dlu, fill:pref:grow, 2dlu");

		PanelBuilder builder = new PanelBuilder(layout);
		builder.add(_mainPanel(), CC.xy(2, 2));

		return builder.getPanel();
	}

	private JPanel _mainPanel() {
		JPanel mainPanel = new JPanel();

		JSplitPane mainScrollPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, _leftPanel(), _rightPanel());
		mainPanel.add(mainScrollPane);

		return mainPanel;
	}

	private JPanel _leftPanel() {
		// JPanel leftPanel = new JPanel();

		FormLayout layout = new FormLayout(
						"2dlu, pref:grow, 2dlu",
						"2dlu, fill:pref:grow, 2dlu, fill:pref:grow, 2dlu, fill:pref:grow, 2dlu, fill:pref:grow, 2dlu, fill:pref:grow, 2dlu, fill:pref:grow, 2dlu, fill:pref:grow, 2dlu, fill:pref:grow, 2dlu, fill:pref:grow, 2dlu, fill:pref:grow, 2dlu");

		JLabel _lableTest1 = new JLabel("Test");
		JLabel _lableTest2 = new JLabel("Test");
		JLabel _lableTest3 = new JLabel("Test");
		JLabel _lableTest4 = new JLabel("Test");
		JLabel _lableTest5 = new JLabel("Test");
		JLabel _lableTest6 = new JLabel("Test");
		JLabel _lableTest7 = new JLabel("Test");
		JLabel _lableTest8 = new JLabel("Test");
		JLabel _lableTest9 = new JLabel("Test");

		PanelBuilder builder = new PanelBuilder(layout);
		builder.add(_lableTest1, CC.xy(2, 2));
		builder.add(_lableTest2, CC.xy(2, 4));
		builder.add(_lableTest3, CC.xy(2, 6));
		builder.add(_lableTest4, CC.xy(2, 8));
		builder.add(_lableTest5, CC.xy(2, 10));
		builder.add(_lableTest6, CC.xy(2, 12));
		builder.add(_lableTest7, CC.xy(2, 14));
		builder.add(_lableTest8, CC.xy(2, 16));
		builder.add(_lableTest9, CC.xy(2, 18));

		return builder.getPanel();
	}

	private JPanel _rightPanel() {
		JPanel rightPanel = new JPanel();

		JSplitPane mapSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, _mapPanel(), _mapInfoPanel());
		rightPanel.add(mapSplitPane);

		return rightPanel;
	}

	private JPanel _mapPanel() {
		JScrollPane mapPane = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
						ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		mapPane.add(map.getMap());
		JPanel mapPanel = new JPanel();
		mapPanel.add(mapPane);

		return mapPanel;
	}

	private JPanel _mapInfoPanel() {
		JPanel mapInfoPanel = new JPanel();

		return mapInfoPanel;
	}
}
