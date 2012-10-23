package org.sgx.mywss.tests;

import java.util.Map;

import org.sgx.j2s.utils.Utils;
import org.sgx.mywss.Home;
import org.sgx.mywss.HomeMessageListener;
import org.sgx.mywss.MessageNotUnderstoodException;
import org.sgx.mywss.model.AbstractMessageListener;
import org.sgx.mywss.model.Message;
import org.sgx.mywss.model.MessageListener;
import org.sgx.mywss.model.Method;

public class Test1 {

	
	String attr1;
	boolean attr2;
	int attr3;	
	public String getAttr1() {
		return attr1;
	}
	public void setAttr1(String attr1) {
		this.attr1 = attr1;
	}
	public boolean isAttr2() {
		return attr2;
	}
	public void setAttr2(boolean attr2) {
		this.attr2 = attr2;
	}
	public int getAttr3() {
		return attr3;
	}
	public void setAttr3(int attr3) {
		this.attr3 = attr3;
	}
	
	

	public static void main(String[]a){		
		test1();
		test2();
		
	}
	
private static void test2() {
	Home.getInstance().registerMessageListener("org.sgx.mywss.Home", new HomeMessageListener());
	}

/**
 * muestra de c�mo el programador java puede definir (de la forma dif�cil) sus manejadores de metodos remotos
 * 
 * navigating to http://localhost:8080/fileIndexer/MYWS?endpoint=endpoint1&msg={"name":"plus", "params":{"operand1":2, "operand2":2}} 
 * returns 
 * {"error":false,"response":4.0}
 * tested!
 */
	private static void test1() {
		
		//registramos 
		Home.getInstance().registerMessageListener("endpoint1", new AbstractMessageListener(){			

			Method[] methods = new Method[]{
					
					//PROGRAMATICALLY WE DEFINE OUR METHOD SIGNATURES AND BODIES
					
					new Method("plus", (Map)Utils.toMap(new Object[][]{ //method parameters
							{"operand1", Method.TYPE_NUMBER},
							{"operand2", Method.TYPE_NUMBER}
					}), Method.TYPE_NUMBER, //method return type
					"@returns operand1+operand2" //method comment (javadoc format)
					){	
						public String dispatchMessage(Message m)throws MessageNotUnderstoodException {
							if(m.getName().equals("plus")) {
								Number op1 = m.getParameters().get("operand1").castToNumber(),
								op2=m.getParameters().get("operand2").castToNumber();
								return (op1.floatValue()+op2.floatValue())+"";
							}
							else{
								throw new MessageNotUnderstoodException(m.getName());
							}
						}
					},
					new Method("pow", (Map)Utils.toMap(new Object[][]{ 
							{"operand1", Method.TYPE_NUMBER},  //method parameters
							{"operand2", Method.TYPE_NUMBER}
						}), Method.TYPE_NUMBER,        //method return type
						"@returns operand1 ^ operand2" //method comment (javadoc format)
					){	//method body:
						public String dispatchMessage(Message m)throws Exception {							
						if(m.getName().equals("pow")) {
							Number op1 = m.getParameters().get("operand1").castToNumber(),
								op2=m.getParameters().get("operand2").castToNumber();
							return (Math.pow(op1.doubleValue(), op2.doubleValue()))+"";
						}
						else
							throw new MessageNotUnderstoodException(m.getName());
						}
					}
			};
			public Method[] getMethods() {
				return methods;
			}			
		});
	}
}

