import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.DebugGraphics;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class Wumpus_Panel extends javax.swing.JPanel{

	private static final long serialVersionUID = 8781968597206423734L;
	
	// TODO: Umschreiben der eindimensionalen Arrays auf zweidimensionale Arrays wechseln
	
	public int width;
	public int height;
	Graphics o;
	int x_koordinate;
	int y_koordinate;	
	
	MyMouseListener mkl = new MyMouseListener();
	private int anzahl = 0;
	int[][] gibmirWert;// = new int[anzahl];
	WumpusBitmapComponent[][] array;
	MyMouseListener forAgent;
	boolean schonBenutzt = true;
	
	FeldPositionierung positioniere = new FeldPositionierung();

	String schwarz2 = "Black.JPG";
	String grau = "Gray.JPG";
	String glitter = "Glitter.JPG";
	String agent2 = "Agent.jpg";
	String gold2 = "Gold.jpg";
	String wumpus2 = "Wumpus.jpg";
	String pit2 = "Pit.jpg";
	String breeze = "Breeze.jpg";
	String smell = "Smell.jpg";

	public Wumpus_Panel() {
		super();
		initGUI();// Frage ob es wirklich augefuehr sein soll
	}

	/* die Methode gibt die anzahl des Feldes(icon) */
	public void grossedesFeldes(int size) {
		anzahl = size;
		mkl.grossedesFeldes(anzahl);
		initGUI();
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		/*
		 * Die Felder werden immer auf das maximum der verfügbaren Fläche
		 * ausgedehnt */
		int width = this.getWidth();
		int height = this.getHeight();
		if (array != null) {
			for (int i = 0; i < array.length; i++) {
				for(int j = 0; j < array.length; j++){
					if (array[i][j] != null) {
						array[i][j].setImageSize(width / anzahl, height / anzahl);
					}
				}
			}
		}
	}

	private void initGUI() {
		int x = 1, y = 1;
		this.removeAll();
		GridBagLayout thisLayout = new GridBagLayout();
		this.setLayout(thisLayout);
		this.setPreferredSize(new java.awt.Dimension(98, 77));
		this.setDebugGraphicsOptions(DebugGraphics.BUFFERED_OPTION);
		this.setAutoscrolls(true);
		array = new WumpusBitmapComponent[anzahl][anzahl];
		gibmirWert = positioniere.feldPositionerung(anzahl);		
		
		// /*initialisierung der array
		// * 1 als Agend in dem Feld
		// * 2 als Gold in dem Feld
		// * 3 als Glitter in nahligenden Felder
		// * 4 als Wumpus in dem Feld
		// * 5 als Geruch in nahligenden Felder
		// * 6 als Pit in dem Feld		
		// * 7 als Brize ind nahligenden Felder  */
		
		for(int i = 0;i<anzahl;i++){
			//zahlenreihe nach unten am linken rand
			this.add(new JLabel(""+(i+1)),new GridBagConstraints(0, i+1, 1, 1, 0.01, 0.01, GridBagConstraints.CENTER,GridBagConstraints.BOTH , new Insets(0, 0, 0, 0), 0, 0));
			//zahlenreihe nach rechts am oberen rand
			this.add(new JLabel(""+(i+1)),new GridBagConstraints(i+1, 0, 1, 1, 0.01, 0.01, GridBagConstraints.CENTER, 0, new Insets(0, 0, 0, 0), 0, 0));
		}
		for (int i = 0, h = 0; i < anzahl; i++) {
			for (int j = 0; j < anzahl; j++) {
				{
					//bestimmeDieErsteZahl(gibmirWert[i][j]);
					if (bestimmeDieErsteZahl(gibmirWert[i][j]) == 1) {
						array[i][j] = new WumpusBitmapComponent(agent2, anzahl, j, i % anzahl);
						mkl.istZustandDesAgentes(i, j);
//						mkl.istZustandDesAgentesX(i);//Es funktioneiert nur beim ersten mal
//						mkl.istZustandDesAgentesX(j);//Es funktioneiert nur beim ersten mal
//						setAgentKoordinatenX(i);
//						setAgentKoordinatenY(j);
//						int xx = setAgentKoordinatenX(i);
//						int yy = setAgentKoordinatenY(j);
//						System.out.println("Agent x " + (xx + 1) + " y " + (yy +1));
					} else if (bestimmeDieErsteZahl(gibmirWert[i][j]) == 2) {
						array[i][j] = new WumpusBitmapComponent(gold2, anzahl, j, i % anzahl);
					}else if (bestimmeDieErsteZahl(gibmirWert[i][j]) == 3) {
						array[i][j] = new WumpusBitmapComponent(glitter, anzahl, j, i % anzahl);
					}else if (bestimmeDieErsteZahl(gibmirWert[i][j]) == 4) {
						array[i][j] = new WumpusBitmapComponent(wumpus2, anzahl, j, i % anzahl);
					}else if (bestimmeDieErsteZahl(gibmirWert[i][j]) == 5) {
						array[i][j] = new WumpusBitmapComponent(smell, anzahl, j, i % anzahl);
					}else if (bestimmeDieErsteZahl(gibmirWert[i][j]) == 6) {
						array[i][j] = new WumpusBitmapComponent(pit2, anzahl, j, i % anzahl);					
					}else if (bestimmeDieErsteZahl(gibmirWert[i][j]) == 7) {
						array[i][j] = new WumpusBitmapComponent(breeze, anzahl, j, i % anzahl);
					
					}
					else
						array[i][j] = new WumpusBitmapComponent(schwarz2, anzahl, j, i % anzahl);
					this.add(array[i][j], new GridBagConstraints(x, y, 1, 1, 0.1, 0.1, GridBagConstraints.SOUTH, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
//					array.actionPerformed();
					array[i][j].addMouseListener(mkl);
					x++;
					h++;
				}
			}
			y++;
			x = 1;
		}
		this.revalidate();
		this.repaint();
	}
		
	
	
//	private class ClickListener implements ActionListener {
//        public void actionPerformed(ActionEvent e) {
//        	WumpusBitmapComponent wechselbareImage =  (WumpusBitmapComponent) e.getSource();
//        	System.out.println("wechselbareImage" + wechselbareImage);
//            wechselbareImage.setVisible(false);
////            String name = wechselbareImage.getText();
////            change(Integer.parseInt(name));
//        }
//    }
	
	/*die Methode wechselt das Bild, wo man per Maus klickt, andert.
	 * 1. Es muss abgefragt werden, wo sich Agent befindet, damit man nicht auf belibiges 
	 * Feld anklickt.
	 * 2. angeklickte Bild soll geaendert werden
	 * 3. Feld,wo der Agent war, soll geaendert werden
	 * */
	public boolean changeImage(int alt_x, int alt_y, int neu_x, int neu_y){
		//TODO: Der Agent vorne abgeschnitten werdenund wieder vorne drangestellt werden. zB.15
		//TODO: Bilder wechseln
		array[alt_x][alt_y] = new WumpusBitmapComponent(grau, anzahl,  alt_y, alt_x);
		array[neu_x][neu_y] = new WumpusBitmapComponent(agent2, anzahl,  neu_y, neu_x);
		return true;
	}
	/*Die Methode bestimmt die erste Zahl.
	 * d.h. die erste Teil des Zahl.
	 * z.B. zahl = 234 nach der Methode zahl = 2 
	 * MAN KANN NOCH SORTIERUNG EINBAUEN!!!!!!!!!!!!!!
	 * */
	//TODO: 
	public int bestimmeDieErsteZahl(int zahl){		
		do{
			if(zahl < 10)
				return zahl;
			else 
				 zahl = zahl / 10;
		}while(zahl < 10);
		return zahl;
	}
	/*die Methode uebergibt die X-Koordinate des Agentes
	 * */
	public int setAgentKoordinatenX(int x){
		x_koordinate = x;		
	return x;
	}
	
	/*die Methode uebergibt die Y-Koordinate des Agentes
	 * */
	public int setAgentKoordinatenY(int y){
		y_koordinate = y;		
	return y;
	}
}