package org.sgx.j2s.yui.widget;

import org.sgx.j2s.html.dom.util.DomUtils;
import org.sgx.j2s.js.JsUtils;
import org.sgx.j2s.model.base.Color;
import org.sgx.j2s.model.base.Rectangle;
import org.sgx.j2s.model.events.PropertyChangeEvent;
import org.sgx.j2s.model.events.PropertyChangeListener;
import org.sgx.j2s.util.Iterator;
import org.sgx.j2s.util.List;
import org.sgx.j2s.util.Map;
import org.sgx.j2s.widget.AbstractWidget;
import org.sgx.j2s.widget.Widget;
import org.sgx.j2s.widget.events.Event;
import org.sgx.j2s.yui.EventListener;
import org.sgx.j2s.yui.YuiDom;
import org.sgx.j2s.yui.YuiElement;
import org.sgx.j2s.yui.YuiEvent;

/**
 * we do not reference html dom element directly. 
 * use yui for html native stuff
 * 
 * i'm my own yui (native) event listener
 * @author SGURIN
 *
 */
public class WidgetImpl extends AbstractWidget implements EventListener {
	
	

	/** MAIN CONSTRUCTOR */
	public WidgetImpl(final String id) {
		super();
		setId(id);
		init();
	}

	private void init() {
		final Object o = getHTMLEl();
		YuiDom.setStyle(o, "position", "absolute");	
		this.layout=null;
		WidgetHome.getInstance().register(this);
		_registerEventListeners(o);
		
		_initDocument();
	}

	private void _registerEventListeners(Object o) {
		YuiEvent.addListener(o, YuiEvent.TYPE_CLICK, this, null);
		//TODO: registrar para todos
	}

	protected Object getHTMLEl() {
		return HtmlYuiImplWidgetFactory.getInstance().getRepresentationOf(this);
	}
	protected YuiElement getYUIEl() {
		return YuiElement.build(getId());
	}
	
	@Override
	public void setBackground(final Color background) {
		YuiDom.setStyle(getHTMLEl(), "background-color", background.toCSS());
		super.setBackground(background);
		
	}

	@Override
	public void setBounds(final Rectangle bounds) {
		final Object o = getHTMLEl();
		YuiDom.setStyle(o, "top", bounds.location.y+bounds.location.getUnit());
		YuiDom.setStyle(o, "left", bounds.location.x+bounds.location.getUnit());
		YuiDom.setStyle(o, "width", bounds.dimension.width+bounds.dimension.getUnit());
		YuiDom.setStyle(o, "height", bounds.dimension.height+bounds.dimension.getUnit());
		super.setBounds(bounds);
	}

	@Override
	public void setChildren(final List<Widget> children) {
		final Object o = getHTMLEl();
		DomUtils.removeAllChilds(DomUtils.getDocument().getElementById(getId()));
		final Iterator<Widget> i = children.iterator();
		while(i.hasNext()) {
			final Object c = HtmlYuiImplWidgetFactory.getInstance().getRepresentationOf(i.next());
			YuiDom.appendChild(o,c);
		}
		super.setChildren(children);
	}

	@Override
	public void setParent(final Widget parent) {	
		final Object parentRepr = HtmlYuiImplWidgetFactory.getInstance().getRepresentationOf(parent);
		final Object self = HtmlYuiImplWidgetFactory.getInstance().getRepresentationOf(this);
		YuiDom.setParent(self, parentRepr);
		super.setParent(parent);
	}
	

	@Override
	public void setVisible(final boolean b) {
		YuiDom.setStyle(getId(), "display", b?"inline":"none");
	}
	
	public String toString() {
		return "yuiWidget("+getId()+")";
	}

	public void appendChild(Widget c) {
		super.appendChild(c);
		final Object childRepr = HtmlYuiImplWidgetFactory.getInstance().getRepresentationOf(c);
		final Object self = HtmlYuiImplWidgetFactory.getInstance().getRepresentationOf(this);
		YuiDom.appendChild(self, childRepr);
	}

	public Object run(Event e, Object obj) {		
//			System.out.println("****\n"+DomUtils.printEvent(e)+"\n******");
			notifyAllMouseListeners(e);
			return false;
	}
	
	
	//private stuff
	/** initializations on dom document */
	private static boolean _docInitialized=false;	
	public static EventListener _oncontextMenuListener = new EventListener() {
		public Object run(Event e, Object obj) {		
			WidgetImpl w = WidgetHome.getInstance().get(e.target.id);
			w.run(e, obj);
			return false;
		}				
	};	
	static void _initDocument() {
		if(!_docInitialized) {			
			Object oncontextMenuListener = _oncontextMenuListener;
			/**
			 * @j2sNative
			 * System.out.println("instalado "+oncontextMenuListener.run);
			 * document.body.oncontextmenu = oncontextMenuListener.run
			 */{}
			_docInitialized=true;
		}
	}

}
