package org.sgx.mywss.model;

import org.sgx.mywss.MessageNotUnderstoodException;

public abstract class AbstractMessageListener implements MessageListener{

	public String dispatchMessage(Message m) throws Exception {
		Method[] mts = getMethods();
		for (int i = 0; i < mts.length; i++) {
			if(mts[i].getName().equals(m.getName())){
				return mts[i].dispatchMessage(m);
			}
		}
		throw new MessageNotUnderstoodException(m.getName());
	}

	public abstract Method[] getMethods();
}
