package de.wumpus.tools;

/**
 * EinSchrittZürueck ist die Hilfsklasse, der einen eigenen Typ repräsentiert.
 * 
 * @author Benjamin Werker
 * @author Sergey Bagautdinov
 * 
 */

public class EinSchrittZurueck {
	
	String bewegung;
	Feld[][] arrayMitCopyWissenbasis;
	int[][] weltCopy;
	
	public EinSchrittZurueck(String _bewegung, Feld[][] copy, int[][] welt){
		bewegung = _bewegung;
		arrayMitCopyWissenbasis = copy;
		weltCopy = welt;
	}
	
	public Feld[][] holeFeld(){
		return arrayMitCopyWissenbasis;
	}
	
	public String holeBewegung(){
		return bewegung;
	}
	
	public int[][] holeWelt(){
		return weltCopy;
	}
}
