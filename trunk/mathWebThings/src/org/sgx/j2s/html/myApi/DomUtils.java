package org.sgx.j2s.html.myApi;

import org.sgx.j2s.widget.base.Rectangle;


public class DomUtils {
	
	// property ids: css valid names
	public static final String  FontFamily = "font-family";
	public static final String  FontStyle = "font-style";
	public static final String  FontVariant = "font-variant";
	public static final String  FontWeight = "font-weight";
	public static final String  FontSize = "font-size";
	public static final String  Color = "color";
	public static final String  BackgroundColor = "background-color";
	public static final String  BackgroundImage = "background-image";
	public static final String  BackgroundRepeat = "background-repeat";
	public static final String  BackgroundAttachment = "background-attachment";
	public static final String  BackgroundPosition = "background-position";
	public static final String  WordSpacing = "word-spacing";
	public static final String  LetterSpacing = "letter-spacing";
	public static final String  TextDecoration = "text-decoration";
	public static final String  VerticalAlignment = "vertical-alignment";
	public static final String  TextTransformation = "text-transformation";
	public static final String  TextAlignment = "text-alignment";
	public static final String  TextIndentation = "text-indentation";
	public static final String  LineHeight = "line-height";
	public static final String  BorderTop = "border-top";
	public static final String  BorderBottom = "border-bottom";
	public static final String  BorderLeft = "border-left";
	public static final String  BorderRight = "border-right";
	public static final String Opacity = "opacity";
	public static final String Cursor = "cursor";
	public static final String ZIndex = "z-index";
	public static final String Overflow = "overflow";	
	
	/** creates and the element to parent
	 * @param tagName tag name of the new element
	 * @param parent the parent element or null
	 * @j2sNative
	 * var e = document.createElement(tagName);
	 * if(parent!=null)
	 * 	parent.appendChild(e)
	 * return e;
	 */
	public static Element createElement(String tagName, Element parent) {
		return null;
	}
	
	public static void appendChild(Node parent, Node child) {
		parent.appendChild(child);
	}
	/**
	 * @j2sNative
	 * return e.innerHTML;
	 */
	public static Object getText(Element e) {return null;}

	/**
	 * makes and return a new html dom elem with the 
	 * given id and tag name and set body as its parent
	 */
	public static Element newElement(String id, String tag) {
		Document doc = getDocument();
		Element e = doc.createElement(tag);
		e.setAttribute("id", id);
		Element parent=getBody();
		parent.appendChild(e);
		return e;
	}
	

	/**
	 * nothing to do with widget semantic
	 * @param e
	 * @param prop a cssstyle property
	 * @param val
	 */
	public static void setStyleProp(Element e, String prop, String val) {
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
	public static String getStyleProp(Element e, String prop) {
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
	public static Document getDocument() {
		throw new RuntimeException("javascript native called from java thread");
	}

	/**
	 *  @j2sNative
	 *  return document.body
	 */
	public static Element getBody() {
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
	public static void removeAllChilds(Node e) {
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
	public static void setInnerHtml(Element e, String s) {}

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
			Element parent, int n) {
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
			Element parent, int n) {
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
	public static String [][] buildElemMatrix(int n, int m, Element parent) {
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

	
	public static boolean isBrowserIE() {
		return getNavigator().appName.equals(Navigator.MSIE);
	}
	/**
	 * @j2sNative 
	 * return navigator
	 */
	public static Navigator getNavigator() {
		return null;
	}
	
	
	

	/**
	 * portable way for syncronically loading a script
	 * taked from http://www.exit12.org/archives/12
	 * @j2sNative
if(window.XMLHttpRequest)
	var req = new XMLHttpRequest();
else
	var req = new ActiveXObject("Microsoft.XMLHTTP");
req.open("GET",jsFileLocation,false);
req.send(null);
if( window.execScript)
	window.execScript(req.responseText);
else
	window.eval(req.responseText);
	 */
	public static void loadScript(String jsFileLocation) {}
//	/**
//	 * @j2sNative
//	 * var script=document.createElement("script");
//	 * script.src=jsFileLocation;
//	 * document.body.appendChild(script);
//	 */
//	public static void loadScript2(String jsFileLocation) {}
	


	public static void makeAbsolutelyBounds(Element div1, Rectangle rectangle) {
		div1.style.height=rectangle.dimension.height+"px";
		div1.style.width=rectangle.dimension.width+"px";
		div1.style.top=rectangle.location.y+"px";
		div1.style.left=rectangle.location.x+"px";
	}

	public static void setBounds(Element element, Rectangle bounds) {
		element.style.width=bounds.getWidth()+"px";
		element.style.height=bounds.getHeight()+"px";
		element.style.left=bounds.getX()+"px";
		element.style.top=bounds.getY()+"px";
	}
	
}
