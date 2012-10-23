package org.sgx.swing.utils;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.Collection;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;

import org.sgx.utils.FSUtils;

/**
 * 
 * @author sgurin
 *
 */

public class SwingUtils {
	/**shows a file chooser dialog and wait till user select a file
	 * all params can be null except parent component
	 * returns the file choosed or null if user cancel or error occurs
	 * */
	public static File chooseFile (Component parent, File currentDirectory, FileFilter filter, String dialogTitle) {		
		JFileChooser	 fc = new JFileChooser();
		if(currentDirectory!=null)
			fc.setCurrentDirectory(currentDirectory);
		if(filter!=null)
			fc.setFileFilter(filter);
		if(dialogTitle!=null)
			fc.setDialogTitle(dialogTitle);		
		int val =  fc.showOpenDialog(parent);
		if(val==JFileChooser.APPROVE_OPTION)
			return fc.getSelectedFile();
		else return null;
	}

	public static JFrame showInFrame(JComponent c) {
		JFrame f = new JFrame();
		f.setContentPane(c);
		f.setSize(300,300);
		f.setVisible(true);
		return f;
	}

	public static Color transformColor(org.sgx.swing.j2s.model.base.Color color) {
		return new Color(color.red, color.green, color.blue);
	}
	public static org.sgx.swing.j2s.model.base.Color  transformColor(Color color) {
		return new org.sgx.swing.j2s.model.base.Color (color.getRed(), color.getGreen(), color.getBlue());
	}
	/**
	 * @param enable will apply setEnabled(enable) to all children components of parent except comp
	 * @param comp
	 * @param parent if null we assume parent is the top level jframe
	 */
	public static void setEnableAllBut(boolean enable, JComponent comp, JComponent parent) {
//			if(parent==null)
//				parent=getAncestor(Class<?> ancestorClass)
		
		setEnableAllBut_(enable, comp, parent);
	}
	/**
	 * recursive - from bottom to top in the container tree
	 * @param enable
	 * @param self
	 * @param parent
	 */
	static void setEnableAllBut_(boolean enable, Container self, JComponent parent){
		if(self!=parent){
			Container p = self.getParent();
			Component[] childs = p.getComponents();
			Component c;
			for (int i = 0; i < childs.length; i++) {
				c = childs[i];
				if(c!=self)
					c.setEnabled(enable);
			}
			setEnableAllBut_(enable, p, parent);
		}			
	}
	
	public static void setEnableAllBut(boolean enable, Collection<JComponent> comp, JComponent parent) {
		for (JComponent c : comp)
			setEnableAllBut(enable, c, parent);
	}

	public static JPanel buildBoxPanel(int axis) {
		JPanel p = new JPanel();
		LayoutManager l = new BoxLayout(p, axis);
		p.setLayout(l);
		JPanel p2 = new JPanel();
		p2.setLayout(new BorderLayout());
		p.add(p2);
		return p;		
	}

	public static BoxLayout getBoxLayoutX(Container c) {
		BoxLayout l = new BoxLayout(c, BoxLayout.X_AXIS);
		return l;
	}
	public static BoxLayout getBoxLayoutY(Container c) {
		BoxLayout l = new BoxLayout(c, BoxLayout.Y_AXIS);
		return l;
	}

	public static void closeJFrame(JFrame f) {
		 WindowEvent wev = new WindowEvent(f, WindowEvent.WINDOW_CLOSING);
         Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);

	}

	public static void listRemoveSelectedItems(JList list) {
		DefaultListModel dlm = (DefaultListModel) list.getModel();
		if (list.getSelectedIndices().length > 0) {
			int[] tmp = list.getSelectedIndices();
			int[] selectedIndices = list.getSelectedIndices();
			for (int i = tmp.length - 1; i >= 0; i--) {
				selectedIndices = list.getSelectedIndices();
				dlm.removeElementAt(selectedIndices[i]);
			}
		}
		list.setModel(dlm);
	}
/**
 * show a save as dialog and force the user to choose a file of given extension
 * @param parent
 * @param extension
 * @return
 */
	public static File showSaveAsDialog(JComponent parent,
			final String extension) {
		return showSaveAsDialog(parent, extension, "Save file", 
				extension!=null?extension+" files" : "All files");
	}
	public static File showSaveAsDialog(JComponent parent,
			final String extension, String title, final String fileDescription) {
		File f=null;
		  // "Save" dialog:
		 JFileChooser c = new JFileChooser();
		 if(title!=null)
			 c.setDialogTitle(title);
		 c.setFileFilter(new FileFilter() {						
			@Override
			public String getDescription() {
				if(fileDescription!=null)
					return fileDescription;
				else
					return "All Files";
			}						
			@Override
			public boolean accept(File f) {
				if(extension!=null) {
					String name = f.getName();
					if(name.contains(".")) {
						return FSUtils.getFileExtension(name).toLowerCase().equals(extension);								 
					}
					return false;
				}
				else
					return true;
			}
		});
	      int rVal = c.showSaveDialog(parent);
	      if (rVal == JFileChooser.APPROVE_OPTION) {
	    	  f= c.getSelectedFile();
	      }
	      if (rVal == JFileChooser.CANCEL_OPTION) {
	    	  return null;
	      }
		  if(!f.getName().contains(".") && extension!=null) { //user forgot extension
			  f = new File(f.getAbsolutePath()+"."+extension);
		  }
		return f;
	}

	public static File showOpenFileDialog(JComponent parent, String extension) {
		return showOpenFileDialog(parent, extension, "Open file", 
				extension!=null?extension+" files" : "All files", true);
	}
	public static File showOpenFileDialog(JComponent parent, String extension, File currDir) {
		return showOpenFileDialog(parent, extension, "Open file", 
				extension!=null?extension+" files" : "All files", true, currDir);
	}
	public static File showOpenFileDialog(JComponent parent, final String extension,
			String title, final String fileDescription, final boolean mustExists) {
		return showOpenFileDialog(parent, extension, title, fileDescription, mustExists, null);
	}
	public static File showOpenFileDialog(JComponent parent, final String extension, 
			String title, final String fileDescription, final boolean mustExists, File currentDir) {
		File f = null;
		JFileChooser c = new JFileChooser();
		
//		File f2 = new File("./");
//		System.out.println(f2.getAbsolutePath());
		
//		int l = currentDir.listFiles().length;
		if(currentDir!=null)
			c.setCurrentDirectory(currentDir);
		if(title!=null)
			c.setDialogTitle(title);
		 c.setFileFilter(new FileFilter() {						
				@Override
				public String getDescription() {
					if(fileDescription!=null)
						return fileDescription;
					else
						return "All Files";
				}						
				@Override
				public boolean accept(File f) {					
					if(extension!=null) {
						String name = f.getName();
						if(name.contains(".")) {
							return FSUtils.getFileExtension(name).toLowerCase().equals(extension);								 
						}
						return false;
					}
					else
						return true;
				}
			});		
		int rVal = c.showOpenDialog(parent);
		if (rVal == JFileChooser.APPROVE_OPTION)
			f = c.getSelectedFile();
		if(!f.getName().contains(".") && extension!=null) { //user forgot extension
			  f = new File(f.getAbsolutePath()+"."+extension);
		}
		if(mustExists && !f.exists()) {
			return null;
		}
		return f;
	}
}
