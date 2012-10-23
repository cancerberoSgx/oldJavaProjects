package org.sgx.mywss.tests.pojomethod;

import java.util.List;
import java.util.Map;

import org.sgx.json.JSONObject;

public class Pojo1 {
	static int counter=0;	
	public String[] method1(JSONObject attr1, JSONObject attr2, JSONObject attr3){
		counter++;
		return new String[]{"hello "+counter, "- ssss.-"};
	}
	public String method1_param_list(){
		return "attr1,attr2,attr3";
	}
	
	public String[] method2(String attr1, List<JSONObject> attr2, Map<String, JSONObject> attr3){
		counter++;
		return new String[]{"hello "+counter, "- ssss.-"};
	}
	public String method2_param_list(){
		return "attr1,attr2,attr3";
	}
}
