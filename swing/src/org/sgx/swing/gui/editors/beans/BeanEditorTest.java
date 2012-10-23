package org.sgx.swing.gui.editors.beans;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import org.sgx.swing.j2s.model.base.Rectangle;
import org.sgx.swing.utils.SwingUtils;
import org.sgx.utils.JavaBeansUtils;
import org.sgx.utils.Utils;


public class BeanEditorTest {

	public static void test1() {
		ProbeBean b = new ProbeBean(new Object[] {"uno", "dos", "tricota"}, true, "hola", 2.3);

		final SimpleBeanEditor f = new SimpleBeanEditor(b, true);
		SwingUtils.showInFrame(f).addWindowListener(new WindowListener() {
			public void windowActivated(WindowEvent arg0) {				
			}
			public void windowClosed(WindowEvent arg0) {
				System.out.println(Utils.toString(f.getSelectedData()));

				System.out.println(JavaBeansUtils.printBean_(f.getCurrentValue()));

			}
			public void windowClosing(WindowEvent arg0) {	
			}
			public void windowDeactivated(WindowEvent arg0) {				
			}
			public void windowDeiconified(WindowEvent arg0) {
			}
			public void windowIconified(WindowEvent arg0) {System.out.println("lskdjflskdjf");
			System.out.println(Utils.toString(f.getSelectedData()));

			System.out.println(JavaBeansUtils.printBean_("jajaj: "+f.getCurrentValue()));
			}
			public void windowOpened(WindowEvent arg0) {
			}			
		});

	}

	public static void main(String[] args) {
//		test1();
		test2();
//		test3();
	}

	private static void test2() {
		ProbeBean b = new ProbeBean(new Object[] {"uno", "dos", "tricota"}, true, "hola", 2.3);
		ProbeBean2 b2 = new ProbeBean2(new Object[] {"uno", "dos", "tricota"}, true, "hola", 2.3, b);		
		final SimpleBeanEditor f = new SimpleBeanEditor(b2, true);
		SwingUtils.showInFrame(f).addWindowListener(new WindowListener() {
			public void windowActivated(WindowEvent arg0) {				
			}
			public void windowClosed(WindowEvent arg0) {
				System.out.println(Utils.toString(f.getSelectedData()));

				System.out.println(JavaBeansUtils.printBean_("jajaj: "+f.getCurrentValue()));

			}
			public void windowClosing(WindowEvent arg0) {	
			}
			public void windowDeactivated(WindowEvent arg0) {				
			}
			public void windowDeiconified(WindowEvent arg0) {
			}
			public void windowIconified(WindowEvent arg0) {System.out.println("lskdjflskdjf");
			System.out.println(Utils.toString(f.getSelectedData()));

			System.out.println(JavaBeansUtils.printBean_("jajaj: "+f.getCurrentValue()));
			}
			public void windowOpened(WindowEvent arg0) {
			}			
		});
	}

	private static void test3() {
		Rectangle r = new Rectangle(2,3,4,5);
		final SimpleBeanEditor<Rectangle> f = new SimpleBeanEditor<Rectangle>(r, true);
		SwingUtils.showInFrame(f).addWindowListener(new WindowListener() {
			public void windowActivated(WindowEvent arg0) {				
			}
			public void windowClosed(WindowEvent arg0) {
				System.out.println(Utils.toString(f.getSelectedData()));

				System.out.println(JavaBeansUtils.printBean_("jajaj: "+f.getCurrentValue()));

			}
			public void windowClosing(WindowEvent arg0) {	
			}
			public void windowDeactivated(WindowEvent arg0) {				
			}
			public void windowDeiconified(WindowEvent arg0) {
			}
			public void windowIconified(WindowEvent arg0) {System.out.println("lskdjflskdjf");
			System.out.println(Utils.toString(f.getSelectedData()));

			System.out.println(JavaBeansUtils.printBean_("jajaj: "+f.getCurrentValue()));
			}
			public void windowOpened(WindowEvent arg0) {
			}			
		});
	}
}
