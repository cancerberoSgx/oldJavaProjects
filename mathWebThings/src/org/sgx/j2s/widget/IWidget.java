package org.sgx.j2s.widget;

//import org.sgx.j2s.base.ModelElement;
import java.util.List;
import java.util.Set;

//import org.sgx.j2s.util.List;
import org.sgx.j2s.widget.base.Color;
import org.sgx.j2s.widget.base.Font;
import org.sgx.j2s.widget.base.Point;
import org.sgx.j2s.widget.base.Rectangle;
import org.sgx.j2s.widget.events.Event;
import org.sgx.j2s.widget.events.UIEvent;
import org.sgx.j2s.widget.events.EventListener;
import org.sgx.j2s.widget.layout.Layout;



/** 
 *  a widget is a rectangular (bounds) visual entity that supports composition 
 *  (parent / children), layout, and simple mouse/key events (only those supported 
 *  by a browser)
 *  
 *  a widget contents can be an image or a text (in this case support for basic text 
 *  properties is given)
 *  
 *  this class must be represented by a rectangle of the correct bounds and background, containing childrens that 'layout' defines
 *  
 *  this is the base interface for all the base widget foundation. 
 *  the idea is to minimize it (because it is supported to be implemented in different techs)
 * @author sgurin
 *
 */
public interface IWidget{
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
			
	List<IWidget> getChildren();
	void setChildren(List<IWidget> children);
	void appendChild(IWidget c);
	void removeChildren(IWidget c);
	
	IWidget getParent();
	void setParent(IWidget p);
	
	Rectangle getBounds();
	void setBounds(Rectangle bounds);	
	
//	Rectangle getContentArea();
//	/** a rectangle relative to getBounds() indicating the children and content will be positioned */
//	void setContentArea(Rectangle a);
	
	Layout getLayout();
	void setLayout(Layout l);
	
//	//como swt o swing: se le pasa al widget el layout data: NO NO VA MAS. el layoutData se debe setear enb el layout . la realaci�n widget - layout siempre ser� 1-1
//	Object getLayoutData();
//	void setLayoutData(Object ld);
	
	Color getBackground();
	/**
	 * if backgroundColor==null then background will be (100%) transparent. default: background==null
	 */
	void setBackground(Color backgroundColor);
	
	String getId();
	void setId(final String id); 
	
	boolean hasClass(String className);
	void addClass(String className);
	void removeClass(String className);
	Set<String> getClasses();
	
	
	void setVisible(boolean b);
	boolean isVisible();
	
	
	//content	
	static String TYPE_IMAGE="image", TYPE_TEXT="text", TYPE_NONE="none"; 
	String getType();
	void setType(String type);
	
	 String getData();
	 /** in the case of being a text widget it reference the text. in the case of being of type image it reference the image source. */
	 void setData(String s);
	
	 Font getFont();
	 void setFont(Font font);
	 
	 
	 //events
	void addEventListener(int eventType, EventListener l);
	void removeEventListener(int eventType, EventListener l);
	/** event posting*/
	public void notifyAllEventListeners(Event e);
	
		 /** this is the most important method. it returns the native representation object. Each implementation willknow... for example a html represerntation can return a html dom object, a swt impl ca return a swt.Composite, a swing repr can return a JPanel, etc*/
	Object getNativeRepr(); 
	/**
	 *  Forces the receiver to have the <em>keyboard focus</em>, causing
	 * all keyboard events to be delivered to it.
	 */
	boolean forceFocus();
//	Point getAbsolutePosition();
	
}
