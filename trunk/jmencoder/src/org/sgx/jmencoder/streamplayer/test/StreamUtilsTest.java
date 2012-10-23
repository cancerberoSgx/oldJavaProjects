package org.sgx.jmencoder.streamplayer.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.TreeModel;
import javax.xml.parsers.ParserConfigurationException;

import org.sgx.jmencoder.streamplayer.ConnectionUtils;
import org.sgx.jmencoder.streamplayer.ModelUtils;
import org.sgx.jmencoder.streamplayer.ConnectionUtils.ReadUrlListener;
import org.sgx.jmencoder.streamplayer.gui.EditableTree;
import org.sgx.jmencoder.streamplayer.model.StreamDocument;
import org.sgx.swing.utils.SwingUtils;
import org.sgx.utils.simplexml.DocHandler;
import org.sgx.utils.simplexml.QDParser;
import org.sgx.utils.simplexml.Reporter;
import org.xml.sax.SAXException;

public class StreamUtilsTest {

	public static void main(String[] args) {
		try {
//			testLoadXml();
//			testTreeModel();
//			testParseUrl();
//			testGetUrl();
//			testGetUrlAsync();
//			testGetUrlParseUrls();
//			testIsUrl("mms://a1495.l4520545494.c45205.g.lm.akamaistream.net/D/1495/45205/v0001/reflector:45494");
			
			printAllUrlsFrom("http://www.radiotower.com/player.php?channel_id=5841");
//			parseASX("http://www.radio36.com.uy/radio36.asx");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
//	<embed width="375" height="100" type="audio/x-pn-realaudio-plugin" autostart="True" pluginspage="www.real.com" console="one" controls="All" nojava="true" src="http://www.breakhost.com.br/bondemcz/"/>
	
	
	private static void testIsUrl(String string) {
		System.out.println(ConnectionUtils.isURL(string));
	}


	private static void parseASX(String url) throws IOException {
		StringBuffer s = ConnectionUtils.readUrl(url);
		DocHandler doc = new Reporter();
		try {
			QDParser.parse(doc,new StringReader(s.toString()));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	private static void printAllUrlsFrom(String url) throws IOException {
		StringBuffer s = ConnectionUtils.readUrl(url);
//		System.out.println(s);
		List<String> urls = ConnectionUtils.findUrlsIn(s.toString());
		for (Iterator iterator = urls.iterator(); iterator.hasNext();) {
			System.out.println((String) iterator.next());			
		}
	}

	private static void testParseUrl() {
		String u = "http://66.98.168.9:9000";
//		try {
			System.out.println(ConnectionUtils.isURL(u));
//		} catch (Exception e) {
//			System.out.println("can't catch: "+e.getLocalizedMessage());
//			e.printStackTrace();
//		}
	}

	private static void testGetUrlParseUrls() {
		ConnectionUtils.readUrlAsync("http://www.radiohoritzo.nom.es/radiohoritzo.m3u", new ConnectionUtils.ReadUrlListener() {			
			@Override
			public void notify(StringBuffer sb) {
				System.out.println(sb.toString());			
			}
		});
	}

	private static void testGetUrlAsync() {
		ConnectionUtils.readUrlAsync("http://www.radiohoritzo.nom.es/radiohoritzo.m3u", new ConnectionUtils.ReadUrlListener() {			
			@Override
			public void notify(StringBuffer sb) {
				String s = sb.toString();
				List<String> urls = ConnectionUtils.findUrlsIn(s);
				for (Iterator iterator = urls.iterator(); iterator.hasNext();) {
					System.out.println(iterator.next());
				}
			}
		});
	}

	private static void testGetUrl() throws IOException {
		StringBuffer sb = ConnectionUtils.readUrl("http://www.radiohoritzo.nom.es/radiohoritzo.m3u");
		System.out.println(sb.toString());
	}

	private static void testTreeModel() throws ParserConfigurationException, SAXException, IOException {
		//cargamos modelo de disco
		ModelUtils utils = ModelUtils.getInstance();
		String file = "resources"+File.separator+"config"+File.separator+"uruguayRadios.xml";
		StreamDocument sd = utils.readXMLDocument(file);
		
		//creamos el treemodel a partir del modelo
		TreeModel treeModel = utils.toTreeModel(sd);
		EditableTree editableTree = new EditableTree();
		JScrollPane panel = editableTree.getContent();
		JTree tree = editableTree.getTree();
		tree.setModel(treeModel);
		SwingUtils.showInFrame(panel);
	}

	private static void testLoadXml() throws ParserConfigurationException, SAXException, IOException {
		ModelUtils utils = ModelUtils.getInstance();
		String file = "resources"+File.separator+"config"+File.separator+"endpointList1.xml";
		StreamDocument sd = utils.readXMLDocument(file);
		StringBuffer b = new StringBuffer("");
		sd.dump(b);
		System.out.println(b.toString());
	}
	
	

}
