package org.sgx.j2s.utils;

import com.sun.corba.se.impl.javax.rmi.CORBA.Util;


public class StringUtilsTest {


	public static void main(String[] args){
//		StringUtilsTest.test1();
		test2();
//		System.out.println(Utils.printAssertsFailed());
	}

	private static void test2() {
		String s="esto es una oración MUY MUY pero muy larga pero mira que si cheldjlfskd fjl sfdkgkdjkf dkjfgldkfjgl ... MUY LARGA";		
		String justified20=StringUtils.fullJustify(s, 30);
		System.out.println(Utils.quote(justified20));
		
		String s2="esto es uasdas MUY LARGA";		
		String centered80 = StringUtils.centerJustify(s2, 80);
		System.out.println(Utils.quote(centered80));
	}

	static void test1() {
		String s = StringUtils.caseInvert("heLLo");
		System.out.println(StringUtils.caseTitle("hello my darlin HELLO"));
		Utils.assertTrue(StringUtils.caseTitle("hello my darlin HELLO").equals("Hello My Darlin HELLO"), 
				"caseTitle 1. was: "+StringUtils.caseTitle("hello my darlin HELLO"));
		Utils.assertTrue(StringUtils.caseSentence("hello. my.darlin.   HELLO").equals("Hello. My.Darlin.   HELLO"), 
				"caseSentence 1. was: "+StringUtils.caseSentence("hello. my.darlin.   HELLO"));
		Utils.assertTrue(StringUtils.caseInvert("heLLo").equals("HEllO"), 
				"caseInvert 1. was: "+StringUtils.caseInvert("heLLo"));
	}

}
