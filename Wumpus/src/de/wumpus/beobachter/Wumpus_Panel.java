package de.wumpus.beobachter;

import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;

import de.wumpus.beobachtet.WumpusWelt;
import de.wumpus.tools.Bezeichnungen;
import de.wumpus.tools.NachrichtenObjekt;
import de.wumpus.tools.WumpusBitmapComponent;

@SuppressWarnings("serial")
public class Wumpus_Panel extends JPanel implements Observer {
	WumpusWelt wump;
	private int anzahl;
	// private int _anzahl;
	private int neuY = 0, neuX = 0, alteY = 0, alteX = 0;
	WumpusBitmapComponent[][] imageArray;
	private GridBagLayout gridBagLayout;
	// Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
	// private static int panelGrosse;

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
	String smellGold = "SmellGold.JPG";
	String breezeSmell = "BreezeSmell.JPG";
	String breezeSmellGold = "BreezeSmellGold.JPG";
	String breezeGlitter = "BreezeGlitter.JPG";
	String smellGlitter = "SmellGlitter.JPG";
	String breezeSmellGlitter = "BreezeSmellGlitter.JPG";

	public Wumpus_Panel(WumpusWelt _wump) {
		super();
		setVisible(true);
		setSize(1, 1);
		wump = _wump;
		anzahl = wump.anzahl;
		imageArray = new WumpusBitmapComponent[anzahl][anzahl];
		initGUI();
	}

	public void setzeAnzahl() {
		setVisible(true);
		anzahl = wump.anzahl;
		imageArray = new WumpusBitmapComponent[anzahl][anzahl];
		initGUI();
	}

	@Override
	public void paint(Graphics g) {
//		System.out.println("getWidth:" +getWidth() + " getHeight:" + getHeight());
		for (int y = 0; y < anzahl; y++) {
			for (int x = 0; x < anzahl; x++) {
				imageArray[y][x].resizeImage(getWidth() / anzahl, getHeight() / anzahl);
			}
		}
		super.paint(g);
	}

	/**
	 * Initalisiert das WumpusPanel, d.h. es werden alle Componenten entfernt und anhand der Informationen der WumpusWelt <b>wump</b> neu eingefügt.
	 */
	private void initGUI() {
		removeAll(); // TODO: setze size so das erstmal passt
		gridBagLayout = setzeLayout();
		setLayout(gridBagLayout);
		imageArray = new WumpusBitmapComponent[anzahl][anzahl];
		zeichneAxenBeschriftung();
		zeichnen();
	}

	/**
	 * Zeichnet die Axenbeschriftung in das WumpusPanel.
	 * 
	 */
	private void zeichneAxenBeschriftung() {
		for (int i = 0; i < anzahl; i++) {
			add(new JLabel("" + (i + 1)), new GridBagConstraints(0, i + 1, 1, 1, 0.01, 0.01, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
			add(new JLabel("" + (i + 1)), new GridBagConstraints(i + 1, 0, 1, 1, 0.01, 0.01, GridBagConstraints.CENTER, 0, new Insets(0, 0, 0, 0), 0, 0));
		}
	}

	/**
	 * Ermittelt anhand der Aanzahl die notwendigen Bedingungen für das Layout und erstellt es.
	 * 
	 * @return GridBagLayout
	 */
	private GridBagLayout setzeLayout() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		double[] weights = new double[anzahl + 1];
		int[] heights = new int[anzahl + 1];
		for (int i = 0; i < anzahl + 1; i++) {
			if (i == 0) {
				weights[i] = 0.01;
				heights[i] = 7;
			} else {
				weights[i] = 0.1;
				heights[i] = 7;
			}
		}
		gridBagLayout.rowWeights = weights;
		gridBagLayout.rowHeights = heights;
		gridBagLayout.columnWeights = weights;
		gridBagLayout.columnWidths = heights;
		return gridBagLayout;
	}

	/**
	 * Diese Methode zeichnet die Bilder in das WumpusPanel
	 */
	public void zeichnen() {
		for (int y = 1; y < anzahl + 1; y++) {
			for (int x = 1; x < anzahl + 1; x++) {
				{
					imageArray[y - 1][x - 1] = welchesBild(wump.weltArray[y - 1][x - 1]);
					// muss im gridbag x und y sein sonst bewegt up nach left
					this.add(imageArray[y - 1][x - 1], new GridBagConstraints(x, y, 1, 1, 0.1, 0.1, GridBagConstraints.SOUTH, GridBagConstraints.BOTH, new Insets(1, 1, 1, 1), 0, 0));
				}
			}
		}
	}

	/**
	 * Diese Methode ermittelt welches Bild für die <b>wahrnehmung</b> gebraucht wird.
	 * 
	 * @param wahrnehmung
	 * @return WumpusBitmapComponent
	 */
	private WumpusBitmapComponent welchesBild(int wahrnehmung) {
		if (wump.bestimmeDieErsteZahl(wahrnehmung) == 1) {
			return new WumpusBitmapComponent(agent, this.getSize().height / anzahl, this.getSize().width / anzahl);
			// System.out.println("TEST: ........................." + array[y-1][x-1].getX() + "," + array[y-1][x-1].getY());
		}
		if (wump.bestimmeDieErsteZahl(wahrnehmung) == 2) {
			int zweiteStelle = wump.bestimmeDieErsteZahl(checkFirst((wahrnehmung), 2));
			System.out.println("zweiteStelle:" + zweiteStelle);
			if (zweiteStelle == 5) {
				int dritteStelle = wump.bestimmeDieErsteZahl(checkFirst(checkFirst((wahrnehmung), 2), 5));
				System.out.println("dritteStelle:" + dritteStelle);
				if (dritteStelle == 7) {
					return new WumpusBitmapComponent(breezeSmellGold, this.getSize().height / anzahl, this.getSize().width / anzahl);
				} else if (dritteStelle == 0 || dritteStelle == 9) {
					return new WumpusBitmapComponent(smellGold, this.getSize().height / anzahl, this.getSize().width / anzahl);
				}
			} else if (zweiteStelle == 0 || zweiteStelle == 9) {
				return new WumpusBitmapComponent(gold, this.getSize().height / anzahl, this.getSize().width / anzahl);
			}
		}
		if (wump.bestimmeDieErsteZahl(wahrnehmung) == 2) {
			int zweiteStelle = wump.bestimmeDieErsteZahl(checkFirst((wahrnehmung), 2));
			if (zweiteStelle == 7) {
				return new WumpusBitmapComponent(breezeGold, this.getSize().height / anzahl, this.getSize().width / anzahl);
			} else if (zweiteStelle == 0 || zweiteStelle == 9) {
				return new WumpusBitmapComponent(gold, this.getSize().height / anzahl, this.getSize().width / anzahl);
			}

		}
		if (wump.bestimmeDieErsteZahl(wahrnehmung) == 3) {
			int zweiteStelle = wump.bestimmeDieErsteZahl(checkFirst((wahrnehmung), 3));
			if (zweiteStelle == 4) {
				return new WumpusBitmapComponent(wumpus2, this.getSize().height / anzahl, this.getSize().width / anzahl);
			} else if (zweiteStelle == 5) {
				int dritteStelle = wump.bestimmeDieErsteZahl(checkFirst(checkFirst((wahrnehmung), 3), 5));
				if (dritteStelle == 7) {
					return new WumpusBitmapComponent(breezeSmellGlitter, this.getSize().height / anzahl, this.getSize().width / anzahl);
				} else if (dritteStelle == 0 || dritteStelle == 9) {
					return new WumpusBitmapComponent(smellGlitter, this.getSize().height / anzahl, this.getSize().width / anzahl);
				}
			} else if (zweiteStelle == 0 || zweiteStelle == 9) {
				return new WumpusBitmapComponent(glitter, this.getSize().height / anzahl, this.getSize().width / anzahl);
			}
		}
		if (wump.bestimmeDieErsteZahl(wahrnehmung) == 3) {
			int zweiteStelle = wump.bestimmeDieErsteZahl(checkFirst((wahrnehmung), 3));
			if (zweiteStelle == 4) {
				return new WumpusBitmapComponent(wumpus2, this.getSize().height / anzahl, this.getSize().width / anzahl);
			} else if (zweiteStelle == 6) {
				return new WumpusBitmapComponent(pit, this.getSize().height / anzahl, this.getSize().width / anzahl);
			} else if (zweiteStelle == 7) {
				return new WumpusBitmapComponent(breezeGlitter, this.getSize().height / anzahl, this.getSize().width / anzahl);
			} else if (zweiteStelle == 0 || zweiteStelle == 9) {
				return new WumpusBitmapComponent(glitter, this.getSize().height / anzahl, this.getSize().width / anzahl);
			}

		} else if (wump.bestimmeDieErsteZahl(wahrnehmung) == 4) {
			return new WumpusBitmapComponent(wumpus2, this.getSize().height / anzahl, this.getSize().width / anzahl);
		} else if (wump.bestimmeDieErsteZahl(wahrnehmung) == 5) {
			int zweiteStelle = wump.bestimmeDieErsteZahl(checkFirst((wahrnehmung), 5));
			if (zweiteStelle == 6) {
				return new WumpusBitmapComponent(pit, this.getSize().height / anzahl, this.getSize().width / anzahl);
			} else if (zweiteStelle == 7) {
				return new WumpusBitmapComponent(breezeSmell, this.getSize().height / anzahl, this.getSize().width / anzahl);
			} else if (zweiteStelle == 0 || zweiteStelle == 9) {
				return new WumpusBitmapComponent(smell, this.getSize().height / anzahl, this.getSize().width / anzahl);
			}

		} else if (wump.bestimmeDieErsteZahl(wahrnehmung) == 6) {
			return new WumpusBitmapComponent(pit, this.getSize().height / anzahl, this.getSize().width / anzahl);
		} else if (wump.bestimmeDieErsteZahl(wahrnehmung) == 7) {
			return new WumpusBitmapComponent(breeze, this.getSize().height / anzahl, this.getSize().width / anzahl);
		} else if (wump.bestimmeDieErsteZahl(wahrnehmung) == 9) {
			return new WumpusBitmapComponent(grau, this.getSize().height / anzahl, this.getSize().width / anzahl);
		} else if (wump.bestimmeDieErsteZahl(wahrnehmung) == 0) {
			return new WumpusBitmapComponent(schwarz, this.getSize().height / anzahl, this.getSize().width / anzahl);
		}
		// Weil muss etwas returnen
		System.out.println("Wahrnehmung war:" + wahrnehmung + " und gab keinen eindeutige erkenntnis daher schwarz eingesetzt");
		return new WumpusBitmapComponent(schwarz, this.getSize().height / anzahl, this.getSize().width / anzahl);

	}

	/**
	 * Diese Methode schneidet den ersten Element(beliebige Zahl) einer Zahlz.B. 0123 nach der Methode 123
	 * 
	 * @param zahl
	 *            ein Zahl
	 * @param first
	 *            erstes Zahl
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
	public void switchImages(int alteY, int alteX, int neueY, int neueX) {
		remove(imageArray[neueY][neueX]);
		imageArray[neueY][neueX] = new WumpusBitmapComponent(agent, getSize().height / anzahl, getSize().width / anzahl);
		add(imageArray[neueY][neueX], new GridBagConstraints(neueX + 1, neueY + 1, 1, 1, 0.1, 0.1, GridBagConstraints.SOUTH, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		remove(imageArray[alteY][alteX]);
		imageArray[alteY][alteX] = welchesBild(wump.weltArray[alteY][alteX]);
		add(imageArray[alteY][alteX], new GridBagConstraints(alteX + 1, alteY + 1, 1, 1, 0.1, 0.1, GridBagConstraints.SOUTH, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		validate();
	}

	public void update(Observable obj, Object arg) {
		if (((NachrichtenObjekt) arg).information.equals(Bezeichnungen.REPAINT)) {
			paint(getGraphics());
		}
		if (((NachrichtenObjekt) arg).information.equals(Bezeichnungen.NEUES_SPIEL)) {
			// erstesmal = true;
			alteY = ((NachrichtenObjekt) arg).y;
			alteX = ((NachrichtenObjekt) arg).x;
			initGUI();
		}
		if (((NachrichtenObjekt) arg).information.equals(Bezeichnungen.BEWEGE)) {
			neuY = ((NachrichtenObjekt) arg).wahrnehmung[0];
			neuX = ((NachrichtenObjekt) arg).wahrnehmung[1];
			switchImages(alteY, alteX, neuY, neuX);
			alteY = neuY;
			alteX = neuX;
		}

	}

}
