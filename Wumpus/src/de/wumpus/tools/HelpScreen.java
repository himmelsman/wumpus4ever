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
 * HelpScreen ist das JFrame welches über alt+h oder das Menü aufgerufen wird. Der HelpScreen gibt eine Einleitung zur Wumpus-Welt und eine Übersicht über die Regeln.
 * 
 * @author Benjamin Werker
 * 
 */
public class HelpScreen extends JFrame {

	JFrame main;
	private JScrollPane jScrollPane1;
	private JButton jButton1;
	private JTextArea helpTextArea;
	private String helpText = "Die Wumpus-Welt\n" + "Ein Agent erforscht eine Höhle auf der Suche nach einem Goldschatz. Die" + "Höhle besteht aus mehreren miteinander verbundenen Räumen. Irgendwo in"
			+ "der Höhle lauert der Wumpus, ein Ungeheuer, das jeden frisst, der in seine" + "Nähe kommt. Obendrein gibt es Fallgruben (PITs), in die der Agent stürzen" + "kann. Die Höhle besteht aus 4x4 Feldern (Räumen). Auf dem Feld, auf dem"
			+ "sich der Wumpus befindet, und in den unmittelbar benachbarten Feldern" + "nimmt man einen unangenehmen Geruch wahr (Stench). Auf Feldern, die" + "unmittelbar neben einer Fallgrube liegen, spürt man einen Luftzug (Breeze).\n\n"
			+ "Ziel\n" + "Hole das Gold ohne in eines der Löcher zu fallen ohne auf den Wumpus zu stoßen und kehre zum Start zurück.\n" + "- Das Gold erkennt man am Glitzern (Glitter)\n"
			+ "- Falls der Agent gegen eine Wand läuft, spürt er einen Stoß\n" + "- Der Agent besitzt (genau) einen Pfeil, mit dem er den Wumpus töten kann\n" + "- Wird der Wumpus getötet, so ist sein Todesschrei überall in der Höhle zu hören\n"
			+ "- Der Agent stirbt, wenn er in eine Fallgrube fällt oder dem lebenden Wumpus begegnet\n" + "- Perzeptionen werden als 5-Tupel dargestellt\n" + "- z.B. Geruch, Luftzug, Glitzern, kein Stoß, kein Schrei\n"
			+ "- [Stench, Breeze, Glitter, None, None]\n\n" +

			"Die Regeln\n" + "- Gold +1000, gefressen -1000\n" + "- 1 pro step, -10 für Pfeilschuss\n" + "- Quadrate in direkter Nachbarschaft" + "zum Wumpus sind “smelly”\n" + "- Quadrate in direkter Nachbarschaft" + "zu Pits sind “breezy”\n"
			+ "- Glitzern bei Gold im gleichen Quadrat\n" + "- Pfeilschuss tötet den Wumpus bei direktem Gegenüberstehen\n" + "- Nur ein Pfeil steht zur Verfügung";

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
					jButton1.setText("Schließen");
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
