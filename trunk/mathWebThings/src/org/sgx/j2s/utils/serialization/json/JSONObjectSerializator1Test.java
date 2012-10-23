package org.sgx.j2s.utils.serialization.json;

import java.util.*;

import org.sgx.j2s.utils.DataHelper;
import org.sgx.j2s.utils.ModelHelper;
import org.sgx.j2s.utils.Utils;
import org.sgx.j2s.utils.serialization.Serializator;

public class JSONObjectSerializator1Test {

	String attrib1;
	JSONObjectSerializator1TestBean1 prop1;
	
	
	public JSONObjectSerializator1Test(String attrib1,
			JSONObjectSerializator1TestBean1 prop1) {
		super();
		this.attrib1 = attrib1;
		this.prop1 = prop1;
	}
	

	public String getAttrib1() {
		return attrib1;
	}

	public void setAttrib1(String attrib1) {
		this.attrib1 = attrib1;
	}

	public JSONObjectSerializator1TestBean1 getProp1() {
		return prop1;
	}

	public void setProp1(JSONObjectSerializator1TestBean1 prop1) {
		this.prop1 = prop1;
	}

	
	
	public static void main(String[] args) {		
//		new Patches().runPatches();
		test1();
		System.out.println(Utils.printAssertsFailed());
	}

	private static void test1() {
		JSONObjectSerializator1TestBean1 bean=new JSONObjectSerializator1TestBean1("string", 123, 234, true, true);
		JSONObjectSerializator1Test t = new JSONObjectSerializator1Test("str1", bean);
		t.getClass().getMethods();
		JSONObjectSerializator1 serializator = new JSONObjectSerializator1();
		String s = serializator.serialize(t);
		System.out.println(s);
	}
}
