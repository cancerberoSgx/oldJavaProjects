package org.sgx.jmencoder.streamplayer.test.probes;
//package org.sgx.jmencoder.streamplayer.test;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.net.URISyntaxException;
//import java.net.URL;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.Map;
//
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.parsers.ParserConfigurationException;
//
//import org.w3c.dom.Document;
//import org.w3c.dom.Element;
//import org.w3c.dom.Node;
//import org.w3c.dom.NodeList;
//import org.xml.sax.SAXException;
//
//import com.beeblos.configsearch.ParseException;
//import com.beeblos.configsearch.Utils;
//import com.beeblos.configsearch.beans.ConfigSearchElem;
//import com.beeblos.configsearch.beans.RepresentativeField;
//import com.beeblos.configsearch.beans.ResumenField;
//import com.beeblos.documentlibrary.util.Constants;
///**
// * @author SGURIN
// */
//public class ConfigRepresentativeFields {
//	
//	public static final String RepositoryElemTag ="Repository";
//	public static final String DocumentClassElemTag = "DocumentClass";
//	public static final String RepresentativeFieldElemTag = "RepresentativeField";
//	public static final String ResumenFieldElemTag = "ResumenField";
//	
//	public static final String NameAttribName = "name";
//	public static final String AlternativeAttribName = "alternative";
//	public static final String ResultTypeAttribName = "ResultType";
//	static String xmlHead = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"+
//		"<SearchConfiguration xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n"+
//	    "xsi:noNamespaceSchemaLocation=\"ConfigSearch.xsd\">\n", 
//    	xmlTail = "\n</SearchConfiguration>";
//
//	public static List<ConfigSearchElem> config= new LinkedList<ConfigSearchElem>();
////	public static String columnWidthsTxt="";
//	
//	public  static void updateConfigSearch() {		
//			try {
//				config = readConfigSearchFrom(com.beeblos.configsearch.cc.Constants.CCCONFIGSEARCH_FILENAME);
//			} catch (ParserConfigurationException e) {
//				e.printStackTrace();
//			} catch (SAXException e) {
//				e.printStackTrace();
//			} catch (IOException e) {
//				e.printStackTrace();
//			} catch (ParseException e) {
//				e.printStackTrace();
//			}
//	}
//	
//	static{
//		updateConfigSearch();
//	}
//	
//
//	
//	public static ConfigSearchElem getConfigElementForRepo(String repositoryName, 
//			List<ConfigSearchElem> configSearch) {
//		Iterator<ConfigSearchElem> i=configSearch.iterator();
//		while(i.hasNext()) {
//			ConfigSearchElem cse = i.next();
//			if(cse.getType().equals(ConfigRepresentativeFields.RepositoryElemTag) && 
//					repositoryName.equals(cse.getName()))  {
//				return cse;
//			}
//		}
//		return null;
//	}
//	
//	public static ConfigSearchElem getConfigElementForDocClass(String docClassName, 
//			List<ConfigSearchElem> configSearch) {
//		Iterator<ConfigSearchElem>i=configSearch.iterator();				
//		while(i.hasNext()) {
//			ConfigSearchElem cse = i.next();
//			if(cse.getType().equals(ConfigRepresentativeFields.DocumentClassElemTag) && 
//					docClassName.equals(cse.getName()))  {
//				return cse;
//			}
//		}
//		return null;
//	}
//	
//	public static String getConfigXml() {
//		String s = xmlHead;		
//		Iterator<ConfigSearchElem> i = config.iterator();
//		while(i.hasNext()) {
//			s+="\n"+i.next().getXmlElem();
//		}
//		return s+xmlTail;
//	}
//	public static void saveConfigTo(String path) throws IOException, URISyntaxException {
//		URL url = ConfigRepresentativeFields.class.getClassLoader().getResource(Constants.SMCONFIGSEARCH_FILENAME);
//		FileOutputStream fos = new FileOutputStream(new File(url.toURI()));		
//		fos.write(getConfigXml().getBytes());
//		fos.close();
//	}
//	public static void saveConfig() throws IOException, URISyntaxException {
//		saveConfigTo(Constants.CONFIGSEARCH_FILENAME);
//	}
//	
//	public static void  updateElement(String name, String xmlElem) throws ParserConfigurationException, SAXException, IOException, ParseException {
//		//parseo un nuevo doc xml, y reemplazo elemento	
//		List<ConfigSearchElem> myconfig = readConfigSearchFromXml(xmlHead+xmlElem+xmlTail);
//		
//		Iterator<ConfigSearchElem> i = config.iterator();
//		int ind=0;
//		while(i.hasNext()) {
//			if(i.next().getName().equals(name))
//				break;
//			ind++;
//		}
//		if(ind<config.size()) { //found
//			config.remove(ind);			
//		}
//		
//		config.add(myconfig.get(0));
//	}
//	
//	public static String getXmlElemForDocClass(String docClassId) {
//		Iterator<ConfigSearchElem> i = config.iterator();
//		while(i.hasNext()) {
//			ConfigSearchElem cs = i.next();
//			if(cs.getType().equals(DocumentClassElemTag) && cs.getName().equals(docClassId)) {
//				return cs.getXmlElem();
//			}
//		}
//		return null;
//	}
//	
//	public static String getXmlElemContentForDocClass(String docClassId) {
//		Iterator<ConfigSearchElem> i = config.iterator();
//		while(i.hasNext()) {
//			ConfigSearchElem cs = i.next();
//			if(cs.getType().equals(DocumentClassElemTag) && cs.getName().equals(docClassId)) {
//				return cs.getXmlElemContent();
//			}
//		}
//		return null;
//	}
//	public static final String columnWidthsElemTag="columnWidths";
//	static String extractColumnWidths(Node node) throws ParseException {
//		return node.getTextContent();
//	}
//	static List<RepresentativeField> extractRepresentativeFields(Node n) throws ParseException {
//		 NodeList elems = n.getChildNodes();
//		 List<RepresentativeField> l = new LinkedList<RepresentativeField>();
//	     for(int i=0; i<elems.getLength(); i++) {
//	    	 if(elems.item(i).getNodeName().equals(RepresentativeFieldElemTag)) {
//	    		 if(!getAttrs(elems.item(i)).containsKey(NameAttribName))
//	    			 throw new ParseException("<"+elems.item(i).getNodeName()+"> without a name attribute. ");
//	    		 RepresentativeField f = new RepresentativeField(getAttrs(elems.item(i)).get(NameAttribName), 
//	    				 getAttrs(elems.item(i)).get(AlternativeAttribName));
//	    		 l.add(f);
//	    	 }	    
//	     }
//	     return l;
//	}
//	
//	static List<ResumenField> extractResumenFields(Node n) throws ParseException {
//		 NodeList elems = n.getChildNodes();
//		 List<ResumenField> l = new LinkedList<ResumenField>();
//	     for(int i=0; i<elems.getLength(); i++) {
//	    	 if(elems.item(i).getNodeName().equals(ResumenFieldElemTag)) {
//	    		 if(!getAttrs(elems.item(i)).containsKey(NameAttribName))
//	    			 throw new ParseException("<"+elems.item(i).getNodeName()+"> without a name attribute. ");
//	    		 ResumenField f = new ResumenField(getAttrs(elems.item(i)).get(NameAttribName), 
//	    				 getAttrs(elems.item(i)).get(AlternativeAttribName));
//	    		 l.add(f);
//	    	 }
//	     }
//	     return l;
//	}
//	
//	
//	static Map<String, String> getAttrs(Node n) {
//		if(n==null || !(n instanceof Element))
//			return new HashMap<String, String>();
//		Map<String, String> m = new HashMap<String, String>();
//		for(int j=0; j<n.getAttributes().getLength(); j++)			
//			m.put(n.getAttributes().item(j).getNodeName(), n.getAttributes().item(j).getNodeValue());
//		return m;
//	}
//	
//	static void checkAttribsFor(String elemName, Map<String, String> attrs) throws ParseException {
//	
//		Iterator<String> i = attrs.keySet().iterator();
//		if(!attrs.containsKey(NameAttribName))
//			throw new ParseException("elem <"+elemName+"> without "+NameAttribName+" attribute" );
//		while(i.hasNext()) {
//			String attrname = i.next();
//			if(!attrname.equals(NameAttribName) && !attrname.equals(AlternativeAttribName)) {
//				if(!elemName.equals(RepositoryElemTag) && attrname.equals(ResultTypeAttribName))
//					throw new ParseException(ResultTypeAttribName +" attrib name found in <"+elemName+">. Only in <Repository> permited. ");
//			}
//		}
//	}
//	
//	
//	
//	
//	private static List<ConfigSearchElem>  readConfigSearchFromXml(String xml) throws ParserConfigurationException, SAXException, IOException, ParseException {
//		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
//        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
//        Document doc = docBuilder.parse (Utils.stringToInputStream(xml));
//        doc.getDocumentElement ().normalize ();
//        
//        List<ConfigSearchElem> csElems = new LinkedList<ConfigSearchElem>();
//        
//        NodeList elems = doc.getDocumentElement().getChildNodes(); 
//        
//        for(int i=0; i<elems.getLength(); i++) { //para cada elemnto primario de searchConfig.xml
//        	if((elems.item(i) instanceof Element)) {
//	        	String elName = elems.item(i).getNodeName();
//	        	if(elems.item(i) instanceof Element && !elName.equals(RepositoryElemTag) &&
//	        			!elName.equals(DocumentClassElemTag) && !elName.equals(columnWidthsElemTag))
//	        		throw new ParseException("Element <"+elems.item(i).getNodeName()+
//	        				"> unrecognized");
//	        	
//	        	if(elName.equals(columnWidthsElemTag)){
////	        		columnWidthsTxt = extractColumnWidths(elems.item(i));
//	        	}
//	        	else{
//		        	Map<String, String> attrs = getAttrs(elems.item(i));
//		        	checkAttribsFor(elName, attrs);		        	
//		        	ConfigSearchElem scElem = new ConfigSearchElem(attrs.get(NameAttribName), elName);
//		        	scElem.setRepresentativeFields(extractRepresentativeFields(elems.item(i)));
//		        	scElem.setResumenFields(extractResumenFields(elems.item(i)));
//		        	scElem.setResultType(attrs.get(ResultTypeAttribName));		        	
//		        	csElems.add(scElem);
//	        	}
//        	}
//        }
//        
//		return csElems;
//	}
//	/**
//	 * lee la lista de configuraciones desde un xml
//	 * @param path
//	 * @return
//	 * @throws ParserConfigurationException 
//	 * @throws IOException 
//	 * @throws SAXException 
//	 * @throws ParseException 
//	 */
//	private static List<ConfigSearchElem> readConfigSearchFrom(String url) 
//			throws ParserConfigurationException, SAXException, IOException, ParseException {
//		InputStream is = ConfigRepresentativeFields.class.getClassLoader().getResourceAsStream(url);
//		return readConfigSearchFromXml(Utils.readFileAsString(is));		
//	}
//	
//
//	private static List<ConfigSearchElem> readConfigSearchFrom(File f) 
//	throws ParserConfigurationException, SAXException, IOException, ParseException {
//		FileInputStream is = new FileInputStream(f);
//		return readConfigSearchFromXml(Utils.readFileAsString(is));	
//	}
//	
//	
//	
//	/**
//	 * carga configuraci�n nuevamente del archivo
//	 * @throws SAXException 
//	 * @throws ParserConfigurationException 
//	 * @throws ParseException 
//	 * @throws IOException 
//	 */
//	public void reload() throws IOException, ParseException, ParserConfigurationException, SAXException{
//		config = readConfigSearchFrom(com.beeblos.configsearch.cc.Constants.CCCONFIGSEARCH_FILENAME);
//	}
//	
//	
//	//test
////	public static void main(String [] a){
//////		test1();
////		test2();
////	}
////
////	private static void test2() {
////		String xml = System.getProperty("user.home")+File.separator+"configSearch.xml";
////		try {
////			readConfigSearchFrom(new File(xml));
////		} catch (ParserConfigurationException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		} catch (SAXException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		} catch (IOException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		} catch (ParseException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		} 	
////
//////		System.out.println("columnsWidths: "+columnWidthsTxt);
////	}
//
////	private static void test1() {
////		String xml = System.getProperty("user.home")+File.separator+"configSearch.xml";
////		try {
////			//xmlPath=xml;// nes 20080726 pasado a Constants.CONFIGSEARCH_FILENAME
////			updateConfigSearch();
////			System.out.println(readConfigSearchFrom(Constants.CONFIGSEARCH_FILENAME));
////			System.out.println(getXmlElemForDocClass("41"));
////			System.out.println(getXmlElemContentForDocClass("41"));
////			
////			String newElem = "<DocumentClass Name=\"41\">"+
////					"<RepresentativeField Name=\"property_5333\"/>"+
////			        "<RepresentativeField Name=\"malvicho\"/>"+
////			    "</DocumentClass>";
////			updateElement("41", newElem);
////			System.out.println("\n\n"+getXmlElemForDocClass("41"));
////			
////			saveConfigTo("c:\\pp.xml");
////		} catch (ParserConfigurationException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		} catch (SAXException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		} catch (IOException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		} catch (ParseException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		} catch (URISyntaxException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
////	}
//	
//	
//}