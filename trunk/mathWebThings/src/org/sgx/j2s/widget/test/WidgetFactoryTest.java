package org.sgx.j2s.widget.test;

import org.sgx.j2s.html.myApi.DomUtils;
import org.sgx.j2s.html.myApi.ElementWrapper;
import org.sgx.j2s.widget.IWidget;
import org.sgx.j2s.widget.WidgetFactory;

public class WidgetFactoryTest {

	public static void main(String[] args) {
		test1();
	}

	private static void test1() {
		WidgetFactory wf = WidgetFactory.getInstance();
		IWidget w1 = wf.createRootWidget(new ElementWrapper(DomUtils.getBody()));
		
		
	}

}
