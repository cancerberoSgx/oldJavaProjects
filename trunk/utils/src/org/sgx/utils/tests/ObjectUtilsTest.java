package org.sgx.utils.tests;

import org.sgx.utils.ObjectUtils;

import sun.security.action.GetIntegerAction;
import junit.framework.TestCase;

public class ObjectUtilsTest extends TestCase {

	/**
	 * javabean ObjectUtilsTestBean has the following properties:
	 * foo String, integer Integer, ;
	this.real = real;
	this.bool1
	 *
	 */
	public void testTransformArrayToMap() {
		fail("Not yet implemented");		
	}

	public void testTransformObjectToMap() {
		fail("Not yet implemented");
	}

	public void testTransformArrToBean() {
		ObjectUtilsTestBean b = (ObjectUtilsTestBean) ObjectUtils.transformArrToBean_(
				new Object[][]	{
						{"foo", "uno"},
						{"integer", 1},
						{"real", 1.0}, 
						{"bool1", true}
				}, ObjectUtilsTestBean.class);
		assertEquals("uno", b.getFoo());
		assertTrue(1==b.getInteger());
		assertEquals(1.0, b.getReal());
		assertTrue(true==b.getBool1());
	}

}
