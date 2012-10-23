package org.sgx.j2s.widget;

import org.sgx.j2s.model.ModelElement;
import org.sgx.j2s.model.base.Color;
import org.sgx.j2s.model.base.Rectangle;
import org.sgx.j2s.util.List;
import org.sgx.j2s.widget.events.EventListener;
import org.sgx.j2s.widget.layout.Layout;



/** 
 *  a widget is a rectangular (bounds) visual entity that supports composition (parent / children)
 *  
 *  conformedd by 2 visual different parts: the self content and the widget children 
 *  
 *  this class must be represented by a rectangle of the correct bounds and background, containing childrens that 'layout' defines
 * @author sgurin
 *
 */
public interface Widget /*extends ModelElement*/{
//	
///*
// * widget events ids
// */
//	public static final String CHILDREN_PID = "children";
//	public static final String PARENT_PID ="parent";
//	public static final String BOUNDS_PID ="bounds";
//	public static final String LAYOUT_PID ="layout";
//	public static final String BACKGROUND_PID ="background";
//	public static final String ID_PID = "id";
			
	List<Widget> getChildren();
	void setChildren(List<Widget> children);
	void appendChild(Widget c);
	
	Widget getParent();
	void setParent(Widget p);
	
	Rectangle getBounds();
	void setBounds(Rectangle bounds);
	
	
	Layout getLayout();
	void setLayout(Layout l);
	
	
	Color getBackground();
	/**
	 * if backgroundColor==null then background will be (100%) transparent
	 * @param backgroundColor
	 */
	void setBackground(Color backgroundColor);
	
	String getId();
	void setId(final String id); 
	
	
	void setVisible(boolean b);
	void addMouseListener(EventListener l);
	
}
