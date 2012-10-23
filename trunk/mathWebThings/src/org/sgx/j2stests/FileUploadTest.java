package org.sgx.j2stests;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;


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
public class FileUploadTest {
	private Composite composite1;
	private Button button2;
	private Button button1;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	private Composite getComposite1(Composite parent) {
		if(composite1 == null) {
			composite1 = new Composite(parent, SWT.NONE);
			GridLayout composite1Layout = new GridLayout();
			composite1Layout.makeColumnsEqualWidth = true;
			composite1.setLayout(composite1Layout);
			{
				button2 = new Button(composite1, SWT.PUSH | SWT.CENTER);
				button2.setText("click me for upload");
			}
		}
		return composite1;
	}
	
	private Button getButton1(Composite parent) {
		if(button1 == null) {
			button1 = new Button(parent, SWT.PUSH | SWT.CENTER);
			button1.setText("button1");
			button1.setBounds(13, 0, -3, 10);
		}
		return button1;
	}

}
