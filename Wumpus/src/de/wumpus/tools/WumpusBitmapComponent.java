package de.wumpus.tools;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 

public class WumpusBitmapComponent extends Canvas{
   private Image img;
   private int grosse,anzahl;
   private int x;
   private int y;
   private String fileName;

   public WumpusBitmapComponent(String fname, int anzahl, int _y, int _x){
		  fileName = fname;
//		   System.out.println("wumpusBitmapCompanent= "+ fname +" "+anzahl+" "+_x+" "+_y);
		  img = getToolkit().getImage(fname);
	      MediaTracker mt = new MediaTracker(this);
	      y = _y;
	      x = _x;
		  this.anzahl = anzahl;
	      mt.addImage(img, 0);
	      if(anzahl == 4){
	    	  grosse = 100;
	      }else if(anzahl == 8){
	    	  grosse = 50;
	      }else if(anzahl == 16){
	    	  grosse = 25;
	      }
	      try {
	         //Warten, bis das Image vollständig geladen ist,
	         //damit getWidth() und getHeight() funktionieren
	         mt.waitForAll();
//	         System.out.println(grosse);
	         img = img.getScaledInstance(grosse, grosse, Image.SCALE_DEFAULT);
//	          img.addActionListener(new ClickListener());
	      } catch (InterruptedException e) {
	    	  System.out.println("In der Konstruktor WumpusBitmapComponent Fehler");
	      }      
	   }
   private void WumpusBitmapComponent(String fname){
		  fileName = fname;
//		   System.out.println("wumpusBitmapCompanent= "+ fname +" "+anzahl+" "+_x+" "+_y);
	      MediaTracker mt = new MediaTracker(this);
	      mt.removeImage(img);
	      img = getToolkit().getImage(fname);
		  mt.addImage(img, 0);
	      if(anzahl == 4){
	    	  grosse = 100;
	      }else if(anzahl == 8){
	    	  grosse = 50;
	      }else if(anzahl == 16){
	    	  grosse = 25;
	      }
	      try {
	         //Warten, bis das Image vollständig geladen ist,
	         //damit getWidth() und getHeight() funktionieren
	         mt.waitForAll();
//	         System.out.println(grosse);
	         img = img.getScaledInstance(grosse, grosse, Image.SCALE_DEFAULT);
//	          img.addActionListener(new ClickListener());
	      } catch (InterruptedException e) {
	    	  System.out.println("In der Konstruktor WumpusBitmapComponent Fehler");
	      }      
	   }
  
   public void paint(Graphics g)
   {
      g.drawImage(img,1,1,this);
   }

   public Dimension getPreferredSize()
   {
      return new Dimension(
         img.getWidth(this),
         img.getHeight(this)
      );
   }

   public Dimension getMinimumSize()
   {
      return new Dimension(
         img.getWidth(this),
         img.getHeight(this)
      );
   }

   public void changeImage(String fName){
	   WumpusBitmapComponent(fName);
   }
   public int getImageSize(){
	   return grosse;
   }
   /*Diese Methode achtet darauf, dass die Bilder quadratisch sind*/
//   public void setImageSize(int sizeW, int sizeH){
//	   if (sizeW < sizeH)
//			img = img.getScaledInstance(sizeW, sizeW, Image.SCALE_DEFAULT);
//	   if (sizeW > sizeH)
//			img = img.getScaledInstance(sizeH, sizeH, Image.SCALE_DEFAULT);
//   }
   public void setImageSize(int sizeW, int sizeH){	   
			img = img.getScaledInstance(sizeW, sizeH, Image.SCALE_DEFAULT);	   
   }

   public String getFileName(){
	   return fileName;
   }
   public int getX(){
	   return x;
   }
   public int getY(){
	   return y;
   }	
}