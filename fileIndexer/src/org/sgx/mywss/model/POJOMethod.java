package org.sgx.mywss.model;

import java.lang.reflect.TypeVariable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.sgx.json.JSONObject;
import org.sgx.mywss.MYWSSUtils;
/**
 * concrete implementation linking to a java object receiver.
 * because java reflection doesn'0t support getting parameter 
 * names in method declarations, the user have to implement an 
 * extra method String $METHODNAME_param_list(). see PojoMethodTest
 * 
 * @author sgurin
 *
 */
public class POJOMethod extends Method {
	Object pojo;
	java.lang.reflect.Method jmethod ;
	private Map<String, Integer> paramIndex;
//	private Map<String, Integer> parameterOrder;
	
	public POJOMethod(String name, Object pojo) throws Exception {
		super(name, null, null, null);
		this.pojo=pojo;
		init();
	}

	private void init() throws Exception {
		//pojo no debería tener métodos with the same name
		jmethod = MYWSSUtils.getMethodNamed(pojo.getClass(), name);
		if(jmethod==null)
			throw new Exception("not method "+name+" found in "+pojo.getClass());
		TypeVariable<java.lang.reflect.Method>[] tp = jmethod.getTypeParameters();
		Class<?>[] pt = jmethod.getParameterTypes();
//		parameterOrder = MYWSSUtils.getParameterOrder(jmethod);
		this.returnType=MYWSSUtils.java2WSType(jmethod.getReturnType());
		this.attributeTypes = MYWSSUtils.java2WSattributeTypes(jmethod);
	}


	@Override
	public String dispatchMessage(Message m) throws Exception {
		Map<String, JSONObject> wsparams = m.getParameters();		
		if(paramIndex==null)
			paramIndex = MYWSSUtils.getMethodParamIndex(pojo, jmethod);
		
		Set<String> paramIndexKeySet = paramIndex.keySet();
		Object[] params = new Object[paramIndexKeySet.size()];
		Iterator<String> it = paramIndex.keySet().iterator();
		while (it.hasNext()) {
			String name = (String) it.next();
			JSONObject val = wsparams.get(name);
			//TODO: check arr bounds
			params[paramIndex.get(name)]=MYWSSUtils.getJavaObject(val);//.castToObject().castToJavType();
		}
//		Object[] jparams = new Object [wsparams.keySet().size()];
//		for(String pname : wsparams.keySet()) {
//			jparams[parameterOrder.get(pname)] =
//				MYWSSUtils.getJavaObject(wsparams.get(pname));
//		}
		Object jresponse = jmethod.invoke(pojo, params);
		return MYWSSUtils.toJSON(MYWSSUtils.getJavaObject2(jresponse));
		//too much simple?
	}

}
