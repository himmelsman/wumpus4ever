//TODO: die Mischung von Bilder
/*
 * Nur um zu zeigen, dass ich was ändern kann.
 */
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

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
public class Wumpus_GUI extends javax.swing.JFrame
               implements WindowListener, ActionListener, KeyListener, MouseListener, Observer{
       private JMenuBar jMenuBar;
       private JSeparator jSeparator1;
       Image myImage;

       private JFrame fensterFrame = this;
       private JMenu fileMenu;
       private JMenu optionen;
       private JTextArea jTextArea1;
       private JTextArea statistik;
       private JMenu help;
       private Wumpus_Panel jPanel1;
//       private MyMouseListener jPanel11;
//       private JFrame jFrame1;
       private JScrollPane ablaufScrollPanel;
       private JScrollPane statistikScrollPanel;
       private JTextArea ablaufTextArea;
       private JTextArea statistikTextArea;
       //private BildschirmPanel bild;

       private boolean ALTPRESSED = false;

       Wumpus_Panel wumpus_panel;

       /**
        * Auto-generated main method to display this JFrame
        */
       public static void main(String[] args) {
               SwingUtilities.invokeLater(new Runnable() {
                       public void run() {
                               Wumpus_GUI inst = new Wumpus_GUI();
                               inst.setLocationRelativeTo(null);
                               inst.setVisible(true);
                       }
               });
       }

       public Wumpus_GUI() {
               super();
               initGUI();
       }

       private void initGUI() {
               try {
                       GridBagLayout thisLayout = new GridBagLayout();
                       setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                       thisLayout.rowWeights = new double[] { 0.1, 0.1, 0.1, 0.1, 0.1,
                                       0.1, 0.1, 0.1 };
                       thisLayout.rowHeights = new int[] { 7, 7, 7, 7, 7, 7, 7, 7 };
                       thisLayout.columnWeights = new double[] { 0.1, 0.1, 0.1, 0.1, 0.1 };
                       thisLayout.columnWidths = new int[] { 7, 7, 7, 7, 7 };
                       getContentPane().setLayout(thisLayout);
                       /*ablauf layout*/
                       {
                               ablaufScrollPanel = new JScrollPane();
                               getContentPane().add(ablaufScrollPanel, new GridBagConstraints(0, 0, 1, 6, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
                               ablaufScrollPanel.setPreferredSize(new java.awt.Dimension(50, 3));
                               {
                                       ablaufTextArea = new JTextArea();
                                       ablaufScrollPanel.setViewportView(ablaufTextArea);
                                       ablaufTextArea.setBorder(BorderFactory.createTitledBorder("Ablauf"));
                                       ablaufTextArea.setEnabled(false);
                                       ablaufTextArea.setPreferredSize(new java.awt.Dimension(20, 18));
                                       ablaufTextArea.setSize(120, 180);
                               }
                       }
                       {
                    	   	   //if(anzahl!=0)/*es muss die Abrfage realisiert werden */
                               jPanel1 = new Wumpus_Panel();/*Aufruf der Wumpus-Panel, also Feld mit allen was da gehoert*/
                               getContentPane().add(jPanel1, new GridBagConstraints(1, 0, 3, 6, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
                       }
                       getContentPane().setLayout(thisLayout);
                       this.setTitle("Wumpus Welt");
                       this.setPreferredSize(new java.awt.Dimension(600, 500));
                       {       /*statistik layout*/
                               statistikScrollPanel = new JScrollPane();
                               getContentPane().add(statistikScrollPanel, new GridBagConstraints(4, 0, 1, 6, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
                               statistikScrollPanel.setPreferredSize(new java.awt.Dimension(50, 3));
                               {
                               statistikTextArea = new JTextArea();
                               statistikScrollPanel.setViewportView(statistikTextArea);
                               statistikTextArea.setBorder(BorderFactory.createTitledBorder("Statistik"));
                               statistikTextArea.setEnabled(false);
                               statistikTextArea.setPreferredSize(new java.awt.Dimension(20, 18));
                               statistikTextArea.setSize(160, 180);
                               }
                       }
                       /*menu Initialisierung*/
                       {
                               jMenuBar = new JMenuBar();
                               setJMenuBar(jMenuBar);
                               {
                                       fileMenu = new JMenu();
                                       jMenuBar.add(fileMenu);
                                       fileMenu.setText("File");       {
                                               JMenuItem feld1 = new JMenuItem("Feld 4x4  (Alt+1)");
                                               fileMenu.add(feld1);
                                               feld1.addActionListener(this);
                                               JMenuItem feld2 = new JMenuItem("Feld 8x8  (Alt+2)");
                                               fileMenu.add(feld2);
                                               feld2.addActionListener(this);
                                               JMenuItem feld3 = new JMenuItem("Feld 16x16(Alt+3)");
                                               fileMenu.add(feld3);
                                               feld3.addActionListener(this);

                                               jSeparator1 = new JSeparator(); /*Trennlinie*/
                                               fileMenu.add(jSeparator1);

                                               JMenuItem exit = new JMenuItem("Exit (Alt+x)");
                                               fileMenu.add(exit);
//                                               exit.addKeyListener(this);
                                               exit.addActionListener(this);
                                       }
                                       optionen = new JMenu();
                                       jMenuBar.add(optionen);
                                       optionen.setText("Optionen");   {
                                               JMenuItem mensch = new JMenuItem("Mensch");
                                               optionen.add(mensch);
                                               JMenuItem ki_agent = new JMenuItem("KI-Agent");
                                               optionen.add(ki_agent);

                                               jSeparator1 = new JSeparator(); /*Trennlinie*/
                                               optionen.add(jSeparator1);

                                               JMenuItem geschwindigkeit = new JMenuItem("Geschwindigkeit");
                                               optionen.add(geschwindigkeit);
                                       }
                                       help = new JMenu();
                                       jMenuBar.add(help);
                                       help.setText("Help");
                                       {
                                               JMenuItem hilfe = new JMenuItem("Hilfe");
                                               help.add(hilfe);
                                               JMenuItem about = new JMenuItem("About");
                                               help.add(about);
                                       }
                               }
                       }
                       pack();
                       this.setSize(800, 600);
                       this.addKeyListener(this);//weil der soll ja für das gesamte programm darauf achten und nicht nur für den button...
                       this.addFocusListener(new FocusListener(){

						@Override
						public void focusGained(FocusEvent arg0) {
							
						}

						@Override
						public void focusLost(FocusEvent arg0) {
							fensterFrame.requestFocus();/*damit das Tastenueberwachung ueber das ganzes Program stattfindet*/
							
						}
                    	   
                       });
//                       jFrame1.addKeyListener(this);
               } catch (Exception e) {
                       e.printStackTrace();
               }
       }

       @Override
       public void windowActivated(WindowEvent we) {
               // TODO Auto-generated method stub

       }

       @Override
       public void windowClosed(WindowEvent arg0) {
               // TODO Auto-generated method stub

       }

       @Override
       public void windowClosing(WindowEvent we) {
               System.exit(0);

       }

       @Override
       public void windowDeactivated(WindowEvent arg0) {
               // TODO Auto-generated method stub

       }

       @Override
       public void windowDeiconified(WindowEvent arg0) {
               // TODO Auto-generated method stub

       }

       @Override
       public void windowIconified(WindowEvent arg0) {
               // TODO Auto-generated method stub

       }

       @Override
       public void windowOpened(WindowEvent arg0) {
               // TODO Auto-generated method stub

       }
       
       /*Initialisierung der Tasten*/
       @Override
       public void actionPerformed(ActionEvent acev) {
//    	       jPanel11 = new MyMouseListener(); 
               String actionBefehl = acev.getActionCommand();
               int grosse =-1;
               if(actionBefehl.equals("Feld 4x4  (Alt+1)")) {
                       jPanel1.grossedesFeldes(4);
//                       jPanel11.grossedesFeldes(4);//fuer MyMouslistener wird die Grosse uebergegeben
                       jPanel1.revalidate();
               }
               else if(actionBefehl.equals("Feld 8x8  (Alt+2)")){
                       jPanel1.grossedesFeldes(8);
//                       jPanel11.grossedesFeldes(8);//fuer MyMouslistener wird die Grosse uebergegeben
                       jPanel1.revalidate();
               }
               else if(actionBefehl.equals("Feld 16x16(Alt+3)")){
                       jPanel1.grossedesFeldes(16);
//                       jPanel11.grossedesFeldes(16);//fuer MyMouslistener wird die Grosse uebergegeben
                       jPanel1.revalidate();
               }
               else if(actionBefehl.equals("Exit (Alt+x)")){
                       System.exit(0);
               }

       }
       /* Realisierung der Tasten
        * Tastatur-Befehle in Menu*/
       @Override
       public void keyPressed(KeyEvent arg0) {
    	   if (arg0.getKeyCode() == KeyEvent.VK_1 && ALTPRESSED) {
    	   jPanel1.grossedesFeldes(4);
    	   }
    	   if (arg0.getKeyCode() == KeyEvent.VK_2 && ALTPRESSED) {
    	   jPanel1.grossedesFeldes(8);
    	   }
    	   if (arg0.getKeyCode() == KeyEvent.VK_3 && ALTPRESSED) {
    	   jPanel1.grossedesFeldes(16);
    	   }

    	   if (arg0.getKeyCode() == KeyEvent.VK_ALT) {
    		   ALTPRESSED = true;
    		   }
    		   if (arg0.getKeyCode() == KeyEvent.VK_X && ALTPRESSED) {
    		   System.exit(0);
    		   }
       }

       @Override
       public void keyReleased(KeyEvent arg0) {
    		   if (arg0.getKeyCode() == KeyEvent.VK_ALT) {
    		   ALTPRESSED = false;
    		   }

       }

       @Override
       public void keyTyped(KeyEvent arg0) {
               // TODO Auto-generated method stub

       }

       @Override
       public void mouseClicked(MouseEvent arg0) {
               // TODO Auto-generated method stub

       }

       @Override
       public void mouseEntered(MouseEvent arg0) {
               // TODO Auto-generated method stub

       }

       @Override
       public void mouseExited(MouseEvent arg0) {
               // TODO Auto-generated method stub

       }

       @Override
       public void mousePressed(MouseEvent klick) {
//    	   int col = myImage.columnAtPoint(klick.getPoint());
//    	      MyImage.setPressedColumn(col);
//    	      myImage.repaint();
//
//    	      System.out.println("Ouch! " + col);

       }

       @Override
       public void mouseReleased(MouseEvent arg0) {
               // TODO Auto-generated method stub

       }

       @Override
       public void update(Observable arg0, Object arg1) {
               // TODO Auto-generated method stub

       }

}