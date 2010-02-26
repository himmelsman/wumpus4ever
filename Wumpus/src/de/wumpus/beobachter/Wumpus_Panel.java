package de.wumpus.beobachter;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.util.Observable;
import java.util.Observer;

import javax.swing.DebugGraphics;
import javax.swing.JLabel;
import javax.swing.JPanel;

import de.wumpus.beobachtet.WumpusWelt;
import de.wumpus.tools.Bezeichnungen;
import de.wumpus.tools.FeldPositionieren;
import de.wumpus.tools.NachrichtenObjekt;
import de.wumpus.tools.WumpusBitmapComponent;

public class Wumpus_Panel extends JPanel implements Observer {
	WumpusWelt wump;

	public int width = 1;
	// public boolean erstesmal = true;
	public int height = 1;

	private int anzahl;
	// private int _anzahl;
	private int neuY = 0, neuX = 0, alteY = 0, alteX = 0;
	WumpusBitmapComponent[][] array;

	Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
	private static int panelGrosse;

	String schwarz = "Black.JPG";
	String grau = "Gray.JPG";
	String glitter = "Glitter.JPG";
	String agent = "Agent.JPG";
	String gold = "Gold.JPG";
	String wumpus2 = "Wumpus.JPG";
	String pit = "Pit.JPG";
	String breeze = "Breeze.JPG";
	String smell = "Smell.JPG";
	String breezeGold = "BreezeGold.JPG";
	String smellGold = "smellGold";
	String breezeSmell = "BreezeSmell.JPG";
	String breezeSmellGold = "BreezeSmellGold.JPG";
	String breezeGlitter = "BreezeGlitter.JPG";
	String smellGlitter = "SmellGlitter.JPG";
	String breezeSmellGlitter = "BreezeSmellGlitter.JPG";

	public Wumpus_Panel(WumpusWelt _wump) {
		super();
		setVisible(true);
		wump = _wump;
		anzahl = wump.anzahl;
		// _anzahl = anzahl + 1;
		array = new WumpusBitmapComponent[anzahl][anzahl];
		// System.out.println("Array = " + array.length);
		// System.out.println("Anzahl = " + anzahl);
		// for (int i = 0; i < anzahl; i++) {
		// for (int j = 0; j < anzahl; j++) {
		// System.out.println("Position " + i + "," + j + " Wert " + wump.weltArray[i][j]);
		// }
		// }
		initGUI();
	}

	public void setzeAnzahl() {
		anzahl = wump.anzahl;
		array = new WumpusBitmapComponent[anzahl][anzahl];
		// for (int i = 0; i < anzahl; i++) {
		// for (int j = 0; j < anzahl; j++) {
		// System.out.println("Position " + i + "," + j + " Wert " + wump.weltArray[i][j]);
		// }
		// }
		initGUI();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		/*
		 * Die Felder werden immer auf das maximum der verfügbaren Fläche ausgedehnt
		 */
		// System.out.println("Anzahl in paint= "+ anzahl);
		int width = this.getWidth();
		int height = this.getHeight();
		// int width = 100;
		// int height = 100;
		if (array != null) {
			for (int i = 0; i < array.length; i++) {
				for (int j = 0; j < array.length; j++) {
					if (array[i][j] != null) {
						// TODO schauen wie die width und height von GUI uebergeben werden.
						array[i][j].setImageSize(width / anzahl, height / anzahl);
						// System.out.println("in paint width / anzahl, height / anzahl " + width / anzahl +" " + height / anzahl);
					}
				}
			}
		}
	}

	private void initGUI() {
		// int x = 1, y = 1;
		removeAll();
		GridBagLayout thisLayout = new GridBagLayout();
		// TODO: statt gridbaglayout eine alternative suchen, welches quadratische felder ermöglicht
		setLayout(thisLayout);
		setPreferredSize(new java.awt.Dimension(panelGrosse, panelGrosse));
		setDebugGraphicsOptions(DebugGraphics.BUFFERED_OPTION);
		setAutoscrolls(true);
		array = new WumpusBitmapComponent[anzahl][anzahl];
		// System.out.println("Anzahl init= " + anzahl);
		zeichnen();
		revalidate();
		repaint();
	}

	/**
	 * Diese Methode zeichnet die Axchenbeschriftung und WumpusPanel
	 */
	public void zeichnen() {
		for (int i = 0; i < anzahl; i++) {
			// zahlenreihe nach unten am linken rand
			this.add(new JLabel("" + (i + 1)), new GridBagConstraints(0, i + 1, 1, 1, 0.01, 0.01, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
			// zahlenreihe nach rechts am oberen rand
			this.add(new JLabel("" + (i + 1)), new GridBagConstraints(i + 1, 0, 1, 1, 0.01, 0.01, GridBagConstraints.CENTER, 0, new Insets(0, 0, 0, 0), 0, 0));
		}
		int x = 1, y = 1;
		for (int i = 0, h = 0; i < anzahl; i++) {
			for (int j = 0; j < anzahl; j++) {
				{ // bestimmeDieErsteZahl(gibmirWert[i][j]);
					// wahrnehmung zu int[]
//					if (wump.bestimmeDieErsteZahl(wump.weltArray[i][j]) == 1) {
//						array[i][j] = new WumpusBitmapComponent(agent2, anzahl, j, i % anzahl);
//						// System.out.println("TEST: ........................." + array[i][j].getX() + "," + array[i][j].getY());
//					} else if (wump.bestimmeDieErsteZahl(wump.weltArray[i][j]) == 2) {
//						int zweiteStelle = new FeldPositionieren().checkFirst(wump.bestimmeDieErsteZahl(wump.weltArray[i][j]), 2);
//						if (zweiteStelle == 5) {
//							int dritteStelle = new FeldPositionieren().checkFirst(zweiteStelle, 5);
//							if (dritteStelle == 7) {
//								array[i][j] = new WumpusBitmapComponent(breezeSmellGold, anzahl, j, i % anzahl);
//							} else if (dritteStelle == 0) {
//								array[i][j] = new WumpusBitmapComponent(smellGold, anzahl, j, i % anzahl);
//							}
//						} else if (zweiteStelle == 0) {
//							array[i][j] = new WumpusBitmapComponent(gold2, anzahl, j, i % anzahl);
//						}
//					} else if (wump.bestimmeDieErsteZahl(wump.weltArray[i][j]) == 2) {
//						int zweiteStelle = new FeldPositionieren().checkFirst(wump.bestimmeDieErsteZahl(wump.weltArray[i][j]), 2);
//						if (zweiteStelle == 7 && new FeldPositionieren().checkFirst(zweiteStelle, 7) == 0) {
//							array[i][j] = new WumpusBitmapComponent(breezeGold, anzahl, j, i % anzahl);
//						} else if (zweiteStelle == 0) {
//							array[i][j] = new WumpusBitmapComponent(gold2, anzahl, j, i % anzahl);
//						}
//
//					} else if (wump.bestimmeDieErsteZahl(wump.weltArray[i][j]) == 3) {
//						int zweiteStelle = new FeldPositionieren().checkFirst(wump.bestimmeDieErsteZahl(wump.weltArray[i][j]), 3);
//						if (zweiteStelle == 5) {
//							int dritteStelle = new FeldPositionieren().checkFirst(zweiteStelle, 5);
//							if (dritteStelle == 7) {
//								array[i][j] = new WumpusBitmapComponent(breezeSmellGlitter, anzahl, j, i % anzahl);
//							} else if (dritteStelle == 0) {
//								array[i][j] = new WumpusBitmapComponent(smellGlitter, anzahl, j, i % anzahl);
//							}
//						} else if (zweiteStelle == 0) {
//							array[i][j] = new WumpusBitmapComponent(glitter, anzahl, j, i % anzahl);
//						}
//					} else if (wump.bestimmeDieErsteZahl(wump.weltArray[i][j]) == 3) {
//						int zweiteStelle = new FeldPositionieren().checkFirst(wump.bestimmeDieErsteZahl(wump.weltArray[i][j]), 3);
//						if (zweiteStelle == 7 && new FeldPositionieren().checkFirst(zweiteStelle, 7) == 0) {
//							array[i][j] = new WumpusBitmapComponent(breezeGlitter, anzahl, j, i % anzahl);
//						} else if (zweiteStelle == 0) {
//							array[i][j] = new WumpusBitmapComponent(glitter, anzahl, j, i % anzahl);
//						}
//
//					} else if (wump.bestimmeDieErsteZahl(wump.weltArray[i][j]) == 4) {
//						array[i][j] = new WumpusBitmapComponent(wumpus2, anzahl, j, i % anzahl);
//					} else if (wump.bestimmeDieErsteZahl(wump.weltArray[i][j]) == 5) {
//						int zweiteStelle = new FeldPositionieren().checkFirst(wump.bestimmeDieErsteZahl(wump.weltArray[i][j]), 5);
//						if (zweiteStelle == 7 && new FeldPositionieren().checkFirst(zweiteStelle, 7) == 0) {
//							array[i][j] = new WumpusBitmapComponent(breezeSmell, anzahl, j, i % anzahl);
//						} else if (zweiteStelle == 0) {
//							array[i][j] = new WumpusBitmapComponent(smell, anzahl, j, i % anzahl);
//						}
//
//					} else if (wump.bestimmeDieErsteZahl(wump.weltArray[i][j]) == 6) {
//						array[i][j] = new WumpusBitmapComponent(pit2, anzahl, j, i % anzahl);
//					} else if (wump.bestimmeDieErsteZahl(wump.weltArray[i][j]) == 7) {
//						array[i][j] = new WumpusBitmapComponent(breeze, anzahl, j, i % anzahl);
//					} else if (wump.bestimmeDieErsteZahl(wump.weltArray[i][j]) == 9) {
//						array[i][j] = new WumpusBitmapComponent(grau, anzahl, j, i % anzahl);
//					} else
					if (wump.bestimmeDieErsteZahl(wump.weltArray[i][j]) == 1) {
						array[i][j] = new WumpusBitmapComponent(agent, anzahl, j, i % anzahl);
						// System.out.println("TEST: ........................." + array[i][j].getX() + "," + array[i][j].getY());
					}
					if (wump.bestimmeDieErsteZahl(wump.weltArray[i][j]) == 2) {
						int zweiteStelle = wump.bestimmeDieErsteZahl(checkFirst((wump.weltArray[i][j]), 2));
						System.out.println("Zweite: " + zweiteStelle);
						if (zweiteStelle == 5) {
							int dritteStelle = wump.bestimmeDieErsteZahl(checkFirst(checkFirst((wump.weltArray[i][j]), 2), 5));
							if (dritteStelle == 7) {
								array[i][j] = new WumpusBitmapComponent(breezeSmellGold, anzahl, j, i % anzahl);
							} else if (dritteStelle == 0) {
								array[i][j] = new WumpusBitmapComponent(smellGold, anzahl, j, i % anzahl);
							}
						} else if (zweiteStelle == 0) {
							array[i][j] = new WumpusBitmapComponent(gold, anzahl, j, i % anzahl);
						}
					}
					if (wump.bestimmeDieErsteZahl(wump.weltArray[i][j]) == 2) {
						int zweiteStelle = wump.bestimmeDieErsteZahl(checkFirst((wump.weltArray[i][j]), 2));
						System.out.println("Zweite: " + zweiteStelle);
						if (zweiteStelle == 7) {
							array[i][j] = new WumpusBitmapComponent(breezeGold, anzahl, j, i % anzahl);
						} else if (zweiteStelle == 0) {
							array[i][j] = new WumpusBitmapComponent(gold, anzahl, j, i % anzahl);
						}

					}
					if (wump.bestimmeDieErsteZahl(wump.weltArray[i][j]) == 3) {
						int zweiteStelle = wump.bestimmeDieErsteZahl(checkFirst((wump.weltArray[i][j]), 3));
						System.out.println("Zweite: " + zweiteStelle);
						if(zweiteStelle == 4) {
							array[i][j] = new WumpusBitmapComponent(wumpus2, anzahl, j, i % anzahl);
						}else if (zweiteStelle == 5) {
							int dritteStelle = wump.bestimmeDieErsteZahl(checkFirst(checkFirst((wump.weltArray[i][j]), 3), 5));
							System.out.println("Dritte: " + dritteStelle);
							if (dritteStelle == 7) {
								array[i][j] = new WumpusBitmapComponent("BreezeSmellGlitter.JPG", anzahl, j, i % anzahl);
							} else if (dritteStelle == 0) {
								array[i][j] = new WumpusBitmapComponent(smellGlitter, anzahl, j, i % anzahl);
							}
						} else if (zweiteStelle == 0) {
							array[i][j] = new WumpusBitmapComponent(glitter, anzahl, j, i % anzahl);
						}
					} 
					if (wump.bestimmeDieErsteZahl(wump.weltArray[i][j]) == 3) {
						int zweiteStelle = wump.bestimmeDieErsteZahl(checkFirst((wump.weltArray[i][j]), 3));
						System.out.println("Zweite: " + zweiteStelle);
						if(zweiteStelle == 4) {
							array[i][j] = new WumpusBitmapComponent(wumpus2, anzahl, j, i % anzahl);
						}else if(zweiteStelle == 6) {
							array[i][j] = new WumpusBitmapComponent(pit, anzahl, j, i % anzahl);
						}else if (zweiteStelle == 7) {
							array[i][j] = new WumpusBitmapComponent(breezeGlitter, anzahl, j, i % anzahl);
						} else if (zweiteStelle == 0) {
							array[i][j] = new WumpusBitmapComponent(glitter, anzahl, j, i % anzahl);
						}

					} else if (wump.bestimmeDieErsteZahl(wump.weltArray[i][j]) == 4) {
						array[i][j] = new WumpusBitmapComponent(wumpus2, anzahl, j, i % anzahl);
					} else if (wump.bestimmeDieErsteZahl(wump.weltArray[i][j]) == 5) {
						int zweiteStelle = wump.bestimmeDieErsteZahl(checkFirst((wump.weltArray[i][j]), 5));
						System.out.println("Zweite: " + zweiteStelle);
						if(zweiteStelle == 6) {
							array[i][j] = new WumpusBitmapComponent(pit, anzahl, j, i % anzahl);
						}else if (zweiteStelle == 7) {
							array[i][j] = new WumpusBitmapComponent(breezeSmell, anzahl, j, i % anzahl);
						} else if (zweiteStelle == 0) {
							array[i][j] = new WumpusBitmapComponent(smell, anzahl, j, i % anzahl);
						}

					} else if (wump.bestimmeDieErsteZahl(wump.weltArray[i][j]) == 6) {
						array[i][j] = new WumpusBitmapComponent(pit, anzahl, j, i % anzahl);
					} else if (wump.bestimmeDieErsteZahl(wump.weltArray[i][j]) == 7) {
						array[i][j] = new WumpusBitmapComponent(breeze, anzahl, j, i % anzahl);
					} else if (wump.bestimmeDieErsteZahl(wump.weltArray[i][j]) == 9) {
						array[i][j] = new WumpusBitmapComponent(grau, anzahl, j, i % anzahl);
					} else	if (wump.bestimmeDieErsteZahl(wump.weltArray[i][j]) == 0) {
						array[i][j] = new WumpusBitmapComponent(schwarz, anzahl, j, i % anzahl);
					}
					System.out.println("zahl in wumpuswelt: " + wump.weltArray[i][j]);
					if(array[i][j] !=null){
						System.out.println("File: " + array[i][j].getFileName());
					}	
					this.add(array[i][j], new GridBagConstraints(x, y, 1, 1, 0.1, 0.1, GridBagConstraints.SOUTH, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					// System.out.println("X = " + x +" Y = " + y);
					// this.add(new JLabel("Test: x:" + i + " y: " + j), new GridBagConstraints(x, y, 1, 1, 0.1, 0.1, GridBagConstraints.SOUTH, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					// array.actionPerformed();
					// array[i][j].addMouseListener(mkl);
					x++;
					h++;
				}
			}
			y++;
			x = 1;
		}
	}

	/**
	 * Diese Methode schneidet den ersten Element(beliebige Zahl) einer Zahlz.B. 0123 nach der Methode 123
	 * @param zahl ein Zahl
	 * @param first erstes Zahl
	 */
	public int checkFirst(int zahl, int first) {
		int zaehler = 0;
		int original = zahl;
		// System.out.println("Orginal " + orginal);
		do {
			zahl = zahl / 10;
			// System.out.println("Zahl " + zahl);
			zaehler++;
		} while (zahl > 10);
		// System.out.println("Zähler " + zaehler);
		for (int i = 0; i < zaehler; i++) {
			first = first * 10;
		}
		// if(zahl < 0)zahl = 9; und if(zahl == 0) zahl =9;
		return (original - first) <= 0 ? 0 : original - first;
	}
	
	/**
	 * Diese Methode bestimmt welches Bild ist mit Y,X-Koordinaten.
	 * 
	 * @param y
	 *            Y-Koordinate des Feldes
	 * @param x
	 *            X-Koordinate des Feldes
	 * @return wird ein Bild zurückgegeben.
	 */
	public String welchesBild(int y, int x) {
		if (wump.bestimmeDieErsteZahl(new FeldPositionieren().checkFirst(wump.weltArray[y][x])) == 2) {
			return gold;
		} else if (wump.bestimmeDieErsteZahl(new FeldPositionieren().checkFirst(wump.weltArray[y][x])) == 3) {
			return glitter;
		} else if (wump.bestimmeDieErsteZahl(new FeldPositionieren().checkFirst(wump.weltArray[y][x])) == 4) {
			return wumpus2;
		} else if (wump.bestimmeDieErsteZahl(new FeldPositionieren().checkFirst(wump.weltArray[y][x])) == 5) {
			return smell;
		} else if (wump.bestimmeDieErsteZahl(new FeldPositionieren().checkFirst(wump.weltArray[y][x])) == 6) {
			return pit;
		} else if (wump.bestimmeDieErsteZahl(new FeldPositionieren().checkFirst(wump.weltArray[y][x])) == 7) {
			return breeze;
		} else if (wump.bestimmeDieErsteZahl(new FeldPositionieren().checkFirst(wump.weltArray[y][x])) == 9) {
			return grau;
		}
		return schwarz;
	}

	/**
	 * Entfernt den Agenten aus seinem bisherigen Feld und ersetzt das Feld durch die Wahrnehmungen die in dem Feld sind. Setzt anschließend den Agenten in das neue Feld
	 * 
	 * @param alteY
	 *            Alte Y-Kordinate des Agenten
	 * @param alteX
	 *            Alte X-Kordinate des Agenten
	 * @param neuY
	 *            Neue Y-Kordinate des Agenten
	 * @param neuX
	 *            Neue X-Kordinate des Agenten
	 */
	public void wechseleNur2Bilder(int alteY, int alteX, int neuY, int neuX) {
		System.err.println("Altes");
		System.out.println("Y: " + alteY + " X: " + alteX + " Dateiname: " + array[alteY][alteX].getFileName());
		remove(array[alteY][alteX]);
		// repaint();

		// removeAll();
		// retainAll();// loescht alle elemente der Collection die nicht in eine andere Collection gibt
		// clear();
		array[alteY][alteX] = new WumpusBitmapComponent(welchesBild(alteY, alteX), anzahl, alteY, alteX);
		add(array[alteY][alteX], new GridBagConstraints(alteY + 1, alteX + 1, 1, 1, 0.1, 0.1, GridBagConstraints.SOUTH, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		// array[alteY][alteX].changeImage(welchesBild(alteY, alteX));
		validate();
		System.err.println("Neues");
		System.out.println("Y: " + neuY + " X: " + neuX + " Dateiname: " + array[neuY][neuX].getFileName());
		remove(array[neuY][neuX]);
		/* Agent muss vorne an die Wahrnemung angefügt werden */
		array[neuY][neuX] = new WumpusBitmapComponent(agent, anzahl, neuY, neuX);
		add(array[neuY][neuX], new GridBagConstraints(neuY + 1, neuX + 1, 1, 1, 0.1, 0.1, GridBagConstraints.SOUTH, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		// repaint();
		validate();
	}

	// TODO: Zeichemethode beim erstellen eines neuen Feldes wird 2 mal aufgerufen
	// private static Object remove(Object array, int index) {
	// int length = getLength(array);
	// if (index < 0 || index >= length) {
	// throw new IndexOutOfBoundsException("Index: " + index + ", Length: " + length);
	// }
	//	      
	// Object result = Array.newInstance(array.getClass().getComponentType(), length - 1);
	// System.arraycopy(array, 0, result, 0, index);
	// if (index < length - 1) {
	// System.arraycopy(array, index + 1, result, index, length - index - 1);
	// }
	//	      
	// return result;
	// }

	// TODO: die Fokus geht verloren. d.h. wenn man den agent steuert, dann funktioniert nicht alt+1 usw...

	public void update(Observable obj, Object arg) {
		// if (((NachrichtenObjekt) arg).information.equals(Bezeichnungen.POSITION)) {
		// alteY = ((NachrichtenObjekt) arg).y;
		// alteX = ((NachrichtenObjekt) arg).x;
		// }
		if (((NachrichtenObjekt) arg).information.equals(Bezeichnungen.REPAINT)) {
			zeichnen();
		}
		if (((NachrichtenObjekt) arg).information.equals(Bezeichnungen.NEUES_SPIEL)) {
			// erstesmal = true;
			alteY = ((NachrichtenObjekt) arg).y;
			alteX = ((NachrichtenObjekt) arg).x;
			zeichnen();
		}
		if (((NachrichtenObjekt) arg).information.equals(Bezeichnungen.BEWEGE)) {
			neuY = ((NachrichtenObjekt) arg).wahrnehmung[0];
			neuX = ((NachrichtenObjekt) arg).wahrnehmung[1];
			// wechseleNur2Bilder(alteY, alteX, neuY, neuX);
			alteY = neuY;
			alteX = neuX;
		}
		// if (((NachrichtenObjekt) arg).information.equals(Bezeichnungen.)) {
		// wechseleNur2Bilder(alteY, alteX, neuY, neuX);
		// }

	}

}
