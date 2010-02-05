package de.wumpus.tools;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.Border;

public class WumpusFieldImage extends Image  {

	private int x;
	private int y;
	private int width;
	private int height;
	
	
	/*durch überschreiben des Konstuktors, kann man die koordinaten zeigen(reinschreiben*/

	public WumpusFieldImage(Image schwarz2, int x, int y){	
		super();
		this.x = x;
		this.y = y;
	}
	
	public void setKoordinates(int _x, int _y) {
		y = _y;
	}

	public int getXKoordinate() {
		return x;
	}

	public int getYKoordinate() {
		return y;
	}

	@Override
	public Graphics getGraphics() {
		
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getHeight(ImageObserver observer) {
		return 0;
	}

	@Override
	public Object getProperty(String name, ImageObserver observer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ImageProducer getSource() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getWidth(ImageObserver observer) {
		// TODO Auto-generated method stub
		return 0;
	}
}
