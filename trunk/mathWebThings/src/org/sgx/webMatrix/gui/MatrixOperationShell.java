package org.sgx.webMatrix.gui;

import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MenuItem;
import org.sgx.j2s.utils.Utils;


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
public class MatrixOperationShell {

	private Shell sShell = null;  //  @jve:decl-index=0:visual-constraint="10,10"
	private Menu menuBar = null;
	private Menu submenu = null;
	private MatrixOperationsPanel mainPanel;


	private void createSShell() {
		sShell = new Shell(SWT.MAX|SWT.MIN|SWT.RESIZE);
		sShell.setText("Shell");
		sShell.setSize(633, 577);
		sShell.setLayout(new FillLayout());
		mainPanel = new MatrixOperationsPanel(sShell, SWT.NONE);
		
		menuBar = new Menu(sShell, SWT.BAR);
		MenuItem submenuItem = new MenuItem(menuBar, SWT.CASCADE);
		submenuItem.setText("help");
		submenu = new Menu(submenuItem);
		MenuItem push = new MenuItem(submenu, SWT.PUSH);
		push.setText("about");
		push.addSelectionListener(new org.eclipse.swt.events.SelectionListener() {
			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
				MessageBox b = new MessageBox(sShell, SWT.DIALOG_TRIM);
				b.setMessage("a visual matrix calculator for the browser and the desktop\nMade in hours by Sebastian Gurin (cancerbero_sgx at sourceforge net) thanks to java2script.");
				b.open();
			}
			public void widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent e) {
			}
		});
		submenuItem.setMenu(submenu);
		sShell.setMenuBar(menuBar);
	}

	
	public static void main(String[] args) {
		Display display = Display.getDefault();
		MatrixOperationShell thisClass = new MatrixOperationShell();
		thisClass.createSShell();
		thisClass.sShell.open();
		thisClass.sShell.pack();
		
		if(Utils.arrayContains(args, "maximized")){
			thisClass.sShell.setMaximized(true);
		}
		while (!thisClass.sShell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

}
