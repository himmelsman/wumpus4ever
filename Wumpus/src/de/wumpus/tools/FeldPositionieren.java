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
		System.out.println("Wir sind in Feld Positionierung und Anzahl ist " + anzahl);// mackaken
																						// es
																						// wird
																						// schonwieder
																						// 2
																						// mal
																						// aufgerufen!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

		/*
		 * initialisierung der istarray 1 als Agend in dem Feld 2 als Gold in
		 * dem Feld 3 als Wumpus in dem Feld 4 als Pit in dem Feld 5 als Geruch
		 * in nahligenden Felder 6 als Brize ind nahligenden Felder
		 */

		int[][] istarray = new int[anzahl][anzahl];/*
													 * Initialisierung der
													 * grosse des Arrays
													 */
		for (int i = 0; i < anzahl; i++) {
			for (int j = 0; j < anzahl; j++) {
				istarray[i][j] = 0;
			}
		}
		/*
		 * heir werden die Zufallzahlen erzeugt und aufgepasst das die doppelte
		 * Zahlen nicht vorkommen
		 */
		Set<Integer> numberSet = new HashSet<Integer>();
		while (numberSet.size() < anzahl) {/*
											 * die Zahl, die Anzahl der
											 * generierten Elementen
											 * gibt(agent,gold,wumpus,pit)
											 */
			numberSet.add(getZuffalszahl(anzahl * anzahl)); // die Zahl wird nur
			// hinzugefügt wenn
			// sie noch nicht
			// besteht
		}
		// /*initialisierung der istarray
		// * 1 als Agend in dem Feld
		// * 2 als Gold in dem Feld
		// * 3 als Glitter in nahligenden Felder
		// * 4 als Wumpus in dem Feld
		// * 5 als Geruch in nahligenden Felder
		// * 6 als Pit in dem Feld
		// * 7 als Brize ind nahligenden Felder */

		/* als besserer vorschlag waere switsh */
		int j = 0, x, y;
		for (Iterator<Integer> it = numberSet.iterator(); it.hasNext();) {
			Integer element = it.next();
			// System.out.println(element.toString());
			if (j == 0) {
				x = element % anzahl;
				y = (element - 1) / anzahl;
				// System.out.println("x = " + x + " y = " + y);
				/*
				 * Dieses Teil ist dafür da, um die Zahlen nach eine Reiche
				 * hinzufuegen
				 */
				if (istarray[x][y] == 0) {
					int value = 1;
					istarray[x][y] = value;
				} else {
					int value = 1;
					istarray[x][y] = istarray[x][y] + value;
				}
				/* Ende von dieses Teil */
				// System.out.println("Element: "+element + " Zahl: 1"+
				// "istarray: " + istarray[element]);
			} else if (j == 1) {
				x = element % anzahl;
				y = (element - 1) / anzahl;
				// System.out.println("x = " + x + " y = " + y);
				/*
				 * Dieses Teil ist dafür da, um die Zahlen nach eine Reiche
				 * hinzufuegen
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
				x = element % anzahl;
				y = (element - 1) / anzahl;
				// System.out.println("x = " + x + " y = " + y);
				/*
				 * Dieses Teil ist dafür da, um die Zahlen nach eine Reiche
				 * hinzufuegen
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
				x = element % anzahl;
				y = (element - 1) / anzahl;
				// System.out.println("x = " + x + " y = " + y);
				/*
				 * Dieses Teil ist dafür da, um die Zahlen nach eine Reiche
				 * hinzufuegen
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
		for (int i = 0; i < anzahl; i++)
			for (int h = 0; h < anzahl; h++)
				System.out.println("Position: " + i + "," + h + " Wert: " + istarray[h][i]);
		return istarray;
	}

	/*
	 * die Methode setzt um den Wumpus und Pits nahligende felder mit Geruchund
	 * Brize. Man muss achten damit die Felder nicht im außerem Rand plaziert
	 * werden.
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

	public int checkLast(int value, int newDigit) {
		int oldDigit = value % 10;
		if (oldDigit == newDigit) {
			return value;
		} else if (oldDigit > newDigit) {
			return checkLast(value / 10, newDigit) * 10 + oldDigit;
		}
		return value * 10 + newDigit;
	}

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
		// if(zahl < 0)zahl = 9; zahl == 0; zahl =9;

		return (original - first) <= 0 ? 9 : original - first;
	}

	public int bestimmeDieErsteZahl(int zahl) {
		do {
			if (zahl < 10)
				return zahl;
			else
				zahl = zahl / 10;
		} while (zahl > 10);
		return zahl;
	}

	public int[] separateWahrnehmungen(int zahl) {
		int zahlTemp = zahl;
		int zaehler = 0;
		do {
			zahlTemp = zahlTemp / 10;
			// System.out.println("Zahl " + zahl);
			zaehler++;
		} while (zahlTemp > 10);
		int[] temp = new int[zaehler+1];
		for (int i = 0; i <= zaehler; i++) {
			temp[i] = bestimmeDieErsteZahl(zahl);
//			System.out.println("Zahl " + zahl + " temp[i] " + temp[i]);
			zahl = checkFirst(zahl, bestimmeDieErsteZahl(zahl));
		}
		return temp;
	}

	public static void main(String[] args) {
		FeldPositionieren fp = new FeldPositionieren();
		int[] array = fp.separateWahrnehmungen(1230);
		for (int i = 0; i < array.length; i++) {
			System.out.println("array " + array[i]);
			
		}
		System.out.println("Fertig");
	}
}
