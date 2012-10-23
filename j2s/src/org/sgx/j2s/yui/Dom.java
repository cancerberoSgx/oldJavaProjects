//package org.sgx.j2s.yui;
//
//
///**
// * file:///C:/doc/javascript/yui_2.5.0/yui/docs/YAHOO.util.Dom.html
// *  Class YAHOO.util.Dom
// * Provides helper methods for DOM elements. 
// * @author sgurin
// *
// */
//public class Dom {
//
//	static {
//		ScriptLoader.loadYUI();
//	}
//	
//	/**
//	 * @j2sNative
//	 * e.innerHTML=s;
//	 */
//	public static void setText(Object e, String s) {}
//
//	
//	/**
//	@j2sNative
//	YAHOO.util.Dom.addClass(e, classNames)
//	 */	
//	public static void addClass(Object e, String [] classNames) {}
//
//	/**
//	@j2sNative
//	return YAHOO.util.Dom.addClass(e, className)
//	 */	
//	public static void addClass(Object e, String className) {}
//	/**
//	@j2sNative
//	return YAHOO.util.Dom.addClass(e, className)
//	 */	
//
//	/**
//	@j2sNative
//	return YAHOO.util.Dom.generateId(e)
//	 */	
//	public static String generateId_(Object e) {return null;}
//	/**
//	@j2sNative
//	return YAHOO.util.Dom.generateId(e)
//	 */	
//	public static String [] generateId__(Object [] e) {return null;}
//	/**
//	@j2sNative
//	return YAHOO.util.Dom.generateId(e, prefix)
//	 */	
//	public static String generateIdPrefixed_(Object e, String prefix) {return null;}
//	/**
//	@j2sNative
//	return YAHOO.util.Dom.generateId(e, prefix)
//	 */	
//	public static String []generateIdPrefixed__(Object []e, String prefix) {return null;}
//	/**
//	@j2sNative
//	return YAHOO.util.Dom.get(id)
//	 */	
//	public static Object get(String id){return null;}
//	
//	public static Object getAncestorBy(Object e, ElementPredicate p){
//		if(p!=null) {
//			/**
//			@j2sNative
//			return YAHOO.util.Dom.getAncestorBy(e, p.test)
//			 */	{return null;}
//		} else 
//			return null;		
//	}
//	/**
//	@j2sNative
//	return YAHOO.util.Dom.getAncestorByClassName(e, className)
//	 */	
//	public static Object getAncestorByClassName(Object e, String className){return null;}
//	/**
//	@j2sNative
//	return YAHOO.util.Dom.getAncestorByTagName(e, TagName)
//	 */	
//	public static Object getAncestorByTagName(Object e, String TagName){return null;}
//	/**
//	@j2sNative
//	return YAHOO.util.Dom.getChildren(e)
//	 */	
//	public static Object[] getChildren(Object e){return null;}
//	
//	public static Object[] getChildrenBy (Object e, ElementPredicate p) {
//		if(p!=null) {
//			/**
//			@j2sNative
//			return YAHOO.util.Dom.getChildrenBy(e, p.test)
//			 */	{return null;}
//		} else 
//			return null;	
//	}
//	
//	public static Region getClientRegion(){return null;}
//	/**Returns The height of the actual document (which includes the body and its margin).
//	@j2sNative
//	return YAHOO.util.Dom.getDocumentHeight()
//	 */	
//	public static int getDocumentHeight(){return 0;}
//	
//	/**Returns The amount that the document is scrolled to the left
//	 * 
//	@j2sNative
//	return YAHOO.util.Dom.getDocumentScrollLeft()
//	 */	
//	public static int getDocumentScrollLeft(){return 0;}
//	/**Returns The amount that the document is scrolled to the left
//	 * TODO: test with frames...
//	@j2sNative
//	return YAHOO.util.Dom.getDocumentScrollLeft(d)
//	 */	
//	public static int getDocumentScrollLeft(Object d){return 0;}
//	/**Returns The amount that the document is scrolled to the top
//	 * 
//	@j2sNative
//	return YAHOO.util.Dom.getDocumentScrollTop()
//	 */	
//	public static int getDocumentScrollTop(){return 0;}
//	/**Returns The amount that the document is scrolled to the top
//	 * TODO: test with frames...
//	@j2sNative
//	return YAHOO.util.Dom.getDocumentScrollTop(d)
//	 */	
//	public static int getDocumentScrollTop(Object d){return 0;}
//	/**Returns The width of the actual document (which includes the body and its margin).
//	 * TODO: test with frames...
//	@j2sNative
//	return YAHOO.util.Dom.getDocumentWidth()
//	 */	
//	public static int getDocumentWidth(){return 0;}
//	/**
//	 * Returns a array of HTMLElements that pass the test applied by supplied boolean method. For optimized performance, include a tag and/or root node when possible.
//	 * @param p predicate to evaluate if a Object must be selected
//	 * @param tag (can be null) The tag name of the elements being collected
//	 * @param root (can be null) Object to use as the starting point
//	 * @return An array of elements that much the given selectors
//	@j2sNative
//	if(tag!=null && root!=null)
//		return YAHOO.util.Dom.getElementsBy(p.test, tag, root);
//	else if(tag!=null)
//		return YAHOO.util.Dom.getElementsBy(p.test, tag);
//	else
//		return YAHOO.util.Dom.getElementsBy(p.test);
//	 */
//	public static Object[] getElementsBy(ElementPredicate p, String tag, Object root)  {return null;}
//	/**
//	 * Returns a array of HTMLElements with the given class. For optimized performance, include a tag and/or root node when possible.
//	 * @param className The class name to match against
//	 * @param tag (can be null) The tag name of the elements being collected
//	 * @param root (can be null) Object to use as the starting point
//	 * @return An array of elements that much the given selectors or null if not found
//	@j2sNative
//	if(tag!=null && root!=null)
//		return YAHOO.util.Dom.getElementsByClassName(className, tag, root);
//	else if(tag!=null)
//		return YAHOO.util.Dom.getElementsByClassName(className, tag);
//	else
//		return YAHOO.util.Dom.getElementsByClassName(className);
//	 */
//	public static Object[] getElementsByClassName(String className, String tag, Object root){return null;}
//	/**Returns Returns the first HTMLObject child or null if not found
//	@j2sNative
//	return YAHOO.util.Dom.getFirstChild(node)
//	 */	
//	public static Object getFirstChild(Object node ) {return null;}
//	/**
//	 * @param node the HTMLObject to use as the starting point
//	 * @param p 
//	 * @return the first HTMLObject child that passes the test method or null if not found
//	@j2sNative
//	return YAHOO.util.Dom.getFirstChildBy(node, p.test);
//	 */
//	public static Object getFirstChildBy(Object node, ElementPredicate p) {return null;}
//	/**Returns Returns the last HTMLObject child or null if not found
//	@j2sNative
//	return YAHOO.util.Dom.getLastChild(node)
//	 */	
//	public static Object getLastChild(Object node ) {return null;}
//	/**
//	 * @param node the HTMLObject to use as the starting point
//	 * @param p 
//	 * @return the last HTMLObject child that passes the test method or null if not found
//	@j2sNative
//	return YAHOO.util.Dom.getLastChildBy(node, p.test);
//	 */
//	public static Object getLastChildBy(Object node, ElementPredicate p) {return null;}
//	/**Returns Returns the next sibling HTMLObject or null if not found
//	@j2sNative
//	return YAHOO.util.Dom.getNextSibling(node)
//	 */	
//	public static Object getNextSibling (Object node ) {return null;}
//	/**
//	 * @param node the HTMLObject to use as the starting point
//	 * @param p 
//	 * @return the next sibling HTMLObject that passes the test method or null if not found
//	@j2sNative
//	return YAHOO.util.Dom.getNextSiblingBy(node, p.test);
//	 */
//	public static Object getNextSiblingBy(Object node, ElementPredicate p) {return null;}
//	/**Returns Returns the Previous sibling HTMLObject or null if not found
//	@j2sNative
//	return YAHOO.util.Dom.getPreviousSibling(node)
//	 */	
//	public static Object  getPreviousSibling (Object node ) {return null;}
//	/**
//	 * @param node the HTMLObject to use as the starting point
//	 * @param p 
//	 * @return the Previous sibling HTMLObject that passes the test method or null if not found
//	@j2sNative
//	return YAHOO.util.Dom.getPreviousSiblingBy(node, p.test);
//	 */
//	public static Object getPreviousSiblingBy(Object node, ElementPredicate p) {return null;}
//	
//	/**Returns the region position of the given element. The Object must be part of the DOM tree to have a region (display:none or elements not appended return null).
//	@j2sNative
//	var r = YAHOO.util.Dom.getRegion(node);
//	if(r) return r;
//	else return null;
//	 */
//	public static Region getRegion(Object node) {return null;}
//	
//	/**
//	@j2sNative
//	return YAHOO.util.Dom.getStyle(node, prop)
//	 */	
//	public static String getStyle(Object node, String prop) {return null;}
////	/**
////	@j2sNative
////	return YAHOO.util.Dom.getStyle(node, prop)
////	 */	
////	public static String [] getStyle(Object [] node, String prop) {return null;}
//	/**
//	@j2sNative
//	return YAHOO.util.Dom.getViewportHeight()
//	 */	
//	public static int getViewportHeight() {return 0;}
//	/**
//	@j2sNative
//	return YAHOO.util.Dom.getViewportWidth()
//	 */	
//	public static int getViewportWidth() {return 0;}
//	/**
//	@j2sNative
//	return YAHOO.util.Dom.getClientRegion()
//	 */	
//	/**
//	@j2sNative
//	return YAHOO.util.Dom.getXY(node)
//	 */	
//	public static String getXY(Object node) {return null;}
//	
//	/**
//	@j2sNative
//	return YAHOO.util.Dom.setStyle(el, property, val);
//	 */	
//	public static void setStyle (Object el ,String property ,String val ){}
//	
////	/**
////	@j2sNative
////	return YAHOO.util.Dom.setStyle(el, property, val);
////	 */	
////	public static void setStyle (Object []el ,String property ,String val ){}
//	
//	
//	
//}
