package org.sgx.jmencoder.options;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.lang.reflect.InvocationTargetException;

import org.sgx.swing.gui.editors.beans.ProbeBean;
import org.sgx.swing.gui.editors.beans.SimpleBeanEditor;
import org.sgx.swing.utils.SwingUtils;
import org.sgx.utils.JavaBeansUtils;
import org.sgx.utils.JavabeanFormatException;
import org.sgx.utils.Utils;

public class SubtitleEditorTest {
	public static void test1() {
		SubtitleFontOption b = new SubtitleFontOption();
//		SubtitleOption b = new SubtitleOption();
//		ProbeBean b = new ProbeBean(new Object[] {"uno", "dos", "tricota"}, true, "hola", 2.3);

		final SimpleBeanEditor f = new SimpleBeanEditor(b, true);
		SwingUtils.showInFrame(f).addWindowListener(new WindowListener() {
			public void windowActivated(WindowEvent arg0) {				
			}
			public void windowClosed(WindowEvent arg0) {
				System.out.println(Utils.toString(f.getSelectedData()));
				try {
					System.out.println(JavaBeansUtils.printBean("jajaj: "+f.getCurrentValue()));
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JavabeanFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			public void windowClosing(WindowEvent arg0) {	
			}
			public void windowDeactivated(WindowEvent arg0) {				
			}
			public void windowDeiconified(WindowEvent arg0) {
			}
			public void windowIconified(WindowEvent arg0) {System.out.println("lskdjflskdjf");
			System.out.println(Utils.toString(f.getSelectedData()));

			try {
				System.out.println(JavaBeansUtils.printBean("jajaj: "+f.getCurrentValue()));
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JavabeanFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			public void windowOpened(WindowEvent arg0) {
			}			
		});

	}

	public static void main(String[] args) {
		test1();
	}
}
