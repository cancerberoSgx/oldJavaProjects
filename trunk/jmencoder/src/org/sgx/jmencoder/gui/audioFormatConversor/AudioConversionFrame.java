package org.sgx.jmencoder.gui.audioFormatConversor;

import javax.swing.SwingUtilities;
import java.awt.BorderLayout;

import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Dimension;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.plaf.OptionPaneUI;

import org.sgx.jmencoder.mplayer.executors.ExecutorFactory;
import org.sgx.jmencoder.mplayer.executors.MencoderExecutorImpl;
import org.sgx.utils.FSUtils;
import org.sgx.utils.Utils;
import org.sgx.utils.exec.EndExecutionInformation;
import org.sgx.utils.exec.Executor;
import org.sgx.utils.exec.ExecutorListener;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

/**
 * 
 * @author SGURIN
 *
 */
public class AudioConversionFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private static final String audioConversioScriptFilePath = "audioConversor.bat";  //  @jve:decl-index=0:

	private JPanel jContentPane = null;

	private JMenuBar jJMenuBar = null;

	private JMenu jMenu = null;

	private JMenuItem jMenuItem = null;

	private AudioConversionPanel audioConversionPanel = null;

	private JPanel jPanel = null;

	private JButton jButton = null;

	private JButton jButton1 = null;

	protected Executor executor;

	private JMenu jMenu1 = null;

	private JMenuItem jMenuItem1 = null;

	private JPanel jPanel1 = null;

	private JLabel stateLabel = null;

//	private JLabel state;
	
	private JMenuBar getJJMenuBar() {
		if (jJMenuBar == null) {
			jJMenuBar = new JMenuBar();
			jJMenuBar.add(getJMenu());
			jJMenuBar.add(getJMenu1());
		}
		return jJMenuBar;
	}

	private JMenu getJMenu() {
		if (jMenu == null) {
			jMenu = new JMenu();
			jMenu.setText("Help");
			jMenu.add(getJMenuItem());
		}
		return jMenu;
	}
	
	private JMenuItem getJMenuItem() {
		if (jMenuItem == null) {
			jMenuItem = new JMenuItem("About");
			jMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JOptionPane.showMessageDialog(AudioConversionFrame.this, "this is a 5 minits made user graphical " +
							"interface so don't complain. \nThe people who really make this possible are ffmpeg and mplayer teams\n" +
							"\nThese are free programs for the gnu/linux operating system ported to windows" +
							"\nSwitch to linux now and discover the real power of free software" +
							"\n\n sgurin (Cancerbero)");
				}
			});
		}
		return jMenuItem;
	}

	private AudioConversionPanel getAudioConversionPanel() {
		if (audioConversionPanel == null) {
			audioConversionPanel = new AudioConversionPanel();
		}
		return audioConversionPanel;
	}

	private JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new JPanel();
			jPanel.setLayout(new BoxLayout(getJPanel(), BoxLayout.X_AXIS));
			jPanel.add(getJButton(), null);
			jPanel.add(getJButton1(), null);
		}
		return jPanel;
	}

	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setText("Start");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(executor!=null)
						if(executor.getProc()!=null)
							executor.getProc().destroy();
					executor = ExecutorFactory.getInstance().getDefaultExecutor();
					
					String cmd = getCommand();
					
					try {
						MencoderExecutorImpl.execute(cmd, new ExecutorListener() {
							
							public void notifyEnd(EndExecutionInformation endExecutionInformation) {
								JOptionPane.showMessageDialog(AudioConversionFrame.this, "End of conversion.");
							}
							public void notifyError(String str) {
								stateLabel.setText("working...\t\t  ("+str+")");
							}
							public void notifyMovieHeader(String movieHeader) {
//								System.out.println(movieHeader);
							}
							public void notifyOuput(String str) {
//								System.out.println("out: "+str);
								stateLabel.setText(str);
							}							
						});
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			});
		}
		return jButton;
	}

	protected String getCommand() {		
		try {			
			List<File> files = audioConversionPanel.getSelectedInputFiles();
			String outputDir = audioConversionPanel.getSelectedOutputDir();
			String outputFormat = audioConversionPanel.getSelectedOutputFormat();
			String s ="", outputFile;
			
			for(File f : files) {
				String inAbsPath=f.getAbsolutePath(), inName=f.getName();
				if(!FSUtils.isCharset(FSUtils.iso_8859_1, inAbsPath)) {
					JOptionPane.showMessageDialog(AudioConversionFrame.this, "name not "+FSUtils.iso_8859_1);
					return null;
				}
				if(outputDir==null)					
					outputFile=FSUtils.normalizePath(FSUtils.getFileParentName(inAbsPath))+File.separator+
							FSUtils.getFileName(inName)+"."+outputFormat;
				else
					outputFile=FSUtils.normalizePath(outputDir)+File.separator+
							FSUtils.getFileName(inName)+"."+outputFormat;
				outputFile = Utils.normalizeString(outputFile);
				
				String bitrateStr = (audioConversionPanel.getSelectedBitRate()==null || audioConversionPanel.getSelectedBitRate().equals(""))?"128":audioConversionPanel.getSelectedBitRate();
				int bitrate=0;
				try {
					bitrate=new Integer(bitrateStr).intValue();
				}catch (Exception e) {
					JOptionPane.showMessageDialog(AudioConversionFrame.this, "bitrate must be an integer");
					return null;
				}
				if(bitrate<=0 || bitrate >4000) {
					JOptionPane.showMessageDialog(AudioConversionFrame.this, "invalid bitrate value. please use 0 < bitrate < 500");
					return null;
				}
				bitrate*=1000;
				
//				try {
//					System.out.println(org.sgx.utils.toIso_8859_1(f.getAbsolutePath()));
//				} catch(Exception e) {
//					e.printStackTrace();
//				}
				if(!f.exists()) {
					System.out.println("ERRRRRR");
				}
				s+=org.sgx.jmencoder.mplayer.MPlayerUtils.ffmpegExe+" -y "+
				" -i "+Utils.quotePath(f.getAbsolutePath())+ 
				" -ab "+bitrate+
				" "+Utils.quotePath(outputFile)+
				"\n";			
			}
			File script = new File(audioConversioScriptFilePath);
			FileOutputStream fos = new FileOutputStream(script);
			fos.write(s.getBytes());
			fos.close();
			return audioConversioScriptFilePath+
//				"\n del audioConversor.bat\n";
				"";
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setText("Stop");
			jButton1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(executor!=null)
						executor.getProc().destroy();
				}
			});
		}
		return jButton1;
	}

	private JMenu getJMenu1() {
		if (jMenu1 == null) {
			jMenu1 = new JMenu("Edit");
			jMenu1.add(getJMenuItem1());
		}
		return jMenu1;
	}

	private JMenuItem getJMenuItem1() {
		if (jMenuItem1 == null) {
			jMenuItem1 = new JMenuItem("Preferences");
			jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return jMenuItem1;
	}


	public AudioConversionFrame() {
		super();
		initialize();
	}

	private void initialize() {
		this.setSize(503, 404);
		this.setJMenuBar(getJJMenuBar());
		this.setContentPane(getJContentPane());
		this.setTitle("JFrame");
	}

	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BoxLayout(jContentPane, BoxLayout.Y_AXIS));
			jContentPane.add(getAudioConversionPanel());
			jContentPane.add(getJPanel());
			jContentPane.add(getJPanel1(), null);
		}
		return jContentPane;
	}

	
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			stateLabel = new JLabel();
			stateLabel.setText("JLabel");
			jPanel1 = new JPanel();
			jPanel1.setLayout(new GridBagLayout());
			jPanel1.add(stateLabel, new GridBagConstraints());
		}
		return jPanel1;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				AudioConversionFrame thisClass = new AudioConversionFrame();
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);
			}
		});
	}
	
}  //  @jve:decl-index=0:visual-constraint="10,10"
