package org.sgx.swing.gui.editors.base;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Arrays;
import java.util.Collections;

import javax.swing.JComponent;
import javax.swing.JFrame;

import org.sgx.swing.j2s.model.base.ListSelection;
import org.sgx.swing.j2s.model.editor.Editor;
import org.sgx.swing.utils.SwingUtils;

public class ListSelectionEditorTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[]opts={"1","2","3"}, s={};
		ListSelection<String> ls = new ListSelection<String>(opts,s);
		final Editor ed = new ListSelectionEditor("hola", ls);
		JFrame f = SwingUtils.showInFrame((JComponent) ed);
		f.addWindowListener(new WindowListener() {

			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void windowClosed(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void windowClosing(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void windowDeactivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void windowDeiconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void windowIconified(WindowEvent arg0) {
				System.out.println(ed.getCurrentValue());
			}

			public void windowOpened(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}

}
