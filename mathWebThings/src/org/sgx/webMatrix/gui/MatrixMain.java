package org.sgx.webMatrix.gui;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.CoolBar;
import org.eclipse.swt.widgets.Button;

public class MatrixMain {

	private Shell sShell = null;
	private Menu menuBar = null;
	private Menu submenu = null;
	private Menu submenu1 = null;
	private Menu submenu2 = null;
	private Button button = null;
	
	private void createSShell() {
		sShell = new Shell();
		sShell.setText("Shell");
		sShell.setSize(new Point(300, 200));
		sShell.setLayout(new GridLayout());
		button = new Button(sShell, SWT.NONE);
		button.setText("new matrix");
		button.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
				MatrixDialog d = new MatrixDialog(sShell, "please define matrix");
				d.createSShell();
				Shell newMatrixDialog =d.getShell();
				newMatrixDialog.setParent(sShell);
			}
		});
		menuBar = new Menu(sShell, SWT.BAR);
		MenuItem submenuItem = new MenuItem(menuBar, SWT.CASCADE);
		submenuItem.setText("file");
		MenuItem submenuItem1 = new MenuItem(menuBar, SWT.CASCADE);
		submenuItem1.setText("edit");
		MenuItem submenuItem2 = new MenuItem(menuBar, SWT.CASCADE);
		submenu2 = new Menu(submenuItem2);
		MenuItem push2 = new MenuItem(submenu2, SWT.PUSH);
		MenuItem push3 = new MenuItem(submenu2, SWT.PUSH);
		push3.setText("about");
		push3.addSelectionListener(new org.eclipse.swt.events.SelectionListener() {
			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
			}
			public void widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent e) {
			}
		});
		submenuItem2.setMenu(submenu2);
		submenu1 = new Menu(submenuItem1);
		MenuItem push1 = new MenuItem(submenu1, SWT.PUSH);
		push1.setText("show operation");
		submenuItem1.setMenu(submenu1);
		submenu = new Menu(submenuItem);
		MenuItem push = new MenuItem(submenu, SWT.PUSH);
		push.setText("exit");
		submenuItem.setMenu(submenu);
		sShell.setMenuBar(menuBar);
	}
	
	

}
