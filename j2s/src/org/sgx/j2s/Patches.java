package org.sgx.j2s;


public class Patches {
	public void runPatches() {
		Boolean b = new Boolean(true);
		Class c = Boolean.class;
		/**((new Character('c')).equals('c') don't apply
		 * @j2sNative
		 * java.lang.Character.prototype.equals=function(obj){return this.toString().equals(obj+"");};
		 * 
		 */{}
		
		 /**((new Boolean(true)).equals(true) don't apply
		  * @j2sNative
		  * Boolean.prototype.equals=function(obj){return this.toString().equals(obj+"");};
		  */{}
		  
		 /**
		  * Boolean.class doesn't have a getName method:
		 * @j2sNative
		 * Boolean.getName=function(){return "java.lang.Boolean";};
		 */{}
		 
		 /**
		  * Boolean.class and others don't have a getMethods() method:
		 * @j2sNative
		 * Boolean.getMethods=function(){return [];};
		 * java.lang.Byte.getMethods=function(){return [];};
		 * java.lang.Double.getMethods=function(){return [];};
		 * java.lang.Float.getMethods=function(){return [];};
		 * java.lang.Integer.getMethods=function(){return [];};
		 * java.lang.Short.getMethods=function(){return [];};
		 * java.lang.String.getMethods=function(){return [];};
		 * java.lang.Number.getMethods=function(){return [];};
		 * java.lang.String.toString=function(){return "java.lang.String";};
		 * java.lang.Long .getMethods=function(){return [];};
		 */{}
			 
	}
}
