package org.sgx.jmencoder.streamplayer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.util.LinkedList;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.sgx.jmencoder.streamplayer.model.Section;
import org.sgx.jmencoder.streamplayer.model.StreamDocument;
import org.sgx.jmencoder.streamplayer.model.StreamElement;
import org.sgx.jmencoder.streamplayer.model.StreamEndpoint;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
/**
 * utilities related to the model of StreamPlayer
 * @author sgurin
 *
 */
public class ModelUtils {
	
	private static ModelUtils instance;
	public static final String DEFAULT_PLAY_LIST_FILE = "resources"+File.separator+"config"+File.separator+"uruguayRadios.xml";
	public static final String EMPTY_PLAY_LIST_FILE = "resources"+File.separator+"config"+File.separator+"new.xml";
	public static final String DEFAULT_PLAYLIST_DIR = "resources"+File.separator+"playlists";
	private int endpointCounter;
	
	

	private ModelUtils() {
	}

	public static ModelUtils getInstance() {
		if (null == instance) {
			instance = new ModelUtils();
		}
		return instance;
	}
	
	////model to - from - xml stuff //////
	private static final String TAG_SECTION = "section",
			TAG_ENDPOINT = "endpoint", TAG_DOCUMENT = "document";
	
	/**
	 * 
	 * @param xmlDoc path to file
	 * @return
	 * @throws ParserConfigurationException
	 * @throws IOException
	 * @throws SAXException
	 */
	public StreamDocument readXMLDocument(String xmlDoc)
			throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory
				.newInstance();
		DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
		FileInputStream fis = new FileInputStream(new File(xmlDoc));
		Document doc = docBuilder.parse(fis);
		doc.getDocumentElement().normalize();
		String docName = null, docDescription = null, docImage = null;
		NodeList elems = doc.getDocumentElement().getChildNodes();
		List<StreamElement> streamNodeList = new LinkedList<StreamElement>();
		for (int j = 0; j < elems.getLength(); j++) {			
			StreamElement sel = null;
			if (elems.item(j) instanceof Element){
				sel=readXMLStreamElement((Element)elems.item(j));
				streamNodeList.add(sel);
			}
		}
		StreamDocument sdoc = new StreamDocument(docName, docDescription, docImage);
		sdoc.setNodes(streamNodeList);
		return sdoc;
	}
	
	public StreamDocument newXMLDocument() throws ParserConfigurationException, SAXException, IOException {		
		return readXMLDocument(EMPTY_PLAY_LIST_FILE);
	}

	public StreamDocument readXMLDocument(File f) throws ParserConfigurationException, SAXException, IOException {
		return readXMLDocument(f.getAbsolutePath());		
	}
	private StreamElement readXMLStreamElement(Element e) {
		
		if (e.getNodeName().toLowerCase().equals(TAG_SECTION)) {
			return readXMLSection(e);
		} else if (e.getNodeName().toLowerCase().equals(TAG_ENDPOINT)) {
			return readXMLEndpoint(e);
		} else {
			throw new RuntimeException("only <section> or <endpoint> elements " +
				"allowed and "+e.getNodeName()+" found. ");
		}
	}

	public StreamEndpoint readXMLEndpoint(Element item) {
		String name = item.getAttribute(StreamElement.PROP_NAME), desc = item
				.getAttribute(StreamElement.PROP_DESCRIPTION), image = item
				.getAttribute(StreamElement.PROP_IMAGE), url = item
				.getAttribute(StreamEndpoint.PROP_URL);

		endpointCounter++;
//		System.out.println("reading endpoint: "+name);
		return new StreamEndpoint(name, desc, image, url);
	}

	public int getEndpointCounter() {
		return endpointCounter;
	}

	public Section readXMLSection(Element item) {

		String name = item.getAttribute(StreamElement.PROP_NAME), desc = item
				.getAttribute(StreamElement.PROP_DESCRIPTION), image = item
				.getAttribute(StreamElement.PROP_IMAGE);

//		System.out.println("reading section: "+name);
		List<StreamElement> children = new LinkedList<StreamElement>();
		NodeList childrenElements = item.getChildNodes();
		for (int i = 0; i < childrenElements.getLength(); i++) {
			Node n = childrenElements.item(i);
			if(n instanceof Element)
				children.add(readXMLStreamElement((Element)n));
		}
		return new Section(name, desc, image, children);
	}

	
	//write model to xml
	public Document writeXMLDocument(StreamDocument sdoc) throws ParserConfigurationException {
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
		Document doc = docBuilder.newDocument();
		Node root = doc.createElement(TAG_DOCUMENT);
		doc.appendChild(root);
		List<StreamElement> snodes = sdoc.getNodes();
		for(StreamElement se : snodes) {
			Element el = null;
			if(se instanceof StreamEndpoint) {
				el= writeXMLEndpoint((StreamEndpoint) se, doc);
			}
			else if(se instanceof Section) {
				el= writeXMLSection((Section) se, doc);
			}
			else
				throw new RuntimeException("not possible1");
			root.appendChild(el);
		}
		return doc;
	}
	public  Element writeXMLSection(Section se, Document doc) {
		Element el = doc.createElement(TAG_SECTION);
		el.setAttribute(Section.PROP_NAME, se.getName());
		el.setAttribute(Section.PROP_DESCRIPTION, se.getDescription());
		el.setAttribute(Section.PROP_IMAGE, se.getImage());
		for(StreamElement child : se.getChildren()) {
			Element c = null;
			if(child instanceof StreamEndpoint) {
				c= writeXMLEndpoint((StreamEndpoint)child, doc);
			}
			else if(child instanceof Section) {
				c= writeXMLSection((Section) child, doc);
			}
			else
				throw new RuntimeException("not possible1");
			el.appendChild(c);
		}
		return el;
	}

	public  Element writeXMLEndpoint(StreamEndpoint se, Document doc) {
		Element el = doc.createElement(TAG_ENDPOINT);
		el.setAttribute(Section.PROP_NAME, se.getName());
		el.setAttribute(Section.PROP_DESCRIPTION, se.getDescription());
		el.setAttribute(Section.PROP_IMAGE, se.getImage());
		el.setAttribute(StreamEndpoint.PROP_URL, se.getUrl());
		return el;
	}

	public void writeXML(Document xmldoc, OutputStream out ) throws TransformerException {
		DOMSource domSource = new DOMSource(xmldoc);
		StreamResult streamResult = new StreamResult(out);
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer serializer = tf.newTransformer();
		serializer.setOutputProperty(OutputKeys.ENCODING,"ISO-8859-1");
		serializer.setOutputProperty(OutputKeys.INDENT,"yes");
		serializer.transform(domSource, streamResult); 
	}
	
	
	/////model to-from swing jtree /////
	
	public DefaultTreeModel toTreeModel(StreamDocument sd) {		
		DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(sd);
		for(StreamElement se : sd.getNodes()) {
			DefaultMutableTreeNode n=null;
			if(se instanceof Section)
				n=toTreeNodeSection((Section)se);
			else if(se instanceof StreamEndpoint)
				n=toTreeNodeEndpoint((StreamEndpoint)se);
			rootNode.add(n);
		}		
		return new DefaultTreeModel(rootNode );
	}

	private DefaultMutableTreeNode toTreeNodeEndpoint(StreamElement se) {
		DefaultMutableTreeNode n = new DefaultMutableTreeNode(se);
		return n;
	}

	private DefaultMutableTreeNode toTreeNodeSection(Section se) {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(se);
		for(StreamElement child : se.getChildren()) {
			DefaultMutableTreeNode n=null;
			if (child instanceof Section)
				n = toTreeNodeSection((Section) child);
			else if (child instanceof StreamEndpoint)
				n = toTreeNodeEndpoint((StreamEndpoint) child);
			root.add(n);
		}
		return root;
	}

	public StreamDocument documentFromTreeModel(DefaultMutableTreeNode n) {
		//return n.getUserObject() will not work because the user changes are not in the model 
		// but in the swing tree model. We have to recurse swing tree model and rebuild the StreamDocument		
		Object uobj = n.getUserObject();
		if (uobj instanceof StreamDocument) {
			//return n.getUserObject() will not work because the user changes are not in the model 
			// but in the swing tree model. We have to recurse swing tree model and rebuild the StreamDocument
			StreamDocument sd = (StreamDocument) uobj;
			
			LinkedList<StreamElement> childs = new LinkedList<StreamElement>();
			for (int i = 0; i < n.getChildCount(); i++) {
				DefaultMutableTreeNode child = (DefaultMutableTreeNode)n.getChildAt(i);
				Object childData = child.getUserObject();
				StreamElement sel = null;
				if (childData instanceof Section) {
					sel = sectionFromTreeModel(child);
				}
				else if (childData instanceof StreamEndpoint) {
					sel = (StreamEndpoint)childData;
				}
				childs.add(sel);
			}
			sd.setNodes(childs);
			return sd;
		} else {
			throw new RuntimeException(
					"Invalid parameter Node. User data must be a stream document, not a "
							+ uobj.getClass());
		}
	}
/**
 * recursive treemodel to model  
 * @param n
 * @return
 */
	private Section sectionFromTreeModel(DefaultMutableTreeNode n) {
		Object uobj = n.getUserObject();
		if (uobj instanceof Section) {
			Section section = (Section) uobj;
			LinkedList<StreamElement> childs = new LinkedList<StreamElement>();
			for (int i = 0; i < n.getChildCount(); i++) {
				DefaultMutableTreeNode child = (DefaultMutableTreeNode)n.getChildAt(i);
				Object childData = child.getUserObject();
				StreamElement sel = null;
				if (childData instanceof Section) {
					sel = sectionFromTreeModel(child);
				}
				else if (childData instanceof StreamEndpoint) {
					sel = (StreamEndpoint)childData;
				}
				childs.add(sel);
			}
			section.setChildren(childs);
			return section;
		}
		else return null;
		
	}


	public void resetEndpointCounter() {
		endpointCounter=0;
	}

//	public void mergePlayLists(List<File>playlists) {
//		
//	}
//	
//	private Section union(String name, String description, Section s1, Section s2) {
//		if(s1==null&&s2==null)return null;
//		if(s1==null)return s2;
//		if(s2==null)return s1;
//		Section r = new Section(name, description, null, null);
//		List<StreamElement> c1 = s1.getChildren();
//		List<StreamElement> c2 = s2.getChildren();
//		for(StreamElement se1 : c1) {
//			if(SEListGet(c2))
//		}
//	}
//	private static StreamElement SEListGet(List<StreamElement> l, String name){
//		for(StreamElement se : l) {
//			if(se.getName().equals(name))
//				return se;
//		}
//		return null;
//	}
}
