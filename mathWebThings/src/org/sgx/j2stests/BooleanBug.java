package org.sgx.j2stests;
/**
 * @j2sP  refix
oldEval = eval;
eval=function(o) {
	  if (Clazz.instanceOf (o, Boolean)) {
	  	return o.toString().equals("true");
	  }
	  else {
	  	return oldEval(o);
	  }
}
*/
public class BooleanBug {
	public static void main(String[] args) {
		Boolean b = new Boolean(false);
		if(b instanceof Boolean) {
			
		}
		if(!b) {
			System.out.println("OK");
		}
		else {
			System.out.println("BUG!");
		}
	}
}
