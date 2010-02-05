package de.wumpus.beobachter;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.*;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

import de.wumpus.beobachtet.WumpusWelt;
import de.wumpus.tools.Bezeichnungen;
import de.wumpus.tools.NachrichtenObjekt;

public class WumpusGUI extends javax.swing.JFrame implements Observer {
    private JMenuBar jMenuBar;
    private JSeparator jSeparator1;
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
	GridBagLayout thisLayout = new GridBagLayout();
	private boolean ALTPRESSED = false;

	public WumpusGUI(Wumpus_Panel panel, WumpusWelt _wump) {
		wumpusPanel = panel;
		wump = _wump;
		initGUI();
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
                /*ablauf layout*/
                {
                        ablaufScrollPanel = new JScrollPane();
                        getContentPane().add(ablaufScrollPanel, new GridBagConstraints(0, 0, 1, 6, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
                        ablaufScrollPanel.setPreferredSize(new java.awt.Dimension(50, 3));
                        {
                                ablaufTextArea = new JTextArea();
                                ablaufScrollPanel.setViewportView(ablaufTextArea);
                                ablaufTextArea.setBorder(BorderFactory.createTitledBorder("Ablauf"));
                                ablaufTextArea.setEnabled(false);
                                ablaufTextArea.setPreferredSize(new java.awt.Dimension(20, 18));
                                ablaufTextArea.setSize(120, 180);
                                ablaufTextArea.setText("");
                        }
                }
                
                {
                	//WumpusPanel
					this.add(wumpusPanel, new GridBagConstraints(1, 0, 3, 6, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				}
                {       /*statistik layout*/
                        statistikScrollPanel = new JScrollPane();
                        getContentPane().add(statistikScrollPanel, new GridBagConstraints(4, 0, 1, 6, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
                        statistikScrollPanel.setPreferredSize(new java.awt.Dimension(50, 3));
                        {
                        statistikTextArea = new JTextArea();
                        statistikScrollPanel.setViewportView(statistikTextArea);
                        statistikTextArea.setBorder(BorderFactory.createTitledBorder("Statistik"));
                        statistikTextArea.setEnabled(false);
                        statistikTextArea.setPreferredSize(new java.awt.Dimension(20, 18));
                        statistikTextArea.setSize(160, 180);
                        }
                }
                /*menu Initialisierung*/
                {
                        jMenuBar = new JMenuBar();
                        setJMenuBar(jMenuBar);
                        {
                                fileMenu = new JMenu();
                                jMenuBar.add(fileMenu);
                                fileMenu.setText("File");       {
                                        JMenuItem feld1 = new JMenuItem("Feld 4x4  (Alt+1)");
                                        fileMenu.add(feld1);
//                                        feld1.addActionListener(this);
                                        JMenuItem feld2 = new JMenuItem("Feld 8x8  (Alt+2)");
                                        fileMenu.add(feld2);
//                                        feld2.addActionListener(this);
                                        JMenuItem feld3 = new JMenuItem("Feld 16x16(Alt+3)");
                                        fileMenu.add(feld3);
//                                        feld3.addActionListener(this);

                                        jSeparator1 = new JSeparator(); /*Trennlinie*/
                                        fileMenu.add(jSeparator1);

                                        JMenuItem exit = new JMenuItem("Exit (Alt+x)");
                                        fileMenu.add(exit);
//                                        exit.addKeyListener(this);
//                                        exit.addActionListener(this);
                                }
                                optionen = new JMenu();
                                jMenuBar.add(optionen);
                                optionen.setText("Optionen");   {
                                        JMenuItem mensch = new JMenuItem("Mensch");
                                        optionen.add(mensch);
                                        JMenuItem ki_agent = new JMenuItem("KI-Agent");
                                        optionen.add(ki_agent);

                                        jSeparator1 = new JSeparator(); /*Trennlinie*/
                                        optionen.add(jSeparator1);

                                        JMenuItem geschwindigkeit = new JMenuItem("Geschwindigkeit");
                                        optionen.add(geschwindigkeit);
                                }
                                help = new JMenu();
                                jMenuBar.add(help);
                                help.setText("Help");
                                {
                                        JMenuItem hilfe = new JMenuItem("Hilfe");
                                        help.add(hilfe);
                                        JMenuItem about = new JMenuItem("About");
                                        help.add(about);
                                }
                        }
                }
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ALT) {
					ALTPRESSED = true;
				}
				if (arg0.getKeyCode() == KeyEvent.VK_1 && ALTPRESSED) {
					wump.neuesSpiel(4);
				} else if (arg0.getKeyCode() == KeyEvent.VK_2 && ALTPRESSED) {
					wump.neuesSpiel(8);
				} else if (arg0.getKeyCode() == KeyEvent.VK_3 && ALTPRESSED) {
					// wump.neuesSpiel(16);
				} else if (arg0.getKeyCode() == KeyEvent.VK_X && ALTPRESSED) {
					System.exit(0);
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

//		this.addWindowListener(new WindowListener() {
//
//			@Override
//			public void windowActivated(WindowEvent arg0) {
//			}
//
//			@Override
//			public void windowClosed(WindowEvent arg0) {
//			}
//
//			@Override
//			public void windowClosing(WindowEvent arg0) {
//				System.exit(0);
//			}
//
//			@Override
//			public void windowDeactivated(WindowEvent arg0) {
//			}
//
//			@Override
//			public void windowDeiconified(WindowEvent arg0) {
//			}
//
//			@Override
//			public void windowIconified(WindowEvent arg0) {
//			}
//
//			@Override
//			public void windowOpened(WindowEvent arg0) {
//			}
//
//		});
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
		}else if (((NachrichtenObjekt) arg).information.equals(Bezeichnungen.BEWEGE)) {
			System.out.println(" " + Bezeichnungen.BEWEGE);
			wumpusPanel.wechseleZweiBilder(((NachrichtenObjekt) arg).y , ((NachrichtenObjekt) arg).x,((NachrichtenObjekt) arg).wahrnehmung[0], ((NachrichtenObjekt) arg).wahrnehmung[1]);
		}
		if(((NachrichtenObjekt) arg).information.equals(Bezeichnungen.POSITION)){
			ablaufTextArea.setText(ablaufTextArea.getText() + "\n" + "Position (" +(((NachrichtenObjekt) arg).x+1) +"|"+(((NachrichtenObjekt) arg).y+1) + ")" );
		}
//		if (((NachrichtenObjekt) arg).information.equals(Bezeichnungen.AGENT)) {
//			ablaufTextArea.setText(ablaufTextArea.getText() + "\n" + Bezeichnungen.AGENT);			
//		}
	if (((NachrichtenObjekt) arg).information.equals(Bezeichnungen.GOLD)) {
			ablaufTextArea.setText(ablaufTextArea.getText() + "\n"  + Bezeichnungen.GOLD);			
		}if (((NachrichtenObjekt) arg).information.equals(Bezeichnungen.GLITTER)) {
			ablaufTextArea.setText(ablaufTextArea.getText() + "\n" + Bezeichnungen.GLITTER);
			
		}if (((NachrichtenObjekt) arg).information.equals(Bezeichnungen.WUMPUS)) {
			ablaufTextArea.setText(ablaufTextArea.getText() + "\n"  + Bezeichnungen.WUMPUS);
			
		}if (((NachrichtenObjekt) arg).information.equals(Bezeichnungen.GERUCH)) {
			ablaufTextArea.setText(ablaufTextArea.getText() + "\n"  + Bezeichnungen.GERUCH);
			
		}if (((NachrichtenObjekt) arg).information.equals(Bezeichnungen.FALLGRUBE)) {
			ablaufTextArea.setText(ablaufTextArea.getText() + "\n"  + Bezeichnungen.FALLGRUBE);
			
		}if (((NachrichtenObjekt) arg).information.equals(Bezeichnungen.BRISE)) {
			ablaufTextArea.setText(ablaufTextArea.getText() + "\n"  + Bezeichnungen.BRISE);
			
		}if (((NachrichtenObjekt) arg).information.equals(Bezeichnungen.BESUCHT)) {
			ablaufTextArea.setText(ablaufTextArea.getText() + "\n"  + Bezeichnungen.BESUCHT);
			
		}
	
	}
}
//TODO: Statistik fehlt
//TODO: Punkte System, bewegung zieht von Guthaben Punkte ab
//TODO: Schwarze Felder sind noch direkt als besuchte Felder, sollten aber nicht
//TODO: ScrollPanel scheint bei den Textfeldern noch nicht zu sein
//TODO: Ereignisabfrage muss Spielbeinflussen. zB. Agent ist auf Gold, Spiel gewonnen, Agent ist auf Fallgrube, Spiel verloren
//TODO: About/Help Menu schreiben, bzw Menu fertig integrieren
//TODO: Die Listener fertig und funktionierend machen.
//TODO: KI Umschalter
//TODO: KI
//TODO: Geschwindkeitsregelung einbauen
//TODO: Wahrscheinlichkeiten für Felder ausrechnen
