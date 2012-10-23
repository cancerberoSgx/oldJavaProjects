package org.sgx.jmencoder.gui.dirtyMencoderGui;



import java.awt.Component;
import java.awt.GridBagLayout;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import org.sgx.swing.gui.editors.file.FileListSelector;

import java.awt.GridBagConstraints;
import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.awt.Dimension;

public class InputPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTabbedPane jTabbedPane = null;

	private SingleFilePanel singleFilePanel = null;
	private JPanel multipleFilePanel = null;
	private FileListSelector jPanel = null;
	
	public InputPanel() {
		super();
		initialize();
	}

	private void initialize() {
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		gridBagConstraints.gridx = 0;
		this.setSize(552, 281);
		this.setLayout(new GridBagLayout());
		this.add(getJTabbedPane(), gridBagConstraints);
	}

	private JTabbedPane getJTabbedPane() {
		if (jTabbedPane == null) {
			jTabbedPane = new JTabbedPane();
			jTabbedPane.addTab("Single", getSingleFilePanel());
			jTabbedPane.addTab("Multiple", null, getMultipleFilePanel(), null);
		}
		return jTabbedPane;
	}


	private Component getSingleFilePanel() {
		if (singleFilePanel == null) {	
			singleFilePanel=new SingleFilePanel();
		}
		return singleFilePanel;
	}
	public JCheckBox getPasteSub() {
		return ((SingleFilePanel)getSingleFilePanel()).getPasteSubtitlesCheck();
	}

	private JPanel getMultipleFilePanel() {
		if (multipleFilePanel == null) {
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.gridwidth = 2;
			gridBagConstraints1.gridy = 0;
			gridBagConstraints1.gridx = 0;
			multipleFilePanel = new JPanel();
			multipleFilePanel.setLayout(new BoxLayout(multipleFilePanel, BoxLayout.Y_AXIS));
			multipleFilePanel.add(getJPanel(), null);
		}
		return multipleFilePanel;
	}
public boolean isMultiple() {
	return jTabbedPane.getSelectedComponent()==multipleFilePanel;
}
	public List<File> getInputVideoSelected() {
		if(isMultiple()) {
			return jPanel.getList();
		}
		else {

			if(singleFilePanel.getInputFileSelector().getSelection()==null ||
					singleFilePanel.getInputFileSelector().getSelection().equals(""))
				return new LinkedList<File>();
			if(jTabbedPane.getSelectedComponent()==singleFilePanel) {
				LinkedList<File> l = new LinkedList<File>();
				l.add(new File(singleFilePanel.getInputFileSelector().getSelection()));
				return l;			
			}	
		}
		return null;		
	}
	public List<File> getInputSubtitleSelected() {
		if(jTabbedPane.getSelectedComponent()==singleFilePanel) {
			if(singleFilePanel.getInputSubSelector().getSelection()==null)
				return null;
			LinkedList<File> l = new LinkedList<File>();
			l.add(new File(singleFilePanel.getInputSubSelector().getSelection()));
			return l;			
		}
		else {
			throw new RuntimeException("not impl");
		}
	}
	public List<File> getOutputVideoFolder() {
		if(jTabbedPane.getSelectedComponent()==singleFilePanel) {
			if(singleFilePanel.getOutputDirSelector().getSelection()==null)
				return null;
			else {
				LinkedList<File> l = new LinkedList<File>();
				String path = singleFilePanel.getOutputDirSelector().getSelection();
				l.add(new File(path));
				return l;
			}
		}
		else {
			throw new RuntimeException("not impl");
		}
	}

	private FileListSelector getJPanel() {
		if (jPanel == null) {
			jPanel = new FileListSelector();
		}
		return jPanel;
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"
