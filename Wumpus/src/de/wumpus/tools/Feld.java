package de.wumpus.tools;

public class Feld {

	public boolean besucht, versteckt, gefahr;
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
		y = x = -1;
	}

	/**
	 * 
	 * @param y Y-Koordinate des Feldes
	 * @param x X-Koordinate des Feldes
	 */
	public Feld(int y, int x) {
		this();
		this.y = y;
		this.x = x;
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

