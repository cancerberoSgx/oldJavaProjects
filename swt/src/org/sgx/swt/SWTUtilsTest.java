package org.sgx.swt;

import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.sgx.swt.dialog.Dialog;
import org.sgx.swt.dialog.DialogListener;

public class SWTUtilsTest {

	public static void main(String[] args) {
//		test1();
//		test2();
		test3();
	}

	private static void test3() {
		Shell shell = new Shell(Display.getDefault());
		shell.setText("titl");
		shell.setLayout(null);
		SWTUtils.showShell(shell);
	}

	private static void test2() {
		Shell shell = SWTUtils.createShell("title"); 
		Browser b = SWTUtils.getBrowserWithBody(shell, "<p>hello<b>seba</b></p><p>llll</p>");
		SWTUtils.showInShell(b);
	}

	private static void test1() {
		Display display = Display.getDefault();
		Shell shell = new Shell(display); 
		SWTUtils.showTextMessageDialog(shell, "hello", "text", new DialogListener() {
			public void notifyDialogEvent(Dialog d, int event) {
				System.out.println("notify "+event);
			}		
			
		});
		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

}
