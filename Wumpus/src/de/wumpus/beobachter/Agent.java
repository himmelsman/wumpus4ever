package de.wumpus.beobachter;

import java.util.Observable;
import java.util.Observer;

import de.wumpus.beobachtet.WumpusWelt;
import de.wumpus.tools.Feld;
import de.wumpus.tools.Bezeichnungen;
import de.wumpus.tools.FeldPositionieren;
import de.wumpus.tools.NachrichtenObjekt;

public class Agent implements Observer {

	private int agentX, agentY, anzahl;
	WumpusWelt wump;
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
		System.out.println("Ich bin der Agent in kl. Agent");
		if (((NachrichtenObjekt) arg).information.equals(Bezeichnungen.AGENT)) {
			System.out.println("Meine Position x " + ((NachrichtenObjekt) arg).x + " Position y: " + ((NachrichtenObjekt) arg).y);
			// positionDesAgentes(((NachrichtenObjekt) arg).y, ((NachrichtenObjekt) arg).x, ((NachrichtenObjekt) arg).wahrnehmung);
			System.out.println("Wissenbasis des Abentes");
			// ausgabe();
		}
		if (((NachrichtenObjekt) arg).information.equals(Bezeichnungen.NEUES_SPIEL)) {
			neuesSpiel();
			// positionDesAgentes(((NachrichtenObjekt) arg).y, ((NachrichtenObjekt) arg).x, ((NachrichtenObjekt) arg).wahrnehmung);
			System.out.println("Wissenbasis des Abentes");
			agentY = ((NachrichtenObjekt) arg).y;
			agentX = ((NachrichtenObjekt) arg).x;
			bewegeAgenten();
			// ausgabe();
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
					System.out.print("2:" + arraymitWissenBasis[j][i].gold + " 4:" + arraymitWissenBasis[j][i].wumpus+ " 6:" + arraymitWissenBasis[j][i].fallgrube);
//					System.out.print("3:" + arraymitWissenBasis[j][i].glitter + " 5:" + arraymitWissenBasis[j][i].geruch + " 7:" + arraymitWissenBasis[j][i].brise);
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
//					System.out.print("2:" + arraymitWissenBasis[j][i].gold + " 4:" + arraymitWissenBasis[j][i].wumpus+ " 6:" + arraymitWissenBasis[j][i].fallgrube);
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

	private boolean ichBinNichtAuserhalb(int y, int x) {
		// System.err.println("Y: " + y + " X: " + x);
		if (y >= 0 && y < anzahl && x >= 0 && x < anzahl) {
			// System.err.println("ich bin nicht ausserhalb");
			return true;
		} else
			return false;
	}

	private void bewegeAgenten() {
		// Derzeit wird der Agent nicht bewegt sondern nur eines der anliegenden Felder wird geprüft
		// Deshalb do while mit Abbruchbedingung
		// TODO:Bewege den Agenten richtig
		boolean trigger = true;
		verarbeiteWahrnehmung(agentY, agentX);
		// Falls Agent am Anfang nicht weiss wohin
		do {
			int richtung = (int) ((Math.random()) * 4 + 1);
//			System.err.println("AgentY " + agentY + " AgentX " + agentX);
//			System.err.println("bewegeAgenten: " + richtung);
			switch (richtung) {
			case 1: {
				if (ichBinNichtAuserhalb(agentY - 1, agentX)) {
					pruefeFeldNachGefahr(agentY = agentY - 1, agentX);
					wump.bewegeAgent(Bezeichnungen.UP);
					trigger = sitzeAufGold(agentY, agentX);
				}
				break;
			}
			case 2: {
				if (ichBinNichtAuserhalb(agentY + 1, agentX)) {
					pruefeFeldNachGefahr(agentY = agentY + 1, agentX);
					wump.bewegeAgent(Bezeichnungen.DOWN);
					trigger = sitzeAufGold(agentY, agentX);
				}
				break;
			}
			case 3: {
				if (ichBinNichtAuserhalb(agentY, agentX - 1)) {
					pruefeFeldNachGefahr(agentY, agentX = agentX - 1);
					wump.bewegeAgent(Bezeichnungen.LINKS);
					trigger = sitzeAufGold(agentY, agentX);
				}
				break;
			}
			case 4: {
				if (ichBinNichtAuserhalb(agentY, agentX + 1)) {
					pruefeFeldNachGefahr(agentY, agentX = agentX + 1);
					wump.bewegeAgent(Bezeichnungen.RECHTS);
					trigger = sitzeAufGold(agentY, agentX);
				}
				break;
			}
			}
			verarbeiteWahrnehmung(agentY, agentX);
			ausgabe();
		} while (trigger);
		wump.agentIstFertig();
	}

	
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

	private boolean sitzeAufGold(int y, int x) {
		if (arraymitWissenBasis[y][x].gold) {
			System.out.println("Gold ist da");
			return false;
		}
		return true;
	}

	private void pruefeFeldNachGefahr(int y, int x) {
		if (istWumpusDa(y, x)) {
			System.err.println("DORT IST DAS WUMPUS.");
		} else if (istFallgrubeDa(y, x)) {
			System.err.println("DORT IST EINE FALLGRUBE.");
		} else if (istGoldDa(y, x)) {
			System.err.println("DORT IST DAS GOLD.");
		} else {
			System.err.println("DORT IST NICHTS NENNENSWERTES.");
		}
	}

	private void pruefeFeldNachSicherheit(int y, int x) {
		if (istWumpusNichtDa(y, x)) {
			System.err.println("DORT IST NICHT DAS WUMPUS.");
		} else if (istFallgrubeNichtDa(y, x)) {
			System.err.println("DORT IST KEINE FALLGRUBE.");
		} else {
			System.err.println("DORT IST NICHTS NENNENSWERTES.");
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
			return true;
		} else if (rechtesFeld != null && rechtesFeld.besucht && rechtesFeld.geruch && istWumpusNichtDa(y - 1, x + 1) && istWumpusNichtDa(y + 1, x + 1) && istWumpusNichtDa(y, x + 2)) {
			return true;
		} else if (oberesFeld != null && oberesFeld.besucht && oberesFeld.geruch && istWumpusNichtDa(y - 2, x) && istWumpusNichtDa(y - 1, x - 1) && istWumpusNichtDa(y - 1, x + 1)) {
			return true;
		} else if (unteresFeld != null && unteresFeld.besucht && unteresFeld.geruch && istWumpusNichtDa(y + 2, x) && istWumpusNichtDa(y + 1, x - 1) && istWumpusNichtDa(y + 1, x + 1)) {
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
		if (linkesFeld != null && linkesFeld.besucht && linkesFeld.brise && istWumpusNichtDa(y - 1, x - 1) && istWumpusNichtDa(y + 1, x - 1) && istWumpusNichtDa(y, x - 2)) {
			return true;
		} else if (rechtesFeld != null && rechtesFeld.besucht && rechtesFeld.brise && istWumpusNichtDa(y - 1, x + 1) && istWumpusNichtDa(y + 1, x + 1) && istWumpusNichtDa(y, x + 2)) {
			return true;
		} else if (oberesFeld != null && oberesFeld.besucht && oberesFeld.brise && istWumpusNichtDa(y - 2, x) && istWumpusNichtDa(y - 1, x - 1) && istWumpusNichtDa(y - 1, x + 1)) {
			return true;
		} else if (unteresFeld != null && unteresFeld.besucht && unteresFeld.brise && istWumpusNichtDa(y + 2, x) && istWumpusNichtDa(y + 1, x - 1) && istWumpusNichtDa(y + 1, x + 1)) {
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
		if (linkesFeld != null && linkesFeld.besucht && linkesFeld.glitter && istWumpusNichtDa(y - 1, x - 1) && istWumpusNichtDa(y + 1, x - 1) && istWumpusNichtDa(y, x - 2)) {
			return true;
		} else if (rechtesFeld != null && rechtesFeld.besucht && rechtesFeld.glitter && istWumpusNichtDa(y - 1, x + 1) && istWumpusNichtDa(y + 1, x + 1) && istWumpusNichtDa(y, x + 2)) {
			return true;
		} else if (oberesFeld != null && oberesFeld.besucht && oberesFeld.glitter && istWumpusNichtDa(y - 2, x) && istWumpusNichtDa(y - 1, x - 1) && istWumpusNichtDa(y - 1, x + 1)) {
			return true;
		} else if (unteresFeld != null && unteresFeld.besucht && unteresFeld.glitter && istWumpusNichtDa(y + 2, x) && istWumpusNichtDa(y + 1, x - 1) && istWumpusNichtDa(y + 1, x + 1)) {
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
