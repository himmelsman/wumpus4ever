package de.wumpus.tools;

public class Auswertung {

	public int glitter, geruch, brise;

	private boolean istWahrnehmung;

	public Auswertung(int _glitter, int _geruch, int _brise) {
		glitter = _glitter;
		geruch = _geruch;
		brise = _brise;
		istWahrnehmung = true;
	}

	public boolean istWahrnehmung() {
		return istWahrnehmung;
	}
	
	public void setzeKeineWahrnehmung(){
		istWahrnehmung = false;
	}
//
//	public void aufwerten(int wahrnehmung) {// war void
//		if (wahrnehmung == 3) {
//			glitter = glitter + 25;
//		} else if (wahrnehmung == 5) {
//			geruch = geruch + 25;
//		} else if (wahrnehmung == 7) {
//			brise = brise + 25;
//		}// kein rueckgabe
//	}
	public void aufwerten(int wahrnehmung, int moeglich) {// war void
		if(moeglich == 0){
			moeglich = 1;
		}
		int prozent = 100 / moeglich;
		if (wahrnehmung == 3 && glitter == 0 || wahrnehmung == 3 && glitter < prozent) {
			glitter = prozent;
		} else if (wahrnehmung == 5 && geruch == 0|| wahrnehmung == 5 && geruch < prozent) {
			geruch = prozent;
		} else if (wahrnehmung == 7 && brise== 0|| wahrnehmung == 7 && brise < prozent) {
			brise = prozent;
		}
	}
}
