package de.wumpus.tools;

public class SchlussfolgerungsHelfer {
	
	private String nachricht;
	private int zaehler;
	
	public SchlussfolgerungsHelfer(String _nachricht, int _zaehler){
		nachricht = _nachricht;
		zaehler = _zaehler;
	}
	
	public String getNachricht(){
		return nachricht;
	}
	public int getZaehler(){
		return zaehler;
	}

	public void erhoehen(){
		zaehler++;
	}
	public void verringern(){
		zaehler--;
	}
	
	public String toString(){
		return nachricht;
	}
}
