package de.wumpus.tools;

import java.awt.*;
import java.net.URL;

/**
 * Diese Klasse dient zum erstellen eines Bildes innerhalb eines Canvas, damit es als Componente verwendet werden kann.
 * 
 * @author Benjamin Werker
 * @author Sergey Bagautdinov
 * 
 */

@SuppressWarnings("serial")
public class WumpusBitmapComponent extends Canvas {
	private Image img;
	private int imageWidth;
	private int imageHeight;
	private String fileName;
	/**
	 * Der Konstruktor lädt ein Image <b>fName</b> und skalliert dieses dann auf <b>height</b> und <b>width</b>.
	 * 
	 * @param fname  Dateinamen
	 * @param height Höhe
	 * @param width  Breite
	 */
	public WumpusBitmapComponent(String fname, int height, int width) {
		fileName = fname;
		imageHeight = height;
		imageWidth = width;
		//TODO: Abfrage ob aus Jar oder nicht
//		img = getToolkit().getImage(ClassLoader.getSystemResource(fname));
		
		img = getToolkit().getImage(fname);
		MediaTracker mt = new MediaTracker(this);
		mt.addImage(img, 0);
		try {
			// Warten, bis das Image vollständig geladen ist,
			// damit getWidth() und getHeight() funktionieren
			mt.waitForAll();
			// System.out.println(grosse);
			img = img.getScaledInstance(width, height, Image.SCALE_DEFAULT);
			// img.addActionListener(new ClickListener());
		} catch (InterruptedException e) {
			System.out.println("In der Konstruktor WumpusBitmapComponent Fehler");
		}
	}
	
	/**
	 *  Zeichnet das Bild.
	 */
	public void paint(Graphics g) {
		g.drawImage(img, 0, 0, this);
	}
	
	/**
	 * Diese Methode skaliert das Bild falls einer der neuen Werte nicht mit den bisherigen Werten des Bildes übereinstimmt.
	 * 
	 * @param width   Neue Breite mit der verglichen wird
	 * @param height  Neue Höhe mit der verglichen wird
	 */
	public void resizeImage(int width, int height) {
		if (imageWidth != width || imageHeight != height) {
//			System.out.println("imageWidth:" + imageWidth + " imageHeight:" + imageHeight + " width:" + width + " height:" + height);
			imageWidth = width;
			imageHeight = height;
			//TODO: Abfrage ob aus Jar oder nicht
//			img = getToolkit().getImage(ClassLoader.getSystemResource(fileName));
			img = getToolkit().getImage(fileName);
			MediaTracker mt = new MediaTracker(this);
			mt.addImage(img, 0);
			try {
				// Warten, bis das Image vollständig geladen ist,
				// damit getWidth() und getHeight() funktionieren
				mt.waitForAll();
				// System.out.println(grosse);
				img = img.getScaledInstance(width, height, Image.SCALE_DEFAULT);
				// img.addActionListener(new ClickListener());
			} catch (InterruptedException e) {
				System.out.println("In der Konstruktor WumpusBitmapComponent Fehler");
			}
			
		}
	}
	
	/**
	 * Gibt den für das Image verwendete Dateinamen zurück
	 * 
	 * @return String mit Namen der Imagedatei
	 */
	public String getFileName() {
		return fileName;
	}
}