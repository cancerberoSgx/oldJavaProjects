package org.sgx.swt;

import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.sgx.j2s.html.myApi.DomUtils;
import org.sgx.j2s.html.myApi.Element;
import org.sgx.j2s.widget.base.Rectangle;
import org.sgx.j2s.widget.css.CSSUtils;
import org.sgx.swt.dialog.Dialog;
import org.sgx.swt.dialog.DialogListener;

public class SWTUtilsTest {

	public static void main(String[] args) {
//		test1();
//		test2();
		test3();
//		test4();
		
//		test5();
	}

//	private static void test5() {
//		Shell shell = new Shell(Display.getDefault());
//		shell.setText("test4 - only html");
//		Element myDiv = DomUtils.getDocument().createElement("iframe"), 
//			myDiv2 = DomUtils.getDocument().createElement("iframe");;
//
//		String ELEM_ID="myParentId";
//		myDiv.id=ELEM_ID;
//		DomUtils.makeAbsolutelyBounds(myDiv, new Rectangle(123,23,144,344));
//		myDiv.style.backgroundColor="yellow";
//		 DomUtils.getBody().appendChild(myDiv);
//		 
//		 
//		DomUtils.makeAbsolutelyBounds(myDiv2, new Rectangle(323,33,144,144));
//		myDiv2.style.backgroundColor="blue";
//		
//		
////		 DomUtils.getBody().appendChild(myDiv);
////		 SWTUtils.setShellDomParent(shell, ELEM_ID);
//		shell.setLayout(null);
//		SWTUtils.showShell(shell);
//	}

	private static void test4() {
		Shell shell = new Shell(Display.getDefault());
		shell.setText("test4 - only html");
		Element myDiv = DomUtils.getDocument().createElement("div"); 
//		Element	myDiv2 = DomUtils.getDocument().createElement("div");

		String ELEM_ID="myParentId";
		myDiv.id=ELEM_ID;
		DomUtils.makeAbsolutelyBounds(myDiv, new Rectangle(123,23,144,344));
		myDiv.style.backgroundColor="yellow";
		 DomUtils.getBody().appendChild(myDiv);
		 
//		DomUtils.makeAbsolutelyBounds(myDiv2, new Rectangle(123,33,144,144));
//		myDiv2.style.backgroundColor="blue";
//		DomUtils.getBody().appendChild(myDiv2);
		
//		 DomUtils.getBody().appendChild(myDiv);
		 SWTUtils.setShellDomParent(shell, ELEM_ID);
		shell.setLayout(null);
		SWTUtils.showShell(shell);
	}

	private static void test3() {
		Shell shell = new Shell(Display.getDefault());
		shell.setText("titl");
		shell.setLayout(null);
		
//DomUtils.getBody().style.position="absolute";
		
		Element myDiv = DomUtils.getDocument().createElement("div"); 
		Element	myDiv2 = DomUtils.getDocument().createElement("div");

		String ELEM_ID="myParentId";
		myDiv.id=ELEM_ID;
		DomUtils.makeAbsolutelyBounds(myDiv, new Rectangle(23,23,144,344));
		myDiv.style.backgroundColor="yellow";
		 DomUtils.getBody().appendChild(myDiv);
		 myDiv.style.position="static";
		 
//		DomUtils.makeAbsolutelyBounds(myDiv2, new Rectangle(23,33,44,24));
//		myDiv2.style.backgroundColor="blue";
//		myDiv.appendChild(myDiv2);
//		 myDiv2.style.position="absolute";
		 
//		DomUtils.getBody().style.position="absolute";
//		 System.out.println(SWTUtils.getJ2sHandlerElem(shell));
		 SWTUtils.setShellDomParent(shell, ELEM_ID);
		 SWTUtils.getJ2sHandlerElem(shell).id="pepepee";
		shell.setSize(200,200);		
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
		SWTUtils.showTextMessageDialog(shell, "hello", "text"
//				, 
//				new DialogListener() {
//			public void notifyDialogEvent(Dialog d, int event) {
//				System.out.println("notify "+event);
//			}
//		}
		);
		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

}
