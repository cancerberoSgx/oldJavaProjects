package org.sgx.json;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.sgx.j2s.utils.Utils;
import org.stringtree.json.JSONReader;

public class JSONTESTs {
	public static void main(String[] args) {
		try {
			JSONTESTs b = new JSONTESTs();
			b.simplejsontest();
			b.simplejsonWriteTest();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	String attr1;	public String getAttr1() {	return attr1;}
	public void setAttr1(String attr1) {this.attr1 = attr1;}	
	private void simplejsonWriteTest() {

	}

	private void simplejsontest() throws Exception {
		String array1 = "[{\"attr1\":\"blabla\", \"attr2\":{}, \"attr3\":[]}, 1, [1,2], [[1,2], 2], 3]";
		JSONObject jo = JSONObjectSimpleImpl.parse(array1);		
		Utils.assertTrue(jo.isArray(), "simplejsontest err1");
		List<JSONObject> arr = jo.castToArray();
		Utils.assertTrue(arr.get(0).isObject() && arr.get(0).castToObject().get("attr1").castToString().equals("blabla"), "simplejsontest err2");
		
		System.out.println(Utils.printAssertsFailed());
		
	}

	private void stringtreejsontest() {
//		String array1 = "{[{'attr1':'blabla', 'attr2':{}, 'attr3':[]}, 1, [1,2], [[1,2], 2], 3]}";
		JSONReader reader = new JSONReader();
		String array1 = "[]";
		JSONObjectStringTreeImpl array1Obj = new JSONObjectStringTreeImpl(reader.read(array1));
		Utils.assertTrue(array1Obj.isArray(), "err1");
		System.out.println(Utils.printAssertsFailed());
	}


}
