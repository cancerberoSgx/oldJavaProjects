//package org.sgx.j2s.widget;
//
//import java.util.*;
//
//import org.eclipse.swt.widgets.Composite;
//import org.sgx.j2s.widget.base.*;
//import org.sgx.j2s.widget.events.UIEvent;
//import org.sgx.j2s.widget.events.EventListener;
//import org.sgx.j2s.widget.layout.Layout;
//import org.sgx.j2s.widget.swt.SWTWidget;
//import org.sgx.swt.SWTUtils;
//
///** 
// * new widget impls must extend this class, so the only methods to implement natively are reduced.
// * 
// * this abstract widget tell suggest widget implementors what must be implemented and what not.
// * @author sgurin
// *
// */
//public abstract class WidgetNoProps implements IWidget{
//	
//	private static long _idCounter=0;
//	private static long _getNextId(){
//		_idCounter++;
//		return _idCounter;
//	}
//	
//	protected Layout layout = null;
//	protected Color background = null;
//	protected List<IWidget> children = new LinkedList<IWidget>();
//	protected Rectangle bounds= new Rectangle(2,2,50,50);
//	protected IWidget parent=null;
//	protected String id;
//
//	protected Font font=null;
//	protected String data=null;
//	protected WidgetClassManager _classManager= WidgetClassManager.getInstance();
//	
////	protected Rectangle contentArea= null;
//	protected String type=TYPE_NONE;
//	private Map<Integer, Set<EventListener>> listeners = new HashMap<Integer, Set<EventListener>>();
//	private Object layoutData;
//	private boolean visible;
//	
//
//	public WidgetNoProps(){
//		setId(_getNextId()+"SWTWidget");
//	}
//	
//	public String getType() {
//		return type;
//	}
//	
//	public Color getBackground() {
//		return background;
//	}	
//
//	public List<IWidget> getChildren() {
//		return children;
//	}
//	
////	public Rectangle getContentArea() {
////		return contentArea;
////	}
//	
//	public Rectangle getBounds() {
//		return bounds;
//	}
//public void setDimension(Dimension d) {
//	setBounds(new Rectangle(bounds.location, d));
//}
//public void setLocation(Point p) {
//	setBounds(new Rectangle(p, bounds.dimension));
//}
//	public String getData() {
//		return data;
//	}
//
//	public void setId(final String id) {
//		this.id = id;
//	}
//
//	public String getId() {
//		return id;
//	}
//	public boolean hasClass(String className) {
//		return _classManager.hasClass(this, className);
//	}
//
//	public void removeClass(String className) {
//		_classManager.removeClass(this, className);
//	}
//	public void addClass(String className) {
//		_classManager.addClass(this, className);
//	}
//	
//	public Set<String> getClasses() {
//		return _classManager.getClasses(this);
//	}
//	
//
//	public IWidget getParent() {
//		return parent;
//	}
//	public final void removeChildren(IWidget c) {
//		children.remove(c);
//		removeChildrenNatively(c);
//	}	
//	protected abstract  void removeChildrenNatively(IWidget c);
//		
//		
//	public final void setChildren(List<IWidget> children) {
//		throw new RuntimeException("not implemented");
//	}
//	
//	public final void appendChild(IWidget c) {
//		this.children.add(c);
//		appendChildNatively(c);
//	}	
//	protected abstract void appendChildNatively(IWidget c);
//
//	
//	
//	
//	public final void setBounds(Rectangle bounds) {
//		this.bounds = bounds;// TODO Auto-generated method stub
//		setBoundsNatively(bounds);
//	}		
//	protected abstract void setBoundsNatively(Rectangle bounds);
//
//	public final void setBackground(Color background) {
//		this.background = background;
//		setBackgroundNatively(background);
//		
//	}	
//	protected abstract void setBackgroundNatively(Color background);
//
//	public final void setFont(Font font) {
//		this.font=font;
//		setFontNatively(font);		
//	}	
//	protected abstract void setFontNatively(Font font2);
//
//
//	public final void setData(String s) {
//		this.data=s;
//		setDataNatively(s);
//	}		
//	protected abstract void setDataNatively(String s);
//	public final void setType(String type) {
//		this.type=type;	
//		setTypeNatively(type);
//	}
//	protected abstract void setTypeNatively(String type);
//
//	public final void setVisible(boolean b){
//		this.visible=b;
//		setVisibleNatively(b);
//	}
//	protected abstract void setVisibleNatively(boolean b);
//
//	public boolean isVisible(){
//		return visible;
//	}
//	
//	public void setParent(IWidget parent) {
//		this.parent = parent;
//		setParentNatively(parent);
//	}	
//	protected abstract void setParentNatively(IWidget parent2);
//
//
//	//events
//
//	public void addEventListener(int eventType, EventListener l){
//		if(listeners.get(eventType)==null)
//			listeners.put(eventType, new HashSet<EventListener>());
//		listeners.get(eventType).add(l);
//	}
//	public void removeEventListener(int eventType, EventListener l){
//		if(listeners.get(eventType)==null)
//			listeners.put(eventType, new HashSet<EventListener>());
//		listeners.get(eventType).remove(l);
//	}
//	public void notifyAllEventListeners(UIEvent e) {
//		if(listeners.get(e.type)==null)
//			listeners.put(e.type, new HashSet<EventListener>());
//		Iterator<EventListener> i = listeners.get(e.type).iterator();
//		while(i.hasNext()) {
//			i.next().handleEvent(e);
//		}
//	}
//	
//	public Font getFont() {
//		return font;
//	}
//	
//	
//	
////	public Object getLayoutData(){
////		return this.layoutData;
////	}
////	public void setLayoutData(Object ld){
////		this.layoutData = ld;
////	}
//	public Layout getLayout() {
//		return layout;
//	}
//	public void setLayout(Layout layout) {
//		this.layout = layout;
//		layout.setHost(this);
//	}
//	
//}
