package de.wumpus.tools;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import de.wumpus.beobachter.WumpusGUI;

public class GameOver extends JFrame {

	private WumpusGUI main;
	private JButton jButton1;
	private JButton jButton2;
	private JLabel gameOverTextArea;
	private String messageText;
	private GameOver gameOverMain;

	public GameOver(WumpusGUI _main, String message) {
		super("Spiel beendet");
		main = _main;
		gameOverMain = this;
		messageText = message;
		main.setEnabled(false);
		initGUI();
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				main.dispose();
				dispose();
			}

		});
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void initGUI() {
		try {
			{
				GridBagLayout thisLayout = new GridBagLayout();
				setPreferredSize(new java.awt.Dimension(400, 200));
				setSize(new java.awt.Dimension(400, 200));
				thisLayout.rowWeights = new double[] { 0.1, 0.01, 0.1 };
				thisLayout.rowHeights = new int[] { 7, 7, 7 };
				thisLayout.columnWeights = new double[] { 0.1, 0.1, 0.1, 0.1, 0.1 };
				thisLayout.columnWidths = new int[] { 7, 7, 7, 7, 7 };
				getContentPane().setLayout(thisLayout);
				{
					{
						gameOverTextArea = new JLabel();
						gameOverTextArea.setText(messageText);
						getContentPane().add(gameOverTextArea, new GridBagConstraints(0, 0, 5, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

					}
				}
				{
					jButton1 = new JButton();
					getContentPane().add(jButton1, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					jButton1.setText("Neues Spiel");
					jButton1.addMouseListener(new MouseListener() {
						@Override
						public void mouseClicked(MouseEvent e) {
							if (e.getClickCount() >= 1) {
								new NeuesSpielDialog(gameOverMain);
							}
						}

						@Override
						public void mouseEntered(MouseEvent e) {
						}

						@Override
						public void mouseExited(MouseEvent e) {
						}

						@Override
						public void mousePressed(MouseEvent e) {
						}

						@Override
						public void mouseReleased(MouseEvent e) {
						}

					});
				}
				{
					jButton2 = new JButton();
					getContentPane().add(jButton2, new GridBagConstraints(3, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					jButton2.setText("Programm beenden");
					jButton2.addMouseListener(new MouseListener() {
						@Override
						public void mouseClicked(MouseEvent e) {
							if (e.getClickCount() >= 1) {
								dispose();
								main.dispose();

							}
						}

						@Override
						public void mouseEntered(MouseEvent e) {
						}

						@Override
						public void mouseExited(MouseEvent e) {
						}

						@Override
						public void mousePressed(MouseEvent e) {
						}

						@Override
						public void mouseReleased(MouseEvent e) {
						}

					});
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private class NeuesSpielDialog extends JFrame {
		GameOver gameOver;
		JButton zufallKlein;
		JButton zufallGross;
		JButton benutzerdefiniert;

		public NeuesSpielDialog(GameOver fenster) {
			super("Neues Spielfeld erzeugen:");
			gameOver = fenster;
			gameOver.setVisible(false);
			initDialog();
			addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					dispose();
					gameOver.setVisible(true);
				}

			});
			setLocationRelativeTo(null);
			setVisible(true);
		}

		private void initDialog() {
			GridBagLayout thisLayout = new GridBagLayout();
			setPreferredSize(new java.awt.Dimension(400, 200));
			setSize(new java.awt.Dimension(400, 200));
			thisLayout.rowWeights = new double[] { 0.1 };
			thisLayout.rowHeights = new int[] { 7 };
			thisLayout.columnWeights = new double[] { 0.01, 0.1, 0.01, 0.1, 0.01, 0.1, 0.01 };
			thisLayout.columnWidths = new int[] { 7, 7, 7, 7, 7, 7, 7 };
			getContentPane().setLayout(thisLayout);
			{
				zufallKlein = new JButton();
				zufallKlein.setText("Zufall 4x4");
				getContentPane().add(zufallKlein, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				zufallKlein.addMouseListener(new MouseListener() {
					@Override
					public void mouseClicked(MouseEvent e) {
						if (e.getClickCount() >= 1) {
							main.setEnabled(true);
							main.neuesSpiel(4);
							dispose();
							gameOver.dispose();
						}
					}

					@Override
					public void mouseEntered(MouseEvent e) {
					}

					@Override
					public void mouseExited(MouseEvent e) {
					}

					@Override
					public void mousePressed(MouseEvent e) {
					}

					@Override
					public void mouseReleased(MouseEvent e) {
					}

				});
			}
			{
				zufallGross = new JButton();
				zufallGross.setText("Zufall 8x8");
				getContentPane().add(zufallGross, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				zufallGross.addMouseListener(new MouseListener() {
					@Override
					public void mouseClicked(MouseEvent e) {
						if (e.getClickCount() >= 1) {
							main.setEnabled(true);
							main.neuesSpiel(8);
							dispose();
							gameOver.dispose();
						}
					}

					@Override
					public void mouseEntered(MouseEvent e) {
					}

					@Override
					public void mouseExited(MouseEvent e) {
					}

					@Override
					public void mousePressed(MouseEvent e) {
					}

					@Override
					public void mouseReleased(MouseEvent e) {
					}

				});

			}
			{
				benutzerdefiniert = new JButton();
				benutzerdefiniert.setText("Benutzerdefiniertes Feld");
				getContentPane().add(benutzerdefiniert, new GridBagConstraints(5, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				benutzerdefiniert.addMouseListener(new MouseListener() {
					@Override
					public void mouseClicked(MouseEvent e) {
						if (e.getClickCount() >= 1) {
							main.setEnabled(true);
							main.neuesSpiel(-1);
							dispose();
							gameOver.dispose();
						}
					}

					@Override
					public void mouseEntered(MouseEvent e) {
					}

					@Override
					public void mouseExited(MouseEvent e) {
					}

					@Override
					public void mousePressed(MouseEvent e) {
					}

					@Override
					public void mouseReleased(MouseEvent e) {
					}

				});

			}
		}

	}
}
