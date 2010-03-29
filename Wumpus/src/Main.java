import de.wumpus.beobachter.Agent;
import de.wumpus.beobachter.WumpusGUI;
import de.wumpus.beobachter.Wumpus_Panel;
import de.wumpus.beobachtet.WumpusWelt;

/**
 * Main ist die Hauptklasse, die alles aufruft.
 * 
 * @author Benjamin Werker
 * @author Sergey Bagautdinov
 * 
 */


class Main{
	public static void main(String args[]){
		WumpusWelt wumpusWeltObserved = new WumpusWelt();//beobachbares Objekt/ die Welt
		Wumpus_Panel panel = new Wumpus_Panel(wumpusWeltObserved);
		WumpusGUI observing = new WumpusGUI(panel, wumpusWeltObserved);//Beobachter
		observing.setLocationRelativeTo(null);
		observing.setVisible(true);
		wumpusWeltObserved.addObserver(panel);
		Agent agent = new Agent(wumpusWeltObserved);//Beobachter		
		wumpusWeltObserved.addObserver(observing);//add Beobachter WumpusGUI
		wumpusWeltObserved.addObserver(agent);//add Beobachter Agent 
		

//		observed.counter(10);
//		observed.positiondesAgentes();
//		observed.test(1, 3, 7, "Ich weiss nix");
		
//		SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                    WumpusGUI inst = new WumpusGUI(panel);
//                    inst.setLocationRelativeTo(null);
//                    inst.setVisible(true);
//            }
//    });
	}
}