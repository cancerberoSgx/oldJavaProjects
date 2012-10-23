package org.sgx.jmencoder.gui.audioFormatConversor;


import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.sgx.jmencoder.mplayer.Format;
import org.sgx.jmencoder.mplayer.executors.ExecutorFactory;
import org.sgx.jmencoder.mplayer.executors.MencoderExecutorImpl;
import org.sgx.swing.gui.editors.file.FileAction;
import org.sgx.swing.gui.editors.file.FileListSelector2;
import org.sgx.swing.gui.editors.file.FileSelector;
import org.sgx.utils.exec.EndExecutionInformation;
import org.sgx.utils.exec.ExecutorListener;
/**
 * 
 * @author SGURIN
 *
 */
public class AudioConversionPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final String defaultBitRate = "128";
	private static final String defaultVolume = "256";
	private FileListSelector2 inputFileSelector = null;
	private JPanel jPanel1 = null;
	private JPanel jPanel2 = null;
	private JLabel jLabel = null;
	private JPanel jPanel21 = null;
	private JLabel jLabel1 = null;
	private JComboBox formatCombo = null;
	private JPanel jPanel3 = null;
	private FileSelector outputDirSelector = null;
	private JPanel jPanel5 = null;
	private JCheckBox jCheckBox = null;
	private JLabel jLabel2 = null;
	private JTextField sampleRate = null;
	private JPanel jPanel211 = null;
	private JLabel jLabel11 = null;
	private JTextField volume = null;
	private JPanel jPanel2111 = null;
	private JLabel jLabel111 = null;
	private JTextField bitRate = null;
	
	public AudioConversionPanel() {
		super();
		initialize();
	}

	private void initialize() {
		this.setSize(409, 310);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(getInputFileSelector(), null);
		this.add(getJPanel1(), null);
		this.add(getJPanel3(), null);
	}

	private FileListSelector2 getInputFileSelector() {
		if (inputFileSelector == null) {
			inputFileSelector = new FileListSelector2("play", 
			new FileAction() {
				public void executeAction(File f) {
					try {
						String cmd = "ffmpeg"+File.separator+"ffplay.exe -vn \""+
							f.getAbsolutePath()+"\"";
						MencoderExecutorImpl.execute(
								cmd, new ExecutorListener() {
									public void notifyEnd(EndExecutionInformation endExecutionInformation) {
									}
									public void notifyError(String str) {
										System.out.println(str);
									}
									public void notifyMovieHeader(String movieHeader) {
									}
									public void notifyOuput(String str) {
										System.out.println(str);
									}								
								});
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				public String getActionName() {
					return "play";
				}				
			});
		}
		return inputFileSelector;
	}

	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jPanel1 = new JPanel();
			jPanel1.setBorder(BorderFactory.createTitledBorder("options"));
			jPanel1.add(getJPanel2(), null);
			jPanel1.add(getJPanel21(), null);
			jPanel1.add(getJPanel211(), null);
			jPanel1.add(getJPanel2111(), null);
		}
		return jPanel1;
	}

	private JPanel getJPanel2() {
		if (jPanel2 == null) {
			jLabel = new JLabel();
			jLabel.setText("output format");
			jPanel2 = new JPanel();
			jPanel2.setLayout(new BoxLayout(getJPanel2(), BoxLayout.Y_AXIS));
			jPanel2.add(jLabel, null);
			jPanel2.add(getFormatCombo(), null);
		}
		return jPanel2;
	}

	private JPanel getJPanel21() {
		if (jPanel21 == null) {
			jLabel1 = new JLabel();
			jLabel1.setText("Sampling rate (Hz)");
			jPanel21 = new JPanel();
			jPanel21.setLayout(new BoxLayout(getJPanel21(), BoxLayout.Y_AXIS));
			jPanel21.add(jLabel1, null);
			jPanel21.add(getSampleRate(), null);
		}
		return jPanel21;
	}

	private JComboBox getFormatCombo() {
		if (formatCombo == null) {
			formatCombo = new JComboBox();
			formatCombo.setModel(new DefaultComboBoxModel(Format.AUDIO_OUTPUT_SUPPORTED_FORMATS));
			
		}
		return formatCombo;
	}

	private JPanel getJPanel3() {
		if (jPanel3 == null) {
			jPanel3 = new JPanel();
			jPanel3.setBorder(BorderFactory.createTitledBorder("output dir"));
			jPanel3.setLayout(new BoxLayout(getJPanel3(), BoxLayout.Y_AXIS));
			jPanel3.add(getOutputDirSelector(), null);
			jPanel3.add(getJPanel5(), null);
		}
		return jPanel3;
	}

	private FileSelector getOutputDirSelector() {
		if (outputDirSelector == null) {
			outputDirSelector = new FileSelector();
		}
		return outputDirSelector;
	}

	private JPanel getJPanel5() {
		if (jPanel5 == null) {
			jLabel2 = new JLabel();
			jLabel2.setText("Leave on the source directory");
			jPanel5 = new JPanel();
			jPanel5.setLayout(new BoxLayout(getJPanel5(), BoxLayout.X_AXIS));
			jPanel5.add(getJCheckBox(), null);
			jPanel5.add(jLabel2, null);
		}
		return jPanel5;
	}

	private JCheckBox getJCheckBox() {
		if (jCheckBox == null) {
			jCheckBox = new JCheckBox();
			jCheckBox.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					outputDirSelector.setEnabled(!jCheckBox.isSelected());
				}
			});
			jCheckBox.setSelected(true);
			outputDirSelector.setEnabled(false);
		}
		return jCheckBox;
	}

	private JTextField getSampleRate() {
		if (sampleRate == null) {
			sampleRate = new JTextField();
		}
		return sampleRate;
	}

	private JPanel getJPanel211() {
		if (jPanel211 == null) {
			jLabel11 = new JLabel();
			jLabel11.setText("volume");
			jPanel211 = new JPanel();
			jPanel211.setLayout(new BoxLayout(getJPanel211(), BoxLayout.Y_AXIS));
			jPanel211.add(jLabel11, null);
			jPanel211.add(getVolume(), null);
		}
		return jPanel211;
	}

	private JTextField getVolume() {
		if (volume == null) {
			volume = new JTextField();
			volume.setText(defaultVolume);
		}
		return volume;
	}

	private JPanel getJPanel2111() {
		if (jPanel2111 == null) {
			jLabel111 = new JLabel();
			jLabel111.setText("bit rate (kbits/s)");
			jPanel2111 = new JPanel();
			jPanel2111.setLayout(new BoxLayout(getJPanel2111(), BoxLayout.Y_AXIS));
			jPanel2111.add(jLabel111, null);
			jPanel2111.add(getBitRate(), null);
		}
		return jPanel2111;
	}

	private JTextField getBitRate() {
		if (bitRate == null) {
			bitRate = new JTextField();
			bitRate.setText(defaultBitRate);
		}
		return bitRate;
	}
	
	public List<File> getSelectedInputFiles() {
		return inputFileSelector.getSelection();
	}
	public String getSelectedOutputDir() {
		if(outputDirSelector.isEnabled())
			return outputDirSelector.getSelection();
		else
			return null;
	}
	public String getSelectedOutputFormat() {
		return Format.AUDIO_OUTPUT_SUPPORTED_FORMATS[formatCombo.getSelectedIndex()];
	}
	public String getSelectedSamplingRate() {
		return sampleRate.getText();
	}
	public String getSelectedVolume() {
		return volume.getText();
	}
	public String getSelectedBitRate() {
		return bitRate.getText();
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
