package org.sgx.j2s.widget;

import org.eclipse.swt.widgets.Composite;
import org.sgx.j2s.html.myApi.ElementWrapper;
import org.sgx.j2s.utils.JsUtils;
import org.sgx.j2s.utils.Utils;
import org.sgx.j2s.widget.html.HTMLWidget;
import org.sgx.j2s.widget.swt.SWTWidget;


public class WidgetFactory {

	private static WidgetFactory instance;
	public static WidgetFactory getInstance() {
		if(instance==null)
			instance = new WidgetFactory();
		return instance;
	}
	private WidgetFactory(){}
	
	/**
	 * @param parent - the native parent for the new root widget.
	 * For example swt needs a Composite parent, html a ElementWrapper
	 * , swing a Jcontainer, etc
	 */
	public IWidget createRootWidget(Object parent) {
		return createWidgetWithNativeParent(parent);
	}
	public IWidget createWidget(IWidget parent) {
		return createWidgetWithNativeParent(parent.getNativeRepr());
	}
	private IWidget createWidgetWithNativeParent(Object parent) {
		if(JsUtils.isJs()) {
			return new HTMLWidget((ElementWrapper)Utils.castOrThrow(parent, ElementWrapper.class, "invalid parent class"));
		}
		else { //is java - use swt impl			
			return new SWTWidget((Composite)Utils.castOrThrow(parent, Composite.class, "invalid parent class"));
		}
	}
	 

	
}
