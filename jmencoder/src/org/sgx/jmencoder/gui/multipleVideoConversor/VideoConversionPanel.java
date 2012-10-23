package org.sgx.jmencoder.gui.multipleVideoConversor;


import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.sgx.jmencoder.mplayer.Format;
import org.sgx.jmencoder.mplayer.executors.MencoderExecutorImpl;
import org.sgx.swing.gui.editors.file.FileAction;
import org.sgx.swing.gui.editors.file.FileListSelector2;
import org.sgx.swing.gui.editors.file.FileSelector;
import org.sgx.utils.exec.EndExecutionInformation;
import org.sgx.utils.exec.ExecutorListener;
/**
 * @author SGURIN
 *
 */
public class VideoConversionPanel extends JPanel {

	private static final long serialVersionUID = 1L;
//	private static final String defaultBitRate = "128";
//	private static final String defaultVolume = "256";
	private FileListSelector2 inputFileSelector = null;
	private JPanel jPanel1 = null;
	private JPanel jPanel2 = null;
	private JLabel jLabel = null;
	private JComboBox formatCombo = null;
	private JPanel jPanel3 = null;
	private FileSelector outputDirSelector = null;
	private JPanel jPanel5 = null;
	private JCheckBox jCheckBox = null;
	private JLabel jLabel2 = null;
	public VideoConversionPanel() {
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
										JOptionPane.showMessageDialog(VideoConversionPanel.this, "... y esta canción, se terminó, tutu...");
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

	private JComboBox getFormatCombo() {
		if (formatCombo == null) {
			formatCombo = new JComboBox();
			formatCombo.setModel(new DefaultComboBoxModel(Format.DUMB_VIDEO_OUTPUT_FORMATS_NAMES));			
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
		return Format.DUMB_VIDEO_OUTPUT_FORMATS_NAMES[formatCombo.getSelectedIndex()];
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
