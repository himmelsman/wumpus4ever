package de.wumpus.beobachter;
 
import java.util.Observable;
import java.util.Observer;

import de.wumpus.tools.Bezeichnungen;
import de.wumpus.tools.NachrichtenObjekt;

public class Agent implements Observer {

	public void update(Observable obj, Object arg) {
		if (((NachrichtenObjekt) arg).information.equals(Bezeichnungen.AGENT)) {
			System.out.println("Ich bin der Agent");
//			System.out.println("Meine Position in der Welt x " + ((NachrichtenObjekt) arg).x + " Position y: " + ((NachrichtenObjekt) arg).y + " Wahrnemung: " + ((NachrichtenObjekt) arg).wahrnehmung[0] + " Information: "
//					+ ((NachrichtenObjekt) arg).information);
//			System.out.println("\n Position x: " + ((NachrichtenObjekt) arg).x + " Position y: " + ((NachrichtenObjekt) arg).y + " Wahrnemung: " + ((NachrichtenObjekt) arg).wahrnehmung[0] + " Information: " + ((NachrichtenObjekt) arg).information);
		}
	}

}
