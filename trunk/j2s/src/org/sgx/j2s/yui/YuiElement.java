package org.sgx.j2s.yui;


/**
 * http://developer.yahoo.com/yui/docs/YAHOO.util.Element.html wraper
 * @author SGURIN
 *
 */
public abstract class YuiElement {
	// gettable attributes keys
	public static final String ATTR_BASE_URI = "baseURI";
	public static final String ATTR_CHILDS_NODES = "childNodes";
	public static final String ATTR_FIRST_CHILD = "firstChild";
	public static final String ATTR_LAST_CHILD = "lastChild";
	public static final String ATTR_LOCAL_NAME = "localName";
	public static final String ATTR_NAMESPACE_URI = "namespaceURI";
	public static final String ATTR_NEXT_SIBLING = "nextSibling";
	public static final String ATTR_NODE_NAME = "nodeName";
	public static final String ATTR_NODE_TYPE = "nodeType";
	public static final String ATTR_NODE_VALUE = "nodeValue";
	public static final String ATTR_OWNER_DOCUMENT = "ownerDocument";
	public static final String ATTR_PARENT_NODE = "parentNode";
	public static final String ATTR_PREFIX = "prefix";
	public static final String ATTR_PREVIOUS_SIBLING = "previousSibling";
	public static final String ATTR_TEXT_CONTENT = "textContent";
//	public static final String ATTR_TEXT = "text";
//	public static final String ATTR_XML = "xml";
		
	public abstract void addClass  ( String className );
	public abstract void appendChild(YuiElement child);	
	public abstract void appendTo(YuiElement parentNode, YuiElement before);
//	public  NodeList childNodes(){
//		return (NodeList) get(ATTR_CHILDS_NODES);
//	}
	
	/**
	 *  className <String>  The className to collect
	 *  tag <String> (optional) The tag to use in conjunction with class name
	 *  Returns: Array of HTMLElements 
	 */
	public abstract YuiElement [] getElementsByClassName (String className, String tag);
	public abstract YuiElement[] getElementsByTagName(String tag);
		
	//attributes
	public abstract Object get(String key);	
	public abstract String[] getAttributeKeys();	
	public abstract void refresh(String key);	
	public abstract void resetValue(String key);	
	public abstract void set(String key, String val);
	
	/**
	 * @j2sNative
	 * return  new YAHOO.util.Element(id);
	 */
	public static YuiElement build(Object id) {
		return null;
	}
}
