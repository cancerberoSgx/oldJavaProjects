package org.sgx.pickturemakeup.gui.editors.color;


import java.util.LinkedList;
import java.util.List;

import javax.swing.event.ChangeListener;

import org.sgx.pickturemakeup.gui.ImageWidgetImpl;
import org.sgx.pickturemakeup.gui.editors.PropertyEditor;
import org.sgx.pickturemakeup.model.PropertyChangeNotifier;
import org.sgx.pickturemakeup.model.PropertyChangeObserver;


public class ColorEditor extends PropertyEditor 
		implements PropertyChangeNotifier {

//	List<ImageWidgetImpl> imgs = new LinkedList<ImageWidgetImpl> ();
	
	public ColorEditor(String propId) {
		super(propId);
	}

//	public void registerSelectionImg(ImageWidgetImpl img) {
//		img.registerForColorSelectionEvent(new ImageColorSelectionListener() {
//			
//		});
//	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

//	@Override
//	public Object getPropertyValue(Class c) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	public void register(String propId, PropertyChangeObserver o) {
		// TODO Auto-generated method stub
		
	}
	

}
