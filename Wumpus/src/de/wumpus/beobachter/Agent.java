package de.wumpus.beobachter;

import java.awt.Toolkit;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import de.wumpus.beobachtet.WumpusWelt;
import de.wumpus.tools.EinSchrittZurueck;
import de.wumpus.tools.Feld;
import de.wumpus.tools.Bezeichnungen;
import de.wumpus.tools.FeldPositionieren;
import de.wumpus.tools.NachrichtenObjekt;
import de.wumpus.tools.Position;

public class Agent implements Observer {

	private int agentX, agentY, anzahl;
	int richtung;
	LinkedList<Position> positionenListe = new LinkedList<Position>();
	LinkedList<String> bewegungsListe = new LinkedList<String>();
	boolean ersteSchritt = false;
	int letzteRichtung = richtung;
	WumpusWelt wump;
	boolean yes = true;
	boolean pfeil = true;
	Feld feld;
	Feld[][] arraymitWissenBasis;

	public Agent(WumpusWelt _wump) {
		wump = _wump;
	}
	
	/**
	 * Diese Methode erzeugt ein neues Spiel(Spielfeld).
	 */
	private void neuesSpiel() {
		// if(anzahl!=wump.anzahl)
		{
			anzahl = wump.anzahl;
			arraymitWissenBasis = new Feld[anzahl][anzahl];
			for (int j = 0; j < anzahl; j++) {
				for (int i = 0; i < anzahl; i++) {
					arraymitWissenBasis[j][i] = new Feld(j, i);
				}
			}
			pfeil = true;
			setzeKeinGefahrNachStart(agentY, agentX);
		}
	}

	public void update(Observable obj, Object arg) {
		if (((NachrichtenObjekt) arg).information.equals(Bezeichnungen.AGENT)) {
			agentY = ((NachrichtenObjekt) arg).y;
			agentX = ((NachrichtenObjekt) arg).x;
			verarbeiteWahrnehmung(agentY, agentX);
		}
		if (((NachrichtenObjekt) arg).information.equals(Bezeichnungen.NEUES_SPIEL)) {
			agentY = ((NachrichtenObjekt) arg).y;
			agentX = ((NachrichtenObjekt) arg).x;
			neuesSpiel();
			verarbeiteWahrnehmung(agentY, agentX);
		}
		if (((NachrichtenObjekt) arg).information.equals(Bezeichnungen.BEWEGE_AGENT)) {
			bewegeAgenten();
		}
		if (((NachrichtenObjekt) arg).information.equals(Bezeichnungen.WUMPUS_WURDE_GETOETET)) {
			entferneWumpus(((NachrichtenObjekt) arg).y, ((NachrichtenObjekt) arg).x);

		}
		if (((NachrichtenObjekt) arg).information.equals(Bezeichnungen.SPEICHERN)) {
			int richtungen = ((NachrichtenObjekt) arg).wahrnehmung[0];
			switch (richtungen) {
			case 1: {
				sendeSpeichern(Bezeichnungen.UP);
				break;
			}
			case 2: {
				sendeSpeichern(Bezeichnungen.DOWN);
				break;
			}
			case 3: {
				sendeSpeichern(Bezeichnungen.LINKS);
				break;
			}
			case 4: {
				sendeSpeichern(Bezeichnungen.RECHTS);
				break;
			}
			case 5: {
				sendeSpeichern(Bezeichnungen.BENUTZE_PFEIL + " " + Bezeichnungen.UP);
				break;
			}
			case 6: {
				sendeSpeichern(Bezeichnungen.BENUTZE_PFEIL + " " + Bezeichnungen.DOWN);
				break;
			}
			case 7: {
				sendeSpeichern(Bezeichnungen.BENUTZE_PFEIL + " " + Bezeichnungen.LINKS);
				break;
			}
			case 8: {
				sendeSpeichern(Bezeichnungen.BENUTZE_PFEIL + " " + Bezeichnungen.RECHTS);
				break;
			}
			}
		}
		if (((NachrichtenObjekt) arg).information.equals(Bezeichnungen.EIN_SCHRITT_ZURUECK)) {
			EinSchrittZurueck temp = wump.letzteBewegung();
			if (temp != null) {
				arraymitWissenBasis = temp.holeFeld();
				String richtung = temp.holeBewegung();
				if (richtung.equals(Bezeichnungen.RECHTS)) {
					agentX = agentX - 1;
				} else if (richtung.equals(Bezeichnungen.LINKS)) {
					agentX = agentX + 1;
				} else if (richtung.equals(Bezeichnungen.UP)) {
					agentY = agentY + 1;
				} else if (richtung.equals(Bezeichnungen.DOWN)) {
					agentY = agentY - 1;
				} else if (richtung.equals(Bezeichnungen.BENUTZE_PFEIL + " " + Bezeichnungen.RECHTS)) {
					agentX = agentX - 1;
					pfeil = true;
				} else if (richtung.equals(Bezeichnungen.BENUTZE_PFEIL + " " + Bezeichnungen.LINKS)) {
					agentX = agentX + 1;
					pfeil = true;
				} else if (richtung.equals(Bezeichnungen.BENUTZE_PFEIL + " " + Bezeichnungen.UP)) {
					agentY = agentY + 1;
					pfeil = true;
				} else if (richtung.equals(Bezeichnungen.BENUTZE_PFEIL + " " + Bezeichnungen.DOWN)) {
					agentY = agentY - 1;
					pfeil = true;
				}
			} else {
				Toolkit.getDefaultToolkit().beep();

			}
		}
	}

	/* diese Methode bestimmt die Position des Agentes */
	// public void positionDesAgentes(int y, int x, int[] wahrnehmung) {
	// System.out.println("anzahl " + anzahl + " array.length" + arraymitWissenBasis.length);
	// System.out.println("Meine Position x " + x + " Position y: " + y);
	// }
	public void ausgabe() {
		System.out.println("----------------------------------------------------------------------------------------------------------");
		for (int j = 0; j < anzahl; j++) {
			for (int i = 0; i < anzahl; i++) {
				if (arraymitWissenBasis[j][i] != null) {
					// TODO: Was wird hier bei der Ausgabe geändert
					// if (arraymitWissenBasis[j][i].istWahrnehmung()) {
					System.out.print("2:" + arraymitWissenBasis[j][i].gold + " 4:" + arraymitWissenBasis[j][i].wumpus + " 6:" + arraymitWissenBasis[j][i].fallgrube);
					// System.out.print("3:" + arraymitWissenBasis[j][i].glitter + " 5:" + arraymitWissenBasis[j][i].geruch + " 7:" + arraymitWissenBasis[j][i].brise);
					// } else {
					// System.out.print("no Wahr");
					// }
				} else {
					System.out.print("3:0 5:0 7:0");
				}
				System.out.print(" | ");
			}
			System.out.println("");
			System.out.println("----------------------------------------------------------------------------------------------------------");
		}
		System.out.println("----------------------------------------------------------------------------------------------------------");
		for (int j = 0; j < anzahl; j++) {
			for (int i = 0; i < anzahl; i++) {
				if (arraymitWissenBasis[j][i] != null) {
					// TODO: Was wird hier bei der Ausgabe geändert
					// if (arraymitWissenBasis[j][i].istWahrnehmung()) {
					// System.out.print("2:" + arraymitWissenBasis[j][i].gold + " 4:" + arraymitWissenBasis[j][i].wumpus+ " 6:" + arraymitWissenBasis[j][i].fallgrube);
					System.out.print("3:" + arraymitWissenBasis[j][i].glitter + " 5:" + arraymitWissenBasis[j][i].geruch + " 7:" + arraymitWissenBasis[j][i].brise);
					// } else {
					// System.out.print("no Wahr");
					// }
				} else {
					System.out.print("3:0 5:0 7:0");
				}
				System.out.print(" | ");
			}
			System.out.println("");
			System.out.println("----------------------------------------------------------------------------------------------------------");
		}
	}

	/**
	 * Diese Methode prueft, ob man nicht ausserhalb des vor gegebenes Bereiches nicht rausgeht. d.h. vermeidet ArrayIndexOutOfBoundsException gibt ein Bumb falls dieses doch vorkommt aus.
	 * 
	 * @param y
	 * @param x
	 */
	private boolean ichBinNichtAuserhalb(int y, int x) {
		// System.err.println("Y: " + y + " X: " + x);
		if (y >= 0 && y < anzahl && x >= 0 && x < anzahl) {
			// System.err.println("ich bin nicht ausserhalb");
			return true;
		} else {
			// TODO: weiterleitung der Wahrnehmung an GUI mit abzug von Punkten wegen Bewegung
			// System.out.println("BUMP");
			return false;
		}
	}

	/**
	 * Soll prüfen ob eine Wand in angegebener Richtung existiert
	 * 
	 * @param y
	 *            Y-Koordinate des Feldes
	 * @param x
	 *            X-Koordinate des Feldes
	 * @param richtung
	 * @return
	 */
	private boolean istDortKeineWand(int y, int x, int richtung) {
		int ySave = y, xSave = x;
		switch (richtung) {
		case 1: {
			y = y - 1;
			break;
		}
		case 2: {
			y = y + 1;
			break;
		}
		case 3: {
			x = x - 1;
			break;
		}
		case 4: {
			x = x + 1;
			break;
		}
		}
		if (y >= 0 && y < anzahl && x >= 0 && x < anzahl) {
			// System.err.println("ich bin nicht ausserhalb");
			return true;
		} else {
			// TODO: Wenn oben gegen eine Wand gelaufen wird, muss in der gesamten Wissensbasis mit dem selben y eine Wand eingetragen werden.
			switch (richtung) {
			case 1: {
				if (!arraymitWissenBasis[ySave][xSave].wandO) {
					System.out.println("BUMP");
				}
				for (int i = 0; i < anzahl; i++)
					arraymitWissenBasis[ySave][i].wandO = true;
				break;
			}
			case 2: {
				if (!arraymitWissenBasis[ySave][xSave].wandU) {
					System.out.println("BUMP");
				}
				for (int i = 0; i < anzahl; i++)
					arraymitWissenBasis[ySave][i].wandU = true;
				break;
			}
			case 3: {
				if (!arraymitWissenBasis[ySave][xSave].wandL) {
					System.out.println("BUMP");
				}
				for (int i = 0; i < anzahl; i++)
					arraymitWissenBasis[i][xSave].wandL = true;
				break;
			}
			case 4: {
				if (!arraymitWissenBasis[ySave][xSave].wandR) {
					System.out.println("BUMP");
				}
				for (int i = 0; i < anzahl; i++)
					arraymitWissenBasis[i][xSave].wandR = true;
				break;
			}
			}
			return false;
		}
	}

	/**
	 * Diese Methode bestimmt die Richtung, in welche der Agent gehen soll.
	 * 
	 * @param agentY
	 *            Y-Koordinate des Agentes
	 * @param agentX
	 *            X-Koordinate des Agentes
	 * @return wird die Richtung übergeben.
	 */
	private int bestimmeDieRichtung(int agentY, int agentX) {
		if (ersteSchritt) {
			/* Erstes Fall: auf neu getretenen Feld ist ein Glitter und nichts(keine weitere Wahrnehmung */
			// if (ichBinNichtAuserhalb(agentY - 1, agentX)) {
			if (arraymitWissenBasis[agentY][agentX].glitter && !arraymitWissenBasis[agentY][agentX].geruch && !arraymitWissenBasis[agentY][agentX].brise) {
				if (ichBinNichtAuserhalb(agentY - 1, agentX) && (istGoldDa(agentY - 1, agentX) || !arraymitWissenBasis[agentY - 1][agentX].besucht)) {
					return 1;
				} else if (ichBinNichtAuserhalb(agentY + 1, agentX) && (istGoldDa(agentY + 1, agentX) || !arraymitWissenBasis[agentY + 1][agentX].besucht)) {
					return 2;
				} else if (ichBinNichtAuserhalb(agentY, agentX - 1) && (istGoldDa(agentY, agentX - 1) || !arraymitWissenBasis[agentY][agentX - 1].besucht)) {
					return 3;
				} else if (ichBinNichtAuserhalb(agentY, agentX + 1) && (istGoldDa(agentY, agentX + 1) || !arraymitWissenBasis[agentY][agentX + 1].besucht)) {
					return 4;
				}
			}
			/* Zweites Fall: auf neu getretenen Feld ist ein Glitter und die weitere Wahrnehmungen */
			else if (arraymitWissenBasis[agentY][agentX].glitter && (arraymitWissenBasis[agentY][agentX].geruch || arraymitWissenBasis[agentY][agentX].brise)) {
				if (ichBinNichtAuserhalb(agentY - 1, agentX) && (istGoldDa(agentY - 1, agentX) || pruefeFeldNachSicherheit(agentY - 1, agentX) && !arraymitWissenBasis[agentY - 1][agentX].besucht)) {
					return 1;
				} else if (ichBinNichtAuserhalb(agentY + 1, agentX) && (istGoldDa(agentY + 1, agentX) || pruefeFeldNachSicherheit(agentY + 1, agentX) && !arraymitWissenBasis[agentY + 1][agentX].besucht)) {
					return 2;
				} else if (ichBinNichtAuserhalb(agentY, agentX - 1) && (istGoldDa(agentY, agentX - 1) || pruefeFeldNachSicherheit(agentY, agentX - 1) && !arraymitWissenBasis[agentY][agentX - 1].besucht)) {
					return 3;
				} else if (ichBinNichtAuserhalb(agentY, agentX + 1) && (istGoldDa(agentY, agentX + 1) || pruefeFeldNachSicherheit(agentY, agentX + 1) && !arraymitWissenBasis[agentY][agentX + 1].besucht)) {
					return 4;
				}
			}

			// }
			/* Dritte Fall: Es ist keine Wahrnehmung da, der Agent soll aber auch nicht zurück gehen. */
			else if (!arraymitWissenBasis[agentY][agentX].geruch && !arraymitWissenBasis[agentY][agentX].glitter && !arraymitWissenBasis[agentY][agentX].brise) {
				int gegenRichtung = eineGegenZahl(richtung);
				// TODO: Wenn gegen Wand wird, soll diese Richtung und Herkunfstrichtung rausgenommen werden.
				do {
					richtung = (int) ((Math.random()) * 4 + 1);
					// if (istDortWand(agentY, agentX, richtung)) {
					// richtung = gegenRichtung;
					// }
				} while (richtung == gegenRichtung);

				return richtung;
			}

			/* Vierte Fall: auf neu getretenen Feld ist kein Glitter und die weitere Wahrnehmungen */
			else {
				if (ichBinNichtAuserhalb(agentY - 1, agentX) && (pruefeFeldNachSicherheit(agentY - 1, agentX) && !arraymitWissenBasis[agentY - 1][agentX].besucht)) {
					return richtung = 1;
				} else if (ichBinNichtAuserhalb(agentY + 1, agentX) && (pruefeFeldNachSicherheit(agentY + 1, agentX) && !arraymitWissenBasis[agentY + 1][agentX].besucht)) {
					return richtung = 2;
				} else if (ichBinNichtAuserhalb(agentY, agentX - 1) && (pruefeFeldNachSicherheit(agentY, agentX - 1) && !arraymitWissenBasis[agentY][agentX - 1].besucht)) {
					return richtung = 3;
				} else if (ichBinNichtAuserhalb(agentY, agentX + 1) && (pruefeFeldNachSicherheit(agentY, agentX + 1) && !arraymitWissenBasis[agentY][agentX + 1].besucht)) {
					return richtung = 4;
				} else {
					int temp = eineGegenZahl(richtung);
					richtung = temp;
					return richtung;
				}
			}
		}
		return richtung = (int) ((Math.random()) * 4 + 1);
	}

	/**
	 * Diese Methode gibt eine gegen Richtung(Zahl). d. h. UP(1) es wird DOWN(2) gelifert.
	 * 
	 * @param zahl
	 *            dieses Zahl representiert eine Richtung
	 * @return wird eine gegen Zahl(Richtung) übergeben.
	 */
	private int eineGegenZahl(int zahl) {
		if (zahl == 1)
			return 2;
		else if (zahl == 2)
			return 1;
		else if (zahl == 3)
			return 4;
		else
			return 3;
	}

	/**
	 * Diese Methode bewegt den Agent in Wumpuswelt.
	 */
	private void bewegeAgenten() {
		/*
		 * if(!bewegungsListe.isEmpty()){ wump.bewegeAgent(bewegungsListe.poll(); if(AgentSitztaufGold) Beende das Spiel mit erfolgsmeldung }
		 */
		// Derzeit wird der Agent nicht bewegt sondern nur eines der anliegenden Felder wird geprüft
		// Deshalb do while mit Abbruchbedingung
		// TODO:Bewege den Agenten richtig
		// setzeKeinGefahrNachStart(agentY, agentX);
		boolean weiterMachen = true;
		sucheDieRoute(agentY, agentX);
		if (!bewegungsListe.isEmpty()) {
			wump.bewegeAgent(bewegungsListe.poll());
			verarbeiteWahrnehmung(agentY, agentX);
			
			// TODO: wenn agent schon einmal sich bewegt hat, muss die setzeKeinGefahrWennKeineWahrnehmung(y,x) aufgeruffen werden.
		} else if (bewegungsListe.isEmpty()) {
			System.out.println("Bewegungsliste war leer, random wird verwendet.");
			richtung = bestimmeDieRichtung(agentY, agentX);
			switch (richtung) {
			case 1: {
				System.out.println("Agent(" + agentY + "|" + agentX + ") versucht nach oben zu gehen");
				if (ichBinNichtAuserhalb(agentY - 1, agentX)) {
					if (!(arraymitWissenBasis[agentY - 1][agentX].gefahr)) {
						agentY = agentY - 1;
						wump.bewegeAgent(Bezeichnungen.UP);
						verarbeiteWahrnehmung(agentY, agentX);
						weiterMachen = sitzeNichtAufGold(agentY, agentX);
						if (!arraymitWissenBasis[agentY][agentX].gold && !arraymitWissenBasis[agentY][agentX].wumpus && !arraymitWissenBasis[agentY][agentX].geruch && !arraymitWissenBasis[agentY][agentX].fallgrube
								&& !arraymitWissenBasis[agentY][agentX].brise) {
							setzeKeinGefahrWennKeineWahrnehmung(agentY, agentX);
						}
					} else if (!pruefeFeldNachGefahr(agentY - 1, agentX)) {
						if (pruefeFeldNachSicherheit(agentY - 1, agentX)) {
							agentY = agentY - 1;
							wump.bewegeAgent(Bezeichnungen.UP);
							verarbeiteWahrnehmung(agentY, agentX);
							weiterMachen = sitzeNichtAufGold(agentY, agentX);
							if (!arraymitWissenBasis[agentY][agentX].gold && !arraymitWissenBasis[agentY][agentX].wumpus && !arraymitWissenBasis[agentY][agentX].geruch && !arraymitWissenBasis[agentY][agentX].fallgrube
									&& !arraymitWissenBasis[agentY][agentX].brise) {
								setzeKeinGefahrWennKeineWahrnehmung(agentY, agentX);
							}
						}
					}
				}
				break;
			}
			case 2: {
				System.out.println("Agent(" + agentY + "|" + agentX + ") versucht nach unten zu gehen");
				if (ichBinNichtAuserhalb(agentY + 1, agentX)) {
					if (!(arraymitWissenBasis[agentY + 1][agentX].gefahr)) {
						agentY = agentY + 1;
						wump.bewegeAgent(Bezeichnungen.DOWN);
						verarbeiteWahrnehmung(agentY, agentX);
						weiterMachen = sitzeNichtAufGold(agentY, agentX);
						if (!arraymitWissenBasis[agentY][agentX].gold && !arraymitWissenBasis[agentY][agentX].wumpus && !arraymitWissenBasis[agentY][agentX].geruch && !arraymitWissenBasis[agentY][agentX].fallgrube
								&& !arraymitWissenBasis[agentY][agentX].brise) {
							setzeKeinGefahrWennKeineWahrnehmung(agentY, agentX);
						}
					} else if (!pruefeFeldNachGefahr(agentY + 1, agentX)) {
						if (pruefeFeldNachSicherheit(agentY + 1, agentX)) {
							agentY = agentY + 1;
							wump.bewegeAgent(Bezeichnungen.DOWN);
							verarbeiteWahrnehmung(agentY, agentX);
							weiterMachen = sitzeNichtAufGold(agentY, agentX);
							if (!arraymitWissenBasis[agentY][agentX].gold && !arraymitWissenBasis[agentY][agentX].wumpus && !arraymitWissenBasis[agentY][agentX].geruch && !arraymitWissenBasis[agentY][agentX].fallgrube
									&& !arraymitWissenBasis[agentY][agentX].brise) {
								setzeKeinGefahrWennKeineWahrnehmung(agentY, agentX);
							}
						}
					}
				}
				break;
			}
			case 3: {
				System.out.println("Agent(" + agentY + "|" + agentX + ") versucht nach links zu gehen");
				if (ichBinNichtAuserhalb(agentY, agentX - 1)) {
					if (!(arraymitWissenBasis[agentY][agentX - 1].gefahr)) {
						agentX = agentX - 1;
						wump.bewegeAgent(Bezeichnungen.LINKS);
						verarbeiteWahrnehmung(agentY, agentX);
						weiterMachen = sitzeNichtAufGold(agentY, agentX);
						if (!arraymitWissenBasis[agentY][agentX].gold && !arraymitWissenBasis[agentY][agentX].wumpus && !arraymitWissenBasis[agentY][agentX].geruch && !arraymitWissenBasis[agentY][agentX].fallgrube
								&& !arraymitWissenBasis[agentY][agentX].brise) {
							setzeKeinGefahrWennKeineWahrnehmung(agentY, agentX);
						}
					} else if (!pruefeFeldNachGefahr(agentY, agentX - 1)) {
						if (pruefeFeldNachSicherheit(agentY, agentX - 1)) {
							agentX = agentX - 1;
							wump.bewegeAgent(Bezeichnungen.LINKS);
							verarbeiteWahrnehmung(agentY, agentX);
							weiterMachen = sitzeNichtAufGold(agentY, agentX);
							if (!arraymitWissenBasis[agentY][agentX].gold && !arraymitWissenBasis[agentY][agentX].wumpus && !arraymitWissenBasis[agentY][agentX].geruch && !arraymitWissenBasis[agentY][agentX].fallgrube
									&& !arraymitWissenBasis[agentY][agentX].brise) {
								setzeKeinGefahrWennKeineWahrnehmung(agentY, agentX);
							}
						}
					}
				}
				break;
			}
			case 4: {
				System.out.println("Agent(" + agentY + "|" + agentX + ") versucht nach rechts zu gehen");
				if (ichBinNichtAuserhalb(agentY, agentX + 1)) {
					if (!(arraymitWissenBasis[agentY][agentX + 1].gefahr)) {
						agentX = agentX + 1;
						wump.bewegeAgent(Bezeichnungen.RECHTS);
						verarbeiteWahrnehmung(agentY, agentX);
						weiterMachen = sitzeNichtAufGold(agentY, agentX);
						if (!arraymitWissenBasis[agentY][agentX].gold && !arraymitWissenBasis[agentY][agentX].wumpus && !arraymitWissenBasis[agentY][agentX].geruch && !arraymitWissenBasis[agentY][agentX].fallgrube
								&& !arraymitWissenBasis[agentY][agentX].brise) {
							setzeKeinGefahrWennKeineWahrnehmung(agentY, agentX);
						}
					} else if (!pruefeFeldNachGefahr(agentY, agentX + 1)) {
						if (pruefeFeldNachSicherheit(agentY, agentX + 1)) {
							agentX = agentX + 1;
							wump.bewegeAgent(Bezeichnungen.RECHTS);
							verarbeiteWahrnehmung(agentY, agentX);
							weiterMachen = sitzeNichtAufGold(agentY, agentX);
							if (!arraymitWissenBasis[agentY][agentX].gold && !arraymitWissenBasis[agentY][agentX].wumpus && !arraymitWissenBasis[agentY][agentX].geruch && !arraymitWissenBasis[agentY][agentX].fallgrube
									&& !arraymitWissenBasis[agentY][agentX].brise) {
								setzeKeinGefahrWennKeineWahrnehmung(agentY, agentX);
							}
						}
					}
				}
				break;
			}
			}
		}
		ersteSchritt = true;
		if (weiterMachen) {
			wump.agentIstFertig();
		}

	}

	/**
	 * Diese Methode setzt die sichere Felder um den Agent direkt nach dem Start.
	 * 
	 * @param y
	 *            y Position von Agent
	 * @param x
	 *            x Position von Agent
	 */
	private void setzeKeinGefahrNachStart(int y, int x) {
		if (ichBinNichtAuserhalb(y - 1, x)) {
			arraymitWissenBasis[y - 1][x].gefahr = false;
		}
		if (ichBinNichtAuserhalb(y, x - 1)) {
			arraymitWissenBasis[y][x - 1].gefahr = false;
		}
		if (ichBinNichtAuserhalb(y + 1, x)) {
			arraymitWissenBasis[y + 1][x].gefahr = false;
		}
		if (ichBinNichtAuserhalb(y, x + 1)) {
			arraymitWissenBasis[y][x + 1].gefahr = false;
		}
	}

	/**
	 * Diese Methode setzt die sichere Felder um den Agent, wenn es in diesem Feld keine Warnehmung existiert.
	 * 
	 * @param y
	 *            y Position von Agent
	 * @param x
	 *            x Position von Agent
	 */
	private void setzeKeinGefahrWennKeineWahrnehmung(int y, int x) {
		if (ichBinNichtAuserhalb(y - 1, x)) {
			Feld p = arraymitWissenBasis[y][x];
			/* Wenn das Feld schon Besucht ist, dann es es ist nicht noetig zu markieren. */
			if (!p.besucht) {
				arraymitWissenBasis[y - 1][x].gefahr = false;
			}
		}
		if (ichBinNichtAuserhalb(y, x - 1)) {
			Feld p = arraymitWissenBasis[y][x];
			if (!p.besucht) {
				arraymitWissenBasis[y][x - 1].gefahr = false;
			}
		}
		if (ichBinNichtAuserhalb(y + 1, x)) {
			Feld p = arraymitWissenBasis[y][x];
			if (!p.besucht) {
				arraymitWissenBasis[y + 1][x].gefahr = false;
			}
		}
		if (ichBinNichtAuserhalb(y, x + 1)) {
			Feld p = arraymitWissenBasis[y][x];
			if (!p.besucht) {
				arraymitWissenBasis[y][x + 1].gefahr = false;
			}
		}
	}

	/**
	 * Wenn ein Feld noch nicht als besucht markiert wurde, es also zum ersten mal besucht wird, wird dieses Feld in der Wissensbasis als besucht abgelegt und als nicht versteckt. Dannach werden die evtl. vorkommenden Wahrnehmungen des Feldes
	 * einzelnd verarbeitet und in der Wissensbasis gespeichert.
	 * 
	 * @param y
	 *            y Position von Agent
	 * @param x
	 *            x Position von Agent
	 */
	private void verarbeiteWahrnehmung(int y, int x) {
		if (!arraymitWissenBasis[y][x].besucht) {
			arraymitWissenBasis[y][x].besucht = true;
			arraymitWissenBasis[y][x].versteckt = false;
			int[] wahrnehmung = new FeldPositionieren().separateWahrnehmungen(wump.weltArray[y][x]);
			for (int i = 0; i < wahrnehmung.length; i++) {
				if (wahrnehmung[i] == 2) {
					arraymitWissenBasis[y][x].gold = true;
				} else if (wahrnehmung[i] == 3) {
					arraymitWissenBasis[y][x].glitter = true;
				} else if (wahrnehmung[i] == 4) {
					arraymitWissenBasis[y][x].wumpus = true;
				} else if (wahrnehmung[i] == 5) {
					arraymitWissenBasis[y][x].geruch = true;
				} else if (wahrnehmung[i] == 6) {
					arraymitWissenBasis[y][x].fallgrube = true;
				} else if (wahrnehmung[i] == 7) {
					arraymitWissenBasis[y][x].brise = true;
				}
			}
		}
		if (!sitzeNichtAufWumpus(agentY, agentX)) {
			wump.sendeSpielZuEndeWumpus();
		}
		if (!sitzeNichtAufPit(agentY, agentX)) {
			wump.sendeSpielZuEndePit();
		}
		if (!sitzeNichtAufGold(agentY, agentX)) {
			wump.sendeSpielZuEndeGold();
		}
	}

	/**
	 * Diese Methode prüft ob Agen in einen Feld mit Gold sich befindet.
	 * 
	 * @param y
	 *            Y-Koordinate des Feldes(Agentes)
	 * @param x
	 *            X-Koordinate des Feldes(Agentes)
	 * @return true wenn es wahr.
	 */
	private boolean sitzeNichtAufGold(int y, int x) {
		if (arraymitWissenBasis[y][x].gold) {
			System.out.println("Gold ist da");
			return false;
		}
		return true;
	}
	
	/**
	 * Diese Methode prüft ob Agen in einen Feld mit Wumpus sich befindet.
	 * 
	 * @param y
	 *            Y-Koordinate des Feldes(Agentes)
	 * @param x
	 *            X-Koordinate des Feldes(Agentes)
	 * @return true wenn es wahr.
	 */
	private boolean sitzeNichtAufWumpus(int y, int x) {
		if (arraymitWissenBasis[y][x].wumpus) {
			System.out.println("Wumpus ist da");
			return false;
		}
		return true;
	}
	
	/**
	 * Diese Methode prüft ob Agen in einen Feld mit Pit sich befindet.
	 * 
	 * @param y
	 *            Y-Koordinate des Feldes(Agentes)
	 * @param x
	 *            X-Koordinate des Feldes(Agentes)
	 * @return true wenn es wahr.
	 */
	private boolean sitzeNichtAufPit(int y, int x) {
		if (arraymitWissenBasis[y][x].fallgrube) {
			System.out.println("Fallgrube ist da");
			return false;
		}
		return true;
	}
	
	/**
	 * Diese Methode prueft Feld nach Gefahr, wenn es nicht moeglich es auszuschlissen, dann gibt es true(gefahr) zurueck.
	 * @param y Y-Koordinate des Feldes
	 * @param x X-Koordinate des Feldes
	 * @return true(Gefahr)
	 */
	private boolean pruefeFeldNachGefahr(int y, int x) {
		if (istWumpusDa(y, x)) {
			System.err.println("DORT IST DAS WUMPUS. AGENT KANN PFEIL VERWENDEN.");

			return true;
		} else if (istFallgrubeDa(y, x)) {
			System.err.println("DORT IST EINE FALLGRUBE.");
			return true;
			// }else if (istGoldDa(y, x)) {
			// System.err.println("DORT IST DAS GOLD.");
			// return true;
		} else {
			System.err.println("DORT IST NICHTS NENNENSWERTES.");
			return false;
		}
	}
	
	/**
	 * Diese Methode prueft Feld nach Sicherheit, wenn es nicht moeglich es auszuschlissen, dann gibt es false(gefahr) zurueck.
	 * @param y Y-Koordinate des Feldes
	 * @param x X-Koordinate des Feldes
	 * @return true(wenn sicher ist)
	 */
	private boolean pruefeFeldNachSicherheit(int y, int x) {
		System.out.println("pruefeFeldNachSicherheit: (" + y + "|" + x + ")");
		/* für den Fall, das da kein Gefahr */
		if (!arraymitWissenBasis[y][x].gefahr)
			return true;
		if (!istWumpusNichtDa(y, x)) {
			System.err.println("DORT IST EVTL. WUMPUS.(" + y + "|" + x + ")");
			return false;
		}
		if (!istFallgrubeNichtDa(y, x)) {
			System.err.println("DORT IST EVTL. FALLGRUBE.(" + y + "|" + x + ")");
			return false;
		}
		{
			System.err.println("DORT IST NICHTS NENNENSWERTES.(" + y + "|" + x + ")");
			return true;
		}
	}

	/* Hilfsmethoden */

	/**
	 * Die Methode bestimm, wo das Wumpus ist. d.h. es wird in Wissenbassis nach Geruch gesucht, dadurch entstehen 4 Fälle, bei denen muss man noch weitere Fälle untescheiden.
	 * 
	 * @return die Position des Wumpus oder <b>null</b>
	 */
	private Position istWumpusDa() {
		Feld erstesFeldMitGeruch = null;
		Feld zweitesFeldMitGeruch = null;
		Feld drittesFeldMitGeruch = null;
		// Feld viertesFeldMitGeruch = null;
		int geheNichtNochmal = 0;
		for (int j = 0; j < anzahl; j++) {
			for (int i = 0; i < anzahl; i++) {
				if (arraymitWissenBasis[j][i].besucht) {
					if (arraymitWissenBasis[j][i].geruch) {
						// TODO: wenn erste bedinung stimm, dann stimmt die zweite stelle(if) usw.
						if (geheNichtNochmal == 0) {
							erstesFeldMitGeruch = arraymitWissenBasis[j][i];
							geheNichtNochmal = 1;
						} else if (geheNichtNochmal == 1) {
							zweitesFeldMitGeruch = arraymitWissenBasis[j][i];
							geheNichtNochmal = 2;
						} else if (geheNichtNochmal == 2) {
							drittesFeldMitGeruch = arraymitWissenBasis[j][i];
							geheNichtNochmal = 3;
						} else if (geheNichtNochmal == 3) {
							// viertesFeldMitGeruch = arraymitWissenBasis[j][i];
							geheNichtNochmal = 4;
						}
					}
				}
			}

		}

		if (geheNichtNochmal == 1) {
			// return null;
			Feld unteresFeld = null;
			Feld oberesFeld = null;
			Feld linkesFeld = null;
			Feld rechtesFeld = null;
			if (erstesFeldMitGeruch.y - 1 >= 0) {
				oberesFeld = arraymitWissenBasis[erstesFeldMitGeruch.y - 1][erstesFeldMitGeruch.x];
			}
			if (erstesFeldMitGeruch.y + 1 < anzahl) {
				unteresFeld = arraymitWissenBasis[erstesFeldMitGeruch.y + 1][erstesFeldMitGeruch.x];
			}
			if (erstesFeldMitGeruch.x - 1 >= 0) {
				linkesFeld = arraymitWissenBasis[erstesFeldMitGeruch.y][erstesFeldMitGeruch.x - 1];
			}
			if (erstesFeldMitGeruch.x + 1 < anzahl) {
				rechtesFeld = arraymitWissenBasis[erstesFeldMitGeruch.y][erstesFeldMitGeruch.x + 1];
			}

			if (linkesFeld != null && !linkesFeld.besucht && istWumpusNichtDa(linkesFeld.y - 1, linkesFeld.x) && istWumpusNichtDa(linkesFeld.y + 1, linkesFeld.x) && istWumpusNichtDa(linkesFeld.y, linkesFeld.x - 1)) {
				return new Position(linkesFeld.y, linkesFeld.x);
			} else if (rechtesFeld != null && rechtesFeld.besucht && istWumpusNichtDa(rechtesFeld.y - 1, rechtesFeld.x) && istWumpusNichtDa(rechtesFeld.y + 1, rechtesFeld.x) && istWumpusNichtDa(rechtesFeld.y, rechtesFeld.x + 1)) {
				return new Position(rechtesFeld.y, rechtesFeld.x);
			} else if (oberesFeld != null && oberesFeld.besucht && istWumpusNichtDa(oberesFeld.y - 1, oberesFeld.x) && istWumpusNichtDa(oberesFeld.y, oberesFeld.x - 1) && istWumpusNichtDa(oberesFeld.y, oberesFeld.x + 1)) {
				return new Position(oberesFeld.y, oberesFeld.x);
			} else if (unteresFeld != null && unteresFeld.besucht && istWumpusNichtDa(unteresFeld.y + 1, unteresFeld.x) && istWumpusNichtDa(unteresFeld.y, unteresFeld.x - 1) && istWumpusNichtDa(unteresFeld.y, unteresFeld.x + 1)) {
				return new Position(unteresFeld.y, unteresFeld.x);
			}
		} else if (geheNichtNochmal == 2) {
			if (erstesFeldMitGeruch.y + 2 == zweitesFeldMitGeruch.y && erstesFeldMitGeruch.x == zweitesFeldMitGeruch.x) {
//				wump.agetSagt(erstesFeldMitGeruch.y - 1, erstesFeldMitGeruch.x);
				return new Position(erstesFeldMitGeruch.y - 1, erstesFeldMitGeruch.x);
			} else if (erstesFeldMitGeruch.y == zweitesFeldMitGeruch.y && erstesFeldMitGeruch.x + 2 == zweitesFeldMitGeruch.x) {
//				wump.agetSagt(erstesFeldMitGeruch.y, erstesFeldMitGeruch.x + 1);
				return new Position(erstesFeldMitGeruch.y, erstesFeldMitGeruch.x + 1);
			} else if (erstesFeldMitGeruch.geruch && zweitesFeldMitGeruch.geruch && ichBinNichtAuserhalb(erstesFeldMitGeruch.y, erstesFeldMitGeruch.x + 1)) {
				if (arraymitWissenBasis[erstesFeldMitGeruch.y][erstesFeldMitGeruch.x + 1].besucht) {
//					wump.agetSagt(erstesFeldMitGeruch.y + 1, erstesFeldMitGeruch.x);
					return new Position(erstesFeldMitGeruch.y + 1, erstesFeldMitGeruch.x);
				}
			} else if (erstesFeldMitGeruch.geruch && zweitesFeldMitGeruch.geruch && ichBinNichtAuserhalb(erstesFeldMitGeruch.y + 1, erstesFeldMitGeruch.x)) {
				if (arraymitWissenBasis[erstesFeldMitGeruch.y + 1][erstesFeldMitGeruch.x].besucht) {
//					wump.agetSagt(erstesFeldMitGeruch.y, erstesFeldMitGeruch.x + 1);
					return new Position(erstesFeldMitGeruch.y, erstesFeldMitGeruch.x + 1);
				}
			} else if (erstesFeldMitGeruch.geruch && zweitesFeldMitGeruch.geruch && ichBinNichtAuserhalb(erstesFeldMitGeruch.y, erstesFeldMitGeruch.x - 1)) {
				if (arraymitWissenBasis[erstesFeldMitGeruch.y][erstesFeldMitGeruch.x - 1].besucht) {
//					wump.agetSagt(erstesFeldMitGeruch.y + 1, erstesFeldMitGeruch.x);
					return new Position(erstesFeldMitGeruch.y + 1, erstesFeldMitGeruch.x);
				}
			} else if (erstesFeldMitGeruch.geruch && zweitesFeldMitGeruch.geruch && ichBinNichtAuserhalb(erstesFeldMitGeruch.y + 1, erstesFeldMitGeruch.x)) {
				if (arraymitWissenBasis[erstesFeldMitGeruch.y + 1][erstesFeldMitGeruch.x].besucht) {
//					wump.agetSagt(erstesFeldMitGeruch.y, erstesFeldMitGeruch.x - 1);
					return new Position(erstesFeldMitGeruch.y, erstesFeldMitGeruch.x - 1);
				}
			}
		} else if (geheNichtNochmal == 3) {
			if (erstesFeldMitGeruch.y + 1 == drittesFeldMitGeruch.y && erstesFeldMitGeruch.x + 1 == drittesFeldMitGeruch.x) {
				if (erstesFeldMitGeruch.y == zweitesFeldMitGeruch.y && erstesFeldMitGeruch.x + 2 == zweitesFeldMitGeruch.x) {
					wump.agetSagt(erstesFeldMitGeruch.y, erstesFeldMitGeruch.x + 1);
					return new Position(erstesFeldMitGeruch.y, erstesFeldMitGeruch.x + 1);
				}
			} else if (erstesFeldMitGeruch.y + 1 == zweitesFeldMitGeruch.y && erstesFeldMitGeruch.x - 1 == zweitesFeldMitGeruch.x) {
				wump.agetSagt(erstesFeldMitGeruch.y, erstesFeldMitGeruch.x + 1);
				return new Position(erstesFeldMitGeruch.y, erstesFeldMitGeruch.x + 1);
			} else if (erstesFeldMitGeruch.y + 2 == drittesFeldMitGeruch.y && erstesFeldMitGeruch.x == drittesFeldMitGeruch.x) {
				if (erstesFeldMitGeruch.y + 1 == zweitesFeldMitGeruch.y && erstesFeldMitGeruch.x - 1 == zweitesFeldMitGeruch.x) {
					wump.agetSagt(erstesFeldMitGeruch.y + 1, erstesFeldMitGeruch.x);
					return new Position(erstesFeldMitGeruch.y + 1, erstesFeldMitGeruch.x);
				}
			} else if (erstesFeldMitGeruch.y + 1 == zweitesFeldMitGeruch.y && erstesFeldMitGeruch.x + 1 == zweitesFeldMitGeruch.x) {
				wump.agetSagt(erstesFeldMitGeruch.y + 1, erstesFeldMitGeruch.x);
				return new Position(erstesFeldMitGeruch.y + 1, erstesFeldMitGeruch.x);
			}
		} else if (geheNichtNochmal == 4) {
			wump.agetSagt(erstesFeldMitGeruch.y + 1, erstesFeldMitGeruch.x);
			return new Position(erstesFeldMitGeruch.y + 1, erstesFeldMitGeruch.x);
		}
		return null;
	}
	
	/**
	 * Diese Methode prueft Feld nach Wumpus, wenn es nicht moeglich es auszuschlissen, dann gibt es false(moegliche gefahr) zurueck.
	 * @param y Y-Koordinate des Feldes
	 * @param x X-Koordinate des Feldes
	 * @return true(wenn Wumpus da)
	 */
	private boolean istWumpusDa(int y, int x) {
		Feld p = arraymitWissenBasis[y][x];
		/* Wenn Feld Besucht dann keine Gefahr */
		if (p.besucht) {
			return false;
		}
		Feld unteresFeld = null;
		Feld oberesFeld = null;
		Feld linkesFeld = null;
		Feld rechtesFeld = null;
		if (y - 1 >= 0) {
			oberesFeld = arraymitWissenBasis[y - 1][x];
		}
		if (y + 1 < anzahl) {
			unteresFeld = arraymitWissenBasis[y + 1][x];
		}
		if (x - 1 >= 0) {
			linkesFeld = arraymitWissenBasis[y][x - 1];
		}
		if (x + 1 < anzahl) {
			rechtesFeld = arraymitWissenBasis[y][x + 1];
		}
		if (linkesFeld != null && linkesFeld.besucht && linkesFeld.geruch && istWumpusNichtDa(y - 1, x - 1) && istWumpusNichtDa(y + 1, x - 1) && istWumpusNichtDa(y, x - 2)) {
			System.out.println("WUMPUS IST IN DEM FELD VOR MIR.");
			return true;
		} else if (rechtesFeld != null && rechtesFeld.besucht && rechtesFeld.geruch && istWumpusNichtDa(y - 1, x + 1) && istWumpusNichtDa(y + 1, x + 1) && istWumpusNichtDa(y, x + 2)) {
			System.out.println("WUMPUS IST IN DEM FELD VOR MIR.");
			return true;
		} else if (oberesFeld != null && oberesFeld.besucht && oberesFeld.geruch && istWumpusNichtDa(y - 2, x) && istWumpusNichtDa(y - 1, x - 1) && istWumpusNichtDa(y - 1, x + 1)) {
			System.out.println("WUMPUS IST IN DEM FELD VOR MIR.");
			return true;
		} else if (unteresFeld != null && unteresFeld.besucht && unteresFeld.geruch && istWumpusNichtDa(y + 2, x) && istWumpusNichtDa(y + 1, x - 1) && istWumpusNichtDa(y + 1, x + 1)) {
			System.out.println("WUMPUS IST IN DEM FELD VOR MIR.");
			return true;
		}
		return false;
	}
	/**
	 * Diese Methode prueft Feld nach Fallgrube, wenn es nicht moeglich es auszuschlissen, dann gibt es false(moegliche gefahr) zurueck.
	 * @param y Y-Koordinate des Feldes
	 * @param x X-Koordinate des Feldes
	 * @return true(wenn Fallgrube da)
	 */
	private boolean istFallgrubeDa(int y, int x) {
		Feld p = arraymitWissenBasis[y][x];
		/* Wenn Feld Besucht dann keine Gefahr */
		if (p.besucht) {
			return false;
		}
		Feld unteresFeld = null;
		Feld oberesFeld = null;
		Feld linkesFeld = null;
		Feld rechtesFeld = null;
		if (y - 1 >= 0) {
			oberesFeld = arraymitWissenBasis[y - 1][x];
		}
		if (y + 1 < anzahl) {
			unteresFeld = arraymitWissenBasis[y + 1][x];
		}
		if (x - 1 >= 0) {
			linkesFeld = arraymitWissenBasis[y][x - 1];
		}
		if (x + 1 < anzahl) {
			rechtesFeld = arraymitWissenBasis[y][x + 1];
		}
		if (linkesFeld != null && linkesFeld.besucht && linkesFeld.brise && istFallgrubeNichtDa(y - 1, x - 1) && istFallgrubeNichtDa(y + 1, x - 1) && istFallgrubeNichtDa(y, x - 2)) {
			return true;
		} else if (rechtesFeld != null && rechtesFeld.besucht && rechtesFeld.brise && istFallgrubeNichtDa(y - 1, x + 1) && istFallgrubeNichtDa(y + 1, x + 1) && istFallgrubeNichtDa(y, x + 2)) {
			return true;
		} else if (oberesFeld != null && oberesFeld.besucht && oberesFeld.brise && istFallgrubeNichtDa(y - 2, x) && istFallgrubeNichtDa(y - 1, x - 1) && istFallgrubeNichtDa(y - 1, x + 1)) {
			return true;
		} else if (unteresFeld != null && unteresFeld.besucht && unteresFeld.brise && istFallgrubeNichtDa(y + 2, x) && istFallgrubeNichtDa(y + 1, x - 1) && istFallgrubeNichtDa(y + 1, x + 1)) {
			return true;
		}
		return false;
	}

	/**
	 * Die Methode bestimm, wo das Gold ist. d.h. es wird in Wissenbassis nach Glitter gesucht, dadurch entstehen 5 Falle, bei denen muss man noch weitere Fälle untescheiden.
	 * 
	 * @return die Position des Goldes oder <b>null</b>
	 */
	private Position istGoldDa() {
		Feld erstesFeldMitGlitter = null;
		Feld zweitesFeldMitGlitter = null;
		Feld drittesFeldMitGlitter = null;
		// Feld viertesFeldMitGlitter = null;
		int geheNichtNochmal = 0;
		for (int j = 0; j < anzahl; j++) {
			for (int i = 0; i < anzahl; i++) {
				if (arraymitWissenBasis[j][i].besucht) {
					if (arraymitWissenBasis[j][i].glitter) {
						if (geheNichtNochmal == 0) {
							erstesFeldMitGlitter = arraymitWissenBasis[j][i];
							geheNichtNochmal = 1;
						} else if (geheNichtNochmal == 1) {
							zweitesFeldMitGlitter = arraymitWissenBasis[j][i];
							geheNichtNochmal = 2;
						} else if (geheNichtNochmal == 2) {
							drittesFeldMitGlitter = arraymitWissenBasis[j][i];
							geheNichtNochmal = 3;
						} else if (geheNichtNochmal == 3) {
							// viertesFeldMitGlitter = arraymitWissenBasis[j][i];
							geheNichtNochmal = 4;
						}
					}
				}
			}

		}

		if (geheNichtNochmal == 1) {

			// return null;
			Feld unteresFeld = null;
			Feld oberesFeld = null;
			Feld linkesFeld = null;
			Feld rechtesFeld = null;
			if (erstesFeldMitGlitter.y - 1 >= 0) {
				oberesFeld = arraymitWissenBasis[erstesFeldMitGlitter.y - 1][erstesFeldMitGlitter.x];
			}
			if (erstesFeldMitGlitter.y + 1 < anzahl) {
				unteresFeld = arraymitWissenBasis[erstesFeldMitGlitter.y + 1][erstesFeldMitGlitter.x];
			}
			if (erstesFeldMitGlitter.x - 1 >= 0) {
				linkesFeld = arraymitWissenBasis[erstesFeldMitGlitter.y][erstesFeldMitGlitter.x - 1];
			}
			if (erstesFeldMitGlitter.x + 1 < anzahl) {
				rechtesFeld = arraymitWissenBasis[erstesFeldMitGlitter.y][erstesFeldMitGlitter.x + 1];
			}

			if (linkesFeld != null && !linkesFeld.besucht && !linkesFeld.gefahr && istGoldNichtDa(linkesFeld.y - 1, linkesFeld.x) && istGoldNichtDa(linkesFeld.y + 1, linkesFeld.x) && istGoldNichtDa(linkesFeld.y, linkesFeld.x - 1)) {
				return new Position(linkesFeld.y, linkesFeld.x);
			} else if (rechtesFeld != null && rechtesFeld.besucht && !rechtesFeld.gefahr && istGoldNichtDa(rechtesFeld.y - 1, rechtesFeld.x) && istGoldNichtDa(rechtesFeld.y + 1, rechtesFeld.x) && istGoldNichtDa(rechtesFeld.y, rechtesFeld.x + 1)) {
				return new Position(rechtesFeld.y, rechtesFeld.x);
			} else if (oberesFeld != null && oberesFeld.besucht && !oberesFeld.gefahr && istGoldNichtDa(oberesFeld.y - 1, oberesFeld.x) && istGoldNichtDa(oberesFeld.y, oberesFeld.x - 1) && istGoldNichtDa(oberesFeld.y, oberesFeld.x + 1)) {
				return new Position(oberesFeld.y, oberesFeld.x);
			} else if (unteresFeld != null && unteresFeld.besucht && !unteresFeld.gefahr && istGoldNichtDa(unteresFeld.y + 1, unteresFeld.x) && istGoldNichtDa(unteresFeld.y, unteresFeld.x - 1) && istGoldNichtDa(unteresFeld.y, unteresFeld.x + 1)) {
				return new Position(unteresFeld.y, unteresFeld.x);
			}
		} else if (geheNichtNochmal == 2) {
			if (erstesFeldMitGlitter.y + 2 == zweitesFeldMitGlitter.y && erstesFeldMitGlitter.x == zweitesFeldMitGlitter.x) {
				return new Position(erstesFeldMitGlitter.y - 1, erstesFeldMitGlitter.x);
			} else if (erstesFeldMitGlitter.y == zweitesFeldMitGlitter.y && erstesFeldMitGlitter.x + 2 == zweitesFeldMitGlitter.x) {
				return new Position(erstesFeldMitGlitter.y, erstesFeldMitGlitter.x + 1);
			} else if (erstesFeldMitGlitter.glitter && zweitesFeldMitGlitter.glitter && ichBinNichtAuserhalb(erstesFeldMitGlitter.y, erstesFeldMitGlitter.x + 1)) {
				if (arraymitWissenBasis[erstesFeldMitGlitter.y][erstesFeldMitGlitter.x + 1].besucht) {
					return new Position(erstesFeldMitGlitter.y + 1, erstesFeldMitGlitter.x);
				}
			} else if (erstesFeldMitGlitter.glitter && zweitesFeldMitGlitter.glitter && ichBinNichtAuserhalb(erstesFeldMitGlitter.y + 1, erstesFeldMitGlitter.x)) {
				if (arraymitWissenBasis[erstesFeldMitGlitter.y + 1][erstesFeldMitGlitter.x].besucht) {
					return new Position(erstesFeldMitGlitter.y, erstesFeldMitGlitter.x + 1);
				}
			} else if (erstesFeldMitGlitter.glitter && zweitesFeldMitGlitter.glitter && ichBinNichtAuserhalb(erstesFeldMitGlitter.y, erstesFeldMitGlitter.x - 1)) {
				if (arraymitWissenBasis[erstesFeldMitGlitter.y][erstesFeldMitGlitter.x - 1].besucht) {
					return new Position(erstesFeldMitGlitter.y + 1, erstesFeldMitGlitter.x);
				}
			} else if (erstesFeldMitGlitter.glitter && zweitesFeldMitGlitter.glitter && ichBinNichtAuserhalb(erstesFeldMitGlitter.y + 1, erstesFeldMitGlitter.x)) {
				if (arraymitWissenBasis[erstesFeldMitGlitter.y + 1][erstesFeldMitGlitter.x].besucht) {
					return new Position(erstesFeldMitGlitter.y, erstesFeldMitGlitter.x - 1);
				}
			}
		} else if (geheNichtNochmal == 3) {
			if (erstesFeldMitGlitter.y + 1 == drittesFeldMitGlitter.y && erstesFeldMitGlitter.x + 1 == drittesFeldMitGlitter.x) {
				if (erstesFeldMitGlitter.y == zweitesFeldMitGlitter.y && erstesFeldMitGlitter.x + 2 == zweitesFeldMitGlitter.x) {
					return new Position(erstesFeldMitGlitter.y, erstesFeldMitGlitter.x + 1);
				}
			} else if (erstesFeldMitGlitter.y + 1 == zweitesFeldMitGlitter.y && erstesFeldMitGlitter.x - 1 == zweitesFeldMitGlitter.x) {
				return new Position(erstesFeldMitGlitter.y, erstesFeldMitGlitter.x + 1);
			} else if (erstesFeldMitGlitter.y + 2 == drittesFeldMitGlitter.y && erstesFeldMitGlitter.x == drittesFeldMitGlitter.x) {
				if (erstesFeldMitGlitter.y + 1 == zweitesFeldMitGlitter.y && erstesFeldMitGlitter.x - 1 == zweitesFeldMitGlitter.x) {
					return new Position(erstesFeldMitGlitter.y + 1, erstesFeldMitGlitter.x);
				}
			} else if (erstesFeldMitGlitter.y + 1 == zweitesFeldMitGlitter.y && erstesFeldMitGlitter.x + 1 == zweitesFeldMitGlitter.x) {
				return new Position(erstesFeldMitGlitter.y + 1, erstesFeldMitGlitter.x);
			}
		} else if (geheNichtNochmal == 4) {
			return new Position(erstesFeldMitGlitter.y + 1, erstesFeldMitGlitter.x);
		}
		return null;
	}

	/**
	 * Diese Methode prueft Feld nach Gold, wenn es nicht moeglich es auszuschlissen, dann gibt es false zurueck.
	 * @param y Y-Koordinate des Feldes
	 * @param x X-Koordinate des Feldes
	 * @return true(wenn Gold da)
	 */
	private boolean istGoldDa(int y, int x) {
		Feld p = arraymitWissenBasis[y][x];
		/* Wenn Feld Besucht dann keine Gold */
		if (p.besucht) {
			return false;
		}
		Feld unteresFeld = null;
		Feld oberesFeld = null;
		Feld linkesFeld = null;
		Feld rechtesFeld = null;
		if (y - 1 >= 0) {
			oberesFeld = arraymitWissenBasis[y - 1][x];
		}
		if (y + 1 < anzahl) {
			unteresFeld = arraymitWissenBasis[y + 1][x];
		}
		if (x - 1 >= 0) {
			linkesFeld = arraymitWissenBasis[y][x - 1];
		}
		if (x + 1 < anzahl) {
			rechtesFeld = arraymitWissenBasis[y][x + 1];
		}
		if (linkesFeld != null && linkesFeld.besucht && linkesFeld.glitter /* && !linkesFeld.gefahr */&& istGoldNichtDa(y - 1, x - 1) && istGoldNichtDa(y + 1, x - 1) && istGoldNichtDa(y, x - 2)) {
			return true;
		} else if (rechtesFeld != null && rechtesFeld.besucht && rechtesFeld.glitter /* && !rechtesFeld.gefahr */&& istGoldNichtDa(y - 1, x + 1) && istGoldNichtDa(y + 1, x + 1) && istGoldNichtDa(y, x + 2)) {
			return true;
		} else if (oberesFeld != null && oberesFeld.besucht && oberesFeld.glitter /* && !oberesFeld.gefahr */&& istGoldNichtDa(y - 2, x) && istGoldNichtDa(y - 1, x - 1) && istGoldNichtDa(y - 1, x + 1)) {
			return true;
		} else if (unteresFeld != null && unteresFeld.besucht && unteresFeld.glitter /* && !unteresFeld.gefahr */&& istGoldNichtDa(y + 2, x) && istGoldNichtDa(y + 1, x - 1) && istGoldNichtDa(y + 1, x + 1)) {
			return true;
		}
		return false;
	}

	/**
	 * Diese Methode prueft nach keinem Wumpus, und gibt true, wenn das Feld schon besucht war oder kann nicht 100% festgestellt werden, sonst false.
	 * 
	 * @param y
	 *            Y-Koordinate zum pruefen
	 * @param x
	 *            X-Koordinate zum pruefen
	 * @return false wenn keine Fallgrube
	 */
	private boolean istWumpusNichtDa(int y, int x) {
		if (ichBinNichtAuserhalb(y, x)) {
			Feld p = arraymitWissenBasis[y][x];
			/* Wenn Feld Besucht dann keine Gefahr */
			if (p.besucht) {
				return true;
			}
			Feld unteresFeld = null;
			Feld oberesFeld = null;
			Feld linkesFeld = null;
			Feld rechtesFeld = null;
			if (y - 1 >= 0) {
				oberesFeld = arraymitWissenBasis[y - 1][x];
			}
			if (y + 1 < anzahl) {
				unteresFeld = arraymitWissenBasis[y + 1][x];
			}
			if (x - 1 >= 0) {
				linkesFeld = arraymitWissenBasis[y][x - 1];
			}
			if (x + 1 < anzahl) {
				rechtesFeld = arraymitWissenBasis[y][x + 1];
			}
			if (linkesFeld != null && linkesFeld.besucht && !linkesFeld.geruch || rechtesFeld != null && rechtesFeld.besucht && !rechtesFeld.geruch || oberesFeld != null && oberesFeld.besucht && !oberesFeld.geruch || unteresFeld != null
					&& unteresFeld.besucht && !unteresFeld.geruch) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Diese Methode prueft nach einer Fallgrube, und gibt true, wenn das Feld schon besucht war oder kann nicht 100% festgestellt werden, sonst false.
	 * 
	 * @param y
	 *            Y-Koordinate zum pruefen
	 * @param x
	 *            X-Koordinate zum pruefen
	 * @return false wenn keine Fallgrube
	 */
	private boolean istFallgrubeNichtDa(int y, int x) {
		if (ichBinNichtAuserhalb(y, x)) {
			Feld p = arraymitWissenBasis[y][x];
			/* Wenn Feld Besucht dann keine Gefahr */
			if (p.besucht) {
				return true;
			}
			Feld unteresFeld = null;
			Feld oberesFeld = null;
			Feld linkesFeld = null;
			Feld rechtesFeld = null;
			if (y - 1 >= 0) {
				oberesFeld = arraymitWissenBasis[y - 1][x];
			}
			if (y + 1 < anzahl) {
				unteresFeld = arraymitWissenBasis[y + 1][x];
			}
			if (x - 1 >= 0) {
				linkesFeld = arraymitWissenBasis[y][x - 1];
			}
			if (x + 1 < anzahl) {
				rechtesFeld = arraymitWissenBasis[y][x + 1];
			}
			if (linkesFeld != null && linkesFeld.besucht && !linkesFeld.brise || rechtesFeld != null && rechtesFeld.besucht && !rechtesFeld.brise || oberesFeld != null && oberesFeld.besucht && !oberesFeld.brise || unteresFeld != null
					&& unteresFeld.besucht && !unteresFeld.brise) {
				return true;
			}
		}
		return false;
	}

	// TODO: Agent kann nun trotz finden des Goldes mit der Taste "B" weiterlaufen. Dies ist abzustellen.

	/**
	 * Diese Methode soll ueber die Rekursion die kurzeste Weg zum Ziel finden und als LinkedList mit den dabei zu besuchenden Feldern zurückgeben.
	 * 
	 * @param liste
	 * @param agentY
	 *            Y-Koordinate des Agentes
	 * @param agentX
	 *            X-Koordinate des Agentes
	 * @param zielY
	 *            Y-Koordinate des Zieles
	 * @param zielX
	 *            X-Koordinate des Zieles
	 * @return
	 */
	private LinkedList<Position> gibMirDieRichtungZumZiel(LinkedList<Position> liste, int agentY, int agentX, int zielY, int zielX) {
		// TODO: Nach dem alle listen erstellt wurden, muss entschieden werden, welche liste returned wird.
		// TODO: Überprüfen der notwendigen Abbruchbedingungen
		// TODO: Überprüfen ob eine liste einen kompletten Pfad hat, also von Agent bis Ziel
		System.out.println("gib mir die Richtung zum Ziel");
		if (liste.contains(new Position(agentY, agentX))) {
			return liste;
		}
		liste.add(new Position(agentY, agentX));
		LinkedList<Position> listeOben, listeUnten, listeRechts, listeLinks;
		LinkedList<LinkedList<Position>> listen = new LinkedList<LinkedList<Position>>();
		if (istDortKeineWand(agentY, agentX, 1)) {
			// TODO: Wenn keine warhnehmung darüber ob Wand oben, muss trotzdem geprüft werden ob outofbound
			if (arraymitWissenBasis[agentY - 1][agentX].besucht && pruefeFeldNachSicherheit(agentY - 1, agentX)) {
				listeOben = gibMirDieRichtungZumZiel(liste, agentY - 1, agentX, zielY, zielX);
				// TODO: Überprüfe wann eine liste drin sein soll
				if (listeKomplett(listeOben, new Position(agentY, agentX), new Position(zielY, zielX))) {
					listen.add(listeOben);
				}
				// gehe agentY = agenY -1;
				// bewegungsListe = gibeMirDieRichtungZumZiel(agentY, agentX, y, x)
				// bewegungsListe.addFirst(Bezeichnungen.UP);
			}
		}
		if (istDortKeineWand(agentY, agentX, 2)) {
			if (arraymitWissenBasis[agentY + 1][agentX].besucht && pruefeFeldNachSicherheit(agentY + 1, agentX)) {
				listeUnten = gibMirDieRichtungZumZiel(liste, agentY + 1, agentX, zielY, zielX);
				if (listeKomplett(listeUnten, new Position(agentY, agentX), new Position(zielY, zielX))) {
					listen.add(listeUnten);
				}
			}
		}
		if (istDortKeineWand(agentY, agentX, 3)) {

			if (arraymitWissenBasis[agentY][agentX - 1].besucht && pruefeFeldNachSicherheit(agentY, agentX - 1)) {
				listeLinks = gibMirDieRichtungZumZiel(liste, agentY, agentX - 1, zielY, zielX);
				if (listeKomplett(listeLinks, new Position(agentY, agentX), new Position(zielY, zielX))) {
					listen.add(listeLinks);
				}
			}
		}
		if (istDortKeineWand(agentY, agentX, 4)) {
			if (arraymitWissenBasis[agentY][agentX + 1].besucht && pruefeFeldNachSicherheit(agentY, agentX + 1)) {
				listeRechts = gibMirDieRichtungZumZiel(liste, agentY, agentX + 1, zielY, zielX);
				if (listeKomplett(listeRechts, new Position(agentY, agentX), new Position(zielY, zielX))) {
					listen.add(listeRechts);
				}
			}
		}
		/* Wenn keine der Richtungen das Ziel enthält wird die anfänglich übergebene Liste zurück gegeben. */
		if (listen.isEmpty()) {
			return liste;
		}
		LinkedList<Position> richtigeListe = listen.getFirst();
		for (int i = 1; i < listen.size(); i++) {
			if (richtigeListe.size() > listen.get(i).size()) {
				richtigeListe = listen.get(i);

			}
		}
		System.out.println("Liste mit Positionen");
		for (int i = 0; i < richtigeListe.size(); i++) {
			System.out.println(i + ".Elemnt der Liste (" + richtigeListe.get(i).y + "|" + richtigeListe.get(i).x + ")");
		}
		return richtigeListe;
	}

	/**
	 * Diese Methode
	 * 
	 * @param agentY
	 *            Y-Koordinate des Agentes
	 * @param agentX
	 *            X-Koordinate des Agentes
	 * @param zielY
	 *            Y-Koordinate des Zieles
	 * @param zielX
	 *            X-Koordinate des Zieles
	 * @return
	 */
	private LinkedList<Position> gibMirDieRichtungZumZiel(int agentY, int agentX, int zielY, int zielX) {
		if (!arraymitWissenBasis[agentY][agentX].besucht || arraymitWissenBasis[zielY][zielX].besucht || agentY == zielY && agentX == zielX)
			return new LinkedList<Position>();
		int[][] bewertung = new int[anzahl][anzahl];
		for (int j = 0; j < anzahl; j++) {
			for (int i = 0; i < anzahl; i++) {
				bewertung[j][i] = -1;
			}
		}
		bewertung[zielY][zielX] = 0;
		LinkedList<Position> fifo = new LinkedList<Position>();
		int first = 0;
		boolean routeGefunden = false;
		fifo.add(new Position(zielY, zielX));
		while (first != fifo.size()) {
			Position temp = fifo.get(first++);
			int entfernung = bewertung[temp.y][temp.x] + 1;
			if (temp.y == agentY && temp.x == agentX) {
				routeGefunden = true;
				break;
			}
			Position oben = new Position(temp.y - 1, temp.x), unten = new Position(temp.y + 1, temp.x), links = new Position(temp.y, temp.x - 1), rechts = new Position(temp.y, temp.x + 1);
			if (ichBinNichtAuserhalb(oben.y, oben.x) && arraymitWissenBasis[oben.y][oben.x].besucht && bewertung[oben.y][oben.x] == -1) {
				bewertung[oben.y][oben.x] = entfernung;
				fifo.add(oben);
			}
			if (ichBinNichtAuserhalb(unten.y, unten.x) && arraymitWissenBasis[unten.y][unten.x].besucht && bewertung[unten.y][unten.x] == -1) {
				bewertung[unten.y][unten.x] = entfernung;
				fifo.add(unten);
			}
			if (ichBinNichtAuserhalb(links.y, links.x) && arraymitWissenBasis[links.y][links.x].besucht && bewertung[links.y][links.x] == -1) {
				bewertung[links.y][links.x] = entfernung;
				fifo.add(links);
			}
			if (ichBinNichtAuserhalb(rechts.y, rechts.x) && arraymitWissenBasis[rechts.y][rechts.x].besucht && bewertung[rechts.y][rechts.x] == -1) {
				bewertung[rechts.y][rechts.x] = entfernung;
				fifo.add(rechts);
			}
		}
		if (!routeGefunden) {
			return new LinkedList<Position>();
		}
		fifo.clear();
		fifo.add(new Position(agentY, agentX));
		while (true) {
			Position temp = fifo.get(fifo.size() - 1);
			int entfernung = bewertung[temp.y][temp.x] - 1;
			if (temp.y == zielY && temp.x == zielX) {
				break;
			}
			Position oben = new Position(temp.y - 1, temp.x), unten = new Position(temp.y + 1, temp.x), links = new Position(temp.y, temp.x - 1), rechts = new Position(temp.y, temp.x + 1);
			if (ichBinNichtAuserhalb(oben.y, oben.x) && bewertung[oben.y][oben.x] == entfernung) {
				fifo.add(oben);
				continue;
			}
			if (ichBinNichtAuserhalb(unten.y, unten.x) && bewertung[unten.y][unten.x] == entfernung) {
				fifo.add(unten);
				continue;
			}
			if (ichBinNichtAuserhalb(links.y, links.x) && bewertung[links.y][links.x] == entfernung) {
				fifo.add(links);
				continue;
			}
			if (ichBinNichtAuserhalb(rechts.y, rechts.x) && bewertung[rechts.y][rechts.x] == entfernung) {
				fifo.add(rechts);
				continue;
			}
		}

		return fifo;
	}

	/**
	 * Prüft ob eine Liste sowohl Position des Agenten als auch des Ziel beinhaltet.
	 * 
	 * @param liste
	 *            eine Liste mit Bewegungsrichtungen
	 * @param agent
	 *            Y,X Position des Agentes
	 * @param ziel
	 *            Y,X Position des Zieles
	 * @return
	 */
	private boolean listeKomplett(LinkedList<Position> liste, Position agent, Position ziel) {
		if (liste.contains(agent) && liste.contains(ziel)) {
			return true;
		}
		return false;
	}

	/**
	 * Diese Methode prüft, ob die nahligende Felder besucht waren. d.h. es muss min. ein nahligendes Feld besucht sein.
	 * 
	 * @param y
	 *            Y-Koordinate des Feldes
	 * @param x
	 *            X-Koordinate des Feldes
	 * @return true wenn min. ein nahligendes Feld besucht ist.
	 */
	private boolean dieNahligendeFelderBesucht(int y, int x) {
		if (ichBinNichtAuserhalb(y - 1, x)) {
			if (arraymitWissenBasis[y - 1][x].besucht) {
				return true;
			}
		}
		if (ichBinNichtAuserhalb(y + 1, x)) {
			if (arraymitWissenBasis[y + 1][x].besucht) {
				return true;
			}
		}
		if (ichBinNichtAuserhalb(y, x - 1)) {
			if (arraymitWissenBasis[y][x - 1].besucht) {
				return true;
			}
		}
		if (ichBinNichtAuserhalb(y, x + 1)) {
			if (arraymitWissenBasis[y][x + 1].besucht) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Diese Methode soll ausgehend von der derzeitigen Position und der aktuellen Wissensbasis versuchen das Gold zu finden. Falls dies nicht möglich ist, soll diese Methode das nächste noch nicht besuchte und sichere Feld finden.
	 * 
	 * @param _y
	 *            Y-Koordinate des Agentes
	 * @param _x
	 *            X-Koordinate des Agentes
	 */
	private void sucheDieRoute(int _y, int _x) {
		/* Fall eins: suche ein Feld, wo ein Gold und kein Gefahr sein kann. */
		boolean wumpusToeten = false;
		System.out.println("Suche die Route");
		int zielY = -1, zielX = -1;
		wump.sendeSchlussfolgerung("Agent versucht zu schlussfolgern wo das Gold ist.");
		Position woIstGold = istGoldDa();
		if (woIstGold == null) {
			for (int y = 0; y < anzahl; y++) {
				for (int x = 0; x < anzahl; x++) {
					if (!arraymitWissenBasis[y][x].besucht) {
						if (istGoldDa(y, x)) {
							System.out.println("Gold zuweisen");
							zielY = y;
							zielX = x;
						}
					}
				}
			}
		} else {
			zielY = woIstGold.y;
			zielX = woIstGold.x;
		}
		/* Wenn kein Gold findbar, weil wissensbasis nicht ausreichend, dann suche nächstes sicheres nicht besuchtes Feld */
		if (zielY == -1 && zielX == -1) {
			// Agent konnte nicht sagen wo gold ist AUSGABE
			wump.sendeSchlussfolgerung("Agent konnte nicht herausfinden wo das Gold ist und versucht nun das nächste sichere Feld zu finden.");
			double zielentfernung = 0;
			for (int y = 0; y < anzahl; y++) {
				for (int x = 0; x < anzahl; x++) {
					// TODO: der Agent fahlt in Pit????????????????????????????????????????????????????????????????????
					if (dieNahligendeFelderBesucht(y, x)) {
						// TODO: der agent prueft jedes feld statt nur nahligenden Felder um die vom Agent schon besuchten felder
						if (!arraymitWissenBasis[y][x].besucht && pruefeFeldNachSicherheit(y, x)) {
							double entfernung = Math.sqrt((agentY - y) * (agentY - y) + (agentX - x) * (agentX - x));
							if (zielY == -1 && zielX == -1) {
								System.out.println("Sicheres Feld zuweisen");
								zielY = y;
								zielX = x;
								zielentfernung = entfernung;
							} else if (zielentfernung > entfernung) {
								zielY = y;
								zielX = x;
								zielentfernung = entfernung;
							}
						}
					}
				}
			}
		}
		if (zielY == -1 && zielX == -1) {
			wump.sendeSchlussfolgerung("Der Agent konnte kein sicheres Feld finden und versucht nun seinen wertvollen Pfeil sicher in die atomare Hülle vom Wumpus zu versenken.");
			Position woIstWumpus = istWumpusDa();
			if (woIstWumpus == null) {
				for (int y = 0; y < anzahl; y++) {
					for (int x = 0; x < anzahl; x++) {
						if (istWumpusDa(y, x)) {
							System.out.println("Wumpus zum Toeten zuweisen");
							zielY = y;
							zielX = x;
							wumpusToeten = true;
						}
					}
				}
			} else {
				System.out.println("Wumpus zum Toeten zuweisen");
				zielY = woIstWumpus.y;
				zielX = woIstWumpus.x;
				// wump.agetSagt(zielY, zielX);
				wumpusToeten = true;
			}
		}

		if (zielY != -1 && zielX != -1) {
			if (!positionenListe.isEmpty()) {
				positionenListe.clear();
			}
			positionenListe = gibMirDieRichtungZumZiel(_y, _x, zielY, zielX);
			// TODO: Wenn eine Liste ausgegeben wird, muss getestet werden ob überhaupt etwas enthält, bzw. ob Ziel und Anfang drin ist
			// if (!listeKomplett(positionenListe, new Position(_y, _x), new Position(zielY, zielX))) {
			// positionenListe.clear();
			// }

			if (!bewegungsListe.isEmpty()) {
				bewegungsListe.clear();
			}
			/*
			 * Es wird geprüft ob die Positionen Liste nicht Leer ist, also ein Weg gefunden wurde. Dann wird diese Liste solange durchlaufen bis sie leer ist, wobei von der ersten zur zweiten Position jeweils die Richtung an die bewegungsListe
			 * weitergegeben wird.F
			 */
			if (!positionenListe.isEmpty()) {
				for (; positionenListe.size() > 1;) {
					// if (positionenListe.size() > 1) {
					bewegungsListe.add(welcheRichtung(positionenListe.poll(), positionenListe.peek()));
					// } else {
					// positionenListe.clear();
					// }
				}
			}
			if (wumpusToeten && pfeil) {
				String richtung = bewegungsListe.getLast();
				bewegungsListe.set(bewegungsListe.size() - 1, Bezeichnungen.BENUTZE_PFEIL + " " + richtung);
				if (bewegungsListe.getFirst().equals(Bezeichnungen.BENUTZE_PFEIL + " " + richtung)) {
					pfeil = false;
				}
			}
			System.out.println("bewegungsliste");
			for (int i = 0; i < bewegungsListe.size(); i++) {
				System.out.println(i + ". " + bewegungsListe.get(i));
			}
		}
		if (zielY == -1 && zielX == -1) {
			double zielentfernung = 0;
			for (int y = 0; y < anzahl; y++) {
				for (int x = 0; x < anzahl; x++) {
					// TODO: der Agent fahlt in Pit????????????????????????????????????????????????????????????????????
					if (dieNahligendeFelderBesucht(y, x)) {
						// TODO: der agent prueft jedes feld statt nur nahligenden Felder um die vom Agent schon besuchten felder
						if (!arraymitWissenBasis[y][x].besucht && !pruefeFeldNachSicherheit(y, x)) {
							double entfernung = Math.sqrt((agentY - y) * (agentY - y) + (agentX - x) * (agentX - x));
							if (zielY == -1 && zielX == -1) {
								System.out.println("Kein sicheres Feld zuweisen");
								zielY = y;
								zielX = x;
								zielentfernung = entfernung;
							} else if (zielentfernung > entfernung) {
								zielY = y;
								zielX = x;
								zielentfernung = entfernung;								
							}
						}
					}
				}
			}
			positionenListe = gibMirDieRichtungZumZiel(_y, _x, zielY, zielX);
			bewegungsListe.add(welcheRichtung(positionenListe.poll(), positionenListe.peek()));
		}
		
		// TODO: Bewegung anhand der bewegungsListe muss eingeleitet werden.
		/*
		 * liste muss in einer schleife, die einzelnd durchlaufen werden kann (Tastendruck) abgelaufen werden for(;bewegungsListe.isEmpty();){ wump.bewegeAgent(bewegungsListe.poll()); PAUSETASTE }
		 */
	}
	
	/**
	 * Diese Methode bestimmt die Richtung aus zwei Positionen.
	 * @param von Startposition
	 * @param nach Zielposition
	 * @return Richtung(String)
	 */
	private String welcheRichtung(Position von, Position nach) {
		if (von.y == nach.y) {
			if (von.x > nach.x) {
				return Bezeichnungen.LINKS;
			} else if (von.x < nach.x) {
				return Bezeichnungen.RECHTS;
			}
		} else if (von.x == nach.x) {
			if (von.y > nach.y) {
				return Bezeichnungen.UP;
			} else if (von.y < nach.y) {
				return Bezeichnungen.DOWN;
			}
		}
		return "GEH NACH HAUSE!";
	}

	/**
	 * Diese Methode prueft Feld nach Gold, wenn es nicht moeglich es auszuschlissen, dann gibt es false zurueck.
	 * @param y Y-Koordinate des Feldes
	 * @param x X-Koordinate des Feldes
	 * @return true(wenn Gold da)
	 */
	private boolean istGoldNichtDa(int y, int x) {
		if (ichBinNichtAuserhalb(y, x)) {
			Feld p = arraymitWissenBasis[y][x];
			/* Wenn Feld Besucht dann keine Gefahr */
			if (p.besucht) {
				return true;
			}
			Feld unteresFeld = null;
			Feld oberesFeld = null;
			Feld linkesFeld = null;
			Feld rechtesFeld = null;
			if (y - 1 >= 0) {
				oberesFeld = arraymitWissenBasis[y - 1][x];
			}
			if (y + 1 < anzahl) {
				unteresFeld = arraymitWissenBasis[y + 1][x];
			}
			if (x - 1 >= 0) {
				linkesFeld = arraymitWissenBasis[y][x - 1];
			}
			if (x + 1 < anzahl) {
				rechtesFeld = arraymitWissenBasis[y][x + 1];
			}
			if (linkesFeld != null && linkesFeld.besucht && !linkesFeld.glitter || rechtesFeld != null && rechtesFeld.besucht && !rechtesFeld.glitter || oberesFeld != null && oberesFeld.besucht && !oberesFeld.glitter || unteresFeld != null
					&& unteresFeld.besucht && !unteresFeld.glitter) {
				return true;
			}
		}
		return false;
	}

	/**	
	 * Diese Methode entfern Wumpus aus Wumpuswelt, wenn der Agent ihm getoetet hat.
	 * @param y Y-Koordinate des Feldes
	 * @param x X-Koordinate des Feldes
	 */
	private void entferneWumpus(int y, int x) {
		if (y - 1 >= 0 && y - 1 < anzahl && x >= 0 && x < anzahl) {
			if (arraymitWissenBasis[y - 1][x].besucht) {
				System.out.println("ersterFall" + "Agent(" + agentY + "|" + agentX + ") Feld(" + (y - 1) + "|" + x + ")");
				arraymitWissenBasis[y - 1][x].geruch = false;
				if (agentY != y - 1 && agentX == x || agentY == y - 1 && agentX != x || agentY != y - 1 && agentX != x) {
					wump.aktualisiereBild(y - 1, x);
				}
			}
		}
		if (y + 1 >= 0 && y + 1 < anzahl && x >= 0 && x < anzahl) {
			if (arraymitWissenBasis[y + 1][x].besucht) {
				System.out.println("zweiterFall " + "Agent(" + agentY + "|" + agentX + ") Feld(" + (y + 1) + "|" + x + ")");
				arraymitWissenBasis[y + 1][x].geruch = false;
				if (agentY != y + 1 && agentX == x || agentY == y + 1 && agentX != x || agentY != y + 1 && agentX != x) {
					wump.aktualisiereBild(y + 1, x);
				}
			}
		}
		if (y >= 0 && y < anzahl && x - 1 >= 0 && x - 1 < anzahl) {
			if (arraymitWissenBasis[y][x - 1].besucht) {
				System.out.println("dritterFall" + "Agent(" + agentY + "|" + agentX + ") Feld(" + (y) + "|" + (x - 1) + ")");
				arraymitWissenBasis[y][x - 1].geruch = false;
				if (agentY != y && agentX == x - 1 || agentY == y && agentX != x - 1 || agentY != y && agentX != x - 1) {
					wump.aktualisiereBild(y, x - 1);
				}
			}
		}
		if (y >= 0 && y < anzahl && x + 1 >= 0 && x + 1 < anzahl) {
			if (arraymitWissenBasis[y][x + 1].besucht) {
				System.out.println("vierterFall" + "Agent(" + agentY + "|" + agentX + ") Feld(" + (y) + "|" + (x + 1) + ")");
				arraymitWissenBasis[y][x + 1].geruch = false;
				if (agentY != y && agentX == x + 1 || agentY == y && agentX != x + 1 || agentY != y && agentX != x + 1) {
					System.out.println("vierterFall" + "Agent(" + agentY + "|" + agentX + ") Feld(" + y + "|" + (x + 1) + ")");
					wump.aktualisiereBild(y, x + 1);
				}
			}
		}
	}

	/**
	 * Diese Methode speichert die Bewegung des Agentes.
	 * @param temp Bewegungsrichtung 
	 */
	private void sendeSpeichern(String temp) {
		wump.speichereBewegung(temp, arraymitWissenBasis, wump.weltArray);
	}
}
