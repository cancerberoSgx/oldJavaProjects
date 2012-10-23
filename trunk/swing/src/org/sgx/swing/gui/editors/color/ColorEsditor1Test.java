package org.sgx.swing.gui.editors.color;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

import org.sgx.swing.j2s.model.base.Color;
import org.sgx.swing.j2s.model.editor.Editor;
import org.sgx.swing.j2s.model.editor.EditorInvalidStateException;
import org.sgx.swing.j2s.model.editor.RealtimeEditor;
import org.sgx.swing.j2s.model.events.EditorListener;
import org.sgx.swing.utils.SwingUtils;

public class ColorEsditor1Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final Editor<Color> e = new ColorEditor1(Color.BLACK, "nati");
		((RealtimeEditor<Color>)e).addEventListener(new EditorListener<Color>() {
			@Override
			public void notifyValueChanged(Color val) {
				System.out.println(val);				
			}			
		});
		JFrame f = SwingUtils.showInFrame(((ColorEditor1)e));
		f.addWindowListener(new WindowListener() {
			public void windowActivated(WindowEvent arg0) {
			}
			public void windowClosed(WindowEvent arg0) {				
			}
			public void windowClosing(WindowEvent arg0) {
			}
			public void windowDeactivated(WindowEvent arg0) {
			}
			public void windowDeiconified(WindowEvent arg0) {				
			}
			public void windowIconified(WindowEvent arg0) {
					System.out.println(e.getCurrentValue());
			}
			public void windowOpened(WindowEvent arg0) {
			}			
		});
	}

}
