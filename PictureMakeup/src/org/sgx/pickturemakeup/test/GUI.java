package org.sgx.pickturemakeup.test;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class GUI {

	public static void putInFrameAndShow(JComponent p) {
		JFrame f = new JFrame();
		f.setContentPane(p);
		f.setSize(300,300);
		f.setVisible(true);
	}
}
