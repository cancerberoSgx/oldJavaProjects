package org.sgx.j2s.remoteinvoker;

import java.util.HashMap;
import java.util.Map;

import org.sgx.j2s.utils.DataHelper;
import org.sgx.j2s.utils.ModelHelper;

import com.sun.corba.se.impl.orbutil.ObjectUtility;

public class RemoteManager {

	Map<String, Class> callables;
	public RemoteManager(){
		super();
		callables = new HashMap<String, Class>();
	}
	public void registerCallable(String className, Class callable) {
		callables.put(className, callable);
	}
	public void invoke(String className, String methodName, Map<String, Object>params) {
		Class c = callables.get(className);
		if(c==null)
			throw new RuntimeException(this.getClass()+": there is no callable named "+className);
		
	}
}
