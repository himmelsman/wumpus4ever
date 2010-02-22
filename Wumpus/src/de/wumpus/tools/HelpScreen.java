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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * HelpScreen ist das JFrame welches �ber alt+h oder das Men� aufgerufen wird. Der HelpScreen gibt eine Einleitung zur Wumpus-Welt und eine �bersicht �ber die Regeln.
 * 
 * @author Benjamin Werker
 * 
 */
public class HelpScreen extends JFrame {

	JFrame main;
	private JScrollPane jScrollPane1;
	private JButton jButton1;
	private JTextArea helpTextArea;
	private String helpText = "Die Wumpus-Welt\n" + "Ein Agent erforscht eine H�hle auf der Suche nach einem Goldschatz. Die" + "H�hle besteht aus mehreren miteinander verbundenen R�umen. Irgendwo in"
			+ "der H�hle lauert der Wumpus, ein Ungeheuer, das jeden frisst, der in seine" + "N�he kommt. Obendrein gibt es Fallgruben (PITs), in die der Agent st�rzen" + "kann. Die H�hle besteht aus 4x4 Feldern (R�umen). Auf dem Feld, auf dem"
			+ "sich der Wumpus befindet, und in den unmittelbar benachbarten Feldern" + "nimmt man einen unangenehmen Geruch wahr (Stench). Auf Feldern, die" + "unmittelbar neben einer Fallgrube liegen, sp�rt man einen Luftzug (Breeze).\n\n"
			+ "Ziel\n" + "Hole das Gold ohne in eines der L�cher zu fallen ohne auf den Wumpus zu sto�en und kehre zum Start zur�ck.\n" + "- Das Gold erkennt man am Glitzern (Glitter)\n"
			+ "- Falls der Agent gegen eine Wand l�uft, sp�rt er einen Sto�\n" + "- Der Agent besitzt (genau) einen Pfeil, mit dem er den Wumpus t�ten kann\n" + "- Wird der Wumpus get�tet, so ist sein Todesschrei �berall in der H�hle zu h�ren\n"
			+ "- Der Agent stirbt, wenn er in eine Fallgrube f�llt oder dem lebenden Wumpus begegnet\n" + "- Perzeptionen werden als 5-Tupel dargestellt\n" + "- z.B. Geruch, Luftzug, Glitzern, kein Sto�, kein Schrei\n"
			+ "- [Stench, Breeze, Glitter, None, None]\n\n" +

			"Die Regeln\n" + "- Gold +1000, gefressen -1000\n" + "- 1 pro step, -10 f�r Pfeilschuss\n" + "- Quadrate in direkter Nachbarschaft" + "zum Wumpus sind �smelly�\n" + "- Quadrate in direkter Nachbarschaft" + "zu Pits sind �breezy�\n"
			+ "- Glitzern bei Gold im gleichen Quadrat\n" + "- Pfeilschuss t�tet den Wumpus bei direktem Gegen�berstehen\n" + "- Nur ein Pfeil steht zur Verf�gung";

	public HelpScreen(JFrame _main) {
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
				thisLayout.rowWeights = new double[] { 0.1, 0.1, 0.1, 0.01 };
				thisLayout.rowHeights = new int[] { 7, 7, 7, 7 };
				thisLayout.columnWeights = new double[] { 0.1, 0.1, 0.1, 0.1 };
				thisLayout.columnWidths = new int[] { 7, 7, 7, 7 };
				getContentPane().setLayout(thisLayout);
				{
					jScrollPane1 = new JScrollPane();
					getContentPane().add(jScrollPane1, new GridBagConstraints(0, 0, 4, 3, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					{
						helpTextArea = new JTextArea();
						jScrollPane1.setViewportView(helpTextArea);
						helpTextArea.setText(helpText);
						helpTextArea.setLineWrap(true);
						helpTextArea.setEditable(false);
					}
				}
				{
					jButton1 = new JButton();
					getContentPane().add(jButton1, new GridBagConstraints(1, 3, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					jButton1.setText("Schlie�en");
					jButton1.addMouseListener(new MouseListener() {
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
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
