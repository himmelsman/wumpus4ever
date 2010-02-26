package de.wumpus.beobachter;

import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import java.util.Scanner;
import java.util.concurrent.LinkedBlockingQueue;

import de.wumpus.beobachtet.WumpusWelt;
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
	Feld feld;
	Feld[][] arraymitWissenBasis;

	public Agent(WumpusWelt _wump) {
		wump = _wump;
	}

	private void neuesSpiel() {
		// if(anzahl!=wump.anzahl)
		{
			anzahl = wump.anzahl;
			arraymitWissenBasis = new Feld[anzahl][anzahl];
			for (int j = 0; j < anzahl; j++) {
				for (int i = 0; i < anzahl; i++) {
					arraymitWissenBasis[j][i] = new Feld();
				}
			}
		}
	}

	public void update(Observable obj, Object arg) {
		// System.out.println("Ich bin der Agent in kl. Agent");
		if (((NachrichtenObjekt) arg).information.equals(Bezeichnungen.AGENT)) {
			// System.out.println("Meine Position y " + ((NachrichtenObjekt) arg).y + " Position x: " + ((NachrichtenObjekt) arg).x);
			// positionDesAgentes(((NachrichtenObjekt) arg).y, ((NachrichtenObjekt) arg).x, ((NachrichtenObjekt) arg).wahrnehmung);
			// System.out.println("Wissenbasis des Abentes");
			// ausgabe();
		}
		if (((NachrichtenObjekt) arg).information.equals(Bezeichnungen.NEUES_SPIEL)) {
			neuesSpiel();
			// positionDesAgentes(((NachrichtenObjekt) arg).y, ((NachrichtenObjekt) arg).x, ((NachrichtenObjekt) arg).wahrnehmung);
			// System.out.println("Wissenbasis des Abentes");
			agentY = ((NachrichtenObjekt) arg).y;
			agentX = ((NachrichtenObjekt) arg).x;
			// bewegeAgenten();
			// ausgabe();
		}
		if (((NachrichtenObjekt) arg).information.equals(Bezeichnungen.BEWEGE_AGENT)) {
			bewegeAgenten();
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
					// TODO: Was wird hier bei der Ausgabe ge�ndert
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
					// TODO: Was wird hier bei der Ausgabe ge�ndert
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
	 * diese Methode prueft, ob man nicht ausserhalb des vor gegebenes Bereiches nicht rausgeht. d.h. vermeidet ArrayIndexOutOfBoundsException gibt ein Bumb falls dieses doch vorkommt aus.
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
			System.out.println("BUMP");
			return false;
		}
	}

	/**
	 * Soll pr�fen ob eine Wand in angegebener Richtung existiert
	 * 
	 * @param y
	 *            Y-Koordinate des Feldes
	 * @param x
	 *            X-Koordinate des Feldes
	 * @param richtung
	 * @return
	 */
	private boolean istDortWand(int y, int x, int richtung) {
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
	 * @return wird die Richtung �bergeben.
	 */
	private int bestimmeDieRichtung(int agentY, int agentX) {
		if (ersteSchritt) {
			// Feld oberesFeldVonGlitter = null;
			// Feld linkesFeldVonGlitter = null;
			// Feld rechtesFeldVonGlitter = null;
			// Feld unteresFeldVonGlitter = null;
			// // boolean esGlittert = false;
			// int geheNichtNochmal = 0;
			// for (int y = 0; y < anzahl; y++) {
			// for (int x = 0; x < anzahl; y++) {
			// if (geheNichtNochmal == 0) {
			// if (arraymitWissenBasis[y][x].glitter) {
			// oberesFeldVonGlitter = arraymitWissenBasis[y][x];
			// geheNichtNochmal = 1;
			// }
			// }
			// if (geheNichtNochmal == 1) {
			// if (arraymitWissenBasis[y][x].glitter) {
			// linkesFeldVonGlitter = arraymitWissenBasis[y][x];
			// geheNichtNochmal = 2;
			// }
			// }
			// if (geheNichtNochmal == 2) {
			// if (arraymitWissenBasis[y][x].glitter) {
			// rechtesFeldVonGlitter = arraymitWissenBasis[y][x];
			// geheNichtNochmal = 3;
			// }
			// }
			// if (geheNichtNochmal == 3) {
			// if (arraymitWissenBasis[y][x].glitter) {
			// unteresFeldVonGlitter = arraymitWissenBasis[y][x];
			// geheNichtNochmal = 4;
			// }
			// }
			// }
			// }
			/* Erstes Fall: auf neu getretenen Feld ist ein Glitter und nichts(keine weitere Wahrnehmung */
			// if (ichBinNichtAuserhalb(agentY - 1, agentX)) {
			if (arraymitWissenBasis[agentY][agentX].glitter && !arraymitWissenBasis[agentY][agentX].geruch && !arraymitWissenBasis[agentY][agentX].brise) {
				if (ichBinNichtAuserhalb(agentY - 1, agentX) && (istGoldDa(agentY - 1, agentX) || !arraymitWissenBasis[agentY - 1][agentX].besucht)) {
					return richtung = 1;
				} else if (ichBinNichtAuserhalb(agentY + 1, agentX) && (istGoldDa(agentY + 1, agentX) || !arraymitWissenBasis[agentY + 1][agentX].besucht)) {
					return richtung = 2;
				} else if (ichBinNichtAuserhalb(agentY, agentX - 1) && (istGoldDa(agentY, agentX - 1) || !arraymitWissenBasis[agentY][agentX - 1].besucht)) {
					return richtung = 3;
				} else if (ichBinNichtAuserhalb(agentY, agentX + 1) && (istGoldDa(agentY, agentX + 1) || !arraymitWissenBasis[agentY][agentX + 1].besucht)) {
					return richtung = 4;
				}
			}
			/* Zweites Fall: auf neu getretenen Feld ist ein Glitter und die weitere Wahrnehmungen */
			else if (arraymitWissenBasis[agentY][agentX].glitter && (arraymitWissenBasis[agentY][agentX].geruch || arraymitWissenBasis[agentY][agentX].brise)) {
				if (ichBinNichtAuserhalb(agentY - 1, agentX) && (istGoldDa(agentY - 1, agentX) || pruefeFeldNachSicherheit(agentY - 1, agentX) && !arraymitWissenBasis[agentY - 1][agentX].besucht)) {
					return richtung = 1;
				} else if (ichBinNichtAuserhalb(agentY + 1, agentX) && (istGoldDa(agentY + 1, agentX) || pruefeFeldNachSicherheit(agentY + 1, agentX) && !arraymitWissenBasis[agentY + 1][agentX].besucht)) {
					return richtung = 2;
				} else if (ichBinNichtAuserhalb(agentY, agentX - 1) && (istGoldDa(agentY, agentX - 1) || pruefeFeldNachSicherheit(agentY, agentX - 1) && !arraymitWissenBasis[agentY][agentX - 1].besucht)) {
					return richtung = 3;
				} else if (ichBinNichtAuserhalb(agentY, agentX + 1) && (istGoldDa(agentY, agentX + 1) || pruefeFeldNachSicherheit(agentY, agentX + 1) && !arraymitWissenBasis[agentY][agentX + 1].besucht)) {
					return richtung = 4;
				}
			}

			// }
			/* Dritte Fall: Es ist keine Wahrnehmung da, der Agent soll aber auch nicht zur�ck gehen. */
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
	 * @return wird eine gegen Zahl(Richtung) �bergeben.
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

	private void bewegeAgenten() {
		/*
		 * if(!bewegungsListe.isEmpty()){ wump.bewegeAgent(bewegungsListe.poll(); if(AgentSitztaufGold) Beende das Spiel mit erfolgsmeldung }
		 */
		// Derzeit wird der Agent nicht bewegt sondern nur eines der anliegenden Felder wird gepr�ft
		// Deshalb do while mit Abbruchbedingung
		// TODO:Bewege den Agenten richtig
		setzeKeinGefahrNachStart(agentY, agentX);
		boolean weiterMachen = true;
		verarbeiteWahrnehmung(agentY, agentX);
		// int richtung = (int) ((Math.random()) * 4 + 1);
		// Falls Agent am Anfang nicht weiss wohin
		// do {
		bestimmeDieRichtung(agentY, agentX);
		 sucheDieRoute(agentY, agentX);
		// System.err.println("AgentY " + agentY + " AgentX " + agentX);
		// TODO: wenn agent schon einmal sich bewegt hat, muss die setzeKeinGefahrWennKeineWahrnehmung(y,x) aufgeruffen werden.
		// System.err.println("bewegeAgenten: " + richtung);
		switch (richtung) {
		case 1: {
			System.out.println("Agent(" + agentY + "|" + agentX + ") versucht nach oben zu gehen");
			if (ichBinNichtAuserhalb(agentY - 1, agentX)) {
				if (!(arraymitWissenBasis[agentY - 1][agentX].gefahr)) {
					// if (!pruefeFeldNachGefahr(agentY - 1, agentX)) {
					// if (pruefeFeldNachSicherheit(agentY - 1, agentX)) {
					agentY = agentY - 1;
					wump.bewegeAgent(Bezeichnungen.UP);
					verarbeiteWahrnehmung(agentY, agentX);
					weiterMachen = sitzeAufGold(agentY, agentX);
					if (!arraymitWissenBasis[agentY][agentX].gold && !arraymitWissenBasis[agentY][agentX].wumpus && !arraymitWissenBasis[agentY][agentX].geruch && !arraymitWissenBasis[agentY][agentX].fallgrube
							&& !arraymitWissenBasis[agentY][agentX].brise) {
						setzeKeinGefahrWennKeineWahrnehmung(agentY, agentX);
					}
				} else if (!pruefeFeldNachGefahr(agentY - 1, agentX)) {
					if (pruefeFeldNachSicherheit(agentY - 1, agentX)) {
						agentY = agentY - 1;
						wump.bewegeAgent(Bezeichnungen.UP);
						verarbeiteWahrnehmung(agentY, agentX);
						weiterMachen = sitzeAufGold(agentY, agentX);
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
					weiterMachen = sitzeAufGold(agentY, agentX);
					if (!arraymitWissenBasis[agentY][agentX].gold && !arraymitWissenBasis[agentY][agentX].wumpus && !arraymitWissenBasis[agentY][agentX].geruch && !arraymitWissenBasis[agentY][agentX].fallgrube
							&& !arraymitWissenBasis[agentY][agentX].brise) {
						setzeKeinGefahrWennKeineWahrnehmung(agentY, agentX);
					}
				} else if (!pruefeFeldNachGefahr(agentY + 1, agentX)) {
					if (pruefeFeldNachSicherheit(agentY + 1, agentX)) {
						agentY = agentY + 1;
						wump.bewegeAgent(Bezeichnungen.DOWN);
						verarbeiteWahrnehmung(agentY, agentX);
						weiterMachen = sitzeAufGold(agentY, agentX);
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
					weiterMachen = sitzeAufGold(agentY, agentX);
					if (!arraymitWissenBasis[agentY][agentX].gold && !arraymitWissenBasis[agentY][agentX].wumpus && !arraymitWissenBasis[agentY][agentX].geruch && !arraymitWissenBasis[agentY][agentX].fallgrube
							&& !arraymitWissenBasis[agentY][agentX].brise) {
						setzeKeinGefahrWennKeineWahrnehmung(agentY, agentX);
					}
				} else if (!pruefeFeldNachGefahr(agentY, agentX - 1)) {
					if (pruefeFeldNachSicherheit(agentY, agentX - 1)) {
						agentX = agentX - 1;
						wump.bewegeAgent(Bezeichnungen.LINKS);
						verarbeiteWahrnehmung(agentY, agentX);
						weiterMachen = sitzeAufGold(agentY, agentX);
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
					weiterMachen = sitzeAufGold(agentY, agentX);
					if (!arraymitWissenBasis[agentY][agentX].gold && !arraymitWissenBasis[agentY][agentX].wumpus && !arraymitWissenBasis[agentY][agentX].geruch && !arraymitWissenBasis[agentY][agentX].fallgrube
							&& !arraymitWissenBasis[agentY][agentX].brise) {
						setzeKeinGefahrWennKeineWahrnehmung(agentY, agentX);
					}
				} else if (!pruefeFeldNachGefahr(agentY, agentX + 1)) {
					if (pruefeFeldNachSicherheit(agentY, agentX + 1)) {
						agentX = agentX + 1;
						wump.bewegeAgent(Bezeichnungen.RECHTS);
						verarbeiteWahrnehmung(agentY, agentX);
						weiterMachen = sitzeAufGold(agentY, agentX);
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
		// ausgabe();
		// new Scanner(System.in).next();
		ersteSchritt = true;
		// } while (false);
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
	}

	/**
	 * Diese Methode pr�ft ob Agen in einen Feld mit Gold sich befindet.
	 * 
	 * @param y
	 *            Y-Koordinate des Feldes(Agentes)
	 * @param x
	 *            X-Koordinate des Feldes(Agentes)
	 * @return true wenn es wahr.
	 */
	private boolean sitzeAufGold(int y, int x) {
		if (arraymitWissenBasis[y][x].gold) {
			System.out.println("Gold ist da");
			return false;
		}
		return true;
	}

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

	private boolean pruefeFeldNachSicherheit(int y, int x) {
		System.out.println("pruefeFeldNachSicherheit: (" + y + "|" + x + ")");
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
		if (linkesFeld != null && linkesFeld.besucht && linkesFeld.glitter && istGoldNichtDa(y - 1, x - 1) && istGoldNichtDa(y + 1, x - 1) && istGoldNichtDa(y, x - 2)) {
			return true;
		} else if (rechtesFeld != null && rechtesFeld.besucht && rechtesFeld.glitter && istGoldNichtDa(y - 1, x + 1) && istGoldNichtDa(y + 1, x + 1) && istGoldNichtDa(y, x + 2)) {
			return true;
		} else if (oberesFeld != null && oberesFeld.besucht && oberesFeld.glitter && istGoldNichtDa(y - 2, x) && istGoldNichtDa(y - 1, x - 1) && istGoldNichtDa(y - 1, x + 1)) {
			return true;
		} else if (unteresFeld != null && unteresFeld.besucht && unteresFeld.glitter && istGoldNichtDa(y + 2, x) && istGoldNichtDa(y + 1, x - 1) && istGoldNichtDa(y + 1, x + 1)) {
			return true;
		}
		return false;
	}

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
	 * Diese Methode soll ueber die Rekursion die kurzeste Weg zum Ziel finden und als LinkedList mit den dabei zu besuchenden Feldern zur�ckgeben.
	 * 
	 * @param liste
	 * @param agentY
	 * @param agentX
	 * @param zielY
	 * @param zielX
	 * @return
	 */
	private LinkedList<Position> gibMirDieRichtungZumZiel(LinkedList<Position> liste, int agentY, int agentX, int zielY, int zielX) {
		// TODO: Nach dem alle listen erstellt wurden, muss entschieden werden, welche liste returned wird.
		// TODO: �berpr�fen der notwendigen Abbruchbedingungen
		// TODO: �berpr�fen ob eine liste einen kompletten Pfad hat, also von Agent bis Ziel
		System.out.println("gib mir die Richtung zum Ziel");
		if (liste.contains(new Position(agentY, agentX))) {
			return liste;
		}
		liste.add(new Position(agentY, agentX));
		LinkedList<Position> listeOben, listeUnten, listeRechts, listeLinks;
		LinkedList<LinkedList<Position>> listen = new LinkedList<LinkedList<Position>>();
		if (istDortWand(agentY, agentX, 1)) {
			// TODO: Wenn keine warhnehmung dar�ber ob Wand oben, muss trotzdem gepr�ft werden ob outofbound
			if (arraymitWissenBasis[agentY - 1][agentX].besucht && pruefeFeldNachSicherheit(agentY - 1, agentX)) {
				listeOben = gibMirDieRichtungZumZiel(liste, agentY - 1, agentX, zielY, zielX);
				// TODO: �berpr�fe wann eine liste drin sein soll
				if (listeKomplett(listeOben, new Position(agentY, agentX), new Position(zielY, zielX))) {
					listen.add(listeOben);
				}
				// gehe agentY = agenY -1;
				// bewegungsListe = gibeMirDieRichtungZumZiel(agentY, agentX, y, x)
				// bewegungsListe.addFirst(Bezeichnungen.UP);
			}
		}
		if (istDortWand(agentY, agentX, 2)) {
			if (arraymitWissenBasis[agentY + 1][agentX].besucht && pruefeFeldNachSicherheit(agentY + 1, agentX)) {
				listeUnten = gibMirDieRichtungZumZiel(liste, agentY + 1, agentX, zielY, zielX);
				if (listeKomplett(listeUnten, new Position(agentY, agentX), new Position(zielY, zielX))) {
					listen.add(listeUnten);
				}
			}
		}
		if (istDortWand(agentY, agentX, 3)) {

			if (arraymitWissenBasis[agentY][agentX - 1].besucht && pruefeFeldNachSicherheit(agentY, agentX - 1)) {
				listeLinks = gibMirDieRichtungZumZiel(liste, agentY, agentX - 1, zielY, zielX);
				if (listeKomplett(listeLinks, new Position(agentY, agentX), new Position(zielY, zielX))) {
					listen.add(listeLinks);
				}
			}
		}
		if (istDortWand(agentY, agentX, 4)) {
			if (arraymitWissenBasis[agentY][agentX + 1].besucht && pruefeFeldNachSicherheit(agentY, agentX + 1)) {
				listeRechts = gibMirDieRichtungZumZiel(liste, agentY, agentX + 1, zielY, zielX);
				if (listeKomplett(listeRechts, new Position(agentY, agentX), new Position(zielY, zielX))) {
					listen.add(listeRechts);
				}
			}
		}
		/* Wenn keine der Richtungen das Ziel enth�lt wird die anf�nglich �bergebene Liste zur�ck gegeben. */
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
	 * Pr�ft ob eine Liste sowohl Position des Agenten als auch des Ziel beinhaltet.
	 * 
	 * @param liste
	 * @param agent
	 * @param ziel
	 * @return
	 */
	private boolean listeKomplett(LinkedList<Position> liste, Position agent, Position ziel) {
		if (liste.contains(agent) && liste.contains(ziel)) {
			return true;
		}
		return false;
	}
	/**
	 * Diese Methode pr�ft, ob die nahligende Felder besucht waren. d.h. es muss min. ein nahligendes Feld besucht sein.
	 * @param y Y-Koordinate des Feldes
	 * @param x X-Koordinate des Feldes
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
	 * Diese Methode soll ausgehend von der derzeitigen Position und der aktuellen Wissensbasis versuchen das Gold zu finden. Falls dies nicht m�glich ist, soll diese Methode das n�chste noch nicht besuchte und sichere Feld finden.
	 * 
	 * @param _y
	 * @param _x
	 */
	private void sucheDieRoute(int _y, int _x) {
		/* Fall eins: suche ein Feld, wo ein Gold und kein Gefahr sein kann. */
		System.out.println("Suche die Route");
		int zielY = -1, zielX = -1;
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
		/* Wenn kein Gold findbar, weil wissensbasis nicht ausreichend, dann suche n�chstes sicheres nicht besuchtes Feld */
		if (zielY == -1 && zielX == -1) {
			for (int y = 0; y < anzahl; y++) {
				for (int x = 0; x < anzahl; x++) {
//TODO: der Agent fahlt in Pit????????????????????????????????????????????????????????????????????
					if (dieNahligendeFelderBesucht(y, x)) {
						// TODO: der agent prueft jedes feld statt nur nahligenden Felder um die vom Agent schon besuchten felder
						if (!arraymitWissenBasis[y][x].besucht && pruefeFeldNachSicherheit(y, x)) {
							if (zielY == -1 && zielX == -1 && (Math.abs(y) + Math.abs(x)) < (Math.abs(zielY) + Math.abs(zielX))) {
								System.out.println("Sicheres Feld zuweisen");
								zielY = y;
								zielX = x;
							}
						}
					}
				}
			}
		}

		if (zielY != -1 && zielX != -1) {
			if (!positionenListe.isEmpty()) {
				positionenListe.clear();
			}
			positionenListe = gibMirDieRichtungZumZiel(new LinkedList<Position>(), _y, _x, zielY, zielX);
			// TODO: Wenn eine Liste ausgegeben wird, muss getestet werden ob �berhaupt etwas enth�lt, bzw. ob Ziel und Anfang drin ist
			if (!listeKomplett(positionenListe, new Position(_y, _x), new Position(zielY, zielX))) {
				positionenListe.clear();
			}

			/*
			 * Es wird gepr�ft ob die Positionen Liste nicht Leer ist, also ein Weg gefunden wurde. Dann wird diese Liste solange durchlaufen bis sie leer ist, wobei von der ersten zur zweiten Position jeweils die Richtung an die bewegungsListe
			 * weitergegeben wird.F
			 */
			if (!positionenListe.isEmpty()) {
				for (; positionenListe.isEmpty();) {
					if (positionenListe.size() > 1) {
						bewegungsListe.add(welcheRichtung(positionenListe.poll(), positionenListe.peek()));
					} else {
						positionenListe.clear();
					}
				}
			}
		}

		// TODO: Bewegung anhand der bewegungsListe muss eingeleitet werden.
		/*
		 * liste muss in einer schleife, die einzelnd durchlaufen werden kann (Tastendruck) abgelaufen werden for(;bewegungsListe.isEmpty();){ wump.bewegeAgent(bewegungsListe.poll()); PAUSETASTE }
		 */
	}

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

}
