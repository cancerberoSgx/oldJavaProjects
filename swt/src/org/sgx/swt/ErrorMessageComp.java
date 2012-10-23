package org.sgx.swt;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Text;

public class ErrorMessageComp extends Composite {
	private static final String DEFAULT_ERROR_TEXT = "An error has occurred.";
	private Text stackTraceText;
	private Label errorCause;
	private Label textLabel;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public ErrorMessageComp(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout(1, false));
		
		textLabel = new Label(this, SWT.NONE);
		textLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		Label lblCause = new Label(this, SWT.NONE);
		lblCause.setText("Cause:");
		
		errorCause = new Label(this, SWT.NONE);
		errorCause.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		Label lblStackTrace = new Label(this, SWT.NONE);
		lblStackTrace.setText("Stack Trace:");
		
		stackTraceText = new Text(this, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.CANCEL | SWT.MULTI);
		stackTraceText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

	}
	
	public void setError(String text, Exception e) {
		textLabel.setText(text==null?DEFAULT_ERROR_TEXT:text);
		errorCause.setText(e.getLocalizedMessage()==null?DEFAULT_ERROR_TEXT:text);
		String s ="";
		StackTraceElement[] st = e.getStackTrace();
		for (int i = 0; i < st.length; i++) {
			s+=st[i].toString()+"\n";
		}
		stackTraceText.setText(s);
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
