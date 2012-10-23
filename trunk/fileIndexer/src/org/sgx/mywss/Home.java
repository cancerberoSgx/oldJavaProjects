package org.sgx.mywss;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.sgx.j2s.utils.Predicate;
import org.sgx.j2s.utils.Predicate2;
import org.sgx.j2s.utils.Utils;
import org.sgx.json.JSONHome;
import org.sgx.json.JSONObject;
import org.sgx.json.JSONParseException;
import org.sgx.mywss.model.Message;
import org.sgx.mywss.model.MessageListener;
import org.sgx.mywss.model.Method;
import org.sgx.mywss.model.POJOMessageListener;

/**
 * concepts:
 * ENDPOINT: an identificator that can be used for reference an endpoint capable of receive messages
 * 
 * TODO: hacer un "HomeListener" que acepte los m�todos de esta home y registrarlo en esta home
 * @author sgurin
 *
 */
public class Home {
	private static Home instance;
	private Map<String, MessageListener> listeners;

	private Home() {
		listeners = new HashMap<String, MessageListener>();
	}

	public static Home getInstance() {
		if (null == instance) {
			instance = new Home();
		}
		return instance;
	}
	public void registerMessageListener(String endPoint, MessageListener listener){
		listeners.put(endPoint, listener);
	}
	/**
	 * examina todos los m�todos del objeto pasado y crea un MessageListener que mediante reflection invoca a los m�todos de este bean.
	 * @throws Exception 
	 */
	public void registerPojoMessageListener(String endpoint, Object bean) throws Exception{		
		registerMessageListener(endpoint, new POJOMessageListener(bean));
	}
	public String dispatchMessage(String endpoint, Message m) throws Exception {
		if(!listeners.containsKey(endpoint))
			throw new NoMessageListenerException("no message listener defined for "+endpoint);
		String response = listeners.get(endpoint).dispatchMessage(m);
		return response;
	}
	public String dispatch(String endpoint, String jsonmsg) throws Exception{
		Message msg = decodeMessageUrl(jsonmsg);
		return dispatchMessage(endpoint, msg);
	}
	/**the total url will be something like:
	 * 
	 * $MAIN_SERVLET_URL?endpoint=endpointId&cmd=sendMessage&msg={'name':'method1', 'params':{'param1':paramvalue}}
	 * 
	 * @throws JSONParseException 
	 * @throws MessageFormatException 
	 * @throws Exception 
	 */
	public Message decodeMessageUrl(String json) throws JSONParseException, MessageFormatException{
		JSONObject jo = JSONHome.getInstance().parseJSON(json);
		if(!jo.isObject())
			throw new MessageFormatException("msg param must be an json valid object");
		Map<String, JSONObject> msgobj = jo.castToObject();
		String name = msgobj.get("name").castToString();
		Map<String, JSONObject> params = msgobj.get("params").castToObject();
		Message msg = new Message(name, params);
		return msg;
	}
	/**
	 * @return the registered endpoint names
	 */
	public Collection<String> listEndpoints() {
		return listeners.keySet();
	}
/**
 * @return true iff a listener was registered for given endpoint
 */
	public boolean hasEndpoint(String endpoint) {
		return listeners.containsKey(endpoint);
	}

	/**
	 * @return the names of methods defined for parameter endpoint
	 * @throws NoSuchEndpointException
	 */
	public Collection<String> listMethodNames(String endpoint) throws NoSuchEndpointException {
		if(hasEndpoint(endpoint)){
			return Utils.select2(listeners.get(endpoint).getMethods(), new Predicate2<Method,String>(){
				
				public String select(String obj) {
					return null;
				}						
			});
		}
		else{
			throw new NoSuchEndpointException(endpoint);
		}
	}

	public Method getMethod(String endpoint, final String methodName) {
		if(hasEndpoint(endpoint)){
			Collection selected = Utils.select(listeners.get(endpoint).getMethods(), new Predicate<Method>(){
				public boolean select(Method o) {
					return methodName.equals(o.getName());
				}
			});
			if(selected.size()==1)
				return ((Method[]) selected.toArray(new Method[selected.size()]))[0];
			else
				return null;
		}
		else
			return null;
	}
}
