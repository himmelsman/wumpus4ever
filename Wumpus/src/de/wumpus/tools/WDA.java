package de.wumpus.tools;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * WDA(Wissensbasis des Agentes) ist die Hilfsklasse, die eine tabelarische graphische Ausgabe ist.
 * 
 * @author Benjamin Werker
 * @author Sergey Bagautdinov
 * 
 */

/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI Builder, which is free for non-commercial use. If Jigloo is being used commercially (ie, by a corporation, company or business for any purpose whatever) then you should
 * purchase a license for each developer using Jigloo. Please visit www.cloudgarden.com for details. Use of Jigloo implies acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE
 * CANNOT BE USED LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class WDA extends javax.swing.JPanel {
	private JTextArea[][] labelArray;
	private int anzahl;

	public WDA() {
		super();
		anzahl = 1;
	}

	public WDA(int _anzahl) {
		super();
		if (_anzahl < 1) {
			anzahl = 1;
		} else {
			anzahl = _anzahl;
		}
		initGUI();
	}

	private void initGUI() {
		try {
			GridLayout thisLayout = new GridLayout(anzahl, anzahl);
			thisLayout.setColumns(anzahl);
			thisLayout.setHgap(1);
			thisLayout.setVgap(1);
			thisLayout.setRows(anzahl);
			setLayout(thisLayout);
			labelArray = new JTextArea[anzahl][anzahl];
			for (int i = 0; i < anzahl; i++) {
				for (int j = 0; j < anzahl; j++) {
					labelArray[i][j] = new JTextArea(i + "\n" + j);
					labelArray[i][j].setFont(new java.awt.Font(this.getFont().getFamily(), 0, 10));
					labelArray[i][j].setFocusable(false);
					labelArray[i][j].setEditable(false);
					if(anzahl>3){
						add(labelArray[i][j]);
					}

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Diese Methode aendert das Feld in Wissensbasis des Agentes.
	 * @param wissensbasis Array von Typ Feld für Wissensbasis des Agentes
	 */
	public void aendereFeld(Feld[][] wissensbasis) {
		anzahl = wissensbasis.length;
		removeAll();
		initGUI();
		String tempString;
		for (int i = 0; i < anzahl; i++) {
			for (int j = 0; j < anzahl; j++) {
				tempString = "";
				int count = 0;
				if (wissensbasis[i][j].besucht) {
					if(count == 1 || count == 3){
						tempString += " ";
						count++;
					}else if(count == 2){
						tempString += "\n";
						count++;
					}else 
						count++;
					tempString += "BE";
					
				}
				if (wissensbasis[i][j].versteckt) {
					if(count == 1 || count == 3){
						tempString += " ";
						count++;
					}else if(count == 2){
						tempString += "\n";
						count++;
					}else 
						count++;
					tempString += "UN";
				}
				if (wissensbasis[i][j].gefahr) {
					if(count == 1 || count == 3){
						tempString += " ";
						count++;
					}else if(count == 2){
						tempString += "\n";
						count++;
					}else 
						count++;
					tempString += "TO";
				}
				if (wissensbasis[i][j].wumpus) {
					if(count == 1 || count == 3){
						tempString += " ";
						count++;
					}else if(count == 2){
						tempString += "\n";
						count++;
					}else 
						count++;
					tempString += "WU";
				}
				if (wissensbasis[i][j].fallgrube) {
					if(count == 1 || count == 3){
						tempString += " ";
						count++;
					}else if(count == 2){
						tempString += "\n";
						count++;
					}else 
						count++;
					tempString += "FA";
				}
				if (wissensbasis[i][j].gold) {
					if(count == 1 || count == 3){
						tempString += " ";
						count++;
					}else if(count == 2){
						tempString += "\n";
						count++;
					}else 
						count++;
					tempString += "GO";
				}
				if (wissensbasis[i][j].brise) {
					if(count == 1 || count == 3){
						tempString += " ";
						count++;
					}else if(count == 2){
						tempString += "\n";
						count++;
					}else 
						count++;
					tempString += "BR";
				}
				if (wissensbasis[i][j].geruch) {
					if(count == 1 || count == 3){
						tempString += " ";
						count++;
					}else if(count == 2){
						tempString += "\n";
						count++;
					}else 
						count++;
					tempString += "GE";
				}
				if (wissensbasis[i][j].glitter) {
					if(count == 1 || count == 3){
						tempString += " ";
						count++;
					}else if(count == 2){
						tempString += "\n";
						count++;
					}else 
						count++;
					tempString += "GL";
				}
				// if(wandO){
				// temp.wandO = true;
				// }
				// if(wandU){
				// temp.wandU = true;
				// }
				// if(wandL){
				// temp.wandL = true;
				// }
				// if(wandR){
				// temp.wandR = true;
				// }
				labelArray[i][j].setText(tempString);
				if(wissensbasis[i][j].agent){
					labelArray[i][j].setBackground(Color.GREEN);
				}
			}
		}
	}

	public void neueLaden(int _anzahl) {
		removeAll();
		if (_anzahl < 1) {
			anzahl = 1;
		} else {
			anzahl = _anzahl;
		}
		initGUI();
	}
}
