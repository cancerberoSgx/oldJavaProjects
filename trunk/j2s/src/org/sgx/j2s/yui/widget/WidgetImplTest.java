package org.sgx.j2s.yui.widget;

import org.sgx.j2s.html.dom.Node;
import org.sgx.j2s.html.dom.NodeList;
import org.sgx.j2s.html.dom.util.DomUtils;
import org.sgx.j2s.js.JsUtils;
import org.sgx.j2s.model.base.Color;
import org.sgx.j2s.model.base.Rectangle;
import org.sgx.j2s.model.events.Event;
import org.sgx.j2s.model.events.EventListener;
import org.sgx.j2s.model.events.PropertyChangeEvent;
import org.sgx.j2s.model.events.PropertyChangeListener;
import org.sgx.j2s.util.impl.Utils;
import org.sgx.j2s.widget.AbstractWidget;
import org.sgx.j2s.widget.AbstractWidgetFactory;
import org.sgx.j2s.widget.Widget;
import org.sgx.j2s.widget.layout.HorizontalBoxLayout;
import org.sgx.j2s.widget.layout.Layout;
import org.sgx.j2s.yui.ScriptLoader;
import org.sgx.j2s.yui.YuiDom;
import org.sgx.j2s.yui.YuiElement;
import org.sgx.j2s.yui.YuiUtils;


public class WidgetImplTest {
	public static void main(String[] args) {
		new ScriptLoader().load_yahoo_dom_events_element();

		JsUtils.activateJ2sDebug();
		test1();
		System.out.println(Utils.printAssertsFailed());
	}

	private static void test1() {	
		AbstractWidgetFactory factory = HtmlYuiImplWidgetFactory.getInstance();
		Widget w1 =(Widget) factory.createWidget(Widget.class);
//		w1.setNotifyChangeProperty(true);
//		w1.set("bounds", new Rectangle(0,0,3,3));
//		w1.addPropertyChangeListener(AbstractWidget.BOUNDS_PID, new PropertyChangeListener() {
//			public void notifyPropertyChange(PropertyChangeEvent e) {
//				System.out.println("bounds change: "+e);
//			}
//		});
		w1.setBackground(Color.BLACK);
		w1.setBounds(new Rectangle(450,450,150,150));

		Widget w11 = factory.createWidget(Widget.class);
		w11.setBackground(Color.RED);
		w11.setBounds(new Rectangle(10,10,40,40));
		w1.appendChild(w11);
		

		Widget w12 = factory.createWidget(Widget.class);
		w12.setBackground(Color.BLUE);
		w12.setBounds(new Rectangle(100,10,40,40));
		w1.appendChild(w12);
		
		
		Layout la1=new HorizontalBoxLayout();
		w1.setLayout(la1);
		la1.layout();
		
		//show root widget
		DomUtils.getBody().appendChild((Node) factory.getRepresentationOf(w1));
		
		Utils.assertTrue(YuiUtils.isChildren(w1.getId(), w11.getId()) &&
				YuiUtils.isChildren(w1.getId(), w12.getId()), "parentship1 ");

	}

}
