package org.sgx.j2s.yui.widget;

import org.sgx.j2s.html.dom.Node;
import org.sgx.j2s.html.dom.util.DomUtils;
import org.sgx.j2s.js.JsUtils;
import org.sgx.j2s.model.base.Color;
import org.sgx.j2s.model.base.Point;
import org.sgx.j2s.model.base.Rectangle;
import org.sgx.j2s.util.impl.Utils;
import org.sgx.j2s.widget.AbstractWidgetFactory;
import org.sgx.j2s.widget.Widget;
import org.sgx.j2s.widget.events.EventListener;
import org.sgx.j2s.widget.layout.HorizontalBoxLayout;
import org.sgx.j2s.widget.layout.Layout;
import org.sgx.j2s.yui.ScriptLoader;
import org.sgx.j2s.yui.YuiDom;


public class WidgetImplTest2 {
	public static void main(String[] args) {
		new ScriptLoader().load_yahoo_dom_events_element();

		JsUtils.activateJ2sDebug();
		test1();
		System.out.println(Utils.printAssertsFailed());
	}

	public static Point prev = null;
	private static void test1() {	
		AbstractWidgetFactory factory = HtmlYuiImplWidgetFactory.getInstance();
		final Widget w1 =(Widget) factory.createWidget(Widget.class);
//		w1.setNotifyChangeProperty(true);
		
		w1.setBackground(Color.BLACK);
		w1.setBounds(new Rectangle(250,450,450,450));
		
		w1.addMouseListener(new EventListener() {
			public void handleEvent(org.sgx.j2s.widget.events.Event evt) {
				Point xy = YuiDom.getXY(evt.target);
				System.out.println(xy);
//				Point p2 = new Point(evt.clientX, evt.clientY).plus(w1.getBounds().location.negate());
				Point p2 = new Point(evt.clientX, evt.clientY);
				
				if(WidgetImplTest2.prev==null) {
					WidgetImplTest2.prev=p2;
				}
				else {
					Point p1 = WidgetImplTest2.prev;
					WidgetImplTest2.prev=null;
					Rectangle bounds = new Rectangle(p1.x, p1.y, Math.abs(p1.x-p2.x), Math.abs(p1.y-p2.y));
					Widget w =(Widget) HtmlYuiImplWidgetFactory.getInstance().createWidget(Widget.class);
					w.setBounds(bounds);
//					System.out.println(bounds);
					w.setBackground(Color.BLUE);
//							new Color(
//							));
					w1.appendChild(w);
				}
//				System.out.println("click");
			}			
		});

//		Widget w11 = factory.createWidget();
//		w11.setBackground(Color.GREEN);
//		w11.setBounds(new Rectangle(10,10,40,40));
//		w1.appendChild(w11);
//		
//
//		Widget w12 = factory.createWidget();
//		w12.setBackground(Color.BLUE);
//		w12.setBounds(new Rectangle(105,160,40,40));
//		w1.appendChild(w12);
				
		//show root widget
		DomUtils.getBody().appendChild((Node) factory.getRepresentationOf(w1));
		

		
		Layout la1=new HorizontalBoxLayout();
		w1.setLayout(la1);
		la1.layout();
		

	}

}
