package org.sgx.jmencoder.gui.multipleVideoConversor;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.sgx.jmencoder.gui.panels.SubtitlePanel;
import org.sgx.jmencoder.mplayer.Format;
import org.sgx.jmencoder.mplayer.executors.ExecutorFactory;
import org.sgx.jmencoder.mplayer.executors.MencoderExecutorImpl;
import org.sgx.jmencoder.mplayer.videoInfoDetector.MediaInfo;
import org.sgx.jmencoder.options.SubtitleOption;
import org.sgx.swing.utils.SwingUtils;
import org.sgx.utils.FSUtils;
import org.sgx.utils.Utils;
import org.sgx.utils.exec.EndExecutionInformation;
import org.sgx.utils.exec.Executor;
import org.sgx.utils.exec.ExecutorListener;


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
/**
 * 
 * @author SGURIN
 *
 */
public class VideoConversionFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	//TODO: make this better
	private static String audioConversioScriptFilePath = "audioConversor"+/*+new Date().getTime()+*/(Utils.isWindows()?".bat":".sh");//".bat";  //  @jve:decl-index=0:
	
	private static final String ffmpegExe = "ffmpeg"+File.separator+"ffmpeg.exe";  //  @jve:decl-index=0:

	private JPanel jContentPane = null;

	private JMenuBar jJMenuBar = null;

	private JMenu jMenu = null;

	private JMenuItem jMenuItem = null;

	private VideoConversionPanel multipleMovieConversionPanel = null;

	private JPanel jPanel = null;

	private JButton jButton = null;

	private JButton jButton1 = null;

	protected Executor executor;

	private JMenu jMenu1 = null;

	private JMenuItem jMenuItem1 = null;

	private JPanel jPanel1 = null;

	private JLabel stateEntry = null;

	private JCheckBox subtitleItem;

	protected SubtitlePanel subPanel;

	protected JFrame subFrame;



	public VideoConversionFrame() {
		super();
		initialize();
	}

	private void initialize() {
		this.setSize(503, 404);
		this.setJMenuBar(getJJMenuBar());
		this.setTitle("multiple movie conversion tool");
		this.setContentPane(getJContentPane());
	}

	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BoxLayout(jContentPane, BoxLayout.Y_AXIS));
			jContentPane.add(getMultipleMovieConversionPanel());
			jContentPane.add(getJPanel());
			jContentPane.add(getJPanel1(), null);
		}
		return jContentPane;
	}

	
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
					JOptionPane.showMessageDialog(VideoConversionFrame.this, "this is a 5 minits made user graphical " +
							"interface so don't complain. \nThe people who really make this possible are ffmpeg and mplayer teams\n" +
							"\nThese are free programs for the gnu/linux operating system ported to windows" +
							"\nSwitch to linux now and discover the real power of free software" +
							"\n\n sgurin (Cancerbero)");
				}
			});
		}
		return jMenuItem;
	}

	private VideoConversionPanel getMultipleMovieConversionPanel() {
		if (multipleMovieConversionPanel == null) {
			multipleMovieConversionPanel = new VideoConversionPanel();
		}
		return multipleMovieConversionPanel;
	}

	private JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new JPanel();
			jPanel.setLayout(new BoxLayout(getJPanel(), BoxLayout.X_AXIS));
			jPanel.add(getJButton(), null);
			jPanel.add(getSubtitleItem(), null);
			jPanel.add(getJButton1(), null);
		}
		return jPanel;
	}

	private JCheckBox getSubtitleItem() {
		subtitleItem = new JCheckBox("paste subtitles?", false);
		subtitleItem.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				if(subtitleItem.isSelected()) {
					if(subFrame==null) {
						subPanel = new SubtitlePanel();
						subFrame = SwingUtils.showInFrame(subPanel);
						subFrame.setSize(700,500);
					}
					subFrame.setVisible(true);
				}
				else {
					if(subFrame!=null)
						subFrame.setVisible(false);
				}
			}
			
		});
		return subtitleItem;
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
					System.out.println("executing conversor cmd: "+cmd);
					try {
						executor=MencoderExecutorImpl.execute(cmd, new ExecutorListener() {
							private int i=0;
							private boolean mePase;
							public void notifyEnd(EndExecutionInformation endExecutionInformation) {		
										JOptionPane.showMessageDialog(VideoConversionFrame.this, "end of the metamorphosis!. enjoy your new video");
							}
							public void notifyError(String str) {
								System.out.println("ERR:"+str);
//								if(i>10) {
//									stateEntry.setText(str);
//									i=0;
//								}
//								i++;
							}
							public void notifyMovieHeader(String movieHeader) {
//								System.out.println(movieHeader);
							}
							public void notifyOuput(String str) {
								mePase=i>100||mePase;
								if(!mePase)
									System.out.println("out: "+str);
								if(i>100) {
									stateEntry.setText(str);
									i=0;
								}
								i++;
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
			List<File> files = multipleMovieConversionPanel.getSelectedInputFiles();
			String outputDir = multipleMovieConversionPanel.getSelectedOutputDir();
			String outputFormat = multipleMovieConversionPanel.getSelectedOutputFormat();
			String ext = Format.getExtensionFor(outputFormat);
			String s ="", outputFile;
			
			boolean pasteSubs = subPanel!=null;
			
			stateEntry.setText("inspecting input videos...");
			
			List<File>subFiles = null;
			SubtitleOption subOp = null;
			if(pasteSubs) {
				subFiles=subPanel.getSubFileList().getList();
				subOp=subPanel.getSubtitleOptionEditor().getCurrentValue();
				if(subFiles.size()!=files.size()) {
					JOptionPane.showMessageDialog(VideoConversionFrame.this, "subtitles and video file amounts must be iqueal!");
					return "";
				}
			}
			int i = 0;
			for(File f : files) {			
				File subf = null;
				if(pasteSubs) {
					subf=subFiles.get(i);
				}
				i++;
				if(outputDir==null)					
					outputFile=FSUtils.normalizePath(FSUtils.getFileParentName(f.getAbsolutePath()))+File.separator+
							FSUtils.getFileName(f.getName())+"__"+outputFormat+"__."+ext;
				else
					outputFile=FSUtils.normalizePath(outputDir)+File.separator+
							FSUtils.getFileName(f.getName())+"__"+outputFormat+"__."+ext;
				outputFile = Utils.normalizeString(outputFile);
				
				MediaInfo info = MediaInfo.getVideoInfo(f);				
				
				try {
					if(pasteSubs) {
						subOp.setSubtitleFile(subf);
						s+=Format.mencoderCommandFor(f.getAbsolutePath(), outputFile, info, outputFormat, subOp);
					}
					else
						s+=Format.mencoderCommandFor(f.getAbsolutePath(), outputFile, info, outputFormat, null);
				}
				catch (Exception e) {
					JOptionPane.showMessageDialog(VideoConversionFrame.this, "not implemented yet. please select another format");
					return "";
				}
				s+="\n\n";
			}
			File script = new File(audioConversioScriptFilePath);
			FileOutputStream fos = new FileOutputStream(script);
			fos.write(s.getBytes());
			fos.close();
			if(Utils.isWindows()){ //windows
				return audioConversioScriptFilePath;
			}
			else {
				return "sh "+audioConversioScriptFilePath;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
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

	/**
	 * This method initializes jPanel1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			stateEntry = new JLabel();
			stateEntry.setText("state....");
			jPanel1 = new JPanel();
			jPanel1.setLayout(new GridBagLayout());
			jPanel1.add(stateEntry, new GridBagConstraints());
		}
		return jPanel1;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				VideoConversionFrame thisClass = new VideoConversionFrame();
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);
			}
		});
	}
	
}  //  @jve:decl-index=0:visual-constraint="10,10"
