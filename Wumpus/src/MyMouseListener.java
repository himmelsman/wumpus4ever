import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class MyMouseListener implements MouseListener{
	
	boolean stimmt;	
	int anzahl = 0;
	int x,y,i,j;
	int[][] agentesArray;
	Wumpus_Panel takeAgentKoordinaten;// = new Wumpus_Panel();
	
	
	/* die Methode gibt die anzahl des Feldes(icon) */
	public void grossedesFeldes(int size) {//wieso kann ich einfach die methode nicht aufrufen? von Wumpus_Panel??
		anzahl = size;	
		System.out.println("anzahl " + anzahl);
		agentesArray = new int[anzahl][anzahl];
		for (int i = 0; i < anzahl; i++) {
			for (int j = 0; j < anzahl; j++) {
				agentesArray[i][j] = 0;
			}
		}
	}
	
	public void istZustandDesAgentes(int x, int y){	
		this.x = x;
		this.y = y;
//		System.out.println("agentesArray ist " + anzahl);
		agentesArray[x][y] = 1;
		System.out.println("Agent befindet sich auf x " + x + " y " + y);		
	}
	
//	public int istZustandDesAgentesX(int x){
//		System.out.println(" x " + x );
//		return x;
//	}
//	
//	public int istZustandDesAgentesy(int y){
//		System.out.println(" y " + y );
//		return y;
//	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/*die Methode wechselt das Bild, wo man per Maus klickt, andert.
	 * 1. Es muss abgefragt werden, wo sich Agent befindet, damit man nicht auf belibiges 
	 * Feld anklickt.
	 * 2. angeklickte Bild soll geaendert werden
	 * 3. Feld,wo der Agent war, soll geaendert werden
	 * */
	@Override
	public void mousePressed(MouseEvent e) {
		WumpusBitmapComponent angecklickteImage =  (WumpusBitmapComponent) e.getSource();//gibt Objekt zuruck
//		takeAgentKoordinaten = new Wumpus_Panel();			
//		agentesArray[x][y] = 1;
		System.out.println("Agent x " + x + " y " + y);
		if(x == angecklickteImage.getX() && y == angecklickteImage.getY())
			System.out.println("wahlen Sie bitte anderes Feld");
		
//		int xx = takeAgentKoordinaten.setAgentKoordinatenX(i);
//		int yy = takeAgentKoordinaten.setAgentKoordinatenY(j);
//		Wumpus_Panel.setAgentKoordinatenY(j);//FRAGEN OB SO WAS GIBT????????????????????????????????????????
//		System.out.println("Agent x " + (xx + 1) + " y " + (yy +1));
//		System.out.println("Agent x y " + agentesArray[x][y]);
    	System.out.println("wechselbareImage " + (angecklickteImage.getX() + 1) + " " + (angecklickteImage.getY() + 1));
    	
//		do{
//			int i = takeAgentKoordinaten.setAgentKoordinatenX(x);
//			int j = takeAgentKoordinaten.setAgentKoordinatenY(y);
//			System.out.println("koordinate x " + i + "y " + j);
//			stimmt = false;
//    	}while(stimmt);
		
	}

	/*Die Methode soll die Wechselung der Agentes darstellen,
	 * d.h. die aktuelle die angecklickte Position wird uebergeben.
	 * Man soll die Koordinaten prueffen und wechseln. 
	 * Es soll nicht vergessen werden, dass die angeklickte Image +1 zu Koordinaten des Agentes
	 * oder existiert schon ein offenes Pfad zwischen Koordinaten des Agentes und angeklickten Image. 
	 * Falle:
	 * 1. x==x und y+1
	 * 2. x==x und y-1
	 * 3. x-1 und y==y
	 * 4. x+1 und y==y			
	 * */
	public void wechseleImage(int alte_x, int alte_y, int neu_x, int neu_y){
		if(alte_x == neu_x && alte_y + 1 == neu_y)
			agentesArray[alte_x][neu_y] = 1;
			//
		if(alte_x == neu_x && alte_y - 1 == neu_y)
			agentesArray[alte_x][neu_y] = 1;
		if(alte_x - 1 == neu_x && alte_y == neu_y)
			agentesArray[neu_x][alte_y] = 1;
		if(alte_x + 1 == neu_x && alte_y == neu_y)
			agentesArray[neu_x][alte_y] = 1;
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	


}
