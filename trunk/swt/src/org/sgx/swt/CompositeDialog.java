package org.sgx.swt;


import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * diálogo que muestra un composite pasado por el usuario
 * @author sg
 *
 */
public class CompositeDialog extends Dialog {

	protected Object result;
	protected Shell shell;
	String title;
	private Control comp;
	private int w;
	private int h;
	private boolean showAcceptButton=true;

	/**for testing only
	 * @param showAcceptButton 
	 * @param h2 
	 * @param comp2 
	 * @param title2 */
	public CompositeDialog(Shell parent, String title, Control comp, 
			int w, int h, boolean showAcceptButton) {
		this(parent, SWT.NONE, title, comp, w, h, showAcceptButton);
	}
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 * @param showAcceptButton 
	 */
	public CompositeDialog(Shell parent, int style, String title, Control comp, 
			int w, int h, boolean showAcceptButton) {
		super(parent, style);
		this.title=title; 
		this.comp=comp;
		this.w=w;
		this.h=h;
		this.showAcceptButton=showAcceptButton;
	}

	public CompositeDialog(Shell parent, String title, Control comp, int w, int h) {
		this(parent, SWT.NONE, title, comp, w, h, true);
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
		
		shell = new Shell(getParent(), SWT.RESIZE);
		shell.setSize(w, h);
//		shell.pack();
		shell.setText(getText());
		shell.setLayout(new GridLayout(1, false));
		shell.setText(title);		
		
		comp.setParent(shell);
		GridData gd = new GridData();
		gd.grabExcessHorizontalSpace=true;
		gd.grabExcessVerticalSpace=true;
		gd.horizontalAlignment=SWT.FILL;
		gd.verticalAlignment=SWT.FILL;
		comp.setLayoutData(gd);//SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		
		if(showAcceptButton) {
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

}