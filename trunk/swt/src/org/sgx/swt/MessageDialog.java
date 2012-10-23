package org.sgx.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class MessageDialog extends Dialog {

	protected Object result;
	protected Shell shell;
	String title, msg;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public MessageDialog(Shell parent, int style, String title, String msg) {
		super(parent, style);
		this.title=title; 
		this.msg=msg;
	}

	public MessageDialog(Shell parent, String title, String msg) {
		super(parent);
		this.title=title; 
		this.msg=msg;
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		
		shell = new Shell(getParent());
		shell.setSize(450, 300);
		shell.setText(getText());
		shell.setLayout(new GridLayout(1, false));
		shell.setText(title);
		
		Label label = new Label(shell, SWT.NONE);
		label.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		label.setText(msg);
		
		Button button = new Button(shell, SWT.NONE);
		button.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		button.setText("Accept");
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				shell.close();
			}			
		});

	}

}
