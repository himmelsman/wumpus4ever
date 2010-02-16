package de.wumpus.beobachter;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.*;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;

import de.wumpus.beobachtet.WumpusWelt;
import de.wumpus.tools.AboutScreen;
import de.wumpus.tools.Benutzerdefiniertes_Feld;
import de.wumpus.tools.Bezeichnungen;
import de.wumpus.tools.HelpScreen;
import de.wumpus.tools.NachrichtenObjekt;

public class WumpusGUI extends JFrame implements Observer {
	private JFrame guiFrame;
	private JMenuBar jMenuBar;
	private JSeparator jSeparator1;
	private JSeparator jSeparator2;
	private JMenu fileMenu;
	private JMenu optionen;
	private JTextArea jTextArea1;
	private JTextArea statistik;
	private JMenu help;
	private JScrollPane ablaufScrollPanel;
	private JScrollPane statistikScrollPanel;
	private JTextArea ablaufTextArea;
	private JTextArea statistikTextArea;
	private Wumpus_Panel wumpusPanel;
	WumpusWelt wump;
	private JLabel punkteLabel;
	private JLabel punkteAnzahlLabel;
	private JLabel schritteLabel;
	private JLabel schritteAnzahlLabel;
	private JLabel jLabel5;
	private JLabel jLabel6;
	GridBagLayout thisLayout = new GridBagLayout();
	private boolean ALTPRESSED = false;
	private int agentSchrittZahler = 0;
	private int gesamtPunktenAnzahl = 10000;
	private int einSchritt_10 = 10;
	

	public WumpusGUI(Wumpus_Panel panel, WumpusWelt _wump) {
		wumpusPanel = panel;
		wump = _wump;
		initGUI();
		guiFrame = this;
	}

	private void initGUI() {
		try {
			{
				setSize(800, 600);
				setTitle("Wumpus Welt");
				setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
				thisLayout.rowWeights = new double[] { 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1 };
				thisLayout.rowHeights = new int[] { 7, 7, 7, 7, 7, 7, 7, 7 };
				thisLayout.columnWeights = new double[] { 0.1, 0.1, 0.1, 0.1, 0.1 };
				thisLayout.columnWidths = new int[] { 7, 7, 7, 7, 7 };
				getContentPane().setLayout(thisLayout);
				/* Ablauflayout */
				{
					ablaufScrollPanel = new JScrollPane();
					getContentPane().add(ablaufScrollPanel, new GridBagConstraints(0, 0, 1, 6, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					ablaufScrollPanel.setPreferredSize(new java.awt.Dimension(75, 1));
					{
						ablaufTextArea = new JTextArea();
						ablaufTextArea.setLineWrap(true);
						ablaufScrollPanel.setViewportView(ablaufTextArea);
						ablaufTextArea.setBorder(BorderFactory.createTitledBorder("Ablauf"));
						ablaufTextArea.setEnabled(false);
//						ablaufTextArea.setEditable(false);
						//TODO: Focuslistener funktioniert nicht mehr wenn TextArea enabled ist.
					}

				}

				{
					// WumpusPanel
					this.add(wumpusPanel, new GridBagConstraints(1, 0, 3, 6, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				}
				{ /* Statistiklayout */
					statistikScrollPanel = new JScrollPane();
					getContentPane().add(statistikScrollPanel, new GridBagConstraints(4, 0, 1, 6, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					statistikScrollPanel.setPreferredSize(new java.awt.Dimension(75, 1));
					{
						statistikTextArea = new JTextArea();
						statistikScrollPanel.setViewportView(statistikTextArea);
						statistikTextArea.setBorder(BorderFactory.createTitledBorder("Statistik"));
						statistikTextArea.setEnabled(false);
						GridBagLayout statistikLayout = new GridBagLayout();
						statistikLayout.rowWeights = new double[] { 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1 };
						statistikLayout.rowHeights = new int[] { 7, 7, 7, 7, 7, 7, 7, 7, 7 };
						statistikLayout.columnWeights = new double[] { 0.01, 0.1, 0.1, 0.01};
						statistikLayout.columnWidths = new int[] { 7, 7, 7, 7 };
						statistikTextArea.setLayout(statistikLayout);
						//TODO: Punkte und Schritte mit Zähler versehen, damit bei bewegung verwendet werden kann.ES KANN SEIN DASS DIE AUF DASS SELBE POSITION SIND
						punkteLabel = new JLabel("Punkte");
						punkteAnzahlLabel = new JLabel(String.valueOf(gesamtPunktenAnzahl));
						statistikTextArea.add(punkteLabel, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
						statistikTextArea.add(punkteAnzahlLabel, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHEAST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
						schritteLabel = new JLabel("Schritte");
						schritteAnzahlLabel = new JLabel(String.valueOf(agentSchrittZahler));
						statistikTextArea.add(schritteLabel, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
						statistikTextArea.add(schritteAnzahlLabel, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHEAST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
						
						
					}
				}
				/* Menu Initialisierung */
				{
					jMenuBar = new JMenuBar();
					setJMenuBar(jMenuBar);
					{
						fileMenu = new JMenu();
						jMenuBar.add(fileMenu);
						fileMenu.setText("File");
						{
							JMenuItem feld1 = new JMenuItem("Feld 4x4  (Alt+1)");
							fileMenu.add(feld1);
							// feld1.addActionListener(this);
							JMenuItem feld2 = new JMenuItem("Feld 8x8  (Alt+2)");
							fileMenu.add(feld2);
							// feld2.addActionListener(this);
							JMenuItem feld3 = new JMenuItem("Feld 16x16(Alt+3)");
							fileMenu.add(feld3);
							// feld3.addActionListener(this);

							jSeparator1 = new JSeparator(); /* Trennlinie */
							fileMenu.add(jSeparator1);
							
							JMenuItem feld4 = new JMenuItem("Def. Feld (Alt+D)");
							fileMenu.add(feld3);
							// feld3.addActionListener(this);

							jSeparator2 = new JSeparator(); /* Trennlinie */
							fileMenu.add(jSeparator2);
							
							JMenuItem exit = new JMenuItem("Exit (Alt+x)");
							fileMenu.add(exit);
							// exit.addKeyListener(this);
							// exit.addActionListener(this);
						}
						optionen = new JMenu();
						jMenuBar.add(optionen);
						optionen.setText("Optionen");
						{
							JMenuItem mensch = new JMenuItem("Mensch");
							optionen.add(mensch);
							JMenuItem ki_agent = new JMenuItem("KI-Agent");
							optionen.add(ki_agent);

							jSeparator1 = new JSeparator(); /* Trennlinie */
							optionen.add(jSeparator1);

							JMenuItem geschwindigkeit = new JMenuItem("Geschwindigkeit");
							optionen.add(geschwindigkeit);
						}
						help = new JMenu();
						jMenuBar.add(help);
						help.setText("Help");
						{
							JMenuItem hilfe = new JMenuItem("Hilfe (Alt+H)");
							help.add(hilfe);
							JMenuItem about = new JMenuItem("About (Alt+A)");
							help.add(about);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.addKeyListener(new KeyListener() {

			/*
			 * Realisierung der Tastarur, ganauer gesagt wird die Weltgrosse ueber Tastatur eingegeben, durch Alt+1 usw.
			 */
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ALT) {
					ALTPRESSED = true;
				}
				if (arg0.getKeyCode() == KeyEvent.VK_1 && ALTPRESSED) {
					wump.neuesSpiel(4);
					gesamtPunktenAnzahl = 10000;
					punkteAnzahlLabel.setText(gesamtPunktenAnzahl + "");
					agentSchrittZahler = 0;
					schritteAnzahlLabel.setText(agentSchrittZahler + "");
					ablaufTextArea.setText("");
				} else if (arg0.getKeyCode() == KeyEvent.VK_2 && ALTPRESSED) {
					wump.neuesSpiel(8);
					gesamtPunktenAnzahl = 10000;
					punkteAnzahlLabel.setText(gesamtPunktenAnzahl + "");
					agentSchrittZahler = 0;
					schritteAnzahlLabel.setText(agentSchrittZahler + "");
					ablaufTextArea.setText("");
				} else if (arg0.getKeyCode() == KeyEvent.VK_3 && ALTPRESSED) {
					// wump.neuesSpiel(16);
//					ablaufTextArea.setText("");
				} else if (arg0.getKeyCode() == KeyEvent.VK_D && ALTPRESSED) {
					setEnabled(false);
					new Benutzerdefiniertes_Feld(guiFrame);
					// wump.neuesSpiel(16);
//					ablaufTextArea.setText("");
				} else if (arg0.getKeyCode() == KeyEvent.VK_X && ALTPRESSED) {
					System.exit(0);
				} else if (arg0.getKeyCode() == KeyEvent.VK_H && ALTPRESSED) {
					System.out.println("ALT + H");
					setEnabled(false);
					new HelpScreen(guiFrame);
				}else if (arg0.getKeyCode() == KeyEvent.VK_A && ALTPRESSED) {
					System.out.println("ALT + A");
					setEnabled(false);
					new AboutScreen(guiFrame); 
				}
				// Abfage der Tastaturpfeile
				if (arg0.getKeyCode() == KeyEvent.VK_LEFT) {
					System.out.println("Left");
					wump.bewegeAgent(Bezeichnungen.LINKS);
				} else if (arg0.getKeyCode() == KeyEvent.VK_RIGHT) {
					System.out.println("Right");
					wump.bewegeAgent(Bezeichnungen.RECHTS);
				} else if (arg0.getKeyCode() == KeyEvent.VK_UP) {
					System.out.println("Up");
					wump.bewegeAgent(Bezeichnungen.UP);
				} else if (arg0.getKeyCode() == KeyEvent.VK_DOWN) {
					System.out.println("Down");
					wump.bewegeAgent(Bezeichnungen.DOWN);
				}
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ALT) {
					ALTPRESSED = false;
				}

			}

			@Override
			public void keyTyped(KeyEvent arg0) {
			}

		});
		// FocusListener damit KeyEvents immer wieder an die GUI gehen
		this.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent arg0) {
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				requestFocus();
				System.out.println("FOCUS");				
			}
		});

	}

	public void update(Observable obj, Object arg) {
		if (((NachrichtenObjekt) arg).information == Bezeichnungen.GUI) {
			// System.out.println("update() ist aufgeruffen, fount ist gleich "
			// + ((Integer)arg).intValue());
			System.out.println("Ich bin die GUI \nPosition x: " + ((NachrichtenObjekt) arg).x + " Position y: " + ((NachrichtenObjekt) arg).y + " Wahrnemung: " + ((NachrichtenObjekt) arg).wahrnehmung[0] + " Information: "
					+ ((NachrichtenObjekt) arg).information);
		} else if (((NachrichtenObjekt) arg).information.equals(Bezeichnungen.REPAINT)) {
			System.out.println(" " + Bezeichnungen.REPAINT);
			wumpusPanel.setzeAnzahl();
		} else if (((NachrichtenObjekt) arg).information.equals(Bezeichnungen.BEWEGE)) {
			System.out.println(" " + Bezeichnungen.BEWEGE);
			//TODO: jetzt brauch man nicht die uebergabeparameter
			wumpusPanel.wechseleZweiBilder(/*((NachrichtenObjekt) arg).y, ((NachrichtenObjekt) arg).x, ((NachrichtenObjekt) arg).wahrnehmung[0], ((NachrichtenObjekt) arg).wahrnehmung[1]*/);
			/*es werden die Schritte gezahlt und die verbrauchte Punkte(-10 pro Schritt) abgezogen*/
			agentSchrittZahler++;
			gesamtPunktenAnzahl = gesamtPunktenAnzahl - einSchritt_10;
//			schritteAnzahlLabel.setText(Integer.toString(agentSchrittZahler));//alternative
			schritteAnzahlLabel.setText(String.valueOf(agentSchrittZahler));
			punkteAnzahlLabel.setText(String.valueOf(gesamtPunktenAnzahl));
		}
		if (((NachrichtenObjekt) arg).information.equals(Bezeichnungen.POSITION)) {
			ablaufTextArea.setText(ablaufTextArea.getText() + "\n" + "Position (" + (((NachrichtenObjekt) arg).x + 1) + "|" + (((NachrichtenObjekt) arg).y + 1) + ")");			
		}
		/*
		 * 1 als Agend in dem Feld 2 als Gold in dem Feld 3 als Glitter in nahligenden Felder 4 als Wumpus in dem Feld 5 als Geruch in nahligenden Felder 6 als Pit in dem Feld 7 als Brize ind nahligenden Felder 9 als graues Feld als Besucht
		 * bezeichnet
		 */

		// Wahrnehmung auswerten
		// TODO: Überarbeiten der Ablaufausgabe so dass Sätze zusammen kommen.
		if (((NachrichtenObjekt) arg).information.equals(Bezeichnungen.WAHRNEHMUNGEN)) {
			ablaufTextArea.setText(ablaufTextArea.getText() + "Position (" + (((NachrichtenObjekt) arg).x + 1) + "|" +  (((NachrichtenObjekt) arg).y+1) + ")\n");
			for (int j = 0, i = 0; i < ((NachrichtenObjekt) arg).wahrnehmung.length; i++) {
				if (((NachrichtenObjekt) arg).wahrnehmung[i] == 1) {
					// Agent
				} else if (((NachrichtenObjekt) arg).wahrnehmung[i] == 2) {
					// Gold
					// TODO: Wenn Agent auf Gold, dann ENDE?
					ablaufTextArea.setText(ablaufTextArea.getText() + "Der Agent sieht das Gold");
					j = 1;
					i = ((NachrichtenObjekt) arg).wahrnehmung.length;
				} else if (((NachrichtenObjekt) arg).wahrnehmung[i] == 3) {
					// Glitter
					ablaufTextArea.setText(ablaufTextArea.getText() + "es glitzert");
					j = 1;
				} else if (((NachrichtenObjekt) arg).wahrnehmung[i] == 4) {
					// Wumpus
					ablaufTextArea.setText(ablaufTextArea.getText() + "Der Agent sieht das Wumpus");
					j = 1;
					i = ((NachrichtenObjekt) arg).wahrnehmung.length;
				} else if (((NachrichtenObjekt) arg).wahrnehmung[i] == 5) {
					// Geruch
					ablaufTextArea.setText(ablaufTextArea.getText() + "es stinkt");
					j = 1;
				} else if (((NachrichtenObjekt) arg).wahrnehmung[i] == 6) {
					// Fallgrube
					ablaufTextArea.setText(ablaufTextArea.getText() + "Der Agent fällt in die Grube");
					j = 1;
					i = ((NachrichtenObjekt) arg).wahrnehmung.length;
				} else if (((NachrichtenObjekt) arg).wahrnehmung[i] == 7) {
					// Brise
					ablaufTextArea.setText(ablaufTextArea.getText() + "es zieht");
					j = 1;
				} else if (((NachrichtenObjekt) arg).wahrnehmung[i] == 9) {
					// Besuchtes Feld
				}
				if (i + 1 < ((NachrichtenObjekt) arg).wahrnehmung.length && ((NachrichtenObjekt) arg).wahrnehmung[i] != 9 && ((NachrichtenObjekt) arg).wahrnehmung[i] != 2 && ((NachrichtenObjekt) arg).wahrnehmung[i] != 4
						&& ((NachrichtenObjekt) arg).wahrnehmung[i] != 6 && ((NachrichtenObjekt) arg).wahrnehmung[i] != 1) {
					ablaufTextArea.setText(ablaufTextArea.getText() + " und ");
				} else if (j != 0) {
					ablaufTextArea.setText(ablaufTextArea.getText() + ".\n");
				}
			}
		}
	}
}
// TODO: Ablauf muss auch bei neuem Spiel resetet werden
// TODO: Punkte System, bewegung zieht von Guthaben Punkte ab(bei neu spiel muss resetet werden)
// TODO: Schwarze Felder sind noch direkt als besuchte Felder, sollten aber nicht
// TODO: Ereignisabfrage muss Spielbeinflussen. zB. Agent ist auf Gold, Spiel gewonnen, Agent ist auf Fallgrube, Spiel verloren
// TODO: About/Help Menu schreiben, bzw Menu fertig integrieren
// TODO: Die Listener fertig und funktionierend machen. Focus Listerner auf GUI ist vorhanden
// TODO: KI Umschalter
// TODO: KI
// TODO: Geschwindkeitsregelung einbauen
// TODO: Wahrscheinlichkeiten für Felder ausrechnen
