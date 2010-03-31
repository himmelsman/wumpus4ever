package de.wumpus.tools;

/**
 * Feld ist die Hilfsklasse, der einen eigenen Typ repräsentiert.
 * 
 * @author Benjamin Werker
 * @author Sergey Bagautdinov
 * 
 */

public class Feld {

	public boolean besucht, versteckt, gefahr;
	public boolean wandL, wandR, wandO, wandU;
	public boolean  wumpus, fallgrube, gold;
	public boolean brise, geruch, glitter;
	public int cont;
	public int x, y;

	public Feld() {
		versteckt = true;
		besucht = false;
		gefahr = true;
		wumpus = fallgrube = gold = false;
		brise = geruch = glitter = false;
		wandR = wandL= wandO= wandU = false;
		y = x = -1;
	}

	/**
	 * Ein einfacher Konstruktor
	 * @param y Y-Koordinate des Feldes
	 * @param x X-Koordinate des Feldes
	 */
	public Feld(int y, int x) {
		this();
		this.y = y;
		this.x = x;
	}
	
	public Feld clone(){
		Feld temp = new Feld();
		if(besucht){
			temp.besucht = true;	
		}
		if(!versteckt){
			temp.versteckt = false;	
		}
		if(!gefahr){
			temp.gefahr = false;	
		}
		if(wumpus){
			temp.wumpus = true;	
		}
		if(fallgrube){
			temp.fallgrube = true;	
		}
		if(gold){
			temp.gold = true;	
		}
		if(brise){
			temp.brise = true;	
		}
		if(geruch){
			temp.geruch = true;	
		}
		if(glitter){
			temp.glitter = true;	
		}
		if(wandO){
			temp.wandO = true;	
		}
		if(wandU){
			temp.wandU = true;	
		}
		if(wandL){
			temp.wandL = true;	
		}
		if(wandR){
			temp.wandR = true;	
		}
		temp.y = y;
		temp.x = x;
		return temp;
	}
}

//
// public void aufwerten(int wahrnehmung) {// war void
// if (wahrnehmung == 3) {
// glitter = glitter + 25;
// } else if (wahrnehmung == 5) {
// geruch = geruch + 25;
// } else if (wahrnehmung == 7) {
// brise = brise + 25;
// }// kein rueckgabe
// }
// public void aufwerten(int wahrnehmung, int moeglich) {// war void
// if(moeglich == 0){
// moeglich = 1;
// }
// int prozent = 100 / moeglich;
// if (wahrnehmung == 3 && glitter == 0 || wahrnehmung == 3 && glitter < prozent) {
// glitter = prozent;
// } else if (wahrnehmung == 5 && geruch == 0|| wahrnehmung == 5 && geruch < prozent) {
// geruch = prozent;
// } else if (wahrnehmung == 7 && brise== 0|| wahrnehmung == 7 && brise < prozent) {
// brise = prozent;
// }
// }

