package org.sgx.picturemakeup.model;

/**
 * simple observer mechanism for ContinuousSlider 
 * @author sgx
 *
 */
public interface PropertyChangeNotifier {
	
	public void register(String propId, PropertyChangeObserver o);
	
}
