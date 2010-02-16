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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class Benutzerdefiniertes_Feld extends JFrame{

		JFrame main;
		private JButton schliessenButton;
		private JTextField gold_Y_koor;
		private JTextField pit_X_koor;
		private JTextField pit_Y_koor;
		private JTextArea positiondesPits;
		private JTextField wumpus_X_koor;
		private JTextField wumpus_Y_koor;
		private JTextArea positiondesWumpus;
		private JTextField gold_X_koor;
		private JTextArea positiondesGoldes;
		private JTextField agent_X_koor;
		private JTextField agent_Y_koor;
		private JTextArea positiondesAgentes;
		private JTextArea eingabeGroessedesFeldes;
		private JTextArea groessedesFeldes;
		private JScrollPane jScrollPane1;

		public Benutzerdefiniertes_Feld(JFrame _main){
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
					setPreferredSize(new java.awt.Dimension(700, 500));
					setSize(new java.awt.Dimension(700, 500));
					thisLayout.rowWeights = new double[] {0.1, 0.1, 0.1, 0.1, 0.1, 0.01};
					thisLayout.rowHeights = new int[] {7, 7, 7, 7, 7, 7};
					thisLayout.columnWeights = new double[] {0.1, 0.1, 0.1, 0.1};
					thisLayout.columnWidths = new int[] {7, 7, 7, 7};
					getContentPane().setLayout(thisLayout);
					{
						groessedesFeldes = new JTextArea();
						getContentPane().add(groessedesFeldes, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
						groessedesFeldes.setText("Groesse des Feldes");
						groessedesFeldes.setEditable(false);
					}
					{
						eingabeGroessedesFeldes = new JTextArea();
						getContentPane().add(eingabeGroessedesFeldes, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
						eingabeGroessedesFeldes.setText("4x4");
						eingabeGroessedesFeldes.setEditable(false);
					}
					{
						positiondesAgentes = new JTextArea();
						getContentPane().add(positiondesAgentes, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
						positiondesAgentes.setText("Position des Agentes:");
						positiondesAgentes.setEditable(false);
					}
					{
						agent_Y_koor = new JTextField();
						//TODO: eingabe feld basteln
//						BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
						getContentPane().add(agent_Y_koor, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
						agent_Y_koor.setText("Y=");
//						Integer.parseInt(br.readLine());
					}
					{
						agent_X_koor = new JTextField();
						getContentPane().add(agent_X_koor, new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
						agent_X_koor.setText("X=");
					}
					{
						positiondesGoldes= new JTextArea();
						getContentPane().add(positiondesGoldes, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
						positiondesGoldes.setText("Position des Goldes");
						positiondesGoldes.setEditable(false);
					}
					{
						gold_Y_koor = new JTextField();
						getContentPane().add(gold_Y_koor, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
						gold_Y_koor.setText("Y=");
					}
					{
						gold_X_koor = new JTextField();
						getContentPane().add(gold_X_koor, new GridBagConstraints(3, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
						gold_X_koor.setText("X=");
					}
					{
						positiondesWumpus = new JTextArea();
						getContentPane().add(positiondesWumpus, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
						positiondesWumpus.setText("Position des Wumpus");
						positiondesWumpus.setEditable(false);
					}
					{
						wumpus_Y_koor = new JTextField();
						getContentPane().add(wumpus_Y_koor, new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
						wumpus_Y_koor.setText("Y=");
					}
					{
						wumpus_X_koor = new JTextField();
						getContentPane().add(wumpus_X_koor, new GridBagConstraints(3, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
						wumpus_X_koor.setText("X=");
					}
					{
						positiondesPits = new JTextArea();
						getContentPane().add(positiondesPits, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
						positiondesPits.setText("Position des Pits");
						positiondesPits.setEditable(false);
					}
					{
						pit_Y_koor = new JTextField();
						getContentPane().add(pit_Y_koor, new GridBagConstraints(2, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
						pit_Y_koor.setText("Y=");
					}
					{
						pit_X_koor = new JTextField();
						getContentPane().add(pit_X_koor, new GridBagConstraints(3, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
						pit_X_koor.setText("X=");
//						pit_X_koor.setLineWrap(true);
//						pit_X_koor.setEditable(false);
					}
					
					{
						schliessenButton = new JButton();
						getContentPane().add(schliessenButton, new GridBagConstraints(1, 5, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
						schliessenButton.setText("Schließen");
						schliessenButton.addMouseListener(new MouseListener(){
							@Override
							public void mouseClicked(MouseEvent e) {
								if(e.getClickCount() >= 1){
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
					
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
}
