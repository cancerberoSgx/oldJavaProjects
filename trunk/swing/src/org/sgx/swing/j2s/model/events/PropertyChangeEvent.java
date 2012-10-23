package org.sgx.swing.j2s.model.events;



public class PropertyChangeEvent implements Event{
	String pid; 
//	Object oldVal; 
	Object newVal;EventTarget target;
	public PropertyChangeEvent(EventTarget target, String pid, Object newVal) {
		super();
		this.pid = pid;
//		this.oldVal = oldVal;
		this.newVal = newVal;
		this.target=target;
	}

	public String getPid() {
		return pid;
	}
//	public Object getOldVal() {
//		return oldVal;
//	}

	public Object getNewVal() {
		return newVal;
	}

	public EventTarget getTarget() {
		return target;
	}
	
	public String toString() {
		return "PChangeEvent("+target+","+pid+","+newVal+")";
	}
}
