package de.wumpus.tools;

/**
 * NachrichtenObjekt ist die Hilfsklasse, der einen eigenen Typ repr�sentiert und wird f�r Nachrichten versendet genutzt.
 * 
 * @author Benjamin Werker
 * @author Sergey Bagautdinov
 * 
 */

public class NachrichtenObjekt {
	
	public int x,y;
	public int[] wahrnehmung;
	public String information;
	public String nachricht;
	public Feld[][] wissensbasis;
	
	
	/**
	 * 
	 * @param y Y-Koordinate des Feldes
	 * @param x X-Koordinate des Feldes
	 * @param wahrnehmung Die Wahrnehmung in Feld
	 * @param _info An wem soll die Information geschickt werden
	 */ 
	public NachrichtenObjekt(int _y, int _x, int[] _wahrnehmung, String _info){
		y = _y;
		x = _x;
		wahrnehmung = _wahrnehmung;
		information  = _info;
	}
	public NachrichtenObjekt(String _info, String _nachricht){
		information = _info;
		nachricht = _nachricht;
	}
	
	public void setzteNachricht(String _nachricht){
		nachricht = _nachricht;
	}
	
	public void setzeWissensbasis(Feld[][] _wissensbasis){
		wissensbasis = _wissensbasis;
	}
}
