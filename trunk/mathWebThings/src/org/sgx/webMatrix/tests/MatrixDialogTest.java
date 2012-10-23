package org.sgx.webMatrix.tests;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.sgx.swt.dialog.Dialog;
import org.sgx.swt.dialog.DialogListener;
import org.sgx.webMatrix.gui.MatrixDialog;

public class MatrixDialogTest implements DialogListener{

	public static void main(String[] args) {
		Display display = Display.getDefault();
		final Shell parent = new Shell(display);
		parent.setText("parent");
		parent.setLayout(new FillLayout());
		Button bt = new Button(parent, SWT.NONE);
		bt.setText("click me");
		bt.addSelectionListener(new SelectionListener(){
			public void widgetDefaultSelected(SelectionEvent arg0) {
			}
			public void widgetSelected(SelectionEvent arg0) {
				MatrixDialog dialog = new MatrixDialog(parent, "a title");
				dialog.open();
			}			
		});
		parent.pack();
		parent.open();

		while (!parent.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}


	public void notifyDialogEvent(Dialog d, int event) {
		if(event==MatrixDialog.DIALOG_ACCEPTED) {
			if(d instanceof MatrixDialog) {
				MatrixDialog matrixDialog = (MatrixDialog)d;
			}
		}
	}
}
