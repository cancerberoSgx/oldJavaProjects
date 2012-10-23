package org.sgx.swt.snippets;


/*
 * Shell example snippet: create a dialog shell
 *
 * For a list of all SWT example snippets see
 * http://www.eclipse.org/swt/snippets/
 */
import org.eclipse.swt.widgets.*;

public class Snippet50 {

public static void main (String [] args) {
	Display display = new Display ();
	Shell shell = new Shell (display);
	shell.setText ("Shell");
	shell.setSize (200, 200);
	shell.open ();
	Shell dialog = new Shell (shell);
	dialog.setText ("Dialog");
	dialog.setSize (200, 200);
	dialog.open ();
	while (!shell.isDisposed ()) {
		if (!display.readAndDispatch ()) display.sleep ();
	}
	display.dispose ();
}
} 
