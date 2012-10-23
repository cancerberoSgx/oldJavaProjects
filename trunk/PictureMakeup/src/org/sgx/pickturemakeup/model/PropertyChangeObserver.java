package org.sgx.pickturemakeup.model;


/**
 * simple observer mechanism for ContinuousSlider 
 * @author sgx
 *
 */
public interface PropertyChangeObserver {
	

	public void notify(String propId, Object value);

}
