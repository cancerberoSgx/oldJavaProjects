package org.sgx.jmencoder.streamplayer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ConnectionUtils {

	public static interface ReadUrlListener {
		void notify(StringBuffer sb) throws Exception;
	}

	public static void readUrlAsync (final String url, ReadUrlListener l) {
		ConnectionUtils.listeners.put(url, l);
		new Thread() {
			public void run() {
				StringBuffer sb=null;
				try {
					sb = ConnectionUtils.readUrl(url);
				} catch (Exception e) {					
					e.printStackTrace();
					try {
						ConnectionUtils.listeners.get(url).notify(sb);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				try {
					ConnectionUtils.listeners.get(url).notify(sb);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			};
		}.start();
	}

	public static StringBuffer readUrl(String url) throws IOException {
		URL u = new URL(url);
		StringBuffer sb = new StringBuffer();
		BufferedReader in = new BufferedReader(
					new InputStreamReader(
					u.openStream()));
		String inputLine;
		while ((inputLine = in.readLine()) != null)
		    sb.append(inputLine+"\n");
		in.close();
		return sb;
	}

	public static List<String> matchUrlsIn(String s, String separator) {
		String [] parts = s.split(separator);
//		System.out.println("spliting with "+separator+" gives "+parts.length);
		List<String> l = new LinkedList<String>();
        for( String item : parts ) {
        	item=item.trim();
        	item=item.replace("\\n", "");
//        	System.out.println("***"+item+"*** "+isURL(item));
        	if(ConnectionUtils.isURL(item))
        		l.add(item);
        }
//        System.out.println("spliting with "+separator+" gives "+l.size());
        return l;
	}

	public static boolean isURL(String s) {
	//		try {
	//            new URL(s);
	//            return true;
	//        } catch (MalformedURLException e) {
	//        	return false;
	//        }
	        
	        String regex = "^(https?|mms|mssx|mmsh|rtsp|pnm)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
	        return s.matches(regex);
	        	
		}

	public static List<String> findUrlsIn(String s) {
		List<String> l = matchUrlsIn(s,"\\s");
		if(l.size()==0)
			l = matchUrlsIn(s,"\\\"");
		if(l.size()==0)
			l = matchUrlsIn(s,"\\s*\\=\\s*");
		return l;
	}
	///** a very dirty way of extracting url endpoints from ASX documents. TODO: makea more XML version */
	//	public static List<String> extractASXEndpoints(String s) {
	//		String [] parts = s.split("\n");
	////		for (int i = 0; i < parts.length; i++) {
	////			String p = parts[i].toLowerCase();
	////			if(p.contains("<))
	////		}
	//		// TODO Auto-generated method stub
	//		return null;
	//	}

	public static Map<String, ReadUrlListener> listeners = new HashMap<String, ReadUrlListener>();

}
