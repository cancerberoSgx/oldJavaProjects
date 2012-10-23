package org.sgx.j2s.xml.parser;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.sgx.j2s.util.List;
import org.sgx.j2s.util.Predicate;
import org.sgx.j2s.util.impl.LinkedList;
import org.sgx.j2s.util.impl.Utils;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class OnlyJava {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Utils.cleanTests();
			test1();
			System.out.println(Utils.printAssertsFailed());
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void test1() throws ParserConfigurationException, SAXException, IOException {
		XMLParser p = XMLParser.getInstance();
		String src = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><hola attr1=\"hola2\" id=\"foo\">text</hola>";
		javax.xml.parsers.DocumentBuilderFactory docFactory = javax.xml.parsers.DocumentBuilderFactory.newInstance();
		javax.xml.parsers.DocumentBuilder docBuilder = docFactory.newDocumentBuilder();			
		StringBuffer StringBuffer1 = new StringBuffer(src);
		java.io.ByteArrayInputStream Bis1 = new java.io.ByteArrayInputStream(StringBuffer1.toString().getBytes());
		org.w3c.dom.Document doc = docBuilder.parse(Bis1);
		Element hola2 = doc.createElement("hola"); 
		Node hola1 = doc.getElementsByTagName("hola").item(0);
		hola1.appendChild(hola2);//add to document
		
		hola2.setAttribute("attr1", "value1");
		Utils.assertTrue(hola2.getAttribute("attr1").equals( "value1"), "test1 3.1");
		Utils.assertTrue(doc.getElementsByTagName("hola").getLength()==2, "test1 4");
		
		List<Node> l = selectElementsByAttrib(doc, "attr1", "value1");
		Utils.assertTrue(l.size()==1, "test1 5 "+ Utils.toString(Utils.toArray(l)));
		
	}
	
	public static List<Node> selectElements(Node e, Predicate<Node> p) {
		List<Node> l = new LinkedList<Node>();
		NodeList childs = e.getChildNodes();
		if(childs!=null) {
			for (int i = 0; i < childs.getLength(); i++) {
				Node c = childs.item(i);
				l.addAll(selectElements(c, p));
			}
		}
		if(p.select(e))
			l.append(e);
		return l;
	}
public static List<Node> selectElementsByAttrib(Node e, final String attrib, final String attribValue) {
	return selectElements(e, new Predicate<Node>() {
		public boolean select(Node o) {
			if(o.getNodeType()==Node.ELEMENT_NODE) {					
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


}
