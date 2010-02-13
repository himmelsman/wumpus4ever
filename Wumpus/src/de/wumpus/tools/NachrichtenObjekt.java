package de.wumpus.tools;
 
public class NachrichtenObjekt {
	
	public int x,y;
	public int[] wahrnehmung;
	public String information;
	
	//Konstruktor uebergibt die x,y-koordinate, wahrnehmung,info
	public NachrichtenObjekt(int _x, int _y, int[] _wahrnehmung, String _info){
		x = _x;
		y = _y;
		wahrnehmung = _wahrnehmung;
		information  = _info;
	}
}
