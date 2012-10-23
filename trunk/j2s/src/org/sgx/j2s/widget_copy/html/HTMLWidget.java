package org.sgx.j2s.widget_copy.html;

import java.util.List;

import org.sgx.j2s.model.base.Color;
import org.sgx.j2s.model.base.Font;
import org.sgx.j2s.model.base.Rectangle;
import org.sgx.j2s.widget_copy.AbstractWidget;
import org.sgx.j2s.widget_copy.Widget;
import org.sgx.j2s.widget_copy.layout.Layout;
/**
 * widget implementation in pure html. I need that the widget id to be a valid html id attr for reference it. 
 *
 */
public class HTMLWidget extends AbstractWidget{

	//utilities
	
	private void _setStyleProp(String propId, String val) {
		Object o = getHTML();
		/**
		 * @j2sNative
		 * o.style[propId] = val;
		 */{}
	}
	private Object getHTML() {
		@SuppressWarnings("unused")
		String htmlId = getId();
		Object o = null;
		/**
		 * @j2sNative
		 * o=document.getElementById(htmlId);
		 */{}
		 return o;
	}
	@Override
	public void setBackground(Color c) {		
		super.setBackground(c);
		_setStyleProp("background-color", c.toCSS());
	}

	@Override
	public void setBounds(Rectangle r) {		
		super.setBounds(r);
		_setStyleProp("top", r.location.y+"px");
		_setStyleProp("left", r.location.x+"px");
		_setStyleProp("width", r.dimension.width+"px");
		_setStyleProp("height", r.dimension.height+"px");
	}

	public void appendChild(Widget c) {
		
	}
	public void addChildren(int index, Widget c) {
		
	}
	public void removeChildren(Widget w) {
		
	}
	public void removeChildren(int ind) {
		
	}
	public void removeAllChildren() {
		
	}
	
	@Override
	public void setChildren(List<Widget> children) {
		super.setChildren(children);
		removeAllChildren();
		for(Widget c : children) {
			if(c instanceof HTMLWidget) {
//				HTMLWidget htmlw = (HTMLWidget)c;
				appendChild(c);
			}
		}
	}

	@Override
	public void setFont(Font font) {
		
		super.setFont(font);
	}

	@Override
	public void setHtmlContent(String html) {
		
		super.setHtmlContent(html);
	}

	@Override
	public void setHtmlSource(String url) {
		
		super.setHtmlSource(url);
	}

	@Override
	public void setId(String id) {
		
		super.setId(id);
	}

	@Override
	public void setImageSource(String url) {
		
		super.setImageSource(url);
	}

	@Override
	public void setLayout(Layout layout) {
		
		super.setLayout(layout);
	}

	@Override
	public void setParent(Widget parent) {
		
		super.setParent(parent);
	}

	@Override
	public void setText(String s) {
		
		super.setText(s);
	}

	@Override
	public void setVisible(boolean b) {
		
		super.setVisible(b);
	}



}
