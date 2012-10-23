package org.sgx.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTError;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Widget;
import org.sgx.j2s.html.myApi.DomUtils;
import org.sgx.j2s.html.myApi.Element;
import org.sgx.swt.dialog.AskForTextDialog;
import org.sgx.swt.dialog.DialogListener;
import org.sgx.swt.dialog.TextMessageDialog;

public class SWTUtils {
	public static void showTextMessageDialog(Shell shell, String title, String msg) {
		TextMessageDialog dialog = new TextMessageDialog (shell,title, msg);
		Point pt = shell.getDisplay().getCursorLocation ();
		dialog.setLocation (pt.x, pt.y);
		dialog.setSize (400, 250);
		dialog.open (); 
	}
	
	public static void askForTextDialog(Shell shell, String title, String msg, String contents, DialogListener listener) {
		AskForTextDialog dialog = new AskForTextDialog (shell,title, msg, contents);
		dialog.addDialogListener(listener);
		Point pt = shell.getDisplay().getCursorLocation ();
		dialog.setLocation (pt.x, pt.y);
		dialog.setSize (400, 250);
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

	public static Color colorToSWT(org.sgx.j2s.widget.base.Color c) {
		return new org.eclipse.swt.graphics.Color(Display.getDefault(),c.red,c.green,c.blue);
	}

	public static org.sgx.j2s.widget.base.Color toSgxColor(RGB newColor) {
		return new org.sgx.j2s.widget.base.Color(newColor.red, newColor.green, newColor.blue);
	}

	public  static int comboIndexOf(Combo combo, String item) {
		String[] sel = combo.getItems();
		for (int i = 0; i < sel.length; i++) {
			if(combo.getItem(i).equals(item))
				return i;
		}
		return -1;
	}

	public static Rectangle rectangleToSWT(org.sgx.j2s.widget.base.Rectangle r) {
		return new Rectangle(r.location.x, r.location.y, r.dimension.width, r.dimension.height);		
	}

/**
 * set's the DOM html element with id "elemId" to be the parent of given shell (works only in html)
 * @param shell
 * @param elemId
 */
	public static void setShellDomParent(Shell shell, String elemId) {
		Element el = DomUtils.getDocument().getElementById(elemId);
		el.style.position="static";//otherless it won't work
		el.appendChild(getJ2sHandlerElem(shell));
	}
	/** (works only in html)
	 * @j2sNative
	 * return w.handle; 
	 */
	public static Element getJ2sHandlerElem(Widget w) {return null;	}
}
