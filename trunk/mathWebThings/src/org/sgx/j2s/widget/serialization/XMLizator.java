package org.sgx.j2s.widget.serialization;

import java.util.Iterator;

import org.sgx.j2s.widget.IWidget;

public class XMLizator {
	
	private static final String ELEM_WIDGET = "widget";
	
	/** return the xml representation of w. if rec, also w's childrens will be serialized */
	public String xmlElementFor(IWidget w, boolean rec) {
		String s = "<"+ELEM_WIDGET+" " +				
			"class=\"";
		for (String cl :  w.getClasses()) {
			s+=cl+" ";			
		}
		s+="\" id=\""+w.getId()+"\"";
		if(rec) {
			
		}
		return s+"</"+ELEM_WIDGET+">";
		
	}
//	public String xmlDocumentFor(IWidget w, boolean rec) {
//		
//	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
