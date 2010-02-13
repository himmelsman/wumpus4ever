package de.wumpus.beobachtet;

import java.util.Observable;

import de.wumpus.tools.*;

public class WumpusWelt extends Observable {

	FeldPositionieren positioniere = new FeldPositionieren();
	public int anzahl = 0;
	int agent_y = 0, agent_x = 0;

	public int[][] weltArray;

	/*
	 * die Methode gibt die Anzahl des Feldes(icon) und initialisiert die
	 * WumpusWelt
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
					setChanged();
					notifyObservers(new NachrichtenObjekt(i, j, new int[] { weltArray[i][j] }, Bezeichnungen.AGENT));
					agent_y = i;
					agent_x = j;
					System.out.println("X " + agent_x + " Y " + agent_y);
				}
				// System.out.println("Position " + i + "," + j + " Wert " +
				// weltArray[i][j]);
			}

		}
	}

	/* Diese Methode bestimm die Position des Agentes */
	public void positiondesAgentes() {
		setChanged();
		String information = "AGENT";
		for (int i = 0; i < anzahl; i++) {
			for (int j = 0; j < anzahl; j++) {
				if (bestimmeDieErsteZahl(weltArray[i][j]) == 1)
					notifyObservers(new NachrichtenObjekt(i, j, new int[] { weltArray[i][j] }, information));
			}
		}
	}

	// TODO die geschwindigkeit soll uebergeben werden
	// TODO: Sortierung
	/*
	 * Die Methode bestimmt die erste Zahl, die in der Arrayposition
	 * abgespeichert ist. d.h. die erste Teil der Zahl. z.B. Zahl = 234 nach der
	 * Methode Zahl = 2
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

	/* Durch diese Methode wird die grosse des WumpusWelt festgelegt */
	public void neuesSpiel(int weltGroesse) {
		grossedesFeldes(weltGroesse);
		System.out.println("Teste vor notifyObservers");
		setChanged();
		notifyObservers(new NachrichtenObjekt(0, 0, null, Bezeichnungen.REPAINT));
		System.out.println("Teste nach notifyObservers");
	}

	/*
	 * Die Methode bewegt den Agen in der WumpusWelt und sendet an der Welt neu
	 * Position des Agentes
	 */
	public void bewegeAgent(String richtung) {
		int x_r = 0, y_r = 0;
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
		}
		/*
		 * Es werden zuerts die Abfragungen gemacht, d.h. wenn der Agent in der
		 * 0.0 ist, muss er nicht in Auserbereich tretten(in diesem Fall nur
		 * rechts oder nach unten).
		 */ 
		if (agent_y + y_r >= 0 && agent_x + x_r >= 0 && agent_y + y_r < anzahl && agent_x + x_r < anzahl) {
			//System.out.println("vor Bewegung:" + "X" + agent_x + " Y" + agent_y + " XR" + x_r + " YR" + y_r + " FELD" + weltArray[agent_y][agent_x]);
			if (weltArray != null) {
				int alteWahrnehmung = weltArray[agent_y][agent_x];
				int neueWahrnehmung = weltArray[agent_y + y_r][agent_x + x_r];
				//System.out.println("alteWahrnehmung " + alteWahrnehmung + " " + weltArray[agent_y][agent_x]);
				weltArray[agent_y][agent_x] = positioniere.checkLast(positioniere.checkFirst(alteWahrnehmung), 9);
				weltArray[agent_y + y_r][agent_x + x_r] = positioniere.checkLast(neueWahrnehmung, 1);
				//System.out.println("alteWahrnehmung nach " + weltArray[agent_y][agent_x]);
				setChanged();
				notifyObservers(new NachrichtenObjekt(agent_y, agent_x, new int[] { (agent_y + y_r), (agent_x + x_r) }, Bezeichnungen.BEWEGE));
				agent_y = agent_y + y_r;
				agent_x = agent_x + x_r;
				//System.out.println("nach Bewegung:" + "X" + agent_x + " Y" + agent_y + " XR" + x_r + " YR" + y_r + " FELD" + weltArray[agent_y][agent_x]);
				// Änderung der Wahrnehmungssendung - alle auf einmal Senden
				int[] wahrnemungen = positioniere.separateWahrnehmungen(weltArray[agent_y][agent_x]);
				setChanged();
				notifyObservers(new NachrichtenObjekt(agent_x, agent_y, wahrnemungen, Bezeichnungen.WAHRNEHMUNGEN));
				setChanged();
				notifyObservers(new NachrichtenObjekt(agent_x, agent_y, wahrnemungen, Bezeichnungen.AGENT));
			}
		}
	}
}
