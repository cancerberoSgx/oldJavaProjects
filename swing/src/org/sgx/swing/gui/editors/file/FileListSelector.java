package org.sgx.swing.gui.editors.file;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.io.File;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.sgx.swing.gui.dnd.FileDrop;
import org.sgx.swing.utils.SwingUtils;

public class FileListSelector extends JPanel implements FileDrop.Listener {

	private static final long serialVersionUID = 1L;
	private FileSelector fileSelector;
	private JList jList = null;

	/** the file list poblated by the user */
	private List<File> selection = new LinkedList<File>();  //  @jve:decl-index=0:
	/**
	 * selected/ list items of "selection" list
	 */
	private List<File> subSelection = new LinkedList<File>();//  @jve:decl-index=0: 
	
	private JPanel subPanel = null;
//	private JPanel buttonsP = null;
//	private JButton deleteBut = null;
//	private JButton moveDownBut = null;
//	private JButton moveUpBut = null;
	private String label="Select File";

	public FileListSelector(String label) {
		super();
		this.label = label;
		initialize();
	}
	public FileListSelector() {
		this("Select File");		
	}
	private void initialize() {
		this.setSize(300, 200);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(getFileSelector() );
		this.add(getSubPanel(), null);
	}
	private FileSelector getFileSelector() {
		if (fileSelector == null) {
			fileSelector = new FileSelector("add...");
			fileSelector.addListener(new FileSelectorListener() {
				public void notifyFileSelection(File[] selectedFiles) {
					FileListSelector.this.addSelection(selectedFiles);
				}
//				public void notifyFileSelection(File selectedFile) {
//					File[] fs = new File[1];
//					fs[0]=selectedFile;
//					FileListSelector.this.addSelection(fs);
//				}				
			});
			fileSelector.setBorder(BorderFactory.createTitledBorder(label));
		}
		return fileSelector;
	}
	
	

	public JList getJList() {
		if (jList == null) {
			jList = new JList();
			jList.setModel(new DefaultListModel());
			
			//dnd for reordering items
			 MouseAdapter listener = new ListReorderListener(jList);
			 jList.addMouseListener(listener);
			 jList.addMouseMotionListener(listener);
			 
			jList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
				public void valueChanged(javax.swing.event.ListSelectionEvent e) {
					subSelection.clear();
					selectedIndex = jList.getSelectedIndex();
					if(selectedIndex==-1)
						return;
					subSelection.add(selection.get(selectedIndex));				
				}
			});
			jList.addKeyListener(new KeyListener() {				
				@Override
				public void keyTyped(KeyEvent e) {}				
				@Override
				public void keyReleased(KeyEvent e) {}				
				@Override
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode()==KeyEvent.VK_DELETE) {
						SwingUtils.listRemoveSelectedItems(jList);
					}
				}
			});
			new  FileDrop( jList, this);
		}
		return jList;
	}
	public void notifyFileSelection(File[] selectedFiles) {
		// TODO Auto-generated method stub
		
	}

	private JPanel getSubPanel() {
		if (subPanel == null) {
			subPanel = new JPanel();
			subPanel.setLayout(new BoxLayout(getSubPanel(), BoxLayout.X_AXIS));
//			subPanel.add(getButtonsP(), null);
			subPanel.add(getJScrollPane(), null);
		}
		return subPanel;
	}
	
	public void filesDropped(File[] list) {
		if(list.length>0)
			addSelection(list);
	}
	
	public void addSelection(List<File> selectedFiles) {
		selection.addAll( selectedFiles);
		jList.removeAll();
		DefaultListModel m = new DefaultListModel();
		int i=0;
		for(File f:selection) {
			m.set(i, f.getName());
			i++;
		}
	}
	public void addSelection(File [] selectedFiles) {
		Collections.addAll(selection, selectedFiles);
		jList.removeAll();
		String [] a = new String[selection.size()];
		int i=0;
		for(File f:selection) {
			a[i]=f.getName();
			i++;
		}
		DefaultListModel m = new DefaultListModel();
		for (int j = 0; j < a.length; j++) {
			m.add(j, a[j]);
		}
		jList.setModel(m);
	}
	public List<File> getList() {
		return selection;
	}
	public List<File> getSubSelection() {
		return subSelection;
	}

	int selectedIndex = 0;
	private JScrollPane jScrollPane = null;
	public int getSubSelectedIndex() {
		return  selectedIndex;
	}
	public void setSubSelectedIndex(int i ) {
		jList.setSelectedIndex(i);
		selectedIndex=i;
	}
	
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setViewportView(getJList());
		}
		return jScrollPane;
	}

	public void clearList() {
		jList.setModel(new DefaultListModel());
		selection.clear();
		this.subSelection.clear();
	}
	
	
	//test

	public static void main(String [] a) {
		final JFrame f = new JFrame();
		f.setContentPane(new FileListSelector());
		f.setSize(400,400);
		f.setVisible(true);
	}
}
