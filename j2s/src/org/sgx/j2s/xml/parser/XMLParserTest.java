package org.sgx.j2s.xml.parser;

import org.sgx.j2s.html.dom.Document;
import org.sgx.j2s.html.dom.Element;
import org.sgx.j2s.html.dom.Node;
import org.sgx.j2s.js.JsUtils;
import org.sgx.j2s.util.List;
import org.sgx.j2s.util.Predicate;
import org.sgx.j2s.util.impl.Utils;
import org.sgx.j2s.util.impl.*;
public class XMLParserTest {

	private Document doc;

	public static void main(String[] args) {
		XMLParserTest test = new XMLParserTest();
		JsUtils.activateJ2sDebug();
		Utils.cleanTests();
		test.test1();
		System.out.println(Utils.printAssertsFailed());
	}

	
	
	private void test1() {
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><hola attr1=\"hola2\" id=\"foo\">text</hola>";
		XMLParser _p = XMLParser.getInstance();
		try {
			doc = _p.parseFromString(xml);
			String prefix="string_";
			Utils.assertTrue(doc.parentNode==null, prefix+"test1 0");
			//query methods
			Node hola1 = doc.getElementsByTagName("hola").item(0);
			Utils.assertTrue(doc.getElementsByTagName("hola").length==1, prefix+"test1 1");
			Utils.assertTrue(hola1.nodeName.equals("hola"), prefix+"test1 2");
			Utils.assertTrue(hola1.nodeType==Node.ELEMENT_NODE, prefix+"test1 2.1");
			
			Utils.assertTrue(hola1.childNodes.length==1, prefix+"test1 3");  //text node is the only child
			
//			manipulation
			Element hola2 = doc.createElement("hola"); //create
			hola1.appendChild(hola2);//add to document
			
			hola2.setAttribute("attr1", "value1");
			Utils.assertTrue(hola2.getAttribute("attr1").equals( "value1"), prefix+"test1 3.1");
			
			hola1.appendChild(hola2);
			Utils.assertTrue(doc.getElementsByTagName("hola").length==2, prefix+"test1 4");
			
			List<Node> l = _p.selectElementsByAttrib(doc, "attr1", "value1");
			Utils.assertTrue(l.size()==1, prefix+"test1 5 "+ Utils.toString(Utils.toArray(l)));
			System.out.println(_p.print(l));
			
			//textnodes
//			l = _p.selectElementsByAttrib(doc, "id", "foo");
//			Utils.assertTrue(l.size()==1, prefix+"test1 6 ");
			
//			l=_p.selectElements(l.iterator().next(), new Predicate<Node>() {
//				public boolean select(Node o) {
//					return o.nodeType==Node.TEXT_NODE;
//				}				
//			});
//			Utils.assertTrue(l.size()==1 && l.iterator().next().nodeValue.equals("text"), prefix+"test1 7 ");
			
			
			//attributes
//			hola2
			
		}catch (Exception e) {
			Utils.assertTrue(false, "exception parsing doc 1");
			e.printStackTrace();
			return ;
		}
	} 

}
