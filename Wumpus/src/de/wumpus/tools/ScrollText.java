package de.wumpus.tools;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ScrollText extends JPanel implements Runnable {
	private int         width, height, off;
	private boolean     running = false;
	private Thread      ticker;
	private Image       offscreenImage;
	private Graphics    offscr;
	private Scroll      scr;
	private Rectangle   win;
	private Dimension dim;
	
	public ScrollText(int _width, int _height){
		super();
		width = _width;
		height = _height;
	}
	
	public void paint (Graphics g) {
		if (offscr == null) {
			offscreenImage = createImage(width, height);
			offscr = offscreenImage.getGraphics();
		}
		offscr.setColor(Color.white);
		offscr.fillRect(0, 0, width, height);
		if (scr != null) {
			scr.draw(offscr, win, off);
			g.drawImage(offscreenImage, 0, 0, null);
		}
	}
	
	public void update (Graphics g) {
		paint(g);
	}
	
	public void run () {
		Vector<String> credits = new Vector<String>();
		{
			int i = 0;
			//TODO: Die Danksagungen vervollständigen bzw. an anderer Stelle erzeugen und nur referenzieren.
			String[] line = new String[]{"Special Thanks To:", "","Andreas Holste", "", "Für kreative Ideen am Rande des Wahnsinns.","Alex Maier", "", "Für die Optimisierung der Source-Code.", "", "Max und Moritz", "", "Für das erwärmen meiner Füße an kalten Tagen."};
			while (i<line.length)
				credits.addElement(line[i++]);
		}
		scr = new Scroll(credits, "Helvetica", Font.BOLD, 16, Color.black,
		                 getGraphics());
		dim = scr.size();
		win = new Rectangle(0, 0, width, height);
		off = height;
		while (running) {
			repaint();
			if (off-- < -dim.height)
				off = height;
			try {
				ticker.sleep(500 / 20);
			} catch (InterruptedException e) { ; }
		}
	}

	public synchronized void start () {
		if (ticker == null  ||  !ticker.isAlive()) {
			running = true;
			ticker = new Thread(this);
			ticker.setPriority(Thread.MIN_PRIORITY + 1);
			ticker.start();
		}
	}
	
	public synchronized void stop () {
		running = false;
	}
}