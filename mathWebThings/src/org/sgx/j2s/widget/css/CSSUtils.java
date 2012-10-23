package org.sgx.j2s.widget.css;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.sgx.j2s.widget.Widget;
import org.sgx.j2s.widget.base.Color;
import org.sgx.j2s.widget.base.Rectangle;
/**
 * poor's man small css parsing utilities.
 * 
 * @author SGURIN
 *
 */
public class CSSUtils {

	public static final String PROP_BACKGROUND_COLOR = "background-color" ,
		PROP_BOUNDS = "bounds", PROP_LOCATION="location", PROP_DIMENSION="dimension";
	
	public static void stylize(Widget w, String prop, String val) {
		if(prop.equals(PROP_BACKGROUND_COLOR)){
			w.setBackground(Color.parseColor(val));
		}
		else if(prop.equals(PROP_BOUNDS)){
			w.setBounds(Rectangle.parseRectangle(val));
		}
		else if(prop.equals(PROP_LOCATION)){
			w.setBounds(Rectangle.parseRectangle(val));
		}
		else if(prop.equals(PROP_DIMENSION)){
			w.setBounds(Rectangle.parseRectangle(val));
		}
		
	}
	
public static void parseCSS(Map<String, Rule> rules, String css) throws CSSParsingException {
	String [] fragments = css.split("}");
	for (int i = 0; i < fragments.length; i++) {
		String f = fragments[i];
		
		if(!(f.indexOf('{')!=-1) || !(f.indexOf('}')==-1) || 
				f.indexOf('}')!=f.lastIndexOf('}')
			){
			throw new CSSParsingException("{ } anidation problem");
		}
		
		String [] arr1 = f.split("\\{");
		if(arr1.length!=2){
			throw new CSSParsingException("{ } anidation problem");
		}
		String[] selectorsStrArr = arr1[0].split(",");
		String[] propsStrArr = arr1[1].split(";");
		Map<String, String> propsMap = new HashMap<String, String>();
		for (int j = 0; j < propsStrArr.length; j++) {
			String[] propStrArr = propsStrArr[j].split(":");
			if(propStrArr.length!=2){
				throw new CSSParsingException("syntax error in definition: \""+propsStrArr[j]+"\"");
			}
			String prop=propStrArr[0].trim(), value=propStrArr[1].trim();
			if(!isValidProp(prop, value)){
				throw new CSSParsingException("invalid property \""+prop+"\" in definition: \""+propsStrArr[j]+"\"");
			}
			propsMap.put(prop, value);
		}
		
		
		for (int j = 0; j < selectorsStrArr.length; j++) {
			String selector = selectorsStrArr[j].trim();
			if(!isValidSelector(selector)) {
				throw new CSSParsingException("invalid selector \""+selector+"\"");
			}
			if(rules.containsKey(selector)){
				rules.get(selector).putAll(propsMap);				
			}
			else{
				rules.put(selector, new Rule(selector, propsMap));
			}			
		}
	}	
}

private static boolean isValidSelector(String selector) {	
	return selector.indexOf('.')==0;
}

public static boolean isValidProp(String prop, String value) {
	return true;//TODO
}

public static void main(String[]a){
	
	try {
		Method m = Widget.class.getMethod("setBounds", new Class[]{Object.class});

		System.out.println(m.getName());
	} catch (SecurityException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} catch (NoSuchMethodException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	Map<String, Rule> rules=new HashMap<String, Rule>();
	String css=".class1{background-color:rgb(2,2,2);}";
	try {
		CSSUtils.parseCSS(rules, css);
	} catch (CSSParsingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	System.out.println(rules);
}
}
