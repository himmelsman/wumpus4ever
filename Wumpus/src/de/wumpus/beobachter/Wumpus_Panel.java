package de.wumpus.beobachter;
 
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Observable;
import java.util.Observer;

import javax.swing.DebugGraphics;
import javax.swing.JLabel;
import javax.swing.JPanel;

import de.wumpus.beobachtet.WumpusWelt;
import de.wumpus.tools.NachrichtenObjekt;
import de.wumpus.tools.WumpusBitmapComponent;

public class Wumpus_Panel extends JPanel{
	WumpusWelt wump;

	public int width = 1;
	public int height = 1;	

	private int anzahl;
	WumpusBitmapComponent[][] array;

	String schwarz2 = "Black.JPG";
	String grau = "Gray.JPG";
	String glitter = "Glitter.JPG";
	String agent2 = "Agent.jpg";
	String gold2 = "Gold.jpg";
	String wumpus2 = "Wumpus.jpg";
	String pit2 = "Pit.jpg";
	String breeze = "Breeze.jpg";
	String smell = "Smell.jpg";
	
	public Wumpus_Panel(WumpusWelt _wump){
		super();
		setVisible(true);
		wump = _wump;
		anzahl = wump.anzahl;
		array = new WumpusBitmapComponent[anzahl][anzahl];
		System.out.println("Array = "+ array.length);
		System.out.println("Anzahl = "+ anzahl);
		for (int i = 0; i < anzahl; i++) {
			for (int j = 0; j < anzahl; j++) {
				System.out.println("Position " + i + "," + j + " Wert " + wump.weltArray[i][j]);
			}
		}
		initGUI();
		revalidate();
		repaint();
		
	}
	
	public void setzeAnzahl(){
		anzahl = wump.anzahl;
		array = new WumpusBitmapComponent[anzahl][anzahl];
		initGUI();
		revalidate();
		repaint();
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		/*
		 * Die Felder werden immer auf das maximum der verfügbaren Fläche
		 * ausgedehnt */
//		System.out.println("Anzahl in paint= "+ anzahl);
		int width = this.getWidth();
		int height = this.getHeight();
//		int width = 100;
//		int height = 100;
		if (array != null) {
			for (int i = 0; i < array.length; i++) {
				for(int j = 0; j < array.length; j++){
					if (array[i][j] != null) {
						//TODO schauen wie die width und height von GUI uebergeben werden.
						array[i][j].setImageSize(width / anzahl, height / anzahl);
//						System.out.println("in paint width / anzahl, height / anzahl " + width / anzahl +" " + height / anzahl);
					}
				}
			}
		}
	}

	private void initGUI() {
//		int x = 1, y = 1;
		this.removeAll();
		GridBagLayout thisLayout = new GridBagLayout();
		//TODO: statt gridbaglayout eine alternative suchen, welches quadratische felder ermöglicht
		this.setLayout(thisLayout);
		this.setPreferredSize(new java.awt.Dimension(100, 100));
		this.setDebugGraphicsOptions(DebugGraphics.BUFFERED_OPTION);
		this.setAutoscrolls(true);
		array = new WumpusBitmapComponent[anzahl][anzahl];
		
		System.out.println("Anzahl init= "+ anzahl);
		zeichnen();
		this.revalidate();
		this.repaint();
	}
	
	public void zeichnen(){
		for (int i = 0; i < anzahl; i++) {
			// zahlenreihe nach unten am linken rand
			this.add(new JLabel("" + (i + 1)), new GridBagConstraints(0, i + 1, 1, 1, 0.01, 0.01, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
			// zahlenreihe nach rechts am oberen rand
			this.add(new JLabel("" + (i + 1)), new GridBagConstraints(i + 1, 0, 1, 1, 0.01, 0.01, GridBagConstraints.CENTER, 0, new Insets(0, 0, 0, 0), 0, 0));
		}
		int x = 1, y = 1;
		for (int i = 0, h = 0; i < anzahl; i++) {
			for (int j = 0; j < anzahl; j++) {
				{	//bestimmeDieErsteZahl(gibmirWert[i][j]);
					if (wump.bestimmeDieErsteZahl(wump.weltArray[i][j]) == 1) {
						array[i][j] = new WumpusBitmapComponent(agent2, anzahl, j, i % anzahl);	
						System.out.println("TEST: ........................." + array[i][j].getX() + "," + array[i][j].getY());
					} else if (wump.bestimmeDieErsteZahl(wump.weltArray[i][j]) == 2) {
						array[i][j] = new WumpusBitmapComponent(gold2, anzahl, j, i % anzahl);
					}else if (wump.bestimmeDieErsteZahl(wump.weltArray[i][j]) == 3) {
						array[i][j] = new WumpusBitmapComponent(glitter, anzahl, j, i % anzahl);
					}else if (wump.bestimmeDieErsteZahl(wump.weltArray[i][j]) == 4) {
						array[i][j] = new WumpusBitmapComponent(wumpus2, anzahl, j, i % anzahl);
					}else if (wump.bestimmeDieErsteZahl(wump.weltArray[i][j]) == 5) {
						array[i][j] = new WumpusBitmapComponent(smell, anzahl, j, i % anzahl);
					}else if (wump.bestimmeDieErsteZahl(wump.weltArray[i][j]) == 6) {
						array[i][j] = new WumpusBitmapComponent(pit2, anzahl, j, i % anzahl);					
					}else if (wump.bestimmeDieErsteZahl(wump.weltArray[i][j]) == 7) {
						array[i][j] = new WumpusBitmapComponent(breeze, anzahl, j, i % anzahl);					
					} else if (wump.bestimmeDieErsteZahl(wump.weltArray[i][j]) == 9) {
						array[i][j] = new WumpusBitmapComponent(grau, anzahl, j, i % anzahl);
					}
					else array[i][j] = new WumpusBitmapComponent(schwarz2, anzahl, j, i % anzahl);
					this.add(array[i][j], new GridBagConstraints(x, y, 1, 1, 0.1, 0.1, GridBagConstraints.SOUTH, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
//					this.add(new JLabel("Test: x:" + i + " y: " + j), new GridBagConstraints(x, y, 1, 1, 0.1, 0.1, GridBagConstraints.SOUTH, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
//					array.actionPerformed();
					//array[i][j].addMouseListener(mkl);
					x++;
					h++;
				}
			}
			y++;
			x = 1;
		}
	}
	
	public String welchesBild(int y, int x){
		if (wump.bestimmeDieErsteZahl(wump.weltArray[y][x]) == 1) {
			return agent2;
		} else if (wump.bestimmeDieErsteZahl(wump.weltArray[y][x]) == 2) {
			return gold2;
		}else if (wump.bestimmeDieErsteZahl(wump.weltArray[y][x]) == 3) {
			return glitter;
		}else if (wump.bestimmeDieErsteZahl(wump.weltArray[y][x]) == 4) {
			return wumpus2;
		}else if (wump.bestimmeDieErsteZahl(wump.weltArray[y][x]) == 5) {
			return smell;
		}else if (wump.bestimmeDieErsteZahl(wump.weltArray[y][x]) == 6) {
			return pit2;
		}else if (wump.bestimmeDieErsteZahl(wump.weltArray[y][x]) == 7) {
			return breeze;
		}else if (wump.bestimmeDieErsteZahl(wump.weltArray[y][x]) == 9) {
			return grau;
		}
		return schwarz2;
	}
	
	public void wechseleZweiBilder(int alteY, int alteX, int neuX, int neuY){
//		System.out.println("Altes");
//		System.out.println("ImageX: "+ array[alteX][alteY].getX() + " ImageY: " + array[alteX][alteY].getY() + " Filename: " + array[alteX][alteY].getFileName());
//		
////		remove(array[alteX][alteY]);
//		array[alteX][alteY] = new WumpusBitmapComponent(welchesBild(alteY,alteX), anzahl, alteY, alteX);
////		add(array[alteX][alteY], new GridBagConstraints(alteY, alteX, 1, 1, 0.1, 0.1, GridBagConstraints.SOUTH, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
//		
//		System.out.println("ImageX: "+ array[alteX][alteY].getX() + " ImageY: " + array[alteX][alteY].getY() + " Filename: " + array[alteX][alteY].getFileName());
//		
//		System.out.println("Neues");
//		System.out.println("ImageX: "+ array[neuX][neuY].getX() + " ImageY: " + array[neuX][neuY].getY() + " Filename: " + array[neuX][neuY].getFileName());
//		
////		remove(array[neuX][neuY]);
//		array[neuX][neuY] = new WumpusBitmapComponent(welchesBild(neuY,neuX), anzahl, neuY, neuX);
////		add(array[neuX][neuY], new GridBagConstraints(neuY, neuX, 1, 1, 0.1, 0.1, GridBagConstraints.SOUTH, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
//		
//		System.out.println("ImageX: "+ array[neuX][neuY].getX() + " ImageY: " + array[neuX][neuY].getY() + " Filename: " + array[neuX][neuY].getFileName());
		removeAll();
		zeichnen();
		revalidate();
		repaint();
	}

	

}
