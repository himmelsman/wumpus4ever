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
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;

/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI Builder, which is free for non-commercial use. If Jigloo is being used commercially (ie, by a corporation, company or business for any purpose whatever) then you should
 * purchase a license for each developer using Jigloo. Please visit www.cloudgarden.com for details. Use of Jigloo implies acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE
 * CANNOT BE USED LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class Benutzerdefiniertes_Feld extends JFrame {

	JFrame main;
	private JButton schliessenButton;
	private JSpinner wumpYKordSpinner;
	private JSpinner pitYKordSpinner;
	private JLabel yColumnLabel;
	private JLabel xColumnLabel;
	private JTextArea positiondesPits;
	private JTextArea positiondesWumpus;
	private JTextArea positiondesGoldes;
	private JTextArea positiondesAgentes;
	private JTextArea eingabeGroessedesFeldes;
	private JTextArea groessedesFeldes;
	private JScrollPane jScrollPane1;
	private JSpinner pitXKordSpinner;
	private JSpinner wumpXKordSpinner;
	private JSpinner goldXKordSpinner;
	private JSpinner agentXKordSpinner;
	private JSpinner agentYKordSpinner;
	private JSpinner goldYKordSpinner;
	private final String[] spinnerListSmall = new String[] { "1", "2", "3", "4" };
	private final String[] spinnerListMedium = new String[] { "1", "2", "3", "4", "5", "6", "7", "8" };

	public Benutzerdefiniertes_Feld(JFrame _main) {
		main = _main;
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
				thisLayout.rowWeights = new double[] { 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.01 };
				thisLayout.rowHeights = new int[] { 7, 7, 7, 7, 7, 7, 7 };
				thisLayout.columnWeights = new double[] { 0.5, 0.3, 0.3 };
				thisLayout.columnWidths = new int[] { 7, 7, 7 };
				getContentPane().setLayout(thisLayout);
				{
					groessedesFeldes = new JTextArea();
					getContentPane().add(groessedesFeldes, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					groessedesFeldes.setText("Groesse des Feldes");
					groessedesFeldes.setEditable(false);
				}
				{
					eingabeGroessedesFeldes = new JTextArea();
					getContentPane().add(eingabeGroessedesFeldes, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					eingabeGroessedesFeldes.setText("4x4");
					eingabeGroessedesFeldes.setEditable(false);
				}
				{
					positiondesAgentes = new JTextArea();
					getContentPane().add(positiondesAgentes, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					positiondesAgentes.setText("Position des Agentes:");
					positiondesAgentes.setEditable(false);
				}
				{
					positiondesGoldes = new JTextArea();
					getContentPane().add(positiondesGoldes, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					positiondesGoldes.setText("Position des Goldes");
					positiondesGoldes.setEditable(false);
				}
				{
					positiondesWumpus = new JTextArea();
					getContentPane().add(positiondesWumpus, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					positiondesWumpus.setText("Position des Wumpus");
					positiondesWumpus.setEditable(false);
				}
				{
					positiondesPits = new JTextArea();
					getContentPane().add(positiondesPits, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					positiondesPits.setText("Position des Pits");
					positiondesPits.setEditable(false);
				}

				{
					schliessenButton = new JButton();
					getContentPane().add(schliessenButton, new GridBagConstraints(1, 6, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					schliessenButton.setText("Schließen");
					schliessenButton.addMouseListener(new MouseListener() {
						@Override
						public void mouseClicked(MouseEvent e) {
							if (e.getClickCount() >= 1) {
								main.setEnabled(true);
								dispose();
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
					pitYKordSpinner = new JSpinner();
					getContentPane().add(pitYKordSpinner, new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					pitYKordSpinner.setModel(pitYKordSpinnerModel);
				}
				{
					SpinnerListModel wumpYKordSpinnerModel = new SpinnerListModel(spinnerListSmall);
					wumpYKordSpinner = new JSpinner();
					getContentPane().add(wumpYKordSpinner, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					wumpYKordSpinner.setModel(wumpYKordSpinnerModel);
				}
				{
					SpinnerListModel goldYKordSpinnerModel = new SpinnerListModel(spinnerListSmall);
					goldYKordSpinner = new JSpinner();
					getContentPane().add(goldYKordSpinner, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					goldYKordSpinner.setModel(goldYKordSpinnerModel);
				}
				{
					SpinnerListModel agentYKordSpinnerModel = new SpinnerListModel(spinnerListSmall);
					agentYKordSpinner = new JSpinner();
					getContentPane().add(agentYKordSpinner, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					agentYKordSpinner.setModel(agentYKordSpinnerModel);
				}
				{
					SpinnerListModel agentXKordSpinnerModel = new SpinnerListModel(spinnerListSmall);
					agentXKordSpinner = new JSpinner();
					getContentPane().add(agentXKordSpinner, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					agentXKordSpinner.setModel(agentXKordSpinnerModel);
				}
				{
					SpinnerListModel goldXKordSpinnerModel = new SpinnerListModel(spinnerListSmall);
					goldXKordSpinner = new JSpinner();
					getContentPane().add(goldXKordSpinner, new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					goldXKordSpinner.setModel(goldXKordSpinnerModel);
				}
				{
					SpinnerListModel wumpXKordSpinnerModel = new SpinnerListModel(spinnerListSmall);
					wumpXKordSpinner = new JSpinner();
					getContentPane().add(wumpXKordSpinner, new GridBagConstraints(2, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					wumpXKordSpinner.setModel(wumpXKordSpinnerModel);
				}
				{
					SpinnerListModel pitXKordSpinnerModel = new SpinnerListModel(spinnerListSmall);
					pitXKordSpinner = new JSpinner();
					getContentPane().add(pitXKordSpinner, new GridBagConstraints(2, 5, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					pitXKordSpinner.setModel(pitXKordSpinnerModel);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
