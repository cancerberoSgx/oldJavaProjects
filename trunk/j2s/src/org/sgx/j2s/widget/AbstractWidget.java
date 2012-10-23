package org.sgx.j2s.widget;

import org.sgx.j2s.model.AbstractModelElement;
import org.sgx.j2s.model.base.Color;
import org.sgx.j2s.model.base.Rectangle;
import org.sgx.j2s.model.events.PropertyChangeListener;
import org.sgx.j2s.util.Iterator;
import org.sgx.j2s.util.List;
import org.sgx.j2s.util.Map;
import org.sgx.j2s.util.impl.LWMap;
import org.sgx.j2s.util.impl.LinkedList;
import org.sgx.j2s.util.impl.Utils;
import org.sgx.j2s.widget.events.Event;
import org.sgx.j2s.widget.events.EventListener;
import org.sgx.j2s.widget.layout.Layout;

/**
 * native implementators can implement only setters for actually make the native visual transformation  and call super.setX() for the model stuff. see htmlyuiimpl
 * @author sgurin
 *
 */
public abstract class AbstractWidget /* extends AbstractModelElement */ implements Widget{

	protected Layout layout = null;
	Color background = Color.BLACK;
	List<Widget> children = new LinkedList<Widget>();
	Rectangle bounds= new Rectangle(2,2,50,50);
	Widget parent=null;
	String id;
	
//	static Map widgetProps = Utils.transformArrayToMap(new Object[][]
//	        { {"layout", Layout.class}, 
//			{"background", Color.class}, 
//			{"children", Integer.class}, 
//			{"bounds", Rectangle.class}, 
//			{"parent", Widget.class}}
//	);
//	@Override
//	public Map getProperties() {
//		return widgetProps;
//	}

	public Layout getLayout() {
		return layout;
	}

	public void setLayout(Layout layout) {
		this.layout = layout;
		layout.setHost(this);
//		_afterSet(Widget.LAYOUT_PID);
	}

	public Color getBackground() {
		return background;
	}

	public void setBackground(Color background) {
		this.background = background;
//		_afterSet(Widget.BACKGROUND_PID);
	}

	public List<Widget> getChildren() {
		return children;
	}

	public void setChildren(List<Widget> children) {
		this.children = children;
//		_afterSet(Widget.CHILDREN_PID);
	}
	
	public void appendChild(Widget c) {
		this.children.append(c);
//		_afterSet(Widget.CHILDREN_PID);
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
//		_afterSet(Widget.BOUNDS_PID);
	}

	public Widget getParent() {
		return parent;
	}

	public void setParent(Widget parent) {
		this.parent = parent;
//		_afterSet(Widget.PARENT_PID);
	}
	
	public String getId() {
		return id;
	}
	public void setId(final String id) {
		this.id = id;
//		_afterSet(Widget.ID_PID);
	}
	

	public abstract void setVisible(boolean b);
	
	
	//events
	List<EventListener> mouseListeners = new LinkedList<EventListener>();
	public void addMouseListener(EventListener l){
		mouseListeners.append(l);
	}
	public void removeMouseListener(EventListener l){
		mouseListeners.remove(l);
	}
	public void notifyAllMouseListeners(Event e) {
		Iterator<EventListener> i = mouseListeners.iterator();
		while(i.hasNext()) {
			i.next().handleEvent(e);
		}
	}
}
