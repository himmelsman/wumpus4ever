package de.wumpus.beobachter;
 
import java.util.Observable;
import java.util.Observer;

import de.wumpus.tools.Bezeichnungen;
import de.wumpus.tools.NachrichtenObjekt;



public class Agent implements Observer {

	int anzahl = 4;
	private double[][] arraymitWissenBasis = new double[anzahl][anzahl];
	
	
	public void update(Observable obj, Object arg) {
		System.out.println("Ich bin der Agent in kl. Agent");
//		arraymitWissenBasis = new double[anzahl][anzahl];
		if (((NachrichtenObjekt) arg).information.equals(Bezeichnungen.AGENT)) {
			System.out.println("Meine Position x " + ((NachrichtenObjekt) arg).x + " Position y: " + ((NachrichtenObjekt) arg).y);
			ersteAufruf(((NachrichtenObjekt)arg).y, ((NachrichtenObjekt)arg).x, ((NachrichtenObjekt)arg).wahrnehmung);
		}
		
//		if (((NachrichtenObjekt) arg).information.equals(Bezeichnungen.AGENT)) {			
//			System.out.println("Meine Position in der Welt x " + ((NachrichtenObjekt) arg).x + " Position y: " + ((NachrichtenObjekt) arg).y + " Wahrnemung: " + ((NachrichtenObjekt) arg).wahrnehmung[0] + " Information: "
//					+ ((NachrichtenObjekt) arg).information);
//			System.out.println("\n Position x: " + ((NachrichtenObjekt) arg).x + " Position y: " + ((NachrichtenObjekt) arg).y + " Wahrnemung: " + ((NachrichtenObjekt) arg).wahrnehmung[0] + " Information: " + ((NachrichtenObjekt) arg).information);	
//		}
		//TODO: die Koordinaten x und y nicht stimmen
		System.out.println("Wissenbasis des Abentes");
		for(int i = 0; i < anzahl; i++){
			for(int j = 0; j < anzahl; j++){				
				System.out.print(arraymitWissenBasis[j][i] + " ");				
			}
			System.out.println();
		}
	}
	//TODO: die Koordinaten x und y nicht stimmen
	public void ersteAufruf(int y, int x, int[] wahrnehmung){
		for(int j = 0; j < anzahl; j++){
			for(int i = 0; i < anzahl; i++){
				if(j == y && i == x){
					System.out.println("Meine Position x " + x + " Position y: " + y);
					arraymitWissenBasis[j][i] = wahrnehmung[0];//wie so ist hier null
					System.out.println(arraymitWissenBasis[i][j] + " die wahrnehmung");
				}				
			}
		}
	}
	
	

}
