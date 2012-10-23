package org.sgx.jmencoder.gui.dirtyMencoderGui;


import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.io.File;

import javax.swing.JCheckBox;
import javax.swing.JLabel;

import org.sgx.swing.gui.editors.file.FileSelector;
import org.sgx.swing.gui.editors.file.FileSelectorListener;

public class SingleFilePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private FileSelector inputFileSelector = null;
	private FileSelector inputSubSelector = null;
	private FileSelector outputDirSelector = null;
	private JPanel jPanel = null;
	private JCheckBox pasteSubtitlesCheck = null;
	private JLabel jLabel = null;
	
	public SingleFilePanel() {
		super();
		initialize();
	}

	private void initialize() {
		this.setSize(300, 200);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(getInputFileSelector(), null);
		this.add(getJPanel(), null);
		this.add(getOutputDirSelector(), null);
	}

	
	public FileSelector getInputSubSelector() {
		if (inputSubSelector == null) {
			inputSubSelector = new FileSelector();
			inputSubSelector.setBorder(BorderFactory.createTitledBorder("input subtitle file"));
			inputSubSelector.addListener(new FileSelectorListener() {
				public void notifyFileSelection(File[] selectedFiles) {
					pasteSubtitlesCheck.setSelected(true);
				}
				public void notifyFileSelection(File selectedFile) {
					
				}
				
			});
		}
		return inputSubSelector;
	}
	
	public FileSelector getInputFileSelector() {
		if (inputFileSelector == null) {
			inputFileSelector = new FileSelector();
			inputFileSelector.setBorder(BorderFactory.createTitledBorder("input video file"));
		}
		return inputFileSelector;
	}
	
	public FileSelector getOutputDirSelector() {
		
		if (outputDirSelector == null) {
			outputDirSelector = new FileSelector();
			outputDirSelector.setBorder(BorderFactory.createTitledBorder("output video folder"));
		}
		return outputDirSelector;
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jLabel = new JLabel();
			jLabel.setText("Paste subtitles");
			jPanel = new JPanel();
			jPanel.setLayout(new BoxLayout(getJPanel(), BoxLayout.X_AXIS));
			jPanel.add(getInputSubSelector(), null);
			jPanel.add(getPasteSubtitlesCheck(), null);
			jPanel.add(jLabel, null);
		}
		return jPanel;
	}

	/**
	 * This method initializes pasteSubtitlesCheck	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox getPasteSubtitlesCheck() {
		if (pasteSubtitlesCheck == null) {
			pasteSubtitlesCheck = new JCheckBox();
		}
		return pasteSubtitlesCheck;
	}

}
