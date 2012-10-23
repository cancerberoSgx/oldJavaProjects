package org.sgx.j2s.yui;

import org.sgx.j2s.widget.base.Point;


/**
 * file:///C:/doc/javascript/yui_2.5.0/yui/docs/YAHOO.util.Dom.html
 *  Class YAHOO.util.Dom
 * Provides helper methods for DOM elements. 
 * 
 * Note that in this class java2js.html.dom instances will be pure 
 * html dom objects methods with methods this type of arguments won0t support 
 * overloading. so methods names were decorated in those situations
 * 
 * @author sgurin 
 *
 */
public class YuiDom {

	
	// property ids: css valid names
	public static final String Sty_FontFamily = "font-family";
	public static final String Sty_FontStyle = "font-style";
	public static final String Sty_FontVariant = "font-variant";
	public static final String Sty_FontWeight = "font-weight";
	public static final String Sty_FontSize = "font-size";
	public static final String Sty_Color = "color";
	public static final String Sty_BackgroundColor = "background-color";
	public static final String Sty_BackgroundImage = "background-image";
	public static final String Sty_BackgroundRepeat = "background-repeat";
	public static final String Sty_BackgroundAttachment = "background-attachment";
	public static final String Sty_BackgroundPosition = "background-position";
	public static final String Sty_WordSpacing = "word-spacing";
	public static final String Sty_LetterSpacing = "letter-spacing";
	public static final String Sty_TextDecoration = "text-decoration";
	public static final String Sty_VerticalAlignment = "vertical-alignment";
	public static final String Sty_TextTransformation = "text-transformation";
	public static final String Sty_TextAlignment = "text-alignment";
	public static final String Sty_TextIndentation = "text-indentation";
	public static final String Sty_LineHeight = "line-height";
	public static final String Sty_BorderTop = "border-top";
	public static final String Sty_BorderBottom = "border-bottom";
	public static final String Sty_BorderLeft = "border-left";
	public static final String Sty_BorderRight = "border-right";
	public static final String Sty_Opacity = "opacity";
	
	
//	static {
//		ScriptLoader.load_yahoo_dom_events_element();
//	}
	
	/**
	@j2sNative
	return YAHOO.util.Dom.addClass(e, className)
	 */	
	public static void addClass(Object e, String className) {}
	/**
	@j2sNative
	return YAHOO.util.Dom.addClass(id, className)
	 */	
	public static void addClass_(String id, String className) {}
	/**
	@j2sNative
	YAHOO.util.Dom.addClass(id, classNames)
	 */	
	public static void addClass_(String id, String [] classNames) {}
	
	/**
	@j2sNative
	return YAHOO.util.Dom.addClass(e, className)
	 */	

	/**
	@j2sNative
	return YAHOO.util.Dom.generateId(e)
	 */	
	public static String generateId(Object e) {return null;}
	/**
	@j2sNative
	return YAHOO.util.Dom.generateId(e)
	 */	
	public static String [] generateId_(Object [] e) {return null;}
	/**
	@j2sNative
	return YAHOO.util.Dom.generateId(e, prefix)
	 */	
	public static String generateId__(Object e, String prefix) {return null;}
	/**
	@j2sNative
	return YAHOO.util.Dom.generateId(e, prefix)
	 */	
	public static String []generateId___(Object []e, String prefix) {return null;}
	/**
	@j2sNative
	return YAHOO.util.Dom.get(id)
	 */	
	public static YuiElement get(Object id){return null;}
	
	public static Object getAncestorBy(Object e, ElementPredicate p){
		if(p!=null) {
			/**
			@j2sNative
			return YAHOO.util.Dom.getAncestorBy(e, p.test)
			 */	{return null;}
		} else 
			return null;		
	}
	/**
	@j2sNative
	return YAHOO.util.Dom.getAncestorByClassName(e, className)
	 */	
	public static Object getAncestorByClassName(Object e, String className){return null;}
	/**
	@j2sNative
	return YAHOO.util.Dom.getAncestorByTagName(e, TagName)
	 */	
	public static Object getAncestorByTagName(Object e, String TagName){return null;}
	
	/**
	@j2sNative
	return YAHOO.util.Dom.getChildren(e)
	 */	
	public static Object getChildren(Object e){return null;}
	/**
	@j2sNative
	return YAHOO.util.Dom.getChildren(id)
	 */	
	public static Object getChildren_id(String id){return null;}
	
	public static Object getChildrenBy(Object e, ElementPredicate p) {
		if(p!=null) {
			/**
			@j2sNative
			return YAHOO.util.Dom.getChildrenBy(e, p.test)
			 */	{return null;}
		} else 
			return null;	
	}
	public static Object getChildrenBy_(String id, ElementPredicate p) {
		if(p!=null) {
			/**
			@j2sNative
			return YAHOO.util.Dom.getChildrenBy(id, p.test)
			 */	{return null;}
		} else 
			return null;	
	}
	/**
	@j2sNative
	return YAHOO.util.Dom.getClientRegion()
	 */	
	public static Region getClientRegion(){return null;}
	/**Returns The height of the actual Object (which includes the body and its margin).
	@j2sNative
	return YAHOO.util.Dom.getObjectHeight()
	 */	
	public static int getObjectHeight(){return 0;}
	
	/**Returns The amount that the Object is scrolled to the left
	 * 
	@j2sNative
	return YAHOO.util.Dom.getObjectScrollLeft()
	 */	
	public static int getObjectScrollLeft(){return 0;}
	/**Returns The amount that the Object is scrolled to the left
	 * TODO: test with frames...
	@j2sNative
	return YAHOO.util.Dom.getObjectScrollLeft(d)
	 */	
	public static int getObjectScrollLeft(Object d){return 0;}
	/**Returns The amount that the Object is scrolled to the top
	 * 
	@j2sNative
	return YAHOO.util.Dom.getObjectScrollTop()
	 */	
	public static int getObjectScrollTop(){return 0;}
	/**Returns The amount that the Object is scrolled to the top
	 * TODO: test with frames...
	@j2sNative
	return YAHOO.util.Dom.getObjectScrollTop(d)
	 */	
	public static int getObjectScrollTop(Object d){return 0;}
	/**Returns The width of the actual Object (which includes the body and its margin).
	 * TODO: test with frames...
	@j2sNative
	return YAHOO.util.Dom.getObjectWidth()
	 */	
	public static int getObjectWidth(){return 0;}
	/**
	 * Returns a array of HTMLElements that pass the test applied by supplied boolean method. For optimized performance, include a tag and/or root node when possible.
	 * @param p predicate to evaluate if a Object must be selected
	 * @param tag (can be null) The tag name of the elements being collected
	 * @param root (can be null) Object to use as the starting point
	 * @return An array of elements that much the given selectors
	@j2sNative
	if(tag!=null && root!=null)
		return YAHOO.util.Dom.getElementsBy(p.test, tag, root);
	else if(tag!=null)
		return YAHOO.util.Dom.getElementsBy(p.test, tag);
	else
		return YAHOO.util.Dom.getElementsBy(p.test);
	 */
	public static Object getElementsBy(ElementPredicate p, String tag, Object root)  {return null;}
	/**
	 * Returns a array of HTMLElements with the given class. For optimized performance, include a tag and/or root node when possible.
	 * @param className The class name to match against
	 * @param tag (can be null) The tag name of the elements being collected
	 * @param root (can be null) Object to use as the starting point
	 * @return An array of elements that much the given selectors or null if not found
	@j2sNative
	if(tag!=null && root!=null)
		return YAHOO.util.Dom.getElementsByClassName(className, tag, root);
	else if(tag!=null)
		return YAHOO.util.Dom.getElementsByClassName(className, tag);
	else
		return YAHOO.util.Dom.getElementsByClassName(className);
	 */
	public static Object getElementsByClassName(String className, String tag, Object root){return null;}
	/**Returns Returns the first HTMLObject child or null if not found
	@j2sNative
	return YAHOO.util.Dom.getFirstChild(node)
	 */	
	public static Object getFirstChild(Object node ) {return null;}
	/**
	 * @param node the HTMLObject to use as the starting point
	 * @param p 
	 * @return the first HTMLObject child that passes the test method or null if not found
	@j2sNative
	return YAHOO.util.Dom.getFirstChildBy(node, p.test);
	 */
	public static Object getFirstChildBy(Object node, ElementPredicate p) {return null;}
	/**Returns Returns the last HTMLObject child or null if not found
	@j2sNative
	return YAHOO.util.Dom.getLastChild(node)
	 */	
	public static Object getLastChild(Object node ) {return null;}
	/**
	 * @param node the HTMLObject to use as the starting point
	 * @param p 
	 * @return the last HTMLObject child that passes the test method or null if not found
	@j2sNative
	return YAHOO.util.Dom.getLastChildBy(node, p.test);
	 */
	public static Object getLastChildBy(Object node, ElementPredicate p) {return null;}
	/**Returns Returns the next sibling HTMLObject or null if not found
	@j2sNative
	return YAHOO.util.Dom.getNextSibling(node)
	 */	
	public static Object getNextSibling (Object node ) {return null;}
	/**
	 * @param node the HTMLObject to use as the starting point
	 * @param p 
	 * @return the next sibling HTMLObject that passes the test method or null if not found
	@j2sNative
	return YAHOO.util.Dom.getNextSiblingBy(node, p.test);
	 */
	public static Object getNextSiblingBy(Object node, ElementPredicate p) {return null;}
	/**Returns Returns the Previous sibling HTMLObject or null if not found
	@j2sNative
	return YAHOO.util.Dom.getPreviousSibling(node)
	 */	
	public static Object  getPreviousSibling (Object node ) {return null;}
	/**
	 * @param node the HTMLObject to use as the starting point
	 * @param p 
	 * @return the Previous sibling HTMLObject that passes the test method or null if not found
	@j2sNative
	return YAHOO.util.Dom.getPreviousSiblingBy(node, p.test);
	 */
	public static Object getPreviousSiblingBy(Object node, ElementPredicate p) {return null;}
	
	/**Returns the region position of the given element. The Object must be part of the DOM tree to have a region (display:none or elements not appended return null).
	@j2sNative
	var r = YAHOO.util.Dom.getRegion(node);
	if(r) return r;
	else return null;
	 */
	public static Region getRegion(Object node) {return null;}
	
	/**
	@j2sNative
	return YAHOO.util.Dom.getStyle(node, prop)
	 */	
	public static String getStyle(Object node, String prop) {return null;}
//	/**
//	@j2sNative
//	return YAHOO.util.Dom.getStyle(node, prop)
//	 */	
//	public static String [] getStyle(Object [] node, String prop) {return null;}
	/**@returns The height of the viewable area of the page (excludes scrollbars).
	@j2sNative
	return YAHOO.util.Dom.getViewportHeight()
	 */	
	public static int getViewportHeight() {return 0;}
	/**@returns The width of the viewable area of the page (excludes scrollbars).
	@j2sNative
	return YAHOO.util.Dom.getViewportWidth()
	 */	
	public static int getViewportWidth() {return 0;}

	/**Gets the current position of an Object based on page coordinates. Object must be part of the DOM tree to have page coordinates (display:none or elements not appended return false).	 	 */	
	public static Point getXY(Object node) {
		int [] xyp = null;
		/**
		 * @j2sNative
		 * xyp= YAHOO.util.Dom.getXY(node);
		 * if(!xyp) return null;
		 */{}
		 return new Point(xyp[0], xyp[1]);
	}
	
	/**Gets the current X position of an Object based on page coordinates. Object must be part of the DOM tree to have page coordinates (display:none or elements not appended return false).
	@j2sNative
	return YAHOO.util.Dom.getX(node)
	 */	
	public static int getX(Object node) {return 0;}
	/**Gets the current Y position of an Object based on page coordinates. Object must be part of the DOM tree to have page coordinates (display:none or elements not appended return false).
	@j2sNative
	return YAHOO.util.Dom.getY(node)
	 */	
	public static int getY(Object node) {return 0;}
	/**Determines whether an HTMLObject has the given className.
	@j2sNative
	return YAHOO.util.Dom.hasClass(node, className)
	 */	
	public static boolean hasClass(Object node, String className) {return false;}
	/**Determines whether an HTMLObject is present in the current Object.
	@j2sNative
	return YAHOO.util.Dom.inObject(node)
	 */	
	public static boolean inDocument(Object node) {return false;}
	/**Inserts the new node as the next sibling of the reference node
	 * @returns The node that was inserted (or null if insert fails)
	@j2sNative
	return YAHOO.util.Dom.insertAfter(newNode, referenceNode)
	 */	
	public static Object insertAfter(Object newNode ,Object referenceNode ){return null;}
	/**Inserts the new node as the previous sibling of the reference node
	 * @returns The node that was inserted (or null if insert fails)
	@j2sNative
	return YAHOO.util.Dom.insertBefore(newNode, referenceNode)
	 */	
	public static Object insertBefore(Object newNode ,Object referenceNode ){return null;}
	/**Determines whether an HTMLObject is an ancestor of another HTML Object in the DOM hierarchy.
	 * @returns Whether or not the haystack is an ancestor of needle
	@j2sNative
	return YAHOO.util.Dom.isAncestor(newNode, referenceNode)
	 */	
	public static boolean isAncestor(Object haystack ,Object needle ){return false;}
	/**Removes a class name from a given element.
	 * @returns true iff remove was successfull
	@j2sNative
	return YAHOO.util.Dom.removeClass(node, className)
	 */	
	public static boolean removeClass(Object node, String className) {return false;}
	/**Replace a class with another class for a given Object or collection of elements. If no oldClassName is present, the newClassName is simply added.
	 * @returns true iff replace class was successfull
	@j2sNative
	return YAHOO.util.Dom.replaceClass(node, oldClassName,newClassName )
	 */	
	public static boolean replaceClass(Object node, String oldClassName, String newClassName) {return false;}
	/**
	@j2sNative
	return YAHOO.util.Dom.setStyle(el, property, val);	 
	*/	
	public static void setStyle (Object el ,String property ,String val ){}	
//	/**
//	@j2sNative
//	return YAHOO.util.Dom.setStyle(el, property, val);
//	 */	
//	public static void setStyle (Object []el ,String property ,String val ){}
	/**Set the X position of an html Object in page coordinates, regardless of how the Object is positioned. The Object must be part of the DOM tree to have page coordinates (display:none or elements not appended return false).
	@j2sNative
	return YAHOO.util.Dom.setX(el, x);
	 */	
	public static void setX (Object el ,int x ){}
	/**Set the X position of an html Object in page coordinates, regardless of how the Object is positioned. The Object must be part of the DOM tree to have page coordinates (display:none or elements not appended return false).
	@j2sNative
	return YAHOO.util.Dom.setY(el, y);
	 */	
	public static void setY (Object el ,int y ){}
	/**Set the position of an html Object in page coordinates, regardless of how the Object is positioned. The element(s) must be part of the DOM tree to have page coordinates (display:none or elements not appended return false).
	@j2sNative
	return YAHOO.util.Dom.setXY(el, [p.x, p.y]);
	 */	
	public static void setXY (Object el,Point p){}
	
	/**
	 * @j2sNative
	 * parent.appendChild(child);
	 */
	public static void appendChild(Object parent, Object child) {}
	
	public static void setParent(Object self, Object parentRepr) {
		appendChild(parentRepr, self);
	}


	
}
