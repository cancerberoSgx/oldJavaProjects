package org.sgx.j2s.utils;

import org.sgx.j2s.utils.serialization.ParseException;

public class DataHelperTest {

	public static void main(String[] args) {
//		new Patches().runPatches();
		Utils.cleanTests();		
		DataHelperTest h =  new DataHelperTest();
		h.isDatatypeTest();
		h.j2sTests();
		h.isArrayTest();
		h.castArrayTest();
		try {
			h. datatypeSerializationTest1();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 System.out.println(Utils.printAssertsFailed() );
	}

	public void castArrayTest() {
		int[]i = {1,2,3};
		String [] s = {", ", "", "s"};
		double[] d = {1.1,2.2,3.3};
		double[] f = {1.1f,2.2f,3.3f};
		DataHelper h = DataHelper.getInstance();
		try {
			Object[]o=null;
			o=(Object[])h.castArray(i);
			o=(Object[])h.castArray(s);
			o=(Object[])h.castArray(d);
			o=(Object[])h.castArray(f);

		}catch (Exception e) {
			Utils.assertTrue(false, "castArrayTest : "+e.getLocalizedMessage());
		}


	}

	public void j2sTests() {
		Class<double[]> cc = double[].class;
		double [] da = new double[]{1,1};
		Utils.assertTrue(/*JsUtils.isJs()&&*/(da instanceof double[]), "j2sTests2");	

		Object ii = 2;
		Utils.assertTrue((new Integer("2")) instanceof Integer, "j2sTests 3");
		Utils.assertTrue((ii instanceof Integer), "j2sTests 4");
		
	}
	
	public void isDatatypeTest() {
		DataHelper h = DataHelper.getInstance();
		Utils.assertTrue(h.isDatatype(123), "isDatatypeTest 1");
		Utils.assertTrue(h.isDatatype(new Integer(123)), "isDatatypeTest 1.1");
		Utils.assertTrue(h.isDatatype("hi"), "isDatatypeTest 2");
		Utils.assertTrue(h.isDatatype(1.1111), "isDatatypeTest 3");
		Utils.assertTrue(h.isDatatype(1.222f), "isDatatypeTest 4");
		Utils.assertTrue(h.isDatatype(true)&&h.isDatatype(false), "isDatatypeTest 5");

		Utils.assertTrue(!h.isDatatype(new String[]{}), "isDatatypeTest 6");
		Utils.assertTrue(!h.isDatatype(new int[]{1,2,3}), "isDatatypeTest 8");
		Utils.assertTrue(!h.isDatatype(new Object()), "isDatatypeTest 9");
	}


	public static Character getc(){return 'c';}

	public void datatypeSerializationTest1() throws ParseException {
		int i = 1234;
		double d = 1.234;
		float f = 1.234f;
		char c = '1';
		byte b = (byte)13;
		Object I=null;
		DataHelper h =  DataHelper.getInstance();

		I =  h.deserializeDatatype("c", "java.lang.Character");
		Utils.assertTrue(I.equals('c'), "datatypeSerializationTest1()__ 1a :"+I);
		Utils.assertTrue(I instanceof Character, "datatypeSerializationTest1() 1b");

		I =  h.deserializeDatatype("1234", "java.lang.Integer");
		Utils.assertTrue(I.equals(1234),"datatypeSerializationTest1() 2a");
		Utils.assertTrue(I instanceof Integer, "datatypeSerializationTest1() 2b");
		Utils.assertTrue(h.serializeDatatype(i).equals("1234"), "datatypeSerializationTest1() 2c");

		I =  h.deserializeDatatype("1.234", "java.lang.Double");		
		Utils.assertTrue(I.equals(1.234)&&I instanceof Double&&
				h.serializeDatatype(d).equals("1.234"), "datatypeSerializationTest1() 3");

		I =  h.deserializeDatatype("1.234", "java.lang.Float");
		Utils.assertTrue(I.equals(1.234f)&&I instanceof Float&&
				(h.serializeDatatype(f).equals("1.234f")||h.serializeDatatype(f).equals("1.234")), 
		"datatypeSerializationTest1() 4");

		//TODO: for all 8 scale types... :(
	}

	public void isArrayTest() {
		DataHelper h = DataHelper.getInstance();
		Utils.assertTrue(h.isArray(new int[]{1,2,3}), "isArrayTest 1");
		Utils.assertTrue(!h.isArray(new Object()), "isArrayTest 2");
		Utils.assertTrue(!h.isArray(123), "isArrayTest 3");
		Utils.assertTrue(!h.isArray("sadf"), "isArrayTest 4");
		Utils.assertTrue(h.isArray(new String[]{"1","2"}), "isArrayTest 5");
		Utils.assertTrue(h.isArray(new Object[]{null}), "isArrayTest 6");
		Utils.assertTrue(h.isArray(new Object[]{}), "isArrayTest 7");
	}
}
