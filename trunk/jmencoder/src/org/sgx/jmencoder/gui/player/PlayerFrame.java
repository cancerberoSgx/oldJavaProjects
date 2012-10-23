package org.sgx.jmencoder.gui.player;

import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;


import java.awt.Dimension;
import java.io.File;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import org.sgx.jmencoder.gui.multipleVideoConversor.VideoConversionFrame;
import org.sgx.utils.FSUtils;

public class PlayerFrame extends JFrame {

	private JMenuItem newItemMenuItem_1;
	private JMenuItem newItemMenuItem;
	private static final long serialVersionUID = 1L;

	private PlaybackControlsPanel jContentPane = null;

	private JMenuBar jJMenuBar = null;

	private JMenu file = null;

	/**
	 * This method initializes jJMenuBar	
	 * 	
	 * @return javax.swing.JMenuBar	
	 */
	private JMenuBar getJJMenuBar() {
		if (jJMenuBar == null) {
			jJMenuBar = new JMenuBar();
			jJMenuBar.add(getFile());
		}
		return jJMenuBar;
	}

	/**
	 * This method initializes file	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getFile() {
		if (file == null) {
			file = new JMenu();
			file.setText("Help");
			file.add(getNewItemMenuItem());
			file.add(getNewItemMenuItem_1());
		}
		return file;
	}

	public static void main(final String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				PlayerFrame thisClass = new PlayerFrame();
//				thisClass.reproduceFiles(FSUtils.pathToFiles(args));
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);
			}
		});
	}

	public void reproduceFiles(File[] fs) {
		jContentPane.doStop();
		jContentPane.playListClear();
		jContentPane.playListAdd(fs);
		jContentPane.doPlay();
	}

	public PlayerFrame() {
		super();
		initialize();
	}

	private void initialize() {
		this.setSize(500, 550);
		this.setJMenuBar(getJJMenuBar());
		this.setContentPane(getJContentPane());
		this.setTitle("JFrame");
	}


	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new PlaybackControlsPanel();
		}
		return jContentPane;
	}
	/**
	 * @return
	 */
	protected JMenuItem getNewItemMenuItem() {
		if (newItemMenuItem == null) {
			newItemMenuItem = new JMenuItem();
			newItemMenuItem.setText("User Manual");
		}
		return newItemMenuItem;
	}
	/**
	 * @return
	 */
	protected JMenuItem getNewItemMenuItem_1() {
		if (newItemMenuItem_1 == null) {
			newItemMenuItem_1 = new JMenuItem();
			newItemMenuItem_1.setText("About");
			newItemMenuItem_1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JOptionPane.showMessageDialog(PlayerFrame.this, "this is a 5 minits made user graphical " +
							"interface with the great mplayer so don't complain. \nThe people who really make this possible are ffmpeg and mplayer teams\n" +
							"\nThese are free programs for the gnu/linux operating system ported to windows" +
							"\nSwitch to linux now and discover the real power of free software" +
							"\n\n sgurin (Cancerbero)");
				}
			});
		}
		return newItemMenuItem_1;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
