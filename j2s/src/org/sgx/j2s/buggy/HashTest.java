package org.sgx.j2s.buggy;

import java.util.Hashtable;

public class HashTest {
	public static void main(String[] args) {
		Hashtable m2 = new Hashtable(); /* <- here in java/util/hashtable.js, line 172, this.newElementArray is not a function. 
		*/
	}
}
