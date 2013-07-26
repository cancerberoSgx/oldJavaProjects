package org.sgx.picturemakeup.gui.editors;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

import org.sgx.picturemakeup.gui.editors.color.ColorEditor;
import org.sgx.picturemakeup.model.ListProperty;
import org.sgx.picturemakeup.model.MatrixProperty;
import org.sgx.picturemakeup.model.NumericProperty;
import org.sgx.picturemakeup.model.Property;
import org.sgx.picturemakeup.model.PropertyChangeObserver;
import org.sgx.picturemakeup.model.PropertyHaver;


/**
 * encargada de asociar un editor a cada clase 
 * brinda las facilidades de edici�n de propiedades de cualquier PropertyHaver
 * @author sgx
 *
 */
public class PropertHaverEditorPanel extends JPanel {

	public PropertyHaver haver;
	Map<String,PropertyEditor> propComps = new HashMap<String,PropertyEditor>();
	private boolean autoApply;
	JButton applyButton;
	
	public PropertHaverEditorPanel(PropertyHaver haver, boolean autoApply) {
		super();
		this.haver=haver;
		this.autoApply=autoApply;
		initialize();		
	}
	public PropertHaverEditorPanel(PropertyHaver haver) {
		this(haver, true);
	}
	
	private void initialize() {
		Property[] props = haver.getProperties();
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS) );
		for(int i = 0; i< props.length; i++) {			
			PropertyEditor c = getComponentFor(props[i]);
			propComps.put(props[i].id, c);
			this.add(c);
		}
		
		applyButton=new JButton("Apply");
//		applyButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				Property[] props = haver.getProperties();
//				for(int i = 0; i< props.length; i++) {
//					haver.changePropertyValue(props[i].id,
//							propComps.get(props[i].id).getPropertyValue(null)
//					);
//				}
//			}			
//		});
		this.add(applyButton);
	}



	public PropertyEditor getComponentFor(final Property p) {
		PropertyEditor c = null;
		if(p.getDefaultValue() instanceof Float || p.getDefaultValue() instanceof Double) {
			if(p instanceof NumericProperty) {
				c = new ContinuousSlider(p.getId(), p.getId(),
						haver.castValueToDouble(((NumericProperty)p).getMin()),
						haver.castValueToDouble(((NumericProperty)p).getMax()),
						haver.castValueToDouble(((NumericProperty)p).getDefaultValue())			
				);
				if(autoApply) {
				c.register(null, new PropertyChangeObserver() {					
					public void notify(String propId, Object value) {						
						PropertHaverEditorPanel.this.haver.changePropertyValue(p.getId(), value);
					}					
				});				
				}
			}
			else 
				throw new RuntimeException("Property not understood - "+p);
		}
		else if(p.getDefaultValue() instanceof Integer) {
			if(p instanceof NumericProperty) {
				c = new DiscreteSlider(p.getId(), p.getId(), 
						(int)(((Integer)((NumericProperty)p).getMin()).intValue()), 
						(int)(((Integer)((NumericProperty)p).getMax()).intValue()),
						(int)(((Integer)((NumericProperty)p).getDefaultValue()).intValue())
					);
				if(autoApply) {
				c.register(p.getId(), new PropertyChangeObserver() {					
					public void notify(String propId, Object value) {
						PropertHaverEditorPanel.this.haver.changePropertyValue(propId,value);						
					}					
				});		
				}
			}
			else 
				throw new RuntimeException("Property not understood - "+p);
		}
		else if(p.getDefaultValue() instanceof Color) {
			ColorEditor csel = new ColorEditor(p.getId());
			//TODO
		}
		else if(p instanceof MatrixProperty) {
			MatrixProperty m = (MatrixProperty)p;
			c = new MatrixEditor(p.getId(), m.getN(), m.getM());
			if(autoApply) {
				c.register(null, new PropertyChangeObserver() {					
					public void notify(String propId, Object value) {						
						PropertHaverEditorPanel.this.haver.changePropertyValue(p.getId(), value);
					}					
				});				
			}
			//TODO
		}
		else if(p instanceof ListProperty) {
			ListProperty m = (ListProperty)p;
			c = new ListSelectionEditor(p.getId(), ((ListProperty)p).getList());
			if(autoApply) {
				c.register(null, new PropertyChangeObserver() {					
					public void notify(String propId, Object value) {						
						PropertHaverEditorPanel.this.haver.changePropertyValue(p.getId(), value);
					}
				});
			}
			//TODO
		}
		else
			throw new RuntimeException("Property not understood - "+p+" defaultValue: "+p.getDefaultValue());
		return c;
	}

	private static final long serialVersionUID = 1L;
	public void setHaver(PropertyHaver haver) {
		this.haver = haver;
	}

	public PropertyHaver getHaver() {
		return haver;
	}


}
