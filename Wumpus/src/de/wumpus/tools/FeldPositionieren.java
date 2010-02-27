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
		 * Initialisierung der istarray durch 0
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
					istarray[x][y] = checkLast(istarray[x][y], value);
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
	 * Die Methode setzt um den Wumpus,Pits und Gold nahligende felder mit Geruch,Brize und Glitter. Man muss achten damit die Felder nicht im außerem Rand plaziert werden.
	 * 
	 * @param x
	 *            x Koordinate
	 * @param y
	 *            y Koordinate
	 * @param value
	 *            ein Wert in Arra[y][x]
	 * @param anzahl
	 *            die grosse des Arrays
	 * @param istarray
	 *            selbst das zweidimensionale Array(Feld)
	 */
	private void setUmfeld(int x, int y, int value, int anzahl, int[][] istarray) {
		if (x != 0) {
			if (istarray[x - 1][y] == 0)
				istarray[x - 1][y] = value;
			else
				istarray[x - 1][y] = checkLast(istarray[x - 1][y], value);
//			System.out.println("value: " + value + " imArray:" + istarray[x - 1][y]);
		}
		if (x != anzahl - 1) {
			if (istarray[x + 1][y] == 0)
				istarray[x + 1][y] = value;
			else
				istarray[x + 1][y] = checkLast(istarray[x + 1][y], value);
//			System.out.println("value: " + value + " imArray:" + istarray[x + 1][y]);
		}
		if (y != 0) {
			if (istarray[x][y - 1] == 0)
				istarray[x][y - 1] = value;
			else
				istarray[x][y - 1] = checkLast(istarray[x][y - 1], value);
//			System.out.println("value: " + value + " imArray:" + istarray[x][y - 1]);
		}
		if (y != anzahl - 1) {
			if (istarray[x][y + 1] == 0)
				istarray[x][y + 1] = value;
			else
				istarray[x][y + 1] = checkLast(istarray[x][y + 1], value);
//			System.out.println("value: " + value + " imArray:" + istarray[x][y + 1]);
		}

	}

	/**
	 * Diese Methode wird die benuzerdefiniertes Feld uebegeben, mit Koordinaten von Agent,Gold,Wumpus,Pit
	 * 
	 * @param anzahl
	 *            die grosse des Arrays
	 * @param positionen
	 *            ein eindimensionales Array
	 */
	public int[][] myfeldPositionierung(int anzahl, int[] positionen) {
		int[][] istarray = new int[anzahl][anzahl];
		for (int i = 0; i < anzahl; i++) {
			for (int j = 0; j < anzahl; j++) {
				istarray[i][j] = 0;
			}
		}
		for (int i = 0; i < anzahl; i++) {
			for (int j = 0; j < anzahl; j++) {
				if (j == positionen[0] && i == positionen[1])
					istarray[j][i] = 1;
				if (j == positionen[2] && i == positionen[3]) {
					if(istarray[j][i] == 0){
						istarray[j][i] = 2;
					} else{
						istarray[j][i] = checkLast(istarray[j][i], 2);
					}
					// System.out.println("istarray[j][i] " + istarray[j][i]);
					setUmfeld(j, i, 3, anzahl, istarray);
				}
				if (j == positionen[4] && i == positionen[5]) {
					istarray[j][i] = 4;
					// System.out.println("istarray[j][i] " + istarray[j][i]);
					setUmfeld(j, i, 5, anzahl, istarray);
				}
				if (j == positionen[6] && i == positionen[7]) {
					istarray[j][i] = 6;
					// System.out.println("istarray[j][i] " + istarray[j][i]);
					setUmfeld(j, i, 7, anzahl, istarray);
				}
			}
		}
		return istarray;
	}

	/**
	 * Die Methode wandelt zwei Zahlen in ein um. d.h. 234 und 1 bekommen wir 1234
	 * 
	 * @param value
	 *            ein Wert in Array[y][x]
	 * @param newDigit
	 *            ein Wert
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
	 * 
	 * @param zahl
	 *            ein Zahl
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
		return (original - first) <= 0 ? 9 : original - first;
	}

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

	/**
	 * Diese Methode uebergibt die einzelne/separate Wahrnechnungen als ein Array an.
	 * 
	 * @param zahl
	 *            ein Zahl
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

//	public static void main(String[] args) {
//		FeldPositionieren fp = new FeldPositionieren();
//		int[] array = fp.separateWahrnehmungen(1230);
//		// for (int i = 0; i < array.length; i++) {
//		// System.out.println("array " + array[i]);
//		//
//		// }
//		// System.out.println("Fertig");
////		System.out.println(fp.checkLast(5, 2));
////		System.out.println(fp.checkLast(25, 7));
////		System.out.println(fp.checkFirst(257, 2));
////		System.out.println(fp.checkFirst(fp.checkFirst(257, 2), 5));
//		int[][] test = fp.myfeldPositionierung(4, new int[]{0,0,2,2,2,1,3,2});
//		for(int y = 0;y<4;y++){
//			for(int x = 0;x<4;x++){
//				System.out.println("Feld(" + (y) + "|" + x + ") " + test[y][x]);
//			}
//		}
//	}
}
