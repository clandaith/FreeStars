package com.clandaith.freestars.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.clandaith.freestars.data.star.Star;
import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;

/**
 * @author troyd
 * 
 *         Copyright Maverik, inc 2013
 * 
 */
public class StarInfo {
	private JPanel starInfoPanel = new JPanel();
	private Star star;
	private int currentViewingStar = 0;
	private ArrayList<Star> stars;
	private JLabel starName = new JLabel();
	private JPanel starImage = new JPanel();
	private JButton btn_Prev = new JButton();
	private JButton btn_Next = new JButton();

	private Map mapPanel;

	public StarInfo(Map mapPanel) {
		this.mapPanel = mapPanel;
	}

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
