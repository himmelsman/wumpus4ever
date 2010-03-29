package de.wumpus.tools;

/**
 * Ablauf ist die Hilfsklasse, der einen eigenen Typ repräsentiert.
 * 
 * @author Benjamin Werker
 * @author Sergey Bagautdinov
 * 
 */

public class Ablauf {

	public Position position;
	public String wahrnehmung;
	public String letzteBewegung;

	public Ablauf(Position _position, String _wahrnehmung, String _letzteBewegung) {
		position = _position;
		wahrnehmung = _wahrnehmung;
		letzteBewegung = _letzteBewegung;
	}

	public void setPosition(Position _position) {
		position = _position;
	}

	public void setWahrnehmung(String _wahrnehmung) {
		wahrnehmung = _wahrnehmung;
	}

	public void setLetzteBewegung(String _letzteBewegung) {
		letzteBewegung = _letzteBewegung;
	}
	
	public Position getPosition(){
		return position;
	}
	public String getWahrnehmung(){
		return wahrnehmung;
	}
	public String getLetzteBewegung(){
		return letzteBewegung;
	}

}
