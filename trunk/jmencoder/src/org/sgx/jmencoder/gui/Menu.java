package org.sgx.jmencoder.gui;


import java.awt.BorderLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

//import org.sgx.jmencoder.CP;
import org.sgx.jmencoder.ResourceManager;
import org.sgx.jmencoder.desktrec.DeskRecMainFrame;
import org.sgx.jmencoder.gui.audioFormatConversor.AudioConversionFrame;
import org.sgx.jmencoder.gui.dirtyMencoderGui.DirtySubtitlePutter;
import org.sgx.jmencoder.gui.dvdripper.DVDRipper;
import org.sgx.jmencoder.gui.multipleVideoConversor.VideoConversionFrame;
import org.sgx.jmencoder.gui.player.PlayerFrame;
import org.sgx.jmencoder.gui.videoInfo.VideoInformationFrame;
import org.sgx.jmencoder.gui.videoJoiner.VideoJoiner;
import org.sgx.jmencoder.mplayer.MPlayerUtils;
import org.sgx.jmencoder.streamplayer.gui.StreamPlayerFrame;
//import org.sgx.utils.classpath.ClasspathUtil;


import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import javax.swing.BoxLayout;

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
public class Menu extends JFrame {

	private JMenuItem newItemMenuItem_1;
	private JMenuItem newItemMenuItem;
	private JMenu helpMenu;
	private JMenuBar menuBar;
	private JPanel jPanel2;
	private JLabel jLabel2;
	private JPanel jPanel1;
	private JLabel jLabel1;
	private JButton jButton8;
	private JButton jButton7;
	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JButton jButton = null;

	private JButton jButton1 = null;

	private JButton jButton2 = null;

	private JButton jButton3 = null;

	private JButton jButton4 = null;

	private JButton jButton5 = null;

	protected VideoInformationFrame VideoInformation;

	private JButton jButton6 = null;

	/**
	 * This is the default constructor
	 */
	public Menu() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {

		this.setJMenuBar(getMainJMenuBar());
		try {
			MPlayerUtils.setupMencoder();
			pack();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setTitle("sg media tools...");

		this.setLocation(0,0);
		this.setContentPane(getJContentPane());
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			GridBagLayout jContentPaneLayout = new GridBagLayout();
			jContentPane.setLayout(jContentPaneLayout);
			jContentPaneLayout.rowWeights = new double[] {0.0, 0.0};
			jContentPaneLayout.rowHeights = new int[] {91, 17};
			jContentPaneLayout.columnWeights = new double[] {0.1};
			jContentPaneLayout.columnWidths = new int[] {7};
			jContentPane.add(getJPanel1(), new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
			jContentPane.add(getJPanel2(), new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		}
		return jContentPane;
	}

	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setText("subtitle putter");
			jButton.setToolTipText("paste subtitles to your movies with this program.");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					DirtySubtitlePutter.main(null);
				}
			});
		}
		return jButton;
	}

	/**
	 * This method initializes jButton1	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setText("audio format\n conversor");
			jButton1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					AudioConversionFrame.main(null);
				}
			});
		}
		return jButton1;
	}

	/**
	 * This method initializes jButton2	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton2() {
		if (jButton2 == null) {
			jButton2 = new JButton();
			jButton2.setText("video format conversor");
			jButton2.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					VideoConversionFrame.main(null);
				}
			});
		}
		return jButton2;
	}

	
/**
	 * This method initializes jButton3	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton3() {
		if (jButton3 == null) {
			jButton3 = new JButton();
			jButton3.setText("video joiner");
			jButton3.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					new VideoJoiner().setVisible(true);
				}
			});
		}
		return jButton3;
	}

/**
 * This method initializes jButton4	
 * 	
 * @return javax.swing.JButton	
 */
private JButton getJButton4() {
	if (jButton4 == null) {
		jButton4 = new JButton();
		jButton4.setText("media player");
		jButton4.setIcon(ResourceManager.getInstance().getIcon("play"));
		jButton4.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				new PlayerFrame().setVisible(true);
			}
		});
	}
	return jButton4;
}

/**
 * This method initializes jButton5	
 * 	
 * @return javax.swing.JButton	
 */
private JButton getJButton5() {
	if (jButton5 == null) {
		jButton5 = new JButton();
		jButton5.setText("media inspector");
		jButton5.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				JFrame f = getVideoInformation();
				f.setVisible(true);
			}
		});
	}
	return jButton5;
}

protected JFrame getVideoInformation() {
	if(VideoInformation==null) {
		VideoInformation=new VideoInformationFrame();
		VideoInformation.addWindowListener(new WindowListener() {
			public void windowActivated(WindowEvent e) {		}
			public void windowClosed(WindowEvent e) {
				
				new Menu().setVisible(true);
				VideoInformation=null;
			}
			public void windowClosing(WindowEvent e) {		}
			public void windowDeactivated(WindowEvent e) {		}
			public void windowDeiconified(WindowEvent e) {		}
			public void windowIconified(WindowEvent e) {		}
			public void windowOpened(WindowEvent e) {		}		
		});
	}
	this.setVisible(false);
	VideoInformation.setVisible(true);
	return VideoInformation;
}

/**
 * This method initializes jButton6	
 * 	
 * @return javax.swing.JButton	
 */
private JButton getJButton6() {
	if (jButton6 == null) {
		jButton6 = new JButton();
		jButton6.setText("DVD ripper");
		jButton6.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				DVDRipper f = new DVDRipper();
				f.setTitle("sgx DVD ripper");
				f.setVisible(true);
			}
		});
	}
	return jButton6;
}

public static void main(String[] args) {
	try {
        UIManager.setLookAndFeel(
            UIManager.getSystemLookAndFeelClassName());
    } 
    catch (Exception e) {}
    SwingUtilities.invokeLater(new Runnable() {
		public void run() {
			Menu menu = new Menu();
			menu.setSize(160,280);
			menu.setVisible(true);
			menu.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			
		}
	});	 
	}
	/**
	 * @return
	 */
	protected JMenuBar getMainJMenuBar() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			setJMenuBar(menuBar);
			menuBar.add(getHelpMenu());
		}
		return menuBar;
	}
	/**
	 * @return
	 */
	protected JMenu getHelpMenu() {
		if (helpMenu == null) {
			helpMenu = new JMenu();
			helpMenu.setText("Help");
			helpMenu.add(getNewItemMenuItem());
			helpMenu.add(getNewItemMenuItem_1());
		}
		return helpMenu;
	}
	/**
	 * @return
	 */
	protected JMenuItem getNewItemMenuItem() {
		if (newItemMenuItem == null) {
			newItemMenuItem = new JMenuItem();
			newItemMenuItem.setText("About");
			newItemMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JOptionPane.showMessageDialog(Menu.this, "These are my graphical interfaces to the great free \nlinux tools mplayer and ffmpeg ported to windows. \nIt is a 5 minits made user graphical " +
							"interface so don't complain. \nThe people who really make this possible are ffmpeg and mplayer teams\n" +
							"\nSwitch to linux now and discover the real power of free software" +
							"\n\n sgurin (Cancerbero)");
				}
			});
		}
		return newItemMenuItem;
	}
	/**
	 * @return
	 */
	protected JMenuItem getNewItemMenuItem_1() {
		if (newItemMenuItem_1 == null) {
			newItemMenuItem_1 = new JMenuItem();
			newItemMenuItem_1.setText("readme");
			newItemMenuItem_1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JOptionPane.showMessageDialog(Menu.this, "Qu� es cabopolonio ?\n"+
							"\n"+
							"le puse ese nombre a una colecci�n de herramientas multimedia basadas en los excelentes proyectos libres mplayer y \n"+
							"ffmpeg. Lo que yo hice fue la interface gr�fica en java-swing orientada a usuarios inexpertos en detalles de \n"+
							"multimedia (como formatos, parametros de video, etc).\n"+
							"\n"+
							"Entre las herramientas se encuentra un reproductor (Player) al cual solo basta con tirarle CUALQUIER archivo \n"+
							"reproducible (video o audio en cualquier formato conocido), con varias opciones relativas a subt�tulos (fuente, \n"+
							"color, tama�o, posici�n) , ecualizaci�n, tama�o, etc.\n"+
							"\n"+
							"LA otra herramienta notable es la llamada \"video format conversor\" al cual solo basta con arrastrarle una lista de \n"+
							"videos en cualquier formato, elegir formato de salida (dvd, vcd, divx, xvid, flv, mp4, etc), y si todo funciona bien \n"+
							"esperar a que la conversi�n termine. Adem�s existe el programa \"audio format conversor\" que convierte cualquier \n"+
							"formato conocido de audio a mp3s.\n"+
							"\n"+
							"La calidad de la interface gr�fica trata de ser minimalista y tiene varios defectos que son consecuencia de mi falta \n"+
							"de tiempo (hago esto en mi tiempo libre sin que nadie me garpe), pero con un poco de costumbre es muy f�cil de usar, \n"+
							"m�s f�cil que los programas an�logos.\n"+
							"\n"+
							"espero sea �til. Yo lo uso con �xito, ning�n reproductor o conversor que conozca me da tanta flexibilidad a no ser \n"+
							"claro el propio mplayer por consola.\n\nAutor: Sebasti�n Gurin\nLicencia: GPL");
				}
			});
		}
		return newItemMenuItem_1;
	}
	
	private JButton getJButton7() {
		if(jButton7 == null) {
			jButton7 = new JButton();
			jButton7.setText("screen recorder");
			jButton7.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					DeskRecMainFrame.main(null);
				}
			});
			jButton7.setIcon(ResourceManager.getInstance().getIcon("record"));
		}
		return jButton7;
	}
	
	private JButton getJButton8() {
		if(jButton8 == null) {
			jButton8 = new JButton();
			jButton8.setText("radio player");
			jButton8.setToolTipText("listen to your favourite online radios and tv channels, and create your own playlist");
			jButton8.setPreferredSize(new java.awt.Dimension(59, 18));
			jButton8.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					StreamPlayerFrame.main(null);
				}
			});
			jButton8.setIcon(ResourceManager.getInstance().getIcon("radio"));
			
		}
		return jButton8;
	}
	
	private JLabel getJLabel1() {
		if(jLabel1 == null) {
			jLabel1 = new JLabel();
			jLabel1.setText("nice tools:");
		}
		return jLabel1;
	}
	
	private JPanel getJPanel1() {
		if(jPanel1 == null) {
			jPanel1 = new JPanel();
			BoxLayout jPanel1Layout = new BoxLayout(jPanel1, javax.swing.BoxLayout.Y_AXIS);
			jPanel1.setLayout(jPanel1Layout);
			jPanel1.add(getJLabel1());
			jPanel1.add(getJButton8());
			jPanel1.add(getJButton4());
			jPanel1.add(getJButton1());
		}
		return jPanel1;
	}
	
	private JLabel getJLabel2() {
		if(jLabel2 == null) {
			jLabel2 = new JLabel();
			jLabel2.setText("experimental tools:");
		}
		return jLabel2;
	}
	
	private JPanel getJPanel2() {
		if(jPanel2 == null) {
			jPanel2 = new JPanel();
			BoxLayout jPanel2Layout = new BoxLayout(jPanel2, javax.swing.BoxLayout.Y_AXIS);
			jPanel2.setLayout(jPanel2Layout);
			jPanel2.add(getJLabel2());
			jPanel2.add(getJButton());
			jPanel2.add(getJButton2());
			jPanel2.add(getJButton3());
			jPanel2.add(getJButton6());
			jPanel2.add(getJButton5());
			jPanel2.add(getJButton7());
		}
		return jPanel2;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
