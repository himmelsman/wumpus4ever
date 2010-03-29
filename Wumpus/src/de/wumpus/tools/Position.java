package de.wumpus.tools;

/**
 * Position ist die Hilfsklasse, der einen eigenen Typ repräsentiert.
 * 
 * @author Benjamin Werker
 * @author Sergey Bagautdinov
 * 
 */

public class Position {
	public int y, x;

	public Position(int _y, int _x) {
		y = _y;
		x = _x;
	}
	
	public String toString(){
		return "(" + y + "|" + x + ")";
	}
}
