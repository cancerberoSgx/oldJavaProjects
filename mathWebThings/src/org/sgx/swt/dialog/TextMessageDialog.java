package org.sgx.swt.dialog;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;

public class TextMessageDialog extends AbstractDialog{

	private Label label = null;
	private Button button = null;

	public TextMessageDialog(Shell parent, String title, String txt){
		super(parent);
		init(parent, title, txt);
	}

	public void setDialogText(String text) {
		label.setText(text);
	}	

	/**
	 * This method initializes shell
	 * @param txt 
	 * @param title 
	 * @param parent 
	 */
	private void init(Shell parent, String title, String txt) {
		shell = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		shell.setText(title);
		shell.setSize(new Point(300, 200));
		shell.setLayout(new GridLayout());
		label = new Label(shell, SWT.NONE);
		label.setText(txt);
		button = new Button(shell, SWT.NONE);
		button.setText("accept");
		button.addSelectionListener(new SelectionListener(){
//			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {				
			}
//			@Override
			public void widgetSelected(SelectionEvent arg0) {
				TextMessageDialog.this.getShell().close();
			}
			
		});
	}

	public static void main(String[] args) {
		Display display = Display.getDefault();
		Shell parent = new Shell(display);
		TextMessageDialog dialog = new TextMessageDialog(parent, "tit", "txt");
		parent.open();
		dialog.open();
		
		while (!parent.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

	@Override
	public Shell getShell() {
		return shell;
	}

	@Override
	public void open() {
		shell.open();
	}

}
