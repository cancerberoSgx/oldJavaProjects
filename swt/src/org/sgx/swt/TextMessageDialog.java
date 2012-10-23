package org.sgx.swt;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.sgx.swt.dialog.DialogListener;

public class TextMessageDialog extends Shell{

//	private Shell sShell = null;
	private Label label = null;
	private Button button = null;
	private DialogListener dialogListener;

	public TextMessageDialog(Shell shell, int i, String title, String msg) {
		super(shell, i);
		createSShell();
		setText(title);
		setMessageText(msg);
	}
	public TextMessageDialog(Shell shell, int i, String title, String msg,
			DialogListener l) {
		this(shell, i, title, msg);
		this.dialogListener=l;
	}
	public void setMessageText(String text) {
		label.setText(text);
	}
	/**
	 * This method initializes sShell
	 */
	private void createSShell() {
		this.setSize(new Point(300, 200));
		this.setLayout(new GridLayout());
		label = new Label(this, SWT.NONE);
		label.setText("Label");
		button = new Button(this, SWT.NONE);
		button.setText("accept");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/* Before this is run, be sure to set up the launch configuration (Arguments->VM Arguments)
		 * for the correct SWT library path in order to run with the SWT dlls. 
		 * The dlls are located in the SWT plugin jar.  
		 * For example, on Windows the Eclipse SWT 3.1 plugin jar is:
		 *       installation_directory\plugins\org.eclipse.swt.win32_3.1.0.jar
		 */
		Display display = Display.getDefault();
//		MessageDialog thisClass = new MessageDialog();
		Shell shell = new Shell(display);
		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}
}
