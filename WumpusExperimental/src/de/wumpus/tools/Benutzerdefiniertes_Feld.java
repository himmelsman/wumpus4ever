package de.wumpus.tools;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;

import de.wumpus.beobachter.WumpusGUI;

/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI Builder, which is free for non-commercial use. If Jigloo is being used commercially (ie, by a corporation, company or business for any purpose whatever) then you should
 * purchase a license for each developer using Jigloo. Please visit www.cloudgarden.com for details. Use of Jigloo implies acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE
 * CANNOT BE USED LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class Benutzerdefiniertes_Feld extends JFrame {

	WumpusGUI main;
	JFrame bFeld;
	private JButton schliessenButton;
	private JSpinner wumpYKordSpinner;
	private JSpinner pitYKordSpinner;
	private JLabel yColumnLabel;
	private JLabel xColumnLabel;
	private JLabel positiondesPits;
	private JLabel positiondesWumpus;
	private JLabel positiondesGoldes;
	private JLabel positiondesAgentes;
	private JLabel eingabeGroessedesFeldes;
	private JLabel groessedesFeldes;
//	private JScrollPane jScrollPane1;
	private JSpinner pitXKordSpinner;
	private JSpinner wumpXKordSpinner;
	private JSpinner goldXKordSpinner;
	private JSpinner agentXKordSpinner;
	private JSpinner agentYKordSpinner;
	private JSpinner goldYKordSpinner;
	private final String[] spinnerListSmall = new String[] { "1", "2", "3", "4" };
	private final String[] spinnerListMedium = new String[] { "1", "2", "3", "4", "5", "6", "7", "8" };

	public Benutzerdefiniertes_Feld(WumpusGUI _main) {
		main = _main;
		bFeld = this;
		initGUI();
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				main.setEnabled(true);
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
				this.setPreferredSize(new java.awt.Dimension(500, 300));
				this.setSize(500, 300);
				this.setTitle("Eingabe für benutzerdefiniertes Feld");
				thisLayout.rowWeights = new double[] { 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.01 };
				thisLayout.rowHeights = new int[] { 7, 7, 7, 7, 7, 7, 7 };
				thisLayout.columnWeights = new double[] { 0.5, 0.3, 0.3 };
				thisLayout.columnWidths = new int[] { 7, 7, 7 };
				getContentPane().setLayout(thisLayout);
				{
					groessedesFeldes = new JLabel();
					getContentPane().add(groessedesFeldes, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					groessedesFeldes.setText("Groesse des Feldes");
				}
				{
					eingabeGroessedesFeldes = new JLabel();
					getContentPane().add(eingabeGroessedesFeldes, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					eingabeGroessedesFeldes.setText("4x4");
				}
				{
					positiondesAgentes = new JLabel();
					getContentPane().add(positiondesAgentes, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					positiondesAgentes.setText("Position des Agentes:");
				}
				{
					positiondesGoldes = new JLabel();
					getContentPane().add(positiondesGoldes, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					positiondesGoldes.setText("Position des Goldes");
				}
				{
					positiondesWumpus = new JLabel();
					getContentPane().add(positiondesWumpus, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					positiondesWumpus.setText("Position des Wumpus");
				}
				{
					positiondesPits = new JLabel();
					getContentPane().add(positiondesPits, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					positiondesPits.setText("Position des Pits");
				}

				{
					schliessenButton = new JButton();
					getContentPane().add(schliessenButton, new GridBagConstraints(1, 6, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					schliessenButton.setText("Schließen");
					schliessenButton.addMouseListener(new MouseListener() {
						@Override
						public void mouseClicked(MouseEvent e) {
							if (e.getClickCount() >= 1) {
								 System.out.println("Agent(" + agentYKordSpinner.getValue() + "|" + agentXKordSpinner.getValue() + ")");
								 System.out.println("Gold(" + goldYKordSpinner.getValue() + "|" + goldXKordSpinner.getValue() + ")");
								 System.out.println("Wumpus(" + wumpYKordSpinner.getValue() + "|" + wumpXKordSpinner.getValue() + ")");
								 System.out.println("Pit(" + pitYKordSpinner.getValue() + "|" + pitXKordSpinner.getValue() + ")");
								int[] positionen = new int[] { Integer.valueOf(agentYKordSpinner.getValue().toString()) -1, Integer.valueOf(agentXKordSpinner.getValue().toString()) -1,
										Integer.valueOf(goldYKordSpinner.getValue().toString()) -1, Integer.valueOf(goldXKordSpinner.getValue().toString()) -1, Integer.valueOf(wumpYKordSpinner.getValue().toString()) -1,
										Integer.valueOf(wumpXKordSpinner.getValue().toString()) -1, Integer.valueOf(pitYKordSpinner.getValue().toString()) -1, Integer.valueOf(pitXKordSpinner.getValue().toString()) -1 };
								String pruefung = PositionenPruefen.pruefePositionen(positionen);
								
								if (pruefung == "Nichts") {
									main.wump.neuesSpiel(4, positionen);
									main.setEnabled(true);
									dispose();
								} else {
									JOptionPane.showMessageDialog(bFeld, pruefung, "Fehler", JOptionPane.WARNING_MESSAGE);
								}
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
					xColumnLabel = new JLabel();
					getContentPane().add(xColumnLabel, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					xColumnLabel.setText("Y - Koordinaten");
				}
				{
					yColumnLabel = new JLabel();
					getContentPane().add(yColumnLabel, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					yColumnLabel.setText("X - Koordinaten");
				}
				{
					SpinnerListModel pitYKordSpinnerModel = new SpinnerListModel(spinnerListSmall);
					// pitYKordSpinnerModel.setValue("0,1,2,3");
					pitYKordSpinner = new JSpinner();
					getContentPane().add(pitYKordSpinner, new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					pitYKordSpinner.setModel(pitYKordSpinnerModel);
					pitYKordSpinner.setPreferredSize(new java.awt.Dimension(45, 21));
				}
				{
					SpinnerListModel wumpYKordSpinnerModel = new SpinnerListModel(spinnerListSmall);
					// wumpYKordSpinnerModel.setValue("0,1,2,3");
					wumpYKordSpinner = new JSpinner();
					getContentPane().add(wumpYKordSpinner, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					wumpYKordSpinner.setModel(wumpYKordSpinnerModel);
					wumpYKordSpinner.setPreferredSize(new java.awt.Dimension(45, 21));
				}
				{
					SpinnerListModel goldYKordSpinnerModel = new SpinnerListModel(spinnerListSmall);
					// goldYKordSpinnerModel.setValue("0,1,2,3");
					goldYKordSpinner = new JSpinner();
					getContentPane().add(goldYKordSpinner, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					goldYKordSpinner.setModel(goldYKordSpinnerModel);
					goldYKordSpinner.setPreferredSize(new java.awt.Dimension(45, 21));
				}
				{
					SpinnerListModel agentYKordSpinnerModel = new SpinnerListModel(spinnerListSmall);
					// agentYKordSpinnerModel.setValue("0,1,2,3");
					agentYKordSpinner = new JSpinner();
					getContentPane().add(agentYKordSpinner, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					agentYKordSpinner.setModel(agentYKordSpinnerModel);
					agentYKordSpinner.setPreferredSize(new java.awt.Dimension(45, 21));
				}
				{
					SpinnerListModel agentXKordSpinnerModel = new SpinnerListModel(spinnerListSmall);
					// agentXKordSpinnerModel.setValue("0,1,2,3");
					agentXKordSpinner = new JSpinner();
					getContentPane().add(agentXKordSpinner, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					agentXKordSpinner.setModel(agentXKordSpinnerModel);
					agentXKordSpinner.setPreferredSize(new java.awt.Dimension(45, 21));
				}
				{
					SpinnerListModel goldXKordSpinnerModel = new SpinnerListModel(spinnerListSmall);
					// goldXKordSpinnerModel.setValue("0,1,2,3");
					goldXKordSpinner = new JSpinner();
					getContentPane().add(goldXKordSpinner, new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					goldXKordSpinner.setModel(goldXKordSpinnerModel);
					goldXKordSpinner.setPreferredSize(new java.awt.Dimension(45, 21));
				}
				{
					SpinnerListModel wumpXKordSpinnerModel = new SpinnerListModel(spinnerListSmall);
					// wumpXKordSpinnerModel.setValue("0,1,2,3");
					wumpXKordSpinner = new JSpinner();
					getContentPane().add(wumpXKordSpinner, new GridBagConstraints(2, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					wumpXKordSpinner.setModel(wumpXKordSpinnerModel);
					wumpXKordSpinner.setPreferredSize(new java.awt.Dimension(45, 21));
				}
				{
					SpinnerListModel pitXKordSpinnerModel = new SpinnerListModel(spinnerListSmall);
					// pitXKordSpinnerModel.setValue("0,1,2,3");
					pitXKordSpinner = new JSpinner();
					getContentPane().add(pitXKordSpinner, new GridBagConstraints(2, 5, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					pitXKordSpinner.setModel(pitXKordSpinnerModel);
					pitXKordSpinner.setPreferredSize(new java.awt.Dimension(45, 21));
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
