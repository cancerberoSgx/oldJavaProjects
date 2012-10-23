package org.sgx.swt.widgets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Widget;
import org.sgx.j2s.html.myApi.DomUtils;
import org.sgx.j2s.html.myApi.Element;
import org.sgx.j2s.widget.base.Color;
import org.sgx.j2s.widget.base.Rectangle;
import org.sgx.swt.SWTUtils;
import org.w3c.dom.html.HTMLUListElement;

/**
 * my mission is to add an arbitrary html dom element into a swt composite. 
 * i'm not a browser widget. i'm not a high level composite
 * @author sg
 *
 */
public class HTMLComposite extends Composite{

	Element htmlElement;
	
	public HTMLComposite(Composite parent, int style) {
		super(parent, style);
		init();
	}

	Element __handle;
	protected boolean fill=true;
	private void init() {
		/**
		 * @j2sNative
		 * this.__handle=this.handle;	
		 */{}
		 __handle.style.position="absolute";
		 
		 addControlListener(new ControlListener() {
			public void controlMoved(ControlEvent arg0) {
			}
			public void controlResized(ControlEvent arg0) {
				if( arg0.widget instanceof Control) {
					Control w = (Control) arg0.widget;
					if(fill && htmlElement!=null)
						DomUtils.makeAbsolutelyBounds(htmlElement, 
								new Rectangle(0,0,w.getBounds().width,w.getBounds().height));
//					System.out.println(w.getBounds());
				}
			}
			 
		 });
		
	}


	public Object getHtmlElement() {
		return htmlElement;
	}

	public void setHtmlElement(Element htmlElement) {
		this.htmlElement=htmlElement;
		DomUtils.removeAllChilds(__handle);
		__handle.appendChild(htmlElement);
		//		Object oldHtmlEl = htmlElement;
		//		this.htmlElement = htmlElement;
		//		addHtmlElement(oldHtmlEl,htmlElement);
	}

	public void setHtmlElementBounds(Rectangle bounds) {

		DomUtils.makeAbsolutelyBounds(this.htmlElement,bounds);
	}


	public static void main(String[] args) {
		//html
		Element div1 = DomUtils.getDocument().createElement("div");
		DomUtils.getBody().appendChild(div1);
		div1.id="div1"; div1.innerHTML="aloooo";div1.style.fontSize="40px";
		div1.style.backgroundColor=Color.BLUE.toCSS();
		DomUtils.makeAbsolutelyBounds(div1, new Rectangle(40,30,100,100));
		
		Display display = Display.getDefault();
		Shell shell = new Shell(display);
		shell.setLayout(new FillLayout());
		shell.addControlListener(new ControlListener() {
				public void controlMoved(ControlEvent arg0) {
				}
				public void controlResized(ControlEvent arg0) {
				
					System.out.println("");
				}
				 
			 });
		HTMLComposite comp = new HTMLComposite(shell,SWT.NONE);
		
		//add html element to composite
		comp.setHtmlElement(div1);
		
		//show
		shell.layout();
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
	}


	public boolean isFill() {
		return fill;
	}


	public void setFill(boolean fill) {
		this.fill = fill;
	}
}
