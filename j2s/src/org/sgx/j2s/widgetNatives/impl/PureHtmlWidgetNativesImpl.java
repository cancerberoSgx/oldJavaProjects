package org.sgx.j2s.widgetNatives.impl;

import org.sgx.j2s.util.List;
import org.sgx.j2s.widget.Widget;
import org.sgx.j2s.widgetNatives.AbstractWidgetNatives;


/**
 * an example of an implementation using not js libs at all. don't use it at production!
 * this concrete impl will attempt to not use no javascript framework. only based in standars and then incoherent due known differences in implementations
 * 
 *  in this implementation we have this two preconditions:
 *  
 *  1) each html element must have an unique id attr (because the id string is used as widgetnatives key.
 *  
 * @author nati
 *
 */
public class PureHtmlWidgetNativesImpl extends AbstractWidgetNatives<String>{

	/**
	 * native helper
	 * @j2sNative
	 * return document.getElementById(id);
	 */
	private Object _get(String id){return null;}
	
	public void addChild(String w, String child) {
	/**
	 * @j2sNative
	 * document.getElementById
	 */{}	
	}

	public List<Widget> getChildrens() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getStyle(String id, String cssName) {
		// TODO Auto-generated method stub
		return null;
	}

	public void removeChild(String w, String child) {
		// TODO Auto-generated method stub
		
	}

	public void setStyle(String id, String name, String value) {
		// TODO Auto-generated method stub
		
	}



}
