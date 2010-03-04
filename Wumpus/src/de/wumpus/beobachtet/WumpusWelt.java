package de.wumpus.beobachtet;

import java.util.Observable;

import de.wumpus.tools.*;

public class WumpusWelt extends Observable {

	FeldPositionieren positioniere = new FeldPositionieren();
	public int anzahl = 0;
	int agent_y = 0, agent_x = 0;
	int neuY = 0, neuX = 0;

	public int[][] weltArray;

	/**
	 * Die Methode gibt die Anzahl des Feldes(icons) und initialisiert die WumpusWelt.
	 * 
	 * @param size
	 *            die grosse des Feldes
	 */
	public void grossedesFeldes(int size) {
		anzahl = size;
		System.out.println("anzahl in Welt " + anzahl);
		weltArray = positioniere.feldPositionierung(anzahl);
		// weltArray = new int[anzahl][anzahl];
		for (int i = 0; i < anzahl; i++) {
			for (int j = 0; j < anzahl; j++) {
				// weltArray[i][j] = 0;
				if (bestimmeDieErsteZahl(weltArray[i][j]) == 1) {
					agent_y = i;
					agent_x = j;
					setChanged();
					notifyObservers(new NachrichtenObjekt(agent_y, agent_x, positioniere.separateWahrnehmungen(weltArray[i][j]), Bezeichnungen.NEUES_SPIEL));
					System.out.println("X " + agent_x + " Y " + agent_y);

				}
				// System.out.println("Position " + i + "," + j + " Wert " +
				// weltArray[i][j]);
			}

		}
	}

	/**
	 * Die Methode gibt die Anzahl des Feldes mit Positionen von Agent,Gold,Wumpus,Pit und initialisiert die WumpusWelt.
	 * 
	 * @param size
	 *            Größe des Feldes
	 * @param positionen
	 *            Vom Benutzerdefiniertes_Feld übergebene Positionen
	 */
	public void grossedesFeldes(int size, int[] positionen) {
		anzahl = size;
		// System.out.println("anzahl in Welt " + anzahl);
		weltArray = positioniere.myfeldPositionierung(anzahl, positionen);
		for (int i = 0; i < anzahl; i++) {
			for (int j = 0; j < anzahl; j++) {
				// weltArray[i][j] = 0;
				// System.out.println("weltArray[i][j] " +weltArray[i][j]);
				if (bestimmeDieErsteZahl(weltArray[i][j]) == 1) {
					agent_y = i;
					agent_x = j;
					setChanged();
					notifyObservers(new NachrichtenObjekt(agent_y, agent_x, positioniere.separateWahrnehmungen(weltArray[i][j]), Bezeichnungen.NEUES_SPIEL));
					System.out.println("Y " + agent_y + " X " + agent_x);
				}
				// System.out.println("Position " + i + "," + j + " Wert " +
				// weltArray[i][j]);
			}

		}
	}

	/* Diese Methode bestimm die Position des Agentes */
	// public void positiondesAgentes() {
	// for (int i = 0; i < anzahl; i++) {
	// for (int j = 0; j < anzahl; j++) {
	// if (bestimmeDieErsteZahl(weltArray[i][j]) == 1) {
	// //TODO: ob es sinnvoll??????????????????????????????????????????????
	// setChanged();
	// notifyObservers(new NachrichtenObjekt(i, j, new int[] { weltArray[i][j] }, Bezeichnungen.POSITION_DES_AGENTES));
	// }
	// }
	// }
	// }
	/**
	 * Die Methode bestimmt die erste Zahl, die in der Arrayposition abgespeichert ist. d.h. die erste Teil der Zahl. z.B. Zahl = 234 nach der Methode Zahl = 2
	 * 
	 * @param zahl
	 *            ein Zahl
	 */
	public int bestimmeDieErsteZahl(int zahl) {
		do {
			if (zahl < 10)
				return zahl;
			else
				zahl = zahl / 10;
		} while (zahl > 10);
		return zahl;
	}

	// public void test(int x, int y, int wahrnehmung, String information){
	// setChanged();
	// notifyObservers(new NachrichtenObjekt(x,y,wahrnehmung, information));
	// }

	/**
	 * Durch diese Methode wird die grosse des WumpusWelt festgelegt
	 * 
	 * @param weltGrosse
	 *            bestimmt die Grosse des Feldes
	 */
	public void neuesSpiel(int weltGroesse) {
		grossedesFeldes(weltGroesse);
		// System.out.println("Teste vor notifyObservers");
		setChanged();
		notifyObservers(new NachrichtenObjekt(0, 0, null, Bezeichnungen.REPAINT));
		// System.out.println("Teste nach notifyObservers");
	}

	/**
	 * Diese Methode ermoglicht schritweise die Bewegung des Agentes.
	 */
	public void bewegeAgentPerTaste() {
		setChanged();
		notifyObservers(new NachrichtenObjekt(0, 0, null, Bezeichnungen.BEWEGE_AGENT));
		setChanged();
		notifyObservers(new NachrichtenObjekt(0, 0, null, Bezeichnungen.REPAINT));
	}

	/**
	 * neues Spiel durch von Benutzer festgelegten Positionen
	 * 
	 * @param weltGrosse
	 *            bestimmt die Grosse des Feldes
	 * @param []positionen es sind die Positionen(y,x) von Agent,Gold,Wumpus,Pit.
	 */
	public void neuesSpiel(int weltGroesse, int[] positionen) {

		grossedesFeldes(weltGroesse, positionen);
		System.out.println("Teste vor notifyObservers");
		setChanged();
		notifyObservers(new NachrichtenObjekt(0, 0, null, Bezeichnungen.REPAINT));
		System.out.println("Teste nach notifyObservers");
	}

	/**
	 * Die Methode bewegt den Agen in der WumpusWelt und sendet an der Welt neu Position des Agentes
	 * 
	 * @param richtung
	 *            bestimmt die Richtung
	 */
	public void bewegeAgent(String richtung) {
		int x_r = 0, y_r = 0;
		boolean wumpusToeten = false;
		/* Abfrage nach der Richtung */
		if (richtung.equals(Bezeichnungen.RECHTS)) {
			x_r = 1;
			y_r = 0;
		} else if (richtung.equals(Bezeichnungen.LINKS)) {
			x_r = -1;
			y_r = 0;
		} else if (richtung.equals(Bezeichnungen.UP)) {
			x_r = 0;
			y_r = -1;
		} else if (richtung.equals(Bezeichnungen.DOWN)) {
			x_r = 0;
			y_r = 1;
		} else if (richtung.equals(Bezeichnungen.BENUTZE_PFEIL + " " + Bezeichnungen.RECHTS)) {
			x_r = 1;
			y_r = 0;
			wumpusToeten = true;
		} else if (richtung.equals(Bezeichnungen.BENUTZE_PFEIL + " " + Bezeichnungen.LINKS)) {
			x_r = -1;
			y_r = 0;
			wumpusToeten = true;
		} else if (richtung.equals(Bezeichnungen.BENUTZE_PFEIL + " " + Bezeichnungen.UP)) {
			x_r = 0;
			y_r = -1;
			wumpusToeten = true;
		} else if (richtung.equals(Bezeichnungen.BENUTZE_PFEIL + " " + Bezeichnungen.DOWN)) {
			x_r = 0;
			y_r = 1;
			wumpusToeten = true;
		}
		/*
		 * Es werden zuerts die Abfragungen gemacht, d.h. wenn der Agent in der 0.0 ist, muss er nicht in Auserbereich tretten(in diesem Fall nur rechts oder nach unten).
		 */
		if (agent_y + y_r >= 0 && agent_x + x_r >= 0 && agent_y + y_r < anzahl && agent_x + x_r < anzahl) {
			// System.out.println("vor Bewegung:" + "X" + agent_x + " Y" + agent_y + " XR" + x_r + " YR" + y_r + " FELD" + weltArray[agent_y][agent_x]);
			if (weltArray != null) {
				int alteWahrnehmung = weltArray[agent_y][agent_x];
				int neueWahrnehmung = weltArray[agent_y + y_r][agent_x + x_r];
				// System.out.println("alteWahrnehmung " + alteWahrnehmung + " " + weltArray[agent_y][agent_x]);
				weltArray[agent_y][agent_x] = positioniere.checkLast(positioniere.checkFirst(alteWahrnehmung), 9);
				if (!wumpusToeten) {
					weltArray[agent_y + y_r][agent_x + x_r] = positioniere.checkLast(neueWahrnehmung, 1);
					setChanged();
					notifyObservers(new NachrichtenObjekt(agent_y, agent_x, new int[] { (agent_y + y_r), (agent_x + x_r) }, Bezeichnungen.BEWEGE));
					agent_y = agent_y + y_r;
					agent_x = agent_x + x_r;
					int[] wahrnemungen = positioniere.separateWahrnehmungen(weltArray[agent_y][agent_x]);
					setChanged();
					notifyObservers(new NachrichtenObjekt(agent_y, agent_x, wahrnemungen, Bezeichnungen.WAHRNEHMUNGEN));
					setChanged();
					notifyObservers(new NachrichtenObjekt(agent_y, agent_x, wahrnemungen, Bezeichnungen.AGENT));
				} else {
					int temp = weltArray[agent_y + y_r][agent_x + x_r];
					weltArray[agent_y + y_r][agent_x + x_r] = positioniere.entferneWahnehmung(weltArray[agent_y + y_r][agent_x + x_r], 4);
					if (temp != weltArray[agent_y + y_r][agent_x + x_r]) {
						if (agent_y + y_r - 1 >= 0 && agent_x + x_r >= 0 && agent_y + y_r - 1 < anzahl && agent_x + x_r < anzahl)
							weltArray[agent_y + y_r - 1][agent_x + x_r] = positioniere.entferneWahnehmung(weltArray[agent_y + y_r - 1][agent_x + x_r], 5);
						
						if (agent_y + y_r + 1 >= 0 && agent_x + x_r >= 0 && agent_y + y_r + 1 < anzahl && agent_x + x_r < anzahl)
							weltArray[agent_y + y_r + 1][agent_x + x_r] = positioniere.entferneWahnehmung(weltArray[agent_y + y_r + 1][agent_x + x_r], 5);

						if (agent_y + y_r >= 0 && agent_x + x_r - 1 >= 0 && agent_y + y_r < anzahl && agent_x + x_r - 1 < anzahl)
							weltArray[agent_y + y_r][agent_x + x_r - 1] = positioniere.entferneWahnehmung(weltArray[agent_y + y_r][agent_x + x_r - 1], 5);

						if (agent_y + y_r >= 0 && agent_x + x_r + 1 >= 0 && agent_y + y_r < anzahl && agent_x + x_r + 1 < anzahl)
							weltArray[agent_y + y_r][agent_x + x_r + 1] = positioniere.entferneWahnehmung(weltArray[agent_y + y_r][agent_x + x_r + 1], 5);
						setChanged();
						notifyObservers(new NachrichtenObjekt(agent_y + y_r, agent_x + y_r, null, Bezeichnungen.WUMPUS_WURDE_GETOETET));
					}else{
						//WUMPUS WAR NICHT DA
						
					}
				}
			}
		}
	}

	public int agentNeuY() {
		return neuY;
	}

	public int agentNeuX() {
		return neuX;
	}

	public void agentIstFertig() {
		setChanged();
		notifyObservers(new NachrichtenObjekt(agent_y, agent_x, new int[] {}, Bezeichnungen.FERTIG));
	}
	
	public void aktualisiereBild(int y, int x){
		setChanged();
		notifyObservers(new NachrichtenObjekt(y, x, new int[] {}, Bezeichnungen.AKTUALISIERE_BILD));
	}
}
