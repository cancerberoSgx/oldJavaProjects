package org.sgx.j2s.bitmap;



public class DomUtils {
	
	public static void insertStyleRules(String css) {
		Object el = createElement("style", getBody());
		setInnerHtml(el, css);
	}
	/** creates and the element to parent
	 * @param tagName tag name of the new element
	 * @param parent the parent element or null
	 * @j2sNative
	 * var e = document.createElement(tagName);
	 * if(parent!=null)
	 * 	parent.appendChild(e)
	 * return e;
	 */
	public static Object createElement(String tagName, Object parent) {
		return null;
	}
	
//	public static void appendChild(Object parent, Object child) {
//		parent.appendChild(child);
//	}
	/**
	 * @j2sNative
	 * return e.innerHTML;
	 */
	public static Object getText(Object e) {return null;}

//	/**
//	 * makes and return a new html dom elem with the 
//	 * given id and tag name and set body as its parent
//	 */
//	public static Element newElement(String id, String tag) {
//		Document doc = getDocument();
//		Element e = doc.createElement(tag);
//		e.setAttribute("id", id);
//		Element parent=getBody();
//		parent.appendChild(e);
//		return e;
//	}
	

	/**
	 * nothing to do with widget semantic
	 * @param e
	 * @param prop a cssstyle property
	 * @param val
	 */
	public static void setStyleProp(Object e, String prop, String val) {
		String p = getJsStylePropertyName(prop);
		/**
		 * @j2sNative
		 * e.style[p]=val
		 */{}
	}
	/**
	 * nothing to do with widget semantic
	 * @param e
	 * @param prop a cssstyle property
	 * @return
	 */
	public static String getStyleProp(Object e, String prop) {
		String p = getJsStylePropertyName(prop);
		/**
		 * @j2sNative
		 * return e.style[p]
		 */{return "non java";}
	}
	
	static String getJsStylePropertyName(String prop) {
		String s = prop;
		if(prop.indexOf("-")!=-1) {
			String[] a = prop.split("-");
			s=a[0]+startMayus(a[1]);
		}
		return s;
	}
	static String startMayus(String s) {
		return s.substring(0,1).toUpperCase()+s.substring(1,s.length());
	}
	/**@return
	 * @j2sNative
	 * return document;
	 */
	public static Object getDocument() {
		throw new RuntimeException("javascript native called from java thread");
	}

	/**
	 *  @j2sNative
	 *  return document.body
	 */
	public static Object getBody() {
		return null;
	}
	
/**
 * @j2sNative
if ( e.hasChildNodes() ){
    while ( e.childNodes.length >= 1 ){
        e.removeChild( e.firstChild );       
    } 
}
 */
	public static void removeAllChilds(Object e) {
//		NodeList childs = e.childNodes;
//		for(int i=0; i<childs.getLength(); i++) {
//			e.removeChild(childs.item(i));
//		}
	}
	
//	/**
//	 * this native function is guaranteed that do not use 
//	 * any javascript library
//	 * 
//	 */
//	public static void loadScript(String path){
//		Element e = getDocument().createElement("script");
//		/** @j2sNative
//		 * e.src=path 
//		 * */{}
//		getBody().appendChild(e);
//	}
	
	/**
	 * wait to html dom object to load recent changes
	 * hacer bien con yui o jquery pero mas o menos sería:
	 * 
	 * @j2sNative
	 * var loaded=false;
	 * window.onLoad=function() {loaded=true; alert("loaded")}
	 *   //while(!loaded){;}
	 */
	public static void flush() {
		
	}


	/**
	 * @j2sNative
	 * e.innerHTML=s;
	 */
	public static void setInnerHtml(Object e, String s) {}

/**
 * 
 * @param id
 * @param string
 * @return
 * @j2sNative
 return document.getElementById(id)[attrName]
 */	public static String attr(String id, String attrName) {
		return null;
	}

 /**
  * try to instantiate and add thousand elements to a document using 
  * docuemtn.createElement and you will see what is the purpuse of this method....
  * erase the contents of parent and put the bunch there
  * 
  * @return an array new elements created into  parent
  */
	public static  void createBunchJs(String idPrefix, String tag, 
			Object parent, int n) {
		String html = "";
		/**
		 * @j2sNative
for (var i = 1; i <= n; i++) {
    html += '<' + tag + ' id=\"' + idPrefix + i + '\"></' + tag + '>';
}
parent.innerHTML = html;
		 */{}
		 setInnerHtml(parent, html);
	}

	public static  void createBunchJava(String idPrefix, String tag, 
			Object parent, int n) {
		String s = "";
		for(int i=0; i<n;i++) {
			s+="<" + tag + " id=\"" + idPrefix + i + "\"></" + tag + ">";
		}
		setInnerHtml(parent, s);
	}
	/**
	 * makes and return a 1x1 px matrix inside 
	 * @param n
	 * @param m
	 * @return
	 */
	public static String [][] buildElemMatrix(int n, int m, Object parent) {
		/**
		 * @j2sNative
var html = "";
for (var i = 1; i <= n; i++) {
    html += "<" + tag + " id=\"" + idPrefix + i + "\"></" + tag + ">";
}
parent.innerHTML = html;
		 */{}
		return null;
	}
/**
 *  @j2sNative
 *  return document.getElementById(elemId);
 */
	public static Object getElementById(String elemId) {return null;}
	/**
	 * @j2sNative
	 * el.setAttribute(name, value);
	 */
public static void setAttribute(Object el, String name, String value) {}
/**
 * @j2sNative
 * return el.getAttribute(name);
 */
public static String getAttribute(Object el, String name) {return null;}

	
//	public static boolean isBrowserIE() {
//		return getNavigator().appName.equals(Navigator.MSIE);
//	}
//	/**
//	 * @j2sNative 
//	 * return navigator
//	 */
//	public static Navigator getNavigator() {
//		return null;
//	}
	
	
	
//
//	/**
//	 * portable way for syncronically loading a script
//	 * taked from http://www.exit12.org/archives/12
//	 * @j2sNative
//if(window.XMLHttpRequest)
//	var req = new XMLHttpRequest();
//else
//	var req = new ActiveXObject("Microsoft.XMLHTTP");
//req.open("GET",jsFileLocation,false);
//req.send(null);
//if( window.execScript)
//	window.execScript(req.responseText);
//else
//	window.eval(req.responseText);
//	 */
//	public static void loadScript(String jsFileLocation) {}
//	
//	public static String printEvent( Event e) {
//		String s="Event(";
//		s+="altKey="+e.altKey+", "+
//			"ctrlKey="+e.ctrlKey+", "+
//			"shiftKey="+e.shiftKey+", "+
//			"button="+e.button+", "+
//			"clientX="+e.clientX+", "+
//			"clientY="+e.clientY+", "+
//			"screenX="+e.screenX+", "+
//			"screenY="+e.screenY+", ";
//		return s+")";
//	}

/**
 * @see http://blog.stevenlevithan.com/archives/faster-than-innerhtml
 * You can use the above as el = replaceHtml(el, newHtml) instead of el.innerHTML = newHtml.
 * 
 * @j2sNative
	var oldEl = el;
	var newEl = oldEl.cloneNode(false);
	newEl.innerHTML = html;
	oldEl.parentNode.replaceChild(newEl, oldEl);
	return newEl;
 */
public static Object innerHtmlFast(Object el, String html) {return  null;}
/**   
 * @param node parent node to search for
 * @param className
 * @param tag use "*" for all elements
 @j2sNative
  	var classElements = new Array();
    var els = node.getElementsByTagName(tag); // use "*" for all elements
    var pattern = new RegExp('\\b'+className+'\\b');
    for (var i = 0; i < els.length; i++)
         if ( pattern.test(els[i].className) )
             classElements[classElements.length] = els[i];
    return classElements;
 */
public static Object[] getElementsByClass(Object node, String className, String tag) {return null;}
	
}
