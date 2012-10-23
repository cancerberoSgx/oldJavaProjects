package org.sgx.j2s.widget_copy;

import java.util.EventListener;
import java.util.List;

import org.sgx.j2s.model.base.Color;
import org.sgx.j2s.model.base.Font;
import org.sgx.j2s.model.base.Rectangle;
import org.sgx.j2s.widget_copy.layout.Layout;



/** 
 *  a widget is a rectangular (bounds) visual entity that supports composition (parent / children)
 *  
 *  conformedd by 2 visual different parts: the self content and the widget children 
 *  
 *  this class must be represented by a rectangle of the correct bounds and background, containing childrens that 'layout' defines
 * @author sgurin
 *
 */
public interface Widget {
	int HTML = 3;
	int RECTANGLE = 2;
	int IMAGE = 1;
	
	/**
	 * type: a non settable property which value must be some of html, rectangle, image
	 */
	int getType();
	
	
	//parent-child - structure
	List<Widget> getChildren();
	void setChildren(List<Widget> children);
	void appendChild(Widget c);	
	void addChildren(int index, Widget c);
	void removeChildren(Widget w);
	void removeChildren(int ind);
	void removeAllChildren();
	Widget getParent();
	void setParent(Widget p);
	
	//main properties
	Rectangle getBounds();
	void setBounds(Rectangle bounds);
	Layout getLayout();
	void setLayout(Layout l);	
	Color getBackground();
	String getId();
	void setId(String id);
	/**
	 * if backgroundColor==null then background will be (100%) transparent
	 * @param backgroundColor
	 */
	void setBackground(Color backgroundColor);	
	void setVisible(boolean  b);
	
	//text type
	public String getText();
	public void setText(String s);	
	public Font getFont();
	public void setFont(Font font);
	
	//html type
	String getHtmlContent();
	void setHtmlContent(String html);
	String getHtmlSource();
	void setHtmlSource(String url);
	
	//image type
	String getImageSource();
	void setImageSource(String url);
	
}
