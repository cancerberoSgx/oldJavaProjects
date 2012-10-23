package org.sgx.j2s.buggy;

import java.util.HashMap;
import java.util.Hashtable;

/*
hi all. i'm reporting 2 bugs that unfortunately I don't have the time to fix right now :()
the first is with java.util.HashTable class. The next line fails:
	
	Hashtable m2 = new Hashtable();

error is: java/util/hashtable.js, line 172, this.newElementArray is not a function.

The other error is whenn referencing the java.lang.Byte like this:

boolean b = (new Object() instanceof Byte);

the error is in the same javascript translated line, "Bbyte is not defined". 
If I do @j2sRequireImport java.lang.Byte, it runs OK.

Sorry for always report bugs and never fix them... ;)
*/
public class Instanceof__ {

	/**
	 * if @ j2sRequireImport_  java.lang.Byte its works ok
	 */
	public static void main(String[] args) {
		boolean b = (new Object() instanceof Byte);
	}
	

}
