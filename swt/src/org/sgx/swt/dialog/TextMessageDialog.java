package org.sgx.swt.dialog;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;

public class TextMessageDialog {

	private Shell sShell = null;
	private Label label = null;
	private Button button = null;

//	public TextMessageDialog(Shell shell, int i, String title, String msg) {
//		super(shell, i);
//		createSShell();
//		setText(title);
//		setMessageText(msg);
//	}
//	public TextMessageDialog(Shell shell, int i, String title, String msg,
//			DialogListener l) {
//		this(shell, i, title, msg);
//		this.dialogListener=l;
//	}
	
	public void setDialogTitle(String title) {
		sShell.setText(title);
	}
	
	public void setDialogText(String text) {
		label.setText(text);
	}
	
	

	/**
	 * This method initializes sShell
	 */
	private void createSShell() {
		sShell = new Shell();
		sShell.setText("Shell");
		sShell.setSize(new Point(300, 200));
		sShell.setLayout(new GridLayout());
		label = new Label(sShell, SWT.NONE);
		label.setText("Label");
		button = new Button(sShell, SWT.NONE);
		button.setText("accept");
	}

	public static void main(String[] args) {
		Display display = Display.getDefault();
		TextMessageDialog thisClass = new TextMessageDialog();
		thisClass.createSShell();
		thisClass.setDialogText("hi puto");
		thisClass.sShell.open();
		thisClass.setDialogTitle("title");
		while (!thisClass.sShell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}
	
}
