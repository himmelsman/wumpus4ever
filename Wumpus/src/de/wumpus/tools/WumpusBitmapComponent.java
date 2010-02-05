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

//   public WumpusBitmapComponent(String fname)  {
   // public WechselBild(String welchesBild)
   public WumpusBitmapComponent(String fname, int anzahl, int _x, int _y){
	  fileName = fname;
//	   System.out.println("wumpusBitmapCompanent= "+ fname +" "+anzahl+" "+_x+" "+_y);
	  img = getToolkit().getImage(fname);
      MediaTracker mt = new MediaTracker(this);
      x = _x;
      y = _y;
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
//         System.out.println(grosse);
         img = img.getScaledInstance(grosse, grosse, Image.SCALE_DEFAULT);
//          img.addActionListener(new ClickListener());
      } catch (InterruptedException e) {
    	  System.out.println("In der Konstruktor WumpusBitmapComponent Fehler");
      }      
   }

//   public void wechseleImage(int alte_x, int alte_y, int neu_x, int neu_y){	      
//	      MediaTracker mt = new MediaTracker(this);	      	  
//	      mt.addImage(img, 0);	      
//	      try {
//	         //Warten, bis das Image vollständig geladen ist,
//	         //damit getWidth() und getHeight() funktionieren
//	         mt.waitForAll();
//	         img = img.getScaledInstance(grosse, grosse, Image.SCALE_DEFAULT);
//	      } catch (InterruptedException e) {
//	    	  System.out.println("In der Methode wechselImage Fehler");
//	      }
//	      
//	   }
   
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
   
   public int getImageSize(){
	   return grosse;
   }
   public void setImageSize(int sizeW, int sizeH){
	   if (sizeW < sizeH)
			img = img.getScaledInstance(sizeW, sizeW, Image.SCALE_DEFAULT);
	   if (sizeW > sizeH)
			img = img.getScaledInstance(sizeH, sizeH, Image.SCALE_DEFAULT);
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
   
   
//	private class ClickListener implements ActionListener {
//        public void actionPerformed(ActionEvent e) {
//        	WumpusBitmapComponent wechselbareImage =  (WumpusBitmapComponent) e.getSource();
//        	System.out.println("wechselbareImage" + wechselbareImage);
//            wechselbareImage.setVisible(false);
////            String name = wechselbareImage.getText();
////            change(Integer.parseInt(name));
//        }
//    }

//    public void change(int num) {
//        int i = 0, j = 0;
//        for (int k = 0; k < 4; k++) {
//            for (int l = 0; l < 4; l++) {
//                if (numbers[k][l] == num) {
//                    i = k;
//                    j = l;
//                }
//            }
//        }
//        if (i > 0) {
//            if (numbers[i - 1][j] == 0) {
//                numbers[i - 1][j] = num;
//                numbers[i][j] = 0;
//            }
//        }
//        if (i < 3) {
//            if (numbers[i + 1][j] == 0) {
//                numbers[i + 1][j] = num;
//                numbers[i][j] = 0;
//            }
//        }
//        if (j > 0) {
//            if (numbers[i][j - 1] == 0) {
//                numbers[i][j - 1] = num;
//                numbers[i][j] = 0;
//            }
//        }
//        if (j < 3) {
//            if (numbers[i][j + 1] == 0) {
//                numbers[i][j + 1] = num;
//                numbers[i][j] = 0;
//            }
//        }
                
    

//	@Override
//	public void actionPerformed(ActionEvent e) {
//		array[][] = new WumpusBitmapComponent(agent2, this, this, this);
//		repaint();	
//  }
	
}