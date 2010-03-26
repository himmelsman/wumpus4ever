package de.wumpus.beobachter;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

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
public class WumpusGUI2 extends JFrame {
	private JPanel wumpuspanel;
	private JPanel bedienung;
	private JPanel jPanel1;
	private JScrollPane schlussfolgerung;
	private JPanel score;
	private JPanel ablauf;
	private JPanel wissensbasis;
	private JPanel ausgaben_bedienung;

	private void initGUI() {
		try {
			{
				GridBagLayout thisLayout = new GridBagLayout();
				this.setPreferredSize(new java.awt.Dimension(800, 600));
				thisLayout.rowWeights = new double[] {0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1};
				thisLayout.rowHeights = new int[] {7, 7, 7, 7, 7, 7, 7, 7, 7};
				thisLayout.columnWeights = new double[] {0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1};
				thisLayout.columnWidths = new int[] {7, 7, 7, 7, 7, 7, 7};
				getContentPane().setLayout(thisLayout);
				{
					wumpuspanel = new JPanel();
					getContentPane().add(wumpuspanel, new GridBagConstraints(0, 0, 4, 6, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				}
				{
					ausgaben_bedienung = new JPanel();
					GridBagLayout jPanel2Layout = new GridBagLayout();
					getContentPane().add(ausgaben_bedienung, new GridBagConstraints(4, 0, 3, 6, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					jPanel2Layout.rowWeights = new double[] {0.1, 0.1};
					jPanel2Layout.rowHeights = new int[] {7, 7};
					jPanel2Layout.columnWeights = new double[] {0.1, 0.1};
					jPanel2Layout.columnWidths = new int[] {7, 7};
					ausgaben_bedienung.setLayout(jPanel2Layout);
					{
						ablauf = new JPanel();
						GridBagLayout jPanel4Layout = new GridBagLayout();
						jPanel4Layout.columnWidths = new int[] {7, 7, 7};
						jPanel4Layout.rowHeights = new int[] {7, 7, 7, 7, 7, 7};
						jPanel4Layout.columnWeights = new double[] {0.01, 0.1, 0.01};
						jPanel4Layout.rowWeights = new double[] {0.1, 0.1, 0.1, 0.1, 0.1, 0.1};
						ausgaben_bedienung.add(ablauf, new GridBagConstraints(1, 0, 1, 2, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
						ablauf.setLayout(jPanel4Layout);
					}
					{
						score = new JPanel();
						ausgaben_bedienung.add(score, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					}
					{
						bedienung = new JPanel();
						ausgaben_bedienung.add(bedienung, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					}
				}
				{
					wissensbasis = new JPanel();
					getContentPane().add(wissensbasis, new GridBagConstraints(0, 6, 4, 3, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				}
				{
					schlussfolgerung = new JScrollPane();
					getContentPane().add(schlussfolgerung, new GridBagConstraints(4, 6, 3, 3, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					{
						jPanel1 = new JPanel();
						schlussfolgerung.setViewportView(jPanel1);
					}
				}
			}
			{
				this.setSize(800, 600);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
