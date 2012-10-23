package org.sgx.swt.editor.tests;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.events.ShellListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.sgx.swt.editor.StringTable;

public class StringTableTest {

	private Shell sShell = null;
	public StringTable grid = null;

	private void createSShell() {
		sShell = new Shell();
		sShell.setText("edit me and inconify me");
		createComposite();
		sShell.setSize(new Point(300, 200));
		sShell.setLayout(new FillLayout());
		sShell.addShellListener(new ShellListener() {
			public void shellActivated(ShellEvent e) {
			}
			public void shellClosed(ShellEvent e) {
			}
			public void shellDeactivated(ShellEvent e) {
			}
			public void shellDeiconified(ShellEvent e) {
			}
			public void shellIconified(ShellEvent e) {
				String[][] data =  grid.getData();
				System.out.println(data[0][0]);
			}			
		});
		
	}

	private void createComposite() {
		String[][] data =  {{"1,1", "1,2", "1,3"}, {"2,1", "2,2", "2,3"}};
		grid = new StringTable(sShell, SWT.NONE, data);
	}

	public static void main(String[] args) {
		Display display = Display.getDefault();
		StringTableTest thisClass = new StringTableTest();
		thisClass.createSShell();
	
		thisClass.sShell.open();
		thisClass.sShell.pack();
		while (!thisClass.sShell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

}
