package org.sgx.jmencoder.gui;


import java.awt.BorderLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JButton;

//import org.sgx.jmencoder.CP;
import org.sgx.jmencoder.gui.audioFormatConversor.AudioConversionFrame;
import org.sgx.jmencoder.gui.dirtyMencoderGui.DirtySubtitlePutter;
import org.sgx.jmencoder.gui.dvdripper.DVDRipper;
import org.sgx.jmencoder.gui.multipleVideoConversor.VideoConversionFrame;
import org.sgx.jmencoder.gui.player.PlayerFrame;
import org.sgx.jmencoder.gui.videoInfo.VideoInformationFrame;
import org.sgx.jmencoder.gui.videoJoiner.VideoJoiner;
import org.sgx.jmencoder.mplayer.MPlayerUtils;
//import org.sgx.utils.classpath.ClasspathUtil;


import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

public class CopyOfMenu extends JFrame {

	private JMenuItem newItemMenuItem_1;
	private JMenuItem newItemMenuItem;
	private JMenu helpMenu;
	private JMenuBar menuBar;
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
	public CopyOfMenu() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(573, 281);
		this.setContentPane(getJContentPane());
		this.setJMenuBar(getMainJMenuBar());
		try {
			MPlayerUtils.setupMencoder();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setTitle("...sgurin multimedia tools...");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJButton(), null);
			jContentPane.add(getJButton1(), null);
			jContentPane.add(getJButton2(), null);
			jContentPane.add(getJButton3(), null);
			jContentPane.add(getJButton4(), null);
			jContentPane.add(getJButton5(), null);
			jContentPane.add(getJButton6(), null);
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
			jButton.setBounds(new Rectangle(19, 47, 175, 44));
			jButton.setText("run subtitle putter");
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
			jButton1.setBounds(new Rectangle(316, 93, 239, 32));
			jButton1.setText("run audio format\n conversor");
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
			jButton2.setBounds(new Rectangle(10, 97, 244, 48));
			jButton2.setText("run video format conversor");
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
			jButton3.setBounds(new Rectangle(208, 49, 130, 36));
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
		jButton4.setBounds(new Rectangle(369, 15, 188, 60));
		jButton4.setText("Player");
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
		jButton5.setBounds(new Rectangle(277, 138, 182, 45));
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
				
				new CopyOfMenu().setVisible(true);
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
		jButton6.setBounds(new Rectangle(20, 155, 203, 46));
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
//	try {
//		ClasspathUtil.getInstance().load(CP.PROJECT_ID);
//	} catch (IOException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
	new CopyOfMenu().setVisible(true);
//	 javax.swing.SwingUtilities.invokeLater(new Runnable() {
//         public void run() {
//         	final Menu f = new Menu();
//         	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         	f.addWindowListener(new WindowListener() {
//					public void windowActivated(WindowEvent e) {
//					}
//					public void windowClosed(WindowEvent e) {
//						
//					}
//					public void windowClosing(WindowEvent e) {
//					}
//					public void windowDeactivated(WindowEvent e) {
//					}
//					public void windowDeiconified(WindowEvent e) {
//					}
//					public void windowIconified(WindowEvent e) {
//					}
//					public void windowOpened(WindowEvent e) {
//					}
//         		
//         	});
//     		f.setVisible(true);
//         }
//     });
	 
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
					JOptionPane.showMessageDialog(CopyOfMenu.this, "These are my graphical interfaces to the great free \nlinux tools mplayer and ffmpeg ported to windows. \nIt is a 5 minits made user graphical " +
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
					JOptionPane.showMessageDialog(CopyOfMenu.this, "Qu� es cabopolonio ?\n"+
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
	/**
	 * @return
	 */
	/**
	 * @return
	 */
}  //  @jve:decl-index=0:visual-constraint="10,10"
