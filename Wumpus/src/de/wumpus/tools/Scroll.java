package de.wumpus.tools;

import java.awt.*;
import java.util.*;

/**
 * Scroll ist die Hilfsklasse, die ermöglicht Text in Laufe der Zeit scrollbar darzustellen.
 * 
 * @author Benjamin Werker
 * @author Sergey Bagautdinov
 * 
 */

class Scroll {
	private Font        scrollFont;
	private Color       clr;
	private FontMetrics fm;
	private Vector     text;
	private int         lines, ascent, height, leading, spacing;
	
	Scroll (Vector text, String font, int fAttr, int pSize, Color clr, Graphics g) {
		scrollFont = new Font(font, fAttr, pSize);
		this.clr = clr;
		fm = g.getFontMetrics(scrollFont);
		ascent = fm.getAscent();
		height = fm.getHeight();
		leading = fm.getLeading();
		spacing = height + leading;
		this.text = text;
		lines = text.size();
	}
	
	public Dimension size () {
		int width = 0;
		for (int ii = 0; ii < lines; ii++) {
			int nw = fm.stringWidth((String) text.elementAt(ii));
			width = nw > width ? nw : width;
		}
		return new Dimension(width, spacing * lines);
	}
	
	public void draw (Graphics g, Rectangle dst, int yOff) {
		Graphics gc = g.create();
		gc.clipRect(dst.x, dst.y, dst.width, dst.height);
		gc.setFont(scrollFont);
		for (int ii = 0; ii < lines; ii++) {
			int lTop = yOff + ii * spacing;
			if ((lTop + spacing) < dst.y  ||  lTop > (dst.y + dst.height))
				continue;
			String cLIne = (String) text.elementAt(ii);
			int sWid = fm.stringWidth(cLIne);
			int xOff = (dst.width - sWid) / 2;
			gc.setColor(clr);
			gc.drawString(cLIne, xOff, lTop + ascent);
		}
	}
}

