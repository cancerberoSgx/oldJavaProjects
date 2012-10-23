package org.stringtree.json.mytests;

import java.util.ArrayList;

import org.stringtree.json.JSONReader;

public class MyTest1 {
	public static void main(String[] args) {
		new MyTest1().test1();
	}

	private void test1() {
		String array1 = "[{'attr1':'blabla', 'attr2':{}, 'attr3':[]}, 1, [1,2], [[1,2], 2], 3]";
		JSONReader reader = new JSONReader();
		Object array1Obj = reader.read(array1);
		System.out.println(array1Obj.getClass());
	}

	public boolean isArray(Object o) {
		return o.getClass().equals(java.util.ArrayList.class);
	}
	public ArrayList castToArray(Object o){
		return (ArrayList) o;
	}
}
