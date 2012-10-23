package org.sgx.swt.layout;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.sgx.swt.SWTUtils;

public class RelativeLayoutTest extends Composite {

	/**
	 * Create the composite
	 * @param parent
	 * @param style
	 */
	public RelativeLayoutTest(Composite parent, int style) {
		super(parent, style);

		final Button button = new Button(this, SWT.CHECK);
		button.setText("Check Button");
		button.setBounds(0, 53, 85, 16);

		final Button button_1 = new Button(this, SWT.NONE);
		button_1.setText("button");
		button_1.setBounds(62, 152, 44, 23);

		final Button button_2 = new Button(this, SWT.TOGGLE);
		button_2.setText("Toggle Button");
		button_2.setBounds(27, 224, 79, 23);
		//
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public static void main(String[] args) {
		Shell shell = new Shell(SWT.SHELL_TRIM);
		shell.setText("RESIZE ME PLEASE");
		shell.setSize(200,200);
		shell.setLayout(new FillLayout());
		
		RelativeLayoutTest l = new RelativeLayoutTest(shell, SWT.NONE);
		l.setBounds(new Rectangle(0,0,200,400));
//		l.resizeChildren();
		l .setLayout(new RelativeLayout(l));
		SWTUtils.showShell(shell);
	}
}
