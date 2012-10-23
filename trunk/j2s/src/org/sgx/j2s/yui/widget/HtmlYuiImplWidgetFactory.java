package org.sgx.j2s.yui.widget;

import org.sgx.j2s.html.dom.Element;
import org.sgx.j2s.html.dom.Node;
import org.sgx.j2s.html.dom.util.DomUtils;
import org.sgx.j2s.util.impl.Utils;
import org.sgx.j2s.widget.AbstractWidgetFactory;
import org.sgx.j2s.widget.HTMLWidget;
import org.sgx.j2s.widget.ImageWidget;
import org.sgx.j2s.widget.TextWidget;
import org.sgx.j2s.widget.Widget;
import org.sgx.j2s.yui.YuiDom;
import org.sgx.j2s.yui.YuiElement;
import org.sgx.j2s.yui.YuiLang;
import org.sgx.j2s.yui.YuiUtils;

/**
 * we will associate widgets to html element ids for not to reference them directly (mem leaks)
 * @author SGURIN
 *
 */
public class HtmlYuiImplWidgetFactory extends AbstractWidgetFactory {

	static {
		inst = new HtmlYuiImplWidgetFactory();
	}
	public static final String WidgetElementTag = "span";
	public static final String HTMLWidgetElementTag = "span";
	public static final String ImageWidgetElementTag = "img";
	public static final String InputWidgetElementTag = "input";
	public static final String TextWidgetElementTag = "span";
	
	
	@Override
	/**
	 * creates a new html element representing w, and appends it to document.body
	 * @returns html id of html dom representator object 
	 */
	public Object createRepresentationFor(Widget w) {
		if(!(w instanceof WidgetImpl))
			throw new RuntimeException("this factory only works for "+WidgetImpl.class.getName()+" widgets");
		WidgetImpl wi = (WidgetImpl)w;
		
		Element thisEl=null;
		if(w instanceof ImageWidget) {
			thisEl = DomUtils.getDocument().createElement(ImageWidgetElementTag);
		}
//		else if(w instanceof InputWidget) {
//			thisEl = DomUtils.getDocument().createElement(InputWidgetElementTag);
//			YuiElement.build(thisEl).set("type", "text");
//		}
		else if(w instanceof TextWidget) {
			thisEl = DomUtils.getDocument().createElement(TextWidgetElementTag);
		}
		else if(w instanceof HTMLWidget) {
			thisEl = DomUtils.getDocument().createElement(HTMLWidgetElementTag);
		}
		else if(w instanceof Widget) {
			thisEl = DomUtils.getDocument().createElement(WidgetElementTag);			
		}
		if(wi.getId()==null || wi.getId().equals(""))			
			wi.setId("widget_"+Utils.getUnique());
		
		thisEl.setAttribute("id",wi.getId());

		getWidgetContainer().appendChild(thisEl);
		return wi.getId();		
	}
	

	private static final String widgetContainerId = "__wc__";
	private static final String widgetContainerTag = "span";
	/**returns a hiden element that is the home of new widgets
	 */
	private Node getWidgetContainer() {
		 Element wc = DomUtils.getDocument().getElementById(widgetContainerId);
		 if(wc==null) {
			 wc=DomUtils.getDocument().createElement(widgetContainerTag);
			 wc.id=widgetContainerId;
			 YuiDom.setStyle(wc, "display", "none");
			 DomUtils.getBody().appendChild(wc);
		 }
		 return wc;
	}
	
	/**
	 * creates and returns a new widget
	 * @param nativeParent null for create a
	 * html dom element ided htmlParentId
	 */
	public Widget createWidget(Class _class) {
		Widget w = null;
		if(_class.getName().equals(Widget.class.getName()))
			w=new WidgetImpl("widget_"+Utils.getUnique());
		else if(_class.getName().equals(TextWidget.class.getName()))
			w=new WidgetImpl("widget_"+Utils.getUnique());	
		else if(_class.getName().equals(ImageWidget.class.getName()))
			w=new WidgetImpl("widget_"+Utils.getUnique());
		else if(_class.getName().equals(HTMLWidget.class.getName()))
			w=new WidgetImpl("widget_"+Utils.getUnique());
		else
			throw new RuntimeException(_class.getName()+" natige widget class name not recognized");
		Node repr = (Node)getRepresentationOf(w);
		return w;
	}
	
	@Override
	public Object getRepresentationOf(Widget w) {
		Object htmlid =  getReprMap().get(w);
		if(htmlid==null) {
			htmlid=createRepresentationFor(w);
			getReprMap().put(w, htmlid);
		}
		return DomUtils.getDocument().getElementById((String) htmlid);
	}

}
