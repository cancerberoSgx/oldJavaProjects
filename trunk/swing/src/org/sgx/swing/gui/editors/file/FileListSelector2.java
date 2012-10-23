package org.sgx.swing.gui.editors.file;


import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.io.File;
import java.util.Collections;
import java.util.EventObject;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.sgx.swing.gui.dnd.FileDrop;
import org.sgx.swing.utils.SwingUtils;
/**
 * lista de selección de archivos con soporte para d&d nativo (filesystem)
 * y reordenacion utilizando d&d
 * @author: sgurin
 */
public class FileListSelector2 extends JPanel implements FileDrop.Listener  {

	private static final long serialVersionUID = 1L;
	private static final String DEFAULT_LABEL = "Select File";
	private FileSelector fileSelector;
	private JList jList = null;

	/** the file list poblated by the user */
	private List<File> selection = new LinkedList<File>();  //  @jve:decl-index=0:
	/**
	 * selected/ list items of "selection" list
	 */
	private List<File> subSelection = new LinkedList<File>();//  @jve:decl-index=0: 

	private JPanel subPanel = null;
	private JPanel buttonsP = null;
	private String label="Select File";
	private FileAction action;

	public FileListSelector2(String label, FileAction action) {
		super();
		this.label = label;
		this.action=action;
		initialize();
	}
	public FileListSelector2(FileAction action) {
		this(DEFAULT_LABEL, action);		
	}
	public FileListSelector2() {
		this(DEFAULT_LABEL, new FileAction() {			
			@Override
			public String getActionName() {
				return null;
			}			
			@Override
			public void executeAction(File f) {
			}
		});
	}
	
	
	public List<File> getList() {
		return selection;
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
					FileListSelector2.this.addSelection(selectedFiles);
				}

//				public void notifyFileSelection(File selectedFile) {
//					File[] fs = new File[1];
//					fs[0]=selectedFile;
//					FileListSelector2.this.addSelection(fs);
//				}				
			});
			fileSelector.setBorder(BorderFactory.createTitledBorder(label));
			fileSelector.setMaximumSize(new Dimension(400, 14));
		}
		return fileSelector;
	}

	void addSelection(List<File> selectedFiles) {
		selection.addAll( selectedFiles);
		jList.removeAll();
		DefaultListModel m = new DefaultListModel();
		int i=0;
		for(File f:selection) {
			m.set(i, f.getName());
			i++;
		}
	}
	void addSelection(File [] selectedFiles) {
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
	private JList getJList() {
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
//					System.out.println(selection.get(jList.getSelectedIndex()));				
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
	private JPanel getSubPanel() {
		if (subPanel == null) {
			subPanel = new JPanel();
			subPanel.setLayout(new BoxLayout(getSubPanel(), BoxLayout.X_AXIS));
			subPanel.add(getButtonsP(), null);
			subPanel.add(getJScrollPane(), null);
		}
		return subPanel;
	}
	private JPanel getButtonsP() {
		if (buttonsP == null) {
			buttonsP = new JPanel();
			buttonsP.setLayout(new BoxLayout(getButtonsP(), BoxLayout.Y_AXIS));
			buttonsP.add(getActionButt(), null);
		}
		return buttonsP;
	}
	public void filesDropped(File[] list) {
		if(list.length>0)
			addSelection(list);
	}
	public List<File> getSelection() {
		return selection;
	}
	public List<File> getSubSelection() {
		return subSelection;
	}

	int selectedIndex = 0;
	private JScrollPane jScrollPane = null;
	private JButton actionButt = null;
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

	public void handler1(EventObject e) {
		System.out.println("handler1");
		if(subSelection.size()>0)
			FileListSelector2.this.action.executeAction(subSelection.get(0));
		else
			JOptionPane.showMessageDialog(FileListSelector2.this, "no file selected");
	}
	private JButton getActionButt() {
		if (actionButt == null) {
			actionButt = new JButton();
			actionButt.setText(action.getActionName());
			actionButt.addKeyListener(new KeyListener() {				
				@Override
				public void keyTyped(KeyEvent e) {
					if(e.getKeyChar()=='\n') {
						handler1(e);
					}
				}				
				@Override
				public void keyReleased(KeyEvent e) {	}				
				@Override
				public void keyPressed(KeyEvent e) {	}
			});
			actionButt.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					handler1(e);
				}
			});
		}
		return actionButt;
	}

	
	//test

	public static void main(String [] a) {
		final JFrame f = new JFrame();
		f.setContentPane(new FileListSelector2(new FileAction() {
			public void executeAction(File file) {
				JOptionPane.showMessageDialog(f, "executing action over "+file.getAbsolutePath());
			}
			public String getActionName() {
				return "my action";
			}
		}));
		f.setSize(400,400);
		f.setVisible(true);
	}

	
}
