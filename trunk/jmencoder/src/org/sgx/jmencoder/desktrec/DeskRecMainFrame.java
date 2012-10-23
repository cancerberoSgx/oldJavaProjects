package org.sgx.jmencoder.desktrec;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;

import org.sgx.swing.utils.SwingUtils;


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
public class DeskRecMainFrame extends javax.swing.JFrame {
	private JMenuBar jMenuBar1;
	private JMenuItem jMenuItem9;
	private JMenuItem jMenuItem11;
	private JMenuItem jMenuItem10;
	private JMenuItem jMenuItem8;
	private JMenuItem jMenuItem7;
	private JMenu jMenu4;
	private JMenuItem jMenuItem6;
	private JMenuItem jMenuItem5;
	private JMenuItem jMenuItem4;
	private JMenuItem jMenuItem3;
	private JMenuItem jMenuItem2;
	private JMenu jMenu3;
	private JMenuItem jMenuItem1;
	private JMenu jMenu2;
	private JMenu jMenu1;
	private DeskRecMainPanel mainPanel;


	public DeskRecMainFrame() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			
			{
				setContentPane(getMainPanel());
				
			}
			
			{
				jMenuBar1 = new JMenuBar();
				setJMenuBar(jMenuBar1);
				{
					jMenu1 = new JMenu();
					jMenuBar1.add(jMenu1);
					jMenu1.setText("File");
					{
						jMenu4 = new JMenu();
						jMenu1.add(jMenu4);
						jMenu4.setText("Export");
						{
							jMenuItem6 = new JMenuItem();
							jMenu4.add(jMenuItem6);
							jMenuItem6.setText("avi");
							jMenuItem6.setBounds(0, -19, 53, 19);
						}
						{
							jMenuItem7 = new JMenuItem();
							jMenu4.add(jMenuItem7);
							jMenuItem7.setText("flv (flash)");
						}
						{
							jMenuItem8 = new JMenuItem();
							jMenu4.add(jMenuItem8);
							jMenuItem8.setText("html with embbeded flash video");
						}
						{
							jMenuItem9 = new JMenuItem();
							jMenu4.add(jMenuItem9);
							jMenu4.add(getJMenuItem11());
							jMenuItem9.setText("pngs (each frame image)");
						}
					}
					{
						jMenuItem10 = new JMenuItem();
						jMenu1.add(jMenuItem10);
						jMenuItem10.setText("Exit");
						jMenuItem10.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								SwingUtils.closeJFrame(DeskRecMainFrame.this);
							}
						});
					}
				}
				{
					jMenu2 = new JMenu();
					jMenuBar1.add(jMenu2);
					jMenu2.setText("Edit");
					{
						jMenuItem1 = new JMenuItem();
						jMenu2.add(jMenuItem1);
						jMenuItem1.setText("Preferences...");
					}
					{
						jMenuItem4 = new JMenuItem();
						jMenu2.add(jMenuItem4);
						jMenuItem4.setText("save these preferences as...");
					}
					{
						jMenuItem5 = new JMenuItem();
						jMenu2.add(jMenuItem5);
						jMenuItem5.setText("load preferences from disk...");
					}
				}
				{
					jMenu3 = new JMenu();
					jMenuBar1.add(jMenu3);
					jMenu3.setText("Help");
					{
						jMenuItem2 = new JMenuItem();
						jMenu3.add(jMenuItem2);
						jMenuItem2.setText("User Manual");
					}
					{
						jMenuItem3 = new JMenuItem();
						jMenu3.add(jMenuItem3);
						jMenuItem3.setText("About");
					}
				}
			}
			pack();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private DeskRecMainPanel getMainPanel() {
		if (mainPanel == null)
			mainPanel = new DeskRecMainPanel();
			mainPanel.setPreferredSize(new java.awt.Dimension(408, 287));
		return mainPanel;
	}

	// test
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
	DeskRecMainFrame inst = new DeskRecMainFrame();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	
	private JMenuItem getJMenuItem11() {
		if(jMenuItem11 == null) {
			jMenuItem11 = new JMenuItem();
			jMenuItem11.setText("Animated gift");
		}
		return jMenuItem11;
	}
}
