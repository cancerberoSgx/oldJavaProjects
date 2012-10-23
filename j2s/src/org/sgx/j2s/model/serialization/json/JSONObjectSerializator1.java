package org.sgx.j2s.model.serialization.json;

//import org.sgx.j2s.model.serialization.ObjectSerializator;
import org.sgx.j2s.model.serialization.Serializator;
import org.sgx.j2s.model.util.DataHelper;
import org.sgx.j2s.model.util.ModelHelper;
import org.sgx.j2s.util.Iterator;
import org.sgx.j2s.util.Map;
import org.sgx.j2s.util.impl.Utils;

public class JSONObjectSerializator1 implements Serializator {
	DataHelper helper = null;
	public JSONObjectSerializator1() {
		helper =DataHelper.getInstance();
	}
	public String getClassName(Object o) {
		return o.getClass().getName();
	}

	public String serialize(Object o)  {
		return serializeObject(o,0);
	}

	/**
	 * xml array repr
	 * @param o
	 * @return
	 */
	private String serializeArray(Object o) {
		Object[]a = helper.castArray(o);
		String s="[";
		for(int i=0;i<a.length;i++) {
			if(i!=a.length-1)
				s=s+a[i]+",";	
		}
		return s+"]";
	}

	public static String toStringTab="  ";
	public static String tab(int tabDeep) {
		String s = "";
		for (int i = 0; i < tabDeep; i++) 
			s+=toStringTab;		
		return s;
	}
	/**
	 * @return a java, javascrpipt, json object compatible repr.
	 */
	public String serializeObject(Object obj, int tabDeep) {
		Map<String, Class> m = ModelHelper.getProperties(obj);
		Iterator<String> i = m.keys().iterator();
		String s = "{", tab = tab(tabDeep);
		while(i.hasNext()) {			
			String k = i.next();
			
			Object o=ModelHelper.get(obj, k);
			String valStr="";
			if(o==null)
				valStr="";
			else if(helper.isArray(o)) {
				Object[]a = helper.castArray(o);
				valStr=serializeArray(a);
			}
			else if(helper.isDatatype(o)) {
				valStr= serializeDT(o);
			}
			else {//normal object
				valStr=serializeObject(o, tabDeep+1);
			}
			s+="\""+k+"\":"+valStr;
			if(i.hasNext())
				s+=", ";
		}
		return s+"}";

	}


	private String serializeDT(Object o) {
		return helper.serializeDatatype(o);
	}

}
