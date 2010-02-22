package de.wumpus.tools;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class FeldPositionieren {

	public int getZuffalszahl(int _anzahl) {
		int Zufallszahl;
		Zufallszahl = (int) ((Math.random()) * _anzahl + 1);
		return Zufallszahl;
	}

	public int[][] feldPositionierung(int anzahl) {
		// System.out.println("Wir sind in Feld Positionierung und Anzahl ist " + anzahl);// mackaken es wird schonwieder 2 mal aufgerufen!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

		/*
		 * initialisierung der istarray durch 0
		 */
		int[][] istarray = new int[anzahl][anzahl];
		for (int i = 0; i < anzahl; i++) {
			for (int j = 0; j < anzahl; j++) {
				istarray[i][j] = 0;
			}
		}
		/*
		 * heir werden die Zufallzahlen erzeugt und aufgepasst das die doppelte Zahlen nicht vorkommen
		 */
		/*
		 * Agent wurde extra aus der Set rausgenohmen, um die Abfage zu stellen(d.h. in nahrligenden Felder von Agent duerfen nicht die Gold,Wumpus,Pit plaziert
		 */
		int agent = getZuffalszahl(anzahl * anzahl);
		Set<Integer> numberSet = new HashSet<Integer>();
		while (numberSet.size() < anzahl - 1) {/* die Zahl, die Anzahl der generierten Elementen gibt(gold,wumpus,pit) */
			int others = getZuffalszahl(anzahl * anzahl);
			// System.err.println("others " + others + " agent" + agent);
			if (others != agent && others != agent - 1 && others != agent + 1 && others != agent + anzahl && others != agent - anzahl)
				numberSet.add(others); /* die Zahl wird nur hinzugefügt, wenn sie noch nicht existiert und liegt nicht in Umgebung von Agent */
		}
		/*
		 * initialisierung der istarray 1 als Agend in dem Feld 2 als Gold in dem Feld 3 als Glitter in nahligenden Felder 4 als Wumpus in dem Feld 5 als Geruch in nahligenden Felder 6 als Pit in dem Feld 7 als Brize ind nahligenden Felder
		 */
		/* hier wird der Agent direkt in WumpusWelt platziert */
		{
			int x, y;
			x = (agent - 1) % anzahl;
			y = (agent - 1) / anzahl;
			// System.out.println("x = " + x + " y = " + y);
			/* Dieses Teil ist dafür da, um die Zahlen nach eine Reiche hinzufuegen */
			if (istarray[x][y] == 0) {
				int value = 1;
				istarray[x][y] = value;
			}
			// else {
			// int value = 1;
			// istarray[x][y] = istarray[x][y] + value;
			// }
			/* Ende von dieses Teil */
			// System.out.println("Element: "+element + " Zahl: 1"+
			// "istarray: " + istarray[element]);
		}
		int j = 1, x, y;
		for (Iterator<Integer> it = numberSet.iterator(); it.hasNext();) {
			Integer element = it.next();
			// System.err.println("element:" +element);
			// System.out.println(element.toString());
			if (j == 1) {
				x = (element - 1) % anzahl;
				y = (element - 1) / anzahl;
				// System.out.println("x = " + x + " y = " + y);
				/*
				 * Dieses Teil ist dafür da, um die Zahlen nach eine Reiche hinzufuegen
				 */
				if (istarray[x][y] == 0) {
					int value = 2;
					istarray[x][y] = value;
				} else {
					int value = 2;
					istarray[x][y] = istarray[x][y] * 10 + value;
				}
				/* Ende von dieses Teil */
				setUmfeld(x, y, 3, anzahl, istarray);
				// System.out.println("Element: "+element + " Zahl: 2" +
				// "istarray: " + istarray[element]);
			} else if (j == 2) {
				x = (element - 1) % anzahl;
				y = (element - 1) / anzahl;
				// System.out.println("x = " + x + " y = " + y);
				/*
				 * Dieses Teil ist dafür da, um die Zahlen nach eine Reiche hinzufuegen
				 */
				if (istarray[x][y] == 0) {
					int value = 4;
					istarray[x][y] = value;
				} else {
					int value = 4;
					istarray[x][y] = istarray[x][y] * 10 + value;
				}
				/* Ende von dieses Teil */
				setUmfeld(x, y, 5, anzahl, istarray);
				// System.out.println("Element: "+element + " Zahl: 3"+
				// "istarray: " + istarray[element]);
			}
			/* als else machen und heir mehrere loescher einbauen */
			else {
				x = (element - 1) % anzahl;
				y = (element - 1) / anzahl;
				// System.out.println("x = " + x + " y = " + y);
				/*
				 * Dieses Teil ist dafür da, um die Zahlen nach eine Reiche hinzufuegen
				 */
				if (istarray[x][y] == 0) {
					int value = 6;
					istarray[x][y] = value;
				} else {
					int value = 6;
					istarray[x][y] = istarray[x][y] * 10 + value;
				}
				/* Ende von dieses Teil */
				setUmfeld(x, y, 7, anzahl, istarray);
				// System.out.println("Element: "+element + " Zahl: 4" +
				// "istarray: " + istarray[element]);
			}
			j++;
		}
		// for (int i = 0; i < anzahl; i++)
		// for (int h = 0; h < anzahl; h++)
		// System.out.println("Position: " + i + "," + h + " Wert: " + istarray[h][i]);
		return istarray;
	}

	/**
	 * Die Methode setzt um den Wumpus und Pits nahligende felder mit Geruchund Brize. Man muss achten damit die Felder nicht im außerem Rand plaziert werden.
	 */
	private void setUmfeld(int x, int y, int value, int anzahl, int[][] istarray) {
		if (x != 0)
			if (istarray[x - 1][y] == 0)
				istarray[x - 1][y] = value;
			else
				istarray[x - 1][y] = checkLast(istarray[x - 1][y], value);
		if (x != anzahl - 1) {
			if (istarray[x + 1][y] == 0)
				istarray[x + 1][y] = value;
			else
				istarray[x + 1][y] = checkLast(istarray[x + 1][y], value);
		}
		if (y != 0) {
			if (istarray[x][y - 1] == 0)
				istarray[x][y - 1] = value;
			else
				istarray[x][y - 1] = checkLast(istarray[x][y - 1], value);
		}
		if (y != anzahl - 1) {
			if (istarray[x][y + 1] == 0)
				istarray[x][y + 1] = value;
			else
				istarray[x][y + 1] = checkLast(istarray[x][y + 1], value);
		}
	}

	/** Diese Methode wird die benuzerdefiniertes Feld uebegeben, mit Koordinaten von Agent,Gold,Wumpus,Pit */
	public int[][] myfeldPositionierung(int anzahl, int[] positionen) {
		int[][] istarray = new int[anzahl][anzahl];
		for (int i = 0; i < anzahl; i++) {
			for (int j = 0; j < anzahl; j++) {
				istarray[i][j] = 0;
			}
		}
//		int[] temparray = new int[positionen.length];
//		for (int i = 0; i < positionen.length; i++) {
//			temparray[i] = (positionen[i]-1);
//		}
//		for (int i = 0; i < positionen.length; i++) {
//			positionen[i]= temparray[i];
//		}
//		
//		for (int i = 0; i < positionen.length; i++)
//			System.out.println("positionen" + positionen[i]);
		for (int i = 0; i < anzahl; i++) {
			for (int j = 0; j < anzahl; j++) {
				if (j == positionen[0] && i == positionen[1])
					istarray[j][i] = 1;
				if (j == positionen[2] && i == positionen[3]) {
					istarray[j][i] = 2;
//					System.out.println("istarray[j][i] " + istarray[j][i]);
					setUmfeld(j, i, 3, anzahl, istarray);
				}
				if (j == positionen[4] && i == positionen[5]) {
					istarray[j][i] = 4;
//					System.out.println("istarray[j][i] " + istarray[j][i]);
					setUmfeld(j,i, 5, anzahl, istarray);
				}
				if (j == positionen[6] && i == positionen[7]) {
					istarray[j][i] = 6;
//					System.out.println("istarray[j][i] " + istarray[j][i]);
					setUmfeld(j, i, 7, anzahl, istarray);
				}
			}
		}
//		for (int i = 0; i < positionen.length; i++)
//			System.out.println("positionarray" + positionen[i]);
//		for (int i = 0, j = 1; i < positionen.length; i = i + 2) {
//			if (j == 1) {
//				if (ichBinNichtAuserhalb(anzahl, i, i + 1)) {
//					istarray[positionen[i]][positionen[i + 1]] = 1;
//					j++;
//				} else
//					System.out.println("es wird auserhalb des vorgegebenes Bereiches zugegriffen ");
//			} else if (j == 1) {
//				if (ichBinNichtAuserhalb(anzahl, i, i + 1)) {
//					istarray[positionen[i]][positionen[i + 1]] = 2;
//					setUmfeld(positionen[i + 1], positionen[i], 3, anzahl, istarray);
//					j++;
//				} else
//					System.out.println("es wird auserhalb des vorgegebenes Bereiches zugegriffen ");
//			} else if (j == 1) {
//				if (ichBinNichtAuserhalb(anzahl, i, i + 1)) {
//					istarray[positionen[i]][positionen[i + 1]] = 4;
//					setUmfeld(positionen[i + 1], positionen[i], 5, anzahl, istarray);
//					j++;
//				} else
//					System.out.println("es wird auserhalb des vorgegebenes Bereiches zugegriffen ");
//			} else {
//				if (ichBinNichtAuserhalb(anzahl, i, i + 1)) {
//					istarray[positionen[i]][positionen[i + 1]] = 6;
//					setUmfeld(positionen[i + 1], positionen[i], 7, anzahl, istarray);
//				} else
//					System.out.println("es wird auserhalb des vorgegebenes Bereiches zugegriffen ");
//			}
//		}
//		for (int i = 0; i < anzahl; i++) {
//			for (int j = 0; j < anzahl; j++) {
//				System.out.println("uebergebenes Array " + istarray[i][j]);
//			}
//			System.out.println();
//		}
		return istarray;
	}

	/**
	 * Die Methode prueft was fuer ein Zahl am Ende ist.z.B. 123 diese Methode gibt als Rueckgabe 3	  
	 */
	public int checkLast(int value, int newDigit) {
		int oldDigit = value % 10;
		if (oldDigit == newDigit) {
			return value;
		} else if (oldDigit > newDigit) {
			return checkLast(value / 10, newDigit) * 10 + oldDigit;
		}
		return value * 10 + newDigit;
	}
	/**
	 * Diese Methode schneidet den ersten Element(Zahl des Agentes) einer Zahlz.B. 123 nach der Methode 23
	 */
	public int checkFirst(int zahl) {
		int zaehler = 0;
		int original = zahl;
		// System.out.println("Orginal " + orginal);
		do {
			zahl = zahl / 10;
			// System.out.println("Zahl " + zahl);
			zaehler++;
		} while (zahl > 10);
		int zahltest = 1;
		// System.out.println("Zähler " + zaehler);
		for (int i = 0; i < zaehler; i++) {
			zahltest = zahltest * 10;
		}
		// if(zahl < 0)zahl = 9; zahl == 0; zahl =9;
		return (original - zahltest) <= 0 ? 9 : original - zahltest;
	}
	/**
	 * Diese Methode schneidet den ersten Element(beliebige Zahl) einer Zahlz.B. 0123 nach der Methode 123
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
		return (original - first) <= 0 ? 9 : original - first;
	}

	/**
	 * Die Methode bestimmt die erste Zahl, die in der Arrayposition abgespeichert ist. d.h. die erste Teil der Zahl. z.B. Zahl = 234 nach der Methode Zahl = 2
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

	/**
	 *  Diese Methode uebergibt die einzelne/separate Wahrnechnungen als ein Array an.  
	 */
	public int[] separateWahrnehmungen(int zahl) {
		int zahlTemp = zahl;
		int zaehler = 0;
		do {
			zahlTemp = zahlTemp / 10;
			// System.out.println("Zahl " + zahl);
			zaehler++;
		} while (zahlTemp > 10);
		int[] temp = new int[zaehler + 1];
		for (int i = 0; i <= zaehler; i++) {
			temp[i] = bestimmeDieErsteZahl(zahl);
			// System.out.println("Zahl " + zahl + " temp[i] " + temp[i]);
			zahl = checkFirst(zahl, bestimmeDieErsteZahl(zahl));
		}
		return temp;
	}

	public static void main(String[] args) {
		FeldPositionieren fp = new FeldPositionieren();
		int[] array = fp.separateWahrnehmungen(1230);
		// for (int i = 0; i < array.length; i++) {
		// System.out.println("array " + array[i]);
		//
		// }
		// System.out.println("Fertig");
	}

	/* diese Methode prueft, ob man nicht ausserhalb des vor gegebenes Bereiches nicht rausgeht. d.h. vermeidet ArrayIndexOutOfBoundsException */
	private boolean ichBinNichtAuserhalb(int anzahl, int y, int x) {
		// System.err.println("Y: " + y + " X: " + x);
		if (y >= 0 && y < anzahl && x >= 0 && x < anzahl) {
			// System.err.println("ich bin nicht ausserhalb");
			return true;
		} else
			return false;
	}
}
