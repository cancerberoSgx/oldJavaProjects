package org.sgx.swt.editor.piano;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class PianoOctaveEditorTest {

	public static void main(String[] args) {
		Display display = Display.getDefault();
		Shell shell = new Shell(display);
		PianoOctaveEditor ed = new PianoOctaveEditor(shell, SWT.NONE) {
			@Override
			public Object getValue() {
				// TODO Auto-generated method stub
				return null;
			}
			@Override
			public void setValue(Object value) {
				// TODO Auto-generated method stub
				
			}			
		};
		shell.open();
	
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

}
