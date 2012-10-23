package org.sgx.jmencoder.gui.videoJoiner;


import javax.swing.SwingUtilities;
import java.awt.BorderLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import org.sgx.jmencoder.mplayer.MPlayerUtils;
import org.sgx.jmencoder.mplayer.executors.ExecutorFactory;
import org.sgx.jmencoder.mplayer.executors.MencoderExecutorImpl;
import org.sgx.swing.gui.editors.file.FileAction;
import org.sgx.swing.gui.editors.file.FileListSelector;
import org.sgx.swing.gui.editors.file.FileListSelector2;
import org.sgx.utils.FSUtils;
import org.sgx.utils.exec.EndExecutionInformation;
import org.sgx.utils.exec.Executor;
import org.sgx.utils.exec.ExecutorListener;


import java.awt.Dimension;

public class VideoJoiner extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private FileListSelector fileSelector = null;
	private JPanel jPanel = null;
	private JTextField output = null;
	private JButton startbt = null;
	private JMenuBar jJMenuBar = null;
	private JMenu jMenu = null;
	private JMenuItem jMenuItem = null;
	private JButton jButton = null;

	Executor executor;
	boolean stopped;
	
	public VideoJoiner() {
		super();
		initialize();
	}

	private void initialize() {
		this.setSize(311, 303);
		this.setJMenuBar(getJJMenuBar());
		this.setContentPane(getJContentPane());
		this.setTitle("video joiner");
	}

	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BoxLayout(getJContentPane(), BoxLayout.Y_AXIS));
			jContentPane.add(getFileSelector(), null);
			jContentPane.add(getJPanel(), null);
			jContentPane.add(getOutput(), null);
		}
		return jContentPane;
	}
	
	private FileListSelector getFileSelector() {
		if (fileSelector == null) {
			fileSelector = new FileListSelector();
			
		}
		return fileSelector;
	}

	private JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new JPanel();
			jPanel.setLayout(new GridBagLayout());
			jPanel.add(getStartbt(), new GridBagConstraints());
			jPanel.add(getJButton(), new GridBagConstraints());
		}
		return jPanel;
	}

	private JTextField getOutput() {
		if (output == null) {
			output = new JTextField();
		}
		return output;
	}
	
	private JButton getStartbt() {
		if (startbt == null) {
			startbt = new JButton();
			startbt.setText("start");
			startbt.addActionListener(new java.awt.event.ActionListener() {
				

				public void actionPerformed(java.awt.event.ActionEvent e) {
					stopped=false;
					if(executor!=null)
						if(executor.getProc()!=null)
							executor.getProc().destroy();					
										
					executor = ExecutorFactory.getDefaultExecutor();
					
					String cmd = getCommand();
				if(cmd!=null)
					try {
						JOptionPane.showMessageDialog(VideoJoiner.this, "all videos will be joined into one file\nin the " +
								"same directory and with the same name \n" +
								"of the first video in the list.");
												
						executor=MencoderExecutorImpl.execute(cmd, new ExecutorListener() {
							public void notifyEnd(EndExecutionInformation endExecutionInformation) {
								String msg =" video concatenation ready !";
								if(stopped) 
									msg= "work canceled by user";
								
								JOptionPane.showMessageDialog(VideoJoiner.this, msg);
							}
							public void notifyError(String str) {
								System.out.println(str);
								output.setText(str);
							}
							public void notifyMovieHeader(String movieHeader) {
//								System.out.println(movieHeader);
							}
							public void notifyOuput(String str) {
								output.setText(str);
							}							
						});
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				
				}
			});
		}
		return startbt;
	}

	protected String getCommand() {
		List<File> l = fileSelector.getList();
		if(l==null||l.isEmpty()) {		
			JOptionPane.showMessageDialog(VideoJoiner.this, "add some videos first!");
			return null;
		}
		String s = "";
		for(File f : l) {
			s+=" \""+f.getAbsolutePath()+"\" ";
		}
		String outputFiles = "";
		String aName=l.get(0).getAbsolutePath();
		return MPlayerUtils.mencoderExe+" -oac copy -ovc copy -idx -forceidx " +
				"-o \""+//FSUtils.getFileParentName(aName)+File.separator+
				FSUtils.getFileName(aName)+"_joined_.avi\" " +
				" "+s;
	}


	private JMenuBar getJJMenuBar() {
		if (jJMenuBar == null) {
			jJMenuBar = new JMenuBar();
			jJMenuBar.add(getJMenu());
		}
		return jJMenuBar;
	}

	private JMenu getJMenu() {
		if (jMenu == null) {
			jMenu = new JMenu("help");
			jMenu.add(getJMenuItem());
		}
		return jMenu;
	}

	private JMenuItem getJMenuItem() {
		if (jMenuItem == null) {
			jMenuItem = new JMenuItem("little help");
			jMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JOptionPane.showMessageDialog(VideoJoiner.this, "\nvideo joiner\n\n" +
							"a small visual tool for video concatenation \n\n" +
							"drop your videos on the file selection list, \n" +
							"arrange them and press start to build the big video \n" +
							"that is the result of inpuit video concatenation. \n\n" +
							"\t@gui author: sgurin");
				}
			});
		}
		return jMenuItem;
	}

	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setText("stop");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(JOptionPane.showConfirmDialog(VideoJoiner.this, "are you sure you want to cancel current work?")==JOptionPane.YES_OPTION) {
						stopped=true;
						if(executor!=null)
							if(executor.getProc()!=null) 
								executor.getProc().destroy();						
					}
				}
			});
		}
		return jButton;
	}




	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				VideoJoiner thisClass = new VideoJoiner();
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);
			}
		});
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"
