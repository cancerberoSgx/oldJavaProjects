package org.sgx.j2s.model.serialization.json;

import org.sgx.j2s.model.serialization.Deserializator;
import org.sgx.j2s.model.serialization.ParseException;

public class JsonDeserializatorTry implements Deserializator {
/**
 * s must be a "trimmed" string
 */
	public Object deserialize(String s_)  throws ParseException{
		String s = s_.trim();
		if(s==null || s.length()==0)
			return null;
		else {
			int l = s.length();
			char c = s.charAt(0);
			if(c=='"'&&l>=2) {//string
				if(s.charAt(l-1)=='"')
					throw new ParseException("string parsing error: s.charAt(l-1)!='\"'");				
				return s.substring(1,l-2);
			}
			if(c=='"') {//string
				
			}
			else if(c=='[') {//array
				
			}
			else if(c=='[') {
				
			}
			else  if(c=='{') {
				
			}
			else {
				throw new ParseException("error parsing value literal: "+s);
			}
			return null;
		}
	}
	

}
