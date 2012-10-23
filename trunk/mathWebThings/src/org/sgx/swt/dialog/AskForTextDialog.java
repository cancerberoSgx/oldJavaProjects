package org.sgx.swt.dialog;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.sgx.j2s.widget.base.Color;
import org.sgx.swt.SWTUtils;


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
public class AskForTextDialog extends AbstractDialog{

	private Text label = null;
	private Button button = null;
	private Label label1;

	public AskForTextDialog(Shell parent, String title, String msg, String contents){
		super(parent);
		init(parent, title, msg,contents);
		
	}

	public void setDialogText(String text) {
		label.setText(text);
	}	

	/**
	 * This method initializes shell
	 * @param txt 
	 * @param title 
	 * @param parent 
	 * @param contents 
	 */
	private void init(Shell parent, String title, String txt, String contents) {
		shell = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		shell.setText(title);
		shell.setSize(new Point(300, 200));
		GridLayout shellLayout = new GridLayout();
		shellLayout.makeColumnsEqualWidth = true;
		shell.setLayout(shellLayout);
		{
			label1 = new Label(shell, SWT.NONE);
			label1.setText(txt);
		}
		GridData labelLData = new GridData();
		labelLData.grabExcessVerticalSpace = true;
		labelLData.verticalAlignment = GridData.FILL;
		labelLData.horizontalAlignment = GridData.FILL;
		labelLData.grabExcessHorizontalSpace = true;
		label = new Text(shell, SWT.MULTI|SWT.BORDER);
		label.setBackground(SWTUtils.colorToSWT(Color.WHITE));
		label.setLayoutData(labelLData);
		label.setText(contents);
		
		button = new Button(shell, SWT.NONE);
		button.setText("accept");
		button.addSelectionListener(new SelectionAdapter(){
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				notifyListeners(AskForTextDialog.this,1);
				AskForTextDialog.this.getShell().close();
			}			
		});
	}
	public String getSelectedText(){
		return label.getText();
	}

	public static void main(String[] args) {
		Display display = Display.getDefault();
		Shell parent = new Shell(display);
		AskForTextDialog dialog = new AskForTextDialog(parent, "tit", "txt", "hehe");
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
