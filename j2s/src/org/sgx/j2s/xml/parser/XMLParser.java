package org.sgx.j2s.xml.parser;

import org.sgx.j2s.html.dom.Document;
import org.sgx.j2s.html.dom.Element;
import org.sgx.j2s.html.dom.Node;
import org.sgx.j2s.html.dom.NodeList;
import org.sgx.j2s.html.dom.util.DomUtils;
import org.sgx.j2s.js.JsUtils;
import org.sgx.j2s.util.List;
import org.sgx.j2s.util.Predicate;
import org.sgx.j2s.util.impl.LinkedList;
/**
 * @author SGURIN
 *
 */
public class XMLParser {
	
	static XMLParser inst;
	public static XMLParser getInstance() {
		if(inst==null)
			inst=new XMLParser();
		return inst;
	}
	private XMLParser(){}
	
	/**
	 * @j2sIgnore
	 */
	java.util.Map<org.w3c.dom.Node, Node> _nodes_ = new java.util.HashMap<org.w3c.dom.Node, Node>();
	/**
	 * @j2sIgnore
	 */
	public  Node getNodeFor(org.w3c.dom.Node o) {
		if(o==null)
			return null;
		Node v = _nodes_.get(o);
		if(v==null) {
			if(o instanceof org.w3c.dom.Document)
				v=new Document((org.w3c.dom.Document) o);
			else if(o instanceof org.w3c.dom.Element)
				v=new Element((org.w3c.dom.Element) o);
			else if(o instanceof org.w3c.dom.Attr)
				v=new org.sgx.j2s.html.dom.Attr((org.w3c.dom.Attr) o);
			else
				v=new Node(o);
			setNodeFor(o,v);
			v._initialize();
		}
		return v;
	}
	public void setNodeFor(org.w3c.dom.Node o,Node n) {
		_nodes_.put(o, n);
	}
	public Document load(String url) throws Exception {
		Document xmlDoc=null;
		try {

			if(!JsUtils.isJs()) {
				/**
				 *@j2sIgnore 
				 */{
				javax.xml.parsers.DocumentBuilderFactory docFactory = javax.xml.parsers.DocumentBuilderFactory.newInstance();
				javax.xml.parsers.DocumentBuilder docBuilder = docFactory.newDocumentBuilder();			
				xmlDoc = (Document) getNodeFor(docBuilder.parse(url));
				 }
			}
			else if(DomUtils.isBrowserIE()) {
				/**
				 * @j2sNative
				 * xmlDoc=new ActiveXObject("Microsoft.XMLDOM");
				 * xmlDoc.async="false";
				 * xmlDoc.load(url);
				 */	{}		
			}
			else {
				/**
				 * @j2sNative
				 * var xmlDoc=document.implementation.createDocument("","",null);
				 * xmlDoc.async="false";
				 * xmlDoc.load(url);
				 */	{}
			}
			return xmlDoc;
		} catch (Exception e) {
			throw e;
		}
	}

	
	public Document parseFromString(String src) throws Exception {
		Document xmlDoc=null;
		try {
			if(!JsUtils.isJs()) {
				/**
				 *@j2sIgnore 
				 */{
				javax.xml.parsers.DocumentBuilderFactory docFactory = javax.xml.parsers.DocumentBuilderFactory.newInstance();
				javax.xml.parsers.DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
				StringBuffer StringBuffer1 = new StringBuffer(src);
				java.io.ByteArrayInputStream Bis1 = new java.io.ByteArrayInputStream(StringBuffer1.toString().getBytes());
				org.w3c.dom.Document _doc = docBuilder.parse(Bis1);
				xmlDoc = (Document) getNodeFor(_doc);
				 }
			}
			else if(DomUtils.isBrowserIE()) {
				/**
				 * @j2sNative
				 * xmlDoc=new ActiveXObject("Microsoft.XMLDOM");
				 * xmlDoc.async="false";
				 * xmlDoc.loadXML(src);
				 */	{}		
			}
			else {
				/**
				 * @j2sNative
				 * var parser=new DOMParser();
				 * xmlDoc=parser.parseFromString(src,"text/xml");
				 */	{}
			}
			return xmlDoc;
		} catch (Exception e) {
			throw e;
		}
	}
	public List<Node> selectElements(Node e, Predicate<Node> p) {
			List<Node> l = new LinkedList<Node>();
			NodeList childs = e.childNodes;
			if(childs!=null) {
				for (int i = 0; i < childs.length; i++) {
					Node c = childs.item(i);
					l.addAll(selectElements(c, p));
				}
			}
			if(p.select(e))
				l.append(e);
			return l;
		}
	public List<Node> selectElementsByAttrib(Node e, final String attrib, final String attribValue) {
		return selectElements(e, new Predicate<Node>() {
			public boolean select(Node o) {
				if(o.nodeType==Node.ELEMENT_NODE) {					
					Element e = (Element)o;
					String a = e.getAttribute(attrib);
					System.out.println(attrib+" - "+attribValue+" - "+e.getAttribute(attrib)+" - "+a);	
					return a!=null?a .equals(attribValue):false;				
				}
				else
					return false;
			}
		});
	}
		
	public String xmlHeader = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
	public String toString(Document doc) {
		String s = xmlHeader;
		NodeList childs = doc.childNodes;
		for (int i = 0; i < childs.length; i++) {
			Node c = childs.item(i);
			s+=printNode(c);
		}
		return s;
	}
	
	public String printNode(Node n) {
		String s = "<"+n.nodeName;
		if(n.nodeType==Node.ELEMENT_NODE) {
//			((org.w3c.dom.Element)n).getAttributeNode(name)
		}
		return s;
	}
	public String print(List<Node> l) {
		String s = "{";
		for(Node n : l) {
			s+=", ("+n.nodeName+"-"+n.nodeValue+")";
		}
		return s+"}";
	}
}
