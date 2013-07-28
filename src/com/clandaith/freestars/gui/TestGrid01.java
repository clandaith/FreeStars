package com.clandaith.freestars.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class TestGrid01 {

	public static void main(String[] args) {
		new TestGrid01();
	}

	public TestGrid01() {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
				}

				JFrame frame = new JFrame("Testing");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setLayout(new BorderLayout());
				frame.add(new TestPane());
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}

	public class TestPane extends JPanel {

		public TestPane() {
			setLayout(new GridBagLayout());

			GridBagConstraints gbc = new GridBagConstraints();
			for (int row = 0; row < 50; row++) {
				for (int col = 0; col < 50; col++) {
					gbc.gridx = col;
					gbc.gridy = row;

					CellPane cellPane = new CellPane(row, col);
					// Border border = null;
					// if (row < 4) {
					// if (col < 4) {
					// border = new MatteBorder(1, 1, 0, 0, Color.GRAY);
					// } else {
					// border = new MatteBorder(1, 1, 0, 1, Color.GRAY);
					// }
					// } else {
					// if (col < 4) {
					// border = new MatteBorder(1, 1, 1, 0, Color.GRAY);
					// } else {
					// border = new MatteBorder(1, 1, 1, 1, Color.GRAY);
					// }
					// }
					// cellPane.setBorder(border);
					add(cellPane, gbc);
				}
			}
		}
	}

	public class CellPane extends JPanel {

		int row;
		int col;

		private Color defaultBackground;

		public CellPane(final int row, final int col) {
			this.row = row;
			this.col = col;

			addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					defaultBackground = getBackground();
					setBackground(Color.BLUE);
					// System.out.println(row + " :: " + col);
				}

				@Override
				public void mouseExited(MouseEvent e) {
					setBackground(defaultBackground);
				}

				@Override
				public void mouseClicked(MouseEvent e) {
					// System.out.println("Clicked in: " + row + " :: " + col);
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					// System.out.println("Released in: " + row + " :: " + col);
				}

				@Override
				public void mouseDragged(MouseEvent e) {
					System.out.println("Clicked in: " + row + " :: " + col);
					System.out.println("Released in: " + row + " :: " + col);
				}
			});
		}

		@Override
		public Dimension getPreferredSize() {
			return new Dimension(10, 10);
		}
	}
}