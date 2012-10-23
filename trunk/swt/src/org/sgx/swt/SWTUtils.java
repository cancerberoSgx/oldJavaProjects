package org.sgx.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTError;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.*;
import org.sgx.swt.dialog.DialogListener;

public class SWTUtils {
	public static void showTextMessageDialog(Shell shell, String title, String msg, DialogListener l) {
		Shell dialog = new TextMessageDialog (shell, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL, title, msg, l);
		Point pt = shell.getDisplay().getCursorLocation ();
		dialog.setLocation (pt.x, pt.y);
		dialog.setText (msg);
		dialog.setSize (100, 100);
		dialog.open (); 
	}

	public static Browser getBrowserWithBody(Composite parent, String htmlbody) {
		String html = htmlizeElement(htmlbody);
		Browser browser;
		try {
			browser = new Browser(parent, SWT.NONE);
		} catch (SWTError e) {
			System.out.println("Could not instantiate Browser: " + e.getMessage());
			return null;
		}
		browser.setText(html);
		return browser;
	}
	public static String htmlizeElement(String html) {
		return "<HTML><HEAD><TITLE>HTML Test</TITLE></HEAD><BODY>"+html+"</BODY></HTML>";
	}
	

	public static void showShell(Shell shell) {
		Display display = Display.getDefault();
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}
	public static Shell getShell(){
		Display display = Display.getDefault();
		Shell shell = new Shell(display);
		shell.setLayout(new FillLayout());
		return shell;
	}
	public static void showInShell(Composite c) {
		Display display = Display.getDefault();
		Shell shell = new Shell(display);
		shell.setLayout(new FillLayout());
		c.setParent(shell);
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

	public static Shell createShell(String title) {
		Display display = Display.getDefault();
		Shell shell = new Shell(display);
		shell.setText(title);
		return shell;
	}

	public static void showHtmlElInShell(Display display, Object el) {
		//	Shell shell = new Shell(display);	
		//	shell.setLayout(null);
		//	
		//	fractalShell.open();

		//	while (!fractalShell.isDisposed()) {
		//		if (!display.readAndDispatch())
		//			display.sleep();
		//	}
		//	display.dispose();
	}

	public static void compositeDestroyAllChildrens(Composite c) {	
		Control[] childs = c.getChildren();
		for (int i = 0; i < childs.length; i++) {
			childs[i].dispose();
		}
	}

	
	

	private static final int COMP_DIALOG_DEFAULT_WIDTH = 500, 
		COMP_DIALOG_DEFAULT_HEIGHT=500;
	
	//dialogs
	public static void showTextMessageDialog(Shell shell, String title, String msg) {
		MessageDialog md = new MessageDialog(shell, title, msg);
		md.open(); 
	}
	public static void showErrorMessageDialog(Shell shell, String title, String msg) {
		MessageDialog md = new MessageDialog(shell, "ERROR - "+title, 
			"ERROR: \n"+msg+"\nLook at the console for more information.");
		md.open(); 
	}
	public static void showCompositeDialog(Shell shell, String title, Control comp, 
			int w, int h, boolean showAcceptButton) {
		CompositeDialog d = new CompositeDialog(shell, title, comp, w, h, showAcceptButton);
		d.open();
	}
	public static void showCompositeDialog(Shell shell, String title, Control comp) {
		showCompositeDialog(shell, title, comp, COMP_DIALOG_DEFAULT_WIDTH, 
				COMP_DIALOG_DEFAULT_HEIGHT);
	}
	public static void showCompositeDialog(Shell shell, String title, Control comp, 
			int w, int h) {
		showCompositeDialog(shell, title, comp, w, h, true);
	}
	public static void showHtmlDialog(Shell shell, String title, String url, int w, int h) {
		Browser b = new Browser(shell, SWT.NONE);
		b.setUrl(url);
		CompositeDialog d = new CompositeDialog(shell, title, b, w, h);
		d.open();
	}
	public static void showErrorDialog(Shell shell, String title, String text, 
			Exception e, int w, int h) {
		ErrorMessageComp ec = new ErrorMessageComp(shell, SWT.NONE);
		ec.setError(text, e);
		showCompositeDialog(shell, title, ec, w, h);
	}
	public static void showErrorDialog(Shell shell, String title, String text, Exception e) {
		showErrorDialog(shell, title, text, e, COMP_DIALOG_DEFAULT_WIDTH, COMP_DIALOG_DEFAULT_HEIGHT);
	}
	
	
	
//	//html and browser
//	public static Browser getBrowserWithBody(Composite parent, String htmlbody) {
//		String html = htmlizeElement(htmlbody);
//		Browser browser;
//		try {
//			browser = new Browser(parent, SWT.NONE);
//		} catch (SWTError e) {
//			System.out.println("Could not instantiate Browser: " + e.getMessage());
//			return null;
//		}
//		browser.setText(html);
//		return browser;
//	}
//	public static String htmlizeElement(String html) {
//		return "<HTML><HEAD><TITLE>HTML Test</TITLE></HEAD><BODY>"+html+"</BODY></HTML>";
//	}
//	
//
//	
//	//shell and composite
//	public static void showShell(Shell shell) {
//		Display display = Display.getDefault();
//		shell.open();
//		while (!shell.isDisposed()) {
//			if (!display.readAndDispatch())
//				display.sleep();
//		}
//		display.dispose();
//	}
//	public static Shell getShell(){
//		Display display = Display.getDefault();
//		Shell shell = new Shell(display);
//		shell.setLayout(new FillLayout());
//		return shell;
//	}
//	public static void showInShell(Composite c) {
//		Display display = Display.getDefault();
//		Shell shell = new Shell(display);
//		shell.setLayout(new FillLayout());
//		c.setParent(shell);
//		shell.open();
//		while (!shell.isDisposed()) {
//			if (!display.readAndDispatch())
//				display.sleep();
//		}
//		display.dispose();
//	}
//	public static Shell createShell(String title) {
//		Display display = Display.getDefault();
//		Shell shell = new Shell(display);
//		shell.setText(title);
//		return shell;
//	}
//	public static void compositeDestroyAllChildrens(Composite c) {	
//		Control[] childs = c.getChildren();
//		for (int i = 0; i < childs.length; i++) {
//			childs[i].dispose();
//		}
//	}
	
	
	
	//tree
	public static void treeExpandAll(Tree tree) {
		TreeItem[] items = tree.getItems();
		for (int i = 0; i < items.length; i++) {
			treeExpandAll_(items[i]);
		}
	}
	private static void treeExpandAll_(TreeItem treeItem) {
		treeItem.setExpanded(true);
		TreeItem[] childs = treeItem.getItems();
		for (int i = 0; i < childs.length; i++) {
			treeExpandAll_(childs[i]);
		}
	}
	public static void treeCollapseAll(Tree tree) {
		TreeItem[] items = tree.getItems();
		for (int i = 0; i < items.length; i++) {
			treeCollapseAll_(items[i]);
		}
	}
	private static void treeCollapseAll_(TreeItem treeItem) {
		treeItem.setExpanded(false);
		TreeItem[] items = treeItem.getItems();
		for (int i = 0; i < items.length; i++) {
			treeCollapseAll_(items[i]);
		}
	}

}
