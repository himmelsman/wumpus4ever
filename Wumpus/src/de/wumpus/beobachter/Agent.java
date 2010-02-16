package de.wumpus.beobachter;
 
import java.util.Observable;
import java.util.Observer;

import de.wumpus.beobachtet.WumpusWelt;
import de.wumpus.tools.Auswertung;
import de.wumpus.tools.Bezeichnungen;
import de.wumpus.tools.FeldPositionieren;
import de.wumpus.tools.NachrichtenObjekt;



public class Agent implements Observer {

	private int anzahl;
	WumpusWelt wump;
	Auswertung auswertung;
	Auswertung[][] arraymitWissenBasis;
	
	public Agent(WumpusWelt _wump){
		wump = _wump;
//		anzahl = wump.anzahl;
//		auswertung = new Auswertung(null, 0, null, 0, null, 0);	
//		arraymitWissenBasis = new Auswertung[anzahl][anzahl];
	}
	
	public void update(Observable obj, Object arg) {
		System.out.println("Ich bin der Agent in kl. Agent");
//		arraymitWissenBasis = new double[anzahl][anzahl];
		if (((NachrichtenObjekt) arg).information.equals(Bezeichnungen.AGENT)) {
			if(anzahl!=wump.anzahl){
				anzahl = wump.anzahl;
				arraymitWissenBasis = new Auswertung[anzahl][anzahl];
				for (int j = 0; j < anzahl; j++) {
					for (int i = 0; i < anzahl; i++) {
						arraymitWissenBasis[j][i] = new Auswertung(0,0,0);
					}
				}
			}
			System.out.println("Meine Position x " + ((NachrichtenObjekt) arg).x + " Position y: " + ((NachrichtenObjekt) arg).y);
			positionDesAgentes(((NachrichtenObjekt)arg).y, ((NachrichtenObjekt)arg).x, ((NachrichtenObjekt)arg).wahrnehmung);
		}
		
//		if (((NachrichtenObjekt) arg).information.equals(Bezeichnungen.AGENT)) {			
//			System.out.println("Meine Position in der Welt x " + ((NachrichtenObjekt) arg).x + " Position y: " + ((NachrichtenObjekt) arg).y + " Wahrnemung: " + ((NachrichtenObjekt) arg).wahrnehmung[0] + " Information: "
//					+ ((NachrichtenObjekt) arg).information);
//			System.out.println("\n Position x: " + ((NachrichtenObjekt) arg).x + " Position y: " + ((NachrichtenObjekt) arg).y + " Wahrnemung: " + ((NachrichtenObjekt) arg).wahrnehmung[0] + " Information: " + ((NachrichtenObjekt) arg).information);	
//		}
		System.out.println("Wissenbasis des Abentes");
//		for(int i = 0; i < anzahl; i++){
//			for(int j = 0; j < anzahl; j++){				
//				System.out.print(arraymitWissenBasis[i][j] + " ");				
//			}
//			System.out.println();
//		}
		ausgabe();
	}
	/*diese Methode bestimmt die Position des Agentes*/
	public void positionDesAgentes(int y, int x, int[] wahrnehmung){
		System.out.println("anzahl " + anzahl + " array.length" + arraymitWissenBasis.length);
//				if(j == y && i == x){
					System.out.println("Meine Position x " + x + " Position y: " + y);
					boolean keineWahrnehmung;
					keineWahrnehmung = true;
					for(int i= 0;i < wahrnehmung.length;i++){
						if (wahrnehmung[i] == 3) {
							setzedieWahrscheilichkeiten(y,  x, 3);
							keineWahrnehmung = false;
						}
						if (wahrnehmung[i] == 5) {
							setzedieWahrscheilichkeiten(y,  x, 5);
							keineWahrnehmung = false;
						}
						if (wahrnehmung[i] == 7) {
							setzedieWahrscheilichkeiten(y,  x, 7);
							keineWahrnehmung = false;
						}
					}
					if(keineWahrnehmung){
						setzeKeineWahnehmnung(y, x);
					}
					
					
//					arraymitWissenBasis[y][x] =	new Auswertung(aufteilungVonWahrnehmung(y, x, wahrnehmung[0]),
//														   aufteilungVonWahrnehmung(y, x, wahrnehmung[1]),
//														   aufteilungVonWahrnehmung(y, x, wahrnehmung[2]));//wie so ist hier null
//					System.out.println(arraymitWissenBasis[y][x] + " die wahrnehmung");
//				}				
//			}
//		}		
	}
	
	public void ausgabe(){
		System.out.println("-----------------------------------------------------");
		for (int j = 0; j < anzahl; j++) {
			for (int i = 0; i < anzahl; i++) {
				if (arraymitWissenBasis[j][i] != null) {
					if (arraymitWissenBasis[j][i].istWahrnehmung()) {
						System.out.print("3:" + arraymitWissenBasis[j][i].glitter + " 5:" + arraymitWissenBasis[j][i].geruch + " 7:" + arraymitWissenBasis[j][i].brise);
					} else {
						System.out.print("no Wahr");
					}
				}else {
					System.out.print("3:0 5:0 7:0");
				}
				System.out.print(" | ");
			}
			System.out.println("");
			System.out.println("-----------------------------------------------------");
		}
	}
	
	public int aufteilungVonWahrnehmung(int y, int x, int wahrnehmung){
//		int dritteZahl = 0, zweiteZahl = 0, ersteZahl = 0;
//		int agentStelle = bestimmeDieErsteZahl(wahrnehmung);
//		if(agentStelle != 0){
//			ersteZahl = bestimmeDieErsteZahl(wahrnehmung);
//			if(ersteZahl != 0){
//				zweiteZahl = bestimmeDieErsteZahl(wahrnehmung - ersteZahl*);
//				if(zweiteZahl != 0){
//					dritteZahl = bestimmeDieErsteZahl(zweiteZahl);
//				}
//			}
//		}	
		FeldPositionieren fp = new FeldPositionieren();
		int[] array = fp.separateWahrnehmungen(wahrnehmung);
		/* 1 als Agent in dem Feld 2 als Gold in dem Feld 3 als Glitter in nahligenden Felder 4 als Wumpus in dem Feld 5 als Geruch in nahligenden Felder 6 als Pit in dem Feld 7 als Brize ind nahligenden Felder 9 als graues Feld als Besucht
		 * bezeichnet
		 */
		for (int i = 0; i < array.length; i++) {
			if (array[i] == 3) {
//				setzedieWahrscheilichkeiten(y, x, 3);
				return 25;
			}
			if (array[i] == 5) {
//				setzedieWahrscheilichkeiten(y, x, 5);
				return 25;
			}
			if (array[i] == 7) {
//				setzedieWahrscheilichkeiten(y, x, 7);
				return 25;
			}
		}
		return 0;
	}
	
//	/*
//	 * Die Methode bestimmt die erste Zahl, die in der Arrayposition
//	 * abgespeichert ist. d.h. die erste Teil der Zahl. z.B. Zahl = 234 nach der
//	 * Methode Zahl = 2
//	 */
//	public int bestimmeDieErsteZahl(int zahl) {
//		do {
//			if (zahl < 10)
//				return zahl;
//			else
//				zahl = zahl / 10;
//		} while (zahl > 10);
//		return zahl;
//	}
	/*Diese Methode setzt die Wahrscheilichketiten von Gold,Wumpus,Pit in Wissenbasis des Agentes */
	public void setzedieWahrscheilichkeiten(int y, int x, int zahl){
		int moeglichkeiten = 0;
		if(y-1 >= 0){
			if(arraymitWissenBasis[y-1][x].istWahrnehmung())
				moeglichkeiten++;
		}
		if((y + 1) <anzahl){
			if(arraymitWissenBasis[y+1][x].istWahrnehmung())
				moeglichkeiten++;
		}
		if(x-1 >= 0){
			if(arraymitWissenBasis[y][x-1].istWahrnehmung())
				moeglichkeiten++;
		}
		if((x + 1) <anzahl){
			if(arraymitWissenBasis[y][x+1].istWahrnehmung())
				moeglichkeiten++;
		}
		if(y-1 >= 0){
			arraymitWissenBasis[y-1][x].aufwerten(zahl,moeglichkeiten);
		}
		if((y + 1) <anzahl){
			arraymitWissenBasis[y+1][x].aufwerten(zahl,moeglichkeiten);
		}
		if(x-1 >= 0){
			arraymitWissenBasis[y][x-1].aufwerten(zahl,moeglichkeiten);
		}
		if((x + 1) <anzahl){
			arraymitWissenBasis[y][x+1].aufwerten(zahl,moeglichkeiten);
		}
	}
	
	public void setzeKeineWahnehmnung(int y, int x){
		System.out.println("setzeKeineWahrnehmung x:" + x +" y:" + y);
		arraymitWissenBasis[y][x].setzeKeineWahrnehmung();
		if(y-1 >= 0){
			arraymitWissenBasis[y-1][x].setzeKeineWahrnehmung();
		}
		if((y + 1) <anzahl){
			arraymitWissenBasis[y+1][x].setzeKeineWahrnehmung();
		}
		if(x-1 >= 0){
			arraymitWissenBasis[y][x-1].setzeKeineWahrnehmung();
		}
		if((x + 1) <anzahl){
			arraymitWissenBasis[y][x+1].setzeKeineWahrnehmung();
		}
	}
}
