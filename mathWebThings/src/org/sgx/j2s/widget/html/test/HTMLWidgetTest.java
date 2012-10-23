package org.sgx.j2s.widget.html.test;

import org.sgx.j2s.html.myApi.DomUtils;
import org.sgx.j2s.html.myApi.ElementWrapper;
import org.sgx.j2s.widget.Widget;
import org.sgx.j2s.widget.base.Color;
import org.sgx.j2s.widget.base.Rectangle;
import org.sgx.j2s.widget.html.HTMLWidget;

public class HTMLWidgetTest {

	
	public static void main(String[] args) {
		test1();
	}

	private static void test1() {
		//DomUtils.createElement("span", DomUtils.getBody()).innerHTML="hello";
		
		HTMLWidget r = new HTMLWidget(new ElementWrapper(DomUtils.getBody()));
		r.setBackground(Color.RED);
		r.setBounds(new Rectangle(200,2,200,200));
		r.setType(Widget.TYPE_IMAGE);
		r.setData("img32.png");
		
		System.out.println("the end");
	}

}
