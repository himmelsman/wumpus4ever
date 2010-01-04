import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class FeldPositionierung {

	public int getZuffalszahl(int _anzahl) {
		int Zufallszahl;
		Zufallszahl = (int) ((Math.random()) * _anzahl + 1);
		return Zufallszahl;
	}

	public int[][] feldPositionerung(int anzahl) {
		System.out.println("Wir sind in Feld Positionierung und Anzahl ist " + anzahl);//mackaken es wird schonwieder 2 mal aufgerufen!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

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
		// * 3 als Wumpus in dem Feld
		// * 4 als Pit in dem Feld
		// * 5 als Geruch in nahligenden Felder
		// * 6 als Brize ind nahligenden Felder 
		// * 7 als Glitter in nahligenden Felder */
		
		/* als besserer vorschlag waere switsh */
		int j = 0, x, y;
		for(Iterator<Integer> it = numberSet.iterator(); it.hasNext();) {
			Integer element = it.next();
			// System.out.println(element.toString());
			if (j == 0) {
				x = element % anzahl;
				y = (element - 1) / anzahl;
				// System.out.println("x = " + x + " y = " + y);
				/* Dieses Teil ist dafür da, um die Zahlen nach eine Reiche hinzufuegen*/
				if(istarray[x][y] == 0){
					int value = 1;
					istarray[x][y] = value;
				}
				else {
					int value = 1;
					istarray[x][y] = istarray[x][y] + value;
				}
				/*Ende von dieses Teil*/
				// System.out.println("Element: "+element + " Zahl: 1"+
				// "istarray: " + istarray[element]);
			} else if (j == 1) {
				x = element % anzahl;
				y = (element - 1) / anzahl;
				// System.out.println("x = " + x + " y = " + y);
				/* Dieses Teil ist dafür da, um die Zahlen nach eine Reiche hinzufuegen*/
				if(istarray[x][y] == 0){
					int value = 2;
					istarray[x][y] = value;
				}
				else {
					int value = 2;
					istarray[x][y] = istarray[x][y] * 10 + value;
				}
				/*Ende von dieses Teil*/
				setUmfeld(x, y, 7, anzahl, istarray);
				// System.out.println("Element: "+element + " Zahl: 2" +
				// "istarray: " + istarray[element]);
			} else if (j == 2) {
				x = element % anzahl;
				y = (element - 1) / anzahl;
				// System.out.println("x = " + x + " y = " + y);
				/* Dieses Teil ist dafür da, um die Zahlen nach eine Reiche hinzufuegen*/
				if(istarray[x][y] == 0){
					int value = 3;
					istarray[x][y] = value;
				}
				else {
					int value = 3;
					istarray[x][y] = istarray[x][y] * 10 + value;
				}
				/*Ende von dieses Teil*/
				setUmfeld(x, y, 5, anzahl, istarray);
				// System.out.println("Element: "+element + " Zahl: 3"+
				// "istarray: " + istarray[element]);
			}
			/* als else machen und heir mehrere loescher einbauen */
			else {
				x = element % anzahl;
				y = (element - 1) / anzahl;
				// System.out.println("x = " + x + " y = " + y);
				/* Dieses Teil ist dafür da, um die Zahlen nach eine Reiche hinzufuegen*/
				if(istarray[x][y] == 0){
					int value = 4;
					istarray[x][y] = value;
				}
				else {
					int value = 4;
					istarray[x][y] = istarray[x][y] * 10 + value;
				}
				/*Ende von dieses Teil*/
				setUmfeld(x, y, 6, anzahl, istarray);
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
	
	/*die Methode setzt um den Wumpus und Pits nahligende felder mit Geruch
	 *und Brize. Man muss achten damit die Felder nicht im außerem Rand plaziert
	 *werden. */
	void setUmfeld(int x, int y, int value, int anzahl, int[][] istarray){
		if (x != 0)
			if (istarray[x - 1][y] == 0)
				istarray[x - 1][y] = value;		
			else
				istarray[x - 1][y] = istarray[x - 1][y] * 10 + value;
		if (x != anzahl - 1) {
			if (istarray[x + 1][y] == 0)
				istarray[x + 1][y] = value;
			else
				istarray[x + 1][y] = istarray[x + 1][y] * 10 + value; 
		}
		if (y != 0){
			if (istarray[x][y - 1] == 0)
				istarray[x][y - 1] = value;
			else
				istarray[x][y - 1] = istarray[x][y - 1] * 10 + value;
		}
		if (y != anzahl - 1){
			if (istarray[x][y + 1] == 0)
				istarray[x][y + 1] = value;
			else
				istarray[x][y + 1] = istarray[x][y + 1] * 10 + value;
		}
	}
//	public static void main(String[] args) {
//		FeldPositionierung fp = new FeldPositionierung();
//		fp.feldPositionerung(4);
//	}
}
