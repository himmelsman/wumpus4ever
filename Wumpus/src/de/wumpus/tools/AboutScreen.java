package de.wumpus.tools;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * AboutScreen ist das JFrame welches über (Alt+a) oder das Menü aufgerufen wird.
 * 
 * @author Benjamin Werker
 * @author Sergey Bagautdinov
 *
 */
public class AboutScreen extends JFrame {

	JFrame main;
	ScrollText scr;
	private JScrollPane jScrollPane1;
	private JButton jButton1;
	private JTextArea aboutTextArea;
	private String aboutText = 
		"Wumpus Welt\n" +
		"Ein AI-Projekt von:\n" +
		"	Bagautdinov Sergey\n" +
		"	Naydich Igor\n" +
		"	Werker Benjamin\n\n" +
		"Im Auftrag von Prof. Dr. H. Klocke";
	public AboutScreen(JFrame _main){
		main = _main;
		initGUI();
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				main.setEnabled(true);
				scr.stop();
				dispose();
			}

		});
		setLocationRelativeTo(null);
		setVisible(true);
		scr.start();
	}
	 
	private void initGUI() {
		try {
			{
				GridBagLayout thisLayout = new GridBagLayout();
				setPreferredSize(new java.awt.Dimension(700, 500));
				setSize(new java.awt.Dimension(700, 500));
				thisLayout.rowWeights = new double[] {0.1, 0.1, 0.1, 0.01};
				thisLayout.rowHeights = new int[] {7, 7, 7, 7};
				thisLayout.columnWeights = new double[] {0.1, 0.1, 0.1, 0.1};
				thisLayout.columnWidths = new int[] {7, 7, 7, 7};
				getContentPane().setLayout(thisLayout);
				{
					jScrollPane1 = new JScrollPane();
					getContentPane().add(jScrollPane1, new GridBagConstraints(0, 0, 4, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					{
						aboutTextArea = new JTextArea();
						jScrollPane1.setViewportView(aboutTextArea);
						aboutTextArea.setText(aboutText);
						aboutTextArea.setLineWrap(true);
						aboutTextArea.setEditable(false);
					}
				}
				{
					scr = new ScrollText(getSize().width,200);
					getContentPane().add(scr, new GridBagConstraints(0, 1, 4, 2, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				}
				{
					jButton1 = new JButton();
					getContentPane().add(jButton1, new GridBagConstraints(1, 3, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					jButton1.setText("Schließen");
					jButton1.addMouseListener(new MouseListener(){
						@Override
						public void mouseClicked(MouseEvent e) {
							if(e.getClickCount() >= 1){
								main.setEnabled(true);
								dispose();
							}
						}
						
						@Override
						public void mouseEntered(MouseEvent e) {
						}

						@Override
						public void mouseExited(MouseEvent e) {
						}

						@Override
						public void mousePressed(MouseEvent e) {
						}

						@Override
						public void mouseReleased(MouseEvent e) {
						}
						
					});
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
