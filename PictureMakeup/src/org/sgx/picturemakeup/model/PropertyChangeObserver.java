package org.sgx.picturemakeup.model;


/**
 * simple observer mechanism for ContinuousSlider 
 * @author sgx
 *
 */
public interface PropertyChangeObserver {
	

	public void notify(String propId, Object value);

}
