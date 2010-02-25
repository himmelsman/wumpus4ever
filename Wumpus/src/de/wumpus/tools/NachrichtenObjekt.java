package de.wumpus.tools;
 
public class NachrichtenObjekt {
	
	public int x,y;
	public int[] wahrnehmung;
	public String information;
	
	
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
}
