package org.sgx.j2s.widget.swt;

import java.io.InputStream;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.MouseTrackListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.LineAttributes;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.sgx.j2s.widget.Widget;
import org.sgx.j2s.widget.IWidget;
import org.sgx.j2s.widget.base.Color;
import org.sgx.j2s.widget.base.Font;
import org.sgx.j2s.widget.base.Point;
import org.sgx.j2s.widget.base.Rectangle;
import org.sgx.j2s.widget.events.UIEvent;
import org.sgx.swt.SWTUtils;

/**
 * THIS SWTWIDGET doesn't support java2script
 * 
 * @author sebastian
 *
 */
public class SWTWidget extends Widget implements MouseListener/*, ControlListener*/, MouseMoveListener, MouseTrackListener, KeyListener, Listener{	
	/** the only way of desasociate widgets from its parent os reparent them. So we create a dummy parent when we need to desasociate widgets */ 
	private static Composite _dummyParent;

	private Composite _handler;
	private Image _originalImg;

	public SWTWidget(Composite parentWidget) {
		super();
		init(parentWidget);		
	}

	public SWTWidget(SWTWidget parent) {
		this(parent._handler);
	}





	private void init(Composite parentWidget) {	
		parentWidget.setLayout(null);
		_handler = new Composite(parentWidget, SWT.BORDER);
		_handler.setLayout(null);

		_handler.addMouseListener( this);
		_handler.addMouseMoveListener(this);
		_handler.addMouseTrackListener(this);
		_handler.addKeyListener(this);
		_handler.addListener(SWT.Paint, this);
	}

	//IWidget method implementation left: 
	@Override
	protected void setFontNatively(Font font2) {		
	}

	@Override
	protected void setDataNatively(String s) {
		if(type==TYPE_IMAGE){
			InputStream io = SWTWidget.class.getClassLoader().getResourceAsStream(s);
			_originalImg = new Image(Display.getCurrent(),io);
		}
		else if(type==TYPE_TEXT){
		}
		_updateContents();
	}
	/**
	 * returns a SWT Composite
	 */
	public Composite getNativeRepr() {		
		return _handler;
	}	

	@Override
	protected void setParentNatively(IWidget parent) {
		_handler.setParent(((SWTWidget)parent)._handler);
	}

	@Override
	protected void setVisibleNatively(boolean b) {
		_handler.setVisible(b);
	}

	@Override
	protected void appendChildNatively(IWidget c) {
		this.children.add(c);
		((Composite)c.getNativeRepr()).setParent(_handler);
	}

	@Override
	protected void removeChildrenNatively(IWidget c) {
		if(getChildren().contains(c))
			((Composite)c.getNativeRepr()).setParent(_dummyParent);		
	}

//	@Override
//	protected void setContentAreaNatively(Rectangle a) {		
//		_updateContents();
//	}
	@Override
	protected void setBackgroundNatively(Color background) {
		_handler.setBackground(SWTUtils.colorToSWT(background));
	}

	protected void setBoundsNatively(Rectangle bounds){
		_handler.setBounds(SWTUtils.rectangleToSWT(bounds));
		_updateContents();
	}


	public boolean forceFocus() {
		return _handler.forceFocus();
	}
	@Override
	protected void setTypeNatively(String type) {
		_updateContents();
	}
	
	
	
	
	//SWT utilities: 
	
	/**
	 * update widget contents (not childrens)
	 */
	private void _updateContents() {
		
//		//redibujamos lanzando evento draw
//		org.eclipse.swt.widgets.Event event = new org.eclipse.swt.widgets.Event();
//		event.type = SWT.Paint;
//		event.widget=_handler;
//		Display.getCurrent().post(event);
//		//y flusheamos
//		_handler.update();
		
		_handler.redraw();
	}
	
	private void _drawContent(GC gc) {
		Rectangle contentArea=getBounds();
		//remember that gc is relative to handler
		if(type==TYPE_IMAGE){				
			if(_originalImg==null)
				setData(data);
//			if(contentArea==null){
//				contentArea=bounds.clone();
//			}
//			Rectangle contentArea = getBounds().translate(getBounds().getLocation().negate());
			//drawImage(Image image, int srcX, int srcY, int srcWidth, int srcHeight, int destX, int destY, int destWidth, int destHeight)
			System.out.println("content area: "+contentArea);	
			gc.drawImage(_originalImg, _originalImg.getBounds().x, _originalImg.getBounds().y,
					_originalImg.getBounds().width, _originalImg.getBounds().height, 
					contentArea.getX(), contentArea.getY(), 
					contentArea.getWidth(), contentArea.getHeight());//				
		}
		else if(type==TYPE_TEXT){
			gc.drawText(data, contentArea.getX(), contentArea.getY());
		}
		//note that widget childrens have their own composite and will draw themselves.		
	}

	


	//SWT event utilities and swt event handlers
	public static  UIEvent buildEventFrom(int type, MouseEvent me){
		UIEvent e = new UIEvent();
		e.type=type;
		e.count= me.count;
		if(me.button==1)
			e.button=UIEvent.BUTTON_LEFT;
		else if(me.button==3)
			e.button=UIEvent.CLICK_RIGHT;
		else if(me.button==2)
			e.button=UIEvent.CLICK_MIDDLE;
		//TODO: todo lo dem�s
		return e;		
	}
	private UIEvent buildEventFrom(int type, KeyEvent arg0) {
		UIEvent e = new UIEvent();
		e.type=type;
		e.character = arg0.character+"";
		//TODO: todo lo dem�s
		return e;
	}
	public void mouseDoubleClick(MouseEvent arg0) {
		UIEvent e = buildEventFrom(UIEvent.ONCLICK_TYPE, arg0);
		e.count=2;
		notifyAllEventListeners(e);
	}
	public void mouseDown(MouseEvent arg0) {
//		System.out.println("mouseDown");
		_handler.forceFocus();
		UIEvent e = buildEventFrom(UIEvent.ONMOUSEDOWN_TYPE, arg0);
		notifyAllEventListeners(e);
	}

	public void mouseUp(MouseEvent arg0) {
//		System.out.println("mouseUp");
		UIEvent e = buildEventFrom( UIEvent.ONMOUSEUP_TYPE,arg0);
		notifyAllEventListeners(e);
	}
	public void mouseMove(MouseEvent arg0) {
//		System.out.println("mouseMove");
		UIEvent e = buildEventFrom(UIEvent.ONMOUSEMOVE_TYPE, arg0);
		notifyAllEventListeners(e);
	}

	public void mouseEnter(MouseEvent arg0) {
//		System.out.println("mouseEnter");
		UIEvent e = buildEventFrom(UIEvent.ONMOUSEIN_TYPE, arg0);
		notifyAllEventListeners(e);
	}

	public void mouseExit(MouseEvent arg0) {
//		System.out.println("mouseLeave");
		UIEvent e = buildEventFrom(UIEvent.ONMOUSEOUT_TYPE, arg0);
		notifyAllEventListeners(e);

	}

	public void mouseHover(MouseEvent arg0) {
		UIEvent e = buildEventFrom(UIEvent.ONMOUSEOVER_TYPE, arg0);
		notifyAllEventListeners(e);
	}

	public void keyPressed(KeyEvent arg0) {
		UIEvent e = buildEventFrom(UIEvent.ONKEYPRESSED_TYPE, arg0);
		notifyAllEventListeners(e);
	}

	public void keyReleased(KeyEvent arg0) {		
		UIEvent e = buildEventFrom(UIEvent.ONKEYRELEASE_TYPE, arg0);
		notifyAllEventListeners(e);
	}

	//here we handle drawing event and paint the contents of this widget manually:
	public void handleEvent(org.eclipse.swt.widgets.Event event) {
		if(event.type==SWT.Paint){
			GC gc = event.gc;
			_drawContent(gc);
		}
	}

	








}
