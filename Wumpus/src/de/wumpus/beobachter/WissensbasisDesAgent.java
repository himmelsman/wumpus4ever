package de.wumpus.beobachter;
import java.awt.GridLayout;
import javax.swing.JPanel;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class WissensbasisDesAgent extends JPanel {
	
	private void initGUI() {
		try {
			GridLayout thisLayout = new GridLayout(4, 4);
			thisLayout.setColumns(4);
			thisLayout.setHgap(5);
			thisLayout.setVgap(5);
			thisLayout.setRows(4);
			setLayout(thisLayout);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
