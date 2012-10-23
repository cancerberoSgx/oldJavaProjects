//package org.sgx.j2s.widget.swt.tests;
//
//import java.io.InputStream;
//import java.util.List;
//
//import org.eclipse.swt.SWT;
//import org.eclipse.swt.events.KeyEvent;
//import org.eclipse.swt.events.KeyListener;
//import org.eclipse.swt.events.MouseEvent;
//import org.eclipse.swt.events.MouseListener;
//import org.eclipse.swt.events.MouseMoveListener;
//import org.eclipse.swt.events.MouseTrackListener;
//import org.eclipse.swt.graphics.Image;
//import org.eclipse.swt.graphics.ImageData;
//import org.eclipse.swt.widgets.Composite;
//import org.eclipse.swt.widgets.Display;
//import org.eclipse.swt.widgets.Label;
//import org.sgx.j2s.widget.Widget;
//import org.sgx.j2s.widget.IWidget;
//import org.sgx.j2s.widget.base.Color;
//import org.sgx.j2s.widget.base.Font;
//import org.sgx.j2s.widget.base.Rectangle;
//import org.sgx.j2s.widget.events.Event;
//import org.sgx.swt.SWTUtils;
//
//public class CopyOfSWTWidget extends Widget implements MouseListener/*, ControlListener*/, MouseMoveListener, MouseTrackListener, KeyListener{	
//	/** the only way of desasociate widgets from its parent os reparent them. So we create a dummy parent when we need to desasociate widgets */ 
//	private static Composite _dummyParent; 
//	private Composite _repr_Root;
//	private Composite _repr_ContentAreaRepr;
//	private Label _repr_label;
//	private Image _originalImg;
//	
//	public CopyOfSWTWidget(Composite parentWidget) {
//		super();
//		init(parentWidget);		
//	}
//	
//	public CopyOfSWTWidget(CopyOfSWTWidget parent) {
//		this(parent._repr_ContentAreaRepr);
//	}
//
//	private void init(Composite parentWidget) {	
//		parentWidget.setLayout(null);
//		_repr_Root = new Composite(parentWidget, SWT.NONE);
//		_repr_Root.setLayout(null);
//		_repr_ContentAreaRepr= new Composite(_repr_Root, SWT.NONE);
////		_repr_ContentAreaRepr.setSize(23,23);
//		_repr_ContentAreaRepr.setLayout(null);		
////		_repr_ContentAreaRepr.setBackgroundImage()
//		_repr_label=new Label(_repr_ContentAreaRepr, SWT.NONE);
//		_repr_label.setVisible(false);
//		
//		_repr_ContentAreaRepr.addMouseListener( this);
////		_repr_Root.addControlListener(this);
//		_repr_ContentAreaRepr.addMouseMoveListener(this);
//		_repr_ContentAreaRepr.addMouseTrackListener(this);
//		_repr_ContentAreaRepr.addKeyListener(this);
//	}
//
//	public boolean isVisible() {
//		return _repr_Root.isVisible();
//	}
//
//	public void setVisible(boolean b) {
//		_repr_Root.setVisible(b);
//	}	
///**
// * returns a SWT Composite
// */
//	public Object getNativeRepr() {		
//		return _repr_Root;
//	}	
//
//	public void setFont(Font font) {
//		this.font=font;
//		// TODO Auto-generated method stub
//		
//	}
//	
//	public void setBackground(Color background) {
//		this.background = background;
//		org.eclipse.swt.graphics.Color c = SWTUtils.colorToSWT(background);
//		_repr_Root.setBackground(c);
//		_repr_ContentAreaRepr.setBackground(c);
//	}
//	
//
//	public void setData(String s) {
//		this.data=s;
//		if(type==TYPE_IMAGE){
//			InputStream io = CopyOfSWTWidget.class.getClassLoader().getResourceAsStream(s);
//			_originalImg = new Image(Display.getCurrent(),io);
//		}
//		else if(type==TYPE_TEXT){
//			_repr_label.setText(data);
//		}
//		_updateContents();
//	}
//	
//
//	public void setType(String type) {
//		this.type=type;	
//		_updateContents();
//	}
//
//	/**
//	 * update widget contents (not childrens)
//	 */
//	private void _updateContents() {
//		if(type==TYPE_IMAGE){
//			//la imagen la representamos con _repr_label.setBackgroundImage()
//			
//			if(_originalImg==null)
//				setData(data);
//			
//						
//			org.eclipse.swt.graphics.Rectangle contentBounds = _repr_ContentAreaRepr.getBounds();				
//			//_originalImg.getBounds();
//			ImageData scaledImgData = _originalImg.getImageData().scaledTo(contentBounds.width, contentBounds.height);
//			Image scaledImg = new Image(Display.getCurrent(),scaledImgData);
//			
////			_repr_ContentAreaRepr.setBackgroundImage(_originalImg);
//			
//			_repr_label.setImage(_originalImg);
//			_repr_label.setBackground(SWTUtils.colorToSWT(Color.GREEN));
//			_repr_label.setBounds(contentBounds);
//			_repr_label.setVisible(true);
//		}
//		else if(type==TYPE_TEXT){
//
//		}
//		else{
//
//		}	
//	}
//
//	public void setContentArea(Rectangle a) {
//		contentArea=a;
//		_repr_ContentAreaRepr.setBounds(SWTUtils.rectangleToSWT(contentArea));
//	}
//
//	public void setBounds(Rectangle bounds) {
//		this.bounds = bounds;// TODO Auto-generated method stub
//		_repr_Root.setBounds(SWTUtils.rectangleToSWT(bounds));
//		_repr_ContentAreaRepr.setBounds(SWTUtils.rectangleToSWT(getContentArea()));
//	}
//		
//	public void setParent(IWidget parent) {
//		this.parent = parent;
//		_repr_Root.setParent(((CopyOfSWTWidget)parent)._repr_Root);
//		// TODO Auto-generated method stub
//	}
//	public void setChildren(List<IWidget> children) {
//		throw new RuntimeException("not implemented");
////		this.children = children;
////		SWTUtils.compositeDestroyAllChildrens(c)
////		((Composite)c.getNativeRepr()).rem
//		// TODO Auto-generated method stub
//	}
//	public void removeChildren(IWidget c) {
//		if(getChildren().contains(c))
//			((Composite)c.getNativeRepr()).setParent(_dummyParent);		
//	}	
//	public void appendChild(IWidget c) {
//		this.children.add(c);
//		((Composite)c.getNativeRepr()).setParent(_repr_ContentAreaRepr);
//	}
//
//	public boolean forceFocus() {
//		return _repr_ContentAreaRepr.forceFocus();
//	}
//	
//	
//	
//	//event utility
//	public static  Event buildEventFrom(int type, MouseEvent me){
//		Event e = new Event();
//		e.type=type;
//		e.count= me.count;
//		if(me.button==1)
//			e.button=1;
//		else if(me.button==3)
//			e.button=2;
//		else if(me.button==2)
//			e.button=3;
//		//TODO: todo lo demás
//		return e;		
//	}
//	private Event buildEventFrom(int type, KeyEvent arg0) {
//		Event e = new Event();
//		e.type=type;
//		e.character = arg0.character;
//		//TODO: todo lo demás
//		return e;
//	}
//	public void mouseDoubleClick(MouseEvent arg0) {
//		Event e = buildEventFrom(Event.ONCLICK_TYPE, arg0);
//		e.count=2;
//		notifyAllEventListeners(e);
//	}
//	public void mouseDown(MouseEvent arg0) {
////		System.out.println("mouseDown");
//		_repr_ContentAreaRepr.forceFocus();
//		Event e = buildEventFrom(Event.ONMOUSEDOWN_TYPE, arg0);
//		notifyAllEventListeners(e);
//	}
//
//	public void mouseUp(MouseEvent arg0) {
////		System.out.println("mouseUp");
//		Event e = buildEventFrom( Event.ONMOUSEUP_TYPE,arg0);
//		notifyAllEventListeners(e);
//	}
//	public void mouseMove(MouseEvent arg0) {
////		System.out.println("mouseMove");
//		Event e = buildEventFrom(Event.ONMOUSEMOVE_TYPE, arg0);
//		notifyAllEventListeners(e);
//	}
//
//	public void mouseEnter(MouseEvent arg0) {
////		System.out.println("mouseEnter");
//		Event e = buildEventFrom(Event.ONMOUSEIN_TYPE, arg0);
//		notifyAllEventListeners(e);
//	}
//
//	public void mouseExit(MouseEvent arg0) {
////		System.out.println("mouseLeave");
//		Event e = buildEventFrom(Event.ONMOUSEOUT_TYPE, arg0);
//		notifyAllEventListeners(e);
//		
//	}
//
//	public void mouseHover(MouseEvent arg0) {
//		Event e = buildEventFrom(Event.ONMOUSEOVER_TYPE, arg0);
//		notifyAllEventListeners(e);
//	}
//
//	public void keyPressed(KeyEvent arg0) {
//		Event e = buildEventFrom(Event.ONKEYPRESSED_TYPE, arg0);
//		notifyAllEventListeners(e);
//	}
//
//	public void keyReleased(KeyEvent arg0) {		
//		Event e = buildEventFrom(Event.ONKEYRELEASE_TYPE, arg0);
//		notifyAllEventListeners(e);
//	}
//	
//}
