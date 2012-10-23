package org.sgx.swt.dialog;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class HTMLDialog extends AbstractDialog{

	private Browser browser1;
	private Button button2;
	private Button button1;
	private Composite composite1;

	public HTMLDialog(Shell parent, String title, String url) {
		super(parent);
		init(parent, title, url);
	}
	private void init(Shell parent, String title, String url) {
		shell=new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		GridLayout shellLayout = new GridLayout();
		shellLayout.makeColumnsEqualWidth = true;
		shell.setLayout(shellLayout);
		shell.setText(title);
		shell.setSize(223, 201);
		{
			composite1 = new Composite(shell, SWT.NONE);
			RowLayout composite1Layout = new RowLayout(org.eclipse.swt.SWT.HORIZONTAL);
			composite1.setLayout(composite1Layout);
			{
				button1 = new Button(composite1, SWT.PUSH | SWT.CENTER);
				button1.setText("<");
				button1.addSelectionListener(new SelectionAdapter(){
					@Override
					public void widgetSelected(SelectionEvent e) {	
						HTMLDialog.this.browser1.back();
					}					
				});
			}
			{
				button2 = new Button(composite1, SWT.PUSH | SWT.CENTER);
				button2.setText(">");
				button2.addSelectionListener(new SelectionAdapter(){
					@Override
					public void widgetSelected(SelectionEvent e) {	
						HTMLDialog.this.browser1.forward();
					}					
				});
			}
		}
		{
			browser1 = new Browser(shell, SWT.NONE);
			GridData browser1LData = new GridData();
			browser1LData.grabExcessHorizontalSpace = true;
			browser1LData.grabExcessVerticalSpace = true;
			browser1LData.horizontalAlignment = GridData.FILL;
			browser1LData.verticalAlignment = GridData.FILL;
			browser1.setLayoutData(browser1LData);
			browser1.setText("browser1");
			browser1.setUrl(url);
		}
	}

	
	
	public static void main(String[] args) {
		Display display = Display.getDefault();
		Shell parent = new Shell(display);
		HTMLDialog dialog = new HTMLDialog(parent, "tit", "http://localhost/mathWebThings/org.sgx.AppsMenu.html");
		dialog.setSize(400,400);
		parent.open();
		dialog.open();
		
		while (!parent.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

}
