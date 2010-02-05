package de.wumpus.beobachtet;

import java.util.Observable;

import de.wumpus.tools.*;

public class WumpusWelt extends Observable {

	// private int[][] feld = FeldPositionieren.feldPositionerung(0);
	// public void counter(int period){
	// for(;period>=0; period--){
	// setChanged();
	// notifyObservers(new Integer(period));
	// try{
	// Thread.sleep(100);
	// }catch(InterruptedException e) {
	// System.out.println("Sleep interrupted");
	// }
	// }
	// }

	FeldPositionieren positioniere = new FeldPositionieren();
	public int anzahl = 0;
	int agent_y = 0, agent_x = 0;

	public int[][] weltArray;

	// TODO Es soll die grosse des weltes bekannt werden
	/* die Methode gibt die anzahl des Feldes(icon) und initialisierung */
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
					System.out.println("X" + agent_x + " Y" + agent_y);
				}
				// System.out.println("Position " + i + "," + j + " Wert " +
				// weltArray[i][j]);
			}

		}
	}

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

	// TODO wovon die welt besteht(was ueberall plaziert)
	// TODO d.h. hier wird die welt sich selbs erstellen und an beobachter
	// abgeschickt
	// TODO (aufruf array = new WumpusBitmapComponent[anzahl][anzahl];
	// TODO gibmirWert = positioniere.feldPositionerung(anzahl);
	// TODO die geschwindigkeit soll uebergeben werden

	/*
	 * Die Methode bestimmt die erste Zahl. d.h. die erste Teil des Zahl. z.B.
	 * zahl = 234 nach der Methode zahl = 2 MAN KANN NOCH SORTIERUNG
	 * EINBAUEN!!!!!!!!!!!!!!
	 */
	// TODO:
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

	public void neuesSpiel(int weltGroesse) {
		grossedesFeldes(weltGroesse);
		System.out.println("Teste vor notifyObservers");
		setChanged();
		notifyObservers(new NachrichtenObjekt(0, 0, null, Bezeichnungen.REPAINT));
		System.out.println("Teste nach notifyObservers");
	}

	public void bewegeAgent(String richtung) {
		int x_r = 0, y_r = 0;
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
		if (agent_y + y_r >= 0 && agent_x + x_r >= 0 && agent_y + y_r < anzahl && agent_x + x_r < anzahl) {
//			System.out.println("vor Bewegung:" + "X" + agent_x + " Y" + agent_y + " XR" + x_r + " YR" + y_r + " FELD" + weltArray[agent_y][agent_x]);
			if (weltArray != null) {
				int alteWahrnehmung = weltArray[agent_y][agent_x];
				int neueWahrnehmung = weltArray[agent_y + y_r][agent_x + x_r];
//				System.out.println("alteWahrnehmung " +alteWahrnehmung + " " + weltArray[agent_y][agent_x]);
				weltArray[agent_y][agent_x] = positioniere.checkLast(positioniere.checkFirst(alteWahrnehmung), 9);
				weltArray[agent_y + y_r][agent_x + x_r] = positioniere.checkLast(neueWahrnehmung, 1);
//				System.out.println("alteWahrnehmung nach " + weltArray[agent_y][agent_x]);
				setChanged();
				notifyObservers(new NachrichtenObjekt(agent_y, agent_x, new int[] { (agent_y + y_r), (agent_x + x_r) }, Bezeichnungen.BEWEGE));
				agent_y = agent_y + y_r;
				agent_x = agent_x + x_r;
//				System.out.println("nach Bewegung:" + "X" + agent_x + " Y" + agent_y + " XR" + x_r + " YR" + y_r + " FELD" + weltArray[agent_y][agent_x]);
				sendeWahrnehmungen(weltArray[agent_y][agent_x]);
			}
		}
	}
	
	public void sendeWahrnehmungen(int zahl){
		int[] array = positioniere.separateWahrnehmungen(zahl);
		setChanged();
		notifyObservers(new NachrichtenObjekt(agent_x, agent_y, null, Bezeichnungen.POSITION));
		for (int i = 0; i < array.length; i++) {
			if (array[i] == 1) {
				setChanged();
				notifyObservers(new NachrichtenObjekt(0, 0, null, Bezeichnungen.AGENT));
			} else if (array[i] == 2) {
				setChanged();
				notifyObservers(new NachrichtenObjekt(0, 0, null, Bezeichnungen.GOLD));
			} else if (array[i] == 3) {
				setChanged();
				notifyObservers(new NachrichtenObjekt(0, 0, null, Bezeichnungen.GLITTER));
			} else if (array[i] == 4) {
				setChanged();
				notifyObservers(new NachrichtenObjekt(0, 0, null, Bezeichnungen.WUMPUS));
			} else if (array[i] == 5) {
				setChanged();
				notifyObservers(new NachrichtenObjekt(0, 0, null, Bezeichnungen.GERUCH));
			} else if (array[i] == 6) {
				setChanged();
				notifyObservers(new NachrichtenObjekt(0, 0, null, Bezeichnungen.FALLGRUBE));
			} else if (array[i] == 7) {
				setChanged();
				notifyObservers(new NachrichtenObjekt(0, 0, null, Bezeichnungen.BRISE));
			}  else if (array[i] == 9) {
				setChanged();
				notifyObservers(new NachrichtenObjekt(0, 0, null, Bezeichnungen.BESUCHT));
			}  
		}
	}

}
