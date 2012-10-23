package org.sgx.swing.gui.editors.file;


import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.JButton;

//import org.sgx.swing.gui.dnd.FileDropListener;
//import org.sgx.swing.gui.dnd.FileDropManager;
import org.sgx.swing.gui.dnd.FileDrop;
import org.sgx.utils.Utils;

public class FileSelector extends JPanel implements FileDrop.Listener {

	private static final long serialVersionUID = 1L;
	private static final Dimension defaultMaximunSize = new Dimension(400, 14);  //  @jve:decl-index=0:
	private JTextField filePath = null;
	private JButton fileChooserBtn = null;
	int selectionMode = JFileChooser.FILES_ONLY;
	boolean mustExist=true;
	String lastFile=null;
//	String selection=null;
	private String label="...";  //  @jve:decl-index=0:
	
	List<FileSelectorListener> listeners=new LinkedList<FileSelectorListener>();  //  @jve:decl-index=0:
	
	public void addListener(FileSelectorListener l) {
		listeners.add(l);
	}

	
	private JTextField getFilePath() {
		if (filePath == null) {
			filePath = new JTextField();
			filePath.setMinimumSize(new Dimension(100,15));
//			filePath.addActionListener(new java.awt.event.ActionListener() {
//				public void actionPerformed(java.awt.event.ActionEvent e) {
//					selection = filePath.getText();
//				}
//			});

			 new  FileDrop( filePath, this);
			 
//			FileDropManager dm = new FileDropManager(filePath);
//			dm.addDropListener(this);
		}
		return filePath;
	}

	private JButton getFileChooserBtn() {
		if (fileChooserBtn == null) {
			fileChooserBtn = new JButton(label);
			fileChooserBtn.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFileChooser fc = new JFileChooser();
					fc.setFileSelectionMode(selectionMode);				
					int returnVal = fc.showOpenDialog(FileSelector.this);
					if (returnVal == JFileChooser.APPROVE_OPTION) {

						/* * * * *  FILE SELECTION EVENT * * * * * * */
						File file = fc.getSelectedFile();	

						String selection=file.getAbsolutePath();
						filePath.setText(selection);
						for(FileSelectorListener l : listeners) {
							if(fc.getSelectedFiles().length==0) {
								File[] fs = {fc.getSelectedFile()};
								l.notifyFileSelection(fs);
							}
							else{
								l.notifyFileSelection(fc.getSelectedFiles());
							}
						}          	
					} else {
						//canceled by user
					}
				}
			});
		}
		return fileChooserBtn;
	}

	public FileSelector() {
		this("...");		
	}

	public FileSelector(String label) {
		super();
		this.label=label;
		initialize();
	}
	
	private void initialize() {
		this.setSize(300, 20);
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS)/*new FlowLayout()*/);
		this.add(getFilePath(), null);
		this.add(getFileChooserBtn(), null);
		this.setMaximumSize(defaultMaximunSize);
	}

	public String getSelection() {
		return filePath.getText();
	}


	public void filesDropped(File[] list) {
		System.out.println(Utils.toString(list)+" - "+list.length);
//		System.out.println("drop");
//		if(list.length>0) {
//			if(list[0]instanceof File);
//				selection = list[0].getAbsolutePath(); 
		String path = list[0].getAbsolutePath();
				filePath.setText(path);
//				selection = path;
//				File [] fs = {list[0]}; 
				for(FileSelectorListener l : listeners) {
					l.notifyFileSelection(list);		
				}
//		}
	}

}
