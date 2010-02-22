package de.wumpus.tools;

import java.util.Observable;
import java.util.Vector;

import de.wumpus.beobachtet.WumpusWelt;

public class SetzeDieGeschwindigkeit extends Observable implements Runnable {

	    public static int limit = 6000;

	    private int Schritzahler;
	    private int schritt;
	    private int schriteProMin;
	    private Thread thread;
	    private WumpusWelt wump;
	    private boolean keyPressed;

	    public SetzeDieGeschwindigkeit(WumpusWelt _wump, int _schriteProMin) {
	        super();
//	        steps = _steps;
	        schriteProMin = _schriteProMin;
	        Schritzahler = 0;
	        wump = _wump;
	        keyPressed = false;
	    }

	    public void setzeDieAnzahlDerSchritte(int schritt) {
	//System.out.println("");
//	        this.steps = steps;
	        Schritzahler = 0;
	    }

	    public void setzeDieGeschwindigkeit(int schriteProMin) {
	//System.out.println("");
	        this.schriteProMin = schriteProMin;
	        if (schriteProMin>0) keyPressed = true;
	        else  keyPressed = false;
	    }

	    public int gebeDieGeschwindigkeit() {
	//System.out.println("");
	        return schriteProMin;
	    }

	    public void run() {
	//System.out.println("");
	        Thread ichBinThread = Thread.currentThread();
//	        while (thread == ichBinThread) {
//	            if (schritt==0 || Schritzahler>=schritt.size() || wump==null) {
//	                thread = null;
//	                setChanged();
//	                notifyObservers(new NotifyObject(WumpusWelt.NOOP, -1, -1, "multi_steps_done"));
//	                break;
//	            }
//	            try {
//	                if (schritt.elementAt(Schritzahler)!=null) {
//	                    int bewege = ((Integer)schritt.elementAt(Schritzahler)).intValue();
//	                    wump.makeMove(bewege);
//	                }
	//else System.out.println("null step");
//	            Schritzahler++;
//	            } catch (Exception ex) {}
//	            if (schriteProMin<=0) {
//	                while (!keyPressed) {
//	                    try {
//	                        Thread.sleep(100);
//	                    } catch (InterruptedException e) {
//	                        break;
//	                    }
//	                }
//	                keyPressed = false;
//	            } else if (schriteProMin<=limit) {
//	                try {
//	                    Thread.sleep(60000L/schriteProMin);
//	                }
//	                catch (InterruptedException e) {
//	                    break;
//	                }
//	            }
//	        }
	    }

	    public void start() {
	//System.out.println("MultiStepper:start()");
	        keyPressed = false;
	        thread = new Thread(this);
	        thread.start();
	    }

	    public void pause() {
	//System.out.println("MultiStepper:pause()");
	        thread = null;
	    }

	    public void pressKey() {
	//System.out.println("MultiStepper:pressKey()");
	        keyPressed = true;
	    }
	


}
