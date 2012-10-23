package org.sgx.jmencoder.gui.dvdripper;

import java.awt.GridBagLayout;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.sgx.jmencoder.mplayer.MPlayerUtils;
import org.sgx.jmencoder.mplayer.executors.MencoderExecutorImpl;
import org.sgx.swing.gui.editors.file.FileSelector;
import org.sgx.utils.exec.EndExecutionInformation;
import org.sgx.utils.exec.ExecutorListener;

import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import javax.swing.JComboBox;

public class DVDRipperPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final Object[] DVDRIP_SUPP_OUTPUT_FORMATS = {"XVID"};
	private static final String SCRIPTFILE = "dvdRip.bat";  //  @jve:decl-index=0:
	private JPanel fileSelector = null;
	private JPanel jPanel = null;
	private JLabel status = null;
	private FileSelector fileIn = null;
	private JButton jButton = null;
	private JButton jButton1 = null;
	private JButton jButton2 = null;
	private FileSelector outputDir = null;
	private JPanel jPanel1 = null;
	private JPanel jPanel2 = null;
	private JComboBox format = null;
	protected MencoderExecutorImpl executor;
	/**
	 * This is the default constructor
	 */
	public DVDRipperPanel() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		status = new JLabel();
		status.setText("JLabel");
		this.setSize(300, 200);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(getFileSelector(), null);
		this.add(getJPanel(), null);
		this.add(status, null);
		this.add(getJPanel1(), null);
	}

	private JPanel getFileSelector() {
		if (fileSelector == null) {
			fileSelector = new JPanel();
			fileSelector.setLayout(new BoxLayout(getFileSelector(), BoxLayout.Y_AXIS));
			fileSelector.add(getFileIn(), null);
			fileSelector.add(getOutputDir(), null);
		}
		return fileSelector;
	}

	private JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new JPanel();
			jPanel.setLayout(new FlowLayout());
			jPanel.add(getJButton(), null);
			jPanel.add(getJButton1(), null);
			jPanel.add(getJButton2(), null);
		}
		return jPanel;
	}

	private FileSelector getFileIn() {
		if (fileIn == null) {
			fileIn = new FileSelector();
		}
		return fileIn;
	}

	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setText("start");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					
					executor = new MencoderExecutorImpl();
					try {
						String cmd = getCommand();
						executor.execute(cmd, new ExecutorListener() {
							private int i=0;

							public void notifyEnd(EndExecutionInformation endExecutionInformation) {
								JOptionPane.showMessageDialog(DVDRipperPanel.this, "end of ripping");
								status.setText("end");
							}
							public void notifyError(String str) {
								status.setText(str);
								System.out.println("EEEEE : "+str);
							}
							public void notifyMovieHeader(String movieHeader) {						}

							public void notifyOuput(String str) {
								if(i>100) {
									i=0;
									status.setText(str);
								}
								i++;
//								System.out.println(str);
							}
							
						});
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
		}
		return jButton;
	}

	protected String getCommand() throws IOException {
		File in = new File(getFileIn().getSelection());
		File out = new File(System.getProperty("user.home")+File.separator+"dvdrip_out_"+new Date().getTime()+".avi");
		File tmp = new File(System.getProperty("user.home")+File.separator+"dvdrip_out_"+new Date().getTime()+"_tmp.avi");
		
		String s = "";
		
		if(!(in.exists()&&in.isDirectory())) 
			return "";
		s+=MPlayerUtils.mencoderExe+" dvd://1  -dvd-device \""+in.getAbsolutePath()+"\" -ovc lavc -lavcopts vcodec=mpeg4:vpass=1 -oac pcm -o \""+tmp.getAbsolutePath()+"\" -vf spp,scale=640:480 \n"+
		MPlayerUtils.mencoderExe+" dvd://1  -dvd-device \""+in.getAbsolutePath()+
		"\" -ovc lavc -lavcopts vcodec=mpeg4:mbd=2:trell:vpass=2 -oac pcm -o \""+out.getAbsolutePath()+"\"  -vf spp,scale=640:480 \n"+
		
		"del \""+tmp.getAbsolutePath()+"\" \n";
		

		FileOutputStream fos = new FileOutputStream(new File(SCRIPTFILE));
		fos.write(s.getBytes());
		fos.close();
//		mencoder dvd://2 -ovc lavc -lavcopts vcodec=mpeg4:vpass=1 -oac copy -o /dev/null
//			mencoder dvd://2 -ovc lavc -lavcopts vcodec=mpeg4:mbd=2:trell:vpass=2 -oac copy -o output.avi
		
		
		
		return SCRIPTFILE;
	}

	/**
	 * This method initializes jButton1	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setText("stop");
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
			jButton2.setText("exit");
		}
		return jButton2;
	}

	/**
	 * This method initializes outputDir	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private FileSelector getOutputDir() {
		if (outputDir == null) {
			outputDir = new FileSelector();
		}
		return outputDir;
	}

	/**
	 * This method initializes jPanel1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jPanel1 = new JPanel();
			jPanel1.setLayout(new FlowLayout());
			jPanel1.add(getJPanel2(), null);
		}
		return jPanel1;
	}

	/**
	 * This method initializes jPanel2	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel2() {
		if (jPanel2 == null) {
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints.weightx = 1.0;
			jPanel2 = new JPanel();
			jPanel2.setLayout(new GridBagLayout());
			jPanel2.add(getFormat(), gridBagConstraints);
		}
		return jPanel2;
	}

	/**
	 * This method initializes format	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getFormat() {
		if (format == null) {
			format = new JComboBox(new DefaultComboBoxModel(DVDRIP_SUPP_OUTPUT_FORMATS));
		}
		return format;
	}

}
