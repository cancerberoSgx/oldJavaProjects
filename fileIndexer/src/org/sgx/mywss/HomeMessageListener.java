package org.sgx.mywss;

import java.util.Map;

import org.sgx.j2s.utils.Utils;
import org.sgx.json.JSONHome;
import org.sgx.json.JSONObject;
import org.sgx.mywss.model.AbstractMessageListener;
import org.sgx.mywss.model.Message;
import org.sgx.mywss.model.Method;

/**
 * i provide a HOME api throught a MYWSS message listener
 * the methods are:
 * public Collection<String> listEndpoints() 
	public boolean hasEndpoint(String endpoint)
	public Collection<String> listMethodsOf(String endpoint) throws NoSuchEndpointException 
	
	
	for example navigating to
	
	http://localhost:8080/fileIndexer/MYWS?endpoint=org.sgx.mywss.Home&msg={"name":"listEndpoints", "params":{}} 
	will return an array of defined endpoints. 
	
	navigating to 
	
http://localhost:8080/fileIndexer/MYWS?endpoint=org.sgx.mywss.Home&msg={"name":"getMethod", "params":{"endpoint":"org.sgx.mywss.Home", "methodName":"getMethod"}}

 * @author sgurin
 *
 */
public class HomeMessageListener extends AbstractMessageListener{

	static Method[] methods = new Method[]{
		new Method("listEndpoints", (Map)Utils.toMap(new Object[][]{}), 
				Method.TYPE_ARRAY, "@return the registered endpoint names"){
			public String dispatchMessage(Message m)throws Exception {
				return Utils.toString(Home.getInstance().listEndpoints());
		}},
		
		new Method("hasEndpoint", (Map)Utils.toMap(new Object[][]{ //method parameters
				{"endpoint", Method.TYPE_STRING}
			}), Method.TYPE_BOOLEAN, "@return true iff a listener was registered for given endpoint"	){
			public String dispatchMessage(Message m)throws Exception {
				Map<String, JSONObject> params = m.getParameters();
				JSONObject endpoint=params.get("endpoint");
				if(endpoint==null || !endpoint.isString())
					throw new MessageNotUnderstoodException("invalid endpoint message attribute");
				return Home.getInstance().hasEndpoint(endpoint.castToString())+"";
		}},
		
		new Method("listMethodNames", (Map)Utils.toMap(new Object[][]{ //method parameters
				{"endpoint", Method.TYPE_STRING}
		}), Method.TYPE_ARRAY,
		" * @return the names of methods defined for parameter endpoint" +"\n"+
		" * @throws NoSuchEndpointException"){
			public String dispatchMessage(Message m)throws Exception {
				Map<String, JSONObject> params = m.getParameters();
				JSONObject endpoint=params.get("endpoint");
				if(endpoint==null || !endpoint.isString())
					throw new MessageNotUnderstoodException("invalid endpoint message attribute");
				return Utils.toString(Home.getInstance().listMethodNames(endpoint.castToString()));
		}},
		
		new Method("getMethod", (Map)Utils.toMap(new Object[][]{ //method parameters
				{"endpoint", Method.TYPE_STRING},{"methodName", Method.TYPE_STRING}
		}), Method.TYPE_OBJECT,
		" * @return the method object corresponding to the parameters or null if there is not such a method defined" 
		){
			public String dispatchMessage(Message m)throws Exception {
				Map<String, JSONObject> params = m.getParameters();
				String endpoint = (String) MYWSSUtils.castOrThrowException(params.get("endpoint"), String.class, "invalid endpoint message attribute");			
				String methodName = (String) MYWSSUtils.castOrThrowException(params.get("methodName"), String.class, "invalid methodName message attribute");				
				String r = MYWSSUtils.toJSON(
					Home.getInstance().getMethod(endpoint, methodName).toMap()				
				);
				return r;
		}}
	};
	
	public Method[] getMethods() {
		return methods;
	}
}
