package org.sgx.j2s.widgetNatives;

import org.sgx.j2s.util.List;
import org.sgx.j2s.widget.Widget;


/**
 * this is the real html [capa]. The important thing is to be a one direction layer so html dom elements
 * are invisible to the user, and to avoid associations  with html dom objects. 
 * 
 * We use a Map (defined by the user) used by the user to reference html dom objects but don't touch them. K is the kwy type
 *  
 * The idea is that (in theory) new html implementations of the entire j2s.widget api can be done only providing 
 * an implementation of this interface. Also the same Widget implementation can vary but use the same WidgetNatives implementation... 
 * 
 * @author sgurin
 *
 */
public interface WidgetNatives<K> {
	
	/* home functions */
	void registerWidget(K k, Widget w);
	
	/**
	 * returns null if iit is not registered
	 */
	Widget getWidget(K k);
	
	/**
	 * 
	 * @param id key of html document
	 * @param cssName the exact css name (i.e "backgrund-color"). the constants are defined in Style
	 * @return string represented css value of the property
	 */
	String getStyle(K id, String cssName);
	void setStyle(K id, String name, String value);
	
	void addChild(K w, K child);
	/**
	 * returns copy of the children list (but list elements are the real children widgets!
	 * @return
	 */
	List<Widget> getChildrens();
	/**
	 * w.(equals()) used for comparision
	 * @param w
	 * @param child
	 */
	void removeChild(K w, K child);
	
}
