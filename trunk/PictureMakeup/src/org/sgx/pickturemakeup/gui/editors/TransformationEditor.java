package org.sgx.pickturemakeup.gui.editors;

import org.sgx.pickturemakeup.gui.ImageWidgetImpl;
import org.sgx.pickturemakeup.model.ImageTransformation;
import org.sgx.pickturemakeup.model.Property;
import org.sgx.pickturemakeup.model.PropertyHaver;
import org.sgx.pickturemakeup.model.PropertyListener;


/**
 * asocio un ImageWiodget con una transformacion 
 * 
 * TODO: asociaci�n a multiples imagewidgets
 * @author sgx
 *
 */
public class TransformationEditor extends PropertHaverEditorPanel{
	
	private static final long serialVersionUID = 1L;
	
	ImageWidgetImpl img;
	
	public TransformationEditor(PropertyHaver haver, ImageWidgetImpl img) {
		super(haver);
		if(! (haver instanceof ImageTransformation))
			throw new RuntimeException("my propertyHaver must be an ImageTransformation and you pass "+haver.getClass());
		this.img=img;
		haver.register(new PropertyListener() {

			public void notifyPropertyChange(String propId, Object value) {
				TransformationEditor.this.img.showTransformation(
						(ImageTransformation) TransformationEditor.this.haver
				);
			}
			
		});
	}

	@Override
	public PropertyEditor getComponentFor(Property p) {
		
		return super.getComponentFor(p);
	}
	
}
