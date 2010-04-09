package de.wumpus.tools;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;

import javax.swing.JButton;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * HelpScreen ist das JFrame welches über (Alt+h) oder das Menü aufgerufen wird. Der HelpScreen gibt eine Einleitung zur Wumpus-Welt und eine Übersicht über die Regeln.
 * 
 * @author Benjamin Werker
 * @author Sergey Bagautdinov
 * 
 */

@SuppressWarnings("serial")
public class HelpScreen extends JFrame {

	JFrame main;
	private JScrollPane jScrollPane1;
	private JButton jButton1;
	private JEditorPane helpTextArea;
//	private URL helpFile = new URL("file://localhost/D:/help.html");

	public HelpScreen(JFrame _main) {
		main = _main;
		initGUI();
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				main.setEnabled(true);
				dispose();
			}

		});
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void initGUI() {
		try {
			{
				GridBagLayout thisLayout = new GridBagLayout();
				setPreferredSize(new java.awt.Dimension(700, 500));
				setSize(new java.awt.Dimension(700, 500));
				thisLayout.rowWeights = new double[] { 0.1, 0.1, 0.1, 0.01 };
				thisLayout.rowHeights = new int[] { 7, 7, 7, 7 };
				thisLayout.columnWeights = new double[] { 0.1, 0.1, 0.1, 0.1 };
				thisLayout.columnWidths = new int[] { 7, 7, 7, 7 };
				getContentPane().setLayout(thisLayout);
				{
					jScrollPane1 = new JScrollPane();
					getContentPane().add(jScrollPane1, new GridBagConstraints(0, 0, 4, 3, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					{
						helpTextArea = new JEditorPane();
						jScrollPane1.setViewportView(helpTextArea);
						//TODO: Abfrage ob aus Jar oder nicht
//						helpTextArea.setPage(ClassLoader.getSystemResource("inhalte/help.html"));
						helpTextArea.setPage(new URL("file:inhalte/help.html"));
						helpTextArea.setEditable(false);
					}
				}
				{
					jButton1 = new JButton();
					getContentPane().add(jButton1, new GridBagConstraints(1, 3, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					jButton1.setText("Schließen");
					jButton1.addMouseListener(new MouseListener() {
						@Override
						public void mouseClicked(MouseEvent e) {
							if (e.getClickCount() >= 1) {
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
