//package org.sgx.j2s.widget.html;
//
//import org.sgx.j2s.widget.IWidget;
//import org.sgx.j2s.widget.Widget;
//import org.sgx.j2s.widget.base.Color;
//import org.sgx.j2s.widget.base.Font;
//import org.sgx.j2s.widget.base.Rectangle;
//import org.sgx.j2s.html.myApi.*;
//
///**
// * this is an example about:
// * 
// * 1) how to extend Widget to a technology (in this case the browser)
// * 2) how to access html 100% in java:
// * 
// * @author SGURIN
// *
// */
//public class CopyOfHTMLWidget extends Widget{
//	
//	static String img_base = "src/";
//	
//	static final String defaultTagName="span"; 
//	
//	static ElementWrapper _inputKeyElFocus=null;
//	
//	static Widget _focusedWidget=null;
//	
//
//	protected ElementWrapper _handler;
//	protected ElementWrapper _imgHandler;
//	
////	protected ElementWrapper _childrenAreaHandler;
//	
//
//	public CopyOfHTMLWidget(ElementWrapper parent) {
//		init(parent);
//	}
//	private void init(ElementWrapper parent) {
//		if(_inputKeyElFocus==null){
//			_inputKeyElFocus=new ElementWrapper(DomUtils.createElement("input", parent.getElement()));
//			_inputKeyElFocus.getElement().type="text";
//			//TODO: registrarnos como keylisteners de _inputKeyElFocus y redirigir los eventos.
//		}
//		_handler = new ElementWrapper(DomUtils.createElement(defaultTagName, parent.getElement()));
//		
//		_imgHandler = new ElementWrapper(DomUtils.createElement("img", _handler.getElement()));
//		_imgHandler.getElement().id="sgx";
//		parent.getElement().style.position="absolute";
//		_handler.getElement().style.position="absolute";
//		_imgHandler.getElement().style.position="absolute";
//		_imgHandler.getElement().style.top="0px";
//		_imgHandler.getElement().style.left="0px";
//		//TODO: registrarnos como mouselisteners de _handler.getElement() y _imgHandler.getElement()y ridirigirlos a nuestros listenres
////		DomUtils.setStyleProp(_imgHandler.getElement(), DomUtils.ZIndex, "-1"); //other childrens will have to go foreground
////		DomUtils.registerEventListener(_imgHandler, "onclick", new EventListener(){
////			public void dispatchEvent(Event e) {
////				System.out.println("onclick");
////			}
////		});
//		setType(TYPE_NONE);
//	}
//	
//	@Override
//	protected void appendChildNatively(IWidget c) {
//		_handler.getElement().appendChild(((CopyOfHTMLWidget)c)._handler.getElement());
//	}
//
//	@Override
//	protected void removeChildrenNatively(IWidget c) {
//		_handler.getElement().removeChild(((CopyOfHTMLWidget)c)._handler.getElement());		
//	}
//
//	@Override
//	protected void setBackgroundNatively(Color background) {
//		String bgc = background.toCSS();		
//		if(background==null)
//			bgc="transparent";
//		System.out.println(_handler.getElement().style);
//		_handler.getElement().style.backgroundColor=bgc;
//	}
//
//	@Override
//	protected void setBoundsNatively(Rectangle bounds) {
//		DomUtils.setBounds(_handler.getElement(), bounds);
//		DomUtils.setBounds(_imgHandler.getElement(), bounds.translate(bounds.location.negate()));
//
//		_imgHandler.getElement().width=bounds.getWidth()+"px";
//		_imgHandler.getElement().height=bounds.getHeight()+"px";
//		
////		if(type == TYPE_IMAGE){
////			try{
////				_imgHandler.getElement().width=bounds.getWidth()+"px";
////				_imgHandler.getElement().height=bounds.getHeight()+"px";
////			}catch(Exception e){				
////			}
////		}	
//	}
//
//	@Override
//	protected void setDataNatively(String s) {
//		_imgHandler.getElement().src=img_base+s;
//		
////		_handler.getElement().innerHTML
////		if(type == TYPE_IMAGE){
//////			System.out.println("setting src to "+img_base+s);
////			_imgHandler.getElement().src=img_base+s;
//////			_handler.getElement().innerHTML="";
////		}else{
//////			_handler.getElement().innerHTML=s;
////		}
//	}
//	@Override
//	protected void setTypeNatively(String type) {
//		if(type == TYPE_IMAGE){
//			_imgHandler.getElement().style.visibility="visible";
//			_imgHandler.getElement().src=img_base+data;
////			_handler.getElement().innerHTML="";
//		}
//		else {
////			_handler.getElement().innerHTML=data;
//			_imgHandler.getElement().style.visibility="hidden";
//		}
//	}
//	@Override
//	protected void setFontNatively(Font f) {
//		DomUtils.setStyleProp(_handler.getElement(), "font-family", f.getFontFamily());
//		DomUtils.setStyleProp(_handler.getElement(), DomUtils.FontSize, f.getSize()+"px");
//		DomUtils.setStyleProp(_handler.getElement(),DomUtils.Color, f.getColor().toCSS());
//		//TODO: faltan cosas
//	}
//
//	@Override
//	protected void setParentNatively(IWidget w) {
//		((CopyOfHTMLWidget)w)._handler.getElement().appendChild(_handler.getElement());
//	}
//
//
//
//	@Override
//	protected void setVisibleNatively(boolean b) {
//		if(b)
//			_handler.getElement().style.visibility="visible";
//		else
//			_handler.getElement().style.visibility="hidden";		
//	}
//
//	public boolean forceFocus() {
//		try{
//		_inputKeyElFocus.getElement().focus();
//		}catch (Exception e) {
//			// TODO: handle exception
//		}
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	public Object getNativeRepr() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//}
